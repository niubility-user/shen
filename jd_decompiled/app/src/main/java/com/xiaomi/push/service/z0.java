package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.push.a3;
import com.xiaomi.push.c3;
import com.xiaomi.push.l;
import com.xiaomi.push.r9;
import com.xiaomi.push.s6;
import com.xiaomi.push.z6;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class z0 {
    private static String d;

    /* renamed from: e */
    private static z0 f19210e = new z0();
    private List<a> a = new ArrayList();
    private a3 b;

    /* renamed from: c */
    private l.b f19211c;

    /* loaded from: classes11.dex */
    public static abstract class a {
        public void b(a3 a3Var) {
        }

        public void c(c3 c3Var) {
        }
    }

    private z0() {
    }

    public static z0 f() {
        return f19210e;
    }

    public static synchronized String g() {
        String str;
        synchronized (z0.class) {
            if (d == null) {
                SharedPreferences sharedPreferences = r9.b().getSharedPreferences("XMPushServiceConfig", 0);
                String string = sharedPreferences.getString("DeviceUUID", null);
                d = string;
                if (string == null) {
                    String h2 = z6.h(r9.b(), false);
                    d = h2;
                    if (h2 != null) {
                        sharedPreferences.edit().putString("DeviceUUID", d).commit();
                    }
                }
            }
            str = d;
        }
        return str;
    }

    private void m() {
        if (this.b == null) {
            o();
        }
    }

    private void n() {
        if (this.f19211c != null) {
            return;
        }
        a1 a1Var = new a1(this);
        this.f19211c = a1Var;
        s6.a(a1Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void o() {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.xiaomi.push.r9.b()     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L27
            java.lang.String r2 = "XMCloudCfg"
            java.io.FileInputStream r1 = r1.openFileInput(r2)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L27
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L27
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L27
            com.xiaomi.push.c0 r0 = com.xiaomi.push.c0.f(r2)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L4f
            com.xiaomi.push.a3 r0 = com.xiaomi.push.a3.r(r0)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L4f
            r4.b = r0     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L4f
            r2.close()     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L4f
        L1d:
            com.xiaomi.push.u9.b(r2)
            goto L43
        L21:
            r0 = move-exception
            goto L2a
        L23:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L50
        L27:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L2a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f
            r1.<init>()     // Catch: java.lang.Throwable -> L4f
            java.lang.String r3 = "load config failure: "
            r1.append(r3)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L4f
            r1.append(r0)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L4f
            g.j.a.a.a.c.o(r0)     // Catch: java.lang.Throwable -> L4f
            goto L1d
        L43:
            com.xiaomi.push.a3 r0 = r4.b
            if (r0 != 0) goto L4e
            com.xiaomi.push.a3 r0 = new com.xiaomi.push.a3
            r0.<init>()
            r4.b = r0
        L4e:
            return
        L4f:
            r0 = move-exception
        L50:
            com.xiaomi.push.u9.b(r2)
            goto L55
        L54:
            throw r0
        L55:
            goto L54
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.z0.o():void");
    }

    public void p() {
        try {
            if (this.b != null) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(r9.b().openFileOutput("XMCloudCfg", 0));
                com.xiaomi.push.y0 n2 = com.xiaomi.push.y0.n(bufferedOutputStream);
                this.b.e(n2);
                n2.q();
                bufferedOutputStream.close();
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.o("save config failure: " + e2.getMessage());
        }
    }

    public int a() {
        m();
        a3 a3Var = this.b;
        if (a3Var != null) {
            return a3Var.u();
        }
        return 0;
    }

    public a3 c() {
        m();
        return this.b;
    }

    public synchronized void i() {
        this.a.clear();
    }

    public void j(c3 c3Var) {
        a[] aVarArr;
        if (c3Var.u() && c3Var.t() > a()) {
            n();
        }
        synchronized (this) {
            List<a> list = this.a;
            aVarArr = (a[]) list.toArray(new a[list.size()]);
        }
        for (a aVar : aVarArr) {
            aVar.c(c3Var);
        }
    }

    public synchronized void k(a aVar) {
        this.a.add(aVar);
    }
}
