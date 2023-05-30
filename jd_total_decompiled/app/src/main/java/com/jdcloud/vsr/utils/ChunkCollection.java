package com.jdcloud.vsr.utils;

import com.jdcloud.vsr.JDTObject;
import java.io.IOError;

/* loaded from: classes18.dex */
public class ChunkCollection extends JDTObject {
    /* JADX INFO: Access modifiers changed from: protected */
    public ChunkCollection(long j2) {
        super(j2);
    }

    private native boolean chunkExists(long j2, String str);

    private native long chunkSize(long j2, String str);

    private native void close(long j2);

    private native void open(long j2) throws IOError;

    private native String read(long j2, String str);

    private native void save(long j2, String str, boolean z);

    private native long size(long j2);

    public boolean chunkExists(String str) {
        return chunkExists(this.handle, str);
    }

    public long chunkSize(String str) {
        return chunkSize(this.handle, str);
    }

    public void close() {
        close(this.handle);
    }

    public void open() throws IOError {
        open(this.handle);
    }

    public String read(String str) {
        return read(this.handle, str);
    }

    public long size() {
        return size(this.handle);
    }
}
