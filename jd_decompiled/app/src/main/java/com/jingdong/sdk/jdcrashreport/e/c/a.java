package com.jingdong.sdk.jdcrashreport.e.c;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.sdk.jdcrashreport.CrashService;
import com.jingdong.sdk.jdcrashreport.b.c;
import com.jingdong.sdk.jdcrashreport.b.i;
import com.jingdong.sdk.jdcrashreport.b.p;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.b.u;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import com.jingdong.sdk.jdcrashreport.crash.jni.NativeMonitor;
import com.jingdong.sdk.jdcrashreport.d;
import com.jingdong.sdk.jdcrashreport.recover.RecoverActivity;
import com.jingdong.sdk.jdcrashreport.recover.e;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class a implements Thread.UncaughtExceptionHandler {

    /* renamed from: c  reason: collision with root package name */
    private static final Object f14939c = new Object();
    private static final String[] d = {"android.os.TransactionTooLargeException"};

    /* renamed from: e  reason: collision with root package name */
    private static String f14940e;
    private Thread.UncaughtExceptionHandler a;
    private Thread.UncaughtExceptionHandler b;

    private ArrayList<Intent> a(CrashInfo crashInfo) {
        for (String str : d) {
            if (crashInfo.crashType.startsWith(str)) {
                return null;
            }
        }
        try {
            ArrayList<Intent> d2 = e.a().d();
            if (!d2.isEmpty()) {
                ComponentName component = d2.get(0).getComponent();
                String className = component != null ? component.getClassName() : null;
                Class<? extends Activity> f2 = e.f();
                if (f2 != null && !f2.getName().equals(className)) {
                    d2.add(0, new Intent(d.I(), f2));
                }
            }
            d2.get(d2.size() - 1).addCategory("FROM_RECOVERY_MODE");
            return d2;
        } catch (Throwable unused) {
            return null;
        }
    }

    private void c(CrashInfo crashInfo, boolean z) {
        Intent intent = new Intent();
        intent.setClass(d.I(), CrashService.class);
        intent.putExtra("IS_RECOVER", z);
        intent.putExtra("crashInfo", p.a(crashInfo.toString()));
        intent.putExtra("from", "JAVA");
        intent.putExtra("config", d.A());
        intent.putExtra("downgradeEnabled", d.g());
        d.I().startService(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0097, code lost:
        if (r0 != null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ca, code lost:
        if (r0 != null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00cc, code lost:
        r0.uncaughtException(r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00d0, code lost:
        com.jingdong.sdk.jdcrashreport.b.r.b("JDCrashReport", "current process die");
        com.jingdong.sdk.jdcrashreport.b.c.o();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00d6, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void d(java.lang.Thread r8, java.lang.Throwable r9) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.jdcrashreport.e.c.a.d(java.lang.Thread, java.lang.Throwable):void");
    }

    private static boolean e(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        String name = uncaughtExceptionHandler.getClass().getName();
        return name.equals("com.android.internal.os.RuntimeInit$KillApplicationHandler") || name.equals("com.android.internal.os.RuntimeInit$UncaughtHandler") || name.equals("com.tencent.tinker.loader.TinkerUncaughtHandler");
    }

    private static boolean f(Thread thread) {
        synchronized (f14939c) {
            if (TextUtils.equals(f14940e, thread.getName())) {
                return true;
            }
            f14940e = thread.getName();
            return false;
        }
    }

    private boolean g(CrashInfo crashInfo) {
        if (d.a() && c.j(d.I())) {
            long b = i.b("FORGE_CRASH_TIME", 0L);
            String f2 = i.f("FORGE_CRASH_CODE", "");
            String b2 = u.b((String.valueOf(crashInfo.crashType).trim() + String.valueOf(crashInfo.crashLine).trim() + String.valueOf(crashInfo.currentPageInfo).trim()).getBytes());
            i.d("FORGE_CRASH_CODE", b2);
            i.h("FORGE_CRASH_TIME", System.currentTimeMillis());
            return !b2.equals(f2) || System.currentTimeMillis() - b >= 60000;
        }
        return false;
    }

    private boolean h(CrashInfo crashInfo, boolean z) {
        if (z) {
            Intent intent = new Intent();
            intent.putExtra("config", d.A());
            intent.putExtra("crashType", crashInfo.crashType);
            intent.setClass(d.I(), RecoverActivity.class);
            intent.addFlags(276856832);
            ArrayList<Intent> a = a(crashInfo);
            if (a != null && a.size() > 0) {
                intent.putParcelableArrayListExtra("RECOVER_INTENTS", a);
            }
            d.I().startActivity(intent);
            return true;
        }
        return false;
    }

    private static boolean i(CrashInfo crashInfo) {
        String str;
        if (c.j(d.I())) {
            return true;
        }
        long b = i.b("last_crash_time", 0L);
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = "";
        if (currentTimeMillis - b < 86400000) {
            String f2 = i.f("last_crash_type", "");
            String f3 = i.f("last_crash_line", "");
            String trim = String.valueOf(crashInfo.crashType).trim();
            if (trim.length() > 300) {
                trim = trim.substring(0, 300);
            }
            String trim2 = String.valueOf(crashInfo.crashLine).trim();
            if (trim2.length() > 300) {
                trim2 = trim2.substring(0, 300);
            }
            String str3 = trim2;
            if (f2.equals(trim) && f3.equals(str3)) {
                int a = i.a("last_crash_consecutive_count", 0) + 1;
                i.g("last_crash_consecutive_count", a);
                return a <= 10 || a % 100 == 0;
            }
            str = str3;
            str2 = trim;
        } else {
            str = "";
        }
        i.e().putLong("last_crash_time", currentTimeMillis).putString("last_crash_type", str2).putString("last_crash_line", str).putInt("last_crash_consecutive_count", 0).apply();
        return true;
    }

    public synchronized void b() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            if (getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                return;
            }
            if (e(defaultUncaughtExceptionHandler)) {
                this.a = defaultUncaughtExceptionHandler;
                this.b = defaultUncaughtExceptionHandler;
            } else {
                this.b = defaultUncaughtExceptionHandler;
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        synchronized (f14939c) {
            if (d.S()) {
                r.h("JDCrashReport", "Caught the following java crash:");
                r.h("JDCrashReport", "--------------> print start <--------------");
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                r.h("JDCrashReport", stringWriter.toString());
                r.h("JDCrashReport", "--------------> print end <--------------");
            }
            NativeMonitor.a().c();
            d(thread, th);
        }
    }
}
