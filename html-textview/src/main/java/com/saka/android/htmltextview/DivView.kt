package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet

class DivView @JvmOverloads constructor(
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
    }
}
