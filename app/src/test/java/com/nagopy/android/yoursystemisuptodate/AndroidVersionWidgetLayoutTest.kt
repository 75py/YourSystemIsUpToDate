package com.nagopy.android.yoursystemisuptodate

import org.junit.Assert.assertEquals
import org.junit.Test

class AndroidVersionWidgetLayoutTest {
    @Test
    fun selectAndroidVersionWidgetLayout_coversRepresentativeDpSizes() {
        assertLayout(AndroidVersionWidgetLayout.COMPACT, widthDp = 57f, heightDp = 102f)
        assertLayout(AndroidVersionWidgetLayout.NARROW_TALL, widthDp = 57f, heightDp = 220f)
        assertLayout(AndroidVersionWidgetLayout.NARROW_EXTRA_TALL, widthDp = 57f, heightDp = 337f)
        assertLayout(AndroidVersionWidgetLayout.STANDARD_SHORT, widthDp = 180f, heightDp = 102f)
        assertLayout(AndroidVersionWidgetLayout.STANDARD_TALL, widthDp = 180f, heightDp = 220f)
        assertLayout(AndroidVersionWidgetLayout.WIDE_SHORT, widthDp = 260f, heightDp = 102f)
        assertLayout(AndroidVersionWidgetLayout.LARGE, widthDp = 260f, heightDp = 220f)
    }

    @Test
    fun selectAndroidVersionWidgetLayout_switchesAtExactWidthBoundaries() {
        assertLayout(AndroidVersionWidgetLayout.COMPACT, widthDp = 99.99f, heightDp = 109.99f)
        assertLayout(AndroidVersionWidgetLayout.STANDARD_SHORT, widthDp = 100f, heightDp = 109.99f)
        assertLayout(AndroidVersionWidgetLayout.STANDARD_SHORT, widthDp = 219.99f, heightDp = 109.99f)
        assertLayout(AndroidVersionWidgetLayout.WIDE_SHORT, widthDp = 220f, heightDp = 109.99f)
    }

    @Test
    fun selectAndroidVersionWidgetLayout_switchesAtExactHeightBoundary() {
        assertLayout(AndroidVersionWidgetLayout.NARROW_TALL, widthDp = 99.99f, heightDp = 110f)
        assertLayout(AndroidVersionWidgetLayout.STANDARD_TALL, widthDp = 100f, heightDp = 110f)
        assertLayout(AndroidVersionWidgetLayout.STANDARD_TALL, widthDp = 180f, heightDp = 110f)
        assertLayout(AndroidVersionWidgetLayout.LARGE, widthDp = 220f, heightDp = 110f)
    }

    @Test
    fun selectAndroidVersionWidgetLayout_switchesAtExactNarrowExtraTallBoundary() {
        assertLayout(AndroidVersionWidgetLayout.NARROW_TALL, widthDp = 99.99f, heightDp = 279.99f)
        assertLayout(AndroidVersionWidgetLayout.NARROW_EXTRA_TALL, widthDp = 99.99f, heightDp = 280f)
    }

    @Test
    fun selectAndroidVersionWidgetLayout_switchesAtExactLargeWidthBoundaryWhenTall() {
        assertLayout(AndroidVersionWidgetLayout.STANDARD_TALL, widthDp = 180f, heightDp = 220f)
        assertLayout(AndroidVersionWidgetLayout.STANDARD_TALL, widthDp = 219.99f, heightDp = 220f)
        assertLayout(AndroidVersionWidgetLayout.LARGE, widthDp = 220f, heightDp = 220f)
    }

    @Test
    fun selectAndroidVersionWidgetLayout_usesAvailableDpInsteadOfCellCount() {
        assertLayout(AndroidVersionWidgetLayout.COMPACT, widthDp = 57f, heightDp = 102f)
        assertLayout(AndroidVersionWidgetLayout.STANDARD_SHORT, widthDp = 127f, heightDp = 51f)
    }

    private fun assertLayout(
        expected: AndroidVersionWidgetLayout,
        widthDp: Float,
        heightDp: Float,
    ) {
        assertEquals(expected, selectAndroidVersionWidgetLayout(widthDp, heightDp))
    }
}
