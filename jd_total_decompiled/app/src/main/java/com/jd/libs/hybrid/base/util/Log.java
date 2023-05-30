package com.jd.libs.hybrid.base.util;

import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.xconsole.XLog;
import com.jd.libs.xdog.b;

/* loaded from: classes16.dex */
public class Log {
    private static Logger a;
    public static boolean showXLog;

    /* loaded from: classes16.dex */
    public interface Logger {
        void d(String str);

        void d(String str, String str2);

        void d(String str, String str2, Throwable th);

        void d(String str, Throwable th);

        void e(String str);

        void e(String str, String str2);

        void e(String str, String str2, Throwable th);

        void e(String str, Throwable th);

        void v(String str);

        void v(String str, String str2);

        void v(String str, String str2, Throwable th);

        void v(String str, Throwable th);

        void w(String str);

        void w(String str, String str2);

        void w(String str, String str2, Throwable th);

        void w(String str, Throwable th);
    }

    private Log() {
    }

    public static void d(String str) {
        Logger logger = a;
        if (logger != null) {
            logger.d(str);
        } else {
            HybridSettings.isDebug();
        }
    }

    public static void e(String str) {
        Logger logger = a;
        if (logger != null) {
            logger.e(str);
        } else {
            HybridSettings.isDebug();
        }
    }

    public static boolean isDebug() {
        return HybridSettings.isDebug() || showXLog;
    }

    public static void setLogger(Logger logger) {
        a = logger;
    }

    public static void v(String str) {
        Logger logger = a;
        if (logger != null) {
            logger.v(str);
        } else {
            HybridSettings.isDebug();
        }
    }

    public static void w(String str) {
        Logger logger = a;
        if (logger != null) {
            logger.w(str);
        } else {
            HybridSettings.isDebug();
        }
    }

    public static void xLogD(String str, String str2) {
        XLog.d(str, null, "[JDHybrid\u65e5\u5fd7]" + str2, "jdhybrid");
        b.g("[JDHybrid\u65e5\u5fd7]" + str2);
    }

    public static void xLogDForDev(String str, String str2) {
        XLog.d(str, null, "[JDHybrid\u65e5\u5fd7]" + str2, "jdhybrid-dev");
        if (str2.contains("\u672a\u627e\u5230\u53ef\u66ff\u6362\u7ebf\u4e0a\u8d44\u6e90\u7684\u672c\u5730\u79bb\u7ebf\u6587\u4ef6")) {
            return;
        }
        b.g("[JDHybrid\u65e5\u5fd7]" + str2);
    }

    public static void xLogE(String str, String str2) {
        XLog.e(str, null, "[JDHybrid\u65e5\u5fd7]" + str2, "jdhybrid");
        b.h("[JDHybrid\u65e5\u5fd7]" + str2);
    }

    public static void xLogEForDev(String str, String str2) {
        XLog.e(str, null, "[JDHybrid\u65e5\u5fd7]" + str2, "jdhybrid-dev");
        b.g("[JDHybrid\u65e5\u5fd7]" + str2);
    }

    public static void xLogD(String str, String str2, Object obj) {
        XLog.d(str, "[JDHybrid\u65e5\u5fd7]" + str2, obj, "jdhybrid");
        b.g("[JDHybrid\u65e5\u5fd7]" + str2 + obj);
    }

    public static void xLogE(String str, String str2, Object obj) {
        XLog.e(str, "[JDHybrid\u65e5\u5fd7]" + str2, obj, "jdhybrid");
        b.h("[JDHybrid\u65e5\u5fd7]" + str2 + obj);
    }

    public static void xLogEForDev(String str, String str2, Object obj) {
        XLog.e(str, "[JDHybrid\u65e5\u5fd7]" + str2, obj, "jdhybrid-dev");
        b.g("[JDHybrid\u65e5\u5fd7]" + obj);
    }

    public static void d(String str, String str2) {
        Logger logger = a;
        if (logger != null) {
            logger.d(str, str2);
        } else if (HybridSettings.isDebug()) {
            String str3 = "JDHybrid-" + str;
        }
    }

    public static void e(String str, String str2) {
        Logger logger = a;
        if (logger != null) {
            logger.e(str, str2);
        } else if (HybridSettings.isDebug()) {
            String str3 = "JDHybrid-" + str;
        }
    }

    public static void v(String str, String str2) {
        Logger logger = a;
        if (logger != null) {
            logger.v(str, str2);
        } else if (HybridSettings.isDebug()) {
            String str3 = "JDHybrid-" + str;
        }
    }

    public static void w(String str, String str2) {
        Logger logger = a;
        if (logger != null) {
            logger.w(str, str2);
        } else if (HybridSettings.isDebug()) {
            String str3 = "JDHybrid-" + str;
        }
    }

    public static void xLogDForDev(String str, String str2, Object obj) {
        XLog.d(str, "[JDHybrid\u65e5\u5fd7]" + str2, obj, "jdhybrid-dev");
        b.g("[JDHybrid\u65e5\u5fd7]" + str2 + obj);
    }

    public static void d(String str, Throwable th) {
        Logger logger = a;
        if (logger != null) {
            logger.d(str, th);
        } else if (HybridSettings.isDebug()) {
            String str2 = "JDHybrid-" + str;
            if (th == null || th.getMessage() == null) {
                return;
            }
            th.getMessage();
        }
    }

    public static void e(String str, Throwable th) {
        Logger logger = a;
        if (logger != null) {
            logger.e(str, th);
        } else if (HybridSettings.isDebug()) {
            String str2 = "JDHybrid-" + str;
            if (th == null || th.getMessage() == null) {
                return;
            }
            th.getMessage();
        }
    }

    public static void v(String str, Throwable th) {
        Logger logger = a;
        if (logger != null) {
            logger.v(str, th);
        } else if (HybridSettings.isDebug()) {
            String str2 = "JDHybrid-" + str;
            if (th == null || th.getMessage() == null) {
                return;
            }
            th.getMessage();
        }
    }

    public static void w(String str, Throwable th) {
        Logger logger = a;
        if (logger != null) {
            logger.w(str, th);
        } else if (HybridSettings.isDebug()) {
            String str2 = "JDHybrid-" + str;
        }
    }

    public static void d(String str, String str2, Throwable th) {
        Logger logger = a;
        if (logger != null) {
            logger.d(str, str2, th);
        } else if (HybridSettings.isDebug()) {
            String str3 = "JDHybrid-" + str;
        }
    }

    public static void e(String str, String str2, Throwable th) {
        Logger logger = a;
        if (logger != null) {
            logger.e(str, str2, th);
        } else if (HybridSettings.isDebug()) {
            String str3 = "JDHybrid-" + str;
        }
    }

    public static void v(String str, String str2, Throwable th) {
        Logger logger = a;
        if (logger != null) {
            logger.v(str, str2, th);
        } else if (HybridSettings.isDebug()) {
            String str3 = "JDHybrid-" + str;
        }
    }

    public static void w(String str, String str2, Throwable th) {
        Logger logger = a;
        if (logger != null) {
            logger.w(str, str2, th);
        } else if (HybridSettings.isDebug()) {
            String str3 = "JDHybrid-" + str;
        }
    }
}
