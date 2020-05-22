package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet

class ImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseElement(context, attrs, defStyleAttr) {

    override fun render() {
        orientation = VERTICAL
        setImage()
    }

    private fun setImage() {
        val roundImageView = RoundImageView(context)
        roundImageView.adjustViewBounds = true
        roundImageView.load(link)
        val layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 18, 0, 18)
        addView(roundImageView, layoutParams)
    }

    private val link: String?
        get() = element.attr("abs:src")
}
