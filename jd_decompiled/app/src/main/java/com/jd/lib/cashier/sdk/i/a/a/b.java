package com.jd.lib.cashier.sdk.i.a.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.quickpay.bean.WXPayServiceEntity;

/* loaded from: classes14.dex */
public abstract class b extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.i.b.b, WXPayServiceEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.i.b.b bVar) {
        if (iHttpSetting == null || bVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platWXPay");
        iHttpSetting.putJsonParam("paySourceId", bVar.f3622f);
        iHttpSetting.putJsonParam("back_url", bVar.f3624h);
        iHttpSetting.putJsonParam("fk_aid", bVar.p);
        iHttpSetting.putJsonParam("fk_appId", bVar.f3625i);
        iHttpSetting.putJsonParam("fk_longtitude", bVar.f3630n);
        iHttpSetting.putJsonParam("fk_latitude", bVar.o);
        iHttpSetting.putJsonParam("fk_traceIp", bVar.f3628l);
        iHttpSetting.putJsonParam("fk_terminalType", bVar.f3629m);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public WXPayServiceEntity d(String str) {
        WXPayServiceEntity wXPayServiceEntity = !TextUtils.isEmpty(str) ? (WXPayServiceEntity) o.a(str, WXPayServiceEntity.class) : null;
        return wXPayServiceEntity != null ? wXPayServiceEntity : new WXPayServiceEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public WXPayServiceEntity i(String str) {
        return (WXPayServiceEntity) o.a(str, WXPayServiceEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
