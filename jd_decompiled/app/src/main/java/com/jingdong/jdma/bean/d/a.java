package com.jingdong.jdma.bean.d;

import com.jd.dynamic.DYConstants;

/* loaded from: classes12.dex */
public class a {
    private String a;
    private String[] b;

    /* renamed from: c  reason: collision with root package name */
    private int f12640c;

    public a() {
    }

    public void a(String str) {
        this.a = str;
    }

    public String[] b() {
        return this.b;
    }

    public String c() {
        return this.a;
    }

    public String toString() {
        String str = "";
        if (this.b != null) {
            for (int i2 = 0; i2 < this.b.length; i2++) {
                str = str + this.b[i2] + DYConstants.DY_REGEX_COMMA;
            }
        }
        return "ReportReturnObject={tableName:" + this.a + ",id:" + str + "}";
    }

    public a(String str, int i2) {
        this.a = str;
        this.f12640c = i2;
    }

    public int a() {
        return this.f12640c;
    }

    public void a(String[] strArr) {
        this.b = strArr;
    }
}
