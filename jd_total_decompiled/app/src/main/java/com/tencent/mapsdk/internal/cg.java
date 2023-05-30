package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class cg {
    public final String a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final int f16373c;
    public final String[] d;

    public cg(String str, String str2, String[] strArr, int i2) {
        this.a = str;
        this.b = str2;
        this.d = a(strArr);
        this.f16373c = i2;
    }

    private String[] a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        int length = strArr.length;
        String[] strArr2 = new String[length];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        return strArr2;
    }
}
