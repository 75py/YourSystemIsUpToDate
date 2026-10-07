package com.nagopy.android.yoursystemisuptodate

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class MainlineVersionDisplayTest {
    @Test fun missingMetadataIsUnavailable() {
        listOf(null, "", "   ", "unknown", " UNKNOWN ").forEach {
            assertNull(mainlineVersionDisplay(it))
        }
    }

    @Test fun dateWithAndroidReleaseSuffixKeepsOnlyTheUpdateLevel() {
        assertEquals("2024-05-01", mainlineVersionDisplay(" 2024-05-01 R "))
        assertEquals("2024-02-29", mainlineVersionDisplay("2024-02-29"))
    }

    @Test fun monthOnlyVersionsDoNotInventADay() {
        assertEquals("2026-09", mainlineVersionDisplay("2026-09"))
        assertEquals("2026-09", mainlineVersionDisplay("2026-09 release"))
    }

    @Test fun malformedDatesAndFutureFormatsAreNotSilentlyChanged() {
        listOf("2025-02-29 R", "2026-13-01", "2026-00", "2026-09-00",
            "2026-09-011", "2026-09-01beta", "release-42").forEach {
            assertEquals(it, mainlineVersionDisplay(it))
        }
    }
}
