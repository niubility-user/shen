package com.cmic.sso.sdk.c.d;

/* loaded from: classes.dex */
public class a {
    private int a;
    private String b;

    private a(int i2, String str) {
        this.a = i2;
        this.b = str;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public static a a(int i2) {
        switch (i2) {
            case 102102:
                return new a(102102, "\u7f51\u7edc\u5f02\u5e38");
            case 102223:
                return new a(102223, "\u6570\u636e\u89e3\u6790\u5f02\u5e38");
            case 102508:
                return new a(102508, "\u6570\u636e\u7f51\u7edc\u5207\u6362\u5931\u8d25");
            case 200025:
                return new a(200025, "\u767b\u5f55\u8d85\u65f6");
            case 200039:
                return new a(200039, "\u7535\u4fe1\u53d6\u53f7\u63a5\u53e3\u5931\u8d25");
            case 200050:
                return new a(200050, "EOF\u5f02\u5e38");
            default:
                return new a(i2, "\u7f51\u7edc\u5f02\u5e38");
        }
    }
}
