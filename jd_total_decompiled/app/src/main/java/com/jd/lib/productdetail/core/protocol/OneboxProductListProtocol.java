package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdOneboxProductListInfo;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class OneboxProductListProtocol extends PdBaseProtocolLiveData<PdOneboxProductListInfo> {
    public OneboxProductListRequestParams mRequestParams;

    /* loaded from: classes15.dex */
    public static class OneboxProductListRequestParams {
        public String bodyKey;
        public String bodyValue;
        public boolean isFilterBar;
        public HashMap<String, String> passThroughMap;
        public CallerInfo callerInfo = new CallerInfo();
        public BusinessParams businessParam = new BusinessParams();
        public ImageSize imagesize = new ImageSize();

        /* loaded from: classes15.dex */
        public static class BusinessParams {
            public String entityId;
            public String keyword;
            public String oneboxSource;
            public String oneboxType;
            public String sku = "";
            public String page = "1";
            public String pagesize = "24";
            public String sort = "0";
            public String stock = "1";
        }

        /* loaded from: classes15.dex */
        public static class CallerInfo {
            public int bizChannel = 1;
            public String bu = "app";
            public String accessId = "1002";
            public String accessName = "app\u5546\u8be6";
        }

        /* loaded from: classes15.dex */
        public class ImageSize {
            public String gridImg;
            public String listImg;
            public String longImg;

            public ImageSize() {
            }
        }
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return "oneBoxSearchCom";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        try {
            OneboxProductListRequestParams oneboxProductListRequestParams = this.mRequestParams;
            if (oneboxProductListRequestParams != null) {
                httpSetting.putJsonParam("callerInfo", JDJSON.parseObject(JDJSON.toJSONString(oneboxProductListRequestParams.callerInfo)));
                JDJSONObject parseObject = JDJSON.parseObject(JDJSON.toJSONString(this.mRequestParams.businessParam));
                if (!TextUtils.isEmpty(this.mRequestParams.bodyKey) && !TextUtils.isEmpty(this.mRequestParams.bodyValue)) {
                    OneboxProductListRequestParams oneboxProductListRequestParams2 = this.mRequestParams;
                    if (oneboxProductListRequestParams2.isFilterBar) {
                        parseObject.put(oneboxProductListRequestParams2.bodyKey, (Object) oneboxProductListRequestParams2.bodyValue);
                    }
                }
                HashMap<String, String> hashMap = this.mRequestParams.passThroughMap;
                if (hashMap != null) {
                    parseObject.putAll(hashMap);
                }
                httpSetting.putJsonParam("businessParam", parseObject);
                httpSetting.setCacheMode(2);
                httpSetting.setEffect(0);
                httpSetting.setNotifyUser(false);
            }
        } catch (Exception unused) {
        }
    }
}
