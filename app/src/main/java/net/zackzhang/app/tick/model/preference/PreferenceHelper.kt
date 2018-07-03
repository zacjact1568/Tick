package net.zackzhang.app.tick.model.preference

import android.content.SharedPreferences
import android.preference.PreferenceManager
import net.zackzhang.app.tick.App
import net.zackzhang.app.tick.R
import net.zackzhang.app.tick.common.Constant

class PreferenceHelper {

    companion object {
        fun getKeyOf(appWidgetId: Int, key: String) = "${key}_$appWidgetId"
    }

    init {
        PreferenceManager.setDefaultValues(App.context, R.xml.preferences, false)
    }

    private val mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.context)

    var setAsDefaultValue
        get() = mSharedPreferences.getBoolean(Constant.PREF_KEY_SET_AS_DEFAULT, false)
        set(value) = mSharedPreferences.edit().putBoolean(Constant.PREF_KEY_SET_AS_DEFAULT, value).apply()

    var hourTextColorValue
        get() = mSharedPreferences.getString(Constant.PREF_KEY_HOUR_TEXT_COLOR, Constant.PREF_DEF_VALUE_HOUR_TEXT_COLOR)!!
        set(value) = mSharedPreferences.edit().putString(Constant.PREF_KEY_HOUR_TEXT_COLOR, value).apply()

    var minuteTextColorValue
        get() = mSharedPreferences.getString(Constant.PREF_KEY_MINUTE_TEXT_COLOR, Constant.PREF_DEF_VALUE_MINUTE_TEXT_COLOR)!!
        set(value) = mSharedPreferences.edit().putString(Constant.PREF_KEY_MINUTE_TEXT_COLOR, value).apply()

    fun getHourTextColorValueOf(appWidgetId: Int): String {
        val key = getKeyOf(appWidgetId, Constant.PREF_KEY_HOUR_TEXT_COLOR)
        if (!mSharedPreferences.contains(key)) {
            // 如果 preference 中还没有此键，添加全局默认值将键值补齐
            setHourTextColorValueOf(appWidgetId, hourTextColorValue)
        }
        return mSharedPreferences.getString(key, hourTextColorValue)
    }

    fun setHourTextColorValueOf(appWidgetId: Int, value: String) {
        mSharedPreferences.edit().putString(getKeyOf(appWidgetId, Constant.PREF_KEY_HOUR_TEXT_COLOR), value).apply()
    }

    fun getMinuteTextColorValueOf(appWidgetId: Int): String {
        val key = getKeyOf(appWidgetId, Constant.PREF_KEY_MINUTE_TEXT_COLOR)
        if (!mSharedPreferences.contains(key)) {
            setMinuteTextColorValueOf(appWidgetId, minuteTextColorValue)
        }
        return mSharedPreferences.getString(key, minuteTextColorValue)
    }

    fun setMinuteTextColorValueOf(appWidgetId: Int, value: String) {
        mSharedPreferences.edit().putString(getKeyOf(appWidgetId, Constant.PREF_KEY_MINUTE_TEXT_COLOR), value).apply()
    }

    fun removeValuesOf(appWidgetId: Int) {
        val editor = mSharedPreferences.edit()
        editor.remove(getKeyOf(appWidgetId, Constant.PREF_KEY_HOUR_TEXT_COLOR))
        editor.remove(getKeyOf(appWidgetId, Constant.PREF_KEY_MINUTE_TEXT_COLOR))
        editor.apply()
    }

    var allValues: Map<String, *>
        get() = mSharedPreferences.all
        set(value) {
            for ((k, v) in value) {
                when (k) {
                    Constant.PREF_KEY_SET_AS_DEFAULT -> setAsDefaultValue = v as Boolean
                    Constant.PREF_KEY_HOUR_TEXT_COLOR -> hourTextColorValue = v as String
                    Constant.PREF_KEY_MINUTE_TEXT_COLOR -> minuteTextColorValue = v as String
                }
            }
        }

    val allValuesCopied
        // 转换成不可变类型
        get() = HashMap(allValues) as Map<String, *>

    fun registerOnChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        mSharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterOnChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}