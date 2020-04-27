# HtmlTextView

**HtmlTextView** is a library for display html content in android.

It currently supports tags </>:

* p
* img
* div
* blockquote
* iframe

## Usage
**HtmlTextView** is availble on jitpack.

Add dependency:

```
    implementation 'com.github.SakaGamer:html-textview:1.0.0'
```

## Quick Start
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