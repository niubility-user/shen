package com.jingdong.jdpush_new.g.c;

import com.jingdong.common.messagecenter.NotificationMessageSummary;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f12835c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f12836e;

    /* renamed from: f  reason: collision with root package name */
    private String f12837f;

    /* renamed from: g  reason: collision with root package name */
    private String f12838g;

    /* renamed from: h  reason: collision with root package name */
    private String f12839h;

    /* renamed from: i  reason: collision with root package name */
    private String f12840i;

    /* renamed from: j  reason: collision with root package name */
    private String f12841j;

    public static c k(String str) {
        try {
            c cVar = new c();
            JSONObject jSONObject = new JSONObject(str);
            cVar.l(jSONObject.has("appId") ? jSONObject.getString("appId") : null);
            cVar.s(jSONObject.has("packageName") ? jSONObject.getString("packageName") : null);
            cVar.q(jSONObject.has("msgseq") ? jSONObject.getString("msgseq") : null);
            cVar.o(jSONObject.has(NotificationMessageSummary.ECHO) ? jSONObject.getString(NotificationMessageSummary.ECHO) : null);
            cVar.p(jSONObject.has("msg") ? jSONObject.getString("msg") : null);
            cVar.r(jSONObject.has("mui") ? jSONObject.getString("mui") : null);
            cVar.u(jSONObject.has("status") ? jSONObject.getString("status") : null);
            cVar.n(jSONObject.has("createTime") ? jSONObject.getString("createTime") : null);
            cVar.t(jSONObject.has("sign") ? jSONObject.getString("sign") : null);
            cVar.m(jSONObject.optString("callbackParam"));
            return cVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String v(c cVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appId", cVar.a());
            jSONObject.put("msgseq", cVar.f());
            jSONObject.put("packageName", cVar.h());
            jSONObject.put(NotificationMessageSummary.ECHO, cVar.d());
            jSONObject.put("msg", cVar.e());
            jSONObject.put("mui", cVar.g());
            jSONObject.put("status", cVar.j());
            jSONObject.put("createTime", cVar.c());
            jSONObject.put("sign", cVar.i());
            jSONObject.put("callbackParam", cVar.b());
            return jSONObject.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.f12841j;
    }

    public String c() {
        return this.f12838g;
    }

    public String d() {
        return this.f12837f;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.b;
    }

    public String g() {
        return this.f12835c;
    }

    public String h() {
        return this.f12839h;
    }

    public String i() {
        return this.f12840i;
    }

    public String j() {
        return this.f12836e;
    }

    public void l(String str) {
        this.a = str;
    }

    public void m(String str) {
        this.f12841j = str;
    }

    public void n(String str) {
        this.f12838g = str;
    }

    public void o(String str) {
        this.f12837f = str;
    }

    public void p(String str) {
        this.d = str;
    }

    public void q(String str) {
        this.b = str;
    }

    public void r(String str) {
        this.f12835c = str;
    }

    public void s(String str) {
        this.f12839h = str;
    }

    public void t(String str) {
        this.f12840i = str;
    }

    public void u(String str) {
        this.f12836e = str;
    }
}
