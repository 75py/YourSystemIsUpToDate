package com.nagopy.android.yoursystemisuptodate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent systemUpdateActivity = new Intent("android.settings.SYSTEM_UPDATE_SETTINGS");
        startActivity(systemUpdateActivity);

        finish();
    }
}

