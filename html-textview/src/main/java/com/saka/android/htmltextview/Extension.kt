package com.saka.android.htmltextview

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String?) {
    Glide.with(this).load(url).error(R.drawable.bg_placeholder).into(this)
}
