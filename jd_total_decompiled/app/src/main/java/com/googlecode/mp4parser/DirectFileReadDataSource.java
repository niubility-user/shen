package com.googlecode.mp4parser;

import com.googlecode.mp4parser.util.CastUtils;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes12.dex */
public class DirectFileReadDataSource implements DataSource {
    private static final int TRANSFER_SIZE = 8192;
    private String filename;
    private RandomAccessFile raf;

    public DirectFileReadDataSource(File file) throws IOException {
        this.raf = new RandomAccessFile(file, "r");
        this.filename = file.getName();
    }

    @Override // com.googlecode.mp4parser.DataSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.raf.close();
    }

    @Override // com.googlecode.mp4parser.DataSource
    public ByteBuffer map(long j2, long j3) throws IOException {
        this.raf.seek(j2);
        byte[] bArr = new byte[CastUtils.l2i(j3)];
        this.raf.readFully(bArr);
        return ByteBuffer.wrap(bArr);
    }

    @Override // com.googlecode.mp4parser.DataSource
    public long position() throws IOException {
        return this.raf.getFilePointer();
    }

    @Override // com.googlecode.mp4parser.DataSource
    public int read(ByteBuffer byteBuffer) throws IOException {
        int remaining = byteBuffer.remaining();
        byte[] bArr = new byte[8192];
        int i2 = 0;
        int i3 = 0;
        while (i2 < remaining) {
            i3 = this.raf.read(bArr, 0, Math.min(remaining - i2, 8192));
            if (i3 < 0) {
                break;
            }
            i2 += i3;
            byteBuffer.put(bArr, 0, i3);
        }
        if (i3 >= 0 || i2 != 0) {
            return i2;
        }
        return -1;
    }

    public int readAllInOnce(ByteBuffer byteBuffer) throws IOException {
        byte[] bArr = new byte[byteBuffer.remaining()];
        int read = this.raf.read(bArr);
        byteBuffer.put(bArr, 0, read);
        return read;
    }

    @Override // com.googlecode.mp4parser.DataSource
    public long size() throws IOException {
        return this.raf.length();
    }

    public String toString() {
        return this.filename;
    }

    @Override // com.googlecode.mp4parser.DataSource
    public long transferTo(long j2, long j3, WritableByteChannel writableByteChannel) throws IOException {
        return writableByteChannel.write(map(j2, j3));
    }

    @Override // com.googlecode.mp4parser.DataSource
    public void position(long j2) throws IOException {
        this.raf.seek(j2);
    }
}
