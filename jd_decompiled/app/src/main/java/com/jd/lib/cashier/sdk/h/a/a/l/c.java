package com.jd.lib.cashier.sdk.h.a.a.l;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.pay.bean.BankCouponResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public abstract class c extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.h.f.c, BankCouponResponse> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(@Nullable IHttpSetting iHttpSetting, @Nullable com.jd.lib.cashier.sdk.h.f.c cVar) {
        if (iHttpSetting == null || cVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platBankCardCouponInfo");
        iHttpSetting.putJsonParam("channelId", cVar.a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    @NotNull
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public BankCouponResponse d(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            BankCouponResponse bankCouponResponse = (BankCouponResponse) o.a(str, BankCouponResponse.class);
            return bankCouponResponse != null ? bankCouponResponse : new BankCouponResponse();
        }
        return new BankCouponResponse();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    @Nullable
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public BankCouponResponse i(@Nullable String str) {
        return (BankCouponResponse) o.a(str, BankCouponResponse.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(@Nullable IHttpConfig iHttpConfig) {
    }
}
