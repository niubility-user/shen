package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class p7 implements x8<o5> {
    @Override // com.tencent.mapsdk.internal.x8
    public o5 a(float f2, o5 o5Var, o5 o5Var2) {
        double d = o5Var.a;
        double d2 = f2;
        Double.isNaN(d2);
        double d3 = o5Var.b;
        Double.isNaN(d2);
        return new o5(d + ((o5Var2.a - d) * d2), d3 + (d2 * (o5Var2.b - d3)));
    }
}
