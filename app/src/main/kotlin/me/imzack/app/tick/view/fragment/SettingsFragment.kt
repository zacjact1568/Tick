package me.imzack.app.tick.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.ListPreference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import me.imzack.app.tick.model.PreferenceHelper
import me.imzack.app.tick.R
import me.imzack.app.tick.common.Constant

class SettingsFragment : PreferenceFragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    companion object {

        private val ARG_APP_WIDGET_ID = "app_widget_id"

        fun newInstance(appWidgetId: Int): SettingsFragment {
            val fragment = SettingsFragment()
            val args = Bundle()
            args.putInt(ARG_APP_WIDGET_ID, appWidgetId)
            fragment.arguments = args
            return fragment
        }
    }

    private val mSetAsDefaultPreference by lazy {
        findPreference(Constant.PREF_KEY_SET_AS_DEFAULT) as SwitchPreference
    }
    private val mHourTextColorPreference by lazy {
        findPreference(Constant.PREF_KEY_HOUR_TEXT_COLOR) as ListPreference
    }
    private val mMinuteTextColorPreference by lazy {
        findPreference(Constant.PREF_KEY_MINUTE_TEXT_COLOR) as ListPreference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.preferences)

        val args = arguments
        if (args != null && args.containsKey(ARG_APP_WIDGET_ID)) {
            // 在添加 widget 的 activity 中
            preferenceScreen.removePreference(mSetAsDefaultPreference)
            val appWidgetId = args.getInt(ARG_APP_WIDGET_ID)
            mHourTextColorPreference.key = PreferenceHelper.getKeyOf(appWidgetId, Constant.PREF_KEY_HOUR_TEXT_COLOR)
            mMinuteTextColorPreference.key = PreferenceHelper.getKeyOf(appWidgetId, Constant.PREF_KEY_MINUTE_TEXT_COLOR)
        }

        setSummary(mHourTextColorPreference.key)
        setSummary(mMinuteTextColorPreference.key)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        when (key) {
            mHourTextColorPreference.key, mMinuteTextColorPreference.key -> setSummary(key)
        }
    }

    private fun setSummary(key: String) {
        when (key) {
            mHourTextColorPreference.key -> mHourTextColorPreference.summary = mHourTextColorPreference.entry
            mMinuteTextColorPreference.key -> mMinuteTextColorPreference.summary = mMinuteTextColorPreference.entry
        }
    }
}
