package com.nagopy.android.yoursystemisuptodate

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AndroidVersionWidgetDataTest {
    @Test
    fun createAndroidVersionWidgetData_exposesSecurityPatchFromApi23() {
        assertNull(
            createAndroidVersionWidgetData(
                sdkInt = 22,
                version = "5.1",
                securityPatch = "2016-01-01",
                buildId = "LMY47D",
            ).securityPatch,
        )
        assertEquals(
            "2016-01-01",
            createAndroidVersionWidgetData(
                sdkInt = 23,
                version = "6.0",
                securityPatch = " 2016-01-01 ",
                buildId = "MRA58K",
            ).securityPatch,
        )
    }

    @Test
    fun createAndroidVersionWidgetData_normalizesOptionalValues() {
        assertEquals(
            AndroidVersionWidgetData(
                version = "17",
                securityPatch = null,
                buildId = null,
                apiLevel = 37,
            ),
            createAndroidVersionWidgetData(
                sdkInt = 37,
                version = "17",
                securityPatch = " UNKNOWN ",
                buildId = "   ",
            ),
        )
    }

    @Test
    fun formatCompactSecurityPatch_shortensIsoDateOnly() {
        assertEquals("2026-08", formatCompactSecurityPatch("2026-08-05"))
        assertEquals("2026-8-5", formatCompactSecurityPatch("2026-8-5"))
        assertNull(formatCompactSecurityPatch("unknown"))
        assertNull(formatCompactSecurityPatch("  "))
    }

    @Test
    fun selectAndroidVersionWidgetContent_limitsFieldsToEachLayout() {
        val data = AndroidVersionWidgetData(
            version = "17",
            securityPatch = "2026-08-05",
            buildId = "BP2A.260805.001",
            apiLevel = 37,
        )

        val versionOnly = AndroidVersionWidgetContent(version = "17")
        assertEquals(
            versionOnly,
            selectAndroidVersionWidgetContent(data, AndroidVersionWidgetLayout.COMPACT),
        )
        assertEquals(
            versionOnly,
            selectAndroidVersionWidgetContent(data, AndroidVersionWidgetLayout.STANDARD_SHORT),
        )
        assertEquals(
            AndroidVersionWidgetContent(version = "17", securityPatch = "2026-08"),
            selectAndroidVersionWidgetContent(data, AndroidVersionWidgetLayout.NARROW_TALL),
        )
        assertEquals(
            AndroidVersionWidgetContent(
                version = "17",
                securityPatch = "2026-08",
                apiLevel = 37,
            ),
            selectAndroidVersionWidgetContent(data, AndroidVersionWidgetLayout.NARROW_EXTRA_TALL),
        )

        val fullMetadata = AndroidVersionWidgetContent(
            version = "17",
            securityPatch = "2026-08-05",
            buildId = "BP2A.260805.001",
        )
        assertEquals(
            fullMetadata,
            selectAndroidVersionWidgetContent(data, AndroidVersionWidgetLayout.STANDARD_TALL),
        )
        assertEquals(
            fullMetadata,
            selectAndroidVersionWidgetContent(data, AndroidVersionWidgetLayout.WIDE_SHORT),
        )
        assertEquals(
            AndroidVersionWidgetContent(
                version = "17",
                securityPatch = "2026-08-05",
                buildId = "BP2A.260805.001",
                apiLevel = 37,
            ),
            selectAndroidVersionWidgetContent(data, AndroidVersionWidgetLayout.LARGE),
        )
    }
}
