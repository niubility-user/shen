package com.jdpay.membercode.f;

import android.graphics.Bitmap;

/* loaded from: classes18.dex */
public abstract class c {
    protected String a;
    protected Bitmap b;

    public Bitmap a() {
        Bitmap b = b();
        this.b = b;
        return b;
    }

    protected abstract Bitmap b();

    public void c() {
        this.b = null;
        this.a = null;
    }

    public String d() {
        return this.a;
    }

    public Bitmap e() {
        return this.b;
    }
}
