package com.meizu.cloud.pushsdk.e.h;

import java.io.IOException;

/* loaded from: classes14.dex */
public abstract class f implements l {

    /* renamed from: g  reason: collision with root package name */
    private final l f15846g;

    public f(l lVar) {
        if (lVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f15846g = lVar;
    }

    @Override // com.meizu.cloud.pushsdk.e.h.l
    public void c(b bVar, long j2) throws IOException {
        this.f15846g.c(bVar, j2);
    }

    @Override // com.meizu.cloud.pushsdk.e.h.l, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f15846g.close();
    }

    @Override // com.meizu.cloud.pushsdk.e.h.l, java.io.Flushable
    public void flush() throws IOException {
        this.f15846g.flush();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f15846g.toString() + ")";
    }
}
