package com.jdjr.risk.device.entity;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class n {
    public static ArrayList<n> a = new ArrayList<>();
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public int f7443c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public int f7444e;

    /* renamed from: f  reason: collision with root package name */
    public int f7445f;

    /* renamed from: g  reason: collision with root package name */
    public int f7446g;

    /* renamed from: h  reason: collision with root package name */
    public int f7447h;

    /* renamed from: i  reason: collision with root package name */
    public int f7448i;

    /* renamed from: j  reason: collision with root package name */
    public int f7449j;

    /* renamed from: k  reason: collision with root package name */
    public int f7450k;

    /* renamed from: l  reason: collision with root package name */
    public int f7451l;

    /* renamed from: m  reason: collision with root package name */
    public int f7452m;

    /* renamed from: n  reason: collision with root package name */
    public int f7453n;
    public int o;
    public int p;
    public int q;
    private final int r = 100000;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;

    public n(long j2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, float f22) {
        this.b = j2;
        this.f7443c = (int) (f2 * 100000.0f);
        this.d = (int) (f3 * 100000.0f);
        this.f7444e = (int) (f4 * 100000.0f);
        this.f7445f = (int) (f5 * 100000.0f);
        this.f7446g = (int) (f6 * 100000.0f);
        this.f7447h = (int) (f7 * 100000.0f);
        this.s = (int) (f8 * 100000.0f);
        this.t = (int) (f9 * 100000.0f);
        this.u = (int) (f10 * 100000.0f);
        this.f7448i = (int) (f11 * 100000.0f);
        this.f7449j = (int) (f12 * 100000.0f);
        this.f7450k = (int) (f13 * 100000.0f);
        this.f7451l = (int) (f14 * 100000.0f);
        this.f7452m = (int) (f15 * 100000.0f);
        this.f7453n = (int) (f16 * 100000.0f);
        this.o = (int) (f17 * 100000.0f);
        this.p = (int) (f18 * 100000.0f);
        this.q = (int) (f19 * 100000.0f);
        this.v = (int) (f20 * 100000.0f);
        this.w = (int) (f21 * 100000.0f);
        this.x = (int) (100000.0f * f22);
    }

    public JSONObject a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("time", this.b + "");
            jSONObject.put("ax_x", this.f7443c + "");
            jSONObject.put("ax_y", this.d + "");
            jSONObject.put("ax_z", this.f7444e + "");
            jSONObject.put("alx_x", this.f7445f + "");
            jSONObject.put("alx_y", this.f7446g + "");
            jSONObject.put("alx_z", this.f7447h + "");
            jSONObject.put("or_x", this.s + "");
            jSONObject.put("or_y", this.t + "");
            jSONObject.put("or_z", this.u + "");
            jSONObject.put("gr_x", this.f7448i + "");
            jSONObject.put("gr_y", this.f7449j + "");
            jSONObject.put("gr_z", this.f7450k + "");
            jSONObject.put("gv_x", this.f7451l + "");
            jSONObject.put("gv_y", this.f7452m + "");
            jSONObject.put("gv_z", this.f7453n + "");
            jSONObject.put("prs_x", this.o + "");
            jSONObject.put("prs_y", this.p + "");
            jSONObject.put("prs_z", this.q + "");
            jSONObject.put("mg_x", this.v + "");
            jSONObject.put("mg_y", this.w + "");
            jSONObject.put("mg_z", this.x + "");
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String toString() {
        JSONObject a2 = a();
        return a2 == null ? "" : a2.toString();
    }
}
