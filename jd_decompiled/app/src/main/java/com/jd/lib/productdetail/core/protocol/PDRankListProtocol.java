package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PDRecommendEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessRecommendRankEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessRecommendRankListEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;
import java.util.List;
import jpbury.t;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDRankListProtocol extends PDBaseProtocol {
    private static final String FUNCTIONID = "asynInteface";

    public PDRankListProtocol(String str) {
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
        try {
            jSONObject.put("skuId", objArr[0]);
            jSONObject.put("category", objArr[1]);
            jSONObject.put("shopId", objArr[2]);
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDPackStyleProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return false;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        JDJSONObject optJSONObject;
        WareBusinessRecommendRankEntity wareBusinessRecommendRankEntity;
        List<WareBusinessRecommendRankListEntity> list;
        if (!TextUtils.isEmpty(str)) {
            try {
                JDJSONObject parseObject = JDJSON.parseObject(str);
                if (parseObject != null && (optJSONObject = parseObject.optJSONObject("rankInfo")) != null && (wareBusinessRecommendRankEntity = (WareBusinessRecommendRankEntity) JDJSON.parseObject(optJSONObject.toString(), WareBusinessRecommendRankEntity.class)) != null && (list = wareBusinessRecommendRankEntity.rankList) != null && list.size() > 0) {
                    getEventBus().post(new PDApiEvent("detail_ranklist_key", wareBusinessRecommendRankEntity, this.mEventKey));
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                }
            }
        }
        return str;
    }

    public ArrayList<PDRecommendEntity> toList(JDJSONArray jDJSONArray) {
        ArrayList<PDRecommendEntity> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                PDRecommendEntity pDRecommendEntity = new PDRecommendEntity(jDJSONArray.getJSONObject(i2));
                if (!TextUtils.isEmpty(pDRecommendEntity.name)) {
                    arrayList.add(pDRecommendEntity);
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d("ServerIcon", e2.getMessage());
                }
            }
        }
        return arrayList;
    }
}
