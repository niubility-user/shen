package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.PDFirstTimePurchaseEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDFirstTimePurchaseProtocol extends PDBaseProtocol {
    private static final String FOUNCTIONID = "firstTimePurchaseGetUserPopState";

    public PDFirstTimePurchaseProtocol(String str) {
        super(str);
    }

    private JSONArray getJsonArray(Object[] objArr) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            jSONObject.put("id", objArr[1]);
            jSONObject.put("price", objArr[2]);
            jSONObject.put("itemFirstCateCd", objArr[3]);
            jSONObject.put("itemSecondCateCd", objArr[4]);
            jSONObject.put("itemThirdCateCd", objArr[5]);
            jSONObject.put("quantity", "1");
            jSONArray.put(jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONArray;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FOUNCTIONID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("productLineId", objArr[0]);
            jSONObject.put("skuVoList", getJsonArray(objArr));
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDFirstTimePurchaseProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public void parseError(HttpError httpError) {
        super.parseError(httpError);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDFirstTimePurchaseEntity pDFirstTimePurchaseEntity = (PDFirstTimePurchaseEntity) JDJSON.parseObject(str, PDFirstTimePurchaseEntity.class);
        getEventBus().post(new PDApiEvent("detail_first_purchase_key", pDFirstTimePurchaseEntity, this.mEventKey));
        return pDFirstTimePurchaseEntity;
    }
}
