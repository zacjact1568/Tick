package net.zackzhang.app.tick.util

import android.support.annotation.ArrayRes
import net.zackzhang.app.tick.App

object ResourceUtil {

    fun getStringArray(@ArrayRes resId: Int): Array<String> = App.context.resources.getStringArray(resId)
}