package com.jingdong.sdk.log;

import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes8.dex */
public final class Log {
    @Deprecated
    public static boolean D;
    @Deprecated
    public static boolean E;
    @Deprecated
    public static boolean I;
    @Deprecated
    public static boolean V;
    @Deprecated
    public static boolean W;

    @Deprecated
    /* loaded from: classes8.dex */
    public interface LogTail {
        void callOutput(String str, String str2, int i2, Throwable th, boolean z);

        void callOutput(String str, String str2, int i2, boolean z);
    }

    public static void d(String str, String str2) {
        if (OKLog.D) {
            OKLog.d(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (OKLog.D) {
            OKLog.d(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        if (OKLog.E) {
            OKLog.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (OKLog.E) {
            OKLog.e(str, str2, th);
        }
    }

    @Deprecated
    public static void enableLog(boolean z) {
    }

    @Deprecated
    public static void enableLogForLogSys(boolean z) {
    }

    public static void i(String str, String str2) {
        if (OKLog.I) {
            OKLog.i(str, str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (OKLog.I) {
            OKLog.i(str, str2, th);
        }
    }

    @Deprecated
    public static boolean isEnabled() {
        return false;
    }

    public static void json(String str, Object obj) {
        OKLog.json(str, obj != null ? obj.toString() : null);
    }

    @Deprecated
    public static void s(int i2) {
        if (OKLog.D) {
            OKLog.d(OKLog.class.getSimpleName(), "" + i2);
        }
    }

    @Deprecated
    public static void s(String str) {
        if (OKLog.D) {
            OKLog.d(OKLog.class.getSimpleName(), str);
        }
    }

    @Deprecated
    public static void s(String str, String str2) {
        if (OKLog.D) {
            OKLog.d(str, str2);
        }
    }

    @Deprecated
    public static void s(String str, String str2, Throwable th) {
        if (OKLog.D) {
            OKLog.d(str, str2, th);
        }
    }

    @Deprecated
    public static void setLogTail(LogTail logTail) {
    }

    public static void v(String str, String str2) {
        if (OKLog.V) {
            OKLog.v(str, str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (OKLog.V) {
            OKLog.v(str, str2, th);
        }
    }

    public static void w(String str, String str2) {
        if (OKLog.W) {
            OKLog.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (OKLog.W) {
            OKLog.w(str, str2, th);
        }
    }

    public static void w(String str, Throwable th) {
        if (OKLog.W) {
            OKLog.w(str, th);
        }
    }
}
