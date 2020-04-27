package com.saka.android.htmltextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saka.android.html_textview.HtmlTextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val content = Content.deserialize(this)
        findViewById<HtmlTextView>(R.id.htmlTextView).setText(content?.content)
    }

}
