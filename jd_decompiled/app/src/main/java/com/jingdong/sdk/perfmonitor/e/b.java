package com.jingdong.sdk.perfmonitor.e;

import android.app.Application;
import android.os.Build;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class b {
    private static String a;
    private static String b;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f15419c;

    private static void a(Closeable closeable, boolean z) {
        if (closeable != null) {
            if (z) {
                try {
                    closeable.close();
                    return;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            try {
                closeable.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public static String b() {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String d = d();
        a = d;
        if (!TextUtils.isEmpty(d)) {
            return a;
        }
        String c2 = c();
        a = c2;
        if (!TextUtils.isEmpty(c2)) {
            return a;
        }
        String f2 = f();
        a = f2;
        return f2;
    }

    public static String c() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String d() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        return null;
    }

    private static String e(Application application) {
        return application.getPackageName();
    }

    @Nullable
    public static synchronized String f() {
        String str;
        synchronized (b.class) {
            if (!f15419c) {
                f15419c = true;
                try {
                    b = i();
                } catch (IOException unused) {
                }
            }
            str = b;
        }
        return str;
    }

    private static int g(byte[] bArr, int i2, int i3, byte b2) {
        for (int i4 = 0; i4 < bArr.length; i4++) {
            if (bArr[i4] == b2) {
                return i4;
            }
        }
        return -1;
    }

    public static boolean h(Application application) {
        String b2 = b();
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        return b2.equals(e(application));
    }

    private static String i() throws IOException {
        int read;
        byte[] bArr = new byte[64];
        FileInputStream fileInputStream = new FileInputStream("/proc/self/cmdline");
        boolean z = false;
        try {
            read = fileInputStream.read(bArr);
        } catch (Throwable th) {
            th = th;
        }
        try {
            int g2 = g(bArr, 0, read, (byte) 0);
            if (g2 > 0) {
                read = g2;
            }
            String str = new String(bArr, 0, read);
            a(fileInputStream, false);
            return str;
        } catch (Throwable th2) {
            th = th2;
            z = true;
            a(fileInputStream, true ^ z);
            throw th;
        }
    }
}
