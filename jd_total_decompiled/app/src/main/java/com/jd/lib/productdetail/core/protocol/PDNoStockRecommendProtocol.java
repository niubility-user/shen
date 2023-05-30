package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.PdNoStockRecommendSame;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class PDNoStockRecommendProtocol extends PdBaseProtocolLiveData<PdNoStockRecommendSame> {
    private static final String FUNCTION_ID = "asynInteface";
    public BusinessParams mParams;

    /* loaded from: classes15.dex */
    public static class BusinessParams {
        public String entityId;
        public String keyword;
        public String oneboxSource;
        public String oneboxType;
        public String skuId = "";
        public String intefaceType = "recommend";
        public String page = "1";
        public String pageSize = "8";

        public HashMap<String, String> toParam() {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("intefaceType", this.intefaceType);
            hashMap.put("skuId", this.skuId);
            hashMap.put("oneboxSource", this.oneboxSource);
            hashMap.put("oneboxType", this.oneboxType);
            hashMap.put("entityId", this.entityId);
            hashMap.put("keyword", this.keyword);
            hashMap.put("page", this.page);
            hashMap.put("pageSize", this.pageSize);
            return hashMap;
        }
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        BusinessParams businessParams = this.mParams;
        if (businessParams != null) {
            httpSetting.putJsonParam(businessParams.toParam());
        }
    }
}
