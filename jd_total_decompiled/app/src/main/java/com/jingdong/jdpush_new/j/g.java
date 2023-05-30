package com.jingdong.jdpush_new.j;

import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class g {
    public static void a(String str) {
        if (OKLog.D) {
            OKLog.d("JDPush", str);
        }
    }

    public static void b(String str, String str2) {
        if (OKLog.D) {
            OKLog.d(str + "-JDPush", str2);
        }
    }

    public static void c(String str) {
        if (OKLog.E) {
            OKLog.e("JDPush", str);
        }
    }

    public static void d(String str, String str2) {
        if (OKLog.E) {
            OKLog.e(str + "-JDPush", str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (OKLog.E) {
            OKLog.e(str + "-JDPush", str2, th);
        }
    }

    public static void f(String str, Throwable th) {
        if (OKLog.E) {
            OKLog.e("JDPush", str, th);
        }
    }

    public static void g(Throwable th) {
        if (OKLog.E) {
            OKLog.e("JDPush", th);
        }
    }

    public static void h(String str) {
        if (OKLog.I) {
            OKLog.i("JDPush", str);
        }
    }

    public static void i(String str, String str2) {
        if (OKLog.I) {
            OKLog.i(str + "-JDPush", str2);
        }
    }

    public static void j(String str) {
        if (OKLog.V) {
            OKLog.v("JDPush", str);
        }
    }

    public static void k(String str, String str2) {
        if (OKLog.V) {
            OKLog.v(str + "-JDPush", str2);
        }
    }
}
