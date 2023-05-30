package com.jd.lib.cashier.sdk.i.a.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.quickpay.bean.JDPayServiceEntity;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.i.b.a, JDPayServiceEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.i.b.a aVar) {
        if (iHttpSetting == null || aVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platJDPayAcc");
        if (!TextUtils.isEmpty(aVar.b)) {
            iHttpSetting.putJsonParam("back_url", aVar.b);
        }
        if (!TextUtils.isEmpty(aVar.a)) {
            iHttpSetting.putJsonParam("paySourceId", aVar.a);
        }
        if (!TextUtils.isEmpty(aVar.f3614i)) {
            iHttpSetting.putJsonParam("channelId", aVar.f3614i);
        }
        if (!TextUtils.isEmpty(aVar.u)) {
            iHttpSetting.putJsonParam("channelCode", aVar.u);
        }
        if (!TextUtils.isEmpty(aVar.f3615j)) {
            iHttpSetting.putJsonParam(PairKey.CHANNEL_TYPE, aVar.f3615j);
        }
        if (!TextUtils.isEmpty(aVar.f3616k)) {
            iHttpSetting.putJsonParam(PairKey.REQUIRE_UUID, aVar.f3616k);
        }
        if (!TextUtils.isEmpty(aVar.f3617l)) {
            iHttpSetting.putJsonParam("jdPayChannel", aVar.f3617l);
        }
        if (!TextUtils.isEmpty(aVar.r)) {
            iHttpSetting.putJsonParam(PairKey.PRE_PAY_SOURCE, aVar.r);
        }
        if (!TextUtils.isEmpty(aVar.f3618m)) {
            iHttpSetting.putJsonParam(PairKey.PLAN_ID, aVar.f3618m);
        }
        if (!TextUtils.isEmpty(aVar.f3619n)) {
            iHttpSetting.putJsonParam(PairKey.PLAN_INFO, aVar.f3619n);
        }
        if (!TextUtils.isEmpty(aVar.p)) {
            iHttpSetting.putJsonParam("activityId", aVar.p);
        }
        if (!TextUtils.isEmpty(aVar.o)) {
            iHttpSetting.putJsonParam("couponId", aVar.o);
        }
        if (!TextUtils.isEmpty(aVar.q)) {
            iHttpSetting.putJsonParam(PairKey.PAY_MARKETING_UUID, aVar.q);
        }
        if (!TextUtils.isEmpty(aVar.s)) {
            iHttpSetting.putJsonParam(PairKey.PAY_TOKEN, aVar.s);
        }
        if (!TextUtils.isEmpty(aVar.t)) {
            iHttpSetting.putJsonParam("couponToken", aVar.t);
        }
        iHttpSetting.putJsonParam("fk_aid", aVar.f3613h);
        iHttpSetting.putJsonParam("fk_appId", aVar.f3609c);
        iHttpSetting.putJsonParam("fk_longtitude", aVar.f3611f);
        iHttpSetting.putJsonParam("fk_latitude", aVar.f3612g);
        iHttpSetting.putJsonParam("fk_traceIp", aVar.d);
        iHttpSetting.putJsonParam("fk_terminalType", aVar.f3610e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public JDPayServiceEntity d(String str) {
        JDPayServiceEntity jDPayServiceEntity = !TextUtils.isEmpty(str) ? (JDPayServiceEntity) o.a(str, JDPayServiceEntity.class) : null;
        return jDPayServiceEntity != null ? jDPayServiceEntity : new JDPayServiceEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public JDPayServiceEntity i(String str) {
        return (JDPayServiceEntity) o.a(str, JDPayServiceEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
