package com.nagopy.android.yoursystemisuptodate

import org.junit.Assert.assertEquals
import org.junit.Test

class UpdateTargetTest {
    @Test fun launcherIconOpensSystemUpdate() {
        assertEquals(UpdateTarget.SYSTEM, resolveUpdateTarget("android.intent.action.MAIN"))
        assertEquals(UpdateTarget.SYSTEM, resolveUpdateTarget(null))
    }

    @Test fun existingOsWidgetsOpenSystemUpdate() {
        assertEquals(UpdateTarget.SYSTEM, resolveUpdateTarget(ACTION_ANDROID_WIDGET_TAP))
    }

    @Test fun playShortcutAndWidgetsOpenGooglePlay() {
        assertEquals(UpdateTarget.GOOGLE_PLAY, resolveUpdateTarget(ACTION_OPEN_PLAY_UPDATE))
    }

    @Test fun unexpectedActionsAreNotMappedToADestination() {
        assertEquals(UpdateTarget.UNKNOWN, resolveUpdateTarget("android.intent.action.VIEW"))
    }
}
