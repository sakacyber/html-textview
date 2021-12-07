package com.saka.android.htmltextview.element

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.saka.android.htmltextview.activity.ImageActivity
import com.saka.android.htmltextview.utility.load
import org.jsoup.nodes.Element

class ImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null,
    content: String = ""
) : BaseElement(context, attrs, defStyleAttr, element, content) {

    private var isInProgress = false

    override fun render() {
        orientation = VERTICAL
        setImage()
    }

    private fun setImage() {
        val roundImageView = RoundImageView(context)
        roundImageView.adjustViewBounds = true
        roundImageView.minimumHeight = 120
        roundImageView.load(link)
        val layoutParams = LayoutParams(-1, -2)
        layoutParams.setMargins(0, 18, 0, 18)
        addView(roundImageView, layoutParams)
        setOnClickListener { startPreviewImage() }
    }

    private fun startPreviewImage() {
        if (isInProgress) return
        isInProgress = true
        val intent = Intent(context, ImageActivity::class.java)
            .putExtra("link", link)
            .putExtra("html", htmlContent)
        ContextCompat.startActivity(context, intent, null)
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) isInProgress = false
    }

    private val link: String? get() = element?.attr("abs:src")
}
