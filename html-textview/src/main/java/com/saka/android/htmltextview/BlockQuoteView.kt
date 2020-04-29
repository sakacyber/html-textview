package com.saka.android.htmltextview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat

class BlockQuoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseElement(context, attrs, defStyleAttr) {

    override fun render() {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        orientation = VERTICAL
        if (element.children().isNotEmpty()) {
            EManager.appendView(this, element.children())
        }
        if (element.text().isNotBlank()) {
            setText()
        }
    }

    private fun setText() {
        val paragraphView = View.inflate(context, R.layout.paragraph_view, null) as TextView
        paragraphView.setTypeface(paragraphView.typeface, Typeface.ITALIC)
        paragraphView.text =
            HtmlCompat.fromHtml(element.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        addView(paragraphView)
    }
}