package com.jingdong.jdma.bean.e;

/* loaded from: classes12.dex */
public class d {
    private c a;
    private String b;

    public d() {
        this.a = new c();
    }

    public String a() {
        return this.b;
    }

    public c b() {
        return this.a;
    }

    public void c() {
        this.a.b();
    }

    public d(String str, c cVar) {
        this.b = str;
        this.a = cVar;
    }
}
