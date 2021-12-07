package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.saka.android.htmltextview.element.BaseElement
import com.saka.android.htmltextview.utility.Conf
import com.saka.android.htmltextview.utility.EManager
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class HtmlTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null
) : BaseElement(context, attrs, defStyleAttr, element) {

    init {
        val typeArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.HtmlTextView
        )
        Conf.textSize =
            typeArray.getDimension(
                R.styleable.HtmlTextView_htmlTextSize,
                Conf.DEF_TEXT_SIZE
            )
        Conf.textLineSpacing =
            typeArray.getDimension(
                R.styleable.HtmlTextView_htmlLineSpacing,
                Conf.DEF_LINE_SPACING
            )
        Conf.imageRoundCorner =
            typeArray.getDimension(
                R.styleable.HtmlTextView_htmlImageRound,
                Conf.DEF_IMAGE_ROUND
            )
        Conf.textPadding =
            typeArray.getDimension(
                R.styleable.HtmlTextView_htmlTextPadding,
                Conf.DEF_PADDING
            )
        htmlContent = typeArray.getString(R.styleable.HtmlTextView_htmlContent) ?: ""
        typeArray.recycle()
    }

    override fun render() {
        orientation = VERTICAL
    }

    fun setText(html: String?) {
        htmlContent = html ?: return
        val document = Jsoup.parse(htmlContent)
        val elementContent = document.body()
        element = elementContent
        val children = element?.children()
        if (!children.isNullOrEmpty()) {
            EManager.appendView(this, children, htmlContent)
        }
    }
}
