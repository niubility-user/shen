package com.jingdong.sdk.platform.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.config.PlatformLog;

/* loaded from: classes10.dex */
public class PlatformTools {
    public static boolean D;
    private static PlatformLog log;

    public static void catchException(Throwable th) {
        if (!D || th == null) {
            return;
        }
        th.printStackTrace();
    }

    public static void catchExceptionAndReportToBugly(final Throwable th) {
        if (D) {
            SyncEventBus.postToMainThread(new Runnable() { // from class: com.jingdong.sdk.platform.utils.PlatformTools.2
                @Override // java.lang.Runnable
                public void run() {
                    Throwable th2 = th;
                    if (th2 != null) {
                        PlatformTools.throwException(th2);
                    }
                }
            });
        }
        if (th != null) {
            reportException(th);
        }
    }

    public static void catchExceptionAndThrow(final Throwable th) {
        if (D) {
            SyncEventBus.postToMainThread(new Runnable() { // from class: com.jingdong.sdk.platform.utils.PlatformTools.1
                @Override // java.lang.Runnable
                public void run() {
                    Throwable th2 = th;
                    if (th2 != null) {
                        PlatformTools.throwException(th2);
                    }
                }
            });
        }
    }

    public static void d(String str, String str2) {
        PlatformLog platformLog = log;
        if (platformLog != null) {
            platformLog.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        PlatformLog platformLog = log;
        if (platformLog != null) {
            platformLog.e(str, str2);
        }
    }

    public static int getColorValue(String str, int i2) {
        int i3 = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                i3 = parseColor(str);
            } catch (Exception e2) {
                if (D) {
                    e2.printStackTrace();
                }
            }
        }
        return i3 == 0 ? i2 : i3;
    }

    private static LayoutInflater getLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public static int getTextSizeValue(int i2, int i3) {
        return i2 > 0 ? i2 / 2 : i3;
    }

    public static String getTextValue(String str, int i2) {
        if (str.length() <= i2 || i2 <= 0) {
            return str;
        }
        return str.substring(0, i2 - 1) + "...";
    }

    public static void i(String str, String str2) {
        PlatformLog platformLog = log;
        if (platformLog != null) {
            platformLog.i(str, str2);
        }
    }

    public static View inflate(Context context, int i2, ViewGroup viewGroup, boolean z) {
        try {
            return getLayoutInflater(context).inflate(i2, viewGroup, z);
        } catch (Throwable unused) {
            return getLayoutInflater(context).inflate(i2, viewGroup, z);
        }
    }

    public static int parseColor(String str) {
        try {
            return Color.parseColor(str);
        } catch (Exception e2) {
            if (D) {
                e2.printStackTrace();
            }
            return 0;
        }
    }

    public static void reportException(Throwable th) {
        PlatformLog platformLog = log;
        if (platformLog != null) {
            platformLog.reportException(th);
        }
    }

    public static void setLog(PlatformLog platformLog) {
        log = platformLog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void throwException(Throwable th) {
        if (th == null) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("This Exception only throw on Debug! Caused by:\n " + th.toString());
        illegalStateException.setStackTrace(th.getStackTrace());
        throw illegalStateException;
    }
}
