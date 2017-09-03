package me.imzack.app.tick.util

import android.text.format.DateFormat
import me.imzack.app.tick.App

object TimeUtil {

    val is24HourFormat
        get() = DateFormat.is24HourFormat(App.context)
}