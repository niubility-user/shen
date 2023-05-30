package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class ng extends hb {
    private og b;

    public ng(og ogVar) {
        this.b = ogVar;
    }

    @Override // com.tencent.mapsdk.internal.hb
    public final byte[] e(String str) {
        og ogVar;
        if (e7.b(str) || !str.startsWith(og.f16936g) || (ogVar = this.b) == null) {
            return null;
        }
        return ogVar.a(str);
    }
}
