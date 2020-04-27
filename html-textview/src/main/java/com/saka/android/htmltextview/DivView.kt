package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet

class DivView @JvmOverloads constructor(
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
    }
}