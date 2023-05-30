package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jingdong.sdk.platform.business.personal.R2;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public final class JSONLexer {
    public static final char[] CA;
    public static final int END = 4;
    public static final char EOI = 26;
    static final int[] IA;
    public static final int NOT_MATCH = -1;
    public static final int NOT_MATCH_NAME = -2;
    public static final int UNKNOWN = 0;
    private static boolean V6 = false;
    public static final int VALUE = 3;
    protected static final int[] digits;
    public static final boolean[] firstIdentifierFlags;
    public static final boolean[] identifierFlags;
    private static final ThreadLocal<char[]> sbufLocal;
    protected int bp;
    public Calendar calendar;
    protected char ch;
    public boolean disableCircularReferenceDetect;
    protected int eofPos;
    protected boolean exp;
    public int features;
    protected long fieldHash;
    protected boolean hasSpecial;
    protected boolean isDouble;
    protected final int len;
    public Locale locale;
    public int matchStat;
    protected int np;
    protected int pos;
    protected char[] sbuf;
    protected int sp;
    protected String stringDefaultValue;
    protected final String text;
    public TimeZone timeZone;
    protected int token;

    static {
        int i2;
        try {
            i2 = Class.forName("android.os.Build$VERSION").getField("SDK_INT").getInt(null);
        } catch (Exception unused) {
            i2 = -1;
        }
        char c2 = 0;
        V6 = i2 >= 23;
        sbufLocal = new ThreadLocal<>();
        digits = new int[103];
        for (int i3 = 48; i3 <= 57; i3++) {
            digits[i3] = i3 - 48;
        }
        for (int i4 = 97; i4 <= 102; i4++) {
            digits[i4] = (i4 - 97) + 10;
        }
        for (int i5 = 65; i5 <= 70; i5++) {
            digits[i5] = (i5 - 65) + 10;
        }
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        CA = charArray;
        int[] iArr = new int[256];
        IA = iArr;
        Arrays.fill(iArr, -1);
        int length = charArray.length;
        for (int i6 = 0; i6 < length; i6++) {
            IA[CA[i6]] = i6;
        }
        IA[61] = 0;
        firstIdentifierFlags = new boolean[256];
        char c3 = 0;
        while (true) {
            boolean[] zArr = firstIdentifierFlags;
            if (c3 >= zArr.length) {
                break;
            }
            if (c3 >= 'A' && c3 <= 'Z') {
                zArr[c3] = true;
            } else if (c3 >= 'a' && c3 <= 'z') {
                zArr[c3] = true;
            } else if (c3 == '_') {
                zArr[c3] = true;
            }
            c3 = (char) (c3 + 1);
        }
        identifierFlags = new boolean[256];
        while (true) {
            boolean[] zArr2 = identifierFlags;
            if (c2 >= zArr2.length) {
                return;
            }
            if (c2 >= 'A' && c2 <= 'Z') {
                zArr2[c2] = true;
            } else if (c2 >= 'a' && c2 <= 'z') {
                zArr2[c2] = true;
            } else if (c2 == '_') {
                zArr2[c2] = true;
            } else if (c2 >= '0' && c2 <= '9') {
                zArr2[c2] = true;
            }
            c2 = (char) (c2 + 1);
        }
    }

    public JSONLexer(String str) {
        this(str, JDJSON.DEFAULT_PARSER_FEATURE);
    }

    static boolean checkDate(char c2, char c3, char c4, char c5, char c6, char c7, int i2, int i3) {
        if (c2 >= '1' && c2 <= '3' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9' && c5 >= '0' && c5 <= '9') {
            if (c6 == '0') {
                if (c7 < '1' || c7 > '9') {
                    return false;
                }
            } else if (c6 != '1' || (c7 != '0' && c7 != '1' && c7 != '2')) {
                return false;
            }
            if (i2 == 48) {
                return i3 >= 49 && i3 <= 57;
            } else if (i2 != 49 && i2 != 50) {
                return i2 == 51 && (i3 == 48 || i3 == 49);
            } else if (i3 >= 48 && i3 <= 57) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x001d, code lost:
        if (r5 <= '4') goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static boolean checkTime(char r4, char r5, char r6, char r7, char r8, char r9) {
        /*
            r0 = 57
            r1 = 0
            r2 = 48
            if (r4 != r2) goto Lc
            if (r5 < r2) goto Lb
            if (r5 <= r0) goto L20
        Lb:
            return r1
        Lc:
            r3 = 49
            if (r4 != r3) goto L15
            if (r5 < r2) goto L14
            if (r5 <= r0) goto L20
        L14:
            return r1
        L15:
            r3 = 50
            if (r4 != r3) goto L42
            if (r5 < r2) goto L42
            r4 = 52
            if (r5 <= r4) goto L20
            goto L42
        L20:
            r4 = 53
            r5 = 54
            if (r6 < r2) goto L2d
            if (r6 > r4) goto L2d
            if (r7 < r2) goto L2c
            if (r7 <= r0) goto L32
        L2c:
            return r1
        L2d:
            if (r6 != r5) goto L42
            if (r7 == r2) goto L32
            return r1
        L32:
            if (r8 < r2) goto L3b
            if (r8 > r4) goto L3b
            if (r9 < r2) goto L3a
            if (r9 <= r0) goto L40
        L3a:
            return r1
        L3b:
            if (r8 != r5) goto L42
            if (r9 == r2) goto L40
            return r1
        L40:
            r4 = 1
            return r4
        L42:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.checkTime(char, char, char, char, char, char):boolean");
    }

    public static final byte[] decodeFast(String str, int i2, int i3) {
        int i4;
        int i5 = 0;
        if (i3 == 0) {
            return new byte[0];
        }
        int i6 = (i2 + i3) - 1;
        while (i2 < i6 && IA[str.charAt(i2)] < 0) {
            i2++;
        }
        while (i6 > 0 && IA[str.charAt(i6)] < 0) {
            i6--;
        }
        int i7 = str.charAt(i6) == '=' ? str.charAt(i6 + (-1)) == '=' ? 2 : 1 : 0;
        int i8 = (i6 - i2) + 1;
        if (i3 > 76) {
            i4 = (str.charAt(76) == '\r' ? i8 / 78 : 0) << 1;
        } else {
            i4 = 0;
        }
        int i9 = (((i8 - i4) * 6) >> 3) - i7;
        byte[] bArr = new byte[i9];
        int i10 = (i9 / 3) * 3;
        int i11 = 0;
        int i12 = 0;
        while (i11 < i10) {
            int[] iArr = IA;
            int i13 = i2 + 1;
            int i14 = i13 + 1;
            int i15 = (iArr[str.charAt(i2)] << 18) | (iArr[str.charAt(i13)] << 12);
            int i16 = i14 + 1;
            int i17 = i15 | (iArr[str.charAt(i14)] << 6);
            int i18 = i16 + 1;
            int i19 = i17 | iArr[str.charAt(i16)];
            int i20 = i11 + 1;
            bArr[i11] = (byte) (i19 >> 16);
            int i21 = i20 + 1;
            bArr[i20] = (byte) (i19 >> 8);
            int i22 = i21 + 1;
            bArr[i21] = (byte) i19;
            if (i4 <= 0 || (i12 = i12 + 1) != 19) {
                i2 = i18;
            } else {
                i2 = i18 + 2;
                i12 = 0;
            }
            i11 = i22;
        }
        if (i11 < i9) {
            int i23 = 0;
            while (i2 <= i6 - i7) {
                i5 |= IA[str.charAt(i2)] << (18 - (i23 * 6));
                i23++;
                i2++;
            }
            int i24 = 16;
            while (i11 < i9) {
                bArr[i11] = (byte) (i5 >> i24);
                i24 -= 8;
                i11++;
            }
        }
        return bArr;
    }

    private int matchFieldHash(long j2) {
        char c2 = this.ch;
        int i2 = 1;
        while (c2 != '\"' && c2 != '\'') {
            if (c2 != ' ' && c2 != '\n' && c2 != '\r' && c2 != '\t' && c2 != '\f' && c2 != '\b') {
                this.fieldHash = 0L;
                this.matchStat = -2;
                return 0;
            }
            int i3 = i2 + 1;
            int i4 = this.bp + i2;
            c2 = i4 >= this.len ? EOI : this.text.charAt(i4);
            i2 = i3;
        }
        long j3 = -3750763034362895579L;
        int i5 = this.bp + i2;
        while (true) {
            if (i5 >= this.len) {
                break;
            }
            char charAt = this.text.charAt(i5);
            if (charAt == c2) {
                i2 += (i5 - this.bp) - i2;
                break;
            }
            j3 = 1099511628211L * (charAt ^ j3);
            i5++;
        }
        if (j3 != j2) {
            this.fieldHash = j3;
            this.matchStat = -2;
            return 0;
        }
        int i6 = i2 + 1;
        int i7 = this.bp + i6;
        char charAt2 = i7 >= this.len ? EOI : this.text.charAt(i7);
        while (charAt2 != ':') {
            if (charAt2 <= ' ' && (charAt2 == ' ' || charAt2 == '\n' || charAt2 == '\r' || charAt2 == '\t' || charAt2 == '\f' || charAt2 == '\b')) {
                int i8 = i6 + 1;
                int i9 = this.bp + i6;
                charAt2 = i9 >= this.len ? EOI : this.text.charAt(i9);
                i6 = i8;
            } else {
                throw new JSONException("match feild error expect ':'");
            }
        }
        return i6 + 1;
    }

    private static String readString(char[] cArr, int i2) {
        int i3;
        char[] cArr2 = new char[i2];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            char c2 = cArr[i4];
            if (c2 != '\\') {
                cArr2[i5] = c2;
                i5++;
            } else {
                i4++;
                char c3 = cArr[i4];
                if (c3 == '\"') {
                    i3 = i5 + 1;
                    cArr2[i5] = Typography.quote;
                } else if (c3 != '\'') {
                    if (c3 != 'F') {
                        if (c3 == '\\') {
                            i3 = i5 + 1;
                            cArr2[i5] = '\\';
                        } else if (c3 == 'b') {
                            i3 = i5 + 1;
                            cArr2[i5] = '\b';
                        } else if (c3 != 'f') {
                            if (c3 == 'n') {
                                i3 = i5 + 1;
                                cArr2[i5] = '\n';
                            } else if (c3 == 'r') {
                                i3 = i5 + 1;
                                cArr2[i5] = '\r';
                            } else if (c3 != 'x') {
                                switch (c3) {
                                    case '/':
                                        i3 = i5 + 1;
                                        cArr2[i5] = '/';
                                        break;
                                    case '0':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 0;
                                        break;
                                    case '1':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 1;
                                        break;
                                    case '2':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 2;
                                        break;
                                    case '3':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 3;
                                        break;
                                    case '4':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 4;
                                        break;
                                    case '5':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 5;
                                        break;
                                    case '6':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 6;
                                        break;
                                    case '7':
                                        i3 = i5 + 1;
                                        cArr2[i5] = 7;
                                        break;
                                    default:
                                        switch (c3) {
                                            case 't':
                                                i3 = i5 + 1;
                                                cArr2[i5] = '\t';
                                                break;
                                            case 'u':
                                                i3 = i5 + 1;
                                                int i6 = i4 + 1;
                                                int i7 = i6 + 1;
                                                int i8 = i7 + 1;
                                                i4 = i8 + 1;
                                                cArr2[i5] = (char) Integer.parseInt(new String(new char[]{cArr[i6], cArr[i7], cArr[i8], cArr[i4]}), 16);
                                                break;
                                            case 'v':
                                                i3 = i5 + 1;
                                                cArr2[i5] = 11;
                                                break;
                                            default:
                                                throw new JSONException("unclosed.str.lit");
                                        }
                                }
                            } else {
                                i3 = i5 + 1;
                                int[] iArr = digits;
                                int i9 = i4 + 1;
                                i4 = i9 + 1;
                                cArr2[i5] = (char) ((iArr[cArr[i9]] * 16) + iArr[cArr[i4]]);
                            }
                        }
                    }
                    i3 = i5 + 1;
                    cArr2[i5] = '\f';
                } else {
                    i3 = i5 + 1;
                    cArr2[i5] = '\'';
                }
                i5 = i3;
            }
            i4++;
        }
        return new String(cArr2, 0, i5);
    }

    private void scanIdent() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            this.sp++;
            next();
        } while (Character.isLetterOrDigit(this.ch));
        String stringVal = stringVal();
        if (stringVal.equals(DYConstants.DY_NULL_STR)) {
            this.token = 8;
        } else if (stringVal.equals(DYConstants.DY_TRUE)) {
            this.token = 6;
        } else if (stringVal.equals(DYConstants.DY_FALSE)) {
            this.token = 7;
        } else if (stringVal.equals("new")) {
            this.token = 9;
        } else if (stringVal.equals("undefined")) {
            this.token = 23;
        } else if (stringVal.equals("Set")) {
            this.token = 21;
        } else if (stringVal.equals("TreeSet")) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    private void setCalendar(char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9) {
        Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar = calendar;
        calendar.set(1, ((c2 - '0') * 1000) + ((c3 - '0') * 100) + ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(2, (((c6 - '0') * 10) + (c7 - '0')) - 1);
        this.calendar.set(5, ((c8 - '0') * 10) + (c9 - '0'));
    }

    private final String subString(int i2, int i3) {
        char[] cArr = this.sbuf;
        if (i3 < cArr.length) {
            this.text.getChars(i2, i2 + i3, cArr, 0);
            return new String(this.sbuf, 0, i3);
        }
        char[] cArr2 = new char[i3];
        this.text.getChars(i2, i3 + i2, cArr2, 0);
        return new String(cArr2);
    }

    public byte[] bytesValue() {
        return decodeFast(this.text, this.np + 1, this.sp);
    }

    protected char charAt(int i2) {
        return i2 >= this.len ? EOI : this.text.charAt(i2);
    }

    public void close() {
        char[] cArr = this.sbuf;
        if (cArr.length <= 8196) {
            sbufLocal.set(cArr);
        }
        this.sbuf = null;
    }

    public final void config(Feature feature, boolean z) {
        if (z) {
            this.features |= feature.mask;
        } else {
            this.features &= feature.mask ^ (-1);
        }
        if (feature == Feature.InitStringFieldAsEmpty) {
            this.stringDefaultValue = z ? "" : null;
        }
        this.disableCircularReferenceDetect = (this.features & Feature.DisableCircularReferenceDetect.mask) != 0;
    }

    public final Number decimalValue(boolean z) {
        char[] cArr;
        boolean z2;
        int i2 = (this.np + this.sp) - 1;
        char charAt = i2 >= this.len ? EOI : this.text.charAt(i2);
        try {
            if (charAt == 'F') {
                return Float.valueOf(Float.parseFloat(numberString()));
            }
            if (charAt == 'D') {
                return Double.valueOf(Double.parseDouble(numberString()));
            }
            if (z) {
                return decimalValue();
            }
            char charAt2 = this.text.charAt((this.np + this.sp) - 1);
            int i3 = this.sp;
            if (charAt2 == 'L' || charAt2 == 'S' || charAt2 == 'B' || charAt2 == 'F' || charAt2 == 'D') {
                i3--;
            }
            int i4 = this.np;
            char[] cArr2 = this.sbuf;
            int i5 = 0;
            if (i3 < cArr2.length) {
                this.text.getChars(i4, i4 + i3, cArr2, 0);
                cArr = this.sbuf;
            } else {
                char[] cArr3 = new char[i3];
                this.text.getChars(i4, i4 + i3, cArr3, 0);
                cArr = cArr3;
            }
            if (i3 <= 9 && !this.exp) {
                char c2 = cArr[0];
                int i6 = 2;
                if (c2 == '-') {
                    c2 = cArr[1];
                    z2 = true;
                } else if (c2 == '+') {
                    c2 = cArr[1];
                    z2 = false;
                } else {
                    z2 = false;
                    i6 = 1;
                }
                int i7 = c2 - '0';
                while (i6 < i3) {
                    char c3 = cArr[i6];
                    if (c3 == '.') {
                        i5 = 1;
                    } else {
                        i7 = (i7 * 10) + (c3 - '0');
                        if (i5 != 0) {
                            i5 *= 10;
                        }
                    }
                    i6++;
                }
                double d = i7;
                double d2 = i5;
                Double.isNaN(d);
                Double.isNaN(d2);
                double d3 = d / d2;
                if (z2) {
                    d3 = -d3;
                }
                return Double.valueOf(d3);
            }
            return Double.valueOf(Double.parseDouble(new String(cArr, 0, i3)));
        } catch (NumberFormatException e2) {
            throw new JSONException(e2.getMessage() + ", " + info());
        }
    }

    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("pos ");
        sb.append(this.bp);
        sb.append(", json : ");
        sb.append(this.len < 65536 ? this.text : this.text.substring(0, 65536));
        return sb.toString();
    }

    public final int intValue() {
        int i2;
        boolean z;
        int i3 = this.np;
        int i4 = this.sp + i3;
        int i5 = 0;
        if ((i3 >= this.len ? EOI : this.text.charAt(i3)) == '-') {
            i2 = Integer.MIN_VALUE;
            i3++;
            z = true;
        } else {
            i2 = -2147483647;
            z = false;
        }
        if (i3 < i4) {
            i5 = -((i3 >= this.len ? EOI : this.text.charAt(i3)) - '0');
            i3++;
        }
        while (i3 < i4) {
            int i6 = i3 + 1;
            char charAt = i3 >= this.len ? EOI : this.text.charAt(i3);
            if (charAt == 'L' || charAt == 'S' || charAt == 'B') {
                i3 = i6;
                break;
            }
            int i7 = charAt - '0';
            if (i5 < -214748364) {
                throw new NumberFormatException(numberString());
            }
            int i8 = i5 * 10;
            if (i8 < i2 + i7) {
                throw new NumberFormatException(numberString());
            }
            i5 = i8 - i7;
            i3 = i6;
        }
        if (z) {
            if (i3 > this.np + 1) {
                return i5;
            }
            throw new NumberFormatException(numberString());
        }
        return -i5;
    }

    public final Number integerValue() throws NumberFormatException {
        char c2;
        long j2;
        boolean z;
        long j3;
        int i2 = this.np;
        int i3 = this.sp + i2;
        int i4 = i3 - 1;
        char charAt = i4 >= this.len ? EOI : this.text.charAt(i4);
        if (charAt == 'B') {
            i3--;
            c2 = 'B';
        } else if (charAt == 'L') {
            i3--;
            c2 = 'L';
        } else if (charAt != 'S') {
            c2 = ' ';
        } else {
            i3--;
            c2 = 'S';
        }
        int i5 = this.np;
        if ((i5 >= this.len ? EOI : this.text.charAt(i5)) == '-') {
            j2 = Long.MIN_VALUE;
            i2++;
            z = true;
        } else {
            j2 = -9223372036854775807L;
            z = false;
        }
        if (i2 < i3) {
            j3 = -((i2 >= this.len ? EOI : this.text.charAt(i2)) - '0');
            i2++;
        } else {
            j3 = 0;
        }
        while (i2 < i3) {
            int i6 = i2 + 1;
            int charAt2 = (i2 >= this.len ? EOI : this.text.charAt(i2)) - '0';
            if (j3 < -922337203685477580L) {
                return new BigInteger(numberString());
            }
            long j4 = j3 * 10;
            long j5 = charAt2;
            if (j4 < j2 + j5) {
                return new BigInteger(numberString());
            }
            j3 = j4 - j5;
            i2 = i6;
        }
        if (!z) {
            long j6 = -j3;
            if (j6 > 2147483647L || c2 == 'L') {
                return Long.valueOf(j6);
            }
            if (c2 == 'S') {
                return Short.valueOf((short) j6);
            }
            if (c2 == 'B') {
                return Byte.valueOf((byte) j6);
            }
            return Integer.valueOf((int) j6);
        } else if (i2 > this.np + 1) {
            if (j3 < -2147483648L || c2 == 'L') {
                return Long.valueOf(j3);
            }
            if (c2 == 'S') {
                return Short.valueOf((short) j3);
            }
            if (c2 == 'B') {
                return Byte.valueOf((byte) j3);
            }
            return Integer.valueOf((int) j3);
        } else {
            throw new NumberFormatException(numberString());
        }
    }

    public final boolean isBlankInput() {
        int i2 = 0;
        while (true) {
            char charAt = charAt(i2);
            boolean z = true;
            if (charAt == 26) {
                return true;
            }
            if (charAt > ' ' || (charAt != ' ' && charAt != '\n' && charAt != '\r' && charAt != '\t' && charAt != '\f' && charAt != '\b')) {
                z = false;
            }
            if (!z) {
                return false;
            }
            i2++;
        }
    }

    public final boolean isEnabled(Feature feature) {
        return (feature.mask & this.features) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0087  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x005e -> B:48:0x0026). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long longValue() throws java.lang.NumberFormatException {
        /*
            r13 = this;
            int r0 = r13.np
            int r1 = r13.sp
            int r1 = r1 + r0
            char r2 = r13.charAt(r0)
            r3 = 1
            r4 = 45
            if (r2 != r4) goto L14
            r4 = -9223372036854775808
            int r0 = r0 + 1
            r2 = 1
            goto L1a
        L14:
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r2 = 0
        L1a:
            if (r0 >= r1) goto L28
            int r6 = r0 + 1
            char r0 = r13.charAt(r0)
            int r0 = r0 + (-48)
            int r0 = -r0
            long r7 = (long) r0
        L26:
            r0 = r6
            goto L2a
        L28:
            r7 = 0
        L2a:
            if (r0 >= r1) goto L75
            int r6 = r0 + 1
            int r9 = r13.len
            if (r0 < r9) goto L35
            r0 = 26
            goto L3b
        L35:
            java.lang.String r9 = r13.text
            char r0 = r9.charAt(r0)
        L3b:
            r9 = 76
            if (r0 == r9) goto L74
            r9 = 83
            if (r0 == r9) goto L74
            r9 = 66
            if (r0 != r9) goto L48
            goto L74
        L48:
            int r0 = r0 + (-48)
            r9 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 < 0) goto L6a
            r9 = 10
            long r7 = r7 * r9
            long r9 = (long) r0
            long r11 = r4 + r9
            int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r0 < 0) goto L60
            long r7 = r7 - r9
            goto L26
        L60:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r13.numberString()
            r0.<init>(r1)
            throw r0
        L6a:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r13.numberString()
            r0.<init>(r1)
            throw r0
        L74:
            r0 = r6
        L75:
            if (r2 == 0) goto L87
            int r1 = r13.np
            int r1 = r1 + r3
            if (r0 <= r1) goto L7d
            return r7
        L7d:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r13.numberString()
            r0.<init>(r1)
            throw r0
        L87:
            long r0 = -r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.longValue():long");
    }

    public boolean matchField(long j2) {
        char c2 = this.ch;
        int i2 = this.bp + 1;
        int i3 = 1;
        while (c2 != '\"' && c2 != '\'') {
            if (c2 <= ' ' && (c2 == ' ' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == '\f' || c2 == '\b')) {
                int i4 = i3 + 1;
                int i5 = this.bp + i3;
                c2 = i5 >= this.len ? EOI : this.text.charAt(i5);
                i3 = i4;
            } else {
                this.fieldHash = 0L;
                this.matchStat = -2;
                return false;
            }
        }
        int i6 = i2;
        long j3 = -3750763034362895579L;
        while (true) {
            if (i6 >= this.len) {
                break;
            }
            char charAt = this.text.charAt(i6);
            if (charAt == c2) {
                i3 += (i6 - i2) + 1;
                break;
            }
            j3 = 1099511628211L * (charAt ^ j3);
            i6++;
        }
        if (j3 != j2) {
            this.matchStat = -2;
            this.fieldHash = j3;
            return false;
        }
        int i7 = i3 + 1;
        int i8 = this.bp + i3;
        char charAt2 = i8 >= this.len ? EOI : this.text.charAt(i8);
        while (charAt2 != ':') {
            if (charAt2 <= ' ' && (charAt2 == ' ' || charAt2 == '\n' || charAt2 == '\r' || charAt2 == '\t' || charAt2 == '\f' || charAt2 == '\b')) {
                int i9 = i7 + 1;
                int i10 = this.bp + i7;
                charAt2 = i10 >= this.len ? EOI : this.text.charAt(i10);
                i7 = i9;
            } else {
                throw new JSONException("match feild error expect ':'");
            }
        }
        int i11 = this.bp + i7;
        char charAt3 = i11 >= this.len ? EOI : this.text.charAt(i11);
        if (charAt3 == '{') {
            int i12 = i11 + 1;
            this.bp = i12;
            this.ch = i12 >= this.len ? EOI : this.text.charAt(i12);
            this.token = 12;
        } else if (charAt3 == '[') {
            int i13 = i11 + 1;
            this.bp = i13;
            this.ch = i13 >= this.len ? EOI : this.text.charAt(i13);
            this.token = 14;
        } else {
            this.bp = i11;
            this.ch = i11 >= this.len ? EOI : this.text.charAt(i11);
            nextToken();
        }
        return true;
    }

    public char next() {
        int i2 = this.bp + 1;
        this.bp = i2;
        char charAt = i2 >= this.len ? EOI : this.text.charAt(i2);
        this.ch = charAt;
        return charAt;
    }

    public final void nextIdent() {
        char c2;
        while (true) {
            c2 = this.ch;
            if (!(c2 <= ' ' && (c2 == ' ' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == '\f' || c2 == '\b'))) {
                break;
            }
            next();
        }
        if (c2 != '_' && !Character.isLetter(c2)) {
            nextToken();
        } else {
            scanIdent();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:206:0x0027, code lost:
        scanNumber();
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x002a, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x0105, code lost:
        scanIdent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x0108, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void nextToken() {
        /*
            Method dump skipped, instructions count: 506
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.nextToken():void");
    }

    public final void nextTokenWithChar(char c2) {
        this.sp = 0;
        while (true) {
            char c3 = this.ch;
            if (c3 == c2) {
                int i2 = this.bp + 1;
                this.bp = i2;
                this.ch = i2 >= this.len ? EOI : this.text.charAt(i2);
                nextToken();
                return;
            } else if (c3 != ' ' && c3 != '\n' && c3 != '\r' && c3 != '\t' && c3 != '\f' && c3 != '\b') {
                throw new JSONException("not match " + c2 + " - " + this.ch);
            } else {
                next();
            }
        }
    }

    public final String numberString() {
        char charAt = this.text.charAt((this.np + this.sp) - 1);
        int i2 = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i2--;
        }
        return subString(this.np, i2);
    }

    public boolean scanBoolean() {
        boolean z = false;
        int i2 = 1;
        if (this.text.startsWith(DYConstants.DY_FALSE, this.bp)) {
            i2 = 5;
        } else if (this.text.startsWith(DYConstants.DY_TRUE, this.bp)) {
            z = true;
            i2 = 4;
        } else {
            char c2 = this.ch;
            if (c2 == '1') {
                z = true;
            } else if (c2 != '0') {
                this.matchStat = -1;
                return false;
            }
        }
        int i3 = this.bp + i2;
        this.bp = i3;
        this.ch = charAt(i3);
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:131:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean scanFieldBoolean(long r13) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldBoolean(long):boolean");
    }

    public Date scanFieldDate(long j2) {
        int i2;
        char charAt;
        char c2;
        int i3;
        Date date;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        if (matchFieldHash == 0) {
            return null;
        }
        int i4 = this.bp;
        int i5 = matchFieldHash + 1;
        int i6 = matchFieldHash + i4;
        int i7 = this.len;
        char c3 = EOI;
        char charAt2 = i6 >= i7 ? EOI : this.text.charAt(i6);
        if (charAt2 == '\"') {
            int i8 = this.bp;
            int i9 = i8 + i5;
            int i10 = i5 + 1;
            int i11 = i8 + i5;
            if (i11 < this.len) {
                this.text.charAt(i11);
            }
            int indexOf = this.text.indexOf(34, this.bp + i10);
            if (indexOf != -1) {
                int i12 = indexOf - i9;
                this.bp = i9;
                if (scanISO8601DateIfMatch(false, i12)) {
                    date = this.calendar.getTime();
                    int i13 = i10 + i12;
                    i3 = i13 + 1;
                    c2 = charAt(i13 + i4);
                    this.bp = i4;
                } else {
                    this.bp = i4;
                    this.matchStat = -1;
                    return null;
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return null;
        } else {
            long j3 = charAt2 - '0';
            while (true) {
                i2 = i5 + 1;
                int i14 = this.bp + i5;
                charAt = i14 >= this.len ? EOI : this.text.charAt(i14);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                j3 = (j3 * 10) + ((long) (charAt - '0'));
                i5 = i2;
            }
            if (charAt == '.') {
                this.matchStat = -1;
                return null;
            }
            if (charAt == '\"') {
                int i15 = i2 + 1;
                int i16 = this.bp + i2;
                c2 = i16 >= this.len ? EOI : this.text.charAt(i16);
                i3 = i15;
            } else {
                c2 = charAt;
                i3 = i2;
            }
            if (j3 < 0) {
                this.matchStat = -1;
                return null;
            }
            date = new Date(j3);
        }
        if (c2 == ',') {
            int i17 = this.bp + (i3 - 1);
            this.bp = i17;
            int i18 = i17 + 1;
            this.bp = i18;
            if (i18 < this.len) {
                c3 = this.text.charAt(i18);
            }
            this.ch = c3;
            this.matchStat = 3;
            this.token = 16;
            return date;
        } else if (c2 == '}') {
            int i19 = i3 + 1;
            char charAt3 = charAt(this.bp + i3);
            if (charAt3 == ',') {
                this.token = 16;
                int i20 = this.bp + (i19 - 1);
                this.bp = i20;
                int i21 = i20 + 1;
                this.bp = i21;
                if (i21 < this.len) {
                    c3 = this.text.charAt(i21);
                }
                this.ch = c3;
            } else if (charAt3 == ']') {
                this.token = 15;
                int i22 = this.bp + (i19 - 1);
                this.bp = i22;
                int i23 = i22 + 1;
                this.bp = i23;
                if (i23 < this.len) {
                    c3 = this.text.charAt(i23);
                }
                this.ch = c3;
            } else if (charAt3 == '}') {
                this.token = 13;
                int i24 = this.bp + (i19 - 1);
                this.bp = i24;
                int i25 = i24 + 1;
                this.bp = i25;
                if (i25 < this.len) {
                    c3 = this.text.charAt(i25);
                }
                this.ch = c3;
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i19 - 1;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:146:0x00bc A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:144:0x00b9 -> B:145:0x00ba). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double scanFieldDouble(long r19) {
        /*
            Method dump skipped, instructions count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldDouble(long):double");
    }

    /* JADX WARN: Code restructure failed: missing block: B:215:0x00d3, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x00d5, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:241:0x011b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0128  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:239:0x0116 -> B:240:0x0119). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double[] scanFieldDoubleArray(long r20) {
        /*
            Method dump skipped, instructions count: 549
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldDoubleArray(long):double[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:247:0x00e5, code lost:
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x00e7, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0296, code lost:
        r12 = r3;
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x0299, code lost:
        return r12;
     */
    /* JADX WARN: Removed duplicated region for block: B:274:0x012d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x013c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:272:0x0129 -> B:273:0x012b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double[][] scanFieldDoubleArray2(long r21) {
        /*
            Method dump skipped, instructions count: 669
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldDoubleArray2(long):double[][]");
    }

    /* JADX WARN: Removed duplicated region for block: B:142:0x00b4 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:140:0x00b1 -> B:141:0x00b2). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float scanFieldFloat(long r18) {
        /*
            Method dump skipped, instructions count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldFloat(long):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:215:0x00d3, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x00d5, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x021c, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x021e, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:241:0x011b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0128  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:239:0x0116 -> B:240:0x0119). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[] scanFieldFloatArray(long r20) {
        /*
            Method dump skipped, instructions count: 543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldFloatArray(long):float[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:247:0x00e5, code lost:
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x00e7, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0290, code lost:
        r12 = r3;
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x0293, code lost:
        return r12;
     */
    /* JADX WARN: Removed duplicated region for block: B:274:0x012d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x013c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:272:0x0129 -> B:273:0x012b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[][] scanFieldFloatArray2(long r21) {
        /*
            Method dump skipped, instructions count: 663
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldFloatArray2(long):float[][]");
    }

    public int scanFieldInt(long j2) {
        int i2;
        char charAt;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        if (matchFieldHash == 0) {
            return 0;
        }
        int i3 = matchFieldHash + 1;
        int i4 = this.bp + matchFieldHash;
        int i5 = this.len;
        char c2 = EOI;
        char charAt2 = i4 >= i5 ? EOI : this.text.charAt(i4);
        boolean z = charAt2 == '\"';
        if (z) {
            int i6 = i3 + 1;
            int i7 = this.bp + i3;
            charAt2 = i7 >= this.len ? EOI : this.text.charAt(i7);
            i3 = i6;
            z = true;
        }
        boolean z2 = charAt2 == '-';
        if (z2) {
            int i8 = i3 + 1;
            int i9 = this.bp + i3;
            charAt2 = i9 >= this.len ? EOI : this.text.charAt(i9);
            i3 = i8;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i10 = charAt2 - '0';
        while (true) {
            i2 = i3 + 1;
            int i11 = this.bp + i3;
            charAt = i11 >= this.len ? EOI : this.text.charAt(i11);
            if (charAt < '0' || charAt > '9') {
                break;
            }
            i10 = (i10 * 10) + (charAt - '0');
            i3 = i2;
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (charAt == '\"') {
            if (!z) {
                this.matchStat = -1;
                return 0;
            }
            int i12 = i2 + 1;
            int i13 = this.bp + i2;
            i2 = i12;
            charAt = i13 >= this.len ? EOI : this.text.charAt(i13);
        }
        if (i10 < 0) {
            this.matchStat = -1;
            return 0;
        }
        while (charAt != ',') {
            if (charAt > ' ' || !(charAt == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t' || charAt == '\f' || charAt == '\b')) {
                if (charAt == '}') {
                    int i14 = i2 + 1;
                    char charAt3 = charAt(this.bp + i2);
                    if (charAt3 == ',') {
                        this.token = 16;
                        int i15 = this.bp + (i14 - 1);
                        this.bp = i15;
                        int i16 = i15 + 1;
                        this.bp = i16;
                        if (i16 < this.len) {
                            c2 = this.text.charAt(i16);
                        }
                        this.ch = c2;
                    } else if (charAt3 == ']') {
                        this.token = 15;
                        int i17 = this.bp + (i14 - 1);
                        this.bp = i17;
                        int i18 = i17 + 1;
                        this.bp = i18;
                        if (i18 < this.len) {
                            c2 = this.text.charAt(i18);
                        }
                        this.ch = c2;
                    } else if (charAt3 == '}') {
                        this.token = 13;
                        int i19 = this.bp + (i14 - 1);
                        this.bp = i19;
                        int i20 = i19 + 1;
                        this.bp = i20;
                        if (i20 < this.len) {
                            c2 = this.text.charAt(i20);
                        }
                        this.ch = c2;
                    } else if (charAt3 == 26) {
                        this.token = 20;
                        this.bp += i14 - 1;
                        this.ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return 0;
                    }
                    this.matchStat = 4;
                    return z2 ? -i10 : i10;
                }
                this.matchStat = -1;
                return 0;
            }
            int i21 = i2 + 1;
            int i22 = this.bp + i2;
            i2 = i21;
            charAt = i22 >= this.len ? EOI : this.text.charAt(i22);
        }
        int i23 = this.bp + (i2 - 1);
        this.bp = i23;
        int i24 = i23 + 1;
        this.bp = i24;
        if (i24 < this.len) {
            c2 = this.text.charAt(i24);
        }
        this.ch = c2;
        this.matchStat = 3;
        this.token = 16;
        return z2 ? -i10 : i10;
    }

    public final int[] scanFieldIntArray(long j2) {
        boolean z;
        int[] iArr;
        int i2;
        int i3;
        char charAt;
        int i4;
        int i5;
        char charAt2;
        int[] iArr2;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        int[] iArr3 = null;
        if (matchFieldHash == 0) {
            return null;
        }
        int i6 = matchFieldHash + 1;
        int i7 = this.bp + matchFieldHash;
        if ((i7 >= this.len ? EOI : this.text.charAt(i7)) != '[') {
            this.matchStat = -1;
            return null;
        }
        int i8 = i6 + 1;
        int i9 = this.bp + i6;
        char charAt3 = i9 >= this.len ? EOI : this.text.charAt(i9);
        int[] iArr4 = new int[16];
        if (charAt3 == ']') {
            i5 = i8 + 1;
            int i10 = this.bp + i8;
            charAt2 = i10 >= this.len ? EOI : this.text.charAt(i10);
            i4 = 0;
        } else {
            int i11 = 0;
            while (true) {
                if (charAt3 == '-') {
                    int i12 = i8 + 1;
                    int i13 = this.bp + i8;
                    charAt3 = i13 >= this.len ? EOI : this.text.charAt(i13);
                    i8 = i12;
                    z = true;
                } else {
                    z = false;
                }
                if (charAt3 < '0') {
                    iArr = iArr3;
                    i2 = -1;
                    break;
                } else if (charAt3 > '9') {
                    i2 = -1;
                    iArr = null;
                    break;
                } else {
                    int i14 = charAt3 - '0';
                    while (true) {
                        i3 = i8 + 1;
                        int i15 = this.bp + i8;
                        charAt = i15 >= this.len ? EOI : this.text.charAt(i15);
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i14 = (i14 * 10) + (charAt - '0');
                        i8 = i3;
                    }
                    if (i11 >= iArr4.length) {
                        int[] iArr5 = new int[(iArr4.length * 3) / 2];
                        System.arraycopy(iArr4, 0, iArr5, 0, i11);
                        iArr4 = iArr5;
                    }
                    i4 = i11 + 1;
                    if (z) {
                        i14 = -i14;
                    }
                    iArr4[i11] = i14;
                    if (charAt == ',') {
                        int i16 = i3 + 1;
                        int i17 = this.bp + i3;
                        i3 = i16;
                        iArr2 = null;
                        charAt = i17 >= this.len ? EOI : this.text.charAt(i17);
                    } else if (charAt == ']') {
                        i5 = i3 + 1;
                        int i18 = this.bp + i3;
                        charAt2 = i18 >= this.len ? EOI : this.text.charAt(i18);
                    } else {
                        iArr2 = null;
                    }
                    i11 = i4;
                    charAt3 = charAt;
                    iArr3 = iArr2;
                    i8 = i3;
                }
            }
            this.matchStat = i2;
            return iArr;
        }
        if (i4 != iArr4.length) {
            int[] iArr6 = new int[i4];
            System.arraycopy(iArr4, 0, iArr6, 0, i4);
            iArr4 = iArr6;
        }
        if (charAt2 == ',') {
            this.bp += i5 - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return iArr4;
        } else if (charAt2 == '}') {
            int i19 = i5 + 1;
            char charAt4 = charAt(this.bp + i5);
            if (charAt4 == ',') {
                this.token = 16;
                this.bp += i19 - 1;
                next();
            } else if (charAt4 == ']') {
                this.token = 15;
                this.bp += i19 - 1;
                next();
            } else if (charAt4 == '}') {
                this.token = 13;
                this.bp += i19 - 1;
                next();
            } else if (charAt4 == 26) {
                this.bp += i19 - 1;
                this.token = 20;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return iArr4;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public long scanFieldLong(long j2) {
        int i2;
        char charAt;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        if (matchFieldHash == 0) {
            return 0L;
        }
        int i3 = matchFieldHash + 1;
        int i4 = this.bp + matchFieldHash;
        char charAt2 = i4 >= this.len ? EOI : this.text.charAt(i4);
        boolean z = charAt2 == '\"';
        if (z) {
            int i5 = i3 + 1;
            int i6 = this.bp + i3;
            charAt2 = i6 >= this.len ? EOI : this.text.charAt(i6);
            i3 = i5;
        }
        boolean z2 = charAt2 == '-';
        if (z2) {
            int i7 = i3 + 1;
            int i8 = this.bp + i3;
            charAt2 = i8 >= this.len ? EOI : this.text.charAt(i8);
            i3 = i7;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return 0L;
        }
        long j3 = charAt2 - '0';
        while (true) {
            i2 = i3 + 1;
            int i9 = this.bp + i3;
            charAt = i9 >= this.len ? EOI : this.text.charAt(i9);
            if (charAt < '0' || charAt > '9') {
                break;
            }
            j3 = (j3 * 10) + ((long) (charAt - '0'));
            i3 = i2;
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0L;
        }
        if (charAt == '\"') {
            if (!z) {
                this.matchStat = -1;
                return 0L;
            }
            int i10 = i2 + 1;
            int i11 = this.bp + i2;
            charAt = i11 >= this.len ? EOI : this.text.charAt(i11);
            i2 = i10;
        }
        if (j3 < 0) {
            this.matchStat = -1;
            return 0L;
        } else if (charAt == ',') {
            int i12 = this.bp + (i2 - 1);
            this.bp = i12;
            int i13 = i12 + 1;
            this.bp = i13;
            this.ch = i13 >= this.len ? EOI : this.text.charAt(i13);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -j3 : j3;
        } else if (charAt == '}') {
            int i14 = i2 + 1;
            char charAt3 = charAt(this.bp + i2);
            if (charAt3 == ',') {
                this.token = 16;
                int i15 = this.bp + (i14 - 1);
                this.bp = i15;
                int i16 = i15 + 1;
                this.bp = i16;
                this.ch = i16 >= this.len ? EOI : this.text.charAt(i16);
            } else if (charAt3 == ']') {
                this.token = 15;
                int i17 = this.bp + (i14 - 1);
                this.bp = i17;
                int i18 = i17 + 1;
                this.bp = i18;
                this.ch = i18 >= this.len ? EOI : this.text.charAt(i18);
            } else if (charAt3 == '}') {
                this.token = 13;
                int i19 = this.bp + (i14 - 1);
                this.bp = i19;
                int i20 = i19 + 1;
                this.bp = i20;
                this.ch = i20 >= this.len ? EOI : this.text.charAt(i20);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i14 - 1;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return 0L;
            }
            this.matchStat = 4;
            return z2 ? -j3 : j3;
        } else {
            this.matchStat = -1;
            return 0L;
        }
    }

    public String scanFieldString(long j2) {
        String str;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        if (matchFieldHash == 0) {
            return null;
        }
        int i2 = matchFieldHash + 1;
        int i3 = this.bp + matchFieldHash;
        if (i3 < this.len) {
            if (this.text.charAt(i3) != '\"') {
                this.matchStat = -1;
                return this.stringDefaultValue;
            }
            int i4 = this.bp + i2;
            int indexOf = this.text.indexOf(34, i4);
            if (indexOf != -1) {
                if (V6) {
                    str = this.text.substring(i4, indexOf);
                } else {
                    int i5 = indexOf - i4;
                    str = new String(sub_chars(this.bp + i2, i5), 0, i5);
                }
                if (str.indexOf(92) != -1) {
                    boolean z = false;
                    while (true) {
                        int i6 = indexOf - 1;
                        int i7 = 0;
                        while (i6 >= 0 && this.text.charAt(i6) == '\\') {
                            i7++;
                            i6--;
                            z = true;
                        }
                        if (i7 % 2 == 0) {
                            break;
                        }
                        indexOf = this.text.indexOf(34, indexOf + 1);
                    }
                    int i8 = indexOf - i4;
                    char[] sub_chars = sub_chars(this.bp + i2, i8);
                    if (z) {
                        str = readString(sub_chars, i8);
                    } else {
                        str = new String(sub_chars, 0, i8);
                        if (str.indexOf(92) != -1) {
                            str = readString(sub_chars, i8);
                        }
                    }
                }
                int i9 = indexOf + 1;
                int i10 = this.len;
                char c2 = EOI;
                char charAt = i9 >= i10 ? EOI : this.text.charAt(i9);
                if (charAt == ',') {
                    this.bp = i9;
                    int i11 = i9 + 1;
                    this.bp = i11;
                    if (i11 < this.len) {
                        c2 = this.text.charAt(i11);
                    }
                    this.ch = c2;
                    this.matchStat = 3;
                    this.token = 16;
                    return str;
                } else if (charAt == '}') {
                    int i12 = i9 + 1;
                    char charAt2 = i12 >= this.len ? EOI : this.text.charAt(i12);
                    if (charAt2 == ',') {
                        this.token = 16;
                        this.bp = i12;
                        next();
                    } else if (charAt2 == ']') {
                        this.token = 15;
                        this.bp = i12;
                        next();
                    } else if (charAt2 == '}') {
                        this.token = 13;
                        this.bp = i12;
                        next();
                    } else if (charAt2 == 26) {
                        this.token = 20;
                        this.bp = i12;
                        this.ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return this.stringDefaultValue;
                    }
                    this.matchStat = 4;
                    return str;
                } else {
                    this.matchStat = -1;
                    return this.stringDefaultValue;
                }
            }
            throw new JSONException("unclosed str, " + info());
        }
        throw new JSONException("unclosed str, " + info());
    }

    public long scanFieldSymbol(long j2) {
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        if (matchFieldHash == 0) {
            return 0L;
        }
        int i2 = matchFieldHash + 1;
        int i3 = this.bp + matchFieldHash;
        int i4 = this.len;
        char c2 = EOI;
        if ((i3 >= i4 ? EOI : this.text.charAt(i3)) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j3 = -3750763034362895579L;
        while (true) {
            int i5 = i2 + 1;
            int i6 = this.bp + i2;
            char charAt = i6 >= this.len ? EOI : this.text.charAt(i6);
            if (charAt == '\"') {
                int i7 = i5 + 1;
                int i8 = this.bp + i5;
                char charAt2 = i8 >= this.len ? EOI : this.text.charAt(i8);
                if (charAt2 == ',') {
                    int i9 = this.bp + (i7 - 1);
                    this.bp = i9;
                    int i10 = i9 + 1;
                    this.bp = i10;
                    if (i10 < this.len) {
                        c2 = this.text.charAt(i10);
                    }
                    this.ch = c2;
                    this.matchStat = 3;
                    return j3;
                } else if (charAt2 == '}') {
                    int i11 = i7 + 1;
                    int i12 = this.bp + i7;
                    char charAt3 = i12 >= this.len ? EOI : this.text.charAt(i12);
                    if (charAt3 == ',') {
                        this.token = 16;
                        this.bp += i11 - 1;
                        next();
                    } else if (charAt3 == ']') {
                        this.token = 15;
                        this.bp += i11 - 1;
                        next();
                    } else if (charAt3 == '}') {
                        this.token = 13;
                        this.bp += i11 - 1;
                        next();
                    } else if (charAt3 == 26) {
                        this.token = 20;
                        this.bp += i11 - 1;
                        this.ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                    this.matchStat = 4;
                    return j3;
                } else {
                    this.matchStat = -1;
                    return 0L;
                }
            }
            j3 = (j3 ^ charAt) * 1099511628211L;
            if (charAt == '\\') {
                this.matchStat = -1;
                return 0L;
            }
            i2 = i5;
        }
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.bp);
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00c4, code lost:
        if (r0 != false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00c7, code lost:
        return -r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:?, code lost:
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long scanLongValue() {
        /*
            r13 = this;
            r0 = 0
            r13.np = r0
            char r1 = r13.ch
            r2 = 1
            r3 = 45
            if (r1 != r3) goto L40
            r0 = -9223372036854775808
            r3 = 0
            int r3 = r3 + r2
            r13.np = r3
            int r3 = r13.bp
            int r3 = r3 + r2
            r13.bp = r3
            int r4 = r13.len
            if (r3 >= r4) goto L24
            java.lang.String r4 = r13.text
            char r3 = r4.charAt(r3)
            r13.ch = r3
            r3 = r0
            r0 = 1
            goto L45
        L24:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "syntax error, "
            r1.append(r2)
            java.lang.String r2 = r13.info()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L40:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L45:
            r5 = 0
        L47:
            char r1 = r13.ch
            r7 = 48
            if (r1 < r7) goto Lc4
            r7 = 57
            if (r1 > r7) goto Lc4
            int r1 = r1 + (-48)
            r7 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            java.lang.String r9 = ", "
            java.lang.String r10 = "error long value, "
            int r11 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r11 < 0) goto La5
            r7 = 10
            long r5 = r5 * r7
            long r7 = (long) r1
            long r11 = r3 + r7
            int r1 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r1 < 0) goto L86
            long r5 = r5 - r7
            int r1 = r13.np
            int r1 = r1 + r2
            r13.np = r1
            int r1 = r13.bp
            int r1 = r1 + r2
            r13.bp = r1
            int r7 = r13.len
            if (r1 < r7) goto L7d
            r1 = 26
            goto L83
        L7d:
            java.lang.String r7 = r13.text
            char r1 = r7.charAt(r1)
        L83:
            r13.ch = r1
            goto L47
        L86:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            r1.append(r5)
            r1.append(r9)
            java.lang.String r2 = r13.info()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        La5:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            r1.append(r5)
            r1.append(r9)
            java.lang.String r2 = r13.info()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        Lc4:
            if (r0 != 0) goto Lc7
            long r5 = -r5
        Lc7:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanLongValue():long");
    }

    public final void scanNumber() {
        char c2;
        char c3;
        int i2 = this.bp;
        this.np = i2;
        this.exp = false;
        if (this.ch == '-') {
            this.sp++;
            int i3 = i2 + 1;
            this.bp = i3;
            this.ch = i3 >= this.len ? EOI : this.text.charAt(i3);
        }
        while (true) {
            c2 = this.ch;
            if (c2 < '0' || c2 > '9') {
                break;
            }
            this.sp++;
            int i4 = this.bp + 1;
            this.bp = i4;
            this.ch = i4 >= this.len ? EOI : this.text.charAt(i4);
        }
        this.isDouble = false;
        if (c2 == '.') {
            this.sp++;
            int i5 = this.bp + 1;
            this.bp = i5;
            this.ch = i5 >= this.len ? EOI : this.text.charAt(i5);
            this.isDouble = true;
            while (true) {
                char c4 = this.ch;
                if (c4 < '0' || c4 > '9') {
                    break;
                }
                this.sp++;
                int i6 = this.bp + 1;
                this.bp = i6;
                this.ch = i6 >= this.len ? EOI : this.text.charAt(i6);
            }
        }
        char c5 = this.ch;
        if (c5 == 'L') {
            this.sp++;
            next();
        } else if (c5 == 'S') {
            this.sp++;
            next();
        } else if (c5 == 'B') {
            this.sp++;
            next();
        } else if (c5 == 'F') {
            this.sp++;
            next();
            this.isDouble = true;
        } else if (c5 == 'D') {
            this.sp++;
            next();
            this.isDouble = true;
        } else if (c5 == 'e' || c5 == 'E') {
            this.sp++;
            int i7 = this.bp + 1;
            this.bp = i7;
            char charAt = i7 >= this.len ? EOI : this.text.charAt(i7);
            this.ch = charAt;
            if (charAt == '+' || charAt == '-') {
                this.sp++;
                int i8 = this.bp + 1;
                this.bp = i8;
                this.ch = i8 >= this.len ? EOI : this.text.charAt(i8);
            }
            while (true) {
                c3 = this.ch;
                if (c3 < '0' || c3 > '9') {
                    break;
                }
                this.sp++;
                int i9 = this.bp + 1;
                this.bp = i9;
                this.ch = i9 >= this.len ? EOI : this.text.charAt(i9);
            }
            if (c3 == 'D' || c3 == 'F') {
                this.sp++;
                next();
            }
            this.exp = true;
            this.isDouble = true;
        }
        if (this.isDouble) {
            this.token = 3;
        } else {
            this.token = 2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:221:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x020d A[Catch: NumberFormatException -> 0x025b, TryCatch #0 {NumberFormatException -> 0x025b, blocks: (B:295:0x01fd, B:301:0x0209, B:303:0x020d, B:309:0x021e, B:306:0x0215, B:308:0x021c, B:312:0x0225, B:315:0x022b, B:320:0x023c, B:300:0x0206, B:322:0x0241, B:324:0x024b, B:326:0x0251), top: B:333:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0225 A[Catch: NumberFormatException -> 0x025b, TryCatch #0 {NumberFormatException -> 0x025b, blocks: (B:295:0x01fd, B:301:0x0209, B:303:0x020d, B:309:0x021e, B:306:0x0215, B:308:0x021c, B:312:0x0225, B:315:0x022b, B:320:0x023c, B:300:0x0206, B:322:0x0241, B:324:0x024b, B:326:0x0251), top: B:333:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x024b A[Catch: NumberFormatException -> 0x025b, TryCatch #0 {NumberFormatException -> 0x025b, blocks: (B:295:0x01fd, B:301:0x0209, B:303:0x020d, B:309:0x021e, B:306:0x0215, B:308:0x021c, B:312:0x0225, B:315:0x022b, B:320:0x023c, B:300:0x0206, B:322:0x0241, B:324:0x024b, B:326:0x0251), top: B:333:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0251 A[Catch: NumberFormatException -> 0x025b, TRY_LEAVE, TryCatch #0 {NumberFormatException -> 0x025b, blocks: (B:295:0x01fd, B:301:0x0209, B:303:0x020d, B:309:0x021e, B:306:0x0215, B:308:0x021c, B:312:0x0225, B:315:0x022b, B:320:0x023c, B:300:0x0206, B:322:0x0241, B:324:0x024b, B:326:0x0251), top: B:333:0x01f9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Number scanNumberValue() {
        /*
            Method dump skipped, instructions count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanNumberValue():java.lang.Number");
    }

    public final void scanString() {
        char c2 = this.ch;
        int i2 = this.bp + 1;
        int indexOf = this.text.indexOf(c2, i2);
        if (indexOf != -1) {
            int i3 = indexOf - i2;
            char[] sub_chars = sub_chars(this.bp + 1, i3);
            boolean z = false;
            while (i3 > 0 && sub_chars[i3 - 1] == '\\') {
                int i4 = 1;
                for (int i5 = i3 - 2; i5 >= 0 && sub_chars[i5] == '\\'; i5--) {
                    i4++;
                }
                if (i4 % 2 == 0) {
                    break;
                }
                int indexOf2 = this.text.indexOf(c2, indexOf + 1);
                int i6 = (indexOf2 - indexOf) + i3;
                if (i6 >= sub_chars.length) {
                    int length = (sub_chars.length * 3) / 2;
                    if (length < i6) {
                        length = i6;
                    }
                    char[] cArr = new char[length];
                    System.arraycopy(sub_chars, 0, cArr, 0, sub_chars.length);
                    sub_chars = cArr;
                }
                this.text.getChars(indexOf, indexOf2, sub_chars, i3);
                indexOf = indexOf2;
                i3 = i6;
                z = true;
            }
            if (!z) {
                for (int i7 = 0; i7 < i3; i7++) {
                    if (sub_chars[i7] == '\\') {
                        z = true;
                    }
                }
            }
            this.sbuf = sub_chars;
            this.sp = i3;
            this.np = this.bp;
            this.hasSpecial = z;
            int i8 = indexOf + 1;
            this.bp = i8;
            this.ch = i8 >= this.len ? EOI : this.text.charAt(i8);
            this.token = 4;
            return;
        }
        throw new JSONException("unclosed str, " + info());
    }

    public String scanStringValue(char c2) {
        String str;
        int i2 = this.bp + 1;
        int indexOf = this.text.indexOf(c2, i2);
        if (indexOf != -1) {
            if (V6) {
                str = this.text.substring(i2, indexOf);
            } else {
                int i3 = indexOf - i2;
                str = new String(sub_chars(this.bp + 1, i3), 0, i3);
            }
            if (str.indexOf(92) != -1) {
                while (true) {
                    int i4 = 0;
                    for (int i5 = indexOf - 1; i5 >= 0 && this.text.charAt(i5) == '\\'; i5--) {
                        i4++;
                    }
                    if (i4 % 2 == 0) {
                        break;
                    }
                    indexOf = this.text.indexOf(c2, indexOf + 1);
                }
                int i6 = indexOf - i2;
                str = readString(sub_chars(this.bp + 1, i6), i6);
            }
            int i7 = indexOf + 1;
            this.bp = i7;
            this.ch = i7 >= this.len ? EOI : this.text.charAt(i7);
            return str;
        }
        throw new JSONException("unclosed str, " + info());
    }

    public final String scanSymbol(SymbolTable symbolTable) {
        char c2;
        while (true) {
            c2 = this.ch;
            if (c2 != ' ' && c2 != '\n' && c2 != '\r' && c2 != '\t' && c2 != '\f' && c2 != '\b') {
                break;
            }
            next();
        }
        if (c2 == '\"') {
            return scanSymbol(symbolTable, Typography.quote);
        }
        if (c2 == '\'') {
            return scanSymbol(symbolTable, '\'');
        }
        if (c2 == '}') {
            next();
            this.token = 13;
            return null;
        } else if (c2 == ',') {
            next();
            this.token = 16;
            return null;
        } else if (c2 == 26) {
            this.token = 20;
            return null;
        } else {
            return scanSymbolUnQuoted(symbolTable);
        }
    }

    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        int i2 = this.ch;
        boolean[] zArr = firstIdentifierFlags;
        if (i2 >= zArr.length || zArr[i2]) {
            this.np = this.bp;
            this.sp = 1;
            while (true) {
                char next = next();
                boolean[] zArr2 = identifierFlags;
                if (next < zArr2.length && !zArr2[next]) {
                    break;
                }
                i2 = (i2 * 31) + next;
                this.sp++;
            }
            this.ch = charAt(this.bp);
            this.token = 18;
            if (this.sp == 4 && this.text.startsWith(DYConstants.DY_NULL_STR, this.np)) {
                return null;
            }
            return symbolTable.addSymbol(this.text, this.np, this.sp, i2);
        }
        throw new JSONException("illegal identifier : " + this.ch + ", " + info());
    }

    protected void setTime(char c2, char c3, char c4, char c5, char c6, char c7) {
        this.calendar.set(11, ((c2 - '0') * 10) + (c3 - '0'));
        this.calendar.set(12, ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(13, ((c6 - '0') * 10) + (c7 - '0'));
    }

    protected void setTimeZone(char c2, char c3, char c4) {
        int i2 = (((c3 - '0') * 10) + (c4 - '0')) * R2.color.c_f2f3f3 * 1000;
        if (c2 == '-') {
            i2 = -i2;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i2) {
            String[] availableIDs = TimeZone.getAvailableIDs(i2);
            if (availableIDs.length > 0) {
                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
            }
        }
    }

    protected void skipComment() {
        next();
        char c2 = this.ch;
        if (c2 != '/') {
            if (c2 == '*') {
                next();
                while (true) {
                    char c3 = this.ch;
                    if (c3 == 26) {
                        return;
                    }
                    if (c3 == '*') {
                        next();
                        if (this.ch == '/') {
                            next();
                            return;
                        }
                    } else {
                        next();
                    }
                }
            } else {
                throw new JSONException("invalid comment");
            }
        }
        do {
            next();
        } while (this.ch != '\n');
        next();
    }

    public final void skipWhitespace() {
        while (true) {
            char c2 = this.ch;
            if (c2 > '/') {
                return;
            }
            if (c2 == ' ' || c2 == '\r' || c2 == '\n' || c2 == '\t' || c2 == '\f' || c2 == '\b') {
                next();
            } else if (c2 != '/') {
                return;
            } else {
                skipComment();
            }
        }
    }

    public final String stringVal() {
        if (this.hasSpecial) {
            return readString(this.sbuf, this.sp);
        }
        return subString(this.np + 1, this.sp);
    }

    final char[] sub_chars(int i2, int i3) {
        char[] cArr = this.sbuf;
        if (i3 < cArr.length) {
            this.text.getChars(i2, i3 + i2, cArr, 0);
            return this.sbuf;
        }
        char[] cArr2 = new char[i3];
        this.sbuf = cArr2;
        this.text.getChars(i2, i3 + i2, cArr2, 0);
        return cArr2;
    }

    public final int token() {
        return this.token;
    }

    public JSONLexer(char[] cArr, int i2) {
        this(cArr, i2, JDJSON.DEFAULT_PARSER_FEATURE);
    }

    /* JADX WARN: Removed duplicated region for block: B:437:0x01f9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:439:0x01fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean scanISO8601DateIfMatch(boolean r36, int r37) {
        /*
            Method dump skipped, instructions count: 1607
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanISO8601DateIfMatch(boolean, int):boolean");
    }

    public JSONLexer(char[] cArr, int i2, int i3) {
        this(new String(cArr, 0, i2), i3);
    }

    public JSONLexer(String str, int i2) {
        this.features = JDJSON.DEFAULT_PARSER_FEATURE;
        this.exp = false;
        this.isDouble = false;
        this.timeZone = JDJSON.defaultTimeZone;
        this.locale = JDJSON.defaultLocale;
        this.calendar = null;
        this.matchStat = 0;
        char[] cArr = sbufLocal.get();
        this.sbuf = cArr;
        if (cArr == null) {
            this.sbuf = new char[512];
        }
        this.features = i2;
        this.text = str;
        int length = str.length();
        this.len = length;
        this.bp = -1;
        int i3 = (-1) + 1;
        this.bp = i3;
        char charAt = i3 >= length ? EOI : str.charAt(i3);
        this.ch = charAt;
        if (charAt == '\ufeff') {
            next();
        }
        this.stringDefaultValue = (Feature.InitStringFieldAsEmpty.mask & i2) != 0 ? "" : null;
        this.disableCircularReferenceDetect = (Feature.DisableCircularReferenceDetect.mask & i2) != 0;
    }

    public String scanSymbol(SymbolTable symbolTable, char c2) {
        String readString;
        int i2 = this.bp + 1;
        int indexOf = this.text.indexOf(c2, i2);
        if (indexOf != -1) {
            int i3 = indexOf - i2;
            char[] sub_chars = sub_chars(this.bp + 1, i3);
            boolean z = false;
            while (i3 > 0 && sub_chars[i3 - 1] == '\\') {
                int i4 = 1;
                for (int i5 = i3 - 2; i5 >= 0 && sub_chars[i5] == '\\'; i5--) {
                    i4++;
                }
                if (i4 % 2 == 0) {
                    break;
                }
                int indexOf2 = this.text.indexOf(c2, indexOf + 1);
                int i6 = (indexOf2 - indexOf) + i3;
                if (i6 >= sub_chars.length) {
                    int length = (sub_chars.length * 3) / 2;
                    if (length < i6) {
                        length = i6;
                    }
                    char[] cArr = new char[length];
                    System.arraycopy(sub_chars, 0, cArr, 0, sub_chars.length);
                    sub_chars = cArr;
                }
                this.text.getChars(indexOf, indexOf2, sub_chars, i3);
                indexOf = indexOf2;
                i3 = i6;
                z = true;
            }
            if (z) {
                readString = readString(sub_chars, i3);
            } else {
                int i7 = 0;
                for (int i8 = 0; i8 < i3; i8++) {
                    char c3 = sub_chars[i8];
                    i7 = (i7 * 31) + c3;
                    if (c3 == '\\') {
                        z = true;
                    }
                }
                if (z) {
                    readString = readString(sub_chars, i3);
                } else {
                    readString = i3 < 20 ? symbolTable.addSymbol(sub_chars, 0, i3, i7) : new String(sub_chars, 0, i3);
                }
            }
            int i9 = indexOf + 1;
            this.bp = i9;
            this.ch = i9 >= this.len ? EOI : this.text.charAt(i9);
            return readString;
        }
        throw new JSONException("unclosed str, " + info());
    }

    public final BigDecimal decimalValue() {
        char charAt = this.text.charAt((this.np + this.sp) - 1);
        int i2 = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i2--;
        }
        int i3 = this.np;
        char[] cArr = this.sbuf;
        if (i2 < cArr.length) {
            this.text.getChars(i3, i3 + i2, cArr, 0);
            return new BigDecimal(this.sbuf, 0, i2);
        }
        char[] cArr2 = new char[i2];
        this.text.getChars(i3, i2 + i3, cArr2, 0);
        return new BigDecimal(cArr2);
    }

    public final void nextToken(int i2) {
        this.sp = 0;
        while (true) {
            if (i2 != 2) {
                char c2 = EOI;
                if (i2 == 4) {
                    char c3 = this.ch;
                    if (c3 == '\"') {
                        this.pos = this.bp;
                        scanString();
                        return;
                    } else if (c3 >= '0' && c3 <= '9') {
                        this.pos = this.bp;
                        scanNumber();
                        return;
                    } else if (c3 == '{') {
                        this.token = 12;
                        int i3 = this.bp + 1;
                        this.bp = i3;
                        if (i3 < this.len) {
                            c2 = this.text.charAt(i3);
                        }
                        this.ch = c2;
                        return;
                    }
                } else if (i2 == 12) {
                    char c4 = this.ch;
                    if (c4 == '{') {
                        this.token = 12;
                        int i4 = this.bp + 1;
                        this.bp = i4;
                        if (i4 < this.len) {
                            c2 = this.text.charAt(i4);
                        }
                        this.ch = c2;
                        return;
                    } else if (c4 == '[') {
                        this.token = 14;
                        int i5 = this.bp + 1;
                        this.bp = i5;
                        if (i5 < this.len) {
                            c2 = this.text.charAt(i5);
                        }
                        this.ch = c2;
                        return;
                    }
                } else if (i2 != 18) {
                    if (i2 != 20) {
                        switch (i2) {
                            case 14:
                                char c5 = this.ch;
                                if (c5 == '[') {
                                    this.token = 14;
                                    next();
                                    return;
                                } else if (c5 == '{') {
                                    this.token = 12;
                                    next();
                                    return;
                                }
                                break;
                            case 15:
                                if (this.ch == ']') {
                                    this.token = 15;
                                    next();
                                    return;
                                }
                                break;
                            case 16:
                                char c6 = this.ch;
                                if (c6 == ',') {
                                    this.token = 16;
                                    int i6 = this.bp + 1;
                                    this.bp = i6;
                                    if (i6 < this.len) {
                                        c2 = this.text.charAt(i6);
                                    }
                                    this.ch = c2;
                                    return;
                                } else if (c6 == '}') {
                                    this.token = 13;
                                    int i7 = this.bp + 1;
                                    this.bp = i7;
                                    if (i7 < this.len) {
                                        c2 = this.text.charAt(i7);
                                    }
                                    this.ch = c2;
                                    return;
                                } else if (c6 == ']') {
                                    this.token = 15;
                                    int i8 = this.bp + 1;
                                    this.bp = i8;
                                    if (i8 < this.len) {
                                        c2 = this.text.charAt(i8);
                                    }
                                    this.ch = c2;
                                    return;
                                } else if (c6 == 26) {
                                    this.token = 20;
                                    return;
                                }
                                break;
                        }
                    }
                    if (this.ch == 26) {
                        this.token = 20;
                        return;
                    }
                } else {
                    nextIdent();
                    return;
                }
            } else {
                char c7 = this.ch;
                if (c7 >= '0' && c7 <= '9') {
                    this.pos = this.bp;
                    scanNumber();
                    return;
                } else if (c7 == '\"') {
                    this.pos = this.bp;
                    scanString();
                    return;
                } else if (c7 == '[') {
                    this.token = 14;
                    next();
                    return;
                } else if (c7 == '{') {
                    this.token = 12;
                    next();
                    return;
                }
            }
            char c8 = this.ch;
            if (c8 != ' ' && c8 != '\n' && c8 != '\r' && c8 != '\t' && c8 != '\f' && c8 != '\b') {
                nextToken();
                return;
            }
            next();
        }
    }
}
