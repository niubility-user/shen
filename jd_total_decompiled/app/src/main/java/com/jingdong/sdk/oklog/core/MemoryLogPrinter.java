package com.jingdong.sdk.oklog.core;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.oklog.OKLogConfig;
import com.jingdong.sdk.oklog.reporter.AbsLogReporter;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MemoryLogPrinter implements c {
    private static final int JSON_INDENT = 2;
    private static final int MIN_STACK_OFFSET = 5;
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String NEW_LINE_REPLACEMENT = " <br> ";
    private a reportHandler;
    private AbsLogReporter reporter;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends Handler {
        AbsLogReporter a;

        public a(Looper looper, AbsLogReporter absLogReporter) {
            super(looper);
            this.a = absLogReporter;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            String str;
            HashMap<String, String> hashMap = new HashMap<>();
            Object[] objArr = (Object[]) message.obj;
            int i2 = message.what;
            String str2 = (String) objArr[0];
            String str3 = (String) objArr[1];
            Throwable th = (Throwable) objArr[2];
            String str4 = (String) objArr[3];
            String str5 = "";
            if (th != null) {
                String th2 = th.toString();
                int indexOf = th2.indexOf(":");
                if (indexOf != -1) {
                    th2 = th2.substring(0, indexOf);
                }
                String stackTraceString = Log.getStackTraceString(th);
                if (!TextUtils.isEmpty(stackTraceString) && stackTraceString.contains(MemoryLogPrinter.NEW_LINE)) {
                    stackTraceString = stackTraceString.replaceAll(MemoryLogPrinter.NEW_LINE, MemoryLogPrinter.NEW_LINE_REPLACEMENT);
                }
                if (!TextUtils.isEmpty(stackTraceString)) {
                    stackTraceString = MemoryLogPrinter.NEW_LINE_REPLACEMENT.concat(String.valueOf(stackTraceString));
                }
                String str6 = th2;
                str5 = stackTraceString;
                str = str6;
            } else {
                str = "";
            }
            HashMap<String, String> additionalData = this.a.getAdditionalData();
            if (additionalData != null) {
                hashMap.putAll(additionalData);
            }
            hashMap.put("exceptionType", str);
            hashMap.put("className", str4);
            hashMap.put("methodStack", str5);
            hashMap.put("msg", str3);
            hashMap.put("occurTime", String.format("%.6f", Float.valueOf(((float) System.currentTimeMillis()) / 1000.0f)));
            hashMap.put("logLevel", i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? i2 != 6 ? "UNKNOWN" : "ERROR" : "WARN" : "INFO" : "DEBUG" : "VERBOSE");
            hashMap.put("logTag", str2);
            this.a.report(hashMap);
        }
    }

    public MemoryLogPrinter(AbsLogReporter absLogReporter) {
        this.reporter = absLogReporter;
        HandlerThread handlerThread = new HandlerThread("OKLog-ReportHandler", 1);
        handlerThread.start();
        this.reportHandler = new a(handlerThread.getLooper(), absLogReporter);
    }

    private String getSourceClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return stackTrace[Math.min(getStackOffset(stackTrace), stackTrace.length)].getClassName();
    }

    private int getStackOffset(StackTraceElement[] stackTraceElementArr) {
        for (int i2 = 5; i2 < stackTraceElementArr.length; i2++) {
            String className = stackTraceElementArr[i2].getClassName();
            if (!OKLogConfig.inLogWrapperClasses(className) && !className.equals(MemoryLogPrinter.class.getName()) && !className.equals(OKLogConfig.class.getName()) && !className.equals(OKLog.class.getName())) {
                return i2;
            }
        }
        return 0;
    }

    private void log(String str, int i2, Throwable th, Object... objArr) {
        AbsLogReporter absLogReporter = this.reporter;
        if (absLogReporter != null && absLogReporter.isReportable(i2)) {
            String a2 = d.a(objArr);
            if (th == null && TextUtils.isEmpty(a2)) {
                return;
            }
            log(i2, str, a2, th);
        }
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void addAdapter(com.jingdong.sdk.oklog.core.a aVar) {
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void d(String str, Object... objArr) {
        log(str, 3, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void d(Throwable th, String str, Object... objArr) {
        log(str, 3, th, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void e(String str, Object... objArr) {
        log(str, 6, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void e(Throwable th, String str, Object... objArr) {
        log(str, 6, th, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void i(String str, Object... objArr) {
        log(str, 4, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void i(Throwable th, String str, Object... objArr) {
        log(str, 4, th, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void json(String str, String str2) {
        if (this.reporter == null) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            d(str, "Empty/Null json content");
            return;
        }
        try {
            String trim = str2.trim();
            if (trim.startsWith("{")) {
                d(str, new JSONObject(trim).toString(2));
            } else if (trim.startsWith("[")) {
                d(str, new JSONArray(trim).toString(2));
            } else {
                e("Invalid Json", new Object[0]);
            }
        } catch (JSONException unused) {
            e("Invalid Json", new Object[0]);
        }
    }

    public void log(int i2, String str, String str2, Throwable th) {
        AbsLogReporter absLogReporter = this.reporter;
        if (absLogReporter != null && absLogReporter.isReportable(i2)) {
            String sourceClassName = getSourceClassName();
            Message obtainMessage = this.reportHandler.obtainMessage();
            obtainMessage.what = i2;
            obtainMessage.obj = new Object[]{str, str2, th, sourceClassName};
            this.reportHandler.sendMessage(obtainMessage);
        }
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void v(String str, Object... objArr) {
        log(str, 2, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void v(Throwable th, String str, Object... objArr) {
        log(str, 2, th, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void w(String str, Object... objArr) {
        log(str, 5, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void w(Throwable th, String str, Object... objArr) {
        log(str, 5, th, objArr);
    }
}
