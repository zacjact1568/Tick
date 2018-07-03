package net.zackzhang.app.tick.receiver

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.RemoteViews
import net.zackzhang.app.tick.App
import net.zackzhang.app.tick.R
import net.zackzhang.app.tick.model.bean.Time
import net.zackzhang.app.tick.util.SystemUtil

class WidgetProvider : AppWidgetProvider() {

    companion object {

        fun getPendingIntentForSend(context: Context): PendingIntent {
            return PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(context, WidgetProvider::class.java)
                            .setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
                            .setPackage(context.packageName)
                            .putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context, WidgetProvider::class.java))),
                    // 若当前已有相同的 pending intent（有特定的区分规则），则不创建新的
                    PendingIntent.FLAG_CANCEL_CURRENT
            )
        }

        fun update(appWidgetId: Int) {
            val context = App.context
            val preferenceHelper = App.preferenceHelper
            val remoteViews = RemoteViews(context.packageName, R.layout.widget)
            val time = Time.instance
            remoteViews.setTextViewText(R.id.vHourText, time.hourString)
            remoteViews.setTextColor(R.id.vHourText, Color.parseColor(preferenceHelper.getHourTextColorValueOf(appWidgetId)))
            remoteViews.setTextViewText(R.id.vMinuteText, time.minuteString)
            remoteViews.setTextColor(R.id.vMinuteText, Color.parseColor(preferenceHelper.getMinuteTextColorValueOf(appWidgetId)))
            AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, remoteViews)
        }
    }

    // 若存在对应的 configuration activity，首次添加 widget 到桌面不会回调此方法
    // 回调时机：安装后第一次打开，系统启动（然后进程立刻被杀死），添加 widget（无 configuration activity），更新时间到
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {

        for (appWidgetId in appWidgetIds) {
            update(appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {

        if (appWidgetIds.size == 1) {
            // 若只剩一个控件（删除后就没有了），则取消更新控件的定时任务
            SystemUtil.cancelUpdateWidgetAlarm()
        }

        for (appWidgetId in appWidgetIds) {
            App.preferenceHelper.removeValuesOf(appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

