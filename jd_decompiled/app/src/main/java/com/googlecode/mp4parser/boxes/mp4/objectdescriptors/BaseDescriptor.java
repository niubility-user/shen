package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import java.io.IOException;
import java.nio.ByteBuffer;

@Descriptor(tags = {0})
/* loaded from: classes12.dex */
public abstract class BaseDescriptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    int sizeBytes;
    int sizeOfInstance;
    int tag;

    abstract int getContentSize();

    public int getSize() {
        return getContentSize() + getSizeSize() + 1;
    }

    public int getSizeSize() {
        int contentSize = getContentSize();
        int i2 = 0;
        while (true) {
            if (contentSize <= 0 && i2 >= this.sizeBytes) {
                return i2;
            }
            contentSize >>>= 7;
            i2++;
        }
    }

    public int getTag() {
        return this.tag;
    }

    public final void parse(int i2, ByteBuffer byteBuffer) throws IOException {
        this.tag = i2;
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.sizeOfInstance = readUInt8 & 127;
        int i3 = 1;
        while ((readUInt8 >>> 7) == 1) {
            readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
            i3++;
            this.sizeOfInstance = (this.sizeOfInstance << 7) | (readUInt8 & 127);
        }
        this.sizeBytes = i3;
        ByteBuffer slice = byteBuffer.slice();
        slice.limit(this.sizeOfInstance);
        parseDetail(slice);
        byteBuffer.position(byteBuffer.position() + this.sizeOfInstance);
    }

    public abstract void parseDetail(ByteBuffer byteBuffer) throws IOException;

    public abstract ByteBuffer serialize();

    public String toString() {
        return "BaseDescriptor{tag=" + this.tag + ", sizeOfInstance=" + this.sizeOfInstance + '}';
    }

    public void writeSize(ByteBuffer byteBuffer, int i2) {
        int position = byteBuffer.position();
        int i3 = 0;
        while (true) {
            if (i2 <= 0 && i3 >= this.sizeBytes) {
                byteBuffer.position(position + getSizeSize());
                return;
            }
            i3++;
            if (i2 > 0) {
                byteBuffer.put((getSizeSize() + position) - i3, (byte) (i2 & 127));
            } else {
                byteBuffer.put((getSizeSize() + position) - i3, Byte.MIN_VALUE);
            }
            i2 >>>= 7;
        }
    }
}
