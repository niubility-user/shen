package com.tencent.open.utils;

/* loaded from: classes9.dex */
public final class n implements Cloneable {
    private long a;

    public n(long j2) {
        this.a = j2;
    }

    public byte[] a() {
        long j2 = this.a;
        return new byte[]{(byte) (255 & j2), (byte) ((65280 & j2) >> 8), (byte) ((16711680 & j2) >> 16), (byte) ((j2 & 4278190080L) >> 24)};
    }

    public long b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof n) && this.a == ((n) obj).b();
    }

    public int hashCode() {
        return (int) this.a;
    }
}
