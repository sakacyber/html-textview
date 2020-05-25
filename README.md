[![](https://jitpack.io/v/SakaGamer/html-textview.svg)](https://jitpack.io/#SakaGamer/html-textview)

# HtmlTextView

**HtmlTextView** is a library for display html content in android. It also support dark mode.

Support tag base on build in [Html.fromHtml (String source,
                int flags)](https://developer.android.com/reference/android/text/Html#fromHtml(java.lang.String,%20int)) in android

It currently supports tags </>:

* ```<iframe>```
* ```<a href="...">```
* ```<b>```
* ```<big>```
* ```<blockquote>```
* ```<br>```
* ```<cite>```
* ```<dfn>```
* ```<div align="...">```
* ```<em>```
* ```<font size="..." color="..." face="...">```
* ```<i>```
* ```<img src="...">```
* ```<p>```
* ```<small>```
* ```<strike>```
* ```<strong>```
* ```<sub>```
* ```<sup>```
* ```<tt>```
* ```<u>```

## Quick Start
**HtmlTextView** is availble on jitpack.

Add dependency:

```
implementation 'com.github.SakaGamer:html-textview:1.0.8'
```

## Usage
to use **HtmlTextView**:

```
// in xml
<com.saka.android.htmltextview.HtmlTextView
    android:id="@+id/htmlTextView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp" />


// in activity or fragment
htmlTextView.setText(html)
```
