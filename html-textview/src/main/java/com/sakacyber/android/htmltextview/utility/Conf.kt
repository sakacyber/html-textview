package com.sakacyber.android.htmltextview.utility

object Conf {

    // default
    var DEF_TEXT_SIZE = 12f
    var DEF_LINE_SPACING = 0f
    var DEF_IMAGE_ROUND = 0f
    var DEF_PADDING = 0f

    // config
    var textSize = DEF_TEXT_SIZE
    var textLineSpacing = DEF_LINE_SPACING
    var imageRoundCorner = DEF_IMAGE_ROUND
    var textPadding = DEF_PADDING

    var htmlContent = ""

    private var imageList = mutableListOf<String?>()

    fun addImage(url: String?) {
        imageList.add(url)
    }

    fun getImageList(): Array<String?> {
        return imageList.toTypedArray()
    }
}
