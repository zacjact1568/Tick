package net.zackzhang.app.tick.util

import android.text.format.DateFormat
import net.zackzhang.app.tick.App

object TimeUtil {

    val is24HourFormat
        get() = DateFormat.is24HourFormat(App.context)
}