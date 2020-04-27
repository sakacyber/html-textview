# HtmlTextView

HtmlTextView is a library for display html content in android.

It currently supports tags:

* text
* image
* div
* blockquote
* iframe

## Usage
HtmlTextView is availble on

```
    implementation 'com.github.SakaGamer:html-textview:1.0.0'
```
## Quick Start
to use HtmlTextView:

```
 <com.saka.android.htmltextview.HtmlTextView
        android:id="@+id/htmlTextView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp" />


// in activity or fragment
htmlTextView.setText(html)

```