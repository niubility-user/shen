package cn.com.union.fido.util.asn1;

import cn.com.union.fido.util.asn1.util.io.Streams;
import java.io.EOFException;
import java.io.InputStream;

/* loaded from: classes.dex */
class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] EMPTY_BYTES = new byte[0];
    private final int _originalLength;
    private int _remaining;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefiniteLengthInputStream(InputStream inputStream, int i2) {
        super(inputStream);
        if (i2 < 0) {
            throw new IllegalArgumentException("negative lengths not allowed");
        }
        this._originalLength = i2;
        this._remaining = i2;
        if (i2 == 0) {
            setParentEofDetect(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRemaining() {
        return this._remaining;
    }

    @Override // java.io.InputStream
    public int read() {
        if (this._remaining == 0) {
            return -1;
        }
        int read = this._in.read();
        if (read >= 0) {
            int i2 = this._remaining - 1;
            this._remaining = i2;
            if (i2 == 0) {
                setParentEofDetect(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int i4 = this._remaining;
        if (i4 == 0) {
            return -1;
        }
        int read = this._in.read(bArr, i2, Math.min(i3, i4));
        if (read >= 0) {
            int i5 = this._remaining - read;
            this._remaining = i5;
            if (i5 == 0) {
                setParentEofDetect(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] toByteArray() {
        int i2 = this._remaining;
        if (i2 == 0) {
            return EMPTY_BYTES;
        }
        byte[] bArr = new byte[i2];
        int readFully = i2 - Streams.readFully(this._in, bArr);
        this._remaining = readFully;
        if (readFully == 0) {
            setParentEofDetect(true);
            return bArr;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }
}
