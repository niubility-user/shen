package com.jingdong.common.jdreactFramework.utils;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class Base64OutputStream extends OutputStream {
    private int buffer;
    private int bytecounter;
    private int linecounter;
    private int linelength;
    private OutputStream outputStream;

    public Base64OutputStream(OutputStream outputStream) {
        this(outputStream, 76);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        commit();
        this.outputStream.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void commit() throws IOException {
        if (this.bytecounter > 0) {
            int i2 = this.linelength;
            if (i2 > 0 && this.linecounter == i2) {
                this.outputStream.write("\r\n".getBytes());
                this.linecounter = 0;
            }
            char charAt = Shared.chars.charAt((this.buffer << 8) >>> 26);
            char charAt2 = Shared.chars.charAt((this.buffer << 14) >>> 26);
            char charAt3 = this.bytecounter < 2 ? Shared.pad : Shared.chars.charAt((this.buffer << 20) >>> 26);
            char charAt4 = this.bytecounter < 3 ? Shared.pad : Shared.chars.charAt((this.buffer << 26) >>> 26);
            this.outputStream.write(charAt);
            this.outputStream.write(charAt2);
            this.outputStream.write(charAt3);
            this.outputStream.write(charAt4);
            this.linecounter += 4;
            this.bytecounter = 0;
            this.buffer = 0;
        }
    }

    @Override // java.io.OutputStream
    public void write(int i2) throws IOException {
        int i3 = this.bytecounter;
        this.buffer = ((i2 & 255) << (16 - (i3 * 8))) | this.buffer;
        int i4 = i3 + 1;
        this.bytecounter = i4;
        if (i4 == 3) {
            commit();
        }
    }

    public Base64OutputStream(OutputStream outputStream, int i2) {
        this.outputStream = null;
        this.buffer = 0;
        this.bytecounter = 0;
        this.linecounter = 0;
        this.linelength = 0;
        this.outputStream = outputStream;
        this.linelength = i2;
    }
}
