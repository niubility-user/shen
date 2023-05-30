package com.jdpay.membercode.f;

import android.graphics.Bitmap;

/* loaded from: classes18.dex */
public class a extends c {

    /* renamed from: c  reason: collision with root package name */
    private int f7534c;
    private int d;

    @Override // com.jdpay.membercode.f.c
    protected Bitmap b() {
        return b.b(this.a, this.f7534c, this.d);
    }

    public void f(int i2, int i3) {
        this.f7534c = i2;
        this.d = i3;
    }

    public boolean g() {
        return this.f7534c > 0 && this.d > 0;
    }
}
