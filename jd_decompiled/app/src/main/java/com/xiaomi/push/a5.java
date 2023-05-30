package com.xiaomi.push;

import com.jingdong.sdk.platform.business.personal.R2;
import com.xiaomi.push.f9;
import com.xiaomi.push.l0;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/* loaded from: classes11.dex */
public class a5 {
    private String a;

    /* renamed from: c */
    private int f18456c;
    private long d;

    /* renamed from: e */
    private z4 f18457e;
    private boolean b = false;

    /* renamed from: f */
    private l0 f18458f = l0.b();

    /* loaded from: classes11.dex */
    public static class a {
        static final a5 a = new a5();
    }

    private s4 b(l0.a aVar) {
        if (aVar.a == 0) {
            Object obj = aVar.f18817c;
            if (obj instanceof s4) {
                return (s4) obj;
            }
            return null;
        }
        s4 a2 = a();
        a2.a(r4.CHANNEL_STATS_COUNTER.a());
        a2.c(aVar.a);
        a2.c(aVar.b);
        return a2;
    }

    private t4 d(int i2) {
        ArrayList arrayList = new ArrayList();
        t4 t4Var = new t4(this.a, arrayList);
        if (!j0.s(this.f18457e.f19350g)) {
            t4Var.a(z6.A(this.f18457e.f19350g));
        }
        h9 h9Var = new h9(i2);
        y8 a2 = new f9.a().a(h9Var);
        try {
            t4Var.b(a2);
        } catch (s8 unused) {
        }
        LinkedList<l0.a> c2 = this.f18458f.c();
        while (c2.size() > 0) {
            try {
                s4 b = b(c2.getLast());
                if (b != null) {
                    b.b(a2);
                }
                if (h9Var.h() > i2) {
                    break;
                }
                if (b != null) {
                    arrayList.add(b);
                }
                c2.removeLast();
            } catch (s8 | NoSuchElementException unused2) {
            }
        }
        return t4Var;
    }

    public static z4 e() {
        z4 z4Var;
        a5 a5Var = a.a;
        synchronized (a5Var) {
            z4Var = a5Var.f18457e;
        }
        return z4Var;
    }

    public static a5 f() {
        return a.a;
    }

    private void g() {
        if (!this.b || System.currentTimeMillis() - this.d <= this.f18456c) {
            return;
        }
        this.b = false;
        this.d = 0L;
    }

    public synchronized s4 a() {
        s4 s4Var;
        s4Var = new s4();
        s4Var.a(j0.g(this.f18457e.f19350g));
        s4Var.a = (byte) 0;
        s4Var.b = 1;
        s4Var.d((int) (System.currentTimeMillis() / 1000));
        return s4Var;
    }

    public synchronized t4 c() {
        t4 t4Var;
        t4Var = null;
        if (l()) {
            int i2 = R2.attr.decimalNumber;
            if (!j0.s(this.f18457e.f19350g)) {
                i2 = R2.attr.ambientEnabled;
            }
            t4Var = d(i2);
        }
        return t4Var;
    }

    public void h(int i2) {
        if (i2 > 0) {
            int i3 = i2 * 1000;
            if (i3 > 604800000) {
                i3 = 604800000;
            }
            if (this.f18456c == i3 && this.b) {
                return;
            }
            this.b = true;
            this.d = System.currentTimeMillis();
            this.f18456c = i3;
            g.j.a.a.a.c.B("enable dot duration = " + i3 + " start = " + this.d);
        }
    }

    public synchronized void i(s4 s4Var) {
        this.f18458f.e(s4Var);
    }

    public synchronized void j(XMPushService xMPushService) {
        this.f18457e = new z4(xMPushService);
        this.a = "";
        com.xiaomi.push.service.z0.f().k(new b5(this));
    }

    public boolean k() {
        return this.b;
    }

    boolean l() {
        g();
        return this.b && this.f18458f.a() > 0;
    }
}
