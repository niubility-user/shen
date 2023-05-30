package com.jingdong.app.mall.bundle.jdweather.e;

import android.os.Build;
import android.os.Looper;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/* loaded from: classes.dex */
public class a {
    public static boolean a(FragmentActivity fragmentActivity) {
        if (fragmentActivity == null || fragmentActivity.isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return !fragmentActivity.isDestroyed();
        }
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        return (supportFragmentManager == null || supportFragmentManager.isDestroyed()) ? false : true;
    }

    public static boolean b() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
