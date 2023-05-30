package com.jingdong.pdj.libdjbasecore.utils;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes7.dex */
public class DJHandlerUtils {
    public static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void checkParentNull(View view) {
        if (view == null || isSubThread()) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static <T> T convert(Object obj) {
        return obj;
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isSubThread() {
        return Looper.myLooper() != Looper.getMainLooper();
    }

    public static void removeCallback(@NotNull Runnable runnable) {
        sHandler.removeCallbacks(runnable);
    }

    public static void runOnUiThread(@NotNull Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            sHandler.post(runnable);
        }
    }

    public static void runOnUiThread(@NotNull Runnable runnable, long j2) {
        sHandler.postDelayed(runnable, j2);
    }
}
