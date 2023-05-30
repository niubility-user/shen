package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class bj implements g5 {

    /* renamed from: g  reason: collision with root package name */
    private final xi f16354g;

    public bj(xi xiVar) {
        this.f16354g = xiVar;
    }

    @Override // com.tencent.mapsdk.internal.g5
    public void a(y5 y5Var) {
        xi xiVar = this.f16354g;
        if (xiVar == null || y5Var != y5.SCALE_LEVEL_CHANGED) {
            return;
        }
        xiVar.N();
        this.f16354g.K();
    }
}
