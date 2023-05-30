package com.jingdong.sdk.utils.d;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes10.dex */
public class a {
    private static a b;
    private SharedPreferences a;

    private a(Context context) {
        this.a = context.getSharedPreferences("jd_global_sp", 0);
    }

    public static synchronized a b(Context context) {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a(context);
            }
            aVar = b;
        }
        return aVar;
    }

    public long a(String str, long j2) {
        return this.a.getLong(str, j2);
    }

    public String c(String str, String str2) {
        return this.a.getString(str, str2);
    }

    public void d(String str, long j2) {
        this.a.edit().putLong(str, j2).apply();
    }

    public void e(String str, String str2) {
        this.a.edit().putString(str, str2).apply();
    }
}
