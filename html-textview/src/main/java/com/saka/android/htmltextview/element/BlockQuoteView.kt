package com.saka.android.htmltextview.element

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import com.saka.android.htmltextview.utility.EManager
import kotlinx.coroutines.CoroutineScope
import org.jsoup.nodes.Element

class BlockQuoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null,
    content: String = "",
    coroutineScope: CoroutineScope? = null
) : BaseElement(context, attrs, defStyleAttr, element, content, coroutineScope) {
    
    override fun render() {
        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val children = element?.children()
        if (children?.isNotEmpty() == true) {
            EManager.appendView(
                view = this,
                elements = children,
                content = htmlContent,
                coroutineScope = scope
            )
        }
        setText()
    }
    
    private fun setText() {
        val paragraphView = TextView(context)
        paragraphView.setTypeface(paragraphView.typeface, Typeface.ITALIC)
        paragraphView.text = element?.text()
        addView(paragraphView)
    }
}
