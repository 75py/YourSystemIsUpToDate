package com.nagopy.android.yoursystemisuptodate

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SystemUpdateActivitySelectionTest {
    @Test
    fun selectSystemUpdateActivity_ignoresNonPositivePriorities() {
        val candidates = listOf(
            candidate("negative", -1),
            candidate("default", 0),
        )

        assertNull(selectSystemUpdateActivity(candidates))
    }

    @Test
    fun selectSystemUpdateActivity_returnsHighestPositivePriority() {
        val candidates = listOf(
            candidate("lower", 1),
            candidate("highest", 10),
            candidate("middle", 5),
        )

        assertEquals("highest", selectSystemUpdateActivity(candidates)?.className)
    }

    private fun candidate(className: String, priority: Int) = SystemUpdateActivityCandidate(
        packageName = "com.android.settings",
        className = className,
        priority = priority,
    )
}
