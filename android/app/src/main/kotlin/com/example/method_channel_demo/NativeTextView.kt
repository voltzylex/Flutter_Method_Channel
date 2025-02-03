// NativeTextViewFactory.kt
package com.example.nativetextviewdemo

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class NativeTextViewFactory : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    override fun create(context: Context, viewId: Int, args: Any?): PlatformView {
        // You can extract arguments if needed
        return NativeTextView(context)
    }
}
class  NativeComplexViewFactory : PlatformViewFactory(StandardMessageCodec.INSTANCE){
    override fun create(context: Context, viewId: Int, args: Any?): PlatformView {
        return NativeComplexView(context)
    }
}
class NativeTextView(context: Context) : PlatformView {
    private val textView: TextView = TextView(context).apply {
        text = "Hello from Native Android TextView!"
        textSize = 20f

    }

    override fun getView(): View {
        return textView
    }

    override fun dispose() {}
}
class NativeComplexView(context: Context) : PlatformView {
    private val rootLayout: LinearLayout = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        // Padding, margin, background, etc.
        setPadding(16, 16, 16, 16)
    }

    private val titleTextView: TextView = TextView(context).apply {
        text = "Multi View Using layout view"
        textSize = 24f
        setTextColor(Color.RED)
        setTypeface(null, Typeface.BOLD)
    }

    private val messageTextView: TextView = TextView(context).apply {
        text = "This is a more complex native UI."
        textSize = 16f
    }

    init {
        rootLayout.addView(titleTextView)
        rootLayout.addView(messageTextView)
    }

    override fun getView(): View = rootLayout

    override fun dispose() {}
}
