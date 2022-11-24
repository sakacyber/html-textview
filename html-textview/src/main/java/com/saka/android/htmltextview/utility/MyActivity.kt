package com.saka.android.htmltextview.utility

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebChromeClient.CustomViewCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.saka.android.htmltextview.R.id
import com.saka.android.htmltextview.R.layout

class MyActivity : Activity() {

    private var webView: WebView? = null
    private var customViewContainer: FrameLayout? = null
    private var customViewCallback: CustomViewCallback? = null
    private var mCustomView: View? = null
    private var mWebChromeClient: MyWebChromeClient? = null

    @SuppressLint("SetJavaScriptEnabled")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.main)
        customViewContainer = findViewById(id.customViewContainer)
        webView = findViewById(id.webView)

        val webView = webView ?: return
        val link = intent.getStringExtra("link")
        val mWebViewClient = MyWebViewClient()

        webView.webViewClient = mWebViewClient
        mWebChromeClient = MyWebChromeClient()
        webView.webChromeClient = mWebChromeClient
        webView.settings.javaScriptEnabled = true
        val url = """<html>
                <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                body {
                    padding: 0;
                    margin: 0 auto;
                    background-color: black;
                }
                iframe{
                    width:100%;
                    height:100%;
                }
                @media only screen and (max-width: 600px) {
                 iframe {
                    position: relative;
                    top: calc(30%);
                  }
                }
                </style>
                </head>
                <body>
                <iframe src="$link" style="border:none;"></iframe>
                </body>
                </html>"""
        webView.loadData(url, "text/html", "UTF-8")
    }

    private fun inCustomView(): Boolean {
        return mCustomView != null
    }

    private fun hideCustomView() {
        mWebChromeClient?.onHideCustomView()
    }

    override fun onPause() {
        super.onPause()
        webView?.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView?.onResume()
    }

    override fun onStop() {
        super.onStop()
        if (inCustomView()) {
            hideCustomView()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (inCustomView()) {
                hideCustomView()
                return true
            }
            if (mCustomView == null && webView?.canGoBack() == true) {
                webView?.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    internal inner class MyWebChromeClient : WebChromeClient() {

        // private val mDefaultVideoPoster: Bitmap? = null
        private var mVideoProgressView: View? = null

        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden()
                return
            }
            mCustomView = view
            webView?.visibility = View.GONE
            customViewContainer?.visibility = View.VISIBLE
            customViewContainer?.addView(view)
            customViewCallback = callback
        }

        override fun getVideoLoadingProgressView(): View? {
            if (mVideoProgressView == null) {
                val inflater = LayoutInflater.from(this@MyActivity)
                mVideoProgressView = inflater.inflate(layout.video_progress, null)
            }
            return mVideoProgressView
        }

        override fun onHideCustomView() {
            super.onHideCustomView()

            if (mCustomView == null) return
            webView?.visibility = View.VISIBLE
            customViewContainer?.visibility = View.GONE

            // Hide the custom view.
            mCustomView?.visibility = View.GONE

            // Remove the custom view from its container.
            customViewContainer?.removeView(mCustomView)
            customViewCallback?.onCustomViewHidden()
            mCustomView = null
        }
    }

    internal class MyWebViewClient : WebViewClient()
}
