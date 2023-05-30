package com.jingdong.sdk.perfmonitor.a;

import androidx.annotation.NonNull;
import com.coremedia.iso.boxes.FreeBox;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c {
    private long a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private long f15290c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private long f15291e;

    /* renamed from: f  reason: collision with root package name */
    private long f15292f;

    /* renamed from: g  reason: collision with root package name */
    private long f15293g;

    /* renamed from: h  reason: collision with root package name */
    private long f15294h;

    /* renamed from: i  reason: collision with root package name */
    private long f15295i;

    /* renamed from: j  reason: collision with root package name */
    private long f15296j;

    /* renamed from: k  reason: collision with root package name */
    private long f15297k;

    /* renamed from: l  reason: collision with root package name */
    private long f15298l;

    /* renamed from: m  reason: collision with root package name */
    private a f15299m;

    /* loaded from: classes12.dex */
    public interface a {
        void a(long j2, long j3);

        void b(long j2, long j3);
    }

    public c(a aVar) {
        this.f15299m = aVar;
    }

    public void a(long j2, long j3, long j4) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f15298l == 0) {
            this.f15298l = j4;
        }
        if (this.a == 0) {
            this.a = currentTimeMillis;
            this.f15290c = j3;
            this.b = j2;
            this.f15292f = j3;
            this.f15291e = j2;
            this.d = currentTimeMillis;
            this.f15295i = j3;
            this.f15294h = j2;
            this.f15293g = currentTimeMillis;
        }
        long j5 = this.f15291e;
        if (j2 > j5) {
            a aVar = this.f15299m;
            if (aVar != null) {
                aVar.b(j5, j2);
            }
            this.f15292f = j3;
            this.f15291e = j2;
            this.d = currentTimeMillis;
        }
        long j6 = this.f15294h;
        if (j2 < j6) {
            a aVar2 = this.f15299m;
            if (aVar2 != null) {
                aVar2.a(j6, j2);
            }
            this.f15295i = j3;
            this.f15294h = j2;
            this.f15293g = currentTimeMillis;
        }
        this.f15296j += j2;
        this.f15297k++;
    }

    @NonNull
    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("total", this.f15298l);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ts", this.a);
            jSONObject2.put("value", this.b);
            jSONObject2.put(FreeBox.TYPE, this.f15290c);
            jSONObject.put("start", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("ts", this.d);
            jSONObject3.put("value", this.f15291e);
            jSONObject3.put(FreeBox.TYPE, this.f15292f);
            jSONObject.put("max", jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("ts", this.f15293g);
            jSONObject4.put("value", this.f15294h);
            jSONObject4.put(FreeBox.TYPE, this.f15295i);
            jSONObject.put("min", jSONObject4);
            if (this.f15297k > 0) {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("value", this.f15296j / this.f15297k);
                jSONObject.put("avg", jSONObject5);
            }
            jSONObject.put("duration", System.currentTimeMillis() - this.a);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }
}
