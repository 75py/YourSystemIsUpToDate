package com.nagopy.android.yoursystemisuptodate

import android.content.Intent

internal const val ACTION_OPEN_SYSTEM_UPDATE =
    "com.nagopy.android.yoursystemisuptodate.action.OPEN_SYSTEM_UPDATE"
internal const val ACTION_OPEN_PLAY_UPDATE =
    "com.nagopy.android.yoursystemisuptodate.action.OPEN_PLAY_UPDATE"
internal const val ACTION_ANDROID_WIDGET_TAP =
    "com.nagopy.android.yoursystemisuptodate.action.WIDGET_TAP"

internal const val PLAY_LAUNCHER_CLASS =
    "com.nagopy.android.yoursystemisuptodate.GooglePlayUpdateLauncher"

internal enum class UpdateTarget { SYSTEM, GOOGLE_PLAY, UNKNOWN }

// Keep both launcher icons, dedicated shortcuts, and existing widgets independent.
// Shortcuts and widgets name their destination in the action. Both launcher icons send
// ACTION_MAIN, so only the launched component (isPlayLauncher) tells those two apart.
// A launch without an action, such as `am start -n`, counts as a launcher launch.
internal fun resolveUpdateTarget(action: String?, isPlayLauncher: Boolean): UpdateTarget =
    when (action) {
        ACTION_OPEN_SYSTEM_UPDATE, ACTION_ANDROID_WIDGET_TAP -> UpdateTarget.SYSTEM
        ACTION_OPEN_PLAY_UPDATE -> UpdateTarget.GOOGLE_PLAY
        Intent.ACTION_MAIN, null ->
            if (isPlayLauncher) UpdateTarget.GOOGLE_PLAY else UpdateTarget.SYSTEM
        else -> UpdateTarget.UNKNOWN
    }
