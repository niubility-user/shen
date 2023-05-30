package com.jdpay.membercode.f;

import android.graphics.Bitmap;

/* loaded from: classes18.dex */
public class e extends c {

    /* renamed from: c  reason: collision with root package name */
    private int f7542c;
    private Bitmap d;

    @Override // com.jdpay.membercode.f.c
    protected Bitmap b() {
        String str = this.a;
        int i2 = this.f7542c;
        return b.c(str, i2, i2, null, this.d, 1);
    }

    @Override // com.jdpay.membercode.f.c
    public void c() {
        super.c();
    }

    public void f(int i2) {
        this.f7542c = i2;
    }

    public void g(Bitmap bitmap) {
        this.d = bitmap;
    }

    public Bitmap h() {
        return this.d;
    }

    public boolean i() {
        return this.f7542c > 0;
    }
}
