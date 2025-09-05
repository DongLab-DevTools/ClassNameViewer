# ClassNameViewer

개발 중인 안드로이드 앱에서 현재 Activity와 Fragment의 클래스명을 화면에 오버레이로 표시해주는 디버그 라이브러리입니다.

## 개요

ClassNameViewer는 현재 화면에 보이는 Activity와 Fragment의 클래스명을 실시간으로 화면에 표시하는 도구입니다.

복잡한 Fragment 구조나 화면 전환이 많은 앱에서 디버깅과 개발 효율성을 크게 향상시킵니다.

## 특징

- **실시간 클래스명 표시**: Activity와 Fragment 클래스명을 화면에 실시간 표시
- **자동 생명주기 관리**: Application 레벨에서 모든 Activity와 Fragment를 자동으로 추적
- **디버그 전용**: Release 빌드에서는 자동으로 비활성화되어 안전
- **UI 커스터마이징**: 텍스트 크기, 색상, 위치 등 자유롭게 설정 가능
- **메모리 안전**: WeakReference 사용으로 메모리 누수 방지
- **터치 상호작용**: 오버레이 터치 시 Toast로 전체 클래스명 표시

## 설치

프로젝트에 라이브러리를 추가하세요:

```kotlin
dependencies {
    implementation 'com.donglab:classnameviewer:1.0.0'
}
```

## 사용법

### Application 클래스에서 초기화 (권장)

한 번만 설정하면 모든 Activity와 Fragment가 자동으로 추적됩니다:

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        val settings = ClassNameViewerSettings(
            debugModeProvider = { BuildConfig.DEBUG },
            enabledProvider = { true }
        )
        
        val lifecycleHandler = ClassNameDebugLifecycleHandler(settings)
        registerActivityLifecycleCallbacks(lifecycleHandler)
    }
}
```

## 설정

### UI 커스터마이징

```kotlin
val config = ClassNameDebugViewerConfig(
    textSize = 12f,                              // 텍스트 크기
    textColor = Color.WHITE,                     // 텍스트 색상
    backgroundColor = Color.argb(128, 0, 0, 0),  // 배경색
    padding = 16,                                // 패딩
    topMargin = 64,                              // 상단 여백
    activityGravity = Gravity.TOP or Gravity.START,  // Activity 표시 위치
    fragmentGravity = Gravity.TOP or Gravity.END     // Fragment 표시 위치
)

val lifecycleHandler = ClassNameDebugLifecycleHandler(settings, config)
```

### 조건부 활성화

```kotlin
val settings = ClassNameViewerSettings(
    debugModeProvider = { BuildConfig.DEBUG },
    enabledProvider = { 
        // SharedPreferences로 런타임에 켜고 끌 수 있음
        PreferenceManager.getDefaultSharedPreferences(this)
            .getBoolean("debug_overlay_enabled", true)
    }
)
```

## API 레퍼런스

### ClassNameDebugLifecycleHandler

Application 레벨에서 자동으로 모든 Activity/Fragment를 관리하는 핸들러입니다.

```kotlin
class ClassNameDebugLifecycleHandler(
    private val settings: ClassNameViewerSettings,
    private val config: ClassNameDebugViewerConfig = ClassNameDebugViewerConfig.defaultConfig()
)
```

### ClassNameViewerFactory

ClassNameDebugViewer 인스턴스를 생성하는 팩토리 클래스입니다.

```kotlin
fun create(
    activity: ComponentActivity,
    settings: ClassNameViewerSettings,
    config: ClassNameDebugViewerConfig = ClassNameDebugViewerConfig.defaultConfig()
): ClassNameDebugViewer
```

### ClassNameDebugViewer

디버그 오버레이를 관리하는 메인 인터페이스입니다.

```kotlin
interface ClassNameDebugViewer {
    fun initialize()                    // 초기화
    fun registerFragment(fragment: Fragment)  // Fragment 등록
    fun clear()                        // 오버레이 정리
}
```

### ClassNameViewerSettings

활성화 조건을 설정하는 클래스입니다.

```kotlin
data class ClassNameViewerSettings(
    val debugModeProvider: () -> Boolean,  // 디버그 모드 확인
    val enabledProvider: () -> Boolean     // 활성화 여부 확인
)
```

### ClassNameDebugViewerConfig

UI 설정 옵션입니다.

```kotlin
data class ClassNameDebugViewerConfig(
    val textSize: Float,        // 텍스트 크기
    val textColor: Int,         // 텍스트 색상
    val backgroundColor: Int,   // 배경색
    val padding: Int,           // 패딩
    val topMargin: Int,         // 상단 여백
    val activityGravity: Int,   // Activity 표시 위치
    val fragmentGravity: Int    // Fragment 표시 위치
)
```

## 시스템 요구사항

- Android API 21 이상
- Kotlin 1.9 이상
- AndroidX Activity & Fragment 라이브러리

## 라이선스

```
MIT License

Copyright (c) 2024 DongLab

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```