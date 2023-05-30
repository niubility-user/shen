package com.jd.lib.cashier.sdk.h.a.a.l;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponResponse;

/* loaded from: classes14.dex */
public abstract class g extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.h.f.g, CyberMoneyCouponResponse> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.h.f.g gVar) {
        if (iHttpSetting == null || gVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platCyberMoneyCouponInfo");
        iHttpSetting.putJsonParam("backUrl", gVar.b);
        iHttpSetting.putJsonParam("channelId", gVar.f3556e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CyberMoneyCouponResponse d(String str) {
        CyberMoneyCouponResponse cyberMoneyCouponResponse = !TextUtils.isEmpty(str) ? (CyberMoneyCouponResponse) o.a(str, CyberMoneyCouponResponse.class) : null;
        return cyberMoneyCouponResponse != null ? cyberMoneyCouponResponse : new CyberMoneyCouponResponse();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CyberMoneyCouponResponse i(String str) {
        return (CyberMoneyCouponResponse) o.a(str, CyberMoneyCouponResponse.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
