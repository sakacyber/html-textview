package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet
import coil.api.load

class ImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseElement(context, attrs, defStyleAttr) {

    override fun render() {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        val imageView = android.widget.ImageView(context)
        imageView.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        imageView.load(link) {
            error(R.drawable.bg_placeholder)
        }
        imageView.adjustViewBounds = true
        imageView.setPadding(0, 24, 0, 24)
        addView(imageView)
    }

    private val link: String?
        get() = element.attr("abs:src")
}