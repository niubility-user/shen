package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDCommentProtocol extends PDBaseProtocol {
    private static final String FUNCTIONID = "getLegoWareDetailComment";

    public PDCommentProtocol(String str) {
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
    protected String getHost() {
        return Configuration.getCommentHost();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("sku", objArr[0]);
            jSONObject.put("category", objArr[1]);
            jSONObject.put("wareType", objArr[2]);
            jSONObject.put("shadowMainSku", objArr[3]);
            jSONObject.put("venderId", objArr[4]);
            jSONObject.put("shieldCurrentComment", objArr[5]);
            if (objArr.length > 6) {
                jSONObject.put("shopType", objArr[6]);
                jSONObject.put("shopId", objArr[7]);
            }
            if (objArr.length > 8) {
                jSONObject.put("isNew", objArr[8]);
            }
            if (objArr.length > 9) {
                jSONObject.put("newTitle", objArr[9]);
            }
            if (objArr.length > 10 && objArr[10] != null) {
                try {
                    int parseInt = Integer.parseInt(objArr[10].toString());
                    if (parseInt > 0) {
                        jSONObject.put("commentNum", parseInt);
                    }
                } catch (Exception unused) {
                }
            }
            if (objArr.length > 11 && objArr[11] != null) {
                jSONObject.put("extInfo", objArr[11]);
            }
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDCommentProtocol", "JSONException -->> ", e2);
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
        PdCommentInfo pdCommentInfo = (PdCommentInfo) JDJSON.parseObject(str, PdCommentInfo.class);
        pdCommentInfo.putJson(str);
        getEventBus().post(new PDApiEvent("detail_comment_key", pdCommentInfo, this.mEventKey));
        return pdCommentInfo;
    }
}
