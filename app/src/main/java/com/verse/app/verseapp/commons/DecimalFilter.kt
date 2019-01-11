package com.verse.app.verseapp.commons

import android.text.InputFilter
import android.text.Spanned

class DecimalFilter(var decimalDigits: Int) : InputFilter {


    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        var dotPos: Int = -1
        val len: Int = dest.length
        if (len > 0) {
            for (i in 0 until dest.length) {
                val c: Char? = dest[i]
                if (c != null && (c == '.' || c == ',')) {
                    dotPos = i
                    break
                }
            }
        }
        if (dotPos >= 0) {

            // protects against many dots
            if (source.equals(".") || source.equals(",")) {
                return "";
            }
            // if the text is entered before the dot
            if (dend <= dotPos) {
                return null
            }
            if (len - dotPos > decimalDigits) {
                return "";
            }
        }


        return null
    }


}
