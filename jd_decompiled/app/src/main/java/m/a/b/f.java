package m.a.b;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

/* loaded from: classes11.dex */
class f extends ByteArrayInputStream implements Cloneable {
    /* JADX INFO: Access modifiers changed from: package-private */
    public f(byte[] bArr) {
        super(bArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0170, code lost:
        if (r1 == 2) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0173, code lost:
        if (r1 != 3) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0175, code lost:
        r11 = ((java.io.ByteArrayInputStream) r17).pos;
        ((java.io.ByteArrayInputStream) r17).pos = r11 + 1;
        r12 = ((java.io.ByteArrayInputStream) r17).buf;
        r13 = ((java.io.ByteArrayInputStream) r17).pos;
        ((java.io.ByteArrayInputStream) r17).pos = r13 + 1;
        r10 = ((java.lang.Character.digit((char) r10[r11], 10) * 100) + 0) + (java.lang.Character.digit((char) r12[r13], 10) * 10);
        r12 = ((java.io.ByteArrayInputStream) r17).buf;
        r13 = ((java.io.ByteArrayInputStream) r17).pos;
        ((java.io.ByteArrayInputStream) r17).pos = r13 + 1;
        r14 = r10 + java.lang.Character.digit((char) r12[r13], 10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x01c5, code lost:
        throw new java.io.IOException("Parse " + r2 + " time, unsupported precision for seconds value");
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x01c6, code lost:
        r11 = ((java.io.ByteArrayInputStream) r17).pos;
        ((java.io.ByteArrayInputStream) r17).pos = r11 + 1;
        r14 = 0 + (java.lang.Character.digit((char) r10[r11], 10) * 100);
        r10 = ((java.io.ByteArrayInputStream) r17).buf;
        r12 = ((java.io.ByteArrayInputStream) r17).pos;
        ((java.io.ByteArrayInputStream) r17).pos = r12 + 1;
        r14 = r14 + (java.lang.Character.digit((char) r10[r12], 10) * 10);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.Date m(int r18, boolean r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 904
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m.a.b.f.m(int, boolean):java.util.Date");
    }

    public boolean equals(Object obj) {
        if (obj instanceof f) {
            return g((f) obj);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f f() {
        try {
            f fVar = (f) clone();
            fVar.mark(Integer.MAX_VALUE);
            return fVar;
        } catch (CloneNotSupportedException e2) {
            throw new IllegalArgumentException(e2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g(f fVar) {
        if (this == fVar) {
            return true;
        }
        int available = available();
        if (fVar.available() != available) {
            return false;
        }
        for (int i2 = 0; i2 < available; i2++) {
            if (((ByteArrayInputStream) this).buf[((ByteArrayInputStream) this).pos + i2] != ((ByteArrayInputStream) fVar).buf[((ByteArrayInputStream) fVar).pos + i2]) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BigInteger h(int i2, boolean z) throws IOException {
        if (i2 <= available()) {
            if (i2 != 0) {
                byte[] bArr = new byte[i2];
                System.arraycopy(((ByteArrayInputStream) this).buf, ((ByteArrayInputStream) this).pos, bArr, 0, i2);
                skip(i2);
                if (z) {
                    return new BigInteger(1, bArr);
                }
                return new BigInteger(bArr);
            }
            throw new IOException("Invalid encoding: zero length Int value");
        }
        throw new IOException("short read of integer");
    }

    public int hashCode() {
        int available = available();
        int i2 = ((ByteArrayInputStream) this).pos;
        int i3 = 0;
        for (int i4 = 0; i4 < available; i4++) {
            i3 += ((ByteArrayInputStream) this).buf[i2 + i4] * i4;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] i() throws IOException {
        return j(available());
    }

    public byte[] j(int i2) throws IOException {
        if (i2 <= available()) {
            if (i2 != 0) {
                byte[] bArr = ((ByteArrayInputStream) this).buf;
                int i3 = ((ByteArrayInputStream) this).pos;
                byte b = bArr[i3];
                if (b >= 0 && b <= 7) {
                    int i4 = i2 - 1;
                    byte[] bArr2 = new byte[i4];
                    System.arraycopy(bArr, i3 + 1, bArr2, 0, i4);
                    if (b != 0) {
                        int i5 = i2 - 2;
                        bArr2[i5] = (byte) (bArr2[i5] & (255 << b));
                    }
                    skip(i2);
                    return bArr2;
                }
                throw new IOException("Invalid number of padding bits");
            }
            throw new IOException("Invalid encoding: zero length bit string");
        }
        throw new IOException("short read of bit string");
    }

    public Date k(int i2) throws IOException {
        if (i2 <= available()) {
            if (i2 >= 13 && i2 <= 23) {
                return m(i2, true);
            }
            throw new IOException("DER Generalized Time length error");
        }
        throw new IOException("short read of DER Generalized Time");
    }

    public int l(int i2) throws IOException {
        BigInteger h2 = h(i2, false);
        if (h2.compareTo(BigInteger.valueOf(-2147483648L)) >= 0) {
            if (h2.compareTo(BigInteger.valueOf(2147483647L)) <= 0) {
                return h2.intValue();
            }
            throw new IOException("Integer exceeds maximum valid value");
        }
        throw new IOException("Integer below minimum valid value");
    }

    public Date n(int i2) throws IOException {
        if (i2 <= available()) {
            if (i2 >= 11 && i2 <= 17) {
                return m(i2, false);
            }
            throw new IOException("DER UTC Time length error");
        }
        throw new IOException("short read of DER UTC Time");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a o() throws IOException {
        if (((ByteArrayInputStream) this).pos >= ((ByteArrayInputStream) this).count) {
            return null;
        }
        int available = available();
        byte[] bArr = ((ByteArrayInputStream) this).buf;
        int i2 = ((ByteArrayInputStream) this).pos;
        int i3 = bArr[i2] & 255;
        if (i3 <= 7) {
            int i4 = available - 1;
            byte[] bArr2 = new byte[i4];
            int i5 = i4 == 0 ? 0 : (i4 * 8) - i3;
            System.arraycopy(bArr, i2 + 1, bArr2, 0, i4);
            a aVar = new a(i5, bArr2);
            ((ByteArrayInputStream) this).pos = ((ByteArrayInputStream) this).count;
            return aVar;
        }
        throw new IOException("Invalid value for unused bits: " + i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int p() throws IOException {
        int i2 = ((ByteArrayInputStream) this).pos;
        if (i2 < ((ByteArrayInputStream) this).count) {
            return ((ByteArrayInputStream) this).buf[i2];
        }
        throw new IOException("out of data");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] q() {
        int available = available();
        if (available <= 0) {
            return null;
        }
        byte[] bArr = new byte[available];
        System.arraycopy(((ByteArrayInputStream) this).buf, ((ByteArrayInputStream) this).pos, bArr, 0, available);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(int i2) throws IOException {
        if (i2 <= available()) {
            ((ByteArrayInputStream) this).count = ((ByteArrayInputStream) this).pos + i2;
            return;
        }
        throw new IOException("insufficient data");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(byte[] bArr, int i2, int i3) {
        super(bArr, i2, i3);
    }
}
