package com.jingdong.manto.q;

/* loaded from: classes16.dex */
public class o {
    public int a = -1;
    public boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public boolean f14079c = false;
    public j d;

    public void a() {
        this.a = -1;
        this.b = false;
        this.f14079c = false;
        j jVar = this.d;
        if (jVar != null) {
            jVar.f();
            this.d.a();
            this.d = null;
        }
    }

    public void a(int i2) {
        if (this.a == i2) {
            a();
        }
    }

    public void a(int i2, boolean z, boolean z2) {
        j jVar;
        if (this.a != i2 && (jVar = this.d) != null && jVar.i().hashCode() == i2) {
            this.d.f();
            this.d.a();
            this.d = null;
        }
        this.a = i2;
        this.b = z;
        this.f14079c = z2;
    }
}
