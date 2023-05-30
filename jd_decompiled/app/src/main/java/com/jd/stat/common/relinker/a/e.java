package com.jd.stat.common.relinker.a;

import com.jd.stat.common.relinker.a.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes18.dex */
public class e extends c.b {

    /* renamed from: j */
    private final f f7022j;

    public e(boolean z, f fVar) throws IOException {
        this.a = z;
        this.f7022j = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = fVar.d(allocate, 16L);
        this.f7014c = fVar.b(allocate, 32L);
        this.d = fVar.b(allocate, 40L);
        this.f7015e = fVar.d(allocate, 54L);
        this.f7016f = fVar.d(allocate, 56L);
        this.f7017g = fVar.d(allocate, 58L);
        this.f7018h = fVar.d(allocate, 60L);
        this.f7019i = fVar.d(allocate, 62L);
    }

    @Override // com.jd.stat.common.relinker.a.c.b
    public c.d a(int i2) throws IOException {
        return new j(this.f7022j, this, i2);
    }

    @Override // com.jd.stat.common.relinker.a.c.b
    public c.AbstractC0219c a(long j2) throws IOException {
        return new h(this.f7022j, this, j2);
    }

    @Override // com.jd.stat.common.relinker.a.c.b
    public c.a a(long j2, int i2) throws IOException {
        return new b(this.f7022j, this, j2, i2);
    }
}
