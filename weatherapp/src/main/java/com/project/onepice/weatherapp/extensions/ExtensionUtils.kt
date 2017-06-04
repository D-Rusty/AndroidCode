package com.project.onepice.weatherapp.extensions

import java.text.DateFormat
import java.util.*

/**
 * 作者: 方天文
 * 日期: 2017/6/2:下午6:06
 * 概况:
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}
