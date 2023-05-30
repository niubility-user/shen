package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class si implements b1 {

    /* renamed from: g  reason: collision with root package name */
    private final xi f17264g;

    public si(xi xiVar) {
        this.f17264g = xiVar;
    }

    @Override // com.tencent.mapsdk.internal.b1
    public void a(v vVar) {
        xi xiVar = this.f17264g;
        if (xiVar == null) {
            return;
        }
        if (vVar != null) {
            xiVar.c(xiVar.getMap().M().x());
        }
        this.f17264g.o0();
    }
}
