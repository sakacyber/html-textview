package com.sakacyber.android.htmltextview.utility

import android.content.res.Resources
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.sakacyber.android.htmltextview.R
import org.jsoup.Jsoup
import java.util.*

fun ImageView.load(url: String?) {
    Glide.with(this).load(url).error(R.drawable.bg_placeholder).into(this)
}

fun ImageView.loadBitmap(bitmap: Bitmap?) {
    Glide.with(this).load(bitmap).error(R.drawable.bg_placeholder).into(this)
}

fun ImageView.loadDrawable(@DrawableRes drawableResId: Int) {
    Glide.with(this).load(drawableResId).into(this)
}

fun Number.dp(): Float {
    return this.toFloat() * Resources.getSystem().displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
}

fun Number.dpi(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
}

fun String.findAllImageLinks(): List<String> {
    val links: MutableList<String> = ArrayList()
    val document = Jsoup.parse(this)
    val medias = document.select("[src]")
    for (element in medias) {
        if (element.tagName() == "img") {
            links.add(element.attr("abs:src"))
        }
    }
    return links
}