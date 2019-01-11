package com.verse.app.verseapp

import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun Fragment.toast(message: CharSequence) =
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()