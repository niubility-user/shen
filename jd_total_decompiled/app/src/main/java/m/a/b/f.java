package m.a.b;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

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
    */
    private Date m(int i2, boolean z) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        byte[] bArr;
        String str = "UTC";
        if (z) {
            byte[] bArr2 = ((ByteArrayInputStream) this).buf;
            int i7 = ((ByteArrayInputStream) this).pos;
            ((ByteArrayInputStream) this).pos = i7 + 1;
            byte[] bArr3 = ((ByteArrayInputStream) this).buf;
            int i8 = ((ByteArrayInputStream) this).pos;
            ((ByteArrayInputStream) this).pos = i8 + 1;
            int digit = (Character.digit((char) bArr2[i7], 10) * 1000) + (Character.digit((char) bArr3[i8], 10) * 100);
            byte[] bArr4 = ((ByteArrayInputStream) this).buf;
            int i9 = ((ByteArrayInputStream) this).pos;
            ((ByteArrayInputStream) this).pos = i9 + 1;
            int digit2 = digit + (Character.digit((char) bArr4[i9], 10) * 10);
            byte[] bArr5 = ((ByteArrayInputStream) this).buf;
            int i10 = ((ByteArrayInputStream) this).pos;
            ((ByteArrayInputStream) this).pos = i10 + 1;
            int digit3 = digit2 + Character.digit((char) bArr5[i10], 10);
            i4 = i2 - 2;
            i3 = digit3;
            str = "Generalized";
        } else {
            byte[] bArr6 = ((ByteArrayInputStream) this).buf;
            int i11 = ((ByteArrayInputStream) this).pos;
            ((ByteArrayInputStream) this).pos = i11 + 1;
            byte[] bArr7 = ((ByteArrayInputStream) this).buf;
            int i12 = ((ByteArrayInputStream) this).pos;
            ((ByteArrayInputStream) this).pos = i12 + 1;
            int digit4 = (Character.digit((char) bArr6[i11], 10) * 10) + Character.digit((char) bArr7[i12], 10);
            i3 = digit4 < 50 ? digit4 + 2000 : digit4 + 1900;
            i4 = i2;
        }
        byte[] bArr8 = ((ByteArrayInputStream) this).buf;
        int i13 = ((ByteArrayInputStream) this).pos;
        ((ByteArrayInputStream) this).pos = i13 + 1;
        byte[] bArr9 = ((ByteArrayInputStream) this).buf;
        int i14 = ((ByteArrayInputStream) this).pos;
        ((ByteArrayInputStream) this).pos = i14 + 1;
        int digit5 = (Character.digit((char) bArr8[i13], 10) * 10) + Character.digit((char) bArr9[i14], 10);
        byte[] bArr10 = ((ByteArrayInputStream) this).buf;
        int i15 = ((ByteArrayInputStream) this).pos;
        ((ByteArrayInputStream) this).pos = i15 + 1;
        byte[] bArr11 = ((ByteArrayInputStream) this).buf;
        int i16 = ((ByteArrayInputStream) this).pos;
        ((ByteArrayInputStream) this).pos = i16 + 1;
        int digit6 = (Character.digit((char) bArr10[i15], 10) * 10) + Character.digit((char) bArr11[i16], 10);
        byte[] bArr12 = ((ByteArrayInputStream) this).buf;
        int i17 = ((ByteArrayInputStream) this).pos;
        ((ByteArrayInputStream) this).pos = i17 + 1;
        byte[] bArr13 = ((ByteArrayInputStream) this).buf;
        int i18 = ((ByteArrayInputStream) this).pos;
        ((ByteArrayInputStream) this).pos = i18 + 1;
        int digit7 = (Character.digit((char) bArr12[i17], 10) * 10) + Character.digit((char) bArr13[i18], 10);
        byte[] bArr14 = ((ByteArrayInputStream) this).buf;
        int i19 = ((ByteArrayInputStream) this).pos;
        ((ByteArrayInputStream) this).pos = i19 + 1;
        byte[] bArr15 = ((ByteArrayInputStream) this).buf;
        int i20 = ((ByteArrayInputStream) this).pos;
        ((ByteArrayInputStream) this).pos = i20 + 1;
        int digit8 = (Character.digit((char) bArr14[i19], 10) * 10) + Character.digit((char) bArr15[i20], 10);
        int i21 = i4 - 10;
        byte b = 90;
        if (i21 <= 2 || i21 >= 12) {
            i5 = 0;
            i6 = 0;
        } else {
            byte[] bArr16 = ((ByteArrayInputStream) this).buf;
            int i22 = ((ByteArrayInputStream) this).pos;
            ((ByteArrayInputStream) this).pos = i22 + 1;
            byte[] bArr17 = ((ByteArrayInputStream) this).buf;
            int i23 = ((ByteArrayInputStream) this).pos;
            ((ByteArrayInputStream) this).pos = i23 + 1;
            int digit9 = (Character.digit((char) bArr16[i22], 10) * 10) + Character.digit((char) bArr17[i23], 10);
            i21 -= 2;
            byte[] bArr18 = ((ByteArrayInputStream) this).buf;
            int i24 = ((ByteArrayInputStream) this).pos;
            if (bArr18[i24] == 46 || bArr18[i24] == 44) {
                int i25 = i21 - 1;
                int i26 = i24 + 1;
                ((ByteArrayInputStream) this).pos = i26;
                int i27 = 0;
                while (true) {
                    bArr = ((ByteArrayInputStream) this).buf;
                    if (bArr[i26] == b || bArr[i26] == 43 || bArr[i26] == 45) {
                        break;
                    }
                    i26++;
                    i27++;
                    b = 90;
                }
                int i28 = ((ByteArrayInputStream) this).pos;
                ((ByteArrayInputStream) this).pos = i28 + 1;
                int digit10 = 0 + (Character.digit((char) bArr[i28], 10) * 100);
                i21 = i25 - i27;
                i5 = digit10;
                i6 = digit9;
            } else {
                i6 = digit9;
                i5 = 0;
            }
        }
        if (digit5 != 0 && digit6 != 0 && digit5 <= 12 && digit6 <= 31 && digit7 < 24 && digit8 < 60 && i6 < 60) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(1, i3);
            gregorianCalendar.set(2, digit5);
            gregorianCalendar.set(5, digit6);
            gregorianCalendar.set(10, digit7);
            gregorianCalendar.set(12, digit8);
            gregorianCalendar.set(13, i6);
            gregorianCalendar.set(14, i5);
            long timeInMillis = gregorianCalendar.getTimeInMillis();
            if (i21 != 1 && i21 != 5) {
                throw new IOException("Parse " + str + " time, invalid offset");
            }
            byte[] bArr19 = ((ByteArrayInputStream) this).buf;
            int i29 = ((ByteArrayInputStream) this).pos;
            int i30 = i29 + 1;
            ((ByteArrayInputStream) this).pos = i30;
            byte b2 = bArr19[i29];
            if (b2 == 43) {
                ((ByteArrayInputStream) this).pos = i30 + 1;
                byte[] bArr20 = ((ByteArrayInputStream) this).buf;
                int i31 = ((ByteArrayInputStream) this).pos;
                ((ByteArrayInputStream) this).pos = i31 + 1;
                int digit11 = (Character.digit((char) bArr19[i30], 10) * 10) + Character.digit((char) bArr20[i31], 10);
                byte[] bArr21 = ((ByteArrayInputStream) this).buf;
                int i32 = ((ByteArrayInputStream) this).pos;
                ((ByteArrayInputStream) this).pos = i32 + 1;
                byte[] bArr22 = ((ByteArrayInputStream) this).buf;
                int i33 = ((ByteArrayInputStream) this).pos;
                ((ByteArrayInputStream) this).pos = i33 + 1;
                int digit12 = (Character.digit((char) bArr21[i32], 10) * 10) + Character.digit((char) bArr22[i33], 10);
                if (digit11 >= 24 || digit12 >= 60) {
                    throw new IOException("Parse " + str + " time, +hhmm");
                }
                timeInMillis -= (((digit11 * 60) + digit12) * 60) * 1000;
            } else if (b2 == 45) {
                ((ByteArrayInputStream) this).pos = i30 + 1;
                byte[] bArr23 = ((ByteArrayInputStream) this).buf;
                int i34 = ((ByteArrayInputStream) this).pos;
                ((ByteArrayInputStream) this).pos = i34 + 1;
                int digit13 = (Character.digit((char) bArr19[i30], 10) * 10) + Character.digit((char) bArr23[i34], 10);
                byte[] bArr24 = ((ByteArrayInputStream) this).buf;
                int i35 = ((ByteArrayInputStream) this).pos;
                ((ByteArrayInputStream) this).pos = i35 + 1;
                byte[] bArr25 = ((ByteArrayInputStream) this).buf;
                int i36 = ((ByteArrayInputStream) this).pos;
                ((ByteArrayInputStream) this).pos = i36 + 1;
                int digit14 = (Character.digit((char) bArr24[i35], 10) * 10) + Character.digit((char) bArr25[i36], 10);
                if (digit13 >= 24 || digit14 >= 60) {
                    throw new IOException("Parse " + str + " time, -hhmm");
                }
                timeInMillis += ((digit13 * 60) + digit14) * 60 * 1000;
            } else if (b2 != 90) {
                throw new IOException("Parse " + str + " time, garbage offset");
            }
            return new Date(timeInMillis);
        }
        throw new IOException("Parse " + str + " time, invalid format");
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
