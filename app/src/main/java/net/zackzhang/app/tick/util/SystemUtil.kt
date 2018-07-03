package net.zackzhang.app.tick.util

import android.app.Activity
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import net.zackzhang.app.tick.App
import net.zackzhang.app.tick.R
import net.zackzhang.app.tick.receiver.WidgetProvider
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

    fun openLink(link: String, activity: Activity, failed: String = activity.getString(R.string.toast_no_link_app_found)) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent)
        } else {
            showToast(failed)
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show()
    }

    val versionName: String
        get() {
            val context = App.context
            return try {
                val info = context.packageManager.getPackageInfo(context.packageName, 0)
                info.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                "null"
            }
        }
}