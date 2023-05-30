package com.jingdong.app.mall.home.floor.bottomfloat;

import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes4.dex */
public abstract class BaseFloatPriority {

    /* renamed from: h */
    private static final String f9137h = "BaseFloatPriority";
    boolean a;
    boolean b;

    /* renamed from: c */
    boolean f9138c;
    boolean d;

    /* renamed from: e */
    int f9139e;

    /* renamed from: f */
    boolean f9140f;

    /* renamed from: g */
    private String f9141g;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PriorityDef {
    }

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ boolean f9142g;

        a(boolean z) {
            BaseFloatPriority.this = r1;
            this.f9142g = z;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            com.jingdong.app.mall.home.floor.bottomfloat.a.d().e(BaseFloatPriority.this, this.f9142g);
        }
    }

    public BaseFloatPriority(String str, int i2) {
        this(str, i2, true);
    }

    public boolean a() {
        return !this.f9138c && (com.jingdong.app.mall.home.floor.bottomfloat.a.d().b(this) || this.f9139e < 10);
    }

    public void b(boolean z) {
        c(z, 500L);
    }

    public void c(boolean z, long j2) {
        this.d = true;
        this.f9138c = z;
        if (k.v()) {
            f.r0(f9137h, this.f9141g + " \u5173\u95ed");
        }
        if (j2 > 0) {
            f.F0(new a(z), j2);
        } else {
            com.jingdong.app.mall.home.floor.bottomfloat.a.d().e(this, z);
        }
    }

    public void d(int i2) {
        if (m(i2)) {
            this.d = true;
            g(i2);
            if (k.v()) {
                f.r0(f9137h, this.f9141g + " \u9690\u85cf");
            }
        }
    }

    public boolean e() {
        return false;
    }

    public boolean f(int i2) {
        return this.f9139e == i2;
    }

    protected abstract void g(int i2);

    public abstract void h();

    void i(int i2) {
        if (f(i2) && a()) {
            if (this.d) {
                k();
                com.jingdong.app.mall.home.floor.bottomfloat.a.d().g(this);
                return;
            }
            return;
        }
        d(i2);
    }

    public void j() {
        this.b = true;
    }

    public void k() {
        if (k.v()) {
            f.r0(f9137h, this.f9141g + " \u663e\u793a");
        }
        this.d = false;
        this.f9138c = false;
    }

    public void l() {
        try {
            i(this.f9139e);
        } catch (Exception e2) {
            f.s0(f9137h, e2);
        }
    }

    public boolean m(int i2) {
        return true;
    }

    public BaseFloatPriority(String str, int i2, boolean z) {
        this.b = false;
        this.f9138c = false;
        this.d = true;
        this.f9141g = str;
        this.f9139e = i2;
        this.f9140f = z;
        this.a = i2 > 100;
        com.jingdong.app.mall.home.floor.bottomfloat.a.d().a(this);
    }
}
