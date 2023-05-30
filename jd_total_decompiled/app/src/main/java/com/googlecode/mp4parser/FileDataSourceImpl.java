package com.googlecode.mp4parser;

import com.googlecode.mp4parser.util.Logger;
import com.jingdong.common.utils.LangUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes12.dex */
public class FileDataSourceImpl implements DataSource {
    private static Logger LOG = Logger.getLogger(FileDataSourceImpl.class);
    FileChannel fc;
    String filename;

    public FileDataSourceImpl(File file) throws FileNotFoundException {
        this.fc = new FileInputStream(file).getChannel();
        this.filename = file.getName();
    }

    @Override // com.googlecode.mp4parser.DataSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.fc.close();
    }

    @Override // com.googlecode.mp4parser.DataSource
    public synchronized ByteBuffer map(long j2, long j3) throws IOException {
        LOG.logDebug(String.valueOf(j2) + LangUtils.SINGLE_SPACE + j3);
        return this.fc.map(FileChannel.MapMode.READ_ONLY, j2, j3);
    }

    @Override // com.googlecode.mp4parser.DataSource
    public synchronized long position() throws IOException {
        return this.fc.position();
    }

    @Override // com.googlecode.mp4parser.DataSource
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        return this.fc.read(byteBuffer);
    }

    @Override // com.googlecode.mp4parser.DataSource
    public synchronized long size() throws IOException {
        return this.fc.size();
    }

    public String toString() {
        return this.filename;
    }

    @Override // com.googlecode.mp4parser.DataSource
    public synchronized long transferTo(long j2, long j3, WritableByteChannel writableByteChannel) throws IOException {
        return this.fc.transferTo(j2, j3, writableByteChannel);
    }

    @Override // com.googlecode.mp4parser.DataSource
    public synchronized void position(long j2) throws IOException {
        this.fc.position(j2);
    }

    public FileDataSourceImpl(String str) throws FileNotFoundException {
        File file = new File(str);
        this.fc = new FileInputStream(file).getChannel();
        this.filename = file.getName();
    }

    public FileDataSourceImpl(FileChannel fileChannel) {
        this.fc = fileChannel;
        this.filename = "unknown";
    }

    public FileDataSourceImpl(FileChannel fileChannel, String str) {
        this.fc = fileChannel;
        this.filename = str;
    }
}
