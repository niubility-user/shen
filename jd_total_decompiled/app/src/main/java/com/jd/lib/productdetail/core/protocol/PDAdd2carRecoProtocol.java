package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.add2carreco.PDAdd2CarRecoEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDAdd2carRecoProtocol extends PDBaseProtocol {
    private static final String FUNCTIONID = "uniformRecommend";
    private boolean isFreshAddCar;

    public PDAdd2carRecoProtocol(String str) {
        super(str);
        this.isFreshAddCar = false;
    }

    private JSONArray getJsonArray(Object obj) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(obj);
        return jSONArray;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTIONID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("source", 5);
            jSONObject.put("page", 1);
            jSONObject.put("pageSize", 24);
            jSONObject.put("skus", getJsonArray(objArr[0]));
            jSONObject.put("curPos", RecommendUtils.getCurrentAddress());
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDPackStyleProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDAdd2CarRecoEntity pDAdd2CarRecoEntity = (PDAdd2CarRecoEntity) JDJSON.parseObject(str, PDAdd2CarRecoEntity.class);
        if (pDAdd2CarRecoEntity != null) {
            if (this.isFreshAddCar) {
                getEventBus().post(new PDApiEvent("detail_add2car_fresh_recommend_key", pDAdd2CarRecoEntity, this.mEventKey));
            } else {
                getEventBus().post(new PDApiEvent("detail_add2car_recommend_key", pDAdd2CarRecoEntity, this.mEventKey));
            }
        }
        return str;
    }

    public PDAdd2carRecoProtocol(String str, boolean z) {
        super(str);
        this.isFreshAddCar = false;
        this.isFreshAddCar = z;
    }
}
