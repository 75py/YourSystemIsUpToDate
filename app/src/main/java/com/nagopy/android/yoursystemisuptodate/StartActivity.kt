package com.nagopy.android.yoursystemisuptodate

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.os.Bundle
import android.widget.Toast

class StartActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openSystemUpdateSettings()
        finish()
    }

    private fun openSystemUpdateSettings() {
        val intent = Intent(ACTION_SYSTEM_UPDATE_SETTINGS)
        try {
            intent.component = findSystemUpdateActivity(intent) ?: run {
                showOpenFailure()
                return
            }
            startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            showOpenFailure()
        } catch (_: SecurityException) {
            showOpenFailure()
        }
    }

    private fun findSystemUpdateActivity(intent: Intent): ComponentName? {
        val resolveInfos = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            packageManager.queryIntentActivities(intent, PackageManager.MATCH_SYSTEM_ONLY)
        } else {
            packageManager.queryIntentActivities(intent, 0).filter { it.isSystemActivity() }
        }
        val candidates = resolveInfos.mapNotNull { resolveInfo ->
            val activityInfo = resolveInfo.activityInfo ?: return@mapNotNull null
            SystemUpdateActivityCandidate(
                packageName = activityInfo.packageName,
                className = activityInfo.name,
                priority = resolveInfo.priority,
            )
        }
        val selected = selectSystemUpdateActivity(candidates) ?: return null
        return ComponentName(selected.packageName, selected.className)
    }

    private fun ResolveInfo.isSystemActivity(): Boolean {
        val flags = activityInfo?.applicationInfo?.flags ?: return false
        return flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

    private fun showOpenFailure() {
        Toast.makeText(this, R.string.system_update_open_failed, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ACTION_SYSTEM_UPDATE_SETTINGS = "android.settings.SYSTEM_UPDATE_SETTINGS"
    }
}

internal data class SystemUpdateActivityCandidate(
    val packageName: String,
    val className: String,
    val priority: Int,
)

internal fun selectSystemUpdateActivity(
    candidates: List<SystemUpdateActivityCandidate>,
): SystemUpdateActivityCandidate? = candidates
    .filter { it.priority > 0 }
    .maxByOrNull { it.priority }
