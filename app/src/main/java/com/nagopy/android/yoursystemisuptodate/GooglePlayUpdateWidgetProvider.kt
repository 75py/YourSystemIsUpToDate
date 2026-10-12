package com.nagopy.android.yoursystemisuptodate

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetProviderInfo
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews

class GooglePlayUpdateWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        updateWidgets(context, manager, ids)
    }

    companion object {
        private const val TAG = "GooglePlayUpdateWidget"

        // Android 15 added picker previews that an app sets at runtime.
        private val supportsGeneratedPreview: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM

        internal fun refreshAllWidgets(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            updateWidgets(context, manager, manager.getAppWidgetIds(
                ComponentName(context, GooglePlayUpdateWidgetProvider::class.java),
            ))
        }

        private fun updateWidgets(context: Context, manager: AppWidgetManager, ids: IntArray) {
            // The picker preview is updated even when no widget is placed yet.
            if (ids.isEmpty() && !supportsGeneratedPreview) return
            val availableVersion = mainlineVersionDisplay(GooglePlaySystemUpdate.version(context))
            updatePickerPreview(context, manager, availableVersion)
            val version = availableVersion ?: context.getString(R.string.play_update_unknown)
            ids.forEach { id ->
                val intent = Intent(context, StartActivity::class.java)
                    .setAction(ACTION_OPEN_PLAY_UPDATE)
                val tap = PendingIntent.getActivity(context, id, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                val views = RemoteViews(context.packageName, R.layout.widget_google_play_update).apply {
                    setTextViewText(R.id.play_widget_version, version)
                    setContentDescription(android.R.id.background,
                        context.getString(R.string.play_widget_accessibility, version))
                    setOnClickPendingIntent(android.R.id.background, tap)
                }
                manager.updateAppWidget(id, views)
            }
        }

        // Shows this device's level in the picker instead of the previewLayout sample. The
        // sample stays in use on older versions, until the app or a widget first runs, while
        // the system rate-limits the call (it then returns false), and when there is no
        // level. The system keeps the last preview, so it shows the level as of the last
        // widget refresh.
        private fun updatePickerPreview(
            context: Context,
            manager: AppWidgetManager,
            version: String?,
        ) {
            if (!supportsGeneratedPreview) return
            val provider = ComponentName(context, GooglePlayUpdateWidgetProvider::class.java)
            val category = AppWidgetProviderInfo.WIDGET_CATEGORY_HOME_SCREEN
            try {
                if (version == null) {
                    manager.removeWidgetPreview(provider, category)
                } else {
                    val preview = RemoteViews(
                        context.packageName,
                        R.layout.widget_google_play_update_preview,
                    ).apply { setTextViewText(R.id.play_widget_version, version) }
                    manager.setWidgetPreview(provider, category, preview)
                }
            } catch (e: RuntimeException) {
                Log.w(TAG, "Failed to update the widget picker preview", e)
            }
        }
    }
}
