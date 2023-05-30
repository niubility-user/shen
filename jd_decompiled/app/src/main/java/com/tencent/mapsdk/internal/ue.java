package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;

/* loaded from: classes9.dex */
public class ue extends ve implements g8 {
    public static final int p = 0;
    public static final int q = 1;
    public static final int r = 2;
    public static final int s = 1;
    public static final int t = 2;
    public static final int u = 3;
    public static final int v = 4;

    /* renamed from: g  reason: collision with root package name */
    private int f17323g;

    /* renamed from: h  reason: collision with root package name */
    public float f17324h = 1.0f;

    /* renamed from: i  reason: collision with root package name */
    public float f17325i = 1.0f;

    /* renamed from: j  reason: collision with root package name */
    public float f17326j = 1.0f;

    /* renamed from: k  reason: collision with root package name */
    public float f17327k = 0.0f;

    /* renamed from: l  reason: collision with root package name */
    public float f17328l = 0.0f;

    /* renamed from: m  reason: collision with root package name */
    public float f17329m = 0.0f;

    /* renamed from: n  reason: collision with root package name */
    private a f17330n;
    private b o;

    /* loaded from: classes9.dex */
    public interface a {
        Bitmap a(int i2);

        boolean a();

        String b();
    }

    /* loaded from: classes9.dex */
    public interface b {
        void a(Bitmap bitmap);
    }

    @Override // com.tencent.mapsdk.internal.g8
    public void a(int i2, Object obj) {
        if (i2 == 1) {
            this.f17324h = ((Number) obj).floatValue();
        } else if (i2 == 2) {
            o5 o5Var = (o5) obj;
            this.f17325i = (float) o5Var.a;
            this.f17326j = (float) o5Var.b;
        } else if (i2 != 3) {
            if (i2 != 4) {
                return;
            }
            this.f17329m = ((Number) obj).floatValue();
        } else {
            o5 o5Var2 = (o5) obj;
            this.f17327k = (float) o5Var2.a;
            this.f17328l = (float) o5Var2.b;
        }
    }

    public synchronized void a(a aVar) {
        this.f17330n = aVar;
    }

    public void b(int i2) {
        this.f17323g = i2;
        this.f17324h = 1.0f;
        this.f17325i = 1.0f;
        this.f17326j = 1.0f;
        this.f17327k = 0.0f;
        this.f17328l = 0.0f;
    }

    public int c() {
        return this.f17323g;
    }
}
