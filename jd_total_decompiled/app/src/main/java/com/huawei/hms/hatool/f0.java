package com.huawei.hms.hatool;

import com.jd.aips.verify.tracker.VerifyTracker;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class f0 extends k0 {

    /* renamed from: g  reason: collision with root package name */
    private String f1364g = "";

    @Override // com.huawei.hms.hatool.o1
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("protocol_version", "1");
        jSONObject.put("compress_mode", "1");
        jSONObject.put("serviceid", this.d);
        jSONObject.put("appid", this.a);
        jSONObject.put("hmac", this.f1364g);
        jSONObject.put("chifer", this.f1389f);
        jSONObject.put(VerifyTracker.KEY_TIMESTAMP, this.b);
        jSONObject.put("servicetag", this.f1387c);
        jSONObject.put("requestid", this.f1388e);
        return jSONObject;
    }

    public void g(String str) {
        this.f1364g = str;
    }
}
