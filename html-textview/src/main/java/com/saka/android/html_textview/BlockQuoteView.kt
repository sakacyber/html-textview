package com.saka.android.html_textview

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

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
            setText(element.text())
        }
    }

    private fun setText(content: String?) {
        val textView = TextView(context).apply {
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            setLineSpacing(EManager.dpToPx(context, 12), 1f)
            setPadding(0, 24, 0, 24)
            setTextColor(Color.BLACK)
            setTypeface(typeface, Typeface.ITALIC)
            textSize = 18f
            text = content
        }
        addView(textView)
    }
}