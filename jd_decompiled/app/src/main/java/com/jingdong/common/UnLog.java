package com.jingdong.common;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class UnLog {
    public static boolean D = false;
    public static boolean E = false;
    public static boolean I = false;
    private static final String TAG = "UnLog";
    public static boolean V;
    public static boolean W;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static boolean printLog = true;
    private static boolean isSetPrintFlag = false;

    static {
        setAll(true);
    }

    public static void d(String str, String str2) {
        boolean z = printLog;
    }

    public static void e(String str, String str2) {
        boolean z = printLog;
    }

    public static void enableLog(boolean z) {
        if (isSetPrintFlag) {
            return;
        }
        isSetPrintFlag = true;
        printLog = z;
        setAll(z);
    }

    public static void enableLogForLogSys(boolean z) {
        if (printLog) {
            return;
        }
        setAll(z);
    }

    public static void i(String str, String str2) {
        boolean z = printLog;
    }

    public static boolean isEnabled() {
        return printLog;
    }

    public static void json(String str, Object obj) {
        if (printLog) {
            printJson(str, obj != null ? obj.toString() : null);
        }
    }

    private static void printJson(String str, String str2) {
        String jSONArray;
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        String str3 = null;
        try {
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (str2.startsWith("{")) {
            jSONArray = new JSONObject(str2).toString(4);
        } else {
            if (str2.startsWith("[")) {
                jSONArray = new JSONArray(str2).toString(4);
            }
            String str4 = "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550" + str + "\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550";
            if (str3 != null || str3.length() <= 4000) {
            }
            String[] split = str3.split(LINE_SEPARATOR);
            StringBuilder sb = new StringBuilder();
            for (String str5 : split) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str5);
                String str6 = LINE_SEPARATOR;
                sb2.append(str6);
                String sb3 = sb2.toString();
                if (sb.length() + sb3.length() >= 4000) {
                    sb.toString();
                    sb = new StringBuilder();
                    sb.append(str5);
                    sb.append(str6);
                } else {
                    sb.append(sb3);
                }
            }
            if (sb.length() > 0) {
                sb.toString();
                return;
            }
            return;
        }
        str3 = jSONArray;
        String str42 = "\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550" + str + "\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550";
        if (str3 != null) {
        }
    }

    public static void s(String str) {
        boolean z = printLog;
    }

    private static void setAll(boolean z) {
        V = z;
        D = z;
        I = z;
        W = z;
        E = z;
    }

    public static void v(String str, String str2) {
        boolean z = printLog;
    }

    public static void w(String str, String str2) {
        boolean z = printLog;
    }

    public static void d(String str, String str2, Throwable th) {
        boolean z = printLog;
    }

    public static void e(String str, String str2, Throwable th) {
        boolean z = printLog;
    }

    public static void i(String str, String str2, Throwable th) {
        boolean z = printLog;
    }

    public static void s(int i2) {
        if (printLog) {
            String str = "message : " + i2;
        }
    }

    public static void v(String str, String str2, Throwable th) {
        boolean z = printLog;
    }

    public static void w(String str, Throwable th) {
        boolean z = printLog;
    }

    public static void w(String str, String str2, Throwable th) {
        boolean z = printLog;
    }

    public static void s(String str, String str2) {
        boolean z = printLog;
    }

    public static void s(String str, String str2, Throwable th) {
        boolean z = printLog;
    }
}
