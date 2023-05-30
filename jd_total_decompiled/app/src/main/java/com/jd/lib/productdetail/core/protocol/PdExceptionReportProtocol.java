package com.jd.lib.productdetail.core.protocol;

import androidx.annotation.NonNull;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class PdExceptionReportProtocol extends PdBaseProtocolLiveData<HashMap> {
    public RequestParams mParams;

    /* loaded from: classes15.dex */
    public static class RequestParams {
        public static final String DIGITAL_BOTTOM = "666666";
        public static final String ERROR_CODE_ADDCART_FAIL = "40004";
        public static final String ERROR_CODE_FAVORITE_FAIL = "40006";
        public static final String ERROR_CODE_FUNCTION_TIMEOUT = "30001";
        public static final String ERROR_CODE_INVALID_FLOORS = "40005";
        public static final String ERROR_CODE_NO_NAME_FLOOR = "40008";
        public static final String ERROR_CODE_NO_ONEBOX = "40011";
        public static final String ERROR_CODE_NO_PRICE_FLOOR = "40007";
        public static final String ERROR_CODE_NO_PRODUCT_DETAIL = "40009";
        public static final String ERROR_CODE_NO_RECOMMEND = "400010";
        public static final String ERROR_CODE_PRICE_INVALID = "40002";
        public static final String ERROR_CODE_RECEIVE_COUPON_FAIL = "40003";
        public static final String ERROR_CODE_REQUEST_TIMEOUT = "40001";
        public String desc;
        public String errorCode;
        public String skuId;
        public String type;
    }

    public PdExceptionReportProtocol(RequestParams requestParams) {
        this.mParams = requestParams;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected String getFunctionId() {
        return "reportEx";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    protected void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.putJsonParam(this.mParams);
    }
}
