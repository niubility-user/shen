package jd.wjlogin_sdk.relinker.e;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import jd.wjlogin_sdk.relinker.e.c;

/* loaded from: classes.dex */
public class j extends c.d {
    public j(f fVar, c.b bVar, int i2) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.a = fVar.e(allocate, bVar.d + (i2 * bVar.f19861g) + 44);
    }
}
