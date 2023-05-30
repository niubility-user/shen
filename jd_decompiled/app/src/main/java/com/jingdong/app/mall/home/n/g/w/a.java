package com.jingdong.app.mall.home.n.g.w;

import android.graphics.Rect;
import com.jingdong.app.mall.home.floor.common.f;

/* loaded from: classes4.dex */
public class a {
    private f a;
    private f b;

    /* renamed from: c  reason: collision with root package name */
    private int f10379c;
    private f d;

    /* renamed from: e  reason: collision with root package name */
    private int f10380e;

    /* renamed from: f  reason: collision with root package name */
    private f f10381f;

    /* renamed from: g  reason: collision with root package name */
    private int f10382g;

    /* renamed from: h  reason: collision with root package name */
    private c f10383h;

    /* renamed from: i  reason: collision with root package name */
    private c f10384i;

    public int a() {
        return this.f10382g;
    }

    public f b() {
        return this.f10381f;
    }

    public f c() {
        return this.a;
    }

    public f d() {
        return this.b;
    }

    public int e() {
        return this.f10379c;
    }

    public c f() {
        return this.f10384i;
    }

    public c g() {
        return this.f10383h;
    }

    public f h() {
        return this.d;
    }

    public int i() {
        return this.f10380e;
    }

    public a j(int i2, int i3, int i4, Rect rect) {
        this.f10382g = i4;
        f fVar = new f(i2, i3);
        this.f10381f = fVar;
        fVar.F(rect);
        return this;
    }

    public a k(int i2, int i3, Rect rect) {
        f fVar = new f(i2, i3);
        this.a = fVar;
        fVar.F(rect);
        return this;
    }

    public a l(int i2, int i3, Rect rect) {
        f fVar = new f(i2, i3);
        this.b = fVar;
        fVar.F(rect);
        return this;
    }

    public a m(int i2) {
        this.f10379c = i2;
        return this;
    }

    public a n(c cVar) {
        this.f10384i = cVar;
        return this;
    }

    public a o(c cVar) {
        this.f10383h = cVar;
        return this;
    }

    public a p(int i2, int i3, int i4, Rect rect) {
        this.f10380e = i4;
        f fVar = new f(i2, i3);
        this.d = fVar;
        fVar.F(rect);
        return this;
    }
}
