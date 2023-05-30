package com.getkeepsafe.relinker.g;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes12.dex */
public class k extends e {
    public k(i iVar, d dVar, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(dVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = dVar.b + (j2 * dVar.d);
        this.a = iVar.n(allocate, j3);
        this.b = iVar.l(allocate, 8 + j3);
        this.f1059c = iVar.l(allocate, 16 + j3);
        this.d = iVar.l(allocate, j3 + 40);
    }
}
