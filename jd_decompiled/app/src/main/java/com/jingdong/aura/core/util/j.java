package com.jingdong.aura.core.util;

import android.os.Build;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class j {
    private static volatile Boolean b;
    private static final byte[] a = new byte[0];

    /* renamed from: c  reason: collision with root package name */
    private static final com.jingdong.aura.core.util.l.b f12170c = com.jingdong.aura.core.util.l.c.a("VMRuntimeCompat");

    public static boolean a() {
        if (b != null) {
            return b.booleanValue();
        }
        synchronized (a) {
            if (b != null) {
                return b.booleanValue();
            }
            b = Boolean.valueOf(b());
            return b.booleanValue();
        }
    }

    private static boolean b() {
        Class<?> cls;
        Method declaredMethod;
        Object invoke;
        Method declaredMethod2;
        try {
        } catch (Throwable th) {
            f12170c.d("is64BitInternal:" + th.getMessage());
        }
        if (Build.VERSION.SDK_INT < 21 || (cls = Class.forName("dalvik.system.VMRuntime")) == null || (declaredMethod = cls.getDeclaredMethod("getRuntime", new Class[0])) == null || (invoke = declaredMethod.invoke(null, new Object[0])) == null || (declaredMethod2 = cls.getDeclaredMethod("is64Bit", new Class[0])) == null) {
            return false;
        }
        Object invoke2 = declaredMethod2.invoke(invoke, new Object[0]);
        f12170c.a("is64BitInternal:" + invoke2);
        if (invoke2 instanceof Boolean) {
            return ((Boolean) invoke2).booleanValue();
        }
        return false;
    }
}
