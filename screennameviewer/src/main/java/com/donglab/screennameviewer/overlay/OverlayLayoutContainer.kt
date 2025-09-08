package com.donglab.screennameviewer.overlay

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

internal class OverlayLayoutContainer(
    private val decorView: ViewGroup?,
    private val context: Context?
) {

    fun createContainer(gravity: Int, topMargin: Int): LinearLayout? {
        return context?.let {
            LinearLayout(it).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(Color.TRANSPARENT)
            }.also { layout ->
                val params = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    this.topMargin = topMargin
                    this.gravity = gravity
                }
                decorView?.addView(layout, params)
            }
        }
    }

    fun addTextView(container: LinearLayout?, textView: TextView) {
        if (!hasTextView(container, textView.tag.toString())) {
            container?.addView(textView)
        }
    }

    fun removeTextView(container: LinearLayout?, name: String) {
        container?.findViewWithTag<View>(name)?.let {
            container.removeView(it)
        }
    }

    fun hasTextView(container: LinearLayout?, name: String): Boolean {
        return container?.findViewWithTag<TextView>(name) != null
    }
}