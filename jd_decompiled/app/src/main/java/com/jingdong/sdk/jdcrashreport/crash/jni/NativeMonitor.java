package com.jingdong.sdk.jdcrashreport.crash.jni;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.sdk.jdcrashreport.CrashService;
import com.jingdong.sdk.jdcrashreport.JDCrashReportConfig;
import com.jingdong.sdk.jdcrashreport.b.c;
import com.jingdong.sdk.jdcrashreport.b.j;
import com.jingdong.sdk.jdcrashreport.b.n;
import com.jingdong.sdk.jdcrashreport.b.o;
import com.jingdong.sdk.jdcrashreport.b.p;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.b.t;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import com.jingdong.sdk.jdcrashreport.d;
import com.jingdong.sdk.jdcrashreport.e.d.b;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

@SuppressLint({"StaticFieldLeak"})
/* loaded from: classes7.dex */
public class NativeMonitor {
    private static final NativeMonitor a = new NativeMonitor();
    private boolean b = false;

    /* renamed from: c */
    private boolean f14901c;

    private NativeMonitor() {
    }

    public static NativeMonitor a() {
        return a;
    }

    private static void crashCallback(String str, String str2, boolean z, boolean z2, String str3) {
        com.jingdong.sdk.jdcrashreport.e.d.a aVar;
        String str4;
        String str5;
        r.b("JDCrashReport", "native crash callback");
        d.s("native", true);
        StringBuffer stringBuffer = new StringBuffer();
        String str6 = "Unknown";
        if (!TextUtils.isEmpty(str) && new File(str).exists()) {
            aVar = b.a(str);
            if (aVar != null) {
                stringBuffer.append("Crash time: ");
                stringBuffer.append(aVar.a());
                stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                stringBuffer.append(aVar.c());
                stringBuffer.append("\n\n");
                stringBuffer.append(aVar.b());
                stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                stringBuffer.append("caused by: \n");
                if (aVar.e() != -100 && aVar.f() != -100) {
                    String nativeResolveCause = nativeResolveCause(aVar.e(), aVar.f());
                    if (TextUtils.isEmpty(nativeResolveCause)) {
                        stringBuffer.append("\n\n");
                    } else {
                        stringBuffer.append("    ");
                        stringBuffer.append(nativeResolveCause);
                        stringBuffer.append("\n\n");
                    }
                }
                stringBuffer.append(aVar.d());
                stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                str6 = aVar.g();
            } else {
                stringBuffer.append("No Native Stack\n\n");
            }
        } else {
            stringBuffer.append("No Native Stack\n\n");
            aVar = null;
        }
        String str7 = str6;
        stringBuffer.append("Java Stack:\n");
        if (aVar != null) {
            str4 = aVar.i();
            if (aVar.j() == aVar.h()) {
                str4 = "main (";
            }
        } else {
            str4 = str3;
        }
        Map<String, JSONObject> c2 = c.c(str4);
        Iterator<JSONObject> it = c2.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                str5 = "no stack";
                break;
            }
            JSONObject next = it.next();
            if (next.optString("name").startsWith(str4)) {
                str3 = next.optString("name");
                str5 = next.optString("stack");
                break;
            }
        }
        String str8 = str3;
        if (!TextUtils.isEmpty(str8)) {
            c2.remove(str8);
        }
        stringBuffer.append(" \u21e2 ");
        stringBuffer.append(str8);
        stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        stringBuffer.append(str5);
        stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        if (d.S()) {
            r.h("JDCrashReport", "Caught the following native crash:");
            r.h("JDCrashReport", "--------------> print start <--------------");
            r.h("JDCrashReport", stringBuffer.toString());
            r.h("JDCrashReport", "--------------> print end <--------------");
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        try {
            com.jingdong.sdk.jdcrashreport.a Z = d.Z();
            if (Z != null) {
                linkedHashMap = Z.a("native", stringBuffer.toString());
            }
        } catch (Throwable unused) {
        }
        try {
            a(str, str7, str8, stringBuffer.toString(), c2, linkedHashMap);
        } catch (Throwable th) {
            r.g("JDCrashReport", th);
        }
    }

    private boolean d() {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                System.loadLibrary("jdcrash");
                return true;
            } catch (Throwable th) {
                r.c("JDCrashReport", "NativeMonitor load so failed.", th);
            }
        }
        return false;
    }

    private static native void nativeAnrHandler();

    private static native int nativeInit(int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, boolean z, boolean z2, int i3, int i4, int i5, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i6, String[] strArr, boolean z8, boolean z9, int i7, int i8, int i9, boolean z10, boolean z11);

    private static native void nativeNotifyJavaCrashed();

    private static native String nativeResolveCause(int i2, int i3);

    private static native void nativeTestCrash(int i2);

    private static void traceCallback(String str, String str2) {
        r.b("JDCrashReport", "trace callback, logPath: " + str);
        if (TextUtils.isEmpty(str)) {
            r.h("JDCrashReport", "trace logPath is empty!");
        } else if (!new File(str).exists()) {
            r.h("JDCrashReport", String.format("trace file '%s' not exist!", str));
        } else {
            com.jingdong.sdk.jdcrashreport.e.a.d.b().e(str, true);
            if (d.S()) {
                return;
            }
            o.c(str);
        }
    }

    public void b() {
        if (this.b && this.f14901c) {
            r.b("JDCrashReport", "native handle anr");
            nativeAnrHandler();
        }
    }

    public void c() {
        if (this.b && this.f14901c) {
            nativeNotifyJavaCrashed();
        }
    }

    public synchronized void a(Context context, String str, boolean z, boolean z2) {
        if (this.b) {
            return;
        }
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            packageName = "unknown";
        }
        String str2 = packageName;
        this.f14901c = z2;
        if (d()) {
            File f2 = j.f();
            if (f2 != null && f2.isDirectory()) {
                if (nativeInit(n.g(), n.d(), n.h(), n.j(), n.c(false), n.e(false), n.i(), str2, str, context.getApplicationInfo().nativeLibraryDir, f2.getAbsolutePath(), z, true, 50, 50, 200, false, false, false, false, false, 0, null, z2, true, 50, 50, 200, false, false) == 0) {
                    this.b = true;
                } else {
                    r.h("JDCrashReport", "NativeMonitor init failed, return value != 0");
                }
            } else {
                r.b("JDCrashReport", "tombstone log dir is null or not a directory");
            }
        } else {
            r.b("JDCrashReport", "NativeMonitor load so failed");
        }
    }

    public static void a(boolean z) {
        if (a().b) {
            nativeTestCrash(z ? 1 : 0);
        }
    }

    private static void a(String str, String str2, String str3, String str4, Map<String, JSONObject> map, LinkedHashMap<String, String> linkedHashMap) {
        CrashInfo createCrashInfo = CrashInfo.createCrashInfo();
        createCrashInfo.busiType = "native";
        createCrashInfo.crashType = str2;
        if (str2.contains("SIGSEGV")) {
            createCrashInfo.msgType = "1";
        } else {
            createCrashInfo.msgType = "2";
        }
        createCrashInfo.processName = c.a(Process.myPid());
        createCrashInfo.threadName = str3;
        str4.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        createCrashInfo.crashLine = "";
        createCrashInfo.isForeground = String.valueOf(c.j(d.I()));
        createCrashInfo.pageInfo = t.h();
        createCrashInfo.currentPageInfo = t.l();
        createCrashInfo.crashStack = str4;
        createCrashInfo.allThreadStack = map;
        if (linkedHashMap != null) {
            createCrashInfo.extraInfo = linkedHashMap;
            createCrashInfo.feedback.putAll(linkedHashMap);
        }
        JDCrashReportConfig.f k2 = d.k(createCrashInfo.crashType, createCrashInfo.crashStack);
        if (JDCrashReportConfig.f.CUSTOM == k2) {
            d.z(createCrashInfo.crashType, createCrashInfo.crashStack);
            return;
        }
        if (JDCrashReportConfig.f.EXCEPTION == k2) {
            createCrashInfo.msgType = "3";
            createCrashInfo.busiType = "java";
        }
        Intent intent = new Intent(d.I(), CrashService.class);
        intent.putExtra("crashInfo", p.a(createCrashInfo.toString()));
        intent.putExtra("from", "Native");
        intent.putExtra("logPath", str);
        intent.putExtra("config", d.A());
        intent.putExtra("downgradeEnabled", d.g());
        intent.putExtra("IS_RECOVER", false);
        d.I().startService(intent);
    }
}
