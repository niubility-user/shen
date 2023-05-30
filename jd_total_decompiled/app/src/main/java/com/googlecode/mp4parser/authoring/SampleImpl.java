package com.googlecode.mp4parser.authoring;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.util.CastUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes12.dex */
public class SampleImpl implements Sample {
    private ByteBuffer[] data;
    private final long offset;
    private final Container parent;
    private final long size;

    public SampleImpl(ByteBuffer byteBuffer) {
        this.offset = -1L;
        this.size = byteBuffer.limit();
        this.data = new ByteBuffer[]{byteBuffer};
        this.parent = null;
    }

    @Override // com.googlecode.mp4parser.authoring.Sample
    public ByteBuffer asByteBuffer() {
        ensureData();
        ByteBuffer wrap = ByteBuffer.wrap(new byte[CastUtils.l2i(this.size)]);
        for (ByteBuffer byteBuffer : this.data) {
            wrap.put(byteBuffer.duplicate());
        }
        wrap.rewind();
        return wrap;
    }

    protected void ensureData() {
        if (this.data != null) {
            return;
        }
        Container container = this.parent;
        if (container != null) {
            try {
                this.data = new ByteBuffer[]{container.getByteBuffer(this.offset, this.size)};
                return;
            } catch (IOException e2) {
                throw new RuntimeException("couldn't read sample " + this, e2);
            }
        }
        throw new RuntimeException("Missing parent container, can't read sample " + this);
    }

    @Override // com.googlecode.mp4parser.authoring.Sample
    public long getSize() {
        return this.size;
    }

    public String toString() {
        return "SampleImpl{offset=" + this.offset + "{size=" + this.size + '}';
    }

    @Override // com.googlecode.mp4parser.authoring.Sample
    public void writeTo(WritableByteChannel writableByteChannel) throws IOException {
        ensureData();
        for (ByteBuffer byteBuffer : this.data) {
            writableByteChannel.write(byteBuffer.duplicate());
        }
    }

    public SampleImpl(ByteBuffer[] byteBufferArr) {
        this.offset = -1L;
        int i2 = 0;
        for (ByteBuffer byteBuffer : byteBufferArr) {
            i2 += byteBuffer.remaining();
        }
        this.size = i2;
        this.data = byteBufferArr;
        this.parent = null;
    }

    public SampleImpl(long j2, long j3, ByteBuffer byteBuffer) {
        this.offset = j2;
        this.size = j3;
        this.data = new ByteBuffer[]{byteBuffer};
        this.parent = null;
    }

    public SampleImpl(long j2, long j3, Container container) {
        this.offset = j2;
        this.size = j3;
        this.data = null;
        this.parent = container;
    }
}
