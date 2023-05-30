package com.jd.stat.common.relinker.a;

import com.jd.stat.common.relinker.a.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes18.dex */
public class d extends c.b {

    /* renamed from: j */
    private final f f7021j;

    public d(boolean z, f fVar) throws IOException {
        this.a = z;
        this.f7021j = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = fVar.d(allocate, 16L);
        this.f7014c = fVar.c(allocate, 28L);
        this.d = fVar.c(allocate, 32L);
        this.f7015e = fVar.d(allocate, 42L);
        this.f7016f = fVar.d(allocate, 44L);
        this.f7017g = fVar.d(allocate, 46L);
        this.f7018h = fVar.d(allocate, 48L);
        this.f7019i = fVar.d(allocate, 50L);
    }

    @Override // com.jd.stat.common.relinker.a.c.b
    public c.d a(int i2) throws IOException {
        return new i(this.f7021j, this, i2);
    }

    @Override // com.jd.stat.common.relinker.a.c.b
    public c.AbstractC0219c a(long j2) throws IOException {
        return new g(this.f7021j, this, j2);
    }

    @Override // com.jd.stat.common.relinker.a.c.b
    public c.a a(long j2, int i2) throws IOException {
        return new a(this.f7021j, this, j2, i2);
    }
}
