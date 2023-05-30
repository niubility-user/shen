package com.jd.lib.cashier.sdk.d.g.f.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.paychannel.octopuspay.entity.OctopusPayEntity;
import com.jd.lib.cashier.sdk.core.utils.o;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.d.g.f.c.b, OctopusPayEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.d.g.f.c.b bVar) {
        if (iHttpSetting == null || bVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platOctopusPay");
        iHttpSetting.putJsonParam("backUrl", bVar.b);
        iHttpSetting.putJsonParam("orderTypeCode", bVar.orderTypeCode);
        if (TextUtils.isEmpty(bVar.d)) {
            return;
        }
        iHttpSetting.putJsonParam("sdkToken", bVar.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public OctopusPayEntity d(String str) {
        OctopusPayEntity octopusPayEntity = !TextUtils.isEmpty(str) ? (OctopusPayEntity) o.a(str, OctopusPayEntity.class) : null;
        return octopusPayEntity != null ? octopusPayEntity : new OctopusPayEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public OctopusPayEntity i(String str) {
        return (OctopusPayEntity) o.a(str, OctopusPayEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
