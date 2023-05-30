package com.jingdong.app.mall.home.u;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes4.dex */
public class c {
    public int a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public int f10976c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public int f10977e;

    /* renamed from: f  reason: collision with root package name */
    public String f10978f;

    public void a(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.a = jDJSONObject.optInt("homeAreaCode");
        this.b = jDJSONObject.optString("areaName");
        this.f10976c = jDJSONObject.optInt("area1Id", -1);
        this.d = jDJSONObject.optString("area1Name");
        this.f10977e = jDJSONObject.optInt("area2Id", -1);
        this.f10978f = jDJSONObject.optString("area2Name");
    }

    public boolean b() {
        return this.f10976c == -1 || TextUtils.isEmpty(this.d) || this.f10977e == -1 || TextUtils.isEmpty(this.f10978f);
    }
}
