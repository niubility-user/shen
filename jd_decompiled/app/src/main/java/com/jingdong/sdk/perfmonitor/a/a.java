package com.jingdong.sdk.perfmonitor.a;

import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a {

    /* renamed from: g  reason: collision with root package name */
    private static int f15278g = 1000;
    private int a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private int f15279c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private int f15280e;

    /* renamed from: f  reason: collision with root package name */
    private int f15281f;

    public void a(int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        this.f15281f++;
        this.f15280e += i2;
        if (this.b == 0) {
            this.b = currentTimeMillis;
            this.a = i2;
        }
        if (i2 >= this.f15279c) {
            this.f15279c = i2;
            this.d = currentTimeMillis;
        }
    }

    @NonNull
    public String toString() {
        int i2;
        int i3;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ts", this.b);
            jSONObject2.put("value", this.a);
            jSONObject.put("start", jSONObject2);
            int i4 = this.f15279c;
            if (i4 > 0 && i4 < f15278g) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("ts", this.d);
                jSONObject3.put("value", this.f15279c);
                jSONObject.put("max", jSONObject3);
            }
            int i5 = this.f15281f;
            if (i5 > 0 && (i2 = this.f15280e) > 0 && (i3 = i2 / i5) > 0 && i3 < f15278g) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("value", i3);
                jSONObject.put("avg", jSONObject4);
            }
            jSONObject.put("duration", System.currentTimeMillis() - this.b);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }
}
