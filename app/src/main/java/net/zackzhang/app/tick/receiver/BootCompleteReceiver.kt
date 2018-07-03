package net.zackzhang.app.tick.receiver

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import net.zackzhang.app.tick.util.SystemUtil

class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context, WidgetProvider::class.java)).isNotEmpty()) {
            SystemUtil.setUpdateWidgetAlarm()
        } else {
            // 若没有控件，退出进程
            System.exit(0)
        }
    }
}
