package com.jd.lib.cashier.sdk.d.g.j.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.paychannel.wxpay.entity.WXPayEntity;
import com.jd.lib.cashier.sdk.core.utils.o;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.d.g.j.c.b, WXPayEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.d.g.j.c.b bVar) {
        if (iHttpSetting == null || bVar == null) {
            return;
        }
        iHttpSetting.putJsonParam("code", bVar.f3287c);
        if (TextUtils.equals(bVar.f3287c, "WECHATAPP")) {
            iHttpSetting.setFunctionId("platPayDollarPay");
        } else {
            iHttpSetting.setFunctionId("platWXPay");
        }
        iHttpSetting.putJsonParam("backUrl", bVar.b);
        if (!TextUtils.isEmpty(bVar.d)) {
            iHttpSetting.putJsonParam("sdkToken", bVar.d);
        }
        if (TextUtils.isEmpty(bVar.groupOrders)) {
            return;
        }
        iHttpSetting.putJsonParam(PairKey.GROUP_ORDERS, bVar.groupOrders);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public WXPayEntity d(String str) {
        WXPayEntity wXPayEntity = !TextUtils.isEmpty(str) ? (WXPayEntity) o.a(str, WXPayEntity.class) : null;
        return wXPayEntity != null ? wXPayEntity : new WXPayEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public WXPayEntity i(String str) {
        return (WXPayEntity) o.a(str, WXPayEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
