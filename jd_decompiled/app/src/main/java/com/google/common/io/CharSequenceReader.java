package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

@GwtIncompatible
/* loaded from: classes12.dex */
final class CharSequenceReader extends Reader {
    private int mark;
    private int pos;
    private CharSequence seq;

    public CharSequenceReader(CharSequence charSequence) {
        this.seq = (CharSequence) Preconditions.checkNotNull(charSequence);
    }

    private void checkOpen() throws IOException {
        if (this.seq == null) {
            throw new IOException("reader closed");
        }
    }

    private boolean hasRemaining() {
        return remaining() > 0;
    }

    private int remaining() {
        return this.seq.length() - this.pos;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.seq = null;
    }

    @Override // java.io.Reader
    public synchronized void mark(int i2) throws IOException {
        Preconditions.checkArgument(i2 >= 0, "readAheadLimit (%s) may not be negative", i2);
        checkOpen();
        this.mark = this.pos;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader, java.lang.Readable
    public synchronized int read(CharBuffer charBuffer) throws IOException {
        Preconditions.checkNotNull(charBuffer);
        checkOpen();
        if (hasRemaining()) {
            int min = Math.min(charBuffer.remaining(), remaining());
            for (int i2 = 0; i2 < min; i2++) {
                CharSequence charSequence = this.seq;
                int i3 = this.pos;
                this.pos = i3 + 1;
                charBuffer.put(charSequence.charAt(i3));
            }
            return min;
        }
        return -1;
    }

    @Override // java.io.Reader
    public synchronized boolean ready() throws IOException {
        checkOpen();
        return true;
    }

    @Override // java.io.Reader
    public synchronized void reset() throws IOException {
        checkOpen();
        this.pos = this.mark;
    }

    @Override // java.io.Reader
    public synchronized long skip(long j2) throws IOException {
        int min;
        Preconditions.checkArgument(j2 >= 0, "n (%s) may not be negative", j2);
        checkOpen();
        min = (int) Math.min(remaining(), j2);
        this.pos += min;
        return min;
    }

    @Override // java.io.Reader
    public synchronized int read() throws IOException {
        char c2;
        checkOpen();
        if (hasRemaining()) {
            CharSequence charSequence = this.seq;
            int i2 = this.pos;
            this.pos = i2 + 1;
            c2 = charSequence.charAt(i2);
        } else {
            c2 = '\uffff';
        }
        return c2;
    }

    @Override // java.io.Reader
    public synchronized int read(char[] cArr, int i2, int i3) throws IOException {
        Preconditions.checkPositionIndexes(i2, i2 + i3, cArr.length);
        checkOpen();
        if (hasRemaining()) {
            int min = Math.min(i3, remaining());
            for (int i4 = 0; i4 < min; i4++) {
                CharSequence charSequence = this.seq;
                int i5 = this.pos;
                this.pos = i5 + 1;
                cArr[i2 + i4] = charSequence.charAt(i5);
            }
            return min;
        }
        return -1;
    }
}
