package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet
import org.jsoup.Jsoup

class HtmlTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseElement(context, attrs, defStyleAttr) {

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