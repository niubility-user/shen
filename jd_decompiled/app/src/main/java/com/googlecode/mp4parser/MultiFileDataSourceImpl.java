package com.googlecode.mp4parser;

import com.googlecode.mp4parser.util.CastUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes12.dex */
public class MultiFileDataSourceImpl implements DataSource {
    FileChannel[] fcs;
    int index = 0;

    public MultiFileDataSourceImpl(File... fileArr) throws FileNotFoundException {
        this.fcs = new FileChannel[fileArr.length];
        for (int i2 = 0; i2 < fileArr.length; i2++) {
            this.fcs[i2] = new FileInputStream(fileArr[i2]).getChannel();
        }
    }

    @Override // com.googlecode.mp4parser.DataSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        for (FileChannel fileChannel : this.fcs) {
            fileChannel.close();
        }
    }

    @Override // com.googlecode.mp4parser.DataSource
    public ByteBuffer map(long j2, long j3) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(CastUtils.l2i(j3));
        transferTo(j2, j3, Channels.newChannel(byteArrayOutputStream));
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    @Override // com.googlecode.mp4parser.DataSource
    public long position() throws IOException {
        long j2 = 0;
        int i2 = 0;
        while (true) {
            int i3 = this.index;
            if (i2 >= i3) {
                return j2 + this.fcs[i3].position();
            }
            j2 += this.fcs[i2].size();
            i2++;
        }
    }

    @Override // com.googlecode.mp4parser.DataSource
    public int read(ByteBuffer byteBuffer) throws IOException {
        int remaining = byteBuffer.remaining();
        int read = this.fcs[this.index].read(byteBuffer);
        if (read != remaining) {
            this.index++;
            return read + read(byteBuffer);
        }
        return read;
    }

    @Override // com.googlecode.mp4parser.DataSource
    public long size() throws IOException {
        long j2 = 0;
        for (FileChannel fileChannel : this.fcs) {
            j2 += fileChannel.size();
        }
        return j2;
    }

    @Override // com.googlecode.mp4parser.DataSource
    public long transferTo(long j2, long j3, WritableByteChannel writableByteChannel) throws IOException {
        if (j3 == 0) {
            return 0L;
        }
        long j4 = 0;
        for (FileChannel fileChannel : this.fcs) {
            long size = fileChannel.size();
            if (j2 >= j4 && j2 < j4 + size && j2 + j3 > j4) {
                long j5 = j2 - j4;
                long min = Math.min(j3, size - j5);
                fileChannel.transferTo(j5, min, writableByteChannel);
                return min + transferTo(j2 + min, j3 - min, writableByteChannel);
            }
            j4 += size;
        }
        return 0L;
    }

    @Override // com.googlecode.mp4parser.DataSource
    public void position(long j2) throws IOException {
        int i2 = 0;
        while (true) {
            FileChannel[] fileChannelArr = this.fcs;
            if (i2 >= fileChannelArr.length) {
                return;
            }
            if (j2 - fileChannelArr[i2].size() < 0) {
                this.fcs[i2].position(j2);
                this.index = i2;
                return;
            }
            j2 -= this.fcs[i2].size();
            i2++;
        }
    }
}
