package com.nagopy.android.yoursystemisuptodate

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Build
import android.util.Log

internal object GooglePlaySystemUpdate {
    private const val TAG = "GooglePlaySystemUpdate"
    private const val PLAY_STORE_PACKAGE = "com.android.vending"

    fun version(context: Context): String? {
        for (packageName in moduleMetadataPackages(configuredMetadataProvider())) {
            try {
                @Suppress("DEPRECATION")
                val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
                return packageInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                // Try the next candidate.
                Log.e(TAG, "Module metadata package not found: $packageName", e)
            } catch (e: SecurityException) {
                // Try the next candidate.
                Log.e(TAG, "Not allowed to read module metadata package: $packageName", e)
            }
        }
        return null
    }

    private fun configuredMetadataProvider(): String? {
        val resources = Resources.getSystem()
        // Settings reads the provider from this framework config, which is not a public resource.
        @SuppressLint("DiscouragedApi")
        val id = resources.getIdentifier("config_defaultModuleMetadataProvider", "string", "android")
        return if (id != 0) resources.getString(id) else null
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
            } catch (e: ActivityNotFoundException) {
                // Try the older action if the newer entry point is unavailable.
                Log.e(TAG, "No Google Play activity handles $action", e)
            } catch (e: SecurityException) {
                // Some builds restrict an entry point to privileged callers.
                Log.e(TAG, "Not allowed to start $action", e)
            }
        }
        return false
    }
}

private val KNOWN_METADATA_PACKAGES = listOf(
    "com.google.android.modulemetadata",
    "com.android.modulemetadata",
)

// Prefer the provider the device declares. Only the known packages are listed in the
// manifest's <queries>, so any other provider stays invisible on Android 11 and later.
internal fun moduleMetadataPackages(configuredProvider: String?): List<String> =
    (listOfNotNull(configuredProvider?.trim()?.takeUnless { it.isEmpty() }) +
        KNOWN_METADATA_PACKAGES).distinct()
