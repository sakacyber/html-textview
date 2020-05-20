package com.saka.android.htmltextview

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.util.*

object EManager {

    private fun isParagraph(element: Element): Boolean {
        return element.tagName() == "p"
    }

    private fun isIFrame(element: Element): Boolean {
        return element.tagName() == "iframe"
    }

    private fun isImage(element: Element): Boolean {
        return element.tagName() == "img"
    }

    private fun isHeader(element: Element): Boolean {
        return element.tagName() == "h"
    }

    private fun isBlockQuote(element: Element): Boolean {
        return element.tagName() == "blockquote"
    }

    private fun isDiv(element: Element): Boolean {
        return element.tagName() == "div"
    }

    fun appendView(view: BaseElement, elements: Elements) {
        var child: BaseElement
        for (e in elements) {
            when {
                isBlockQuote(e) -> {
                    child = BlockQuoteView(context = view.context)
                    child.element = e
                }
                isHeader(e) -> {
                    child = HeaderView(context = view.context)
                    child.element = e
                }
                isIFrame(e) -> {
                    child = IFrameView(context = view.context)
                    child.element = e
                }
                isParagraph(e) -> {
                    child = ParagraphView(context = view.context)
                    child.element = e
                }
                isImage(e) -> {
                    child = ImageView(context = view.context)
                    child.element = e
                    Log.d("EManager", "ImageView: $e")
                }
                isDiv(e) -> {
                    child = DivView(context = view.context)
                    child.element = e
                }
                else -> {
                    child = HeaderView(context = view.context)
                    child.element = e
                }
            }
            view.addView(child)
        }
    }

    fun dpToPx(context: Context, dp: Int): Float {
        val displayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun findAllVideoLinks(content: String?): List<String> {
        val links: MutableList<String> = ArrayList()
        val document = Jsoup.parse(content)
        val medias = document.select("[src]")
        for (element in medias) {
            if (element.tagName() == "iframe") {
                links.add(element.attr("abs:src"))
            }
        }
        return links
    }

    fun findAllImagesLinks(content: String?): List<String> {
        val links: MutableList<String> = ArrayList()
        val document = Jsoup.parse(content)
        val medias = document.select("[src]")
        for (element in medias) {
            if (element.tagName() == "img") {
                links.add(element.attr("abs:src"))
            }
        }
        return links
    }
}
