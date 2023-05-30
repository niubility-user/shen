package com.getkeepsafe.relinker.g;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes12.dex */
public class h extends d {

    /* renamed from: g */
    private final i f1061g;

    public h(boolean z, i iVar) throws IOException {
        this.a = z;
        this.f1061g = iVar;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        iVar.k(allocate, 16L);
        this.b = iVar.l(allocate, 32L);
        this.f1056c = iVar.l(allocate, 40L);
        this.d = iVar.k(allocate, 54L);
        this.f1057e = iVar.k(allocate, 56L);
        this.f1058f = iVar.k(allocate, 58L);
        iVar.k(allocate, 60L);
        iVar.k(allocate, 62L);
    }

    @Override // com.getkeepsafe.relinker.g.d
    public c a(long j2, int i2) throws IOException {
        return new b(this.f1061g, this, j2, i2);
    }

    @Override // com.getkeepsafe.relinker.g.d
    public e b(long j2) throws IOException {
        return new k(this.f1061g, this, j2);
    }

    @Override // com.getkeepsafe.relinker.g.d
    public f c(int i2) throws IOException {
        return new m(this.f1061g, this, i2);
    }
}
