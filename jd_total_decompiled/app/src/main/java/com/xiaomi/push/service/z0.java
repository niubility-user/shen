package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.push.a3;
import com.xiaomi.push.c3;
import com.xiaomi.push.l;
import com.xiaomi.push.r9;
import com.xiaomi.push.s6;
import com.xiaomi.push.u9;
import com.xiaomi.push.z6;
import java.io.BufferedInputStream;
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

    /* JADX WARN: Removed duplicated region for block: B:72:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void o() {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        Exception e2;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(r9.b().openFileInput("XMCloudCfg"));
            } catch (Exception e3) {
                bufferedInputStream = null;
                e2 = e3;
            } catch (Throwable th2) {
                bufferedInputStream = null;
                th = th2;
                u9.b(bufferedInputStream);
                throw th;
            }
            try {
                this.b = a3.r(com.xiaomi.push.c0.f(bufferedInputStream));
                bufferedInputStream.close();
            } catch (Exception e4) {
                e2 = e4;
                g.j.a.a.a.c.o("load config failure: " + e2.getMessage());
                u9.b(bufferedInputStream);
                if (this.b != null) {
                }
            }
            u9.b(bufferedInputStream);
            if (this.b != null) {
                this.b = new a3();
            }
        } catch (Throwable th3) {
            th = th3;
            u9.b(bufferedInputStream);
            throw th;
        }
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
