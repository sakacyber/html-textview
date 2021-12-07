package com.saka.android.htmltextview.element

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebViewClient
import org.jsoup.nodes.Element

class TableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    element: Element? = null,
    content: String = ""
) : BaseElement(context, attrs, defStyleAttr, element, content) {

    override fun render() {
        orientation = VERTICAL
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        setContent()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setContent() {
        val webView =
            WebViewCompat(context)
        webView.layoutParams = LayoutParams(-1, -2)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        element?.attr("width", "100%")
        webView.loadData(element.toString(), "text/html", "UTF-8")
        addView(webView)
    }
}