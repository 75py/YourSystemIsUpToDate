package com.nagopy.android.yoursystemisuptodate;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class StartActivity extends Activity {

    private static final String ACTION_SYSTEM_UPDATE_SETTINGS = "android.settings.SYSTEM_UPDATE_SETTINGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent systemUpdateActivity = new Intent(ACTION_SYSTEM_UPDATE_SETTINGS);
        startActivityIfLaunchable(systemUpdateActivity);

        finish();
    }

    void startActivityIfLaunchable(Intent intent) {
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Failed to open the system update screen.", Toast.LENGTH_SHORT).show();
        }
    }
}
