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
import android.view.View
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
            val data = currentAndroidVersionWidgetData()
            val tapIntent = createTapIntent(context, appWidgetId)
            val remoteViews = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                createResponsiveRemoteViews(context, data, tapIntent)
            } else {
                createLegacyRemoteViews(
                    context = context,
                    options = suppliedOptions ?: appWidgetManager.getAppWidgetOptions(appWidgetId),
                    data = data,
                    tapIntent = tapIntent,
                )
            }
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }

        private fun createResponsiveRemoteViews(
            context: Context,
            data: AndroidVersionWidgetData,
            tapIntent: PendingIntent,
        ): RemoteViews {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                error("Responsive RemoteViews require Android 12 or later")
            }
            val viewsBySize = linkedMapOf(
                SizeF(MIN_WIDGET_WIDTH_DP, MIN_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.COMPACT,
                    data,
                    tapIntent,
                ),
                SizeF(MIN_WIDGET_WIDTH_DP, TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.NARROW_TALL,
                    data,
                    tapIntent,
                ),
                SizeF(MIN_WIDGET_WIDTH_DP, EXTRA_TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.NARROW_EXTRA_TALL,
                    data,
                    tapIntent,
                ),
                SizeF(STANDARD_WIDGET_WIDTH_DP, MIN_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_SHORT,
                    data,
                    tapIntent,
                ),
                SizeF(STANDARD_WIDGET_WIDTH_DP, TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_TALL,
                    data,
                    tapIntent,
                ),
                SizeF(STANDARD_WIDGET_WIDTH_DP, EXTRA_TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_TALL,
                    data,
                    tapIntent,
                ),
                SizeF(TWO_COLUMN_WIDGET_WIDTH_DP, MIN_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_SHORT,
                    data,
                    tapIntent,
                ),
                SizeF(TWO_COLUMN_WIDGET_WIDTH_DP, TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_TALL,
                    data,
                    tapIntent,
                ),
                SizeF(TWO_COLUMN_WIDGET_WIDTH_DP, EXTRA_TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.STANDARD_TALL,
                    data,
                    tapIntent,
                ),
                SizeF(WIDE_WIDGET_WIDTH_DP, MIN_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.WIDE_SHORT,
                    data,
                    tapIntent,
                ),
                SizeF(LARGE_WIDGET_WIDTH_DP, TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.LARGE,
                    data,
                    tapIntent,
                ),
                SizeF(LARGE_WIDGET_WIDTH_DP, EXTRA_TALL_WIDGET_HEIGHT_DP) to createRemoteViews(
                    context,
                    AndroidVersionWidgetLayout.LARGE,
                    data,
                    tapIntent,
                ),
            )
            return RemoteViews(viewsBySize)
        }

        private fun createLegacyRemoteViews(
            context: Context,
            options: Bundle,
            data: AndroidVersionWidgetData,
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
                data,
                tapIntent,
            )
            val landscape = createRemoteViews(
                context,
                selectAndroidVersionWidgetLayout(
                    widthDp = max(minWidth, maxWidth),
                    heightDp = min(minHeight, maxHeight),
                ),
                data,
                tapIntent,
            )
            return RemoteViews(landscape, portrait)
        }

        private fun createRemoteViews(
            context: Context,
            layout: AndroidVersionWidgetLayout,
            data: AndroidVersionWidgetData,
            tapIntent: PendingIntent,
        ): RemoteViews = RemoteViews(context.packageName, layout.layoutResource).apply {
            val content = selectAndroidVersionWidgetContent(data, layout)
            setTextViewText(R.id.widget_version, content.version)
            configureMetadata(layout, content)
            setContentDescription(
                android.R.id.background,
                buildAccessibilityDescription(context, content),
            )
            setOnClickPendingIntent(android.R.id.background, tapIntent)
        }

        private val AndroidVersionWidgetLayout.layoutResource: Int
            get() = when (this) {
                AndroidVersionWidgetLayout.COMPACT -> R.layout.widget_android_version_compact
                AndroidVersionWidgetLayout.NARROW_TALL -> {
                    R.layout.widget_android_version_narrow_tall
                }
                AndroidVersionWidgetLayout.NARROW_EXTRA_TALL -> {
                    R.layout.widget_android_version_narrow_extra_tall
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

        private fun RemoteViews.configureMetadata(
            layout: AndroidVersionWidgetLayout,
            content: AndroidVersionWidgetContent,
        ) {
            when (layout) {
                AndroidVersionWidgetLayout.COMPACT,
                AndroidVersionWidgetLayout.STANDARD_SHORT,
                -> Unit

                AndroidVersionWidgetLayout.NARROW_TALL -> {
                    setOptionalText(
                        R.id.widget_security_patch_group,
                        R.id.widget_security_patch,
                        content.securityPatch,
                    )
                }

                AndroidVersionWidgetLayout.NARROW_EXTRA_TALL -> {
                    setMetadataContainerVisibility(
                        content.securityPatch != null || content.apiLevel != null,
                    )
                    setOptionalText(
                        R.id.widget_security_patch_group,
                        R.id.widget_security_patch,
                        content.securityPatch,
                    )
                    setOptionalText(
                        R.id.widget_api_level_group,
                        R.id.widget_api_level,
                        content.apiLevel?.toString(),
                    )
                }

                AndroidVersionWidgetLayout.STANDARD_TALL,
                AndroidVersionWidgetLayout.WIDE_SHORT,
                -> {
                    setMetadataContainerVisibility(
                        content.securityPatch != null || content.buildId != null,
                    )
                    setOptionalText(
                        R.id.widget_security_patch_group,
                        R.id.widget_security_patch,
                        content.securityPatch,
                    )
                    setOptionalText(
                        R.id.widget_build_id_group,
                        R.id.widget_build_id,
                        content.buildId,
                    )
                }

                AndroidVersionWidgetLayout.LARGE -> {
                    setMetadataContainerVisibility(
                        content.securityPatch != null ||
                            content.buildId != null ||
                            content.apiLevel != null,
                    )
                    setOptionalText(
                        R.id.widget_security_patch_group,
                        R.id.widget_security_patch,
                        content.securityPatch,
                    )
                    setOptionalText(
                        R.id.widget_build_id_group,
                        R.id.widget_build_id,
                        content.buildId,
                    )
                    setOptionalText(
                        R.id.widget_api_level_group,
                        R.id.widget_api_level,
                        content.apiLevel?.toString(),
                    )
                }
            }
        }

        private fun RemoteViews.setMetadataContainerVisibility(visible: Boolean) {
            setViewVisibility(R.id.widget_metadata, if (visible) View.VISIBLE else View.GONE)
        }

        private fun RemoteViews.setOptionalText(
            groupId: Int,
            valueId: Int,
            value: String?,
        ) {
            setViewVisibility(groupId, if (value != null) View.VISIBLE else View.GONE)
            if (value != null) {
                setTextViewText(valueId, value)
            }
        }

        private fun buildAccessibilityDescription(
            context: Context,
            content: AndroidVersionWidgetContent,
        ): String = buildList {
            add(context.getString(R.string.widget_accessibility_android_version, content.version))
            content.securityPatch?.let {
                add(context.getString(R.string.widget_accessibility_security_patch, it))
            }
            content.buildId?.let {
                add(context.getString(R.string.widget_accessibility_build_id, it))
            }
            content.apiLevel?.let {
                add(context.getString(R.string.widget_accessibility_api_level, it))
            }
        }.joinToString(context.getString(R.string.widget_accessibility_separator))

        private fun currentAndroidVersionWidgetData(): AndroidVersionWidgetData {
            val securityPatch = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Build.VERSION.SECURITY_PATCH
            } else {
                null
            }
            return createAndroidVersionWidgetData(
                sdkInt = Build.VERSION.SDK_INT,
                version = currentAndroidVersionDisplay(),
                securityPatch = securityPatch,
                buildId = Build.ID,
            )
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
