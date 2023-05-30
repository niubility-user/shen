package com.jd.lib.cashier.sdk.h.a.a.l;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.pay.bean.OctopusRateResponse;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public abstract class h extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.h.f.h, OctopusRateResponse> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(@Nullable IHttpSetting iHttpSetting, @Nullable com.jd.lib.cashier.sdk.h.f.h hVar) {
        if (iHttpSetting == null || hVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platOctopusPayRate");
        iHttpSetting.putJsonParam("backUrl", hVar.b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    @NotNull
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public OctopusRateResponse d(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            OctopusRateResponse octopusRateResponse = (OctopusRateResponse) o.a(str, OctopusRateResponse.class);
            return octopusRateResponse != null ? octopusRateResponse : new OctopusRateResponse();
        }
        return new OctopusRateResponse();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    @NotNull
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public OctopusRateResponse i(@Nullable String str) {
        Object a = o.a(str, OctopusRateResponse.class);
        Intrinsics.checkExpressionValueIsNotNull(a, "CashierJsonParser.parseJ\u2026RateResponse::class.java)");
        return (OctopusRateResponse) a;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(@Nullable IHttpConfig iHttpConfig) {
    }
}
