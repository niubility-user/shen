package com.xiaomi.push;

import java.io.ByteArrayOutputStream;

/* loaded from: classes11.dex */
public class t8 {
    private final ByteArrayOutputStream a;
    private final g9 b;

    /* renamed from: c  reason: collision with root package name */
    private y8 f19241c;

    public t8(b9 b9Var) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.a = byteArrayOutputStream;
        g9 g9Var = new g9(byteArrayOutputStream);
        this.b = g9Var;
        this.f19241c = b9Var.a(g9Var);
    }

    public byte[] a(n8 n8Var) {
        this.a.reset();
        n8Var.b(this.f19241c);
        return this.a.toByteArray();
    }
}
