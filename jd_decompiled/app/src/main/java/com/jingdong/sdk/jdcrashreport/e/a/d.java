package com.jingdong.sdk.jdcrashreport.e.a;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.jdcrashreport.JDCrashReportListener;
import com.jingdong.sdk.jdcrashreport.b.h;
import com.jingdong.sdk.jdcrashreport.b.i;
import com.jingdong.sdk.jdcrashreport.b.j;
import com.jingdong.sdk.jdcrashreport.b.o;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.b.x;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.io.File;
import java.util.Locale;

/* loaded from: classes7.dex */
public class d {
    private static final d d = new d();
    private int a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f14917c = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements JDCrashReportListener {
        final /* synthetic */ String a;

        a(d dVar, String str) {
            this.a = str;
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
        public void onEnd(int i2, String str, CrashInfo crashInfo) {
            long a = x.a(crashInfo.crashTime);
            o.b(new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(a))));
            if (i.f("handled_anr_last_md5", "").equals(this.a)) {
                i.g("handled_same_anr_times", i.a("handled_same_anr_times", 0) + 1);
            } else {
                i.g("handled_same_anr_times", 1);
            }
            i.d("handled_anr_last_md5", this.a);
            i.h("handled_anr_last_time", a);
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
        public void onError(int i2, String str, CrashInfo crashInfo) {
            j.d(new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(x.a(crashInfo.crashTime)))), crashInfo);
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
        public void onStart(CrashInfo crashInfo) {
        }
    }

    private d() {
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x014f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.jingdong.sdk.jdcrashreport.e.a.a a(java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdcrashreport.e.a.d.a(java.lang.String):com.jingdong.sdk.jdcrashreport.e.a.a");
    }

    public static d b() {
        return d;
    }

    private void d(CrashInfo crashInfo, String str) {
        a aVar = new a(this, str);
        try {
            Thread thread = new Thread(new h(crashInfo, aVar));
            thread.start();
            thread.join();
        } catch (Exception e2) {
            r.c("JDCrashReport", "Report anr failed", e2);
            aVar.onError(0, e2.getMessage(), crashInfo);
        }
    }

    private boolean f(com.jingdong.sdk.jdcrashreport.e.a.a aVar) {
        long a2 = x.a(aVar.d()) - i.b("handled_anr_last_time", 0L);
        if (Math.abs(a2) < 10000) {
            r.b("JDCrashReport", "anr too often, ignore this anr.");
            return false;
        }
        String f2 = i.f("handled_anr_last_md5", "");
        String h2 = aVar.h();
        r.b("JDCrashReport", String.format("last md5: %s\uff0c md5: %s", f2, h2));
        if (f2.equals(h2)) {
            r.b("JDCrashReport", "this anr is same as the last");
            if (Math.abs(a2) < Final.HALF_MINUTE) {
                r.b("JDCrashReport", "same anr too often, ignore this anr.");
                return false;
            }
            int a3 = i.a("handled_same_anr_times", 0);
            if (a3 >= 3) {
                r.b("JDCrashReport", String.format(Locale.getDefault(), "this anr has reported %d times", Integer.valueOf(a3)));
                return false;
            }
        }
        return true;
    }

    public synchronized void c(Context context) {
        if (this.f14917c) {
            r.h("JDCrashReport", "TraceHandler has been initialized.");
            return;
        }
        int myPid = Process.myPid();
        this.a = myPid;
        String a2 = com.jingdong.sdk.jdcrashreport.b.c.a(myPid);
        if (TextUtils.isEmpty(a2)) {
            r.h("JDCrashReport", "process name is empty, initialize TraceHandler failed.");
            return;
        }
        this.b = a2;
        this.f14917c = true;
    }

    public synchronized void e(String str, boolean z) {
        com.jingdong.sdk.jdcrashreport.e.a.a a2;
        r.b("JDCrashReport", "handleTrace");
        if (!this.f14917c) {
            r.h("JDCrashReport", "TraceHandler has not been initilized!");
            return;
        }
        if (z) {
            a2 = com.jingdong.sdk.jdcrashreport.e.d.b.b(str);
            if (a2 != null && TextUtils.isEmpty(a2.g())) {
                String b = c.b();
                a2.e(b);
                a2.b(b);
                a2.c(false);
            }
        } else {
            a2 = a(str);
        }
        if (a2 == null) {
            r.b("JDCrashReport", "traceInfo is null");
            return;
        }
        if (com.jingdong.sdk.jdcrashreport.d.S()) {
            r.h("JDCrashReport", "Caught the following anr, main thread stack:");
            r.h("JDCrashReport", "--------------> print start <--------------");
            r.h("JDCrashReport", a2.toString());
            r.h("JDCrashReport", "--------------> print end <--------------");
        }
        if (com.jingdong.sdk.jdcrashreport.d.g()) {
            r.b("JDCrashReport", "downgrade is enabled, not report anr");
        } else if (!f(a2)) {
            r.b("JDCrashReport", "need not report the anr");
        } else {
            r.b("JDCrashReport", "report anr");
            CrashInfo a3 = c.a(a2);
            if (a3 == null) {
                r.h("JDCrashReport", "createCrashInfo failed, crashInfo is null");
            } else {
                d(a3, a2.h());
            }
        }
    }
}
