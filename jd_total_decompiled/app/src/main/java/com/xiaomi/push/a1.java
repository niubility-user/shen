package com.xiaomi.push;

import com.jingdong.common.apkcenter.ApkDownloadTable;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class a1 {
    private int a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private long f18447c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private long f18448e;

    public a1() {
        this(0, 0L, 0L, null);
    }

    public a1(int i2, long j2, long j3, Exception exc) {
        this.a = i2;
        this.b = j2;
        this.f18448e = j3;
        this.f18447c = System.currentTimeMillis();
        if (exc != null) {
            this.d = exc.getClass().getSimpleName();
        }
    }

    public int a() {
        return this.a;
    }

    public a1 b(JSONObject jSONObject) {
        this.b = jSONObject.getLong("cost");
        this.f18448e = jSONObject.getLong(ApkDownloadTable.FIELD_SIZE);
        this.f18447c = jSONObject.getLong("ts");
        this.a = jSONObject.getInt("wt");
        this.d = jSONObject.optString("expt");
        return this;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cost", this.b);
        jSONObject.put(ApkDownloadTable.FIELD_SIZE, this.f18448e);
        jSONObject.put("ts", this.f18447c);
        jSONObject.put("wt", this.a);
        jSONObject.put("expt", this.d);
        return jSONObject;
    }
}
