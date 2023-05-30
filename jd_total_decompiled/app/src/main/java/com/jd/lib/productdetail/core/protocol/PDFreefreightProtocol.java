package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.coudan.PDCoudanEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDFreefreightProtocol extends PDBaseProtocol {
    public PDFreefreightProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return "wareFreeFreight";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("skuId", objArr[0]);
            if (objArr[1] != null) {
                jSONObject.put("ordermin", objArr[1]);
            }
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDFreefreightProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDCoudanEntity pDCoudanEntity = (PDCoudanEntity) JDJSON.parseObject(str, PDCoudanEntity.class);
        if (pDCoudanEntity != null && pDCoudanEntity.freightInfo != null) {
            getEventBus().post(new PDApiEvent("detail_coudan_freefreight_key", pDCoudanEntity.freightInfo, this.mEventKey));
        }
        return pDCoudanEntity;
    }
}
