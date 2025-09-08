package com.donglab.classnameviewer.factory

import androidx.activity.ComponentActivity
import com.donglab.classnameviewer.config.ScreenNameOverlayConfig
import com.donglab.classnameviewer.config.ScreenNameViewerSetting
import com.donglab.classnameviewer.overlay.ScreenNameOverlayRenderer
import com.donglab.classnameviewer.viewer.ScreenNameViewer
import com.donglab.classnameviewer.viewer.ScreenNameViewerImpl
import java.lang.ref.WeakReference

object ScreenNameViewerFactory {
    
    /**
     * 설정을 받아 ClassNameDebugViewer를 생성합니다.
     * 
     * @param activity 대상 Activity
     * @param settings 활성화 조건 설정 (필수)
     * @param config UI 설정 (선택사항)
     */
    fun create(
        activity: ComponentActivity,
        settings: ScreenNameViewerSetting,
        config: ScreenNameOverlayConfig = ScreenNameOverlayConfig.defaultConfig()
    ): ScreenNameViewer {
        return ScreenNameViewerImpl(
            activity = activity,
            overlayManager = ScreenNameOverlayRenderer(WeakReference(activity)),
            config = config,
            settings = settings
        )
    }
}