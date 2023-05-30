package com.googlecode.mp4parser;

import com.googlecode.mp4parser.util.CastUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes12.dex */
public class MemoryDataSourceImpl implements DataSource {
    ByteBuffer data;

    public MemoryDataSourceImpl(byte[] bArr) {
        this.data = ByteBuffer.wrap(bArr);
    }

    @Override // com.googlecode.mp4parser.DataSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // com.googlecode.mp4parser.DataSource
    public ByteBuffer map(long j2, long j3) throws IOException {
        int position = this.data.position();
        this.data.position(CastUtils.l2i(j2));
        ByteBuffer slice = this.data.slice();
        slice.limit(CastUtils.l2i(j3));
        this.data.position(position);
        return slice;
    }

    @Override // com.googlecode.mp4parser.DataSource
    public long position() throws IOException {
        return this.data.position();
    }

    @Override // com.googlecode.mp4parser.DataSource
    public int read(ByteBuffer byteBuffer) throws IOException {
        if (this.data.remaining() != 0 || byteBuffer.remaining() == 0) {
            int min = Math.min(byteBuffer.remaining(), this.data.remaining());
            if (byteBuffer.hasArray()) {
                byteBuffer.put(this.data.array(), this.data.position(), min);
                ByteBuffer byteBuffer2 = this.data;
                byteBuffer2.position(byteBuffer2.position() + min);
            } else {
                byte[] bArr = new byte[min];
                this.data.get(bArr);
                byteBuffer.put(bArr);
            }
            return min;
        }
        return -1;
    }

    @Override // com.googlecode.mp4parser.DataSource
    public long size() throws IOException {
        return this.data.capacity();
    }

    @Override // com.googlecode.mp4parser.DataSource
    public long transferTo(long j2, long j3, WritableByteChannel writableByteChannel) throws IOException {
        return writableByteChannel.write((ByteBuffer) ((ByteBuffer) this.data.position(CastUtils.l2i(j2))).slice().limit(CastUtils.l2i(j3)));
    }

    @Override // com.googlecode.mp4parser.DataSource
    public void position(long j2) throws IOException {
        this.data.position(CastUtils.l2i(j2));
    }

    public MemoryDataSourceImpl(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }
}
