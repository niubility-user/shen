package com.jingdong.manto.sdk.e;

import com.jingdong.manto.utils.MantoLog;
import java.io.FileInputStream;
import java.io.FilterInputStream;

/* loaded from: classes16.dex */
public class b extends FilterInputStream {
    private long a;

    public b(FileInputStream fileInputStream) {
        super(fileInputStream);
        this.a = 0L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final void mark(int i2) {
        try {
            this.a = ((FileInputStream) ((FilterInputStream) this).in).getChannel().position();
        } catch (Throwable th) {
            MantoLog.e("FileSeekingInputStream", "Failed seeking FileChannel.");
            th.printStackTrace();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final void reset() {
        ((FileInputStream) ((FilterInputStream) this).in).getChannel().position(this.a);
    }
}
