package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes15.dex */
public class PdRankRecommendProtecal extends PdBaseProtocolLiveData<Object> {
    public RecommendProductListRequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class RecommendProductListRequestParams {
        public String cid1;
        public String cid2;
        public String cid3;
        public String fromName;
        public int queryType;
        public String rankIdAndType;
        public int sceneId;
        public String skuId;
        public String storeId;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void dealJsonData(HttpResponse httpResponse, ExceptionReporter exceptionReporter) {
        JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
        if (fastJsonObject != null) {
            this.mResult.postValue(new PdBaseProtocolLiveData.Result(PdBaseProtocolLiveData.Result.DataStatus.SUCCESS, fastJsonObject, null));
            return;
        }
        exceptionReporter.reportHttpBusinessException(httpResponse);
        this.mResult.postValue(new PdBaseProtocolLiveData.Result(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, null));
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return "getProductRankLandingInfo";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            if (this.mRequestParams != null) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("rankIdAndType", (Object) this.mRequestParams.rankIdAndType);
                jDJSONObject.put("queryType", (Object) Integer.valueOf(this.mRequestParams.queryType));
                jDJSONObject.put("skuId", (Object) this.mRequestParams.skuId);
                jDJSONObject.put("storeId", (Object) this.mRequestParams.storeId);
                jDJSONObject.put("cid1", (Object) this.mRequestParams.cid1);
                jDJSONObject.put("cid2", (Object) this.mRequestParams.cid2);
                jDJSONObject.put("cid3", (Object) this.mRequestParams.cid3);
                jDJSONObject.put("fromName", (Object) this.mRequestParams.fromName);
                jDJSONObject.put("sceneId", (Object) Integer.valueOf(this.mRequestParams.sceneId));
                httpSetting.putJsonParam("rankFloorAndDetailQuery", jDJSONObject);
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
