package com.sakacyber.android.htmltextview.utility

import android.content.Context
import android.util.DisplayMetrics
import com.sakacyber.android.htmltextview.element.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.util.*

object EManager {

//    private fun isParagraph(element: Element): Boolean {
//        return element.tagName() == "p"
//    }
//
//    private fun isIFrame(element: Element): Boolean {
//        return element.tagName() == "iframe"
//    }
//
//    private fun isImage(element: Element): Boolean {
//        return element.tagName() == "img"
//    }
//
//    private fun isHeader(element: Element): Boolean {
//        return element.tagName() == "h"
//    }
//
//    private fun isBlockQuote(element: Element): Boolean {
//        return element.tagName() == "blockquote"
//    }
//
//    private fun isDiv(element: Element): Boolean {
//        return element.tagName() == "div"
//    }
//
//    private fun isTable(element: Element): Boolean {
//        return element.tagName() == "table"
//    }

    fun appendView(view: BaseElement, elements: Elements, content: String = "") {
        var child: BaseElement
        for (e in elements) {
            when (e.tagName()) {
                "blockquote" -> {
                    child = BlockQuoteView(
                        context = view.context,
                        element = e,
                        content = content
                    )
                }
                "h" -> {
                    child = HeaderView(
                        context = view.context,
                        element = e,
                        content = content
                    )
                }
                "iframe" -> {
                    child = IFrameView(
                        context = view.context,
                        element = e,
                        content = content
                    )
                }
                "p" -> {
                    child = ParagraphView(
                        context = view.context,
                        element = e,
                        content = content
                    )
                }
                "img" -> {
                    child = ImageView(
                        context = view.context,
                        element = e,
                        content = content
                    )
                }
                "div" -> {
                    child = DivView(
                        context = view.context,
                        element = e,
                        content = content
                    )
                }
                "table" -> {
                    child = TableView(
                        context = view.context,
                        element = e,
                        content = content
                    )
                }
                else -> {
                    child = HeaderView(
                        context = view.context,
                        element = e,
                        content = content
                    )
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
