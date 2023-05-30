package com.jd.lib.productdetail.tradein.h;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes16.dex */
public class d extends PdBaseProtocolLiveData<TradeInSelectStoreResp> {
    public final Object a;

    public d(Object obj) {
        this.a = obj;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public String getFunctionId() {
        return "barterStores";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        httpSetting.putJsonParam(this.a);
    }
}
