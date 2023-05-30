package com.meizu.cloud.pushsdk.e.h;

/* loaded from: classes14.dex */
public final class j {
    final byte[] a;
    int b;

    /* renamed from: c */
    int f15858c;
    boolean d;

    /* renamed from: e */
    final boolean f15859e;

    /* renamed from: f */
    j f15860f;

    /* renamed from: g */
    j f15861g;

    public j() {
        this.a = new byte[2048];
        this.f15859e = true;
        this.d = false;
    }

    public j(j jVar) {
        this(jVar.a, jVar.b, jVar.f15858c);
    }

    j(byte[] bArr, int i2, int i3) {
        this.a = bArr;
        this.b = i2;
        this.f15858c = i3;
        this.f15859e = false;
        this.d = true;
    }

    public j a(int i2) {
        if (i2 <= 0 || i2 > this.f15858c - this.b) {
            throw new IllegalArgumentException();
        }
        j jVar = new j(this);
        jVar.f15858c = jVar.b + i2;
        this.b += i2;
        this.f15861g.b(jVar);
        return jVar;
    }

    public j b(j jVar) {
        jVar.f15861g = this;
        jVar.f15860f = this.f15860f;
        this.f15860f.f15861g = jVar;
        this.f15860f = jVar;
        return jVar;
    }

    public void c() {
        j jVar = this.f15861g;
        if (jVar == this) {
            throw new IllegalStateException();
        }
        if (jVar.f15859e) {
            int i2 = this.f15858c - this.b;
            if (i2 > (2048 - jVar.f15858c) + (jVar.d ? 0 : jVar.b)) {
                return;
            }
            d(jVar, i2);
            e();
            k.b(this);
        }
    }

    public void d(j jVar, int i2) {
        if (!jVar.f15859e) {
            throw new IllegalArgumentException();
        }
        int i3 = jVar.f15858c;
        int i4 = i3 + i2;
        if (i4 > 2048) {
            if (jVar.d) {
                throw new IllegalArgumentException();
            }
            int i5 = jVar.b;
            if (i4 - i5 > 2048) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = jVar.a;
            System.arraycopy(bArr, i5, bArr, 0, i3 - i5);
            jVar.f15858c -= jVar.b;
            jVar.b = 0;
        }
        System.arraycopy(this.a, this.b, jVar.a, jVar.f15858c, i2);
        jVar.f15858c += i2;
        this.b += i2;
    }

    public j e() {
        j jVar = this.f15860f;
        j jVar2 = jVar != this ? jVar : null;
        j jVar3 = this.f15861g;
        jVar3.f15860f = jVar;
        this.f15860f.f15861g = jVar3;
        this.f15860f = null;
        this.f15861g = null;
        return jVar2;
    }
}
