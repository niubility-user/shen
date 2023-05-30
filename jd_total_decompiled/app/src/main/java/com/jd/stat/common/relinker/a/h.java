package com.jd.stat.common.relinker.a;

import com.jd.stat.common.relinker.a.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes18.dex */
public class h extends c.AbstractC0219c {
    public h(f fVar, c.b bVar, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = bVar.f7014c + (j2 * bVar.f7015e);
        this.a = fVar.c(allocate, j3);
        this.b = fVar.b(allocate, 8 + j3);
        this.f7020c = fVar.b(allocate, 16 + j3);
        this.d = fVar.b(allocate, j3 + 40);
    }
}
