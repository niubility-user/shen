package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.share.PdShareContent;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PdShareContentElderProtocol extends PDBaseProtocol {
    public PdShareContentElderProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return "lv";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("comefrom", "shareMPage");
            jSONObject.put("skuId", objArr[0]);
        } catch (JSONException e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public void parseError(HttpError httpError) {
        getEventBus().post(new PDApiEvent(PDApiEvent.DETAIL_SHARE_CONTENT_ELDER_KEY, "", this.mEventKey));
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        String str2;
        PdShareContent pdShareContent = (PdShareContent) JDJSON.parseObject(str, PdShareContent.class);
        if (pdShareContent == null || (str2 = pdShareContent.shareTxt) == null) {
            str2 = "";
        }
        getEventBus().post(new PDApiEvent(PDApiEvent.DETAIL_SHARE_CONTENT_ELDER_KEY, str2, this.mEventKey));
        return str;
    }
}
