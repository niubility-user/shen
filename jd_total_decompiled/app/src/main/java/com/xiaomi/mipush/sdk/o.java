package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.g7;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
public class o {

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: f */
        private static volatile a f18401f;
        private Context a;
        private String b;

        /* renamed from: c */
        private Boolean f18402c;
        private C0822a d = new C0822a();

        /* renamed from: e */
        private final ArrayList<g7> f18403e = new ArrayList<>();

        /* renamed from: com.xiaomi.mipush.sdk.o$a$a */
        /* loaded from: classes11.dex */
        public class C0822a {

            /* renamed from: c */
            private ScheduledFuture<?> f18404c;
            private ScheduledThreadPoolExecutor a = new ScheduledThreadPoolExecutor(1);
            public final ArrayList<g7> b = new ArrayList<>();
            private final Runnable d = new t(this);

            public C0822a() {
                a.this = r2;
            }

            public void c() {
                if (this.f18404c == null) {
                    this.f18404c = this.a.scheduleAtFixedRate(this.d, 1000L, 1000L, TimeUnit.MILLISECONDS);
                }
            }

            public void f() {
                g7 remove = this.b.remove(0);
                Iterator<c8> it = com.xiaomi.push.service.e1.c(Arrays.asList(remove), a.this.a.getPackageName(), o0.c(a.this.a).d(), 30720).iterator();
                while (it.hasNext()) {
                    g.j.a.a.a.c.B("MiTinyDataClient Send item by PushServiceClient.sendMessage(XmActionNotification)." + remove.d());
                    f0.h(a.this.a).y(it.next(), c7.Notification, true, null);
                }
            }

            public void e(g7 g7Var) {
                this.a.execute(new s(this, g7Var));
            }
        }

        public static a b() {
            if (f18401f == null) {
                synchronized (a.class) {
                    if (f18401f == null) {
                        f18401f = new a();
                    }
                }
            }
            return f18401f;
        }

        private void d(g7 g7Var) {
            synchronized (this.f18403e) {
                if (!this.f18403e.contains(g7Var)) {
                    this.f18403e.add(g7Var);
                    if (this.f18403e.size() > 100) {
                        this.f18403e.remove(0);
                    }
                }
            }
        }

        private boolean f(Context context) {
            if (f0.h(context).J()) {
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
                    if (packageInfo == null) {
                        return false;
                    }
                    return packageInfo.versionCode >= 108;
                } catch (Exception unused) {
                    return false;
                }
            }
            return true;
        }

        private boolean i(Context context) {
            return o0.c(context).d() == null && !f(this.a);
        }

        private boolean j(g7 g7Var) {
            if (com.xiaomi.push.service.e1.e(g7Var, false)) {
                return false;
            }
            if (!this.f18402c.booleanValue()) {
                this.d.e(g7Var);
                return true;
            }
            g.j.a.a.a.c.B("MiTinyDataClient Send item by PushServiceClient.sendTinyData(ClientUploadDataItem)." + g7Var.d());
            f0.h(this.a).t(g7Var);
            return true;
        }

        public void c(Context context) {
            if (context == null) {
                g.j.a.a.a.c.o("context is null, MiTinyDataClientImp.init() failed.");
                return;
            }
            this.a = context;
            this.f18402c = Boolean.valueOf(f(context));
            h("com.xiaomi.xmpushsdk.tinydataPending.init");
        }

        public boolean e() {
            return this.a != null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:171:0x00a3, code lost:
            r0 = "MiTinyDataClient Pending " + r6.b() + " reason is com.xiaomi.xmpushsdk.tinydataPending.channel";
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public synchronized boolean g(g7 g7Var) {
            String str;
            if (g7Var == null) {
                return false;
            }
            if (com.xiaomi.push.service.e1.e(g7Var, true)) {
                return false;
            }
            boolean z = TextUtils.isEmpty(g7Var.m60a()) && TextUtils.isEmpty(this.b);
            boolean z2 = !e();
            Context context = this.a;
            boolean z3 = context == null || i(context);
            if (!z2 && !z && !z3) {
                g.j.a.a.a.c.B("MiTinyDataClient Send item immediately." + g7Var.d());
                if (TextUtils.isEmpty(g7Var.d())) {
                    g7Var.f(com.xiaomi.push.service.f0.a());
                }
                if (TextUtils.isEmpty(g7Var.m60a())) {
                    g7Var.a(this.b);
                }
                if (TextUtils.isEmpty(g7Var.c())) {
                    g7Var.e(this.a.getPackageName());
                }
                if (g7Var.a() <= 0) {
                    g7Var.b(System.currentTimeMillis());
                }
                return j(g7Var);
            }
            if (!z2) {
                if (z3) {
                    str = "MiTinyDataClient Pending " + g7Var.b() + " reason is com.xiaomi.xmpushsdk.tinydataPending.appId";
                }
                d(g7Var);
                return true;
            }
            str = "MiTinyDataClient Pending " + g7Var.b() + " reason is com.xiaomi.xmpushsdk.tinydataPending.init";
            g.j.a.a.a.c.B(str);
            d(g7Var);
            return true;
        }

        public void h(String str) {
            g.j.a.a.a.c.B("MiTinyDataClient.processPendingList(" + str + ")");
            ArrayList arrayList = new ArrayList();
            synchronized (this.f18403e) {
                arrayList.addAll(this.f18403e);
                this.f18403e.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                g((g7) it.next());
            }
        }
    }

    public static boolean a(Context context, g7 g7Var) {
        g.j.a.a.a.c.B("MiTinyDataClient.upload " + g7Var.d());
        if (!a.b().e()) {
            a.b().c(context);
        }
        return a.b().g(g7Var);
    }

    public static boolean b(String str, String str2, long j2, String str3) {
        g7 g7Var = new g7();
        g7Var.d(str);
        g7Var.c(str2);
        g7Var.a(j2);
        g7Var.b(str3);
        return a.b().g(g7Var);
    }
}
