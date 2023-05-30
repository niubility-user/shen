package com.jingdong.sdk.jdcrashreport.e.b;

import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class e extends com.jingdong.sdk.jdcrashreport.e.b.a {

    /* renamed from: c  reason: collision with root package name */
    private static volatile e f14932c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Throwable f14933g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Thread f14934h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f14935i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f14936j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ String f14937k;

        a(Throwable th, Thread thread, String str, String str2, String str3) {
            this.f14933g = th;
            this.f14934h = thread;
            this.f14935i = str;
            this.f14936j = str2;
            this.f14937k = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            LinkedHashMap<String, String> a;
            if (this.f14933g == null) {
                return;
            }
            if (com.jingdong.sdk.jdcrashreport.d.S()) {
                r.h("JDCrashReport", "Caught the following RN exception:");
                r.h("JDCrashReport", "--------------> print start <--------------");
                StringWriter stringWriter = new StringWriter();
                this.f14933g.printStackTrace(new PrintWriter(stringWriter));
                r.h("JDCrashReport", stringWriter.toString());
                r.h("JDCrashReport", "--------------> print end <--------------");
            }
            CrashInfo generateCrashInfo = CrashInfo.generateCrashInfo(this.f14934h, this.f14933g);
            if (generateCrashInfo == null) {
                return;
            }
            generateCrashInfo.msgType = "4";
            generateCrashInfo.busiType = "rn";
            generateCrashInfo.moduleName = this.f14935i;
            generateCrashInfo.moduleVersion = this.f14936j;
            generateCrashInfo.commitId = this.f14937k;
            generateCrashInfo.allThreadStack = null;
            generateCrashInfo.sysLog = null;
            try {
                com.jingdong.sdk.jdcrashreport.a Z = com.jingdong.sdk.jdcrashreport.d.Z();
                if (Z != null && (a = Z.a("rn", generateCrashInfo.crashStack)) != null) {
                    generateCrashInfo.extraInfo = a;
                    generateCrashInfo.feedback.putAll(a);
                }
            } catch (Throwable unused) {
            }
            e.this.b(generateCrashInfo);
        }
    }

    private e() {
        super(com.jingdong.sdk.jdcrashreport.d.G().f14900c, com.jingdong.sdk.jdcrashreport.d.S() ? Final.FIVE_SECOND : 60000L);
    }

    public static synchronized e c() {
        e eVar;
        synchronized (e.class) {
            if (f14932c == null) {
                f14932c = new e();
            }
            eVar = f14932c;
        }
        return eVar;
    }

    public void d(String str, String str2, String str3, Throwable th) {
        if (com.jingdong.sdk.jdcrashreport.d.g()) {
            r.b("JDCrashReport", "downgrade is enabled, not report rn");
        } else {
            com.jingdong.sdk.jdcrashreport.b.d.c(new a(th, Thread.currentThread(), str, str2, str3));
        }
    }
}
