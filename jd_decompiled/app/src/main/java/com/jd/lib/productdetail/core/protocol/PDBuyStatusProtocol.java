package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.PDBuyStatusEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDBuyStatusProtocol extends PDBaseProtocol {
    private static final String FUNCTIONID = "wareBarrageMsg";

    public PDBuyStatusProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected int getEffect() {
        return 0;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTIONID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        if (objArr != null && objArr.length > 0) {
            try {
                jSONObject.put("skuId", objArr[0]);
                if (objArr[1] != null) {
                    jSONObject.put("joinBuy", objArr[1]);
                }
            } catch (JSONException e2) {
                if (Log.D) {
                    Log.d("PDCouponProtocol", "JSONException -->> ", e2);
                }
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDBuyStatusEntity pDBuyStatusEntity = (PDBuyStatusEntity) JDJSON.parseObject(str, PDBuyStatusEntity.class);
        getEventBus().post(new PDApiEvent("detail_buy_status_key", pDBuyStatusEntity, this.mEventKey));
        return pDBuyStatusEntity;
    }
}
