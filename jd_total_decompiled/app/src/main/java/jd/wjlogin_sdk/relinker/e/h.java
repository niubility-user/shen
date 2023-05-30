package jd.wjlogin_sdk.relinker.e;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import jd.wjlogin_sdk.relinker.e.c;

/* loaded from: classes.dex */
public class h extends c.AbstractC0851c {
    public h(f fVar, c.b bVar, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = bVar.f19858c + (j2 * bVar.f19859e);
        this.a = fVar.e(allocate, j3);
        this.b = fVar.c(allocate, 8 + j3);
        this.f19866c = fVar.c(allocate, 16 + j3);
        this.d = fVar.c(allocate, j3 + 40);
    }
}
