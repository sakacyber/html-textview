package com.saka.android.htmltextview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TextView

class ParagraphView @JvmOverloads constructor(
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
        if (element.text().isNotEmpty()) {
            setText(element.text())
        }
    }

    private fun setText(text: String?) {
        val textView = TextView(context)
        textView.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        textView.textSize = 18f
        val lineSpacing = EManager.dpToPx(context, 12)
        textView.setLineSpacing(lineSpacing, 1f)
        textView.setPadding(0, 24, 0, 24)
        textView.text = text
        textView.setTextColor(Color.BLACK)
        addView(textView)
    }
}
