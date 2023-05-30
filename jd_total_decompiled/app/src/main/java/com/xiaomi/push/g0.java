package com.xiaomi.push;

/* loaded from: classes11.dex */
public class g0 implements i0 {
    private final String a;
    private final String b;

    public g0(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.a = str;
        this.b = str2;
    }

    @Override // com.xiaomi.push.i0
    public String a() {
        return this.a;
    }

    @Override // com.xiaomi.push.i0
    public String b() {
        return this.b;
    }
}
