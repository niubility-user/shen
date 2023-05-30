package com.jd.lib.cashier.sdk.pay.handler;

import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import java.util.Map;

/* loaded from: classes14.dex */
public class a {
    private long a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.cashier.sdk.pay.handler.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public static /* synthetic */ class C0144a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.jd.lib.cashier.sdk.d.g.g.f.values().length];
            a = iArr;
            try {
                iArr[com.jd.lib.cashier.sdk.d.g.g.f.WEIXIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.OCTOPUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.QQWALLET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.UNIONPAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.MEDICALPAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.JDPAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[com.jd.lib.cashier.sdk.d.g.g.f.CYBERMONEY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private void b(CashierPayActivity cashierPayActivity, com.jd.lib.cashier.sdk.h.c.a aVar, PaymentChoseHolder paymentChoseHolder, com.jd.lib.cashier.sdk.d.g.g.c cVar) {
        if (cVar == null || paymentChoseHolder == null) {
            return;
        }
        cVar.setActivity(cashierPayActivity);
        cVar.d = aVar.B;
        cVar.a = paymentChoseHolder.from;
        cVar.appId = paymentChoseHolder.appId;
        cVar.f3287c = paymentChoseHolder.channelType;
        cVar.paySign = aVar.f3518l;
        cVar.b = aVar.f3514h;
        cVar.orderId = aVar.f3511e;
        cVar.orderType = aVar.f3515i;
        cVar.orderPrice = aVar.f3516j;
        cVar.orderTypeCode = aVar.f3517k;
        if (!TextUtils.isEmpty(paymentChoseHolder.groupOrders)) {
            cVar.groupOrders = paymentChoseHolder.groupOrders;
        }
        if (!TextUtils.isEmpty(paymentChoseHolder.combinedOrderId)) {
            cVar.combinedOrderId = paymentChoseHolder.combinedOrderId;
        }
        r.b("CashierPayGeneratorHandler", paymentChoseHolder.toString());
    }

    private com.jd.lib.cashier.sdk.d.g.a.c.b c(PaymentChoseHolder paymentChoseHolder) {
        com.jd.lib.cashier.sdk.d.g.a.c.b bVar = new com.jd.lib.cashier.sdk.d.g.a.c.b();
        if (paymentChoseHolder != null) {
            bVar.f3249e = paymentChoseHolder.jumpApp;
            if (!TextUtils.isEmpty(paymentChoseHolder.channelId)) {
                bVar.f3250f = paymentChoseHolder.channelId;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.jdPayChannel)) {
                bVar.f3251g = paymentChoseHolder.jdPayChannel;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.changetag)) {
                bVar.f3252h = paymentChoseHolder.changetag;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.payToken)) {
                bVar.f3253i = paymentChoseHolder.payToken;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.requireUUID)) {
                bVar.f3254j = paymentChoseHolder.requireUUID;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.prizeId)) {
                bVar.f3255k = paymentChoseHolder.prizeId;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.uniqueChannelId)) {
                bVar.f3256l = paymentChoseHolder.uniqueChannelId;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.channelType)) {
                bVar.f3257m = paymentChoseHolder.channelType;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.payMarketingUUID)) {
                bVar.f3258n = paymentChoseHolder.payMarketingUUID;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.channelStatus)) {
                bVar.o = paymentChoseHolder.channelStatus;
            }
        }
        return bVar;
    }

    private com.jd.lib.cashier.sdk.d.g.c.c.b d(PaymentChoseHolder paymentChoseHolder) {
        com.jd.lib.cashier.sdk.d.g.c.c.b bVar = new com.jd.lib.cashier.sdk.d.g.c.c.b();
        if (paymentChoseHolder != null) {
            if (!TextUtils.isEmpty(paymentChoseHolder.combineType)) {
                bVar.y = paymentChoseHolder.combineType;
            }
            bVar.t = paymentChoseHolder.isNewCard;
            if (!TextUtils.isEmpty(paymentChoseHolder.planId)) {
                bVar.f3270g = paymentChoseHolder.planId;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.prizeId)) {
                bVar.f3277n = paymentChoseHolder.prizeId;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.couponId)) {
                bVar.f3271h = paymentChoseHolder.couponId;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.planInfo)) {
                bVar.f3275l = paymentChoseHolder.planInfo;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.bankCode)) {
                bVar.s = paymentChoseHolder.bankCode;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.channelId)) {
                bVar.f3269f = paymentChoseHolder.channelId;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.uniqueChannelId)) {
                bVar.f3268e = paymentChoseHolder.uniqueChannelId;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.activityId)) {
                bVar.f3272i = paymentChoseHolder.activityId;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.requireUUID)) {
                bVar.f3274k = paymentChoseHolder.requireUUID;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.accountCode)) {
                bVar.r = paymentChoseHolder.accountCode;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.productCode)) {
                bVar.u = paymentChoseHolder.productCode;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.channelType)) {
                bVar.o = paymentChoseHolder.channelType;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.bankPlanRate)) {
                bVar.p = paymentChoseHolder.bankPlanRate;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.channelStatus)) {
                bVar.f3273j = paymentChoseHolder.channelStatus;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.payMarketingUUID)) {
                bVar.f3276m = paymentChoseHolder.payMarketingUUID;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.merchantFeeSubSideBy)) {
                bVar.q = paymentChoseHolder.merchantFeeSubSideBy;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.payToken)) {
                bVar.v = paymentChoseHolder.payToken;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.jdPayChannel)) {
                bVar.w = paymentChoseHolder.jdPayChannel;
            }
            if (!TextUtils.isEmpty(paymentChoseHolder.changetag)) {
                bVar.x = paymentChoseHolder.changetag;
            }
            Map<String, String> map = paymentChoseHolder.tradeMap;
            if (map != null && !map.isEmpty()) {
                bVar.z = paymentChoseHolder.tradeMap;
            }
        }
        return bVar;
    }

    private com.jd.lib.cashier.sdk.d.f.a e(com.jd.lib.cashier.sdk.d.g.g.f fVar) {
        if (fVar != null) {
            switch (C0144a.a[fVar.ordinal()]) {
                case 1:
                    return new com.jd.lib.cashier.sdk.d.g.j.a.b();
                case 2:
                    return new com.jd.lib.cashier.sdk.d.g.f.a.b();
                case 3:
                    return new com.jd.lib.cashier.sdk.d.g.h.a.b();
                case 4:
                    return new com.jd.lib.cashier.sdk.d.g.i.a.b();
                case 5:
                    return new com.jd.lib.cashier.sdk.d.g.d.a.b();
                case 6:
                    return new com.jd.lib.cashier.sdk.d.g.c.a.b();
                case 7:
                    return new com.jd.lib.cashier.sdk.d.g.a.a.b();
                default:
                    return null;
            }
        }
        return null;
    }

    private com.jd.lib.cashier.sdk.d.g.g.c f(PaymentChoseHolder paymentChoseHolder, com.jd.lib.cashier.sdk.d.g.g.f fVar) {
        if (fVar != null) {
            switch (C0144a.a[fVar.ordinal()]) {
                case 1:
                    return new com.jd.lib.cashier.sdk.d.g.j.c.b();
                case 2:
                    return new com.jd.lib.cashier.sdk.d.g.f.c.b();
                case 3:
                    return new com.jd.lib.cashier.sdk.d.g.h.c.b();
                case 4:
                    return new com.jd.lib.cashier.sdk.d.g.i.c.b();
                case 5:
                    return new com.jd.lib.cashier.sdk.d.g.d.c.b();
                case 6:
                    return d(paymentChoseHolder);
                case 7:
                    return c(paymentChoseHolder);
                default:
                    return null;
            }
        }
        return null;
    }

    private boolean g() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.a <= 1200) {
            return true;
        }
        this.a = currentTimeMillis;
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00ac, code lost:
        if (r1.equals(com.jingdong.common.cashiernative.CashierPayChannelCode.HUA_WEI_PAY) == false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.jd.lib.cashier.sdk.d.g.g.f h(com.jd.lib.cashier.sdk.h.c.a r6, com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder r7) {
        /*
            Method dump skipped, instructions count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.handler.a.h(com.jd.lib.cashier.sdk.h.c.a, com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder):com.jd.lib.cashier.sdk.d.g.g.f");
    }

    public void a(CashierPayActivity cashierPayActivity, PaymentChoseHolder paymentChoseHolder) {
        if (g() || !g0.a(cashierPayActivity) || paymentChoseHolder == null) {
            return;
        }
        com.jd.lib.cashier.sdk.h.c.a b = ((CashierPayViewModel) ViewModelProviders.of(cashierPayActivity).get(CashierPayViewModel.class)).b();
        com.jd.lib.cashier.sdk.d.g.g.f h2 = h(b, paymentChoseHolder);
        com.jd.lib.cashier.sdk.d.f.a e2 = e(h2);
        com.jd.lib.cashier.sdk.d.g.g.c f2 = f(paymentChoseHolder, h2);
        if (f2 == null || e2 == null) {
            return;
        }
        b(cashierPayActivity, b, paymentChoseHolder, f2);
        e2.e(f2);
    }
}
