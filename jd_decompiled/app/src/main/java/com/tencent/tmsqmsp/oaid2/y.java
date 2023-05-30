package com.tencent.tmsqmsp.oaid2;

import android.content.Context;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class y {
    public static String a(Context context) {
        x a = x.a();
        return a.a(context.getApplicationContext(), a.f18099c);
    }

    public static final boolean a() {
        Context context = null;
        try {
            Method method = Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]);
            method.setAccessible(true);
            context = (Context) method.invoke(null, new Object[0]);
        } catch (Exception e2) {
            String str = "ActivityThread:currentApplication --> " + e2.toString();
        }
        if (context == null) {
            return false;
        }
        return x.a().a(context, false);
    }

    public static String b(Context context) {
        x a = x.a();
        return a.a(context.getApplicationContext(), a.b);
    }
}
