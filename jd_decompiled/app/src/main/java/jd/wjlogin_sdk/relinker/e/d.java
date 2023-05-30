package jd.wjlogin_sdk.relinker.e;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import jd.wjlogin_sdk.relinker.e.c;

/* loaded from: classes.dex */
public class d extends c.b {

    /* renamed from: m */
    private final f f19867m;

    public d(boolean z, f fVar) throws IOException {
        this.a = z;
        this.f19867m = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.b = fVar.b(allocate, 16L);
        this.f19858c = fVar.e(allocate, 28L);
        this.d = fVar.e(allocate, 32L);
        this.f19859e = fVar.b(allocate, 42L);
        this.f19860f = fVar.b(allocate, 44L);
        this.f19861g = fVar.b(allocate, 46L);
        this.f19862h = fVar.b(allocate, 48L);
        this.f19863i = fVar.b(allocate, 50L);
    }

    @Override // jd.wjlogin_sdk.relinker.e.c.b
    public c.d a(int i2) throws IOException {
        return new i(this.f19867m, this, i2);
    }

    @Override // jd.wjlogin_sdk.relinker.e.c.b
    public c.AbstractC0851c a(long j2) throws IOException {
        return new g(this.f19867m, this, j2);
    }

    @Override // jd.wjlogin_sdk.relinker.e.c.b
    public c.a a(long j2, int i2) throws IOException {
        return new a(this.f19867m, this, j2, i2);
    }
}
