package com.jd.lib.productdetail.tradein.d;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.estimate.TradeInSaveData;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes16.dex */
public class a extends PdBaseProtocolLiveData<TradeInSaveData> {
    public final Object a;

    public a(Object obj) {
        this.a = obj;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public String getFunctionId() {
        return "barterDelInquiryItem";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        httpSetting.putJsonParam(this.a);
    }
}
