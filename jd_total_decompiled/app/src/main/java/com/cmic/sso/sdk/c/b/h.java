package com.cmic.sso.sdk.c.b;

import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class h extends a {
    protected String y = "";
    protected String z = "";

    @Override // com.cmic.sso.sdk.c.b.a
    public void a(String str) {
        this.v = t(str);
    }

    @Override // com.cmic.sso.sdk.c.b.g
    protected String a_(String str) {
        return this.b + this.f989c + this.d + this.f990e + this.f991f + this.f992g + this.f993h + this.f994i + this.f995j + this.f998m + this.f999n + str + this.o + this.q + this.r + this.s + this.t + this.u + this.v + this.y + this.z + this.w + this.x;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ver", this.a);
            jSONObject.put("sdkver", this.b);
            jSONObject.put("appid", this.f989c);
            jSONObject.put("imsi", this.d);
            jSONObject.put("operatortype", this.f990e);
            jSONObject.put("networktype", this.f991f);
            jSONObject.put("mobilebrand", this.f992g);
            jSONObject.put("mobilemodel", this.f993h);
            jSONObject.put("mobilesystem", this.f994i);
            jSONObject.put("clienttype", this.f995j);
            jSONObject.put("interfacever", this.f996k);
            jSONObject.put("expandparams", this.f997l);
            jSONObject.put("msgid", this.f998m);
            jSONObject.put(VerifyTracker.KEY_TIMESTAMP, this.f999n);
            jSONObject.put("subimsi", this.o);
            jSONObject.put("sign", this.p);
            jSONObject.put("apppackage", this.q);
            jSONObject.put("appsign", this.r);
            jSONObject.put("ipv4_list", this.s);
            jSONObject.put("ipv6_list", this.t);
            jSONObject.put("sdkType", this.u);
            jSONObject.put("tempPDR", this.v);
            jSONObject.put("scrip", this.y);
            jSONObject.put("userCapaid", this.z);
            jSONObject.put("funcType", this.w);
            jSONObject.put("socketip", this.x);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return this.a + ContainerUtils.FIELD_DELIMITER + this.b + ContainerUtils.FIELD_DELIMITER + this.f989c + ContainerUtils.FIELD_DELIMITER + this.d + ContainerUtils.FIELD_DELIMITER + this.f990e + ContainerUtils.FIELD_DELIMITER + this.f991f + ContainerUtils.FIELD_DELIMITER + this.f992g + ContainerUtils.FIELD_DELIMITER + this.f993h + ContainerUtils.FIELD_DELIMITER + this.f994i + ContainerUtils.FIELD_DELIMITER + this.f995j + ContainerUtils.FIELD_DELIMITER + this.f996k + ContainerUtils.FIELD_DELIMITER + this.f997l + ContainerUtils.FIELD_DELIMITER + this.f998m + ContainerUtils.FIELD_DELIMITER + this.f999n + ContainerUtils.FIELD_DELIMITER + this.o + ContainerUtils.FIELD_DELIMITER + this.p + ContainerUtils.FIELD_DELIMITER + this.q + ContainerUtils.FIELD_DELIMITER + this.r + "&&" + this.s + ContainerUtils.FIELD_DELIMITER + this.t + ContainerUtils.FIELD_DELIMITER + this.u + ContainerUtils.FIELD_DELIMITER + this.v + ContainerUtils.FIELD_DELIMITER + this.y + ContainerUtils.FIELD_DELIMITER + this.z + ContainerUtils.FIELD_DELIMITER + this.w + ContainerUtils.FIELD_DELIMITER + this.x;
    }

    public void w(String str) {
        this.y = t(str);
    }

    public void x(String str) {
        this.z = t(str);
    }
}
