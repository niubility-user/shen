package com.jingdong.jdexreport.e;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdexreport.einterface.InitCommonInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.mapsdk.internal.l4;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {

    /* renamed from: l */
    private static b f12596l = null;

    /* renamed from: m */
    public static String f12597m = "";
    private String a;
    private String b;

    /* renamed from: c */
    private String f12598c;
    private String d;

    /* renamed from: e */
    private String f12599e;

    /* renamed from: f */
    private String f12600f;

    /* renamed from: g */
    private String f12601g;

    /* renamed from: h */
    private String f12602h;

    /* renamed from: i */
    private String f12603i;

    /* renamed from: j */
    private String f12604j;

    /* renamed from: k */
    private InitCommonInfo f12605k;

    private b(InitCommonInfo initCommonInfo) {
        this.a = "";
        this.b = "";
        this.f12598c = "";
        this.d = "";
        this.f12599e = "";
        this.f12600f = "";
        this.f12601g = "";
        this.f12602h = "";
        this.f12603i = "";
        this.f12604j = "";
        this.f12605k = initCommonInfo;
        this.a = initCommonInfo.guid;
        this.b = a(BaseInfo.getDeviceModel(), 12);
        this.f12598c = b();
        this.d = Build.VERSION.RELEASE;
        this.f12600f = initCommonInfo.appv;
        this.f12601g = initCommonInfo.hmv;
        this.f12602h = initCommonInfo.build;
        this.f12603i = "3";
        this.f12599e = "android";
        this.f12604j = initCommonInfo.appId;
    }

    public static synchronized b a(InitCommonInfo initCommonInfo) {
        b bVar;
        synchronized (b.class) {
            if (f12596l == null) {
                f12596l = new b(initCommonInfo);
            }
            bVar = f12596l;
        }
        return bVar;
    }

    private static String b() {
        return TextUtils.equals("harmony", Class.forName("com.huawei.system.BuildEx").getMethod("getOsBrand", new Class[0]).invoke(null, new Object[0]).toString()) ? "harmony" : "android";
    }

    public static synchronized void a() {
        synchronized (b.class) {
            f12596l = null;
        }
    }

    public JSONObject a(Context context) {
        InitCommonInfo initCommonInfo;
        JSONObject jSONObject = new JSONObject();
        try {
            String a = com.jingdong.jdexreport.a.a.a.a();
            String a2 = com.jingdong.jdexreport.a.a.c.a(a + "5YT%aC89$22OI@pQ");
            jSONObject.put("accountId", a(f12597m));
            if (TextUtils.isEmpty(this.a) && (initCommonInfo = this.f12605k) != null) {
                this.a = initCommonInfo.getGuid();
            }
            jSONObject.put("machineCode", this.a);
            jSONObject.put("machineType", this.b);
            jSONObject.put("os", this.f12598c);
            jSONObject.put(HybridSDK.OS_VERSION, this.d);
            jSONObject.put("app", this.f12599e);
            jSONObject.put("appVersion", this.f12600f);
            jSONObject.put("harmonyVersion", this.f12601g);
            jSONObject.put("net", com.jingdong.jdexreport.a.a.d.b(context));
            jSONObject.put("curTime", a);
            jSONObject.put(l4.f16791e, this.f12603i);
            jSONObject.put("token", a2);
            jSONObject.put(HybridSDK.APP_VERSION_CODE, this.f12602h);
            jSONObject.put("appId", this.f12604j);
        } catch (ClassCastException e2) {
            e2.printStackTrace();
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return jSONObject;
    }

    private static String a(String str, int i2) {
        if (str != null) {
            try {
                return str.length() > i2 ? str.substring(0, i2) : str;
            } catch (Exception e2) {
                e2.printStackTrace();
                return str;
            }
        }
        return str;
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : com.jingdong.jdexreport.common.secure.a.a(str, "this is the pinaddress key apoaffffe");
    }
}
