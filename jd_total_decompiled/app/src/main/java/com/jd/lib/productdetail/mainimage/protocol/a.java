package com.jd.lib.productdetail.mainimage.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.protocol.PDBaseProtocol;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends PDBaseProtocol {
    public PdMainImagePresenter a;

    public a(String str, PdMainImagePresenter pdMainImagePresenter) {
        super(str);
        this.a = pdMainImagePresenter;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public int getEffect() {
        return 0;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public String getFunctionId() {
        return "getLegoWareDetailComment";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public String getHost() {
        return Configuration.getCommentHost();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        int parseInt;
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
            if (objArr.length > 10) {
                if (objArr[10] != null && (parseInt = Integer.parseInt(objArr[10].toString())) > 0) {
                    jSONObject.put("commentNum", parseInt);
                }
            }
        } catch (JSONException | Exception unused) {
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public boolean isNotifyUser() {
        return false;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PdCommentInfo pdCommentInfo = (PdCommentInfo) JDJSON.parseObject(str, PdCommentInfo.class);
        pdCommentInfo.putJson(str);
        PdMainImagePresenter pdMainImagePresenter = this.a;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.pdCommentInfo.postValue(pdCommentInfo);
        }
        return pdCommentInfo;
    }
}
