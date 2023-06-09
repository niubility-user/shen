package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import java.nio.ByteBuffer;

/* loaded from: classes12.dex */
public class BitReaderBuffer {
    private ByteBuffer buffer;
    int initialPos;
    int position;

    public BitReaderBuffer(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
        this.initialPos = byteBuffer.position();
    }

    public int byteSync() {
        int i2 = 8 - (this.position % 8);
        if (i2 == 8) {
            i2 = 0;
        }
        readBits(i2);
        return i2;
    }

    public int getPosition() {
        return this.position;
    }

    public int readBits(int i2) {
        int readBits;
        int i3 = this.buffer.get(this.initialPos + (this.position / 8));
        if (i3 < 0) {
            i3 += 256;
        }
        int i4 = this.position;
        int i5 = 8 - (i4 % 8);
        if (i2 <= i5) {
            readBits = ((i3 << (i4 % 8)) & 255) >> ((i4 % 8) + (i5 - i2));
            this.position = i4 + i2;
        } else {
            int i6 = i2 - i5;
            readBits = (readBits(i5) << i6) + readBits(i6);
        }
        ByteBuffer byteBuffer = this.buffer;
        int i7 = this.initialPos;
        double d = this.position;
        Double.isNaN(d);
        byteBuffer.position(i7 + ((int) Math.ceil(d / 8.0d)));
        return readBits;
    }

    public boolean readBool() {
        return readBits(1) == 1;
    }

    public int remainingBits() {
        return (this.buffer.limit() * 8) - this.position;
    }
}
