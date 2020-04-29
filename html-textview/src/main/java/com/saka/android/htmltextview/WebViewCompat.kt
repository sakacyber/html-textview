package com.saka.android.htmltextview

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.webkit.WebView

class WebViewCompat @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : WebView(context.createConfigurationContext(Configuration()), attrs, defStyleAttr, defStyleRes)