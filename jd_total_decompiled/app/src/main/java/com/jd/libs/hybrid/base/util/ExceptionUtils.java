package com.jd.libs.hybrid.base.util;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import jpbury.t;

/* loaded from: classes16.dex */
public class ExceptionUtils {
    private static Boolean a;
    private static CustomExceptionReporter b;

    @Keep
    /* loaded from: classes16.dex */
    public interface CustomExceptionReporter {
        void reportError(Context context, HashMap<String, String> hashMap);
    }

    private ExceptionUtils() {
    }

    private static void a() {
        try {
            new ExceptionReporter();
            a = Boolean.TRUE;
        } catch (Throwable unused) {
            Log.e("ExceptionUtils", "Error in importing module of 'com.jingdong.jdsdk.network.toolbox.ExceptionReporter', cannot use ExceptionReporter to report exception data. You can either set your own by ExceptionUtils.setExceptionReporter() method or add dependency for 'ExceptionReporter'");
            a = Boolean.FALSE;
        }
    }

    private static void b(Context context, HashMap<String, String> hashMap) {
        if (a == null) {
            a();
        }
        if (a.booleanValue()) {
            try {
                ExceptionReporter.sendExceptionData(context, hashMap);
            } catch (Throwable th) {
                Log.e("ExceptionUtils", th);
            }
        }
    }

    public static String getStackStringFromException(Throwable th) {
        PrintWriter printWriter;
        StringWriter stringWriter;
        if (th != null) {
            StringWriter stringWriter2 = null;
            try {
                stringWriter = new StringWriter();
                try {
                    printWriter = new PrintWriter((Writer) stringWriter, true);
                } catch (Exception unused) {
                    printWriter = null;
                } catch (Throwable th2) {
                    th = th2;
                    printWriter = null;
                }
            } catch (Exception unused2) {
                printWriter = null;
            } catch (Throwable th3) {
                th = th3;
                printWriter = null;
            }
            try {
                th.printStackTrace(printWriter);
                String stringBuffer = stringWriter.getBuffer().toString();
                try {
                    stringWriter.close();
                } catch (Exception unused3) {
                }
                try {
                    printWriter.close();
                } catch (Exception unused4) {
                }
                return stringBuffer;
            } catch (Exception unused5) {
                stringWriter2 = stringWriter;
                if (stringWriter2 != null) {
                    try {
                        stringWriter2.close();
                    } catch (Exception unused6) {
                    }
                }
                if (printWriter != null) {
                    try {
                        printWriter.close();
                        return "null message";
                    } catch (Exception unused7) {
                        return "null message";
                    }
                }
                return "null message";
            } catch (Throwable th4) {
                th = th4;
                stringWriter2 = stringWriter;
                if (stringWriter2 != null) {
                    try {
                        stringWriter2.close();
                    } catch (Exception unused8) {
                    }
                }
                if (printWriter != null) {
                    try {
                        printWriter.close();
                    } catch (Exception unused9) {
                    }
                }
                throw th;
            }
        }
        return "null message";
    }

    public static void report(HashMap<String, String> hashMap) {
        CustomExceptionReporter customExceptionReporter = b;
        if (customExceptionReporter != null) {
            customExceptionReporter.reportError(HybridSettings.getAppContext(), hashMap);
        } else {
            b(HybridSettings.getAppContext(), hashMap);
        }
    }

    public static void reportError(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return;
        }
        hashMap.put("errCode", "944");
        hashMap.put(PerformanceManager.ERR_TYPE, "2");
        if (!hashMap.containsKey("occurTime")) {
            double currentTimeMillis = System.currentTimeMillis();
            Double.isNaN(currentTimeMillis);
            hashMap.put("occurTime", String.format("%.6f", Double.valueOf(currentTimeMillis / 1000.0d)));
        }
        Log.d("ExceptionUtils", "report exception: " + hashMap.toString());
        report(hashMap);
    }

    public static void setExceptionReporter(CustomExceptionReporter customExceptionReporter) {
        b = customExceptionReporter;
    }

    public static void reportError(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("function", str);
        hashMap.put("errMsg", str2);
        hashMap.put(t.f20145j, str4);
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("reserved2", str3);
        }
        reportError(hashMap);
    }
}
