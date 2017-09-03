package me.imzack.app.tick.util

import android.app.AlarmManager
import android.content.Context
import me.imzack.app.tick.App
import me.imzack.app.tick.recevier.WidgetProvider
import java.util.*

object SystemUtil {

    fun setUpdateWidgetAlarm() {
        val context = App.context
        val calendar = Calendar.getInstance()
        // 触发时间设为下一分钟的起始点
        calendar.add(Calendar.MINUTE, 1)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager).setRepeating(AlarmManager.RTC, calendar.timeInMillis, 60000, WidgetProvider.getPendingIntentForSend(context))
    }

    fun cancelUpdateWidgetAlarm() {
        val context = App.context
        (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager).cancel(WidgetProvider.getPendingIntentForSend(context))
    }
}