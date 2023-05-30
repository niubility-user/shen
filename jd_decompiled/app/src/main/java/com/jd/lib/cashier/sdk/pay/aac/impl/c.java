package com.jd.lib.cashier.sdk.pay.aac.impl;

import android.graphics.Point;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.h0;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.h.g.t;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes14.dex */
public class c implements com.jd.lib.cashier.sdk.d.d.a {

    /* renamed from: g */
    private CashierPayActivity f3653g;

    public c(CashierPayActivity cashierPayActivity) {
        this.f3653g = cashierPayActivity;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x001b A[Catch: Exception -> 0x002b, TryCatch #0 {Exception -> 0x002b, blocks: (B:22:0x0001, B:24:0x000b, B:30:0x001b, B:31:0x0024), top: B:36:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0024 A[Catch: Exception -> 0x002b, TRY_LEAVE, TryCatch #0 {Exception -> 0x002b, blocks: (B:22:0x0001, B:24:0x000b, B:30:0x001b, B:31:0x0024), top: B:36:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int a(com.jd.lib.cashier.sdk.pay.bean.Payment r4) {
        /*
            r3 = this;
            r0 = 0
            java.lang.String r1 = "5"
            java.lang.String r2 = r4.status     // Catch: java.lang.Exception -> L2b
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L2b
            if (r1 != 0) goto L18
            java.lang.String r1 = "1"
            java.lang.String r2 = r4.status     // Catch: java.lang.Exception -> L2b
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L2b
            if (r1 == 0) goto L16
            goto L18
        L16:
            r1 = 0
            goto L19
        L18:
            r1 = 1
        L19:
            if (r1 == 0) goto L24
            java.lang.String r1 = r4.availableBalance     // Catch: java.lang.Exception -> L2b
            java.lang.String r4 = r4.investorDoc     // Catch: java.lang.Exception -> L2b
            int r4 = r3.f(r1, r4)     // Catch: java.lang.Exception -> L2b
            goto L2a
        L24:
            java.lang.String r4 = r4.regulatorCantUseDesc     // Catch: java.lang.Exception -> L2b
            int r4 = r3.h(r4)     // Catch: java.lang.Exception -> L2b
        L2a:
            return r4
        L2b:
            r4 = move-exception
            r4.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.aac.impl.c.a(com.jd.lib.cashier.sdk.pay.bean.Payment):int");
    }

    private int f(String str, String str2) {
        int dip2px;
        int dip2px2;
        int i2;
        int c2 = h0.c(this.f3653g, 20, 20, 3.5f, R2.anim.popdown_anim_feedback);
        int dip2px3 = DpiUtil.dip2px(this.f3653g, 4.0f);
        DisplayMetrics i3 = y.i();
        if (i3 != null && i3.density > 2.8f) {
            dip2px3 = DpiUtil.dip2px(this.f3653g, 6.0f);
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Point d = h0.d(this.f3653g, str, y.n(12.0f), c2, -2, true);
            int dip2px4 = d == null ? DpiUtil.dip2px(this.f3653g, 12.0f) : d.y;
            Point d2 = h0.d(this.f3653g, str2, y.n(12.0f), c2, -2, false);
            dip2px = dip2px4 + (d2 == null ? DpiUtil.dip2px(this.f3653g, 30.0f) : d2.y) + (DpiUtil.dip2px(this.f3653g, 8.0f) * 2);
            i2 = DpiUtil.dip2px(this.f3653g, 6.0f);
        } else {
            if (!TextUtils.isEmpty(str2)) {
                Point d3 = h0.d(this.f3653g, str2, y.n(12.0f), c2, -2, false);
                dip2px = d3 == null ? DpiUtil.dip2px(this.f3653g, 30.0f) : d3.y;
                dip2px2 = DpiUtil.dip2px(this.f3653g, 8.0f);
            } else if (TextUtils.isEmpty(str)) {
                return 0;
            } else {
                Point d4 = h0.d(this.f3653g, str, y.n(12.0f), c2, -2, true);
                dip2px = d4 == null ? DpiUtil.dip2px(this.f3653g, 12.0f) : d4.y;
                dip2px2 = DpiUtil.dip2px(this.f3653g, 8.0f);
            }
            i2 = dip2px2 * 2;
        }
        return dip2px + i2 + dip2px3;
    }

    private int h(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int dip2px = DpiUtil.dip2px(this.f3653g, 4.0f);
        DisplayMetrics i2 = y.i();
        if (i2 != null && i2.density > 2.8f) {
            dip2px = DpiUtil.dip2px(this.f3653g, 6.0f);
        }
        Point d = h0.d(this.f3653g, str, y.n(12.0f), h0.c(this.f3653g, 20, 20, 3.5f, R2.anim.popdown_anim_feedback), -2, false);
        return d == null ? DpiUtil.dip2px(this.f3653g, 45.0f) : d.y + (DpiUtil.dip2px(this.f3653g, 8.0f) * 2) + dip2px;
    }

    public void c(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        try {
            if (g0.a(this.f3653g) && list != null && !list.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                int i2 = 0;
                for (com.jd.lib.cashier.sdk.d.a.e.a aVar : list) {
                    if (aVar instanceof t) {
                        Payment a = ((t) aVar).a();
                        arrayList.add(a);
                        i2 = Math.max(i2, a(a));
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Payment) it.next()).rootViewHeight = i2;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3653g != null) {
            this.f3653g = null;
        }
    }
}
