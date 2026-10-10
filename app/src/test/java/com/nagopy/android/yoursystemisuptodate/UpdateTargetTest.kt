package com.nagopy.android.yoursystemisuptodate

import org.junit.Assert.assertEquals
import org.junit.Test

class UpdateTargetTest {
    @Test fun existingUsersKeepTheSystemUpdateLauncher() {
        assertEquals(UpdateTarget.SYSTEM, resolveUpdateTarget("android.intent.action.MAIN", false))
        assertEquals(UpdateTarget.SYSTEM, resolveUpdateTarget(null, false))
    }

    @Test fun secondLauncherOpensGooglePlay() {
        assertEquals(UpdateTarget.GOOGLE_PLAY, resolveUpdateTarget("android.intent.action.MAIN", true))
    }

    @Test fun existingOsWidgetsAlwaysOpenSystemUpdate() {
        assertEquals(UpdateTarget.SYSTEM, resolveUpdateTarget(ACTION_ANDROID_WIDGET_TAP, true))
    }

    @Test fun playWidgetsAlwaysOpenGooglePlay() {
        for (isPlayLauncher in listOf(false, true)) {
            assertEquals(UpdateTarget.GOOGLE_PLAY, resolveUpdateTarget(ACTION_OPEN_PLAY_UPDATE, isPlayLauncher))
        }
    }

    @Test fun unexpectedActionsAreNotMappedToADestination() {
        for (isPlayLauncher in listOf(false, true)) {
            assertEquals(UpdateTarget.UNKNOWN, resolveUpdateTarget("android.intent.action.VIEW", isPlayLauncher))
        }
    }
}
