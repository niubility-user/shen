package com.jingdong.common.jdreactFramework.utils;

import android.util.Log;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.helper.Logger;

/* loaded from: classes5.dex */
public class JLog {
    private static Logger sLogger;

    public static void d(String str, String str2) {
        Logger logger;
        if (!JDReactHelper.newInstance().isDebug() || (logger = sLogger) == null) {
            return;
        }
        logger.d(str, str2);
    }

    public static void e(String str, String str2) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.e(str, str2);
        }
    }

    public static void i(String str, String str2) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.i(str, str2);
        }
    }

    public static void setLogger(Logger logger) {
        sLogger = logger;
    }

    public static void w(String str, String str2) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.w(str, str2);
        }
    }

    public static void e(String str, Throwable th) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.e(str, th);
        } else {
            Log.getStackTraceString(th);
        }
    }

    public static void i(String str, Throwable th) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.i(str, th);
        } else {
            Log.getStackTraceString(th);
        }
    }

    public static void w(String str, Throwable th) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.w(str, th);
        } else {
            Log.getStackTraceString(th);
        }
    }

    public static void d(String str, Throwable th) {
        if (JDReactHelper.newInstance().isDebug()) {
            Logger logger = sLogger;
            if (logger != null) {
                logger.d(str, th);
            } else {
                Log.getStackTraceString(th);
            }
        }
    }
}
