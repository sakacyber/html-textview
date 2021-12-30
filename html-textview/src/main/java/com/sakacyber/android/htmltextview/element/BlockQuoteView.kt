package com.sakacyber.android.htmltextview.element

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import com.sakacyber.android.htmltextview.utility.EManager
import org.jsoup.nodes.Element

class BlockQuoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null,
    content: String = ""
) : BaseElement(context, attrs, defStyleAttr, element, content) {

    override fun render() {
        orientation = VERTICAL
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        val children = element?.children()
        if (children?.isNotEmpty() == true) {
            EManager.appendView(this, children, htmlContent)
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
