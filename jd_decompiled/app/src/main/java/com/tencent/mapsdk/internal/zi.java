package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class zi implements xd {
    private ce a;
    private ej b;

    /* renamed from: c  reason: collision with root package name */
    private yd f17587c;
    private boolean d;

    public zi(xi xiVar, String str) {
        this.b = xiVar;
        this.a = new cj(xiVar.getContext(), str);
        this.f17587c = new aj(xiVar.getContext(), str);
    }

    @Override // com.tencent.mapsdk.internal.xd
    public void a(boolean z) {
        this.d = z;
    }

    @Override // com.tencent.mapsdk.internal.xd
    public boolean a() {
        return this.d;
    }

    @Override // com.tencent.mapsdk.internal.xd
    public yd b() {
        return this.f17587c;
    }

    @Override // com.tencent.mapsdk.internal.xd
    public hb c() {
        return new yi(this.b);
    }

    @Override // com.tencent.mapsdk.internal.xd
    public ce d() {
        return this.a;
    }
}
