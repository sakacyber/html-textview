package com.saka.android.htmltextview.utility

object Conf {

    // default
    const val DEF_TEXT_SIZE = 14f
    const val DEF_TEXT_COLOR = 0
    const val DEF_LINE_SPACING = 0f
    const val DEF_IMAGE_ROUND = 0f
    const val DEF_PADDING = 0f

    // config
    var textSize = DEF_TEXT_SIZE
    var textLineSpacing = DEF_LINE_SPACING
    var imageRoundCorner = DEF_IMAGE_ROUND
    var textPadding = DEF_PADDING
    var textColor = DEF_TEXT_COLOR
    var htmlContent = ""

    private var imageList = mutableListOf<String?>()

    fun addImage(url: String?) {
        imageList.add(url)
    }

    fun getImageList(): Array<String?> {
        return imageList.toTypedArray()
    }
}
