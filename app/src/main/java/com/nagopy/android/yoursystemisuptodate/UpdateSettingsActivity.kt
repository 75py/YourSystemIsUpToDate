package com.nagopy.android.yoursystemisuptodate

import android.app.Activity
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class UpdateSettingsActivity : Activity() {
    private lateinit var versionView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val padding = (20 * resources.displayMetrics.density).toInt()
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(padding, padding, padding, padding)
        }
        val scroll = ScrollView(this).apply { addView(content) }
        scroll.setOnApplyWindowInsetsListener { view, insets ->
            @Suppress("DEPRECATION")
            view.setPadding(insets.systemWindowInsetLeft, insets.systemWindowInsetTop,
                insets.systemWindowInsetRight, insets.systemWindowInsetBottom)
            insets
        }
        setContentView(scroll)

        content.addView(TextView(this).apply {
            setText(R.string.update_settings_title)
            textSize = 24f
        })
        versionView = TextView(this).apply {
            textSize = 18f
            setPadding(0, padding, 0, padding / 2)
        }
        content.addView(versionView)
        content.addView(TextView(this).apply { setText(R.string.play_update_level_description) })
        content.addView(Button(this).apply {
            setText(R.string.open_play_update)
            setOnClickListener {
                startActivity(Intent(this@UpdateSettingsActivity, StartActivity::class.java)
                    .setAction(ACTION_OPEN_PLAY_UPDATE))
            }
        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(ShortcutManager::class.java)
            if (manager?.isRequestPinShortcutSupported == true &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                content.addView(Button(this).apply {
                    setText(R.string.pin_play_shortcut)
                    setOnClickListener {
                        val shortcut = ShortcutInfo.Builder(this@UpdateSettingsActivity, "play_update_pinned")
                            .setShortLabel(getString(R.string.play_shortcut_short_label))
                            .setLongLabel(getString(R.string.play_update_label))
                            .setIcon(Icon.createWithResource(this@UpdateSettingsActivity, R.drawable.ic_play_update))
                            .setIntent(Intent(this@UpdateSettingsActivity, StartActivity::class.java)
                                .setAction(ACTION_OPEN_PLAY_UPDATE))
                            .build()
                        if (!manager.requestPinShortcut(shortcut, null)) {
                            Toast.makeText(this@UpdateSettingsActivity,
                                R.string.pin_shortcut_unavailable, Toast.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val version = mainlineVersionDisplay(GooglePlaySystemUpdate.version(this))
            ?: getString(R.string.play_update_unknown)
        versionView.text = getString(R.string.play_update_installed_version, version)
        GooglePlayUpdateWidgetProvider.refreshAllWidgets(this)
    }
}
