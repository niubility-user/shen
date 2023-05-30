package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.SkinRecommendInfoEntity;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes15.dex */
public class SkinRecommendInfoProtocol extends PdBaseProtocolLiveData<SkinRecommendInfoEntity> {
    public SkinRecommendInfoRequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class SkinRecommendInfoRequestParams {
        public String caseNo;
        public String lat;
        public String lon;
        public Long skuId;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return "querySkinRecommendInfo";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            SkinRecommendInfoRequestParams skinRecommendInfoRequestParams = this.mRequestParams;
            if (skinRecommendInfoRequestParams != null) {
                httpSetting.putJsonParam("caseNo", skinRecommendInfoRequestParams.caseNo);
                httpSetting.putJsonParam("skuId", this.mRequestParams.skuId);
                httpSetting.putJsonParam(JDMtaUtils.LON, this.mRequestParams.lon);
                httpSetting.putJsonParam("lat", this.mRequestParams.lat);
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
