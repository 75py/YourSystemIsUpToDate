package com.nagopy.android.yoursystemisuptodate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.Toast;

public class SettingsActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_system_update:
                Intent startActivity = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(startActivity);
                break;
            case R.id.button_create_shortcut:
                Intent installShortcut = CreateShortcutActivity.makeShortcutIntent(getApplicationContext());
                installShortcut.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
                sendBroadcast(installShortcut);
                Toast.makeText(getApplicationContext(), R.string.msg_shortcut_installed, Toast.LENGTH_LONG).show();

                // 連打できなくする
                v.setClickable(false);
                v.setEnabled(false);
                break;
            default:
                throw new IllegalArgumentException("Unknown id:" + v.getId());
        }
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            addPreferencesFromResource(R.xml.activity_settings);
        }
    }

}
