package me.imzack.app.tick.view.activity

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import me.imzack.app.tick.App
import me.imzack.app.tick.R
import me.imzack.app.tick.view.fragment.SettingsFragment
import me.imzack.app.tick.util.SystemUtil
import me.imzack.app.tick.recevier.WidgetProvider

// 在添加小部件时启动此 activity
class WidgetConfigurationActivity : AppCompatActivity() {

    private var mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 按下返回键会取消添加 widget
        setResult(Activity.RESULT_CANCELED)

        setContentView(R.layout.activity_widget_configuration)

        ButterKnife.bind(this)

        // 从 intent 中读取 widget id
        mAppWidgetId = intent.extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)

        // 如果 intent 中没有 widget id，退出
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        // 若使用默认设置，直接添加 widget
        if (App.preferenceHelper.setAsDefaultValue) {
            addWidget()
        }

        fragmentManager.beginTransaction().replace(R.id.vSettingsLayout, SettingsFragment.newInstance(mAppWidgetId)).commit()
    }

    private fun addWidget() {
        // configuration activity 需要主动更新 widget
        WidgetProvider.update(mAppWidgetId)

        // 设置更新控件的广播
        SystemUtil.setUpdateWidgetAlarm()

        // 回传原始的 widget id
        val intent = Intent()
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    @OnClick(R.id.vAddButton)
    fun onClick(view: View) {
        when (view.id) {
            R.id.vAddButton -> addWidget()
        }
    }
}

