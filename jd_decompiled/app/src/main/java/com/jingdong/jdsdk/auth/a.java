package com.jingdong.jdsdk.auth;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

/* loaded from: classes14.dex */
public abstract class a {
    protected PrintStream pStream;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] getBytes(java.nio.ByteBuffer r4) {
        /*
            r3 = this;
            boolean r0 = r4.hasArray()
            if (r0 == 0) goto L20
            byte[] r0 = r4.array()
            int r1 = r0.length
            int r2 = r4.capacity()
            if (r1 != r2) goto L20
            int r1 = r0.length
            int r2 = r4.remaining()
            if (r1 != r2) goto L20
            int r1 = r4.limit()
            r4.position(r1)
            goto L21
        L20:
            r0 = 0
        L21:
            if (r0 != 0) goto L2c
            int r0 = r4.remaining()
            byte[] r0 = new byte[r0]
            r4.get(r0)
        L2c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.auth.a.getBytes(java.nio.ByteBuffer):byte[]");
    }

    protected abstract int bytesPerAtom();

    protected abstract int bytesPerLine();

    public void encode(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[bytesPerLine()];
        encodeBufferPrefix(outputStream);
        while (true) {
            int readFully = readFully(inputStream, bArr);
            if (readFully == 0) {
                break;
            }
            encodeLinePrefix(outputStream, readFully);
            int i2 = 0;
            while (i2 < readFully) {
                if (bytesPerAtom() + i2 <= readFully) {
                    encodeAtom(outputStream, bArr, i2, bytesPerAtom());
                } else {
                    encodeAtom(outputStream, bArr, i2, readFully - i2);
                }
                i2 += bytesPerAtom();
            }
            if (readFully < bytesPerLine()) {
                break;
            }
            encodeLineSuffix(outputStream);
        }
        encodeBufferSuffix(outputStream);
    }

    protected abstract void encodeAtom(OutputStream outputStream, byte[] bArr, int i2, int i3) throws IOException;

    public void encodeBuffer(InputStream inputStream, OutputStream outputStream) throws IOException {
        int readFully;
        byte[] bArr = new byte[bytesPerLine()];
        encodeBufferPrefix(outputStream);
        do {
            readFully = readFully(inputStream, bArr);
            if (readFully == 0) {
                break;
            }
            encodeLinePrefix(outputStream, readFully);
            int i2 = 0;
            while (i2 < readFully) {
                if (bytesPerAtom() + i2 <= readFully) {
                    encodeAtom(outputStream, bArr, i2, bytesPerAtom());
                } else {
                    encodeAtom(outputStream, bArr, i2, readFully - i2);
                }
                i2 += bytesPerAtom();
            }
            encodeLineSuffix(outputStream);
        } while (readFully >= bytesPerLine());
        encodeBufferSuffix(outputStream);
    }

    protected void encodeBufferPrefix(OutputStream outputStream) throws IOException {
        this.pStream = new PrintStream(outputStream);
    }

    protected void encodeBufferSuffix(OutputStream outputStream) throws IOException {
    }

    protected void encodeLinePrefix(OutputStream outputStream, int i2) throws IOException {
    }

    protected void encodeLineSuffix(OutputStream outputStream) throws IOException {
        this.pStream.println();
    }

    protected int readFully(InputStream inputStream, byte[] bArr) throws IOException {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int read = inputStream.read();
            if (read == -1) {
                return i2;
            }
            bArr[i2] = (byte) read;
        }
        return bArr.length;
    }

    public void encode(byte[] bArr, OutputStream outputStream) throws IOException {
        encode(new ByteArrayInputStream(bArr), outputStream);
    }

    public void encodeBuffer(byte[] bArr, OutputStream outputStream) throws IOException {
        encodeBuffer(new ByteArrayInputStream(bArr), outputStream);
    }

    public String encode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encode(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            return byteArrayOutputStream.toString("8859_1");
        } catch (Exception unused) {
            throw new Error("CharacterEncoder.encode internal error");
        }
    }

    public String encodeBuffer(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encodeBuffer(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            return byteArrayOutputStream.toString();
        } catch (Exception unused) {
            throw new Error("CharacterEncoder.encodeBuffer internal error");
        }
    }

    public void encode(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        encode(getBytes(byteBuffer), outputStream);
    }

    public void encodeBuffer(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        encodeBuffer(getBytes(byteBuffer), outputStream);
    }

    public String encode(ByteBuffer byteBuffer) {
        return encode(getBytes(byteBuffer));
    }

    public String encodeBuffer(ByteBuffer byteBuffer) {
        return encodeBuffer(getBytes(byteBuffer));
    }
}
