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

    @Test fun versionNameIsShownAsIs() {
        listOf("2024-05-01", "2026-09", "2024-05-01 R", "release-42").forEach {
            assertEquals(it, mainlineVersionDisplay(it))
        }
    }

    @Test fun surroundingWhitespaceIsDropped() {
        assertEquals("2024-05-01", mainlineVersionDisplay(" 2024-05-01\n"))
    }
}
