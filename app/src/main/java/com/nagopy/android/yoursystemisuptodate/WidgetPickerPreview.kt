package com.nagopy.android.yoursystemisuptodate

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.ComponentName
import android.os.Build
import android.util.Log
import android.widget.RemoteViews

private const val TAG = "WidgetPickerPreview"

// From Android 15 the widget picker can show this device's values instead of the
// previewLayout sample. The sample stays in use on older versions, until the app or a
// widget first runs, and while the system rate-limits this call (it then returns false).
// The system keeps the last preview, so it shows the values as of the last widget refresh.
internal fun AppWidgetManager.updatePickerPreview(
    provider: ComponentName,
    createPreview: () -> RemoteViews,
) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) return
    try {
        setWidgetPreview(
            provider,
            AppWidgetProviderInfo.WIDGET_CATEGORY_HOME_SCREEN,
            createPreview(),
        )
    } catch (e: RuntimeException) {
        Log.w(TAG, "Failed to set the picker preview of ${provider.className}", e)
    }
}
