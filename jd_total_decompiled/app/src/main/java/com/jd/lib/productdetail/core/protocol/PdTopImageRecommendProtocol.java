package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdTopRecommendEntity;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes15.dex */
public class PdTopImageRecommendProtocol extends PdBaseProtocolLiveData<PdTopRecommendEntity> {
    public RecommendProductListRequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class RecommendProductListRequestParams {
        public String cid1;
        public String cid2;
        public String cid3;
        public String skuId;
        public String storeId;
        public String channelStr = "PRODUCT_DETAIL";
        public String fromName = "MainPhotoRecomendRank";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return "productRecommendRank";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            RecommendProductListRequestParams recommendProductListRequestParams = this.mRequestParams;
            if (recommendProductListRequestParams != null) {
                httpSetting.putJsonParam("cid1", recommendProductListRequestParams.cid1);
                httpSetting.putJsonParam("cid2", this.mRequestParams.cid2);
                httpSetting.putJsonParam("cid3", this.mRequestParams.cid3);
                httpSetting.putJsonParam("storeId", this.mRequestParams.storeId);
                httpSetting.putJsonParam("skuId", this.mRequestParams.skuId);
                httpSetting.putJsonParam("channelStr", this.mRequestParams.channelStr);
                httpSetting.putJsonParam("fromName", this.mRequestParams.fromName);
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
