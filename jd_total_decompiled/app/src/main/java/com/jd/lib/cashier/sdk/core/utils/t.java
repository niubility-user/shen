package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.core.ui.entity.CashierPayItemInfoEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import java.lang.ref.WeakReference;

/* loaded from: classes14.dex */
public class t {
    private WeakReference<Context> a;

    public t(Context context) {
        this.a = new WeakReference<>(context);
    }

    public int a(Payment payment, CashierPayItemInfoEntity cashierPayItemInfoEntity) {
        String str;
        int i2;
        float f2;
        int i3;
        int i4;
        int i5;
        int dip2px;
        if (payment == null || cashierPayItemInfoEntity == null) {
            return 0;
        }
        try {
            WeakReference<Context> weakReference = this.a;
            if (weakReference == null || !(cashierPayItemInfoEntity.moreInfoTipShow || cashierPayItemInfoEntity.preferentiaNumShow)) {
                return 0;
            }
            Context context = weakReference.get();
            int i6 = cashierPayItemInfoEntity.leftAndRightSpace;
            int i7 = cashierPayItemInfoEntity.insideSpace;
            DisplayMetrics i8 = y.i();
            if (i8 == null) {
                return 0;
            }
            int dip2px2 = (i8.widthPixels - (DpiUtil.dip2px(context, i6) * 2)) - (DpiUtil.dip2px(context, i7) * 2);
            String str2 = payment.logo;
            String str3 = payment.statusDesc;
            String str4 = payment.channelNameTail;
            String str5 = payment.channelNameMiddle;
            if (CashierPayChannelCode.JD_PAY_HONEY.equals(payment.code)) {
                str = payment.channelNamePre;
            } else {
                str = payment.channelName;
            }
            int dip2px3 = !TextUtils.isEmpty(str2) ? DpiUtil.dip2px(context, cashierPayItemInfoEntity.logoWidth) : 0;
            if (TextUtils.isEmpty(str)) {
                i2 = 0;
            } else {
                Point d = h0.d(context, str, y.n(cashierPayItemInfoEntity.nameSize), -2, -2, true);
                i2 = d == null ? DpiUtil.dip2px(context, 100.0f) : d.x;
            }
            if (TextUtils.isEmpty(str3)) {
                f2 = 40.0f;
                i3 = 0;
            } else {
                f2 = 40.0f;
                Point d2 = h0.d(context, str3, y.n(cashierPayItemInfoEntity.statusDescSize), -2, -2, true);
                i3 = d2 == null ? DpiUtil.dip2px(context, 40.0f) : d2.x;
            }
            if (TextUtils.isEmpty(str4)) {
                i4 = 0;
            } else {
                Point d3 = h0.d(context, str4, y.n(cashierPayItemInfoEntity.channelNameTailSize), -2, -2, true);
                i4 = d3 == null ? DpiUtil.dip2px(context, f2) : d3.x + DpiUtil.dip2px(context, cashierPayItemInfoEntity.channelNameTailPadding);
            }
            if (TextUtils.isEmpty(str5)) {
                i5 = 0;
            } else {
                Point d4 = h0.d(context, str5, y.n(cashierPayItemInfoEntity.channelNameMiddleSize), -2, -2, true);
                i5 = d4 == null ? DpiUtil.dip2px(context, f2) : d4.x;
            }
            int i9 = cashierPayItemInfoEntity.icon1Width;
            int i10 = cashierPayItemInfoEntity.icon2Width;
            int i11 = dip2px3 + i2 + i3 + i4 + i5 + i9 + i10;
            if (i4 > 0) {
                i11 += DpiUtil.dip2px(context, cashierPayItemInfoEntity.channelNameTailLeftSpace);
            }
            if (i5 > 0) {
                i11 += DpiUtil.dip2px(context, cashierPayItemInfoEntity.channelNameMiddleLeftSpace);
            }
            if (i9 > 0) {
                i11 += DpiUtil.dip2px(context, cashierPayItemInfoEntity.icon1LeftSpace);
            }
            if (i10 > 0) {
                i11 += DpiUtil.dip2px(context, cashierPayItemInfoEntity.icon2LeftSpace);
            }
            int dip2px4 = ((dip2px2 - (i11 + DpiUtil.dip2px(context, cashierPayItemInfoEntity.nameLeftSpace))) - DpiUtil.dip2px(context, cashierPayItemInfoEntity.checkboxWidth)) - DpiUtil.dip2px(context, cashierPayItemInfoEntity.tipRightSpace);
            if (cashierPayItemInfoEntity.moreInfoTipShow) {
                Point d5 = h0.d(context, payment.moreInfoTip, y.n(cashierPayItemInfoEntity.moreInfoTipSize), -2, -2, true);
                if (dip2px4 >= (d5 == null ? DpiUtil.dip2px(context, f2) : d5.x)) {
                    return -2;
                }
                if (dip2px4 > DpiUtil.dip2px(context, 45.0f)) {
                    dip2px = DpiUtil.dip2px(context, 14.0f);
                } else {
                    return DpiUtil.dip2px(context, 45.0f);
                }
            } else {
                String str6 = payment.preferentiaNum;
                com.jd.lib.cashier.sdk.pay.dialog.e eVar = payment.selectedCouponEntity;
                if (eVar != null) {
                    str6 = eVar.getTitleName();
                }
                boolean equals = "1".equals(payment.canSelectCoupon);
                Point d6 = h0.d(context, str6, cashierPayItemInfoEntity.tipNameSize, -2, -2, true);
                int dip2px5 = d6 == null ? DpiUtil.dip2px(context, f2) : d6.x;
                if (equals) {
                    dip2px5 += DpiUtil.dip2px(context, cashierPayItemInfoEntity.tipArrowWidth);
                }
                if (dip2px4 >= dip2px5) {
                    return -2;
                }
                if (dip2px4 > DpiUtil.dip2px(context, 45.0f)) {
                    dip2px = DpiUtil.dip2px(context, 14.0f);
                } else {
                    return DpiUtil.dip2px(context, 45.0f);
                }
            }
            return dip2px4 - dip2px;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
