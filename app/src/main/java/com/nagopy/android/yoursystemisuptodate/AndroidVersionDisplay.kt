package com.nagopy.android.yoursystemisuptodate

import android.os.Build

internal fun currentAndroidVersionDisplay(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        selectAndroidVersionDisplay(
            sdkInt = Build.VERSION.SDK_INT,
            releaseOrCodename = Build.VERSION.RELEASE_OR_CODENAME,
            releaseOrPreviewDisplay = Build.VERSION.RELEASE_OR_PREVIEW_DISPLAY,
        )
    } else {
        selectAndroidVersionDisplay(
            sdkInt = Build.VERSION.SDK_INT,
            releaseOrCodename = Build.VERSION.RELEASE_OR_CODENAME,
        )
    }
}

internal fun selectAndroidVersionDisplay(
    sdkInt: Int,
    releaseOrCodename: String,
    releaseOrPreviewDisplay: String? = null,
): String = if (sdkInt >= 33) checkNotNull(releaseOrPreviewDisplay) else releaseOrCodename
