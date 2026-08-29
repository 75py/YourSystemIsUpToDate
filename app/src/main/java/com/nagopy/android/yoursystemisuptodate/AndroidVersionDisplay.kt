package com.nagopy.android.yoursystemisuptodate

import android.os.Build

internal fun currentAndroidVersionDisplay(): String {
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> selectAndroidVersionDisplay(
            sdkInt = Build.VERSION.SDK_INT,
            release = Build.VERSION.RELEASE,
            releaseOrCodename = Build.VERSION.RELEASE_OR_CODENAME,
            releaseOrPreviewDisplay = Build.VERSION.RELEASE_OR_PREVIEW_DISPLAY,
        )
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> selectAndroidVersionDisplay(
            sdkInt = Build.VERSION.SDK_INT,
            release = Build.VERSION.RELEASE,
            releaseOrCodename = Build.VERSION.RELEASE_OR_CODENAME,
        )
        else -> selectAndroidVersionDisplay(
            sdkInt = Build.VERSION.SDK_INT,
            release = Build.VERSION.RELEASE,
        )
    }
}

internal fun selectAndroidVersionDisplay(
    sdkInt: Int,
    release: String,
    releaseOrCodename: String? = null,
    releaseOrPreviewDisplay: String? = null,
): String = when {
    sdkInt >= 33 -> checkNotNull(releaseOrPreviewDisplay)
    sdkInt >= 30 -> checkNotNull(releaseOrCodename)
    else -> release
}
