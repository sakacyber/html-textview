package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.LifecycleCoroutineScope
import com.saka.android.htmltextview.utility.Conf
import com.saka.android.htmltextview.utility.Conf.htmlContent
import com.saka.android.htmltextview.utility.EManager
import org.jsoup.Jsoup

class HtmlTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    init {
        val typeArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.HtmlTextView
        )
        Conf.textSize = typeArray.getDimension(
            R.styleable.HtmlTextView_htmlTextSize,
            Conf.DEF_TEXT_SIZE
        )
        Conf.textColor = typeArray.getColor(
            R.styleable.HtmlTextView_htmlTextColor,
            ContextCompat.getColor(context, R.color.colorTextPrimary)
        )
        Conf.textLineSpacing = typeArray.getDimension(
            R.styleable.HtmlTextView_htmlLineSpacing,
            Conf.DEF_LINE_SPACING
        )
        Conf.imageRoundCorner = typeArray.getDimension(
            R.styleable.HtmlTextView_htmlImageRound,
            Conf.DEF_IMAGE_ROUND
        )
        Conf.textPadding = typeArray.getDimension(
            R.styleable.HtmlTextView_htmlTextPadding,
            Conf.DEF_PADDING
        )
        htmlContent = typeArray.getString(R.styleable.HtmlTextView_htmlContent) ?: ""
        typeArray.recycle()
    }

    fun setText(html: String?, lifecycle: LifecycleCoroutineScope) {
        htmlContent = html ?: return
        val children = Jsoup.parse(htmlContent).body().children()
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        addView(linearLayout, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        if (!children.isNullOrEmpty()) {
            EManager.appendView(linearLayout, children, htmlContent, lifecycle)
        }
    }

    fun updateTextSize(fontSize: Float) {
        EManager.updateParagraphFontSize(fontSize)
    }
}
