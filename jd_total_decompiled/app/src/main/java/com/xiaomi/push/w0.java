package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes11.dex */
public class w0 {
    private static volatile w0 b;
    private Context a;

    private w0(Context context) {
        this.a = context;
    }

    public static w0 b(Context context) {
        if (b == null) {
            synchronized (w0.class) {
                if (b == null) {
                    b = new w0(context);
                }
            }
        }
        return b;
    }

    public synchronized long a(String str, String str2, long j2) {
        try {
        } catch (Throwable unused) {
            return j2;
        }
        return this.a.getSharedPreferences(str, 4).getLong(str2, j2);
    }

    public synchronized String c(String str, String str2, String str3) {
        try {
        } catch (Throwable unused) {
            return str3;
        }
        return this.a.getSharedPreferences(str, 4).getString(str2, str3);
    }

    public synchronized void d(String str, String str2, long j2) {
        SharedPreferences.Editor edit = this.a.getSharedPreferences(str, 4).edit();
        edit.putLong(str2, j2);
        edit.commit();
    }

    public synchronized void e(String str, String str2, String str3) {
        SharedPreferences.Editor edit = this.a.getSharedPreferences(str, 4).edit();
        edit.putString(str2, str3);
        edit.commit();
    }
}
