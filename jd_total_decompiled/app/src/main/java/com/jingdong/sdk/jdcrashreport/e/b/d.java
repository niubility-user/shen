package com.jingdong.sdk.jdcrashreport.e.b;

import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class d extends com.jingdong.sdk.jdcrashreport.e.b.a {

    /* renamed from: c  reason: collision with root package name */
    private static volatile d f14925c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Throwable f14926g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Thread f14927h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f14928i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ String f14929j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ Map f14930k;

        a(Throwable th, Thread thread, String str, String str2, Map map) {
            this.f14926g = th;
            this.f14927h = thread;
            this.f14928i = str;
            this.f14929j = str2;
            this.f14930k = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            LinkedHashMap<String, String> a;
            if (com.jingdong.sdk.jdcrashreport.d.S()) {
                r.h("JDCrashReport", "Caught the following flutter exception:");
                r.h("JDCrashReport", "--------------> print start <--------------");
                StringWriter stringWriter = new StringWriter();
                Throwable th = this.f14926g;
                if (th != null) {
                    th.printStackTrace(new PrintWriter(stringWriter));
                } else {
                    r.h("JDCrashReport", "throwable is null!!!");
                }
                r.h("JDCrashReport", stringWriter.toString());
                r.h("JDCrashReport", "--------------> print end <--------------");
            }
            CrashInfo generateCrashInfo = CrashInfo.generateCrashInfo(this.f14927h, this.f14926g);
            if (generateCrashInfo == null) {
                return;
            }
            generateCrashInfo.msgType = "5";
            generateCrashInfo.busiType = JDReactConstant.FLUTTER_PATH;
            generateCrashInfo.moduleName = this.f14928i;
            generateCrashInfo.moduleVersion = this.f14929j;
            Map map = this.f14930k;
            if (map != null && map.size() > 0) {
                generateCrashInfo.extraInfo.putAll(this.f14930k);
                generateCrashInfo.feedback.putAll(this.f14930k);
                if (this.f14930k.containsKey("flutterSdkVersion")) {
                    generateCrashInfo.flutterSdkVersion = (String) this.f14930k.get("flutterSdkVersion");
                }
            }
            generateCrashInfo.allThreadStack = null;
            generateCrashInfo.sysLog = null;
            try {
                com.jingdong.sdk.jdcrashreport.a Z = com.jingdong.sdk.jdcrashreport.d.Z();
                if (Z != null && (a = Z.a(JDReactConstant.FLUTTER_PATH, generateCrashInfo.crashStack)) != null) {
                    generateCrashInfo.extraInfo.putAll(a);
                    generateCrashInfo.feedback.putAll(a);
                }
            } catch (Throwable unused) {
            }
            d.this.b(generateCrashInfo);
        }
    }

    private d() {
        super(com.jingdong.sdk.jdcrashreport.d.G().d, com.jingdong.sdk.jdcrashreport.d.S() ? Final.FIVE_SECOND : 60000L);
    }

    public static synchronized d c() {
        d dVar;
        synchronized (d.class) {
            if (f14925c == null) {
                f14925c = new d();
            }
            dVar = f14925c;
        }
        return dVar;
    }

    public void d(Throwable th, String str, String str2, Map<String, String> map) {
        if (com.jingdong.sdk.jdcrashreport.d.g()) {
            r.b("JDCrashReport", "downgrade is enabled, not report flutter");
        } else {
            com.jingdong.sdk.jdcrashreport.b.d.c(new a(th, Thread.currentThread(), str, str2, map));
        }
    }
}
