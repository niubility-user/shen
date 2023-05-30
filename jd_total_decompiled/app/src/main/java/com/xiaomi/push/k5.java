package com.xiaomi.push;

import java.nio.ByteBuffer;

/* loaded from: classes11.dex */
public final class k5 extends e5 {
    public k5() {
        l("PING", null);
        k("0");
        h(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.xiaomi.push.e5
    public ByteBuffer f(ByteBuffer byteBuffer) {
        return p().length == 0 ? byteBuffer : super.f(byteBuffer);
    }

    @Override // com.xiaomi.push.e5
    public int x() {
        if (p().length == 0) {
            return 0;
        }
        return super.x();
    }
}
