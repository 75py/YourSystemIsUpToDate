package com.nagopy.android.yoursystemisuptodate;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

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
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> result = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (result.isEmpty()) {
            Toast.makeText(this, "Failed to open the system update screen.", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(intent);
        }
    }
}

