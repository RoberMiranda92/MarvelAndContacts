package com.verse.app.verseapp.commons

import android.content.res.Resources


class WindowsUtils {

    companion object {
        /**
         * This method converts dp unit to equivalent pixels, depending on device density.
         *
         * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
         * @return A float value to represent px equivalent to dp depending on device density
         */
        fun convertDpToPixel(dp: Int): Int {
            val metrics = Resources.getSystem().displayMetrics
            val px = dp * (metrics.densityDpi / 160f)
            return Math.round(px)
        }

        /**
         * This method converts device specific pixels to density independent pixels.
         *
         * @param px A value in px (pixels) unit. Which we need to convert into db
         * @return A float value to represent dp equivalent to px value
         */
        fun convertPixelsToDp(px: Float): Int {
            val metrics = Resources.getSystem().displayMetrics
            val dp = px / (metrics.densityDpi / 160f)
            return Math.round(dp)
        }

    }
}