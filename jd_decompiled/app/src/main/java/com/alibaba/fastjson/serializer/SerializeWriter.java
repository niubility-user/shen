package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.JSONLexer;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public final class SerializeWriter extends Writer {
    public static final char[] DIGITS;
    static final byte[] specicalFlags_doubleQuotes;
    static final byte[] specicalFlags_singleQuotes;
    protected char[] buf;
    protected int count;
    protected int features;
    protected final Writer writer;
    private static final ThreadLocal<char[]> bufLocal = new ThreadLocal<>();
    static final int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
    static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static final char[] DigitTens = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    static final char[] DigitOnes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final char[] ascii_chars = {'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
    static final char[] replaceChars = new char[93];

    static {
        byte[] bArr = new byte[161];
        specicalFlags_doubleQuotes = bArr;
        byte[] bArr2 = new byte[161];
        specicalFlags_singleQuotes = bArr2;
        bArr[0] = 4;
        bArr[1] = 4;
        bArr[2] = 4;
        bArr[3] = 4;
        bArr[4] = 4;
        bArr[5] = 4;
        bArr[6] = 4;
        bArr[7] = 4;
        bArr[8] = 1;
        bArr[9] = 1;
        bArr[10] = 1;
        bArr[11] = 4;
        bArr[12] = 1;
        bArr[13] = 1;
        bArr[34] = 1;
        bArr[92] = 1;
        bArr2[0] = 4;
        bArr2[1] = 4;
        bArr2[2] = 4;
        bArr2[3] = 4;
        bArr2[4] = 4;
        bArr2[5] = 4;
        bArr2[6] = 4;
        bArr2[7] = 4;
        bArr2[8] = 1;
        bArr2[9] = 1;
        bArr2[10] = 1;
        bArr2[11] = 4;
        bArr2[12] = 1;
        bArr2[13] = 1;
        bArr2[92] = 1;
        bArr2[39] = 1;
        for (int i2 = 14; i2 <= 31; i2++) {
            specicalFlags_doubleQuotes[i2] = 4;
            specicalFlags_singleQuotes[i2] = 4;
        }
        for (int i3 = 127; i3 < 160; i3++) {
            specicalFlags_doubleQuotes[i3] = 4;
            specicalFlags_singleQuotes[i3] = 4;
        }
        char[] cArr = replaceChars;
        cArr[0] = '0';
        cArr[1] = '1';
        cArr[2] = '2';
        cArr[3] = '3';
        cArr[4] = '4';
        cArr[5] = '5';
        cArr[6] = '6';
        cArr[7] = '7';
        cArr[8] = 'b';
        cArr[9] = 't';
        cArr[10] = 'n';
        cArr[11] = 'v';
        cArr[12] = 'f';
        cArr[13] = 'r';
        cArr[34] = Typography.quote;
        cArr[39] = '\'';
        cArr[47] = '/';
        cArr[92] = '\\';
        DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public SerializeWriter() {
        this((Writer) null);
    }

    public static void getChars(long j2, int i2, char[] cArr) {
        char c2;
        if (j2 < 0) {
            c2 = '-';
            j2 = -j2;
        } else {
            c2 = 0;
        }
        while (j2 > 2147483647L) {
            long j3 = j2 / 100;
            int i3 = (int) (j2 - (((j3 << 6) + (j3 << 5)) + (j3 << 2)));
            int i4 = i2 - 1;
            cArr[i4] = DigitOnes[i3];
            i2 = i4 - 1;
            cArr[i2] = DigitTens[i3];
            j2 = j3;
        }
        int i5 = (int) j2;
        while (i5 >= 65536) {
            int i6 = i5 / 100;
            int i7 = i5 - (((i6 << 6) + (i6 << 5)) + (i6 << 2));
            int i8 = i2 - 1;
            cArr[i8] = DigitOnes[i7];
            i2 = i8 - 1;
            cArr[i2] = DigitTens[i7];
            i5 = i6;
        }
        while (true) {
            int i9 = (52429 * i5) >>> 19;
            i2--;
            cArr[i2] = digits[i5 - ((i9 << 3) + (i9 << 1))];
            if (i9 == 0) {
                break;
            }
            i5 = i9;
        }
        if (c2 != 0) {
            cArr[i2 - 1] = c2;
        }
    }

    private void writeKeyWithDoubleQuoteIfHasSpecial(String str) {
        int length = str.length();
        boolean z = true;
        int i2 = this.count + length + 1;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                if (length == 0) {
                    write(34);
                    write(34);
                    write(58);
                    return;
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        z = false;
                        break;
                    }
                    char charAt = str.charAt(i3);
                    byte[] bArr = specicalFlags_doubleQuotes;
                    if (charAt < bArr.length && bArr[charAt] != 0) {
                        break;
                    }
                    i3++;
                }
                if (z) {
                    write(34);
                }
                for (int i4 = 0; i4 < length; i4++) {
                    char charAt2 = str.charAt(i4);
                    byte[] bArr2 = specicalFlags_doubleQuotes;
                    if (charAt2 < bArr2.length && bArr2[charAt2] != 0) {
                        write(92);
                        write(replaceChars[charAt2]);
                    } else {
                        write(charAt2);
                    }
                }
                if (z) {
                    write(34);
                }
                write(58);
                return;
            }
            expandCapacity(i2);
        }
        if (length == 0) {
            int i5 = this.count;
            if (i5 + 3 > this.buf.length) {
                expandCapacity(i5 + 3);
            }
            char[] cArr = this.buf;
            int i6 = this.count;
            int i7 = i6 + 1;
            this.count = i7;
            cArr[i6] = Typography.quote;
            int i8 = i7 + 1;
            this.count = i8;
            cArr[i7] = Typography.quote;
            this.count = i8 + 1;
            cArr[i8] = ':';
            return;
        }
        int i9 = this.count;
        int i10 = i9 + length;
        str.getChars(0, length, this.buf, i9);
        this.count = i2;
        int i11 = i9;
        boolean z2 = false;
        while (i11 < i10) {
            char[] cArr2 = this.buf;
            char c2 = cArr2[i11];
            byte[] bArr3 = specicalFlags_doubleQuotes;
            if (c2 < bArr3.length && bArr3[c2] != 0) {
                if (!z2) {
                    i2 += 3;
                    if (i2 > cArr2.length) {
                        expandCapacity(i2);
                    }
                    this.count = i2;
                    char[] cArr3 = this.buf;
                    int i12 = i11 + 1;
                    System.arraycopy(cArr3, i12, cArr3, i11 + 3, (i10 - i11) - 1);
                    char[] cArr4 = this.buf;
                    System.arraycopy(cArr4, 0, cArr4, 1, i11);
                    char[] cArr5 = this.buf;
                    cArr5[i9] = Typography.quote;
                    cArr5[i12] = '\\';
                    int i13 = i12 + 1;
                    cArr5[i13] = replaceChars[c2];
                    i10 += 2;
                    cArr5[this.count - 2] = Typography.quote;
                    i11 = i13;
                    z2 = true;
                } else {
                    i2++;
                    if (i2 > cArr2.length) {
                        expandCapacity(i2);
                    }
                    this.count = i2;
                    char[] cArr6 = this.buf;
                    int i14 = i11 + 1;
                    System.arraycopy(cArr6, i14, cArr6, i11 + 2, i10 - i11);
                    char[] cArr7 = this.buf;
                    cArr7[i11] = '\\';
                    cArr7[i14] = replaceChars[c2];
                    i10++;
                    i11 = i14;
                }
            }
            i11++;
        }
        this.buf[this.count - 1] = ':';
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String str) {
        int length = str.length();
        boolean z = true;
        int i2 = this.count + length + 1;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                if (length == 0) {
                    write(39);
                    write(39);
                    write(58);
                    return;
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        z = false;
                        break;
                    }
                    char charAt = str.charAt(i3);
                    byte[] bArr = specicalFlags_singleQuotes;
                    if (charAt < bArr.length && bArr[charAt] != 0) {
                        break;
                    }
                    i3++;
                }
                if (z) {
                    write(39);
                }
                for (int i4 = 0; i4 < length; i4++) {
                    char charAt2 = str.charAt(i4);
                    byte[] bArr2 = specicalFlags_singleQuotes;
                    if (charAt2 < bArr2.length && bArr2[charAt2] != 0) {
                        write(92);
                        write(replaceChars[charAt2]);
                    } else {
                        write(charAt2);
                    }
                }
                if (z) {
                    write(39);
                }
                write(58);
                return;
            }
            expandCapacity(i2);
        }
        if (length == 0) {
            int i5 = this.count;
            if (i5 + 3 > this.buf.length) {
                expandCapacity(i5 + 3);
            }
            char[] cArr = this.buf;
            int i6 = this.count;
            int i7 = i6 + 1;
            this.count = i7;
            cArr[i6] = '\'';
            int i8 = i7 + 1;
            this.count = i8;
            cArr[i7] = '\'';
            this.count = i8 + 1;
            cArr[i8] = ':';
            return;
        }
        int i9 = this.count;
        int i10 = i9 + length;
        str.getChars(0, length, this.buf, i9);
        this.count = i2;
        int i11 = i9;
        boolean z2 = false;
        while (i11 < i10) {
            char[] cArr2 = this.buf;
            char c2 = cArr2[i11];
            byte[] bArr3 = specicalFlags_singleQuotes;
            if (c2 < bArr3.length && bArr3[c2] != 0) {
                if (!z2) {
                    i2 += 3;
                    if (i2 > cArr2.length) {
                        expandCapacity(i2);
                    }
                    this.count = i2;
                    char[] cArr3 = this.buf;
                    int i12 = i11 + 1;
                    System.arraycopy(cArr3, i12, cArr3, i11 + 3, (i10 - i11) - 1);
                    char[] cArr4 = this.buf;
                    System.arraycopy(cArr4, 0, cArr4, 1, i11);
                    char[] cArr5 = this.buf;
                    cArr5[i9] = '\'';
                    cArr5[i12] = '\\';
                    int i13 = i12 + 1;
                    cArr5[i13] = replaceChars[c2];
                    i10 += 2;
                    cArr5[this.count - 2] = '\'';
                    i11 = i13;
                    z2 = true;
                } else {
                    i2++;
                    if (i2 > cArr2.length) {
                        expandCapacity(i2);
                    }
                    this.count = i2;
                    char[] cArr6 = this.buf;
                    int i14 = i11 + 1;
                    System.arraycopy(cArr6, i14, cArr6, i11 + 2, i10 - i11);
                    char[] cArr7 = this.buf;
                    cArr7[i11] = '\\';
                    cArr7[i14] = replaceChars[c2];
                    i10++;
                    i11 = i14;
                }
            }
            i11++;
        }
        this.buf[i2 - 1] = ':';
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        char[] cArr = this.buf;
        if (cArr.length <= 8192) {
            bufLocal.set(cArr);
        }
        this.buf = null;
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        if (z) {
            this.features = serializerFeature.mask | this.features;
            return;
        }
        this.features = (serializerFeature.mask ^ (-1)) & this.features;
    }

    public void expandCapacity(int i2) {
        char[] cArr = this.buf;
        int length = ((cArr.length * 3) / 2) + 1;
        if (length >= i2) {
            i2 = length;
        }
        char[] cArr2 = new char[i2];
        System.arraycopy(cArr, 0, cArr2, 0, this.count);
        this.buf = cArr2;
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        Writer writer = this.writer;
        if (writer == null) {
            return;
        }
        try {
            writer.write(this.buf, 0, this.count);
            this.writer.flush();
            this.count = 0;
        } catch (IOException e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return (serializerFeature.mask & this.features) != 0;
    }

    public byte[] toBytes(String str) {
        if (this.writer == null) {
            if (str == null) {
                str = "UTF-8";
            }
            try {
                return new String(this.buf, 0, this.count).getBytes(str);
            } catch (UnsupportedEncodingException e2) {
                throw new JSONException("toBytes error", e2);
            }
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    @Override // java.io.Writer
    public void write(int i2) {
        int i3 = 1;
        int i4 = this.count + 1;
        if (i4 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i4);
            } else {
                flush();
                this.buf[this.count] = (char) i2;
                this.count = i3;
            }
        }
        i3 = i4;
        this.buf[this.count] = (char) i2;
        this.count = i3;
    }

    public void writeByteArray(byte[] bArr) {
        int length = bArr.length;
        boolean z = (this.features & SerializerFeature.UseSingleQuotes.mask) != 0;
        char c2 = z ? '\'' : Typography.quote;
        if (length == 0) {
            write(z ? "''" : "\"\"");
            return;
        }
        char[] cArr = JSONLexer.CA;
        int i2 = (length / 3) * 3;
        int i3 = length - 1;
        int i4 = this.count;
        int i5 = (((i3 / 3) + 1) << 2) + i4 + 2;
        if (i5 > this.buf.length) {
            if (this.writer != null) {
                write(c2);
                int i6 = 0;
                while (i6 < i2) {
                    int i7 = i6 + 1;
                    int i8 = i7 + 1;
                    int i9 = ((bArr[i6] & 255) << 16) | ((bArr[i7] & 255) << 8) | (bArr[i8] & 255);
                    write(cArr[(i9 >>> 18) & 63]);
                    write(cArr[(i9 >>> 12) & 63]);
                    write(cArr[(i9 >>> 6) & 63]);
                    write(cArr[i9 & 63]);
                    i6 = i8 + 1;
                }
                int i10 = length - i2;
                if (i10 > 0) {
                    int i11 = ((bArr[i2] & 255) << 10) | (i10 == 2 ? (bArr[i3] & 255) << 2 : 0);
                    write(cArr[i11 >> 12]);
                    write(cArr[(i11 >>> 6) & 63]);
                    write(i10 == 2 ? cArr[i11 & 63] : '=');
                    write(61);
                }
                write(c2);
                return;
            }
            expandCapacity(i5);
        }
        this.count = i5;
        int i12 = i4 + 1;
        this.buf[i4] = c2;
        int i13 = 0;
        while (i13 < i2) {
            int i14 = i13 + 1;
            int i15 = i14 + 1;
            int i16 = ((bArr[i13] & 255) << 16) | ((bArr[i14] & 255) << 8);
            int i17 = i15 + 1;
            int i18 = i16 | (bArr[i15] & 255);
            char[] cArr2 = this.buf;
            int i19 = i12 + 1;
            cArr2[i12] = cArr[(i18 >>> 18) & 63];
            int i20 = i19 + 1;
            cArr2[i19] = cArr[(i18 >>> 12) & 63];
            int i21 = i20 + 1;
            cArr2[i20] = cArr[(i18 >>> 6) & 63];
            i12 = i21 + 1;
            cArr2[i21] = cArr[i18 & 63];
            i13 = i17;
        }
        int i22 = length - i2;
        if (i22 > 0) {
            int i23 = ((bArr[i2] & 255) << 10) | (i22 == 2 ? (bArr[i3] & 255) << 2 : 0);
            char[] cArr3 = this.buf;
            cArr3[i5 - 5] = cArr[i23 >> 12];
            cArr3[i5 - 4] = cArr[(i23 >>> 6) & 63];
            cArr3[i5 - 3] = i22 == 2 ? cArr[i23 & 63] : '=';
            cArr3[i5 - 2] = '=';
        }
        this.buf[i5 - 1] = c2;
    }

    public void writeFieldName(String str, boolean z) {
        int i2 = this.features;
        if ((SerializerFeature.UseSingleQuotes.mask & i2) != 0) {
            if ((SerializerFeature.QuoteFieldNames.mask & i2) != 0) {
                writeStringWithSingleQuote(str);
                write(58);
                return;
            }
            writeKeyWithSingleQuoteIfHasSpecial(str);
        } else if ((i2 & SerializerFeature.QuoteFieldNames.mask) != 0) {
            writeStringWithDoubleQuote(str, ':', z);
        } else {
            writeKeyWithDoubleQuoteIfHasSpecial(str);
        }
    }

    public void writeInt(int i2) {
        if (i2 == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int i3 = 0;
        while ((i2 < 0 ? -i2 : i2) > sizeTable[i3]) {
            i3++;
        }
        int i4 = i3 + 1;
        if (i2 < 0) {
            i4++;
        }
        int i5 = this.count + i4;
        if (i5 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i5);
            } else {
                char[] cArr = new char[i4];
                getChars(i2, i4, cArr);
                write(cArr, 0, i4);
                return;
            }
        }
        getChars(i2, i5, this.buf);
        this.count = i5;
    }

    public void writeLong(long j2) {
        if (j2 == Long.MIN_VALUE) {
            write("-9223372036854775808");
            return;
        }
        long j3 = j2 < 0 ? -j2 : j2;
        int i2 = 1;
        long j4 = 10;
        while (true) {
            if (i2 >= 19) {
                i2 = 0;
                break;
            } else if (j3 < j4) {
                break;
            } else {
                j4 *= 10;
                i2++;
            }
        }
        int i3 = i2 != 0 ? i2 : 19;
        if (j2 < 0) {
            i3++;
        }
        int i4 = this.count + i3;
        if (i4 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i4);
            } else {
                char[] cArr = new char[i3];
                getChars(j2, i3, cArr);
                write(cArr, 0, i3);
                return;
            }
        }
        getChars(j2, i4, this.buf);
        this.count = i4;
    }

    public void writeNull() {
        write(DYConstants.DY_NULL_STR);
    }

    public void writeString(String str) {
        if ((this.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
            writeStringWithSingleQuote(str);
        } else {
            writeStringWithDoubleQuote(str, (char) 0, true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:177:0x0092, code lost:
        if (r14 == (-1)) goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0094, code lost:
        r15 = r3;
        r14 = r12;
        r16 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x00c0, code lost:
        if ((com.alibaba.fastjson.serializer.SerializerFeature.WriteSlashAsSpecial.mask & r17.features) != 0) goto L195;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x00c8, code lost:
        if (r3 != '\\') goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x00d1, code lost:
        if (r3 != '\"') goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x00e4, code lost:
        if (r14 == (-1)) goto L178;
     */
    /* JADX WARN: Removed duplicated region for block: B:206:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x00e7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeStringWithDoubleQuote(java.lang.String r18, char r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 587
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.SerializeWriter.writeStringWithDoubleQuote(java.lang.String, char, boolean):void");
    }

    public void writeStringWithSingleQuote(String str) {
        int i2 = 0;
        if (str == null) {
            int i3 = this.count + 4;
            if (i3 > this.buf.length) {
                expandCapacity(i3);
            }
            DYConstants.DY_NULL_STR.getChars(0, 4, this.buf, this.count);
            this.count = i3;
            return;
        }
        int length = str.length();
        int i4 = this.count + length + 2;
        if (i4 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i2 < str.length()) {
                    char charAt = str.charAt(i2);
                    if (charAt > '\r' && charAt != '\\' && charAt != '\'' && (charAt != '/' || (this.features & SerializerFeature.WriteSlashAsSpecial.mask) == 0)) {
                        write(charAt);
                    } else {
                        write(92);
                        write(replaceChars[charAt]);
                    }
                    i2++;
                }
                write(39);
                return;
            }
            expandCapacity(i4);
        }
        int i5 = this.count;
        int i6 = i5 + 1;
        int i7 = i6 + length;
        char[] cArr = this.buf;
        cArr[i5] = '\'';
        str.getChars(0, length, cArr, i6);
        this.count = i4;
        int i8 = -1;
        char c2 = 0;
        for (int i9 = i6; i9 < i7; i9++) {
            char c3 = this.buf[i9];
            if (c3 <= '\r' || c3 == '\\' || c3 == '\'' || (c3 == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0)) {
                i2++;
                i8 = i9;
                c2 = c3;
            }
        }
        int i10 = i4 + i2;
        if (i10 > this.buf.length) {
            expandCapacity(i10);
        }
        this.count = i10;
        if (i2 == 1) {
            char[] cArr2 = this.buf;
            int i11 = i8 + 1;
            System.arraycopy(cArr2, i11, cArr2, i8 + 2, (i7 - i8) - 1);
            char[] cArr3 = this.buf;
            cArr3[i8] = '\\';
            cArr3[i11] = replaceChars[c2];
        } else if (i2 > 1) {
            char[] cArr4 = this.buf;
            int i12 = i8 + 1;
            System.arraycopy(cArr4, i12, cArr4, i8 + 2, (i7 - i8) - 1);
            char[] cArr5 = this.buf;
            cArr5[i8] = '\\';
            cArr5[i12] = replaceChars[c2];
            int i13 = i7 + 1;
            for (int i14 = i12 - 2; i14 >= i6; i14--) {
                char[] cArr6 = this.buf;
                char c4 = cArr6[i14];
                if (c4 <= '\r' || c4 == '\\' || c4 == '\'' || (c4 == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0)) {
                    int i15 = i14 + 1;
                    System.arraycopy(cArr6, i15, cArr6, i14 + 2, (i13 - i14) - 1);
                    char[] cArr7 = this.buf;
                    cArr7[i14] = '\\';
                    cArr7[i15] = replaceChars[c4];
                    i13++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeTo(Writer writer) throws IOException {
        if (this.writer == null) {
            writer.write(this.buf, 0, this.count);
            return;
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public SerializeWriter(Writer writer) {
        this.writer = writer;
        this.features = JDJSON.DEFAULT_GENERATE_FEATURE;
        ThreadLocal<char[]> threadLocal = bufLocal;
        this.buf = threadLocal.get();
        if (threadLocal != null) {
            threadLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
    }

    public void writeTo(OutputStream outputStream, String str) throws IOException {
        writeTo(outputStream, Charset.forName(str));
    }

    public void writeTo(OutputStream outputStream, Charset charset) throws IOException {
        if (this.writer == null) {
            outputStream.write(new String(this.buf, 0, this.count).getBytes(charset.name()));
            return;
        }
        throw new UnsupportedOperationException("writer not null");
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? DYConstants.DY_NULL_STR : charSequence.toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i2, int i3) {
        int i4;
        if (i2 < 0 || i2 > cArr.length || i3 < 0 || (i4 = i2 + i3) > cArr.length || i4 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i3 == 0) {
            return;
        }
        int i5 = this.count + i3;
        if (i5 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i5);
            }
            do {
                char[] cArr2 = this.buf;
                int length = cArr2.length;
                int i6 = this.count;
                int i7 = length - i6;
                System.arraycopy(cArr, i2, cArr2, i6, i7);
                this.count = this.buf.length;
                flush();
                i3 -= i7;
                i2 += i7;
            } while (i3 > this.buf.length);
            i5 = i3;
        }
        System.arraycopy(cArr, i2, this.buf, this.count, i3);
        this.count = i5;
    }

    public SerializeWriter(SerializerFeature... serializerFeatureArr) {
        this(null, 0, serializerFeatureArr);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence charSequence, int i2, int i3) {
        if (charSequence == null) {
            charSequence = DYConstants.DY_NULL_STR;
        }
        String charSequence2 = charSequence.subSequence(i2, i3).toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    public SerializeWriter(Writer writer, int i2, SerializerFeature[] serializerFeatureArr) {
        this.writer = writer;
        ThreadLocal<char[]> threadLocal = bufLocal;
        char[] cArr = threadLocal.get();
        this.buf = cArr;
        if (cArr != null) {
            threadLocal.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
        for (SerializerFeature serializerFeature : serializerFeatureArr) {
            i2 |= serializerFeature.mask;
        }
        this.features = i2;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(char c2) {
        write(c2);
        return this;
    }

    public SerializeWriter(int i2) {
        this(null, i2);
    }

    public SerializeWriter(Writer writer, int i2) {
        this.writer = writer;
        if (i2 > 0) {
            this.buf = new char[i2];
            return;
        }
        throw new IllegalArgumentException("Negative initial size: " + i2);
    }

    @Override // java.io.Writer
    public void write(String str, int i2, int i3) {
        int i4;
        int i5 = this.count + i3;
        if (i5 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i5);
            } else {
                while (true) {
                    char[] cArr = this.buf;
                    int length = cArr.length;
                    int i6 = this.count;
                    int i7 = length - i6;
                    i4 = i2 + i7;
                    str.getChars(i2, i4, cArr, i6);
                    this.count = this.buf.length;
                    flush();
                    i3 -= i7;
                    if (i3 <= this.buf.length) {
                        break;
                    }
                    i2 = i4;
                }
                i5 = i3;
                i2 = i4;
            }
        }
        str.getChars(i2, i3 + i2, this.buf, this.count);
        this.count = i5;
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str == null) {
            writeNull();
        } else {
            write(str, 0, str.length());
        }
    }

    public void write(boolean z) {
        write(z ? DYConstants.DY_TRUE : DYConstants.DY_FALSE);
    }
}
