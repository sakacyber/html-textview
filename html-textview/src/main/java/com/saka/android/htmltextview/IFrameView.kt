package com.saka.android.htmltextview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class IFrameView @JvmOverloads constructor(
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
        setContent()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setContent() {
        val webView = WebViewCompat(context)
        webView.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                return false
            }
        }
        webView.settings.javaScriptEnabled = true
        element.attr("width", "100%")
        webView.loadData(element.toString(), "text/html", "UTF-8")
        addView(webView)
    }
}
