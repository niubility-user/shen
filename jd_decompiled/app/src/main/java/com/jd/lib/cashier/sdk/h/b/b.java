package com.jd.lib.cashier.sdk.h.b;

import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.pay.bean.Payment;

/* loaded from: classes14.dex */
public class b extends a {
    private static volatile b a;

    private b() {
    }

    public static synchronized b c() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                synchronized (b.class) {
                    if (a == null) {
                        a = new b();
                    }
                }
            }
            bVar = a;
        }
        return bVar;
    }

    private synchronized void d(int i2, int i3, Payment payment) {
        int i4;
        if (payment != null && i2 <= i3 - 1) {
            if (i2 == 0) {
                if (i2 == i4) {
                    payment.splitLineType = a.b.FLOOR_TOP_AND_BOTTOM;
                } else {
                    payment.splitLineType = a.b.FLOOR_TOP_AND_NORMAL;
                }
            } else if (i2 == i4) {
                payment.splitLineType = a.b.FLOOR_BOTTOM;
            } else {
                payment.splitLineType = a.b.NORMAL;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003f A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041 A[Catch: all -> 0x0075, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x000a, B:9:0x0010, B:10:0x001e, B:12:0x0023, B:18:0x002f, B:20:0x0035, B:24:0x0041, B:26:0x0049, B:28:0x005c, B:30:0x006d, B:29:0x0065), top: B:39:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.util.List<com.jd.lib.cashier.sdk.d.a.e.a> b(com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L75
            r0.<init>()     // Catch: java.lang.Throwable -> L75
            if (r7 != 0) goto La
            monitor-exit(r6)
            return r0
        La:
            boolean r1 = r7.showPayLogo()     // Catch: java.lang.Throwable -> L75
            if (r1 == 0) goto L1e
            com.jd.lib.cashier.sdk.h.g.b r1 = new com.jd.lib.cashier.sdk.h.g.b     // Catch: java.lang.Throwable -> L75
            java.lang.String r2 = r7.jdPayIcon     // Catch: java.lang.Throwable -> L75
            java.lang.String r3 = r7.jdPayTheme     // Catch: java.lang.Throwable -> L75
            java.lang.String r4 = r7.jdPayThemeBlack     // Catch: java.lang.Throwable -> L75
            r1.<init>(r2, r3, r4)     // Catch: java.lang.Throwable -> L75
            r0.add(r1)     // Catch: java.lang.Throwable -> L75
        L1e:
            java.util.List<com.jd.lib.cashier.sdk.pay.bean.Payment> r1 = r7.jdPayChannelList     // Catch: java.lang.Throwable -> L75
            r2 = 0
            if (r1 == 0) goto L2c
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L75
            if (r1 == 0) goto L2a
            goto L2c
        L2a:
            r1 = 0
            goto L2d
        L2c:
            r1 = 1
        L2d:
            if (r1 == 0) goto L3d
            boolean r3 = r7.showPayLogo()     // Catch: java.lang.Throwable -> L75
            if (r3 == 0) goto L3d
            com.jd.lib.cashier.sdk.h.g.f r3 = new com.jd.lib.cashier.sdk.h.g.f     // Catch: java.lang.Throwable -> L75
            r3.<init>()     // Catch: java.lang.Throwable -> L75
            r0.add(r3)     // Catch: java.lang.Throwable -> L75
        L3d:
            if (r1 == 0) goto L41
            monitor-exit(r6)
            return r0
        L41:
            java.util.List<com.jd.lib.cashier.sdk.pay.bean.Payment> r7 = r7.jdPayChannelList     // Catch: java.lang.Throwable -> L75
            int r1 = r7.size()     // Catch: java.lang.Throwable -> L75
        L47:
            if (r2 >= r1) goto L73
            java.lang.Object r3 = r7.get(r2)     // Catch: java.lang.Throwable -> L75
            com.jd.lib.cashier.sdk.pay.bean.Payment r3 = (com.jd.lib.cashier.sdk.pay.bean.Payment) r3     // Catch: java.lang.Throwable -> L75
            r6.d(r2, r1, r3)     // Catch: java.lang.Throwable -> L75
            java.lang.String r4 = r3.code     // Catch: java.lang.Throwable -> L75
            java.lang.String r5 = "moreInfo"
            boolean r4 = android.text.TextUtils.equals(r4, r5)     // Catch: java.lang.Throwable -> L75
            if (r4 == 0) goto L65
            r6.a(r3)     // Catch: java.lang.Throwable -> L75
            com.jd.lib.cashier.sdk.h.g.a r4 = new com.jd.lib.cashier.sdk.h.g.a     // Catch: java.lang.Throwable -> L75
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L75
            goto L6d
        L65:
            r6.a(r3)     // Catch: java.lang.Throwable -> L75
            com.jd.lib.cashier.sdk.h.g.c r4 = new com.jd.lib.cashier.sdk.h.g.c     // Catch: java.lang.Throwable -> L75
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L75
        L6d:
            r0.add(r4)     // Catch: java.lang.Throwable -> L75
            int r2 = r2 + 1
            goto L47
        L73:
            monitor-exit(r6)
            return r0
        L75:
            r7 = move-exception
            monitor-exit(r6)
            goto L79
        L78:
            throw r7
        L79:
            goto L78
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.h.b.b.b(com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity):java.util.List");
    }
}
