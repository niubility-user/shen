package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes15.dex */
public class PdDpgLayerDetailProtocol extends PdBaseProtocolLiveData<Object> {
    public PdDpgLayerRequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class PdDpgLayerRequestParams {
        public String area;
        public String matchId;
        public String skuId;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return "dpg_detail";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            PdDpgLayerRequestParams pdDpgLayerRequestParams = this.mRequestParams;
            if (pdDpgLayerRequestParams != null) {
                httpSetting.putJsonParam("matchId", pdDpgLayerRequestParams.matchId);
                httpSetting.putJsonParam("skuId", this.mRequestParams.skuId);
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
