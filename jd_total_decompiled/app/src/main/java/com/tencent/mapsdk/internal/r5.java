package com.tencent.mapsdk.internal;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes9.dex */
public class r5 {

    /* renamed from: e  reason: collision with root package name */
    public static final int f17176e = 0;

    /* renamed from: f  reason: collision with root package name */
    public static final int f17177f = 1;
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private JSONArray f17178c;
    private String[] d;

    public r5() {
        this.d = new String[0];
    }

    public r5(int i2, int i3, JSONArray jSONArray) {
        this.d = new String[0];
        this.a = i2;
        this.b = i3;
        this.f17178c = jSONArray;
        if (jSONArray == null) {
            this.d = null;
            return;
        }
        int length = jSONArray.length();
        this.d = new String[length];
        for (int i4 = 0; i4 < length; i4++) {
            try {
                this.d[i4] = this.f17178c.getString(i4);
            } catch (JSONException e2) {
                this.d = null;
                ma.b(Log.getStackTraceString(e2));
                return;
            }
        }
    }

    public JSONArray a() {
        return this.f17178c;
    }

    public String[] b() {
        return this.d;
    }

    public int c() {
        return this.a;
    }

    public int d() {
        return this.b;
    }

    public boolean e() {
        return this.a == 1;
    }

    public boolean f() {
        return this.b == 1;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("IndoorAuth{");
        stringBuffer.append("mEnabled=");
        stringBuffer.append(this.a);
        stringBuffer.append(", mType=");
        stringBuffer.append(this.b);
        stringBuffer.append(", mBuildingJsonArray=");
        stringBuffer.append(this.f17178c);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
