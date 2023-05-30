package com.jingdong.jdpush_new.g.c;

import com.jingdong.common.messagecenter.NotificationMessageSummary;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f12828c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f12829e;

    /* renamed from: f  reason: collision with root package name */
    private String f12830f;

    /* renamed from: g  reason: collision with root package name */
    private int f12831g;

    /* renamed from: h  reason: collision with root package name */
    private String f12832h;

    /* renamed from: i  reason: collision with root package name */
    private String f12833i;

    /* renamed from: j  reason: collision with root package name */
    private String f12834j;

    public static b k(String str) {
        try {
            b bVar = new b();
            JSONObject jSONObject = new JSONObject(str);
            bVar.o(jSONObject.has("id") ? jSONObject.getString("id") : null);
            bVar.l(jSONObject.has("appId") ? jSONObject.getString("appId") : null);
            bVar.n(jSONObject.has("command") ? jSONObject.getString("command") : null);
            bVar.m(jSONObject.has("appSecret") ? jSONObject.getString("appSecret") : null);
            bVar.q(jSONObject.has("status") ? jSONObject.getString("status") : null);
            bVar.t(jSONObject.has("version_app") ? jSONObject.getString("version_app") : null);
            bVar.u(jSONObject.has("version_os") ? jSONObject.getString("version_os") : null);
            bVar.r(jSONObject.has("time") ? jSONObject.getInt("time") : 0);
            bVar.s(jSONObject.has("timeStamp") ? jSONObject.getString("timeStamp") : null);
            bVar.p(jSONObject.has(NotificationMessageSummary.MSG_BODY) ? jSONObject.getString(NotificationMessageSummary.MSG_BODY) : null);
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.f12828c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.f12830f;
    }

    public String f() {
        return this.f12829e;
    }

    public int g() {
        return this.f12831g;
    }

    public String h() {
        return this.f12834j;
    }

    public String i() {
        return this.f12832h;
    }

    public String j() {
        return this.f12833i;
    }

    public void l(String str) {
        this.b = str;
    }

    public void m(String str) {
        this.f12828c = str;
    }

    public void n(String str) {
        this.d = str;
    }

    public void o(String str) {
        this.a = str;
    }

    public void p(String str) {
        this.f12830f = str;
    }

    public void q(String str) {
        this.f12829e = str;
    }

    public void r(int i2) {
        this.f12831g = i2;
    }

    public void s(String str) {
        this.f12834j = str;
    }

    public void t(String str) {
        this.f12832h = str;
    }

    public void u(String str) {
        this.f12833i = str;
    }
}
