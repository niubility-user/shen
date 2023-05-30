package com.caverock.androidsvg;

import com.caverock.androidsvg.b;
import com.caverock.androidsvg.h;

/* loaded from: classes.dex */
public class g {
    b.r a;
    f b;

    /* renamed from: c  reason: collision with root package name */
    String f846c;
    h.b d;

    /* renamed from: e  reason: collision with root package name */
    String f847e;

    /* renamed from: f  reason: collision with root package name */
    h.b f848f;

    public g() {
        this.a = null;
        this.b = null;
        this.f846c = null;
        this.d = null;
        this.f847e = null;
        this.f848f = null;
    }

    public g a(String str) {
        this.a = new b(b.u.RenderOptions).d(str);
        return this;
    }

    public boolean b() {
        b.r rVar = this.a;
        return rVar != null && rVar.f() > 0;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        return this.f846c != null;
    }

    public boolean e() {
        return this.f847e != null;
    }

    public boolean f() {
        return this.d != null;
    }

    public boolean g() {
        return this.f848f != null;
    }

    public g h(float f2, float f3, float f4, float f5) {
        this.f848f = new h.b(f2, f3, f4, f5);
        return this;
    }

    public g(g gVar) {
        this.a = null;
        this.b = null;
        this.f846c = null;
        this.d = null;
        this.f847e = null;
        this.f848f = null;
        if (gVar == null) {
            return;
        }
        this.a = gVar.a;
        this.b = gVar.b;
        this.d = gVar.d;
        this.f847e = gVar.f847e;
        this.f848f = gVar.f848f;
    }
}
