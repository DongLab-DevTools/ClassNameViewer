# ClassNameViewer

<div>
  <details open>
    <summary><b>한국어(Korean)</b></summary>
    
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

### 활성화 조건 주입

```kotlin
val settings = ClassNameViewerSettings(
    debugModeCondition = { BuildConfig.DEBUG },
    enabledCondition = { 
        PreferenceManager.getDefaultSharedPreferences(this)
            .getBoolean("debug_overlay_enabled", true)
    }
)
```
- `debudModeCondition`: 디버그 모드 조건을 주입합니다.
- `enabledCondition`: 오버레이 기능 활성화 조건을 주입합니다. 


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

  </details>
</div>

<div>
  <details>
    <summary><b>English</b></summary>

A lightweight Android debug library that displays Activity and Fragment class names as screen overlays during development.

## Overview

ClassNameViewer displays the class names of currently visible Activities and Fragments in real-time on screen.

It significantly improves debugging and development efficiency in apps with complex Fragment structures or frequent screen transitions.

## Features

- **Real-time class name display**: Shows Activity and Fragment class names on screen in real-time
- **Automatic lifecycle management**: Automatically tracks all Activities and Fragments at the Application level
- **Debug-only**: Automatically disabled in release builds for safety
- **UI customization**: Freely configure text size, color, position, etc.
- **Memory safe**: Prevents memory leaks using WeakReference
- **Touch interaction**: Touch overlay to show full class name in toast

## Installation

Add the library to your project:

```kotlin
dependencies {
    implementation 'com.donglab:classnameviewer:1.0.0'
}
```

## Usage

### Initialize in Application class (Recommended)

Set up once and all Activities and Fragments will be automatically tracked:

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

## Configuration

### UI Customization

```kotlin
val config = ClassNameDebugViewerConfig(
    textSize = 12f,                              // Text size
    textColor = Color.WHITE,                     // Text color
    backgroundColor = Color.argb(128, 0, 0, 0),  // Background color
    padding = 16,                                // Padding
    topMargin = 64,                              // Top margin
    activityGravity = Gravity.TOP or Gravity.START,  // Activity display position
    fragmentGravity = Gravity.TOP or Gravity.END     // Fragment display position
)

val lifecycleHandler = ClassNameDebugLifecycleHandler(settings, config)
```

### Conditional Activation

```kotlin
val settings = ClassNameViewerSettings(
    debugModeProvider = { BuildConfig.DEBUG },
    enabledProvider = { 
        // Can be toggled at runtime via SharedPreferences
        PreferenceManager.getDefaultSharedPreferences(this)
            .getBoolean("debug_overlay_enabled", true)
    }
)
```

## System Requirements

- Android API 21+
- Kotlin 1.9+
- AndroidX Activity & Fragment libraries

## License

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

  </details>
</div>
