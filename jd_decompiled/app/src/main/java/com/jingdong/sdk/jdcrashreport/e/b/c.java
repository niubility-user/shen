package com.jingdong.sdk.jdcrashreport.e.b;

import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class c extends com.jingdong.sdk.jdcrashreport.e.b.a {

    /* renamed from: c  reason: collision with root package name */
    private static volatile c f14920c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Throwable f14921g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Thread f14922h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f14923i;

        a(Throwable th, Thread thread, String str) {
            this.f14921g = th;
            this.f14922h = thread;
            this.f14923i = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            LinkedHashMap<String, String> a;
            if (com.jingdong.sdk.jdcrashreport.d.S()) {
                r.h("JDCrashReport", "Caught the following custom exception:");
                r.h("JDCrashReport", "--------------> print start <--------------");
                StringWriter stringWriter = new StringWriter();
                Throwable th = this.f14921g;
                if (th != null) {
                    th.printStackTrace(new PrintWriter(stringWriter));
                } else {
                    r.h("JDCrashReport", "throwable is null!!!");
                }
                r.h("JDCrashReport", stringWriter.toString());
                r.h("JDCrashReport", "--------------> print end <--------------");
            }
            CrashInfo generateCrashInfo = CrashInfo.generateCrashInfo(this.f14922h, this.f14921g);
            if (generateCrashInfo == null) {
                return;
            }
            generateCrashInfo.msgType = "3";
            generateCrashInfo.busiType = "java";
            generateCrashInfo.moduleName = this.f14923i;
            try {
                com.jingdong.sdk.jdcrashreport.a Z = com.jingdong.sdk.jdcrashreport.d.Z();
                if (Z != null && (a = Z.a(NavigatorHolder.NaviEntity.TYPE_CUSTOM, generateCrashInfo.crashStack)) != null) {
                    generateCrashInfo.extraInfo = a;
                    generateCrashInfo.feedback.putAll(a);
                }
            } catch (Throwable unused) {
            }
            c.this.b(generateCrashInfo);
        }
    }

    private c() {
        super(com.jingdong.sdk.jdcrashreport.d.G().b, com.jingdong.sdk.jdcrashreport.d.S() ? Final.FIVE_SECOND : 60000L);
    }

    public static synchronized c c() {
        c cVar;
        synchronized (c.class) {
            if (f14920c == null) {
                f14920c = new c();
            }
            cVar = f14920c;
        }
        return cVar;
    }

    public void d(Throwable th, String str) {
        if (com.jingdong.sdk.jdcrashreport.d.g()) {
            r.b("JDCrashReport", "downgrade is enabled, not report custom");
        } else {
            com.jingdong.sdk.jdcrashreport.b.d.c(new a(th, Thread.currentThread(), str));
        }
    }
}
