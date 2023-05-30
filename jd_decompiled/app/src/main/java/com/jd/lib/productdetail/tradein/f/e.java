package com.jd.lib.productdetail.tradein.f;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes16.dex */
public class e extends PdBaseProtocolLiveData<TradeInResultData> {
    public final Object a;

    public e(Object obj) {
        this.a = obj;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public String getFunctionId() {
        return "barterDetail";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public boolean onAfterParseData(TradeInResultData tradeInResultData) {
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo;
        TradeInResultData tradeInResultData2 = tradeInResultData;
        if (tradeInResultData2 != null && (tradeInWareCardInfo = tradeInResultData2.wareNew) != null) {
            tradeInWareCardInfo.mNewDevice = true;
        }
        return super.onAfterParseData(tradeInResultData2);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        httpSetting.putJsonParam(this.a);
    }
}
