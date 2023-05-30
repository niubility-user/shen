package com.coremedia.iso;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class IsoTypeWriterVariable {
    public static void write(long j2, ByteBuffer byteBuffer, int i2) {
        if (i2 == 1) {
            IsoTypeWriter.writeUInt8(byteBuffer, (int) (j2 & 255));
        } else if (i2 == 2) {
            IsoTypeWriter.writeUInt16(byteBuffer, (int) (j2 & 65535));
        } else if (i2 == 3) {
            IsoTypeWriter.writeUInt24(byteBuffer, (int) (j2 & 16777215));
        } else if (i2 == 4) {
            IsoTypeWriter.writeUInt32(byteBuffer, j2);
        } else if (i2 == 8) {
            IsoTypeWriter.writeUInt64(byteBuffer, j2);
        } else {
            throw new RuntimeException("I don't know how to read " + i2 + " bytes");
        }
    }
}
