package com.nagopy.android.yoursystemisuptodate

import org.junit.Assert.assertEquals
import org.junit.Test

class AndroidVersionDisplayTest {
    @Test
    fun selectAndroidVersionDisplay_belowApi30UsesRelease() {
        assertEquals(
            "release",
            selectAndroidVersionDisplay(
                sdkInt = 29,
                release = "release",
            ),
        )
    }

    @Test
    fun selectAndroidVersionDisplay_api30Through32UsesReleaseOrCodename() {
        listOf(30, 31, 32).forEach { sdkInt ->
            assertEquals(
                "release-or-codename",
                selectAndroidVersionDisplay(
                    sdkInt = sdkInt,
                    release = "release",
                    releaseOrCodename = "release-or-codename",
                ),
            )
        }
    }

    @Test
    fun selectAndroidVersionDisplay_api33AndAboveUsesReleaseOrPreviewDisplay() {
        listOf(33, 36).forEach { sdkInt ->
            assertEquals(
                "release-or-preview-display",
                selectAndroidVersionDisplay(
                    sdkInt = sdkInt,
                    release = "release",
                    releaseOrCodename = "release-or-codename",
                    releaseOrPreviewDisplay = "release-or-preview-display",
                ),
            )
        }
    }
}
