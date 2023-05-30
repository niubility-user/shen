package com.jingdong.common.lbs.report;

import android.os.Build;
import com.jingdong.common.lbs.utils.DeviceUtil;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class a {
    String a;
    private String b = "android";

    /* renamed from: c  reason: collision with root package name */
    private String f12366c = DeviceUtil.getAppVersionName();
    private String d = DeviceUtil.getSDKVersion();

    /* renamed from: e  reason: collision with root package name */
    private String f12367e = DeviceUtil.getUUID();

    /* renamed from: f  reason: collision with root package name */
    private String f12368f = DeviceUtil.getAppPackageName();

    /* renamed from: g  reason: collision with root package name */
    private String f12369g = DeviceUtil.getPin();

    /* renamed from: h  reason: collision with root package name */
    private String f12370h = Build.VERSION.RELEASE;

    public final String a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("client", this.b);
            jSONObject.put("appVer", this.f12366c);
            jSONObject.put("sdkVer", this.d);
            jSONObject.put("udid", "");
            jSONObject.put("boundId", this.f12368f);
            jSONObject.put("configVer", this.a);
            jSONObject.put("pin", this.f12369g);
            jSONObject.put("osVer", this.f12370h);
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
