package com.jingdong.common.weixin;

import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.anotation.JSONField;
import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes.dex */
public class WeiXinEntity {
    public String appId = "wxe75a2e68877315fb";
    public String nonceStr;
    @JSONField(name = "package")
    public String packageValue;
    public String partnerId;
    public String prepayId;
    public String sign;
    public String timeStamp;

    public WeiXinEntity() {
    }

    public WeiXinEntity(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        this.partnerId = jSONObjectProxy.optString("partnerId");
        this.prepayId = jSONObjectProxy.optString("prepayId");
        this.nonceStr = jSONObjectProxy.optString("nonceStr");
        this.timeStamp = jSONObjectProxy.optString("timeStamp");
        this.packageValue = jSONObjectProxy.optString("package");
        this.sign = jSONObjectProxy.optString("sign");
    }

    public WeiXinEntity(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.partnerId = jDJSONObject.optString("partnerId");
        this.prepayId = jDJSONObject.optString("prepayId");
        this.nonceStr = jDJSONObject.optString("nonceStr");
        this.timeStamp = jDJSONObject.optString("timeStamp");
        this.packageValue = jDJSONObject.optString("package");
        this.sign = jDJSONObject.optString("sign");
    }
}
