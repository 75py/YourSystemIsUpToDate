package com.nagopy.android.yoursystemisuptodate

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

internal object GooglePlaySystemUpdate {
    private const val METADATA_PACKAGE = "com.google.android.modulemetadata"
    private const val PLAY_STORE_PACKAGE = "com.android.vending"

    @Suppress("DEPRECATION")
    fun version(context: Context): String? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return null
        return try {
            normalizeMainlineVersion(
                context.packageManager.getPackageInfo(METADATA_PACKAGE, 0).versionName,
            )
        } catch (_: PackageManager.NameNotFoundException) {
            null
        } catch (_: SecurityException) {
            null
        }
    }

    // These actions are used by AOSP Settings but are not guaranteed public SDK APIs.
    // Restrict delivery to Google Play; do not let an arbitrary app intercept the shortcut.
    fun open(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return false
        for (action in listOf(
            "android.settings.MODULE_UPDATE_VERSIONS",
            "android.settings.MODULE_UPDATE_SETTINGS",
        )) {
            try {
                context.startActivity(
                    Intent(action).setPackage(PLAY_STORE_PACKAGE)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
                )
                return true
            } catch (_: ActivityNotFoundException) {
                // Try the older action if the newer entry point is unavailable.
            } catch (_: SecurityException) {
                // Some builds restrict an entry point to privileged callers.
            }
        }
        return false
    }
}
