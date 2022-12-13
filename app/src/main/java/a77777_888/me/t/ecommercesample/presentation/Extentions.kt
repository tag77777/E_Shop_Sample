package a77777_888.me.t.ecommercesample.presentation

import android.graphics.Paint
import android.widget.TextView
import androidx.annotation.StringRes

fun TextView.setUnderlinedText(newText: String) {
    text = newText
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun TextView.setStrikeText(newText: String) {
    text = newText
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}
