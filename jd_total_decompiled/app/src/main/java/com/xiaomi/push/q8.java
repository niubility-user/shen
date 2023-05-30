package com.xiaomi.push;

import java.io.ByteArrayOutputStream;

/* loaded from: classes11.dex */
public class q8 extends ByteArrayOutputStream {
    public q8(int i2) {
        super(i2);
    }

    public int f() {
        return ((ByteArrayOutputStream) this).count;
    }

    public byte[] g() {
        return ((ByteArrayOutputStream) this).buf;
    }
}
