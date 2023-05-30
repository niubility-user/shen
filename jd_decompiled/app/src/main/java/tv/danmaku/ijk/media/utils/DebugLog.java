package tv.danmaku.ijk.media.utils;

import java.util.Locale;

/* loaded from: classes11.dex */
public class DebugLog {
    private static boolean DEBUG;

    public static void d(String str, String str2) {
        if (DEBUG) {
            String str3 = str + threadName();
        }
    }

    public static void dfmt(String str, String str2, Object... objArr) {
        if (DEBUG) {
            String.format(Locale.US, str2, objArr);
        }
    }

    public static void e(String str, String str2) {
        if (DEBUG) {
            String str3 = str + threadName();
        }
    }

    public static void efmt(String str, String str2, Object... objArr) {
        if (DEBUG) {
            String.format(Locale.US, str2, objArr);
        }
    }

    public static void i(String str, String str2) {
        boolean z = DEBUG;
    }

    public static void ifmt(String str, String str2, Object... objArr) {
        if (DEBUG) {
            String.format(Locale.US, str2, objArr);
        }
    }

    public static void printCause(Throwable th) {
        if (DEBUG) {
            Throwable cause = th.getCause();
            if (cause != null) {
                th = cause;
            }
            printStackTrace(th);
        }
    }

    public static void printStackTrace(Throwable th) {
        if (DEBUG) {
            th.printStackTrace();
        }
    }

    public static void setDebug(boolean z) {
        DEBUG = z;
    }

    public static String threadName() {
        return "[" + Thread.currentThread().getName() + "]";
    }

    public static void v(String str, String str2) {
        boolean z = DEBUG;
    }

    public static void vfmt(String str, String str2, Object... objArr) {
        if (DEBUG) {
            String.format(Locale.US, str2, objArr);
        }
    }

    public static void w(String str, String str2) {
        if (DEBUG) {
            String str3 = str + threadName();
        }
    }

    public static void wfmt(String str, String str2, Object... objArr) {
        if (DEBUG) {
            String.format(Locale.US, str2, objArr);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (DEBUG) {
            String str3 = str + threadName();
        }
    }

    public static void v(String str, String str2, Throwable th) {
        boolean z = DEBUG;
    }

    public static void d(String str, String str2, Throwable th) {
        if (DEBUG) {
            String str3 = str + threadName();
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (DEBUG) {
            String str3 = str + threadName();
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (DEBUG) {
            String str3 = str + threadName();
        }
    }
}
