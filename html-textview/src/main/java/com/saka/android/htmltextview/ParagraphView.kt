package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat

class ParagraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseElement(context, attrs, defStyleAttr) {

    override fun render() {
        orientation = VERTICAL
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        if (element.children().isNotEmpty()) {
            EManager.appendView(this, element.children())
        }
        if (element.text().isNotEmpty()) {
            setText()
        }
    }

    private fun setText() {
        val paragraphView = View.inflate(context, R.layout.paragraph_view, null) as TextView
        paragraphView.text =
            HtmlCompat.fromHtml(element.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        addView(paragraphView)
    }
}
