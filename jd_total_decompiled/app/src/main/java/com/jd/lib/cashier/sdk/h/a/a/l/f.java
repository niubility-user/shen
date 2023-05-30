package com.jd.lib.cashier.sdk.h.a.a.l;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPayPlanResponse;
import com.jingdong.jdsdk.constant.CartConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public abstract class f extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.h.f.f, CreditCardPayPlanResponse> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(@Nullable IHttpSetting iHttpSetting, @Nullable com.jd.lib.cashier.sdk.h.f.f fVar) {
        if (iHttpSetting == null || fVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platCreditCardCalculateRate");
        iHttpSetting.putJsonParam(PairKey.BANK_CODE, fVar.a());
        iHttpSetting.putJsonParam("channelId", fVar.b());
        iHttpSetting.putJsonParam(CartConstant.KEY_OPERATIONTYPE, fVar.f());
        if (!TextUtils.isEmpty(fVar.d())) {
            iHttpSetting.putJsonParam("currentPlan", fVar.d());
        }
        if (!TextUtils.isEmpty(fVar.h())) {
            iHttpSetting.putJsonParam("targetPlan", fVar.h());
        }
        if (fVar.c() != null) {
            iHttpSetting.putJsonParam("currentCoupon", fVar.c());
        }
        if (fVar.g() != null) {
            iHttpSetting.putJsonParam("targetCoupon", fVar.g());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    @NotNull
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CreditCardPayPlanResponse d(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            CreditCardPayPlanResponse creditCardPayPlanResponse = (CreditCardPayPlanResponse) o.a(str, CreditCardPayPlanResponse.class);
            return creditCardPayPlanResponse != null ? creditCardPayPlanResponse : new CreditCardPayPlanResponse();
        }
        return new CreditCardPayPlanResponse();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    @Nullable
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CreditCardPayPlanResponse i(@Nullable String str) {
        return (CreditCardPayPlanResponse) o.a(str, CreditCardPayPlanResponse.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(@Nullable IHttpConfig iHttpConfig) {
    }
}
