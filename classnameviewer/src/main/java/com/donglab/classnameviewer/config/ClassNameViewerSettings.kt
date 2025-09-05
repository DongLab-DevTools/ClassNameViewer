package com.donglab.classnameviewer.config

/**
 * ClassNameViewer의 활성화 조건을 정의하는 설정 클래스
 */
data class ClassNameViewerSettings(
    val debugModeProvider: () -> Boolean,
    val enabledProvider: () -> Boolean
) {
    val isDebugMode: Boolean
        get() = debugModeProvider()
    
    val isEnabled: Boolean
        get() = isDebugMode && enabledProvider()
}