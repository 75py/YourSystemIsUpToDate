package com.nagopy.android.yoursystemisuptodate

import org.junit.Assert.assertEquals
import org.junit.Test

class ModuleMetadataPackagesTest {
    private val known = listOf("com.google.android.modulemetadata", "com.android.modulemetadata")

    @Test fun missingConfigFallsBackToTheKnownProviders() {
        listOf(null, "", "   ").forEach {
            assertEquals(known, moduleMetadataPackages(it))
        }
    }

    @Test fun configuredProviderIsTriedFirstWithoutDuplicates() {
        assertEquals(known.reversed(), moduleMetadataPackages(" com.android.modulemetadata "))
        assertEquals(known, moduleMetadataPackages("com.google.android.modulemetadata"))
        assertEquals(listOf("com.example.metadata") + known, moduleMetadataPackages("com.example.metadata"))
    }
}
