package com.jdcloud.vsr.imaging;

/* loaded from: classes18.dex */
public enum PixelFormat {
    SingleByte,
    TripleByte,
    QuadByte,
    SingleFloat,
    TripleFloat,
    QuadFloat,
    BinaryMask,
    QuaternaryMask,
    HexMask;
    
    private int[] BITS_PER_PIXEL = {8, 24, 32, 32, 96, 128, 1, 2, 4};

    PixelFormat() {
    }

    public int getBitsPerPixel() {
        return this.BITS_PER_PIXEL[ordinal()];
    }
}
