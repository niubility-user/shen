package com.jd.dynamic.b.m;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class b implements k {
    private final String a;
    private final i b;

    public b(@NotNull String str, @Nullable i iVar) {
        this.a = str;
        this.b = iVar;
    }

    @Override // com.jd.dynamic.b.m.k
    public void a() {
        i iVar = this.b;
        if (iVar != null) {
            iVar.a(this.a);
        }
    }
}
