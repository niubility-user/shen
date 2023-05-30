package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PdEyeSightProtocol extends PDBaseProtocol {
    public static String CLICK_CLOSE = "clickClose";
    public static final String CLICK_TIM = "clickTim";
    private static final String FUNCTION_ID = "eyeSight";
    private String clickType;

    public PdEyeSightProtocol(String str, String str2) {
        super(str);
        this.clickType = str2;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("giftSn", objArr[0]);
            jSONObject.put("skuId", objArr[1]);
            jSONObject.put("clickEvent", this.clickType);
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDFirstTimePurchaseProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        if (TextUtils.equals(CLICK_TIM, this.clickType)) {
            JDJSONObject parseObject = JDJSON.parseObject(str);
            getEventBus().post(new PDApiEvent("detail_buy_status_key", parseObject, this.mEventKey));
            return parseObject;
        }
        return str;
    }
}
