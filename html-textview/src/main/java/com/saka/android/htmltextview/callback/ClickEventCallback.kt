package com.saka.android.htmltextview.callback

interface ClickEventCallback {
    fun onHtmlImageClick(pos: Int, src: String?){}
    fun onHtmlVideoClick(pos: Int, src: String?){}
}
