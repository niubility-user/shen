package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.ecard.PDECardCartNumEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDECardAddCartProtocol extends PDBaseProtocol {
    public static final String FOUNCTION_ECARD_ADD_CART = "addCart";
    public static final String FOUNCTION_ECARD_CART_NUM = "getCartNum";
    private String founctionId;

    public PDECardAddCartProtocol(String str, String str2) {
        super(str);
        this.founctionId = "getCartNum";
        this.founctionId = str2;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return this.founctionId;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getHost() {
        return Configuration.getWareHost();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            if (TextUtils.equals(this.founctionId, "addCart")) {
                if (objArr[0] != null) {
                    jSONObject.put("sku", objArr[0]);
                }
                if (objArr[1] != null) {
                    jSONObject.put("num", objArr[1]);
                }
            }
            jSONObject.put("type", "0");
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDECardAddCartProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return !TextUtils.equals(this.founctionId, "getCartNum");
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isPost() {
        return true;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDECardCartNumEntity pDECardCartNumEntity = (PDECardCartNumEntity) JDJSON.parseObject(str, PDECardCartNumEntity.class);
        pDECardCartNumEntity.functionID = this.founctionId;
        if (TextUtils.equals(pDECardCartNumEntity.code, "0")) {
            getEventBus().post(new PDApiEvent("get_ecard_count_key", pDECardCartNumEntity, this.mEventKey));
        }
        return pDECardCartNumEntity;
    }
}
