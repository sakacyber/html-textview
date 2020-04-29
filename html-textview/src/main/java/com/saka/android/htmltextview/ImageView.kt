package com.saka.android.htmltextview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
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
        orientation = VERTICAL
        setImage()
    }

    private fun setImage() {
        val imageView = View.inflate(context, R.layout.image_view, null) as ImageView
        imageView.load(link) {
            error(R.drawable.bg_placeholder)
        }
        addView(imageView)
    }

    private val link: String?
        get() = element.attr("abs:src")
}