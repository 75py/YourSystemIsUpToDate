package com.nagopy.android.yoursystemisuptodate

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

class StartActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val systemUpdateActivity = Intent(ACTION_SYSTEM_UPDATE_SETTINGS)
        startActivityIfLaunchable(systemUpdateActivity)
        finish()
    }

    private fun startActivityIfLaunchable(intent: Intent?) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Failed to open the system update screen.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        private const val ACTION_SYSTEM_UPDATE_SETTINGS = "android.settings.SYSTEM_UPDATE_SETTINGS"
    }
}
