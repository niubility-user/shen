package com.jd.lib.cashier.sdk.d.g.d.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.paychannel.medicalpay.entity.MedicalPayEntity;
import com.jd.lib.cashier.sdk.core.utils.o;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.d.g.d.c.b, MedicalPayEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.d.g.d.c.b bVar) {
        if (iHttpSetting == null || bVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platMedicalPay");
        iHttpSetting.putJsonParam("channelCode", bVar.f3287c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public MedicalPayEntity d(String str) {
        MedicalPayEntity medicalPayEntity = !TextUtils.isEmpty(str) ? (MedicalPayEntity) o.a(str, MedicalPayEntity.class) : null;
        return medicalPayEntity != null ? medicalPayEntity : new MedicalPayEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public MedicalPayEntity i(String str) {
        return (MedicalPayEntity) o.a(str, MedicalPayEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
