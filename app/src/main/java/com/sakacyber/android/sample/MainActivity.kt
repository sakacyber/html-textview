package com.sakacyber.android.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.saka.android.htmltextview.HtmlTextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val content = Content.deserialize(this)
        findViewById<HtmlTextView>(R.id.htmlTextView).setText(content?.content, lifecycleScope)
    }
}
