package com.jd.lib.cashier.sdk.e.a.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditPayEntity;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.e.d.a, CreditPayEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.e.d.a aVar) {
        if (iHttpSetting == null || aVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platCreditPayIndex");
        iHttpSetting.setEffect(0);
        iHttpSetting.putJsonParam("orderTypeCode", aVar.orderTypeCode);
        iHttpSetting.putJsonParam("payablePrice", aVar.a);
        iHttpSetting.putJsonParam("paySourceId", aVar.b);
        iHttpSetting.putJsonParam("back_url", aVar.f3328c);
        iHttpSetting.putJsonParam("fk_aid", aVar.f3333i);
        iHttpSetting.putJsonParam("fk_appId", aVar.d);
        iHttpSetting.putJsonParam("fk_longtitude", aVar.f3331g);
        iHttpSetting.putJsonParam("fk_latitude", aVar.f3332h);
        iHttpSetting.putJsonParam("fk_traceIp", aVar.f3329e);
        iHttpSetting.putJsonParam("fk_terminalType", aVar.f3330f);
        iHttpSetting.setNotifyUser(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CreditPayEntity d(String str) {
        CreditPayEntity creditPayEntity = !TextUtils.isEmpty(str) ? (CreditPayEntity) o.a(str, CreditPayEntity.class) : null;
        return creditPayEntity != null ? creditPayEntity : new CreditPayEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CreditPayEntity i(String str) {
        return (CreditPayEntity) o.a(str, CreditPayEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
