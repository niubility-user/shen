package com.getkeepsafe.relinker.g;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes12.dex */
public class j extends e {
    public j(i iVar, d dVar, long j2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(dVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = dVar.b + (j2 * dVar.d);
        this.a = iVar.n(allocate, j3);
        this.b = iVar.n(allocate, 4 + j3);
        this.f1059c = iVar.n(allocate, 8 + j3);
        this.d = iVar.n(allocate, j3 + 20);
    }
}
