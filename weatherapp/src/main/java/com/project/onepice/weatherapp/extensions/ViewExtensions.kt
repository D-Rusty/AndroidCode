package com.project.onepice.weatherapp.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView

/**
 * 作者: 方天文
 * 日期: 2017/6/2:下午6:11
 * 概况:
 */
val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)