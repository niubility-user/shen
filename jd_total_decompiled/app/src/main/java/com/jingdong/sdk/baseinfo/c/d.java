package com.jingdong.sdk.baseinfo.c;

import android.content.SharedPreferences;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes.dex */
public final class d {
    private static SharedPreferences a;

    public static synchronized SharedPreferences a() {
        SharedPreferences sharedPreferences;
        synchronized (d.class) {
            if (a == null) {
                a = BaseInfo.getContext().getSharedPreferences("mPaaS_BaseInfo", 0);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }
}
