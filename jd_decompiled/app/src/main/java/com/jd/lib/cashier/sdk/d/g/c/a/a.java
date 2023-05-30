package com.jd.lib.cashier.sdk.d.g.c.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.paychannel.jdpay.entity.JDPayPaymentACCEntity;
import com.jd.lib.cashier.sdk.core.utils.o;
import java.util.Map;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.d.g.c.c.b, JDPayPaymentACCEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.d.g.c.c.b bVar) {
        if (iHttpSetting == null || bVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platJDPayAcc");
        if (!TextUtils.isEmpty(bVar.y)) {
            iHttpSetting.putJsonParam(PairKey.COMBINE_TYPE, bVar.y);
        }
        iHttpSetting.putJsonParam("activityId", bVar.f3272i);
        iHttpSetting.putJsonParam(PairKey.BANK_CODE, bVar.s);
        iHttpSetting.putJsonParam("backUrl", bVar.b);
        iHttpSetting.putJsonParam(PairKey.BANK_PLAN_RATE, bVar.p);
        iHttpSetting.putJsonParam(PairKey.CHANNEL_STATUS, bVar.f3273j);
        iHttpSetting.putJsonParam("channelId", bVar.f3269f);
        iHttpSetting.putJsonParam(PairKey.UNIQUE_CHANNEL_ID, bVar.f3268e);
        iHttpSetting.putJsonParam(PairKey.CHANNEL_TYPE, bVar.o);
        iHttpSetting.putJsonParam(PairKey.IS_NEW_CARD, Boolean.valueOf(bVar.t));
        iHttpSetting.putJsonParam(PairKey.ACCOUNT_CODE, bVar.r);
        iHttpSetting.putJsonParam("couponId", bVar.f3271h);
        iHttpSetting.putJsonParam(PairKey.PLAN_ID, bVar.f3270g);
        iHttpSetting.putJsonParam(PairKey.PLAN_INFO, bVar.f3275l);
        iHttpSetting.putJsonParam(PairKey.PRIZE_ID, bVar.f3277n);
        iHttpSetting.putJsonParam(PairKey.REQUIRE_UUID, bVar.f3274k);
        iHttpSetting.putJsonParam("productCode", bVar.u);
        iHttpSetting.putJsonParam(PairKey.PAY_MARKETING_UUID, bVar.f3276m);
        iHttpSetting.putJsonParam(PairKey.MER_CHANT_FEE_SUB_SIDE_BY, bVar.q);
        iHttpSetting.putJsonParam(PairKey.PAY_TOKEN, bVar.v);
        iHttpSetting.putJsonParam("jdPayChannel", bVar.w);
        if (!TextUtils.isEmpty(bVar.d)) {
            iHttpSetting.putJsonParam("sdkToken", bVar.d);
        }
        if (!TextUtils.isEmpty(bVar.groupOrders)) {
            iHttpSetting.putJsonParam(PairKey.GROUP_ORDERS, bVar.groupOrders);
        }
        Map<String, String> map = bVar.z;
        if (map != null && !map.isEmpty()) {
            iHttpSetting.putJsonParam(PairKey.TRADE_MAP, bVar.z);
        }
        if (TextUtils.isEmpty(bVar.A)) {
            return;
        }
        iHttpSetting.putJsonParam("selectedSplitSkuPlanInfoList", bVar.A);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public JDPayPaymentACCEntity d(String str) {
        JDPayPaymentACCEntity jDPayPaymentACCEntity = !TextUtils.isEmpty(str) ? (JDPayPaymentACCEntity) o.a(str, JDPayPaymentACCEntity.class) : null;
        return jDPayPaymentACCEntity != null ? jDPayPaymentACCEntity : new JDPayPaymentACCEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public JDPayPaymentACCEntity i(String str) {
        return (JDPayPaymentACCEntity) o.a(str, JDPayPaymentACCEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
