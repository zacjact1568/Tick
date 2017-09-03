package me.imzack.app.tick.model.bean

import me.imzack.app.tick.util.TimeUtil
import java.util.*

data class Time(val hour: Int, val minute: Int) {

    companion object {
        val instance: Time
            get() {
                val calendar = Calendar.getInstance()
                return Time(calendar.get(if (TimeUtil.is24HourFormat) Calendar.HOUR_OF_DAY else Calendar.HOUR), calendar.get(Calendar.MINUTE))
            }
    }

    // Use double digit
    val hourString
        get() = if (hour < 10) "0$hour" else hour.toString()

    val minuteString
        get() = if (minute < 10) "0$minute" else minute.toString()
}