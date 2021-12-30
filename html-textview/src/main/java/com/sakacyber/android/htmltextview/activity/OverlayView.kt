package com.sakacyber.android.htmltextview.activity

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import com.sakacyber.android.htmltextview.R

class OverlayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val callback: EventCallback? = null
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        val viewOverlay = View.inflate(context, R.layout.view_overlay, this)
//        viewOverlay.findViewById<ImageView>(R.id.imageClose).setOnClickListener {
//            callback?.onCloseClick()
//        }
        viewOverlay.findViewById<ImageView>(R.id.imageMore).setOnClickListener {
            shopPopupMenu(it)
        }
    }

    fun updateText(curr: Int, size: Int) {
        val currIndex = "${curr + 1}/$size"
        findViewById<TextView>(R.id.viewIndex).text = currIndex
    }

    private fun shopPopupMenu(it: View) {
        val popupMenu = PopupMenu(it.context, it)
        popupMenu.inflate(R.menu.save_menu)
        popupMenu.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.menu_save -> {
                    callback?.onSaveClick()
                }
            }
            true
        }
        popupMenu.show()
    }

    interface EventCallback {
        //        fun onCloseClick()
        fun onSaveClick()
    }
}
