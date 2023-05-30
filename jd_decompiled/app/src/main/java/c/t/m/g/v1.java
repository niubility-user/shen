package c.t.m.g;

import java.util.Arrays;

/* loaded from: classes.dex */
public class v1 {
    public int a;
    public int[] b;

    /* renamed from: c  reason: collision with root package name */
    public int f718c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public int f719e;

    public v1(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("cacheSize max > 0.");
        }
        this.a = i2;
        this.b = new int[i2];
        a();
    }

    public void a() {
        this.d = 0;
        this.f719e = 0;
        this.f718c = 0;
        Arrays.fill(this.b, 0);
    }

    public void b(int i2) {
        int i3 = this.f718c;
        int[] iArr = this.b;
        int i4 = this.d;
        int i5 = i3 - iArr[i4];
        this.f718c = i5;
        this.f718c = i5 + i2;
        iArr[i4] = i2;
        int i6 = i4 + 1;
        this.d = i6;
        if (i6 == this.a) {
            this.d = 0;
        }
        int i7 = this.f719e;
        if (i7 < Integer.MAX_VALUE) {
            this.f719e = i7 + 1;
        }
    }

    public int c() {
        return this.a;
    }

    public final int d(int i2) {
        int i3 = this.f719e;
        int i4 = this.a;
        return i3 < i4 ? i2 : ((this.d + i2) + i4) % i4;
    }

    public int e() {
        int i2 = this.f719e;
        int i3 = this.a;
        return i2 < i3 ? i2 : i3;
    }

    public int f(int i2) {
        if (i2 < 0 || i2 >= e()) {
            throw new ArrayIndexOutOfBoundsException("cache max size is " + this.a + ",current size is " + e() + ",index is " + i2);
        }
        return this.b[d(i2)];
    }
}
