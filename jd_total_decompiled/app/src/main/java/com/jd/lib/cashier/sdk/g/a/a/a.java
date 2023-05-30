package com.jd.lib.cashier.sdk.g.a.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.freindpaydialog.bean.CashierFriendPayDialogEntity;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.g.f.a, CashierFriendPayDialogEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.g.f.a aVar) {
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
    public CashierFriendPayDialogEntity d(String str) {
        CashierFriendPayDialogEntity cashierFriendPayDialogEntity = !TextUtils.isEmpty(str) ? (CashierFriendPayDialogEntity) o.a(str, CashierFriendPayDialogEntity.class) : null;
        return cashierFriendPayDialogEntity != null ? cashierFriendPayDialogEntity : new CashierFriendPayDialogEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CashierFriendPayDialogEntity i(String str) {
        return (CashierFriendPayDialogEntity) o.a(str, CashierFriendPayDialogEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
