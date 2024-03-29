package com.saka.android.htmltextview.element

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.webkit.WebView

open class WebViewCompat @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : WebView(context.createConfigurationContext(Configuration()), attrs, defStyleAttr, defStyleRes)
