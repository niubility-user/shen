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
public class PDGetCartNumProtocol extends PDBaseProtocol {
    public PDGetCartNumProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return "getCartNum";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getHost() {
        return Configuration.getWareHost();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("type", "1");
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDGetCartNumProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return false;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isPost() {
        return true;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDECardCartNumEntity pDECardCartNumEntity = (PDECardCartNumEntity) JDJSON.parseObject(str, PDECardCartNumEntity.class);
        if (TextUtils.equals(pDECardCartNumEntity.code, "0")) {
            getEventBus().post(new PDApiEvent("get_printbag_count_key", pDECardCartNumEntity, this.mEventKey));
        }
        return pDECardCartNumEntity;
    }
}
