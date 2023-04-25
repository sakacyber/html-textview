package com.sakacyber.android.sample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.saka.android.htmltextview.HtmlTextView

class MainActivity : AppCompatActivity() {

    private var fontLarge = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val content = Content.deserialize(this)
        val htmlTextView = findViewById<HtmlTextView>(R.id.htmlTextView)
        htmlTextView.setText(content?.content, lifecycleScope)

        val button = findViewById<Button>(R.id.buttonFont)
        button.setOnClickListener {
            if (fontLarge)
                htmlTextView.updateTextSize(16f)
            else
                htmlTextView.updateTextSize(24f)
            fontLarge = !fontLarge
        }
    }
}
