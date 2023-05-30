package com.tencent.tmsqmsp.oaid2;

/* loaded from: classes9.dex */
public class z {
    public int a;
    public long b = System.currentTimeMillis() + 86400000;

    /* renamed from: c  reason: collision with root package name */
    public String f18102c;

    public z(String str, int i2) {
        this.f18102c = str;
        this.a = i2;
    }

    public String toString() {
        return "ValueData{value='" + this.f18102c + "', code=" + this.a + ", expired=" + this.b + '}';
    }
}
