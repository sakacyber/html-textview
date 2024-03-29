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
import kotlinx.coroutines.CoroutineScope
import org.jsoup.nodes.Element

class ParagraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null,
    content: String = "",
    coroutineScope: CoroutineScope? = null
) : BaseElement(context, attrs, defStyleAttr, element, content, coroutineScope) {

    lateinit var paragraphView: TextView

    override fun render() {
        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val children = element?.children()
        if (children?.isNotEmpty() == true) {
            EManager.appendView(
                view = this,
                elements = children,
                content = htmlContent,
                coroutineScope = scope
            )
        }
        setText()
    }

    private fun setText() {
        paragraphView = TextView(context)
        paragraphView.autoLinkMask = Linkify.ALL
        paragraphView.setTextColor(
            if (Conf.textColor != 0) {
                Conf.textColor
            } else {
                ContextCompat.getColor(context, R.color.colorTextPrimary)
            }
        )
        paragraphView.typeface = ResourcesCompat.getFont(context, R.font.font_battambang_regular)
        paragraphView.setTextSize(TypedValue.COMPLEX_UNIT_SP, Conf.textSize)
        paragraphView.setLineSpacing(Conf.textLineSpacing, 1f)
        paragraphView.setPadding(Conf.textPadding.toInt())
        paragraphView.text = element?.text()
        addView(paragraphView)
    }

    fun updateFontSize(fontSize: Float) {
        paragraphView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize)
    }
}
