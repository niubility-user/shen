package com.jingdong.app.mall.lockscreen.slider;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;

/* loaded from: classes4.dex */
public class b {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private float f11174c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private float f11175e;

    /* renamed from: f  reason: collision with root package name */
    private float f11176f;

    /* renamed from: g  reason: collision with root package name */
    private float f11177g;

    /* renamed from: h  reason: collision with root package name */
    private float f11178h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f11179i;

    /* renamed from: j  reason: collision with root package name */
    private float f11180j;

    /* renamed from: k  reason: collision with root package name */
    private e f11181k;

    /* renamed from: l  reason: collision with root package name */
    private d f11182l;

    /* renamed from: com.jingdong.app.mall.lockscreen.slider.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public static class C0344b {
        private b a = new b();

        public b a() {
            return this.a;
        }

        public C0344b b(@FloatRange(from = 0.10000000149011612d, to = 0.8999999761581421d) float f2) {
            this.a.f11178h = f2;
            return this;
        }

        public C0344b c(e eVar) {
            this.a.f11181k = eVar;
            return this;
        }

        public C0344b d(@ColorInt int i2) {
            this.a.d = i2;
            return this;
        }

        public C0344b e(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
            this.a.f11176f = f2;
            return this;
        }

        public C0344b f(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
            this.a.f11175e = f2;
            return this;
        }

        public C0344b g(float f2) {
            this.a.f11174c = f2;
            return this;
        }

        public C0344b h(float f2) {
            this.a.f11177g = f2;
            return this;
        }
    }

    public boolean h() {
        return (this.a == -1 || this.b == -1) ? false : true;
    }

    public float i() {
        return this.f11178h;
    }

    public float j(float f2) {
        return this.f11180j * f2;
    }

    public d k() {
        return this.f11182l;
    }

    public e l() {
        return this.f11181k;
    }

    public int m() {
        return this.a;
    }

    public int n() {
        return this.d;
    }

    public float o() {
        return this.f11176f;
    }

    public float p() {
        return this.f11175e;
    }

    public int q() {
        return this.b;
    }

    public float r() {
        return this.f11174c;
    }

    public float s() {
        return this.f11177g;
    }

    public boolean t() {
        return this.f11179i;
    }

    private b() {
        this.a = -1;
        this.b = -1;
        this.f11174c = 1.0f;
        this.d = -16777216;
        this.f11175e = 0.8f;
        this.f11176f = 0.0f;
        this.f11177g = 5.0f;
        this.f11178h = 0.25f;
        this.f11179i = false;
        this.f11180j = 0.18f;
        this.f11181k = e.LEFT;
    }
}
