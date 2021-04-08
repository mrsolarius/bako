package app.bako.utils

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import app.bako.R

class UiUtils {
    companion object {
        private const val BRIGHTNESS_THRESHOLD = 130

        /**
         * Convertie le layout de base avec les couleur demander
         */
        fun getCustomDrawable(color: Int, context: Context): Drawable {
            val drawable = ContextCompat.getDrawable(context, R.drawable.layout_bg)
            val colorFilter: ColorFilter = LightingColorFilter(color, color)
            drawable!!.colorFilter = colorFilter
            return drawable
        }

        /**
         * Calcule si la couleur et dark ou si elle light
         *
         * @see {@literal http://en.wikipedia.org/wiki/HSV_color_space%23Lightness}
         */
        fun isColorDark(color: Int): Boolean {
            return (30 * Color.red(color) + 59 * Color.green(color) + 11 * Color.blue(color)) / 100 <= BRIGHTNESS_THRESHOLD
        }
    }
}