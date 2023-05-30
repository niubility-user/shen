package com.jd.stat.common.relinker.a;

import com.jd.stat.common.relinker.a.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes18.dex */
public class b extends c.a {
    public b(f fVar, c.b bVar, long j2, int i2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = j2 + (i2 * 16);
        this.a = fVar.b(allocate, j3);
        this.b = fVar.b(allocate, j3 + 8);
    }
}
