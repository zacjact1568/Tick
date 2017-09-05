package me.imzack.app.tick.util

import android.support.annotation.ArrayRes
import me.imzack.app.tick.App

object ResourceUtil {

    fun getStringArray(@ArrayRes resId: Int): Array<String> = App.context.resources.getStringArray(resId)
}