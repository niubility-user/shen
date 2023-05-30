package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.PDIsAppointEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDIsAppointProtocol extends PDBaseProtocol {
    private static final String FUNCTION_ID = "isAppoint";
    private boolean hasSr;
    public boolean isNowBuy;

    public PDIsAppointProtocol(String str) {
        super(str);
        this.hasSr = false;
        this.isNowBuy = false;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("skuId", objArr[0]);
            if (objArr.length > 1) {
                if (objArr[1] != null) {
                    jSONObject.put("sr", objArr[1]);
                    this.hasSr = true;
                } else {
                    this.hasSr = false;
                }
            }
            if (objArr.length > 2 && objArr[2] != null) {
                jSONObject.put("isShowCode", objArr[2]);
            }
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDIsAppointProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDIsAppointEntity pDIsAppointEntity = (PDIsAppointEntity) JDJSON.parseObject(str, PDIsAppointEntity.class);
        if (pDIsAppointEntity != null) {
            pDIsAppointEntity.isNowbuy = this.isNowBuy;
            getEventBus().post(new PDApiEvent(this.hasSr ? "detail_appoint_hassr_key" : "detail_appoint_nosr_key", pDIsAppointEntity, this.mEventKey));
        }
        return pDIsAppointEntity;
    }
}
