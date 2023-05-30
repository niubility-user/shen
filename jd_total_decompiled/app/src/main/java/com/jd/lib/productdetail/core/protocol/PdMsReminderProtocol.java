package com.jd.lib.productdetail.core.protocol;

import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PdMsReminderProtocol extends PDBaseProtocol {
    private static final String FUNCTIONID = "seckill";
    private JSONObject mRequest;

    public PdMsReminderProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected int getEffect() {
        return 0;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return "seckill";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        return this.mRequest;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return false;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        return null;
    }

    public void setJSONObject(JSONObject jSONObject) {
        this.mRequest = jSONObject;
    }
}
