package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes15.dex */
public class PdXpTrailProtocol extends PdBaseProtocolLiveData<Object> {
    public RequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class RequestParams {
        public long id;
        public int source = 3;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return "applyForSoa";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            RequestParams requestParams = this.mRequestParams;
            if (requestParams != null) {
                httpSetting.putJsonParam("id", Long.valueOf(requestParams.id));
                httpSetting.putJsonParam("source", Integer.valueOf(this.mRequestParams.source));
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
