package net.zackzhang.app.tick.util

import android.util.Log
import net.zackzhang.app.tick.App

object LogUtil {

    private val DEF_TAG = "____"

    val VERBOSE = 1
    val DEBUG = 2
    val INFO = 3
    val WARN = 4
    val ERROR = 5
    val NOTHING = 6

    //level的值是多少，就打印对应常量级别以上的日志（级别就是常量值）
    //e.g.为WARN就打印WARN级别以上的日志（WARN、ERROR）
    var level = VERBOSE

    fun v(tag: String, msg: String) {
        if (level <= VERBOSE) Log.v(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (level <= DEBUG) Log.d(tag, msg)
    }

    fun d(tag: String, msg: Int) {
        if (level <= DEBUG) Log.d(tag, msg.toString() + "")
    }

    fun i(tag: String, msg: String) {
        if (level <= INFO) Log.i(tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (level <= WARN) Log.w(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (level <= ERROR) Log.e(tag, msg)
    }

    fun d(msg: String) {
        d(DEF_TAG, msg)
    }

    fun d(msg: Int) {
        d(DEF_TAG, msg)
    }

    fun here() {
        d("****HERE****")
    }

    fun logAllSharedPreferences() {
        d(App.preferenceHelper.allValues.toString())
    }
}
