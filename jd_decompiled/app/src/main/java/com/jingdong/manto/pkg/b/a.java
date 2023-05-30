package com.jingdong.manto.pkg.b;

import com.coremedia.iso.boxes.FreeBox;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/* loaded from: classes16.dex */
public class a extends InputStream {
    public final ByteBuffer a;
    private int b;

    public a(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
    }

    public static void a(ByteBuffer byteBuffer) {
        if (byteBuffer.getClass().getName().equals("java.nio.DirectByteBuffer")) {
            try {
                Method declaredMethod = byteBuffer.getClass().getDeclaredMethod(FreeBox.TYPE, new Class[0]);
                boolean isAccessible = declaredMethod.isAccessible();
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(byteBuffer, new Object[0]);
                declaredMethod.setAccessible(isAccessible);
            } catch (Exception unused) {
            }
        }
        System.gc();
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.a.remaining();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        super.close();
        a(this.a);
    }

    @Override // java.io.InputStream
    public final synchronized void mark(int i2) {
        this.b = this.a.position();
    }

    @Override // java.io.InputStream
    public final boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public final int read() {
        if (this.a.hasRemaining()) {
            return this.a.get() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i2, int i3) {
        if (this.a.hasRemaining()) {
            int min = Math.min(i3, this.a.remaining());
            this.a.get(bArr, i2, min);
            return min;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public final synchronized void reset() {
        this.a.position(this.b);
    }
}
