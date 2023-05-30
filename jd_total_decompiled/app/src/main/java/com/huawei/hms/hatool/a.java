package com.huawei.hms.hatool;

import android.content.Context;
import java.util.LinkedHashMap;

/* loaded from: classes12.dex */
public abstract class a {
    private static z0 a;

    private static synchronized z0 a() {
        z0 z0Var;
        synchronized (a.class) {
            if (a == null) {
                a = q.c().b();
            }
            z0Var = a;
        }
        return z0Var;
    }

    public static void a(int i2, String str, LinkedHashMap<String, String> linkedHashMap) {
        if (a() == null || !q1.b().a()) {
            return;
        }
        if (i2 == 1 || i2 == 0) {
            a.a(i2, str, linkedHashMap);
            return;
        }
        v.d("hmsSdk", "Data type no longer collects range.type: " + i2);
    }

    @Deprecated
    public static void a(Context context, String str, String str2) {
        if (a() != null) {
            a.a(context, str, str2);
        }
    }

    public static void b(int i2, String str, LinkedHashMap<String, String> linkedHashMap) {
        if (a() == null || !q1.b().a()) {
            return;
        }
        if (i2 == 1 || i2 == 0) {
            a.b(i2, str, linkedHashMap);
            return;
        }
        v.d("hmsSdk", "Data type no longer collects range.type: " + i2);
    }

    public static boolean b() {
        return q.c().a();
    }

    public static void c() {
        if (a() == null || !q1.b().a()) {
            return;
        }
        a.a(-1);
    }
}
