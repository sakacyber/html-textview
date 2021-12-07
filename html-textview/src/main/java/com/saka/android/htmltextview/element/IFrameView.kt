package com.saka.android.htmltextview.element

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.saka.android.htmltextview.R
import com.saka.android.htmltextview.activity.VideoActivity
import com.saka.android.htmltextview.utility.ThumbnailLoader
import com.saka.android.htmltextview.utility.dp
import org.jsoup.nodes.Element

class IFrameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null,
    content: String = ""
) : BaseElement(context, attrs, defStyleAttr, element, content) {

    private var isInProgress = false

    override fun render() {
        orientation = VERTICAL
        setContent()
    }

    private fun setContent() {
        val imagePreview = ImageView(context)
        imagePreview.layoutParams = LayoutParams(-1, 240.dp().toInt())
        imagePreview.adjustViewBounds = true
        imagePreview.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.img_holder))
        ThumbnailLoader(imagePreview).execute(link)
        addView(imagePreview)
        setOnClickListener {
            startVideoPlayer()
        }
    }

    private fun startVideoPlayer() {
        if (isInProgress) return
        isInProgress = true
        val intent = Intent(context, VideoActivity::class.java)
            .putExtra("link", link)
        ContextCompat.startActivity(context, intent, null)
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) isInProgress = false
    }

    private val link: String?
        get() = element?.attr("abs:src")
}
