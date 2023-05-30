package com.jingdong.jdpush_new.g.c;

import com.jingdong.common.messagecenter.NotificationMessageSummary;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class d {
    private Integer a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f12842c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f12843e;

    /* renamed from: f  reason: collision with root package name */
    private String f12844f;

    public static d g(String str) {
        try {
            d dVar = new d();
            JSONObject jSONObject = new JSONObject(str);
            dVar.j(jSONObject.has("id") ? Integer.valueOf(jSONObject.getInt("id")) : null);
            dVar.h(jSONObject.has("appId") ? jSONObject.getString("appId") : null);
            dVar.i(jSONObject.has("command") ? jSONObject.getString("command") : null);
            dVar.m(jSONObject.has("status") ? jSONObject.getString("status") : null);
            dVar.k(jSONObject.has(NotificationMessageSummary.MSG_BODY) ? jSONObject.getString(NotificationMessageSummary.MSG_BODY) : null);
            dVar.l(jSONObject.has(NotificationMessageSummary.MSG_SEQ) ? jSONObject.getString(NotificationMessageSummary.MSG_SEQ) : null);
            return dVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String n(d dVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", dVar.c());
            jSONObject.put("appId", dVar.a());
            jSONObject.put("command", dVar.b());
            jSONObject.put("status", dVar.f());
            jSONObject.put(NotificationMessageSummary.MSG_BODY, dVar.d());
            jSONObject.put(NotificationMessageSummary.MSG_SEQ, dVar.e());
            return jSONObject.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public Integer c() {
        return this.a;
    }

    public String d() {
        return this.f12842c;
    }

    public String e() {
        return this.f12844f;
    }

    public String f() {
        return this.f12843e;
    }

    public void h(String str) {
        this.b = str;
    }

    public void i(String str) {
        this.d = str;
    }

    public void j(Integer num) {
        this.a = num;
    }

    public void k(String str) {
        this.f12842c = str;
    }

    public void l(String str) {
        this.f12844f = str;
    }

    public void m(String str) {
        this.f12843e = str;
    }
}
