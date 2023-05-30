package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import com.jingdong.common.utils.LangUtils;
import com.tencent.smtt.sdk.TbsHandlerThread;
import java.io.File;

/* loaded from: classes9.dex */
public class TbsLog {
    public static final int MSG_PV_REPORT = 501;
    public static final String X5LOGTAG = "x5logtag";
    private static boolean a = true;
    private static Handler b;

    /* renamed from: c  reason: collision with root package name */
    private static TbsLogClient f17901c;

    public static void d(String str, String str2) {
        d(str, "", str2);
    }

    public static void d(String str, String str2, String str3) {
        TbsLogClient tbsLogClient = f17901c;
    }

    public static void d(String str, String str2, boolean z) {
        d(str, str2);
        TbsLogClient tbsLogClient = f17901c;
        if (tbsLogClient != null && a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    public static void e(String str, String str2) {
        e(str, "", str2);
    }

    public static void e(String str, String str2, String str3) {
        TbsLogClient tbsLogClient = f17901c;
        if (tbsLogClient == null) {
            return;
        }
        tbsLogClient.e(str, "TBS:" + str2 + LangUtils.SINGLE_SPACE + str3);
        f17901c.writeLog("(E)-" + str + "-TBS:" + str2 + LangUtils.SINGLE_SPACE + str3);
    }

    public static void e(String str, String str2, boolean z) {
        e(str, str2);
        TbsLogClient tbsLogClient = f17901c;
        if (tbsLogClient != null && a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    public static String getTbsLogFilePath() {
        File file = TbsLogClient.f17902c;
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static Handler getTbsLogHandler() {
        return b;
    }

    public static void i(String str, String str2) {
        i(str, "", str2);
    }

    public static void i(String str, String str2, String str3) {
        TbsLogClient tbsLogClient = f17901c;
        if (tbsLogClient == null) {
            return;
        }
        tbsLogClient.i(str, "TBS:" + str2 + LangUtils.SINGLE_SPACE + str3);
        f17901c.writeLog("(I)-" + str + "-TBS:" + str2 + LangUtils.SINGLE_SPACE + str3);
    }

    public static void i(String str, String str2, boolean z) {
        i(str, str2);
        TbsLogClient tbsLogClient = f17901c;
        if (tbsLogClient != null && a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    public static void i(Throwable th) {
        i("handle_throwable", Log.getStackTraceString(th));
    }

    public static synchronized void initIfNeed(Context context) {
        synchronized (TbsLog.class) {
            if (f17901c == null) {
                setTbsLogClient(new TbsLogClient(context));
            }
            if (b == null) {
                b = new Handler(TbsHandlerThread.getInstance().getLooper()) { // from class: com.tencent.smtt.utils.TbsLog.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        int i2 = message.what;
                        if (i2 == 500) {
                            if (TbsLog.f17901c != null) {
                                TbsLog.f17901c.writeLogToDisk();
                            }
                        } else if (i2 == 501) {
                            Object obj = message.obj;
                            if (obj instanceof Runnable) {
                                ((Runnable) obj).run();
                            }
                        }
                    }
                };
            }
        }
    }

    public static void setLogView(TextView textView) {
        TbsLogClient tbsLogClient;
        if (textView == null || (tbsLogClient = f17901c) == null) {
            return;
        }
        tbsLogClient.setLogView(textView);
    }

    public static boolean setTbsLogClient(TbsLogClient tbsLogClient) {
        if (tbsLogClient == null) {
            return false;
        }
        f17901c = tbsLogClient;
        return true;
    }

    @Deprecated
    public static void setWriteLogJIT(boolean z) {
        TbsLogClient tbsLogClient = f17901c;
        if (tbsLogClient == null) {
            return;
        }
        tbsLogClient.setWriteLogJIT(z);
    }

    public static void v(String str, String str2) {
        v(str, "", str2);
    }

    public static void v(String str, String str2, String str3) {
        TbsLogClient tbsLogClient = f17901c;
    }

    public static void v(String str, String str2, boolean z) {
        v(str, str2);
        TbsLogClient tbsLogClient = f17901c;
        if (tbsLogClient != null && a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    public static void w(String str, String str2) {
        w(str, "", str2);
    }

    public static void w(String str, String str2, String str3) {
        TbsLogClient tbsLogClient = f17901c;
        if (tbsLogClient == null) {
            return;
        }
        tbsLogClient.w(str, "TBS:" + str2 + LangUtils.SINGLE_SPACE + str3);
        f17901c.writeLog("(W)-" + str + "-TBS:" + str2 + LangUtils.SINGLE_SPACE + str3);
    }

    public static void w(String str, String str2, boolean z) {
        w(str, str2);
        TbsLogClient tbsLogClient = f17901c;
        if (tbsLogClient != null && a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    public static synchronized void writeLogToDisk() {
        Handler handler;
        synchronized (TbsLog.class) {
            if (f17901c != null && (handler = b) != null) {
                handler.obtainMessage(500).sendToTarget();
            }
        }
    }
}
