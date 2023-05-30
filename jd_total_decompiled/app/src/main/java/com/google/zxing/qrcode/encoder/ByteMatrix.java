package com.google.zxing.qrcode.encoder;

import java.lang.reflect.Array;

/* loaded from: classes12.dex */
public final class ByteMatrix {
    private final byte[][] bytes;
    private final int height;
    private final int width;

    public ByteMatrix(int i2, int i3) {
        this.bytes = (byte[][]) Array.newInstance(byte.class, i3, i2);
        this.width = i2;
        this.height = i3;
    }

    public void clear(byte b) {
        for (int i2 = 0; i2 < this.height; i2++) {
            for (int i3 = 0; i3 < this.width; i3++) {
                this.bytes[i2][i3] = b;
            }
        }
    }

    public byte get(int i2, int i3) {
        return this.bytes[i3][i2];
    }

    public byte[][] getArray() {
        return this.bytes;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void set(int i2, int i3, byte b) {
        this.bytes[i3][i2] = b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.width * 2 * this.height) + 2);
        for (int i2 = 0; i2 < this.height; i2++) {
            for (int i3 = 0; i3 < this.width; i3++) {
                byte b = this.bytes[i2][i3];
                if (b == 0) {
                    sb.append(" 0");
                } else if (b != 1) {
                    sb.append("  ");
                } else {
                    sb.append(" 1");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void set(int i2, int i3, int i4) {
        this.bytes[i3][i2] = (byte) i4;
    }

    public void set(int i2, int i3, boolean z) {
        this.bytes[i3][i2] = z ? (byte) 1 : (byte) 0;
    }
}
