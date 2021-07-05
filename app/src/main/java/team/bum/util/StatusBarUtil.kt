package team.bum.util

import android.app.Activity

object StatusBarUtil {
    fun changeColor(activity: Activity, color: Int) {
        activity.window?.run {
            statusBarColor = color
        }
    }
}