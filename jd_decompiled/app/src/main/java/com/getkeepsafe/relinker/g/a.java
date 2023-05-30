package com.getkeepsafe.relinker.g;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes12.dex */
public class a extends c {
    public a(i iVar, d dVar, long j2, int i2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(dVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = j2 + (i2 * 8);
        this.a = iVar.n(allocate, j3);
        this.b = iVar.n(allocate, j3 + 4);
    }
}
