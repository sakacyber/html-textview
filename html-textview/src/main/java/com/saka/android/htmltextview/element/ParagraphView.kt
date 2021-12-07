package com.saka.android.htmltextview.element

import android.content.Context
import android.text.util.Linkify
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import com.saka.android.htmltextview.R
import com.saka.android.htmltextview.utility.Conf
import com.saka.android.htmltextview.utility.EManager
import org.jsoup.nodes.Element

class ParagraphView @JvmOverloads constructor(
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
        paragraphView.autoLinkMask = Linkify.ALL
        paragraphView.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.colorTextPrimary
            )
        )
        paragraphView.typeface = ResourcesCompat.getFont(
            context,
            R.font.font_battambang_regular
        )
//        val paragraphView = View.inflate(context, R.layout.paragraph_view, null) as TextView
        paragraphView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            Conf.textSize
        )
        paragraphView.setLineSpacing(Conf.textLineSpacing, 1f)
        paragraphView.setPadding(Conf.textPadding.toInt())
        paragraphView.text = element?.text()
        addView(paragraphView)
    }
}