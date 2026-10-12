package com.nagopy.android.yoursystemisuptodate

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews

class GooglePlayUpdateWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        updateWidgets(context, manager, ids)
    }

    companion object {
        internal fun refreshAllWidgets(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            updateWidgets(context, manager, manager.getAppWidgetIds(
                ComponentName(context, GooglePlayUpdateWidgetProvider::class.java),
            ))
        }

        private fun updateWidgets(context: Context, manager: AppWidgetManager, ids: IntArray) {
            // The picker preview is updated even when no widget is placed yet.
            val updatesPreview = Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM
            if (ids.isEmpty() && !updatesPreview) return
            val availableVersion = mainlineVersionDisplay(GooglePlaySystemUpdate.version(context))
            if (availableVersion != null) {
                updatePickerPreview(context, manager, availableVersion)
            }
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

        // Without a level, the picker keeps the sample from previewLayout.
        private fun updatePickerPreview(
            context: Context,
            manager: AppWidgetManager,
            version: String,
        ) {
            manager.updatePickerPreview(
                ComponentName(context, GooglePlayUpdateWidgetProvider::class.java),
            ) {
                RemoteViews(
                    context.packageName,
                    R.layout.widget_google_play_update_preview,
                ).apply { setTextViewText(R.id.play_widget_version, version) }
            }
        }
    }
}
