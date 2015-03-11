package com.nagopy.android.yoursystemisuptodate.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


/**
 * デバッグ用クラス.<br>
 * このクラスのメソッドは全て、リリースビルド時にProguardで削除する。
 */
public class DebugUtil {

    private static final String TAG = "YourSystemIsUpToDate";

    private DebugUtil() {
    }


    /**
     * ログ出力.<br>
     * リリースビルドではProguardで削除する。
     *
     * @param msg メッセージ
     */
    public static void verboseLog(String msg) {
        Log.v(TAG, msg);
    }

    /**
     * デバッグログ出力.<br>
     * リリースビルドではProguardで削除する。
     *
     * @param msg メッセージ
     */
    public static void debugLog(String msg) {
        Log.d(TAG, msg);
    }

    /**
     * デバッグログ出力.<br>
     * リリースビルドではProguardで削除する。
     *
     * @param obj 任意のオブジェクト
     */
    public static void debugLog(Object obj) {
        debugLog(obj == null ? "nullpo" : obj.toString());
    }

    /**
     * 情報ログ出力.<br>
     * リリースビルドではProguardで削除する。
     *
     * @param msg メッセージ
     */
    public static void infoLog(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * エラーログ出力.<br>
     * リリースビルドではProguardで削除する。
     *
     * @param msg メッセージ
     */
    public static void errorLog(String msg) {
        Log.e(TAG, msg);
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
