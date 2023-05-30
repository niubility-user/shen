package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Set;

/* loaded from: classes15.dex */
public class PreferentialRecommendProtocol extends PdBaseProtocolLiveData<Object> {
    public RecommendProductListRequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class RecommendProductListRequestParams {
        public String area;
        public String asyncLoadingCate3Id;
        public int cateBottomSkuSize;
        public int cateTabBottomSize;
        public Set<Integer> fieldSet;
        public String mSkuId;
        public String pageId;
        public String pid;
        public String channel = "0";
        public int minSkuSize = 6;
        public int maxSkuSize = 20;
        public int cateTabMaxSize = 8;
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
        return "obtainProductInfo";
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
                httpSetting.putJsonParam("cateTabMaxSize", Integer.valueOf(this.mRequestParams.cateTabMaxSize));
                httpSetting.putJsonParam("fieldSet", this.mRequestParams.fieldSet);
                if (!TextUtils.isEmpty(this.mRequestParams.asyncLoadingCate3Id)) {
                    httpSetting.putJsonParam("asyncLoadingCate3Id", this.mRequestParams.asyncLoadingCate3Id);
                }
                int i2 = this.mRequestParams.maxSkuSize;
                if (i2 > 0) {
                    httpSetting.putJsonParam("maxSkuSize", Integer.valueOf(i2));
                }
                int i3 = this.mRequestParams.minSkuSize;
                if (i3 > 0) {
                    httpSetting.putJsonParam("minSkuSize", Integer.valueOf(i3));
                }
                int i4 = this.mRequestParams.cateBottomSkuSize;
                if (i4 > 0) {
                    httpSetting.putJsonParam("cateBottomSkuSize", Integer.valueOf(i4));
                }
                int i5 = this.mRequestParams.cateTabBottomSize;
                if (i5 > 0) {
                    httpSetting.putJsonParam("cateTabBottomSize", Integer.valueOf(i5));
                }
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
