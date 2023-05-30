package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.FamilyNumberAddData;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PdFamilyNumberAddProtocol extends PDBaseProtocol {
    private static final String FUNCTIONID = "asynInteface";
    private String mFrom;

    public PdFamilyNumberAddProtocol(String str) {
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
            if (objArr.length > 1) {
                jSONObject.put("intefaceType", objArr[1]);
            }
            if (objArr.length > 2 && (objArr[2] instanceof String)) {
                this.mFrom = (String) objArr[2];
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return false;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        try {
            FamilyNumberAddData familyNumberAddData = (FamilyNumberAddData) JDJSON.parseObject(str, FamilyNumberAddData.class);
            familyNumberAddData.from = this.mFrom;
            getEventBus().post(new PDApiEvent("detail_family_number_add_key", familyNumberAddData, this.mEventKey));
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return str;
    }
}
