package com.jd.lib.cashier.sdk.d.g.a.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.paychannel.cybermoneypay.entity.CyberMoneyPayEntity;
import com.jd.lib.cashier.sdk.core.utils.o;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<com.jd.lib.cashier.sdk.d.g.a.c.b, CyberMoneyPayEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, com.jd.lib.cashier.sdk.d.g.a.c.b bVar) {
        if (iHttpSetting == null || bVar == null) {
            return;
        }
        iHttpSetting.setFunctionId("platCyberMoneyPay");
        iHttpSetting.putJsonParam("channelId", bVar.f3250f);
        iHttpSetting.putJsonParam("channelCode", bVar.f3287c);
        iHttpSetting.putJsonParam("payType", "app");
        iHttpSetting.putJsonParam("pageBackUrl", "dcep");
        iHttpSetting.putJsonParam("jumpApp", bVar.f3249e);
        iHttpSetting.putJsonParam(PairKey.PAY_TOKEN, bVar.f3253i);
        iHttpSetting.putJsonParam(PairKey.REQUIRE_UUID, bVar.f3254j);
        iHttpSetting.putJsonParam(PairKey.CHANNEL_TYPE, bVar.f3257m);
        iHttpSetting.putJsonParam("jdPayChannel", bVar.f3251g);
        iHttpSetting.putJsonParam(PairKey.CHANNEL_STATUS, bVar.o);
        iHttpSetting.putJsonParam(PairKey.PAY_MARKETING_UUID, bVar.f3258n);
        iHttpSetting.putJsonParam(PairKey.PRIZE_ID, bVar.f3255k);
        iHttpSetting.putJsonParam(PairKey.UNIQUE_CHANNEL_ID, bVar.f3256l);
        if (TextUtils.isEmpty(bVar.d)) {
            return;
        }
        iHttpSetting.putJsonParam("sdkToken", bVar.d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CyberMoneyPayEntity d(String str) {
        CyberMoneyPayEntity cyberMoneyPayEntity = !TextUtils.isEmpty(str) ? (CyberMoneyPayEntity) o.a(str, CyberMoneyPayEntity.class) : null;
        return cyberMoneyPayEntity != null ? cyberMoneyPayEntity : new CyberMoneyPayEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CyberMoneyPayEntity i(String str) {
        return (CyberMoneyPayEntity) o.a(str, CyberMoneyPayEntity.class);
    }
}
