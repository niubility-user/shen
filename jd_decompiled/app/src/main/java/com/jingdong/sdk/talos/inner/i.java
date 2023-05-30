package com.jingdong.sdk.talos.inner;

/* loaded from: classes.dex */
class i implements d {
    private static i d;
    private d a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private h f15526c;

    private i() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i d() {
        if (d == null) {
            synchronized (i.class) {
                d = new i();
            }
        }
        return d;
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void a(int i2, String str, long j2, String str2, long j3, boolean z, int i3) {
        d dVar = this.a;
        if (dVar != null) {
            dVar.a(i2, str, j2, str2, j3, z, i3);
        }
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void a(String str) {
        d dVar = this.a;
        if (dVar != null) {
            dVar.a(str);
        }
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void a(boolean z) {
        d dVar = this.a;
        if (dVar != null) {
            dVar.a(z);
        }
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void b(h hVar) {
        this.f15526c = hVar;
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void c() {
        d dVar = this.a;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // com.jingdong.sdk.talos.inner.d
    public final void c(String str, String str2, int i2, String str3, String str4) {
        if (this.b) {
            return;
        }
        if (!CProtocol.e()) {
            this.a = null;
            return;
        }
        CProtocol f2 = CProtocol.f();
        this.a = f2;
        f2.b(this.f15526c);
        this.a.c(str, str2, i2, str3, str4);
        this.b = true;
    }
}
