/*
 * Copyright (C) 2013 75py
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nagopy.android.yoursystemisuptodate.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class ImageUtil {

    /**
     * アイコンサイズを取得する.
     *
     * @param context {@link android.content.Context}
     * @return アイコンサイズ
     */
    public static int getLauncherLargeIconSize(Context context) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            return (int) context.getResources().getDimension(android.R.dimen.app_icon_size);
        } else {
            ActivityManager activityManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            return activityManager.getLauncherLargeIconSize();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static int getLauncherLargeIconDensity(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.getLauncherLargeIconDensity();
    }

    public static Drawable getDrawable(Context context, int resId) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
            return context.getResources().getDrawableForDensity(resId, getLauncherLargeIconDensity(context));
        }else{
            return context.getResources().getDrawable(resId);
        }
    }

    /**
     * Bitmap -> Drawable
     *
     * @param bitmap original bitmap
     * @return drawable
     */
    public static Drawable toDrawable(Resources res, Bitmap bitmap) {
        Drawable drawable = new BitmapDrawable(res, bitmap);
        return drawable;
    }

    /**
     * Bitmap -> Drawable
     *
     * @param bitmap original bitmap
     * @return drawable
     */
    public static Drawable toDrawable(Bitmap bitmap) {
        return toDrawable(null, bitmap);
    }

    /**
     * Drawable -> Bitmap
     *
     * @param drawable original drawable
     * @return bitmap
     */
    public static Bitmap toBitmap(Drawable drawable) {
        return ((BitmapDrawable) drawable).getBitmap();
    }

    /**
     * resize image.
     *
     * @param originalBitmap original bitmap
     * @param newWidth       width
     * @param newHeight      height
     * @return resized bitmap
     */
    public static Bitmap resizeBitmap(Bitmap originalBitmap, int newWidth, int newHeight) {
        int originalBitmapWidth = originalBitmap.getWidth();
        int originalBitmapHeight = originalBitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postScale(((float) newWidth) / originalBitmapWidth, ((float) newHeight) / originalBitmapHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmapWidth, originalBitmapHeight, matrix, false);
        return resizedBitmap;
    }
}
