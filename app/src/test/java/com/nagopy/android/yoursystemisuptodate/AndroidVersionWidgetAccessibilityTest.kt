package com.nagopy.android.yoursystemisuptodate

import org.junit.Assert.assertEquals
import org.junit.Test

class AndroidVersionWidgetAccessibilityTest {
    @Test
    fun buildDescription_inEnglishIncludesMetadataAndDestination() {
        assertEquals(
            "Android 17. Security patch 2026-08-05. Build BP2A.260805.001. " +
                "API level 37. Opens system update settings",
            buildAndroidVersionWidgetAccessibilityDescription(
                androidVersion = "Android 17",
                securityPatch = "Security patch 2026-08-05",
                buildId = "Build BP2A.260805.001",
                apiLevel = "API level 37",
                action = "Opens system update settings",
                separator = ". ",
            ),
        )
    }

    @Test
    fun buildDescription_inJapaneseIncludesAvailableMetadataAndDestination() {
        assertEquals(
            "Android 17、セキュリティ パッチ 2026-08、API レベル 37、" +
                "システム アップデート設定を開きます",
            buildAndroidVersionWidgetAccessibilityDescription(
                androidVersion = "Android 17",
                securityPatch = "セキュリティ パッチ 2026-08",
                buildId = null,
                apiLevel = "API レベル 37",
                action = "システム アップデート設定を開きます",
                separator = "、",
            ),
        )
    }

    @Test
    fun buildDescription_omitsEachUnavailableMetadataValue() {
        assertEquals(
            "Android 17. Opens system update settings",
            buildAndroidVersionWidgetAccessibilityDescription(
                androidVersion = "Android 17",
                securityPatch = null,
                buildId = null,
                apiLevel = null,
                action = "Opens system update settings",
                separator = ". ",
            ),
        )
        assertEquals(
            "Android 17. Build BP2A.260805.001. Opens system update settings",
            buildAndroidVersionWidgetAccessibilityDescription(
                androidVersion = "Android 17",
                securityPatch = null,
                buildId = "Build BP2A.260805.001",
                apiLevel = null,
                action = "Opens system update settings",
                separator = ". ",
            ),
        )
    }
}
