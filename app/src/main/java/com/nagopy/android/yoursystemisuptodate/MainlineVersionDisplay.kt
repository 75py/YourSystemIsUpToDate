package com.nagopy.android.yoursystemisuptodate

import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

// Keep month-only precision. Never turn the metadata package's install timestamp into
// an update level, and preserve unrecognized version names rather than inventing a date.
internal fun mainlineVersionDisplay(version: String?): String? {
    val value = version?.trim()
        ?.takeUnless { it.isEmpty() || it.equals("unknown", ignoreCase = true) }
        ?: return null
    val match = MAINLINE_DATE_PREFIX.matchEntire(value) ?: return value
    val date = match.groupValues[1]
    val pattern = if (date.length == 10) "yyyy-MM-dd" else "yyyy-MM"
    val position = ParsePosition(0)
    val parsed = SimpleDateFormat(pattern, Locale.ROOT).apply {
        isLenient = false
        timeZone = TimeZone.getTimeZone("UTC")
    }.parse(date, position)
    return if (parsed != null && position.index == date.length) date else value
}

private val MAINLINE_DATE_PREFIX = Regex("(\\d{4}-\\d{2}(?:-\\d{2})?)(?:\\s+.*)?")
