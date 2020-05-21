package com.saka.android.htmltextview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class ImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseElement(context, attrs, defStyleAttr) {

    private var radius = DEFAULT_CORNER
    private var rect: RectF = RectF()
    private var path: Path = Path()

    override fun onDraw(canvas: Canvas?) {
        rect.set(0f, 0f, width.toFloat(), height.toFloat())
        path.addRoundRect(rect, radius, radius, Path.Direction.CW)
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }

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

    companion object {
        private const val DEFAULT_CORNER = 12F
    }
}
