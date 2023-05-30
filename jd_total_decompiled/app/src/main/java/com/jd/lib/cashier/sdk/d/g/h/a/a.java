package com.jd.lib.cashier.sdk.d.g.h.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.paychannel.qqwallet.entity.QQWalletPayEntity;
import com.jd.lib.cashier.sdk.core.utils.o;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.d.g.h.c.b, QQWalletPayEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.d.g.h.c.b bVar) {
        if (iHttpSetting == null || bVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platQQWalletPay");
        iHttpSetting.putJsonParam("backUrl", bVar.b);
        if (TextUtils.isEmpty(bVar.d)) {
            return;
        }
        iHttpSetting.putJsonParam("sdkToken", bVar.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public QQWalletPayEntity d(String str) {
        QQWalletPayEntity qQWalletPayEntity = !TextUtils.isEmpty(str) ? (QQWalletPayEntity) o.a(str, QQWalletPayEntity.class) : null;
        return qQWalletPayEntity != null ? qQWalletPayEntity : new QQWalletPayEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public QQWalletPayEntity i(String str) {
        return (QQWalletPayEntity) o.a(str, QQWalletPayEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
