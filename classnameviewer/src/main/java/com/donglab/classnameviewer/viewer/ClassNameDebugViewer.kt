package com.donglab.classnameviewer.viewer

import androidx.fragment.app.Fragment

interface ClassNameDebugViewer {
    fun initialize()
    fun registerFragment(fragment: Fragment)
    fun clear()
}