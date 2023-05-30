package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.j2;
import com.tencent.mapsdk.internal.j2.a;

/* loaded from: classes9.dex */
public abstract class i3<R extends j2.a> extends g3<R> {

    /* renamed from: f  reason: collision with root package name */
    private String f16680f;

    /* renamed from: g  reason: collision with root package name */
    private String f16681g;

    public void b(String str) {
        this.f16680f = str;
    }

    public void c(String str) {
        this.f16681g = str;
    }

    @Override // com.tencent.mapsdk.internal.g3, com.tencent.mapsdk.internal.j2
    public final String f() {
        return this.f16681g;
    }

    @Override // com.tencent.mapsdk.internal.j2
    public final String g() {
        return this.f16680f;
    }
}
