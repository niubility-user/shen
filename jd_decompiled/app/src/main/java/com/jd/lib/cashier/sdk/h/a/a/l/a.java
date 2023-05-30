package com.jd.lib.cashier.sdk.h.a.a.l;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoCouponResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.h.f.a, BaiTiaoCouponResponse> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(@Nullable IHttpSetting iHttpSetting, @Nullable com.jd.lib.cashier.sdk.h.f.a aVar) {
        if (iHttpSetting == null || aVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platBaitiaoCouponInfo");
        iHttpSetting.putJsonParam("code", aVar.b());
        if (!TextUtils.isEmpty(aVar.d())) {
            iHttpSetting.putJsonParam("sdkToken", aVar.d());
        }
        if (!TextUtils.isEmpty(aVar.c())) {
            iHttpSetting.putJsonParam(PairKey.COMBINE_TYPE, aVar.c());
        }
        if (TextUtils.isEmpty(aVar.a())) {
            return;
        }
        iHttpSetting.putJsonParam("channelId", aVar.a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    @NotNull
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public BaiTiaoCouponResponse d(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            BaiTiaoCouponResponse baiTiaoCouponResponse = (BaiTiaoCouponResponse) o.a(str, BaiTiaoCouponResponse.class);
            return baiTiaoCouponResponse != null ? baiTiaoCouponResponse : new BaiTiaoCouponResponse();
        }
        return new BaiTiaoCouponResponse();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    @Nullable
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public BaiTiaoCouponResponse i(@Nullable String str) {
        return (BaiTiaoCouponResponse) o.a(str, BaiTiaoCouponResponse.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(@Nullable IHttpConfig iHttpConfig) {
    }
}
