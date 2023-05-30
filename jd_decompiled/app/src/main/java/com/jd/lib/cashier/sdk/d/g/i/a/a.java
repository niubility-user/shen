package com.jd.lib.cashier.sdk.d.g.i.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.paychannel.unionpay.entity.UnionPayEntity;
import com.jd.lib.cashier.sdk.core.utils.o;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.d.g.i.c.b, UnionPayEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.d.g.i.c.b bVar) {
        if (iHttpSetting == null || bVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platUnionPay");
        iHttpSetting.putJsonParam("backUrl", bVar.b);
        iHttpSetting.putJsonParam("channelCode", bVar.f3287c);
        if (TextUtils.isEmpty(bVar.d)) {
            return;
        }
        iHttpSetting.putJsonParam("sdkToken", bVar.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public UnionPayEntity d(String str) {
        UnionPayEntity unionPayEntity = !TextUtils.isEmpty(str) ? (UnionPayEntity) o.a(str, UnionPayEntity.class) : null;
        return unionPayEntity != null ? unionPayEntity : new UnionPayEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public UnionPayEntity i(String str) {
        return (UnionPayEntity) o.a(str, UnionPayEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
