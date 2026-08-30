package com.nagopy.android.yoursystemisuptodate

internal data class AndroidVersionWidgetData(
    val version: String,
    val securityPatch: String?,
    val buildId: String?,
    val apiLevel: Int?,
)

internal data class AndroidVersionWidgetContent(
    val version: String,
    val securityPatch: String? = null,
    val buildId: String? = null,
    val apiLevel: Int? = null,
)

internal fun createAndroidVersionWidgetData(
    sdkInt: Int,
    version: String,
    securityPatch: String?,
    buildId: String?,
): AndroidVersionWidgetData = AndroidVersionWidgetData(
    version = version,
    securityPatch = if (sdkInt >= 23) normalizeOptionalBuildValue(securityPatch) else null,
    buildId = normalizeOptionalBuildValue(buildId),
    apiLevel = sdkInt.takeIf { it > 0 },
)

internal fun selectAndroidVersionWidgetContent(
    data: AndroidVersionWidgetData,
    layout: AndroidVersionWidgetLayout,
): AndroidVersionWidgetContent = when (layout) {
    AndroidVersionWidgetLayout.COMPACT,
    AndroidVersionWidgetLayout.STANDARD_SHORT,
    -> AndroidVersionWidgetContent(version = data.version)

    AndroidVersionWidgetLayout.NARROW_TALL -> AndroidVersionWidgetContent(
        version = data.version,
        securityPatch = formatCompactSecurityPatch(data.securityPatch),
    )

    AndroidVersionWidgetLayout.NARROW_EXTRA_TALL -> AndroidVersionWidgetContent(
        version = data.version,
        securityPatch = formatCompactSecurityPatch(data.securityPatch),
        apiLevel = data.apiLevel,
    )

    AndroidVersionWidgetLayout.STANDARD_TALL,
    AndroidVersionWidgetLayout.WIDE_SHORT,
    -> AndroidVersionWidgetContent(
        version = data.version,
        securityPatch = data.securityPatch,
        buildId = data.buildId,
    )

    AndroidVersionWidgetLayout.LARGE -> AndroidVersionWidgetContent(
        version = data.version,
        securityPatch = data.securityPatch,
        buildId = data.buildId,
        apiLevel = data.apiLevel,
    )
}

internal fun formatCompactSecurityPatch(securityPatch: String?): String? {
    val normalized = normalizeOptionalBuildValue(securityPatch) ?: return null
    return if (SECURITY_PATCH_PATTERN.matches(normalized)) {
        normalized.substring(0, 7)
    } else {
        normalized
    }
}

private fun normalizeOptionalBuildValue(value: String?): String? = value
    ?.trim()
    ?.takeUnless { it.isEmpty() || it.equals("unknown", ignoreCase = true) }

private val SECURITY_PATCH_PATTERN = Regex("\\d{4}-\\d{2}-\\d{2}")
