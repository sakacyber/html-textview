package com.saka.android.htmltextview.element

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.InsetDrawable
import android.util.AttributeSet
import android.util.Base64
import android.view.MotionEvent
import android.webkit.WebView
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.saka.android.htmltextview.R
import com.saka.android.htmltextview.activity.VideoActivity
import com.saka.android.htmltextview.utility.MyActivity
import com.saka.android.htmltextview.utility.VideoLoader
import com.saka.android.htmltextview.utility.dp
import com.saka.android.htmltextview.utility.loadBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jsoup.nodes.Element

class IFrameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null,
    content: String = "",
    coroutineScope: CoroutineScope? = null
) : BaseElement(context, attrs, defStyleAttr, element, content, coroutineScope) {

    private var isInProgress = false

    companion object {
        const val BASE_URL = "http://13.250.71.15:81"
    }

    override fun render() {
        orientation = VERTICAL
        setContent()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setContent() {
        link?.let {
            if (VideoLoader.isYoutubeFormat(it) || it.contains(BASE_URL)) {
                val imagePreview = ImageView(context)
                imagePreview.layoutParams = LayoutParams(-1, 240.dp().toInt())
                imagePreview.adjustViewBounds = true
                imagePreview.setImageDrawable(
                    ContextCompat.getDrawable(context, R.drawable.img_holder)
                )

                scope?.launch {
                    it.loadBitmap { bitmap ->
                        imagePreview.background = BitmapDrawable(Resources.getSystem(), bitmap)
                        imagePreview.setImageDrawable(
                            InsetDrawable(
                                ContextCompat.getDrawable(
                                    imagePreview.context,
                                    R.drawable.ic_play_circle_24
                                ),
                                80
                            )
                        )
                    }
                }

                addView(imagePreview)
                setOnClickListener {
                    startVideoPlayer()
                }
            } else {
                val webView = WebView(context)
                val maxTouchDuration = 100
                var mDownTime: Long = 0
                webView.layoutParams = LayoutParams(-1, 220.dp().toInt())
                webView.settings.javaScriptEnabled = true
                webView.setOnTouchListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            mDownTime = event.eventTime
                        }
                        MotionEvent.ACTION_UP -> {
                            if (event.eventTime - mDownTime <= maxTouchDuration) {
                                if (!isInProgress) {
                                    isInProgress = true
                                    val intent = Intent(context, MyActivity::class.java)
                                        .putExtra("link", link)
                                    ContextCompat.startActivity(context, intent, null)
                                }
                            }
                        }
                    }
                    true
                }
                val htmlStart = """
                     <HTML>
                     <HEAD>
                     <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">
                     <style>
                     iframe {
                      width: 100% !important;
                      height: 100% !important;
                     }
                     body {
                     padding: 0;
                     margin: 0;
                     background: black;
                     }
                     </style>
                     </HEAD>
                     <body>
                     """
                val htmlEnd = "</body></HTML>"
                val content = "<iframe src=\"$link\" style=\"border:none;\"></iframe>"
                val htmlString = "$htmlStart$content$htmlEnd"
                val encodedHtml = Base64.encodeToString(htmlString.toByteArray(), Base64.NO_PADDING)
                webView.loadData(encodedHtml, "text/html", "base64")
                addView(webView)
            }
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
