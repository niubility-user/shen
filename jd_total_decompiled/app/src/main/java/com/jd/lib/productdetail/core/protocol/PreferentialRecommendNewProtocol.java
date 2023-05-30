package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Map;
import java.util.Set;

/* loaded from: classes15.dex */
public class PreferentialRecommendNewProtocol extends PdBaseProtocolLiveData<Object> {
    public RecommendProductListRequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class RecommendProductListRequestParams {
        public String area;
        public Map<String, Object> cateParam;
        public Set<Integer> fieldSet;
        public String mSkuId;
        public String pageId;
        public String pid;
        public String channel = "0";
        public int skuSize = 16;
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
        return "obtainGrowingInfo";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            RecommendProductListRequestParams recommendProductListRequestParams = this.mRequestParams;
            if (recommendProductListRequestParams != null) {
                httpSetting.putJsonParam("pid", recommendProductListRequestParams.pid);
                httpSetting.putJsonParam("pageId", this.mRequestParams.pageId);
                httpSetting.putJsonParam("channel", this.mRequestParams.channel);
                httpSetting.putJsonParam("area", this.mRequestParams.area);
                httpSetting.putJsonParam("mSkuId", this.mRequestParams.mSkuId);
                httpSetting.putJsonParam("fieldSet", this.mRequestParams.fieldSet);
                int i2 = this.mRequestParams.skuSize;
                if (i2 > 0) {
                    httpSetting.putJsonParam("skuSize", Integer.valueOf(i2));
                }
                httpSetting.putJsonParam("cateParam", this.mRequestParams.cateParam);
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
