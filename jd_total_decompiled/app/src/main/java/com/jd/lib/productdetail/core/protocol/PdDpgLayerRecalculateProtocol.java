package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSON;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdDpgLayerRecalculateProtocol extends PdBaseProtocolLiveData<Object> {
    public PdDpgLayerRecalculateRequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class PdDpgLayerRecalculateRequestParams {
        public String matchId;
        public String function = "dpg_detail";
        public List<SkuInfoParam> skuInfoParamList = new ArrayList();
    }

    /* loaded from: classes15.dex */
    public static class SkuInfoParam {
        public int num;
        public String skuId;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return "dpg_recalculate";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            PdDpgLayerRecalculateRequestParams pdDpgLayerRecalculateRequestParams = this.mRequestParams;
            if (pdDpgLayerRecalculateRequestParams != null) {
                httpSetting.putJsonParam("matchId", pdDpgLayerRecalculateRequestParams.matchId);
                httpSetting.putJsonParam("function", this.mRequestParams.function);
                httpSetting.putJsonParam("skuInfoParamList", JDJSON.parseArray(JDJSON.toJSONString(this.mRequestParams.skuInfoParamList)));
                httpSetting.setPost(false);
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
