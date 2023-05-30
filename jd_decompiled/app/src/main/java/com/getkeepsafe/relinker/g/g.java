package com.getkeepsafe.relinker.g;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes12.dex */
public class g extends d {

    /* renamed from: g */
    private final i f1060g;

    public g(boolean z, i iVar) throws IOException {
        this.a = z;
        this.f1060g = iVar;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        iVar.k(allocate, 16L);
        this.b = iVar.n(allocate, 28L);
        this.f1056c = iVar.n(allocate, 32L);
        this.d = iVar.k(allocate, 42L);
        this.f1057e = iVar.k(allocate, 44L);
        this.f1058f = iVar.k(allocate, 46L);
        iVar.k(allocate, 48L);
        iVar.k(allocate, 50L);
    }

    @Override // com.getkeepsafe.relinker.g.d
    public c a(long j2, int i2) throws IOException {
        return new a(this.f1060g, this, j2, i2);
    }

    @Override // com.getkeepsafe.relinker.g.d
    public e b(long j2) throws IOException {
        return new j(this.f1060g, this, j2);
    }

    @Override // com.getkeepsafe.relinker.g.d
    public f c(int i2) throws IOException {
        return new l(this.f1060g, this, i2);
    }
}
