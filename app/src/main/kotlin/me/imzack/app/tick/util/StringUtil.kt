package me.imzack.app.tick.util

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

object StringUtil {

    val SPAN_COLOR = 0
    val SPAN_STYLE = 1

    /** 整个字符串都添加span */
    fun addSpan(cs: CharSequence, spans: Array<Int>, extras: Array<Any>? = null): SpannableString {
        val ss = SpannableString(cs)
        for (i in 0 until spans.size) {
            val span = spans[i]
            val extra = extras?.get(i)
            addSpan(ss, span, extra, 0, cs.length)
        }
        return ss
    }

    private fun addSpan(ss: SpannableString, span: Int, extra: Any?, start: Int, length: Int) {
        ss.setSpan(when (span) {
            SPAN_STYLE -> StyleSpan(extra as Int)
            SPAN_COLOR -> ForegroundColorSpan(extra as Int)
            else -> throw IllegalArgumentException("The argument \"span\" cannot be \"$span\"")
        }, start, start + length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
    }
}