package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentOfficerInfo;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PdCommentOfficerProtocol extends PDBaseProtocol {
    private static final String FUNCTIONID = "getCommentOfficerStatusInfo";

    public PdCommentOfficerProtocol(String str) {
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
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return false;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        JDJSONObject parseObject = JDJSON.parseObject(str);
        if (parseObject == null || parseObject.isNull("result")) {
            return null;
        }
        PdCommentOfficerInfo pdCommentOfficerInfo = (PdCommentOfficerInfo) JDJSON.parseObject(parseObject.optJSONObject("result").toString(), PdCommentOfficerInfo.class);
        getEventBus().post(new PDApiEvent("detail_comment_officer_key", pdCommentOfficerInfo, this.mEventKey));
        return pdCommentOfficerInfo;
    }
}
