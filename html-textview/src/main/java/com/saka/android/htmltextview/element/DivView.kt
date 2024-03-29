package com.saka.android.htmltextview.element

import android.content.Context
import android.util.AttributeSet
import com.saka.android.htmltextview.utility.EManager
import kotlinx.coroutines.CoroutineScope
import org.jsoup.nodes.Element

class DivView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null,
    content: String = "",
    coroutineScope: CoroutineScope? = null
) : BaseElement(context, attrs, defStyleAttr, element, content, coroutineScope) {

    override fun render() {
        orientation = VERTICAL
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        val children = element?.children()
        if (children?.isNotEmpty() == true) {
            EManager.appendView(view = this, elements = children, coroutineScope = scope)
        }
    }
}
