package com.jingdong.manto.utils;

import android.util.Log;
import com.jingdong.manto.sdk.api.IMantoLog;

/* loaded from: classes16.dex */
public class MantoLog {
    private static IMantoLog a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);

    public static void d(String str, String str2) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.d(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.d(str, str2, th);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.d(str, String.format(str2, objArr));
        }
    }

    public static void d(String str, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.d(str, th);
        }
    }

    public static void d(String str, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.d(str, objArr);
        }
    }

    public static void e(String str, String str2) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.e(str, str2, th);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.e(str, String.format(str2, objArr));
        }
    }

    public static void e(String str, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.e(str, th);
        }
    }

    public static void e(String str, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.e(str, objArr);
        }
    }

    public static String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static void i(String str, String str2) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.i(str, str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.i(str, str2, th);
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.i(str, String.format(str2, objArr));
        }
    }

    public static void i(String str, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.i(str, th);
        }
    }

    public static void i(String str, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.i(str, objArr);
        }
    }

    public static void json(String str, String str2) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.json(str, str2);
        }
    }

    public static void v(String str, String str2) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.v(str, str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.v(str, str2, th);
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.v(str, String.format(str2, objArr));
        }
    }

    public static void v(String str, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.v(str, th);
        }
    }

    public static void v(String str, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.v(str, objArr);
        }
    }

    public static void w(String str, String str2) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.w(str, str2, th);
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.w(str, String.format(str2, objArr));
        }
    }

    public static void w(String str, Throwable th) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.w(str, th);
        }
    }

    public static void w(String str, Object... objArr) {
        if (a == null) {
            a = (IMantoLog) com.jingdong.a.n(IMantoLog.class);
        }
        IMantoLog iMantoLog = a;
        if (iMantoLog != null) {
            iMantoLog.w(str, objArr);
        }
    }
}
