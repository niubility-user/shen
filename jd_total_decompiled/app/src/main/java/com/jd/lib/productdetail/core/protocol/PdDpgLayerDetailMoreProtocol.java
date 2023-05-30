package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes15.dex */
public class PdDpgLayerDetailMoreProtocol extends PdBaseProtocolLiveData<Object> {
    public PdDpgLayerDetailMoreRequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class PdDpgLayerDetailMoreRequestParams {
        public int pageNo;
        public int pageSize;
        public String shopId;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return "shop_detail";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            PdDpgLayerDetailMoreRequestParams pdDpgLayerDetailMoreRequestParams = this.mRequestParams;
            if (pdDpgLayerDetailMoreRequestParams != null) {
                httpSetting.putJsonParam("shopId", pdDpgLayerDetailMoreRequestParams.shopId);
                httpSetting.putJsonParam("pageNo", Integer.valueOf(this.mRequestParams.pageNo));
                httpSetting.putJsonParam("pageSize", Integer.valueOf(this.mRequestParams.pageSize));
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setPost(false);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
