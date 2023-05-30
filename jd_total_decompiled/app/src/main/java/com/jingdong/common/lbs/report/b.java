package com.jingdong.common.lbs.report;

import android.os.Build;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.lbs.utils.DeviceUtil;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.meizu.cloud.pushsdk.notification.model.AdvertisementOption;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public double f12371c;
    public double d;

    /* renamed from: e  reason: collision with root package name */
    public int f12372e;

    /* renamed from: f  reason: collision with root package name */
    public int f12373f;

    /* renamed from: g  reason: collision with root package name */
    public int f12374g;

    /* renamed from: h  reason: collision with root package name */
    public int f12375h;

    /* renamed from: j  reason: collision with root package name */
    public int f12377j;

    /* renamed from: l  reason: collision with root package name */
    public long f12379l;

    /* renamed from: m  reason: collision with root package name */
    private String f12380m;

    /* renamed from: i  reason: collision with root package name */
    public String f12376i = "";

    /* renamed from: k  reason: collision with root package name */
    public String f12378k = "";
    String a = "";

    /* renamed from: n  reason: collision with root package name */
    private String f12381n = "android";
    private String o = "android";
    private String p = DeviceUtil.getAppPackageName();
    private String q = DeviceUtil.getAppVersionName();
    private String v = DeviceUtil.getUUID();
    private String w = DeviceUtil.getPin();
    private String x = Build.VERSION.RELEASE;
    private String y = "none";
    private String r = BaseInfo.getDeviceBrand();
    private String s = BaseInfo.getDeviceModel();
    private String t = DeviceUtil.getDeviceName();
    private String u = DeviceUtil.getSDKVersion();

    public final String a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AdvertisementOption.AD_PACKAGE, this.a);
            jSONObject.put("ck", this.b);
            jSONObject.put("pa", this.f12380m);
            jSONObject.put("ct", this.f12381n);
            jSONObject.put("ot", this.o);
            jSONObject.put("an", this.p);
            jSONObject.put("av", this.q);
            jSONObject.put(NotificationMessageSummary.DD_MSG, this.r);
            jSONObject.put("dm", this.s);
            jSONObject.put("de", this.t);
            jSONObject.put("sv", this.u);
            jSONObject.put("ud", this.v);
            jSONObject.put("pn", this.w);
            jSONObject.put("os", this.x);
            jSONObject.put("ne", this.y);
            StringBuilder sb = new StringBuilder();
            sb.append(this.f12371c);
            jSONObject.put("ln", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.d);
            jSONObject.put("la", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(this.f12372e);
            jSONObject.put("pi", sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append(this.f12373f);
            jSONObject.put("ci", sb4.toString());
            StringBuilder sb5 = new StringBuilder();
            sb5.append(this.f12374g);
            jSONObject.put(AppIconSetting.DEFAULT_LARGE_ICON, sb5.toString());
            StringBuilder sb6 = new StringBuilder();
            sb6.append(this.f12375h);
            jSONObject.put("ti", sb6.toString());
            jSONObject.put("ad", this.f12376i);
            jSONObject.put("st", this.f12377j);
            jSONObject.put("mg", this.f12378k);
            jSONObject.put("tc", this.f12379l);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("info", jSONObject);
            return jSONObject2.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public final void a(JDLocationOption jDLocationOption) {
        if (jDLocationOption == null) {
            return;
        }
        this.a = jDLocationOption.getBusinessId();
        this.f12380m = jDLocationOption.getJsonStr();
    }
}
