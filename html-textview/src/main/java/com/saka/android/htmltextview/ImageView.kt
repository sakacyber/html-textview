package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class ImageView @JvmOverloads constructor(
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
        setImage()
    }

    private fun setImage() {
        val imageView = View.inflate(context, R.layout.image_view, null) as ImageView
        imageView.load(link)
        addView(imageView)
    }

    private val link: String?
        get() = element.attr("abs:src")
}
