package com.jd.lib.cashier.sdk.a.a.a;

import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.BTSkuCalculateEntity;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.RequestParamBTSkuCalculateRate;
import com.jd.lib.cashier.sdk.btcombinationpay.bean.RequestParamSkuPlanInfo;
import com.jd.lib.cashier.sdk.core.utils.o;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class a extends com.jd.lib.cashier.sdk.d.f.a<RequestParamBTSkuCalculateRate, BTSkuCalculateEntity> {
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public void c(IHttpSetting iHttpSetting, RequestParamBTSkuCalculateRate requestParamBTSkuCalculateRate) {
        if (iHttpSetting == null || requestParamBTSkuCalculateRate == null) {
            return;
        }
        iHttpSetting.setFunctionId("platBaiTiaoSkuCalculateRate");
        List<RequestParamSkuPlanInfo> list = requestParamBTSkuCalculateRate.selectedPlanList;
        if (list != null) {
            iHttpSetting.putJsonParam("selectedPlanList", list);
        }
        if (TextUtils.isEmpty(requestParamBTSkuCalculateRate.operationFlag)) {
            return;
        }
        iHttpSetting.putJsonParam("operationFlag", requestParamBTSkuCalculateRate.operationFlag);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public BTSkuCalculateEntity d(String str) {
        BTSkuCalculateEntity bTSkuCalculateEntity = !TextUtils.isEmpty(str) ? (BTSkuCalculateEntity) o.a(str, BTSkuCalculateEntity.class) : null;
        return bTSkuCalculateEntity != null ? bTSkuCalculateEntity : new BTSkuCalculateEntity();
    }

    @Override // com.jd.lib.cashier.sdk.d.f.a
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public BTSkuCalculateEntity i(String str) {
        return (BTSkuCalculateEntity) o.a(str, BTSkuCalculateEntity.class);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onReady(IHttpConfig iHttpConfig) {
    }
}
