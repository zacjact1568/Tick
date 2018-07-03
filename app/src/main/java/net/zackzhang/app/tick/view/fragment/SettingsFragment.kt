package net.zackzhang.app.tick.view.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.v4.app.FragmentActivity
import net.zackzhang.app.tick.model.preference.PreferenceHelper
import net.zackzhang.app.tick.R
import net.zackzhang.app.tick.common.Constant
import net.zackzhang.app.tick.util.SystemUtil

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

    private val TAG_THANKS = "thanks"
    private val TAG_PROBLEMS = "problems"

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
            // 在 WidgetConfigurationActivity 中，移除全局设置的 preference category
            preferenceScreen.removePreference(findPreference(Constant.PREF_CATEGORY_KEY_GLOBAL))
            preferenceScreen.removePreference(findPreference(Constant.PREF_CATEGORY_KEY_ABOUT))
            val appWidgetId = args.getInt(ARG_APP_WIDGET_ID)
            mHourTextColorPreference.key = PreferenceHelper.getKeyOf(appWidgetId, Constant.PREF_KEY_HOUR_TEXT_COLOR)
            mMinuteTextColorPreference.key = PreferenceHelper.getKeyOf(appWidgetId, Constant.PREF_KEY_MINUTE_TEXT_COLOR)
        } else {
            // 在 HomeActivity 中
            findPreference(Constant.PREF_KEY_VERSION).summary = SystemUtil.versionName
            // 这两个在 preference 中没有值，因此不能用 onPreferenceChangeListener
            findPreference(Constant.PREF_KEY_THANKS).onPreferenceClickListener = Preference.OnPreferenceClickListener {
                showFragment(TAG_THANKS)
                true
            }
            findPreference(Constant.PREF_KEY_PROBLEMS).onPreferenceClickListener = Preference.OnPreferenceClickListener {
                showFragment(TAG_PROBLEMS)
                true
            }
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

    private fun showFragment(tag: String) {
        (activity as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.vSettingsLayout, when (tag) {
            TAG_THANKS -> ThanksFragment()
            TAG_PROBLEMS -> ProblemsFragment()
            else -> throw IllegalArgumentException("Tag \"$tag\" is illegal")
        }).addToBackStack(null).commit()
    }
}
