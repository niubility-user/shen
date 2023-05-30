package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;
import com.xiaomi.push.x4;

/* loaded from: classes11.dex */
public class u4 implements i0.b.a {
    private XMPushService a;
    private i0.b b;

    /* renamed from: c */
    private o5 f19254c;

    /* renamed from: e */
    private int f19255e;

    /* renamed from: f */
    private boolean f19256f = false;
    private i0.c d = i0.c.binding;

    public u4(XMPushService xMPushService, i0.b bVar) {
        this.a = xMPushService;
        this.b = bVar;
    }

    private void d() {
        this.b.n(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void e() {
        r4 r4Var;
        d();
        if (!this.f19256f || this.f19255e == 11) {
            return;
        }
        s4 a = a5.f().a();
        int i2 = w4.a[this.d.ordinal()];
        if (i2 != 1) {
            if (i2 == 3) {
                r4Var = r4.BIND_SUCCESS;
            }
            if (a != null) {
                a.b(this.f19254c.d());
                a.d(this.b.b);
                a.b = 1;
                try {
                    a.a((byte) Integer.parseInt(this.b.f19100h));
                } catch (NumberFormatException unused) {
                }
                a5.f().i(a);
                return;
            }
            return;
        }
        int i3 = this.f19255e;
        if (i3 == 17) {
            r4Var = r4.BIND_TCP_READ_TIMEOUT;
        } else if (i3 == 21) {
            r4Var = r4.BIND_TIMEOUT;
        } else {
            try {
                x4.a d = x4.d(a5.e().a());
                a.f206a = d.a.a();
                a.c(d.b);
            } catch (NullPointerException unused2) {
                a = null;
            }
            if (a != null) {
            }
        }
        a.f206a = r4Var.a();
        if (a != null) {
        }
    }

    @Override // com.xiaomi.push.service.i0.b.a
    public void a(i0.c cVar, i0.c cVar2, int i2) {
        if (!this.f19256f && cVar == i0.c.binding) {
            this.d = cVar2;
            this.f19255e = i2;
            this.f19256f = true;
        }
        this.a.a(new v4(this, 4));
    }

    public void b() {
        this.b.i(this);
        this.f19254c = this.a.m151a();
    }
}
