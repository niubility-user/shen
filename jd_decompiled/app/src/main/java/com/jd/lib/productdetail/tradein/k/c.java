package com.jd.lib.productdetail.tradein.k;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.d.f;
import com.jd.lib.productdetail.tradein.inform.TradeInInformData;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes16.dex */
public class c extends PdBaseProtocolLiveData<TradeInInformData> {
    public final Object a;

    public c(Object obj) {
        this.a = obj;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public String getFunctionId() {
        return "barterNotice";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public boolean onAfterParseData(TradeInInformData tradeInInformData) {
        TradeInInformData.Data data;
        TradeInStep destStep;
        TradeInResultData tradeInResultData;
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo;
        TradeInInformData tradeInInformData2 = tradeInInformData;
        super.onAfterParseData(tradeInInformData2);
        if ((tradeInInformData2 instanceof TradeInInformData) && (data = tradeInInformData2.data) != null && (destStep = data.getDestStep()) != TradeInStep.INFORM) {
            if (destStep == TradeInStep.ESTIMATE) {
                f.a(tradeInInformData2.data.localMachineInfo);
            } else if (destStep == TradeInStep.TRADEIN && (tradeInResultData = tradeInInformData2.data.oldProductListInfo) != null && (tradeInWareCardInfo = tradeInResultData.wareNew) != null) {
                tradeInWareCardInfo.mNewDevice = true;
            }
        }
        return true;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        httpSetting.setUseFastJsonParser(false);
        httpSetting.putJsonParam(this.a);
    }
}
