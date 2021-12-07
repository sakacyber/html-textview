package com.saka.android.htmltextview.element

import android.content.Context
import android.util.AttributeSet
import com.saka.android.htmltextview.element.BaseElement
import org.jsoup.nodes.Element

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null,
    content: String = ""
) : BaseElement(context, attrs, defStyleAttr, element, content) {

    override fun render() {
    }
}
