package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;

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

    /* JADX WARN: Removed duplicated region for block: B:60:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e() {
        /*
            r4 = this;
            r4.d()
            boolean r0 = r4.f19256f
            if (r0 != 0) goto L8
            return
        L8:
            int r0 = r4.f19255e
            r1 = 11
            if (r0 != r1) goto Lf
            return
        Lf:
            com.xiaomi.push.a5 r0 = com.xiaomi.push.a5.f()
            com.xiaomi.push.s4 r0 = r0.a()
            int[] r1 = com.xiaomi.push.w4.a
            com.xiaomi.push.service.i0$c r2 = r4.d
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L31
            r3 = 3
            if (r1 == r3) goto L28
            goto L5c
        L28:
            com.xiaomi.push.r4 r1 = com.xiaomi.push.r4.BIND_SUCCESS
        L2a:
            int r1 = r1.a()
            r0.f206a = r1
            goto L5c
        L31:
            int r1 = r4.f19255e
            r3 = 17
            if (r1 != r3) goto L3a
            com.xiaomi.push.r4 r1 = com.xiaomi.push.r4.BIND_TCP_READ_TIMEOUT
            goto L2a
        L3a:
            r3 = 21
            if (r1 != r3) goto L41
            com.xiaomi.push.r4 r1 = com.xiaomi.push.r4.BIND_TIMEOUT
            goto L2a
        L41:
            com.xiaomi.push.z4 r1 = com.xiaomi.push.a5.e()     // Catch: java.lang.NullPointerException -> L5b
            java.lang.Exception r1 = r1.a()     // Catch: java.lang.NullPointerException -> L5b
            com.xiaomi.push.x4$a r1 = com.xiaomi.push.x4.d(r1)     // Catch: java.lang.NullPointerException -> L5b
            com.xiaomi.push.r4 r3 = r1.a     // Catch: java.lang.NullPointerException -> L5b
            int r3 = r3.a()     // Catch: java.lang.NullPointerException -> L5b
            r0.f206a = r3     // Catch: java.lang.NullPointerException -> L5b
            java.lang.String r1 = r1.b     // Catch: java.lang.NullPointerException -> L5b
            r0.c(r1)     // Catch: java.lang.NullPointerException -> L5b
            goto L5c
        L5b:
            r0 = 0
        L5c:
            if (r0 == 0) goto L83
            com.xiaomi.push.o5 r1 = r4.f19254c
            java.lang.String r1 = r1.d()
            r0.b(r1)
            com.xiaomi.push.service.i0$b r1 = r4.b
            java.lang.String r1 = r1.b
            r0.d(r1)
            r0.b = r2
            com.xiaomi.push.service.i0$b r1 = r4.b     // Catch: java.lang.NumberFormatException -> L7c
            java.lang.String r1 = r1.f19100h     // Catch: java.lang.NumberFormatException -> L7c
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L7c
            byte r1 = (byte) r1     // Catch: java.lang.NumberFormatException -> L7c
            r0.a(r1)     // Catch: java.lang.NumberFormatException -> L7c
        L7c:
            com.xiaomi.push.a5 r1 = com.xiaomi.push.a5.f()
            r1.i(r0)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.u4.e():void");
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
