package com.cmic.sso.sdk.d;

import android.text.TextUtils;
import com.cmic.sso.sdk.auth.AuthnHelper;
import com.cmic.sso.sdk.c.b.g;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.tencent.mapsdk.internal.l4;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a extends g {
    private String A;
    private JSONArray o;
    private String x;
    private String y;
    private String z;
    private String b = null;

    /* renamed from: c  reason: collision with root package name */
    private String f1025c = null;
    private String d = null;

    /* renamed from: e  reason: collision with root package name */
    private String f1026e = null;

    /* renamed from: f  reason: collision with root package name */
    private String f1027f = null;

    /* renamed from: g  reason: collision with root package name */
    private String f1028g = null;

    /* renamed from: h  reason: collision with root package name */
    private String f1029h = null;

    /* renamed from: i  reason: collision with root package name */
    private String f1030i = null;

    /* renamed from: j  reason: collision with root package name */
    private String f1031j = null;

    /* renamed from: k  reason: collision with root package name */
    private String f1032k = "";

    /* renamed from: l  reason: collision with root package name */
    private String f1033l = null;

    /* renamed from: m  reason: collision with root package name */
    private String f1034m = null;

    /* renamed from: n  reason: collision with root package name */
    private String f1035n = null;
    private String p = null;
    private String q = null;
    private String r = null;
    private String s = null;
    private String t = null;
    private String u = null;
    private String v = null;
    private String w = null;
    public CopyOnWriteArrayList<Throwable> a = new CopyOnWriteArrayList<>();

    public void A(String str) {
        this.A = str;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public String a() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(JSONArray jSONArray) {
        this.o = jSONArray;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    protected String a_(String str) {
        return null;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.v = str;
    }

    public void d(String str) {
        this.w = str;
    }

    public void e(String str) {
        this.r = str;
    }

    public void f(String str) {
        this.f1034m = str;
    }

    public void g(String str) {
        this.f1033l = str;
    }

    public void h(String str) {
        this.f1032k = str;
    }

    public void i(String str) {
        this.d = str;
    }

    public void j(String str) {
        this.f1026e = str;
    }

    public void k(String str) {
        this.f1027f = str;
    }

    public void l(String str) {
        this.f1030i = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(String str) {
        this.u = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(String str) {
        this.p = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(String str) {
        this.s = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(String str) {
        this.t = str;
    }

    public void q(String str) {
        this.f1035n = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(String str) {
        this.f1025c = str;
    }

    public void s(String str) {
        this.f1028g = str;
    }

    public void t(String str) {
        this.f1029h = str;
    }

    public void u(String str) {
        this.f1031j = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(String str) {
        this.q = str;
    }

    public void x(String str) {
        this.x = str;
    }

    public void y(String str) {
        this.y = str;
    }

    public void z(String str) {
        this.z = str;
    }

    @Override // com.cmic.sso.sdk.c.b.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", this.b);
            jSONObject.put("traceId", this.f1025c);
            jSONObject.put("appName", this.d);
            jSONObject.put("appVersion", this.f1026e);
            jSONObject.put(l4.f16791e, AuthnHelper.SDK_VERSION);
            jSONObject.put("clientType", "android");
            jSONObject.put("timeOut", this.f1027f);
            jSONObject.put("requestTime", this.f1028g);
            jSONObject.put("responseTime", this.f1029h);
            jSONObject.put("elapsedTime", this.f1030i);
            jSONObject.put("requestType", this.f1031j);
            jSONObject.put("interfaceType", this.f1032k);
            jSONObject.put("interfaceCode", this.f1033l);
            jSONObject.put("interfaceElasped", this.f1034m);
            jSONObject.put("loginType", this.f1035n);
            jSONObject.put("exceptionStackTrace", this.o);
            jSONObject.put("operatorType", this.p);
            jSONObject.put("networkType", this.q);
            jSONObject.put(JDNetCacheManager.BRAND_BIZKEY, this.r);
            jSONObject.put("reqDevice", this.s);
            jSONObject.put("reqSystem", this.t);
            jSONObject.put("simCardNum", this.u);
            jSONObject.put("imsiState", this.v);
            jSONObject.put("resultCode", this.w);
            jSONObject.put("AID", this.x);
            jSONObject.put("sysOperType", this.y);
            jSONObject.put("scripType", this.z);
            if (!TextUtils.isEmpty(this.A)) {
                jSONObject.put("networkTypeByAPI", this.A);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
