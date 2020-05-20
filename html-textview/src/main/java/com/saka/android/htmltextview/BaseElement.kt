package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import org.jsoup.nodes.Element

abstract class BaseElement(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        render()
    }

    lateinit var element: Element

    abstract fun render()
}
