package com.jd.lib.cashier.sdk.f.a.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.freindpay.bean.CashierFriendPayEntity;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.f.e.a, CashierFriendPayEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.f.e.a aVar) {
        if (iHttpSetting == null || aVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platDFPay");
        iHttpSetting.putJsonParam("backUrl", aVar.a);
        iHttpSetting.setEffect(1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CashierFriendPayEntity d(String str) {
        CashierFriendPayEntity cashierFriendPayEntity = !TextUtils.isEmpty(str) ? (CashierFriendPayEntity) o.a(str, CashierFriendPayEntity.class) : null;
        return cashierFriendPayEntity != null ? cashierFriendPayEntity : new CashierFriendPayEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CashierFriendPayEntity i(String str) {
        return (CashierFriendPayEntity) o.a(str, CashierFriendPayEntity.class);
    }
}
