package com.nagopy.android.yoursystemisuptodate

internal const val MIN_WIDGET_WIDTH_DP = 40f
internal const val MIN_WIDGET_HEIGHT_DP = 40f
internal const val STANDARD_WIDGET_WIDTH_DP = 100f
internal const val TWO_COLUMN_WIDGET_WIDTH_DP = 180f
internal const val WIDE_WIDGET_WIDTH_DP = 220f
internal const val LARGE_WIDGET_WIDTH_DP = 220f
internal const val TALL_WIDGET_HEIGHT_DP = 110f
internal const val DEFAULT_WIDGET_WIDTH_DP = 110f
internal const val DEFAULT_WIDGET_HEIGHT_DP = 40f

internal enum class AndroidVersionWidgetLayout {
    COMPACT,
    NARROW_TALL,
    STANDARD_SHORT,
    STANDARD_TALL,
    WIDE_SHORT,
    LARGE,
}

internal fun selectAndroidVersionWidgetLayout(
    widthDp: Float,
    heightDp: Float,
): AndroidVersionWidgetLayout = when {
    widthDp < STANDARD_WIDGET_WIDTH_DP && heightDp < TALL_WIDGET_HEIGHT_DP -> {
        AndroidVersionWidgetLayout.COMPACT
    }
    widthDp < STANDARD_WIDGET_WIDTH_DP -> AndroidVersionWidgetLayout.NARROW_TALL
    widthDp < WIDE_WIDGET_WIDTH_DP && heightDp < TALL_WIDGET_HEIGHT_DP -> {
        AndroidVersionWidgetLayout.STANDARD_SHORT
    }
    heightDp < TALL_WIDGET_HEIGHT_DP -> AndroidVersionWidgetLayout.WIDE_SHORT
    widthDp < LARGE_WIDGET_WIDTH_DP -> AndroidVersionWidgetLayout.STANDARD_TALL
    else -> AndroidVersionWidgetLayout.LARGE
}
