package com.sakacyber.android.htmltextview.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.sakacyber.android.htmltextview.R
import com.sakacyber.android.htmltextview.utility.findAllImageLinks
import com.sakacyber.android.htmltextview.utility.load
import com.stfalcon.imageviewer.StfalconImageViewer
import io.github.muddz.quickshot.QuickShot

class ImageActivity : AppCompatActivity(), OverlayView.EventCallback, PermissionListener {

    private var list = listOf<String>()
    private var link = ""
    private var isPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        link = intent.getStringExtra("link") ?: ""
        val htmlContent = intent.getStringExtra("html") ?: ""
        list = htmlContent.findAllImageLinks()
        val currentIndex = list.indexOf(link)

        // view overlay
        val overlayView = OverlayView(this, callback = this)
        overlayView.updateText(currentIndex, list.size)

        StfalconImageViewer.Builder(this, list, ::loadImage)
            .withDismissListener {
                finishOverlay()
            }
            .withImageChangeListener {
                link = list[it]
                overlayView.updateText(it, list.size)
            }
            .withStartPosition(currentIndex)
            .withOverlayView(overlayView)
            .withBackgroundColor(ContextCompat.getColor(this, R.color.colorOverlay))
            .show()

        isPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun loadImage(imageView: ImageView, src: String) {
        imageView.load(src)
    }

    private fun finishOverlay() {
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onSaveClick() {
        loadAndSaveImage()
    }

    private fun loadAndSaveImage() {
        if (!isPermissionGranted) {
            Dexter.withContext(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(this)
                .check()
            return
        }
        // importance to use applicationContext
        Glide.with(applicationContext).load(link).into(object : CustomTarget<Drawable>() {
            override fun onLoadCleared(placeholder: Drawable?) {
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                QuickShot.of(resource.toBitmap(), this@ImageActivity)
                    .setResultListener(quickShotListener).save()
            }
        })
    }

    private val quickShotListener = object : QuickShot.QuickShotListener {
        override fun onQuickShotSuccess(path: String?) {
            Toast.makeText(
                this@ImageActivity,
                getString(R.string.msg_save_image),
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onQuickShotFailed(path: String?, errorMsg: String?) {
        }
    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
        isPermissionGranted = true
        loadAndSaveImage()
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
        Toast.makeText(
            this,
            getString(R.string.msg_permission_rational),
            Toast.LENGTH_SHORT
        ).show()
        p1?.continuePermissionRequest()
    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        Toast.makeText(
            this,
            getString(R.string.msg_need_permission),
            Toast.LENGTH_SHORT
        ).show()
    }
}
