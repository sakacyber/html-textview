package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import org.jsoup.Jsoup

class HtmlTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseElement(context, attrs, defStyleAttr) {

    init {
        val att = context.obtainStyledAttributes(R.styleable.HtmlTextView)
        val textSize = att.getFloat(R.styleable.HtmlTextView_htmlTextSize, 16f)
        val textColor = att.getColor(R.styleable.HtmlTextView_htmlTextColor, ContextCompat.getColor(context, R.color.colorTextPrimary))
        val lineSpacing = att.getFloat(R.styleable.HtmlTextView_htmlLineSpacing, 1.2f)
        HtmlConfig.htmlTextSize = textSize
        HtmlConfig.htmlTextColor = textColor
        HtmlConfig.htmlLineSpacing = lineSpacing
        att.recycle()
    }

    override fun render() {
        orientation = VERTICAL
    }

    fun setText(html: String?) {
        val document = Jsoup.parse(html)
        val elementContent = document.body()
        element = elementContent
        EManager.appendView(this, element.children())
        invalidate()
    }
}
