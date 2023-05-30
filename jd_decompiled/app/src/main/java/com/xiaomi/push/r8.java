package com.xiaomi.push;

/* loaded from: classes11.dex */
public class r8 {
    private final y8 a;
    private final i9 b;

    public r8(b9 b9Var) {
        i9 i9Var = new i9();
        this.b = i9Var;
        this.a = b9Var.a(i9Var);
    }

    public void a(n8 n8Var, byte[] bArr) {
        try {
            this.b.h(bArr);
            n8Var.a(this.a);
        } finally {
            this.a.I();
        }
    }
}
