package com.nagopy.android.yoursystemisuptodate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.nagopy.android.yoursystemisuptodate.util.ImageUtil;


public class CreateShortcutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(RESULT_OK, makeShortcutIntent(getApplicationContext()));
        finish();

//        if (TextUtils.equals(getIntent().getAction(), Intent.ACTION_MAIN)) {
//            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
//            sendBroadcast(intent);
//        } else if (TextUtils.equals(getIntent().getAction(), Intent.ACTION_CREATE_SHORTCUT)) {
//            setResult(RESULT_OK, intent);
//        }
    }

    public static Intent makeShortcutIntent(Context context) {
        Intent shortcutIntent = new Intent(context, StartActivity.class);

        String name = context.getString(R.string.label_system_update);
        Drawable iconDrawable = ImageUtil.getDrawable(context, R.mipmap.ic_launcher);
        Intent intent = new Intent()
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent)
                .putExtra(Intent.EXTRA_SHORTCUT_NAME, name)
                .putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, R.mipmap.ic_launcher)
                .putExtra(Intent.EXTRA_SHORTCUT_ICON, ImageUtil.toBitmap(iconDrawable));
        return intent;
    }
}
