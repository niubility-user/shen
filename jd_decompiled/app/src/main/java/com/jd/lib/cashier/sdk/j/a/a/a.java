package com.jd.lib.cashier.sdk.j.a.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.risk.bean.CashierRiskCheckEntity;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.j.b.a, CashierRiskCheckEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.j.b.a aVar) {
        if (iHttpSetting == null || aVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platJudgeMaliceOrder");
        iHttpSetting.putJsonParam("orderId", aVar.orderId);
        iHttpSetting.putJsonParam("orderDate", aVar.a);
        iHttpSetting.setEffect(1);
        iHttpSetting.setNotifyUser(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CashierRiskCheckEntity d(String str) {
        CashierRiskCheckEntity cashierRiskCheckEntity = !TextUtils.isEmpty(str) ? (CashierRiskCheckEntity) o.a(str, CashierRiskCheckEntity.class) : null;
        return cashierRiskCheckEntity != null ? cashierRiskCheckEntity : new CashierRiskCheckEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CashierRiskCheckEntity i(String str) {
        return (CashierRiskCheckEntity) o.a(str, CashierRiskCheckEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
