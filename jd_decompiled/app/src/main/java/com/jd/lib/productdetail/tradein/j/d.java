package com.jd.lib.productdetail.tradein.j;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysResp;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes16.dex */
public class d extends PdBaseProtocolLiveData<TradeInTradeWaysResp> {
    public final Object a;

    public d(Object obj) {
        this.a = obj;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public String getFunctionId() {
        return "barterTradeMode";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        httpSetting.putJsonParam(this.a);
    }
}
