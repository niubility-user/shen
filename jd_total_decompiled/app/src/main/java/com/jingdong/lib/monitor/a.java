package com.jingdong.lib.monitor;

import android.content.SharedPreferences;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes14.dex */
public class a {
    private static SharedPreferences a;

    public static SharedPreferences.Editor a() {
        return b().edit();
    }

    public static synchronized SharedPreferences b() {
        SharedPreferences sharedPreferences;
        synchronized (a.class) {
            if (a == null) {
                a = JdSdk.getInstance().getApplication().getSharedPreferences("crash_sp", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }
}
