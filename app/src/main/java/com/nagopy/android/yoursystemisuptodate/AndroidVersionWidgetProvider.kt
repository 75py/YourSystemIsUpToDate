package com.nagopy.android.yoursystemisuptodate

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.SizeF
import android.widget.RemoteViews
import kotlin.math.max
import kotlin.math.min

class AndroidVersionWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        updateWidgets(context, appWidgetManager, appWidgetIds)
    }

    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle?,
    ) {
        updateWidget(context, appWidgetManager, appWidgetId, newOptions)
    }

    companion object {
        private const val ACTION_WIDGET_TAP =
            "com.nagopy.android.yoursystemisuptodate.action.WIDGET_TAP"

        internal fun refreshAllWidgets(context: Context) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val provider = ComponentName(context, AndroidVersionWidgetProvider::class.java)
            updateWidgets(
                context,
                appWidgetManager,
                appWidgetManager.getAppWidgetIds(provider),
            )
        }

        private fun updateWidgets(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetIds: IntArray,
        ) {
            appWidgetIds.forEach { appWidgetId ->
                updateWidget(context, appWidgetManager, appWidgetId)
            }
        }

        private fun updateWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int,
            suppliedOptions: Bundle? = null,
        ) {
            val version = currentAndroidVersionDisplay()
            val tapIntent = createTapIntent(context, appWidgetId)
            val remoteViews = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                createResponsiveRemoteViews(context, version, tapIntent)
            } else {
                createLegacyRemoteViews(
                    context = context,
                    options = suppliedOptions ?: appWidgetManager.getAppWidgetOptions(appWidgetId),
                    version = version,
                    tapIntent = tapIntent,
                )
            }
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }

        private fun createResponsiveRemoteViews(
            context: Context,
            version: String,
            tapIntent: PendingIntent,
        ): RemoteViews {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                error("Responsive RemoteViews require Android 12 or later")
            }
            val viewsBySize = linkedMapOf(
                SizeF(MIN_WIDGET_WIDTH_DP, MIN_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.COMPACT,
                    version,
                    tapIntent,
                ),
                SizeF(MIN_WIDGET_WIDTH_DP, TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.NARROW_TALL,
                    version,
                    tapIntent,
                ),
                SizeF(STANDARD_WIDGET_WIDTH_DP, MIN_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_SHORT,
                    version,
                    tapIntent,
                ),
                SizeF(STANDARD_WIDGET_WIDTH_DP, TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_TALL,
                    version,
                    tapIntent,
                ),
                SizeF(TWO_COLUMN_WIDGET_WIDTH_DP, MIN_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_SHORT,
                    version,
                    tapIntent,
                ),
                SizeF(TWO_COLUMN_WIDGET_WIDTH_DP, TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_TALL,
                    version,
                    tapIntent,
                ),
                SizeF(WIDE_WIDGET_WIDTH_DP, MIN_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.WIDE_SHORT,
                    version,
                    tapIntent,
                ),
                SizeF(LARGE_WIDGET_WIDTH_DP, TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.LARGE,
                    version,
                    tapIntent,
                ),
            )
            return RemoteViews(viewsBySize)
        }

        private fun createLegacyRemoteViews(
            context: Context,
            options: Bundle,
            version: String,
            tapIntent: PendingIntent,
        ): RemoteViews {
            val minWidth = options.positiveDp(
                AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH,
                DEFAULT_WIDGET_WIDTH_DP,
            )
            val maxWidth = options.positiveDp(
                AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH,
                minWidth,
            )
            val minHeight = options.positiveDp(
                AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT,
                DEFAULT_WIDGET_HEIGHT_DP,
            )
            val maxHeight = options.positiveDp(
                AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT,
                minHeight,
            )

            val portrait = createRemoteViews(
                context,
                selectAndroidVersionWidgetLayout(
                    widthDp = min(minWidth, maxWidth),
                    heightDp = max(minHeight, maxHeight),
                ),
                version,
                tapIntent,
            )
            val landscape = createRemoteViews(
                context,
                selectAndroidVersionWidgetLayout(
                    widthDp = max(minWidth, maxWidth),
                    heightDp = min(minHeight, maxHeight),
                ),
                version,
                tapIntent,
            )
            return RemoteViews(landscape, portrait)
        }

        private fun createRemoteViews(
            context: Context,
            layout: AndroidVersionWidgetLayout,
            version: String,
            tapIntent: PendingIntent,
        ): RemoteViews = RemoteViews(context.packageName, layout.layoutResource).apply {
            setTextViewText(R.id.widget_version, version)
            setTextViewText(R.id.widget_action, context.getText(R.string.widget_action))
            setContentDescription(
                android.R.id.background,
                context.getString(R.string.widget_content_description, version),
            )
            setOnClickPendingIntent(android.R.id.background, tapIntent)
        }

        private val AndroidVersionWidgetLayout.layoutResource: Int
            get() = when (this) {
                AndroidVersionWidgetLayout.COMPACT -> R.layout.widget_android_version_compact
                AndroidVersionWidgetLayout.NARROW_TALL -> {
                    R.layout.widget_android_version_narrow_tall
                }
                AndroidVersionWidgetLayout.STANDARD_SHORT -> {
                    R.layout.widget_android_version_standard_short
                }
                AndroidVersionWidgetLayout.STANDARD_TALL -> {
                    R.layout.widget_android_version_standard_tall
                }
                AndroidVersionWidgetLayout.WIDE_SHORT -> R.layout.widget_android_version_wide_short
                AndroidVersionWidgetLayout.LARGE -> R.layout.widget_android_version_large
            }

        private fun createTapIntent(context: Context, appWidgetId: Int): PendingIntent {
            val intent = Intent(context, StartActivity::class.java).apply {
                action = ACTION_WIDGET_TAP
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            }
            val immutableFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }
            return PendingIntent.getActivity(
                context,
                appWidgetId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or immutableFlag,
            )
        }

        private fun Bundle.positiveDp(key: String, fallback: Float): Float =
            getInt(key).takeIf { it > 0 }?.toFloat() ?: fallback
    }
}
