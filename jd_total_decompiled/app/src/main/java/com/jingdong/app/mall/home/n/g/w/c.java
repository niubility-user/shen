package com.jingdong.app.mall.home.n.g.w;

import android.graphics.Rect;
import com.jingdong.app.mall.home.floor.common.f;

/* loaded from: classes4.dex */
public class c {
    private f a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private int f10386c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f10387e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f10388f = true;

    public boolean a() {
        return this.f10388f;
    }

    public int b() {
        return this.f10386c;
    }

    public int c() {
        return this.f10387e;
    }

    public int d() {
        return this.d;
    }

    public f e() {
        return this.a;
    }

    public boolean f() {
        return this.a == null;
    }

    public boolean g() {
        return this.b;
    }

    public c h(boolean z) {
        this.f10388f = z;
        return this;
    }

    public c i(int i2) {
        this.f10386c = i2;
        return this;
    }

    public c j() {
        this.b = true;
        return this;
    }

    public c k(int i2) {
        this.f10387e = i2;
        return this;
    }

    public c l(int i2) {
        this.d = i2;
        return this;
    }

    public c m(int i2, int i3, Rect rect) {
        f fVar = new f(i2, i3);
        this.a = fVar;
        fVar.F(rect);
        return this;
    }
}
