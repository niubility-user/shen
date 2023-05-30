package com.jd.lib.cashier.sdk.h.a.a.l;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanResponse;
import com.jingdong.jdsdk.constant.CartConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public abstract class b extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.h.f.b, BaiTiaoPayPlanResponse> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(@Nullable IHttpSetting iHttpSetting, @Nullable com.jd.lib.cashier.sdk.h.f.b bVar) {
        if (iHttpSetting == null || bVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platBaiTiaoCalculateRate");
        iHttpSetting.putJsonParam("code", bVar.b());
        if (!TextUtils.isEmpty(bVar.j())) {
            iHttpSetting.putJsonParam("showPlans", bVar.j());
        }
        iHttpSetting.putJsonParam("firstQuery", bVar.f());
        iHttpSetting.putJsonParam(CartConstant.KEY_OPERATIONTYPE, bVar.g());
        if (bVar.d() != null) {
            iHttpSetting.putJsonParam("currentCoupon", bVar.d());
        }
        if (bVar.k() != null) {
            iHttpSetting.putJsonParam("targetCoupon", bVar.k());
        }
        if (!TextUtils.isEmpty(bVar.c())) {
            iHttpSetting.putJsonParam(PairKey.COMBINE_TYPE, bVar.c());
        }
        iHttpSetting.putJsonParam("currentPlan", bVar.e());
        iHttpSetting.putJsonParam("targetPlan", bVar.l());
        iHttpSetting.putJsonParam("planRate", bVar.h());
        if (!TextUtils.isEmpty(bVar.i())) {
            iHttpSetting.putJsonParam("sdkToken", bVar.i());
        }
        if (TextUtils.isEmpty(bVar.a())) {
            return;
        }
        iHttpSetting.putJsonParam("channelId", bVar.a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    @NotNull
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public BaiTiaoPayPlanResponse d(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            BaiTiaoPayPlanResponse baiTiaoPayPlanResponse = (BaiTiaoPayPlanResponse) o.a(str, BaiTiaoPayPlanResponse.class);
            return baiTiaoPayPlanResponse != null ? baiTiaoPayPlanResponse : new BaiTiaoPayPlanResponse();
        }
        return new BaiTiaoPayPlanResponse();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    @Nullable
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public BaiTiaoPayPlanResponse i(@Nullable String str) {
        return (BaiTiaoPayPlanResponse) o.a(str, BaiTiaoPayPlanResponse.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(@Nullable IHttpConfig iHttpConfig) {
    }
}
