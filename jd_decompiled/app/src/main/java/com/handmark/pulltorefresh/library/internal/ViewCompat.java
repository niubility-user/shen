package com.handmark.pulltorefresh.library.internal;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/* loaded from: classes12.dex */
public class ViewCompat {

    @TargetApi(11)
    /* loaded from: classes12.dex */
    static class SDK11 {
        SDK11() {
        }

        public static void setLayerType(View view, int i2) {
            view.setLayerType(i2, null);
        }
    }

    @TargetApi(16)
    /* loaded from: classes12.dex */
    static class SDK16 {
        SDK16() {
        }

        public static void postOnAnimation(View view, Runnable runnable) {
            view.postOnAnimation(runnable);
        }

        public static void setBackground(View view, Drawable drawable) {
            view.setBackground(drawable);
        }
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            SDK16.postOnAnimation(view, runnable);
        } else {
            view.postDelayed(runnable, 16L);
        }
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            SDK16.setBackground(view, drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setLayerType(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 11) {
            SDK11.setLayerType(view, i2);
        }
    }
}
