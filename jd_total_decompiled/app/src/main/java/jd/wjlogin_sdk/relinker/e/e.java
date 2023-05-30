package jd.wjlogin_sdk.relinker.e;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import jd.wjlogin_sdk.relinker.e.c;

/* loaded from: classes.dex */
public class e extends c.b {

    /* renamed from: m */
    private final f f19868m;

    public e(boolean z, f fVar) throws IOException {
        this.a = z;
        this.f19868m = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = fVar.b(allocate, 16L);
        this.f19858c = fVar.c(allocate, 32L);
        this.d = fVar.c(allocate, 40L);
        this.f19859e = fVar.b(allocate, 54L);
        this.f19860f = fVar.b(allocate, 56L);
        this.f19861g = fVar.b(allocate, 58L);
        this.f19862h = fVar.b(allocate, 60L);
        this.f19863i = fVar.b(allocate, 62L);
    }

    @Override // jd.wjlogin_sdk.relinker.e.c.b
    public c.d a(int i2) throws IOException {
        return new j(this.f19868m, this, i2);
    }

    @Override // jd.wjlogin_sdk.relinker.e.c.b
    public c.AbstractC0851c a(long j2) throws IOException {
        return new h(this.f19868m, this, j2);
    }

    @Override // jd.wjlogin_sdk.relinker.e.c.b
    public c.a a(long j2, int i2) throws IOException {
        return new b(this.f19868m, this, j2, i2);
    }
}
