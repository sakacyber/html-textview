package com.saka.android.htmltextview.element

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.coroutines.CoroutineScope
import org.jsoup.nodes.Element

abstract class BaseElement : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        element: Element? = null,
        content: String = "",
        coroutineScope: CoroutineScope? = null
    ) : super(context, attrs, defStyleAttr) {
        this.element = element
        this.htmlContent = content
        this.scope = coroutineScope
        this.render()
    }

    var htmlContent: String = ""
    var element: Element? = null
    var scope: CoroutineScope? = null

    abstract fun render()
}
