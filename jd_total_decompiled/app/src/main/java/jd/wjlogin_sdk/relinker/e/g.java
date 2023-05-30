package jd.wjlogin_sdk.relinker.e;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import jd.wjlogin_sdk.relinker.e.c;

/* loaded from: classes.dex */
public class g extends c.AbstractC0851c {
    public g(f fVar, c.b bVar, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = bVar.f19858c + (j2 * bVar.f19859e);
        this.a = fVar.e(allocate, j3);
        this.b = fVar.e(allocate, 4 + j3);
        this.f19866c = fVar.e(allocate, 8 + j3);
        this.d = fVar.e(allocate, j3 + 20);
    }
}
