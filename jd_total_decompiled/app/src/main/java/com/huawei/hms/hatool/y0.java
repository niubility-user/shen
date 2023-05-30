package com.huawei.hms.hatool;

import com.jingdong.sdk.baseinfo.BaseInfo;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class y0 extends t0 {

    /* renamed from: f  reason: collision with root package name */
    String f1419f;

    /* renamed from: g  reason: collision with root package name */
    String f1420g;

    /* renamed from: h  reason: collision with root package name */
    private String f1421h;

    @Override // com.huawei.hms.hatool.o1
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("_rom_ver", this.f1421h);
        jSONObject.put("_emui_ver", this.a);
        jSONObject.put("_model", BaseInfo.getDeviceModel());
        jSONObject.put("_mcc", this.f1419f);
        jSONObject.put("_mnc", this.f1420g);
        jSONObject.put("_package_name", this.b);
        jSONObject.put("_app_ver", this.f1415c);
        jSONObject.put("_lib_ver", "2.2.0.313");
        jSONObject.put("_channel", this.d);
        jSONObject.put("_lib_name", "hianalytics");
        jSONObject.put("_oaid_tracking_flag", this.f1416e);
        return jSONObject;
    }

    public void f(String str) {
        this.f1419f = str;
    }

    public void g(String str) {
        this.f1420g = str;
    }

    public void h(String str) {
        this.f1421h = str;
    }
}
