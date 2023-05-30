package com.jingdong.sdk.platform.floor.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.base.UnProguard;
import com.jingdong.sdk.platform.utils.PlatformTools;

/* loaded from: classes10.dex */
public class FloorTools extends UnProguard {
    private static final String TAG = "FloorTools";

    public static Bitmap convertViewToBitmap(int i2, View view) {
        if (view != null && view.getMeasuredWidth() != 0) {
            try {
                Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(i2);
                canvas.translate(-view.getScrollX(), -view.getScrollY());
                view.draw(canvas);
                Matrix matrix = new Matrix();
                matrix.postScale(0.5f, 0.5f);
                return Bitmap.createBitmap(createBitmap, 0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), matrix, true);
            } catch (Throwable th) {
                if (PlatformTools.D) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String createKey() {
        return SyncEventBus.createKey();
    }

    public static boolean isAPI21LevelHight() {
        return Build.VERSION.SDK_INT >= 21;
    }
}
