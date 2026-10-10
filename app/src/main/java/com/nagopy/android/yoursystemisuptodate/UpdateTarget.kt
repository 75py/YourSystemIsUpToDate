package com.nagopy.android.yoursystemisuptodate

import android.content.Intent

internal const val ACTION_OPEN_PLAY_UPDATE =
    "com.nagopy.android.yoursystemisuptodate.action.OPEN_PLAY_UPDATE"
internal const val ACTION_ANDROID_WIDGET_TAP =
    "com.nagopy.android.yoursystemisuptodate.action.WIDGET_TAP"

internal enum class UpdateTarget { SYSTEM, GOOGLE_PLAY, UNKNOWN }

// The launcher icon and the Android version widget open OS update. The Play shortcut and
// the Play widget name their destination in the action.
// A launch without an action, such as `am start -n`, counts as a launcher launch.
internal fun resolveUpdateTarget(action: String?): UpdateTarget =
    when (action) {
        Intent.ACTION_MAIN, null, ACTION_ANDROID_WIDGET_TAP -> UpdateTarget.SYSTEM
        ACTION_OPEN_PLAY_UPDATE -> UpdateTarget.GOOGLE_PLAY
        else -> UpdateTarget.UNKNOWN
    }
