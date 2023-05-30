package com.jd.lib.cashier.sdk.b.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.productdetail.core.protocol.PdLVBody;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.b.f.a, CashierGetSuccessUrlEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.b.f.a aVar) {
        if (iHttpSetting == null || aVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platPayResult");
        iHttpSetting.setEffect(1);
        iHttpSetting.putJsonParam("version9Flag", "0");
        iHttpSetting.putJsonParam("channelCode", aVar.a);
        iHttpSetting.putJsonParam("payStatusReqFlag", aVar.b);
        iHttpSetting.putJsonParam(PdLVBody.LATITUDE, com.jd.lib.cashier.sdk.b.e.a.a().b());
        iHttpSetting.putJsonParam(PdLVBody.LONGITUDE, com.jd.lib.cashier.sdk.b.e.a.a().c());
        int i2 = aVar.d;
        if (i2 > 0) {
            iHttpSetting.setCallTimeOut(i2);
        }
        if (TextUtils.isEmpty(aVar.f2872c)) {
            return;
        }
        iHttpSetting.putJsonParam("octopusPayId", aVar.f2872c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CashierGetSuccessUrlEntity d(String str) {
        return new CashierGetSuccessUrlEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CashierGetSuccessUrlEntity i(String str) {
        return (CashierGetSuccessUrlEntity) o.a(str, CashierGetSuccessUrlEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
