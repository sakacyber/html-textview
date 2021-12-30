package com.sakacyber.android.htmltextview.element

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import org.jsoup.nodes.Element

abstract class BaseElement : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        element: Element? = null,
        content: String = ""
    ) : super(context, attrs, defStyleAttr) {
        this.element = element
        this.htmlContent = content
        this.render()
    }

    var element: Element? = null
    var htmlContent: String = ""

    abstract fun render()
}
