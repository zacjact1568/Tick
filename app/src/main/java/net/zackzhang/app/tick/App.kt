package net.zackzhang.app.tick

import android.app.Application
import android.content.Context
import net.zackzhang.app.tick.model.preference.PreferenceHelper

class App : Application() {

    companion object {

        lateinit var context: Context
            private set

        val preferenceHelper by lazy { PreferenceHelper() }
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }
}