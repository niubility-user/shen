package com.jingdong.manto.m.p0.f.f.c;

import jd.wjlogin_sdk.util.ReplyCode;

/* loaded from: classes15.dex */
final class k {
    private final g a;
    private final long b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(g gVar, long j2) {
        this.a = gVar;
        this.b = j2;
    }

    private byte[] a(long j2, long j3, long j4, int i2, long j5, byte b) {
        return new byte[]{82, 73, 70, 70, (byte) (j3 & 255), (byte) ((j3 >> 8) & 255), (byte) ((j3 >> 16) & 255), (byte) ((j3 >> 24) & 255), 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, (byte) i2, 0, (byte) (j4 & 255), (byte) ((j4 >> 8) & 255), (byte) ((j4 >> 16) & 255), (byte) ((j4 >> 24) & 255), (byte) (j5 & 255), (byte) ((j5 >> 8) & 255), (byte) ((j5 >> 16) & 255), (byte) ((j5 >> 24) & 255), (byte) (i2 * (b / 8)), 0, b, 0, ReplyCode.reply0x64, 97, 116, 97, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255)};
    }

    public byte[] a() {
        long e2 = this.a.c().e();
        int i2 = this.a.c().a() == 16 ? 1 : 2;
        byte b = this.a.c().b();
        long j2 = this.b - 44;
        return a(j2, j2 + 36, e2, i2, ((b * e2) * i2) / 8, b);
    }
}
