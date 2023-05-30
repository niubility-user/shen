package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PDRecommendEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDRecommendHourPurchaseProtocol extends PDBaseProtocol {
    private static final String FUNCTIONID = "uniformRecommend";

    public PDRecommendHourPurchaseProtocol(String str) {
        super(str);
    }

    private JSONArray getJsonArray(Object obj) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(obj);
        return jSONArray;
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
    protected String getHost() {
        return Configuration.getPersonalHost();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("source", 55);
            jSONObject.put("skus", getJsonArray(objArr[0]));
            jSONObject.put("curPos", RecommendUtils.getCurrentAddress());
            if (objArr.length > 2) {
                jSONObject.put("storeId", objArr[1]);
                jSONObject.put("venderId", objArr[2]);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return false;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JDJSONObject parseObject = JDJSON.parseObject(str);
                if (parseObject != null) {
                    String optString = parseObject.optString(PDConstant.EXTRA_EXPID);
                    JDJSONArray jSONArray = parseObject.getJSONArray("wareInfoList");
                    if (jSONArray != null && jSONArray.size() > 0) {
                        ArrayList arrayList = new ArrayList(jSONArray.size());
                        for (int i2 = 0; i2 < jSONArray.size(); i2++) {
                            JDJSONObject jSONObject = jSONArray.getJSONObject(i2);
                            if (jSONObject != null) {
                                PDRecommendEntity pDRecommendEntity = new PDRecommendEntity(jSONObject, String.valueOf(i2));
                                pDRecommendEntity.rid = optString;
                                arrayList.add(pDRecommendEntity);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            getEventBus().post(new PDApiEvent(PDApiEvent.DETAIL_RECOMMEND_HOUR_PURCHASE_KEY, arrayList, this.mEventKey));
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }
}
