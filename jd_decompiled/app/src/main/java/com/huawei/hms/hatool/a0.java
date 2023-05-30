package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a0 implements g {
    private Context a = q0.i();
    private String b;

    /* renamed from: c */
    private JSONObject f1346c;
    private String d;

    /* renamed from: e */
    private String f1347e;

    /* renamed from: f */
    private String f1348f;

    /* renamed from: g */
    private String f1349g;

    /* renamed from: h */
    private Boolean f1350h;

    public a0(String str, JSONObject jSONObject, String str2, String str3, long j2) {
        this.b = str;
        this.f1346c = jSONObject;
        this.d = str2;
        this.f1347e = str3;
        this.f1348f = String.valueOf(j2);
        if (z.i(str2, "oper")) {
            p0 a = y.a().a(str2, j2);
            this.f1349g = a.a();
            this.f1350h = Boolean.valueOf(a.b());
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        JSONArray jSONArray;
        v.c("hmsSdk", "Begin to run EventRecordTask...");
        int h2 = q0.h();
        int k2 = a1.k(this.d, this.f1347e);
        if (c0.a(this.a, "stat_v2_1", h2 * 1048576)) {
            v.c("hmsSdk", "stat sp file reach max limited size, discard new event");
            e.a().a("", "alltype");
            return;
        }
        b1 b1Var = new b1();
        b1Var.b(this.b);
        b1Var.a(this.f1346c.toString());
        b1Var.d(this.f1347e);
        b1Var.c(this.f1348f);
        b1Var.f(this.f1349g);
        Boolean bool = this.f1350h;
        b1Var.e(bool == null ? null : String.valueOf(bool));
        try {
            JSONObject d = b1Var.d();
            String a = n1.a(this.d, this.f1347e);
            String a2 = d.a(this.a, "stat_v2_1", a, "");
            try {
                jSONArray = !TextUtils.isEmpty(a2) ? new JSONArray(a2) : new JSONArray();
            } catch (JSONException unused) {
                v.d("hmsSdk", "Cached data corrupted: stat_v2_1");
                jSONArray = new JSONArray();
            }
            jSONArray.put(d);
            d.b(this.a, "stat_v2_1", a, jSONArray.toString());
            if (jSONArray.toString().length() > k2 * 1024) {
                e.a().a(this.d, this.f1347e);
            }
        } catch (JSONException unused2) {
            v.e("hmsSdk", "eventRecord toJson error! The record failed.");
        }
    }
}
