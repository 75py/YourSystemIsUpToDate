package com.nagopy.android.yoursystemisuptodate

// Show the metadata version name as it is. Only a missing value becomes "Unavailable".
internal fun mainlineVersionDisplay(version: String?): String? = version
    ?.trim()
    ?.takeUnless { it.isEmpty() || it.equals("unknown", ignoreCase = true) }
