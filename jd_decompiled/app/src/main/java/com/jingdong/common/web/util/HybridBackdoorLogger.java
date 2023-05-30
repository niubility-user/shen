package com.jingdong.common.web.util;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.libs.hybrid.base.util.Log;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class HybridBackdoorLogger implements Log.Logger {
    private static String D = "debug";
    private static String E = "error";
    private static String W = "warn";
    private static volatile StringBuffer sHybridLog;
    private static BackdoorLogChangeListener sLogChangeListener;

    /* loaded from: classes12.dex */
    public interface BackdoorLogChangeListener {
        void onNewLog(String str);
    }

    public static StringBuffer getHybridLog() {
        return sHybridLog;
    }

    private synchronized void log(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (sHybridLog == null) {
            sHybridLog = new StringBuffer();
        }
        while (sHybridLog.length() > 99999) {
            sHybridLog.delete(0, sHybridLog.indexOf("<br>", 1));
        }
        if (E.equals(str3)) {
            sHybridLog.append("<font color=\"#dd2222\">");
        } else if (W.equals(str3)) {
            sHybridLog.append("<font color=\"#dd9822\">");
        }
        if (!TextUtils.isEmpty(str)) {
            StringBuffer stringBuffer = sHybridLog;
            stringBuffer.append("[");
            stringBuffer.append(str);
            stringBuffer.append("]:");
        }
        sHybridLog.append(str2);
        if (E.equals(str3) || W.equals(str3)) {
            sHybridLog.append("</font>");
        }
        sHybridLog.append("<br>------------------------------<br>");
        BackdoorLogChangeListener backdoorLogChangeListener = sLogChangeListener;
        if (backdoorLogChangeListener != null) {
            backdoorLogChangeListener.onNewLog(sHybridLog.toString());
        }
    }

    public static synchronized void newLogSection() {
        synchronized (HybridBackdoorLogger.class) {
            if (sHybridLog != null) {
                sHybridLog.append("<font color=\"#dd2222\"><br><br><br><br><br><br><br><br><br>New Log:\n");
                sHybridLog.append("<br>------------------------------<br></font>");
            }
        }
    }

    public static void setHybridBackdoorLogger(Log.Logger logger) {
        Log.setLogger(logger);
    }

    public static void setLogChangeListener(BackdoorLogChangeListener backdoorLogChangeListener) {
        sLogChangeListener = backdoorLogChangeListener;
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void d(String str) {
        log(null, str, D);
        if (OKLog.D) {
            OKLog.d("JDHybrid", str);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void e(String str) {
        log(null, str, E);
        if (OKLog.E) {
            OKLog.e("JDHybrid", str);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void v(String str) {
        log(null, str, D);
        if (OKLog.V) {
            OKLog.v("JDHybrid", str);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void w(String str) {
        log(null, str, W);
        if (OKLog.W) {
            OKLog.w("JDHybrid", str);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void d(String str, String str2) {
        log(str, str2, D);
        if (OKLog.D) {
            OKLog.d("JDHybrid-" + str, str2);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void e(String str, String str2) {
        log(str, str2, E);
        if (OKLog.E) {
            OKLog.e("JDHybrid-" + str, str2);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void v(String str, String str2) {
        log(str, str2, D);
        if (OKLog.V) {
            OKLog.v("JDHybrid-" + str, str2);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void w(String str, String str2) {
        log(str, str2, W);
        if (OKLog.W) {
            OKLog.w("JDHybrid-" + str, str2);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void d(String str, Throwable th) {
        log(str, th.getMessage(), D);
        if (OKLog.D) {
            OKLog.d("JDHybrid-" + str, th);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void e(String str, Throwable th) {
        log(str, th.getMessage(), E);
        if (OKLog.E) {
            OKLog.e("JDHybrid-" + str, th);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void v(String str, Throwable th) {
        log(str, th.getMessage(), D);
        if (OKLog.V) {
            OKLog.v("JDHybrid-" + str, th);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void w(String str, Throwable th) {
        log(str, th.getMessage(), W);
        if (OKLog.W) {
            OKLog.w("JDHybrid-" + str, th);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void d(String str, String str2, Throwable th) {
        log(str, str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + th.getMessage(), D);
        if (OKLog.D) {
            OKLog.d("JDHybrid-" + str, str2, th);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void e(String str, String str2, Throwable th) {
        log(str, str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + th.getMessage(), E);
        if (OKLog.E) {
            OKLog.e("JDHybrid-" + str, str2, th);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void v(String str, String str2, Throwable th) {
        log(str, str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + th.getMessage(), D);
        if (OKLog.V) {
            OKLog.v("JDHybrid-" + str, str2, th);
        }
    }

    @Override // com.jd.libs.hybrid.base.util.Log.Logger
    public void w(String str, String str2, Throwable th) {
        log(str, str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + th.getMessage(), W);
        if (OKLog.W) {
            OKLog.w("JDHybrid-" + str, str2, th);
        }
    }
}
