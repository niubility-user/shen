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

    /* JADX WARN: Code restructure failed: missing block: B:94:0x001d, code lost:
        if (r5 <= '4') goto L96;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static boolean checkTime(char c2, char c3, char c4, char c5, char c6, char c7) {
        if (c2 == '0') {
            if (c3 < '0' || c3 > '9') {
                return false;
            }
        } else if (c2 != '1') {
            if (c2 == '2') {
                if (c3 >= '0') {
                }
            }
            return false;
        } else if (c3 < '0' || c3 > '9') {
            return false;
        }
        if (c4 < '0' || c4 > '5') {
            if (c4 != '6' || c5 != '0') {
                return false;
            }
        } else if (c5 < '0' || c5 > '9') {
            return false;
        }
        return (c6 < '0' || c6 > '5') ? c6 == '6' && c7 == '0' : c7 >= '0' && c7 <= '9';
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

    /* JADX WARN: Removed duplicated region for block: B:113:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x002c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x005e -> B:88:0x0026). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final long longValue() throws NumberFormatException {
        long j2;
        boolean z;
        long j3;
        int i2 = this.np;
        int i3 = this.sp + i2;
        if (charAt(i2) == '-') {
            j2 = Long.MIN_VALUE;
            i2++;
            z = true;
        } else {
            j2 = -9223372036854775807L;
            z = false;
        }
        if (i2 < i3) {
            int i4 = i2 + 1;
            j3 = -(charAt(i2) - '0');
            i2 = i4;
            if (i2 < i3) {
                i4 = i2 + 1;
                char charAt = i2 >= this.len ? EOI : this.text.charAt(i2);
                if (charAt == 'L' || charAt == 'S' || charAt == 'B') {
                    i2 = i4;
                } else {
                    int i5 = charAt - '0';
                    if (j3 < -922337203685477580L) {
                        throw new NumberFormatException(numberString());
                    }
                    long j4 = j3 * 10;
                    long j5 = i5;
                    if (j4 < j2 + j5) {
                        throw new NumberFormatException(numberString());
                    }
                    j3 = j4 - j5;
                    i2 = i4;
                    if (i2 < i3) {
                    }
                }
            }
            if (z) {
                return -j3;
            }
            if (i2 > this.np + 1) {
                return j3;
            }
            throw new NumberFormatException(numberString());
        }
        j3 = 0;
        if (i2 < i3) {
        }
        if (z) {
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:396:0x0027, code lost:
        scanNumber();
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x002a, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:477:0x0105, code lost:
        scanIdent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:478:0x0108, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void nextToken() {
        int i2;
        this.sp = 0;
        while (true) {
            int i3 = this.bp;
            this.pos = i3;
            char c2 = this.ch;
            if (c2 == '/') {
                skipComment();
            } else if (c2 == '\"') {
                scanString();
                return;
            } else if ((c2 < '0' || c2 > '9') && c2 != '-') {
                if (c2 == ',') {
                    next();
                    this.token = 16;
                    return;
                }
                if (c2 != '\f' && c2 != '\r' && c2 != ' ') {
                    if (c2 == ':') {
                        next();
                        this.token = 17;
                        return;
                    }
                    char c3 = EOI;
                    if (c2 == '[') {
                        int i4 = i3 + 1;
                        this.bp = i4;
                        if (i4 < this.len) {
                            c3 = this.text.charAt(i4);
                        }
                        this.ch = c3;
                        this.token = 14;
                        return;
                    } else if (c2 == ']') {
                        next();
                        this.token = 15;
                        return;
                    } else if (c2 == 'f') {
                        if (this.text.startsWith(DYConstants.DY_FALSE, i3)) {
                            int i5 = this.bp + 5;
                            this.bp = i5;
                            char charAt = charAt(i5);
                            this.ch = charAt;
                            if (charAt == ' ' || charAt == ',' || charAt == '}' || charAt == ']' || charAt == '\n' || charAt == '\r' || charAt == '\t' || charAt == 26 || charAt == '\f' || charAt == '\b' || charAt == ':') {
                                this.token = 7;
                                return;
                            }
                        }
                        throw new JSONException("scan false error");
                    } else if (c2 == 'n') {
                        if (this.text.startsWith(DYConstants.DY_NULL_STR, i3)) {
                            this.bp += 4;
                            i2 = 8;
                        } else if (this.text.startsWith("new", this.bp)) {
                            this.bp += 3;
                            i2 = 9;
                        } else {
                            i2 = 0;
                        }
                        if (i2 != 0) {
                            char charAt2 = charAt(this.bp);
                            this.ch = charAt2;
                            if (charAt2 == ' ' || charAt2 == ',' || charAt2 == '}' || charAt2 == ']' || charAt2 == '\n' || charAt2 == '\r' || charAt2 == '\t' || charAt2 == 26 || charAt2 == '\f' || charAt2 == '\b') {
                                this.token = i2;
                                return;
                            }
                        }
                        throw new JSONException("scan null/new error");
                    } else if (c2 == '{') {
                        int i6 = i3 + 1;
                        this.bp = i6;
                        if (i6 < this.len) {
                            c3 = this.text.charAt(i6);
                        }
                        this.ch = c3;
                        this.token = 12;
                        return;
                    } else if (c2 == '}') {
                        int i7 = i3 + 1;
                        this.bp = i7;
                        if (i7 < this.len) {
                            c3 = this.text.charAt(i7);
                        }
                        this.ch = c3;
                        this.token = 13;
                        return;
                    } else if (c2 != 'S' && c2 != 'T') {
                        if (c2 == 't') {
                            if (this.text.startsWith(DYConstants.DY_TRUE, i3)) {
                                int i8 = this.bp + 4;
                                this.bp = i8;
                                char charAt3 = charAt(i8);
                                this.ch = charAt3;
                                if (charAt3 == ' ' || charAt3 == ',' || charAt3 == '}' || charAt3 == ']' || charAt3 == '\n' || charAt3 == '\r' || charAt3 == '\t' || charAt3 == 26 || charAt3 == '\f' || charAt3 == '\b' || charAt3 == ':') {
                                    this.token = 6;
                                    return;
                                }
                            }
                            throw new JSONException("scan true error");
                        } else if (c2 != 'u') {
                            switch (c2) {
                                case '\b':
                                case '\t':
                                case '\n':
                                    break;
                                default:
                                    switch (c2) {
                                        case '\'':
                                            scanString();
                                            return;
                                        case '(':
                                            next();
                                            this.token = 10;
                                            return;
                                        case ')':
                                            next();
                                            this.token = 11;
                                            return;
                                        default:
                                            int i9 = this.len;
                                            if (i3 == i9 || (c2 == 26 && i3 + 1 == i9)) {
                                                if (this.token != 20) {
                                                    this.token = 20;
                                                    int i10 = this.eofPos;
                                                    this.bp = i10;
                                                    this.pos = i10;
                                                    return;
                                                }
                                                throw new JSONException("EOF error");
                                            } else if (c2 > 31 && c2 != '\u007f') {
                                                this.token = 1;
                                                next();
                                                return;
                                            } else {
                                                next();
                                                break;
                                            }
                                            break;
                                    }
                            }
                        }
                    }
                }
                next();
            }
        }
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

    /* JADX WARN: Removed duplicated region for block: B:229:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean scanFieldBoolean(long j2) {
        int i2;
        boolean z;
        char charAt;
        int i3;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        if (matchFieldHash == 0) {
            return false;
        }
        if (!this.text.startsWith(DYConstants.DY_FALSE, this.bp + matchFieldHash)) {
            if (this.text.startsWith(DYConstants.DY_TRUE, this.bp + matchFieldHash)) {
                i2 = matchFieldHash + 4;
            } else if (this.text.startsWith("\"false\"", this.bp + matchFieldHash)) {
                i2 = matchFieldHash + 7;
            } else if (this.text.startsWith("\"true\"", this.bp + matchFieldHash)) {
                i2 = matchFieldHash + 6;
            } else if (this.text.charAt(this.bp + matchFieldHash) == '1') {
                i2 = matchFieldHash + 1;
            } else if (this.text.charAt(this.bp + matchFieldHash) == '0') {
                i2 = matchFieldHash + 1;
            } else if (this.text.startsWith("\"1\"", this.bp + matchFieldHash)) {
                i2 = matchFieldHash + 3;
            } else if (!this.text.startsWith("\"0\"", this.bp + matchFieldHash)) {
                this.matchStat = -1;
                return false;
            } else {
                i2 = matchFieldHash + 3;
            }
            z = true;
            int i4 = i2 + 1;
            int i5 = this.bp + i2;
            int i6 = this.len;
            char c2 = EOI;
            charAt = i5 < i6 ? EOI : this.text.charAt(i5);
            while (charAt != ',') {
                if (charAt == '}' || !(charAt == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t' || charAt == '\f' || charAt == '\b')) {
                    if (charAt == '}') {
                        int i7 = i4 + 1;
                        char charAt2 = charAt(this.bp + i4);
                        if (charAt2 == ',') {
                            this.token = 16;
                            int i8 = this.bp + (i7 - 1);
                            this.bp = i8;
                            int i9 = i8 + 1;
                            this.bp = i9;
                            if (i9 < this.len) {
                                c2 = this.text.charAt(i9);
                            }
                            this.ch = c2;
                        } else if (charAt2 == ']') {
                            this.token = 15;
                            int i10 = this.bp + (i7 - 1);
                            this.bp = i10;
                            int i11 = i10 + 1;
                            this.bp = i11;
                            if (i11 < this.len) {
                                c2 = this.text.charAt(i11);
                            }
                            this.ch = c2;
                        } else if (charAt2 == '}') {
                            this.token = 13;
                            int i12 = this.bp + (i7 - 1);
                            this.bp = i12;
                            int i13 = i12 + 1;
                            this.bp = i13;
                            if (i13 < this.len) {
                                c2 = this.text.charAt(i13);
                            }
                            this.ch = c2;
                        } else if (charAt2 == 26) {
                            this.token = 20;
                            this.bp += i7 - 1;
                            this.ch = EOI;
                        } else {
                            this.matchStat = -1;
                            return false;
                        }
                        this.matchStat = 4;
                        return z;
                    }
                    this.matchStat = -1;
                    return false;
                }
                int i14 = i4 + 1;
                int i15 = this.bp + i4;
                charAt = i15 >= this.len ? EOI : this.text.charAt(i15);
                i4 = i14;
            }
            int i16 = this.bp + (i4 - 1);
            this.bp = i16;
            i3 = i16 + 1;
            this.bp = i3;
            if (i3 < this.len) {
                c2 = this.text.charAt(i3);
            }
            this.ch = c2;
            this.matchStat = 3;
            this.token = 16;
            return z;
        }
        i2 = matchFieldHash + 5;
        z = false;
        int i42 = i2 + 1;
        int i52 = this.bp + i2;
        int i62 = this.len;
        char c22 = EOI;
        if (i52 < i62) {
        }
        while (charAt != ',') {
        }
        int i162 = this.bp + (i42 - 1);
        this.bp = i162;
        i3 = i162 + 1;
        this.bp = i3;
        if (i3 < this.len) {
        }
        this.ch = c22;
        this.matchStat = 3;
        this.token = 16;
        return z;
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

    /* JADX WARN: Removed duplicated region for block: B:238:0x00bc A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:236:0x00b9 -> B:237:0x00ba). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final double scanFieldDouble(long j2) {
        int i2;
        char charAt;
        int i3;
        double parseDouble;
        int i4;
        char charAt2;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        if (matchFieldHash == 0) {
            return 0.0d;
        }
        int i5 = matchFieldHash + 1;
        char charAt3 = charAt(this.bp + matchFieldHash);
        int i6 = this.bp;
        int i7 = (i6 + i5) - 1;
        boolean z = charAt3 == '-';
        if (z) {
            i5++;
            charAt3 = charAt(i6 + i5);
        }
        if (charAt3 >= '0' && charAt3 <= '9') {
            int i8 = charAt3 - '0';
            while (true) {
                i2 = i5 + 1;
                charAt = charAt(this.bp + i5);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i8 = (i8 * 10) + (charAt - '0');
                i5 = i2;
            }
            if (charAt == '.') {
                int i9 = i2 + 1;
                char charAt4 = charAt(this.bp + i2);
                if (charAt4 < '0' || charAt4 > '9') {
                    this.matchStat = -1;
                    return 0.0d;
                }
                i8 = (i8 * 10) + (charAt4 - '0');
                int i10 = 10;
                while (true) {
                    i4 = i9 + 1;
                    charAt2 = charAt(this.bp + i9);
                    if (charAt2 < '0' || charAt2 > '9') {
                        break;
                    }
                    i8 = (i8 * 10) + (charAt2 - '0');
                    i10 *= 10;
                    i9 = i4;
                }
                i2 = i4;
                i3 = i10;
                charAt = charAt2;
            } else {
                i3 = 1;
            }
            boolean z2 = charAt == 'e' || charAt == 'E';
            if (z2) {
                int i11 = i2 + 1;
                charAt = charAt(this.bp + i2);
                if (charAt == '+' || charAt == '-') {
                    int i12 = i11 + 1;
                    charAt = charAt(this.bp + i11);
                    i2 = i12;
                    if (charAt >= '0' && charAt <= '9') {
                        i12 = i2 + 1;
                        charAt = charAt(this.bp + i2);
                        i2 = i12;
                        if (charAt >= '0') {
                            i12 = i2 + 1;
                            charAt = charAt(this.bp + i2);
                            i2 = i12;
                            if (charAt >= '0') {
                            }
                        }
                    }
                } else {
                    i2 = i11;
                    if (charAt >= '0') {
                    }
                }
            }
            int i13 = ((this.bp + i2) - i7) - 1;
            if (!z2 && i13 < 10) {
                double d = i8;
                double d2 = i3;
                Double.isNaN(d);
                Double.isNaN(d2);
                parseDouble = d / d2;
                if (z) {
                    parseDouble = -parseDouble;
                }
            } else {
                parseDouble = Double.parseDouble(subString(i7, i13));
            }
            if (charAt == ',') {
                this.bp += i2 - 1;
                next();
                this.matchStat = 3;
                this.token = 16;
                return parseDouble;
            } else if (charAt == '}') {
                int i14 = i2 + 1;
                char charAt5 = charAt(this.bp + i2);
                if (charAt5 == ',') {
                    this.token = 16;
                    this.bp += i14 - 1;
                    next();
                } else if (charAt5 == ']') {
                    this.token = 15;
                    this.bp += i14 - 1;
                    next();
                } else if (charAt5 == '}') {
                    this.token = 13;
                    this.bp += i14 - 1;
                    next();
                } else if (charAt5 == 26) {
                    this.bp += i14 - 1;
                    this.token = 20;
                    this.ch = EOI;
                } else {
                    this.matchStat = -1;
                    return 0.0d;
                }
                this.matchStat = 4;
                return parseDouble;
            } else {
                this.matchStat = -1;
                return 0.0d;
            }
        }
        this.matchStat = -1;
        return 0.0d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:370:0x00d3, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:371:0x00d5, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:396:0x011b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0128  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:394:0x0116 -> B:395:0x0119). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final double[] scanFieldDoubleArray(long j2) {
        int i2;
        char charAt;
        int i3;
        double parseDouble;
        char charAt2;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        double[] dArr = null;
        if (matchFieldHash == 0) {
            return null;
        }
        int i4 = matchFieldHash + 1;
        int i5 = this.bp + matchFieldHash;
        if ((i5 >= this.len ? EOI : this.text.charAt(i5)) != '[') {
            this.matchStat = -1;
            return null;
        }
        int i6 = i4 + 1;
        int i7 = this.bp + i4;
        char charAt3 = i7 >= this.len ? EOI : this.text.charAt(i7);
        double[] dArr2 = new double[16];
        int i8 = 0;
        while (true) {
            int i9 = this.bp;
            int i10 = (i9 + i6) - 1;
            boolean z = charAt3 == '-';
            if (z) {
                int i11 = i9 + i6;
                i6++;
                charAt3 = i11 >= this.len ? EOI : this.text.charAt(i11);
            }
            if (charAt3 < '0' || charAt3 > '9') {
                break;
            }
            int i12 = charAt3 - '0';
            while (true) {
                i2 = i6 + 1;
                int i13 = this.bp + i6;
                charAt = i13 >= this.len ? EOI : this.text.charAt(i13);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i12 = (i12 * 10) + (charAt - '0');
                i6 = i2;
            }
            if (charAt == '.') {
                int i14 = i2 + 1;
                int i15 = this.bp + i2;
                char charAt4 = i15 >= this.len ? EOI : this.text.charAt(i15);
                if (charAt4 >= '0' && charAt4 <= '9') {
                    i12 = (i12 * 10) + (charAt4 - '0');
                    i3 = 10;
                    while (true) {
                        i2 = i14 + 1;
                        int i16 = this.bp + i14;
                        charAt = i16 >= this.len ? EOI : this.text.charAt(i16);
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i12 = (i12 * 10) + (charAt - '0');
                        i3 *= 10;
                        i14 = i2;
                    }
                } else {
                    break;
                }
            } else {
                i3 = 1;
            }
            boolean z2 = charAt == 'e' || charAt == 'E';
            if (z2) {
                int i17 = i2 + 1;
                int i18 = this.bp + i2;
                char charAt5 = i18 >= this.len ? EOI : this.text.charAt(i18);
                if (charAt5 == '+' || charAt5 == '-') {
                    int i19 = i17 + 1;
                    int i20 = this.bp + i17;
                    if (i20 < this.len) {
                        charAt2 = this.text.charAt(i20);
                        i2 = i19;
                        charAt = charAt2;
                        if (charAt >= '0' && charAt <= '9') {
                            i19 = i2 + 1;
                            int i21 = this.bp + i2;
                            if (i21 < this.len) {
                                charAt2 = this.text.charAt(i21);
                                i2 = i19;
                                charAt = charAt2;
                                if (charAt >= '0') {
                                    i19 = i2 + 1;
                                    int i212 = this.bp + i2;
                                    if (i212 < this.len) {
                                    }
                                }
                            }
                        }
                    }
                    charAt2 = EOI;
                    i2 = i19;
                    charAt = charAt2;
                    if (charAt >= '0') {
                    }
                } else {
                    charAt = charAt5;
                    i2 = i17;
                    if (charAt >= '0') {
                    }
                }
            }
            int i22 = ((this.bp + i2) - i10) - 1;
            if (!z2 && i22 < 10) {
                double d = i12;
                double d2 = i3;
                Double.isNaN(d);
                Double.isNaN(d2);
                parseDouble = d / d2;
                if (z) {
                    parseDouble = -parseDouble;
                }
            } else {
                parseDouble = Double.parseDouble(subString(i10, i22));
            }
            if (i8 >= dArr2.length) {
                double[] dArr3 = new double[(dArr2.length * 3) / 2];
                System.arraycopy(dArr2, 0, dArr3, 0, i8);
                dArr2 = dArr3;
            }
            int i23 = i8 + 1;
            dArr2[i8] = parseDouble;
            if (charAt == ',') {
                int i24 = i2 + 1;
                int i25 = this.bp + i2;
                charAt = i25 >= this.len ? EOI : this.text.charAt(i25);
                i2 = i24;
            } else if (charAt == ']') {
                int i26 = i2 + 1;
                int i27 = this.bp + i2;
                char charAt6 = i27 >= this.len ? EOI : this.text.charAt(i27);
                if (i23 != dArr2.length) {
                    double[] dArr4 = new double[i23];
                    System.arraycopy(dArr2, 0, dArr4, 0, i23);
                    dArr2 = dArr4;
                }
                if (charAt6 == ',') {
                    this.bp += i26 - 1;
                    next();
                    this.matchStat = 3;
                    this.token = 16;
                    return dArr2;
                } else if (charAt6 == '}') {
                    int i28 = i26 + 1;
                    int i29 = this.bp + i26;
                    char charAt7 = i29 >= this.len ? EOI : this.text.charAt(i29);
                    if (charAt7 == ',') {
                        this.token = 16;
                        this.bp += i28 - 1;
                        next();
                    } else if (charAt7 == ']') {
                        this.token = 15;
                        this.bp += i28 - 1;
                        next();
                    } else if (charAt7 == '}') {
                        this.token = 13;
                        this.bp += i28 - 1;
                        next();
                    } else if (charAt7 == 26) {
                        this.bp += i28 - 1;
                        this.token = 20;
                        this.ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                    this.matchStat = 4;
                    return dArr2;
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            dArr = null;
            charAt3 = charAt;
            i8 = i23;
            i6 = i2;
        }
        this.matchStat = -1;
        return dArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:432:0x00e5, code lost:
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x00e7, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:535:0x0296, code lost:
        r12 = r3;
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:536:0x0299, code lost:
        return r12;
     */
    /* JADX WARN: Removed duplicated region for block: B:459:0x012d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x013c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:457:0x0129 -> B:458:0x012b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final double[][] scanFieldDoubleArray2(long j2) {
        int i2;
        char charAt;
        int i3;
        double parseDouble;
        int i4;
        double[][] dArr;
        double[][] dArr2;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        double[][] dArr3 = null;
        if (matchFieldHash == 0) {
            return null;
        }
        int i5 = matchFieldHash + 1;
        int i6 = this.bp + matchFieldHash;
        char charAt2 = i6 >= this.len ? EOI : this.text.charAt(i6);
        char c2 = '[';
        if (charAt2 != '[') {
            this.matchStat = -1;
            return null;
        }
        int i7 = i5 + 1;
        int i8 = this.bp + i5;
        char charAt3 = i8 >= this.len ? EOI : this.text.charAt(i8);
        int i9 = 16;
        double[][] dArr4 = new double[16];
        int i10 = 0;
        loop0: while (true) {
            if (charAt3 == c2) {
                int i11 = i7 + 1;
                int i12 = this.bp + i7;
                char charAt4 = i12 >= this.len ? EOI : this.text.charAt(i12);
                double[] dArr5 = new double[i9];
                int i13 = 0;
                while (true) {
                    int i14 = this.bp;
                    int i15 = (i14 + i11) - 1;
                    boolean z = charAt4 == '-';
                    if (z) {
                        int i16 = i14 + i11;
                        i11++;
                        charAt4 = i16 >= this.len ? EOI : this.text.charAt(i16);
                    }
                    if (charAt4 < '0' || charAt4 > '9') {
                        break loop0;
                    }
                    int i17 = charAt4 - '0';
                    while (true) {
                        i2 = i11 + 1;
                        int i18 = this.bp + i11;
                        charAt = i18 >= this.len ? EOI : this.text.charAt(i18);
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i17 = (i17 * 10) + (charAt - '0');
                        i11 = i2;
                    }
                    if (charAt == '.') {
                        int i19 = i2 + 1;
                        int i20 = this.bp + i2;
                        char charAt5 = i20 >= this.len ? EOI : this.text.charAt(i20);
                        if (charAt5 >= '0' && charAt5 <= '9') {
                            i17 = (i17 * 10) + (charAt5 - '0');
                            i3 = 10;
                            while (true) {
                                i2 = i19 + 1;
                                int i21 = this.bp + i19;
                                charAt = i21 >= this.len ? EOI : this.text.charAt(i21);
                                if (charAt < '0' || charAt > '9') {
                                    break;
                                }
                                i17 = (i17 * 10) + (charAt - '0');
                                i3 *= 10;
                                i19 = i2;
                            }
                        } else {
                            break loop0;
                        }
                    } else {
                        i3 = 1;
                    }
                    boolean z2 = charAt == 'e' || charAt == 'E';
                    if (z2) {
                        int i22 = i2 + 1;
                        int i23 = this.bp + i2;
                        char charAt6 = i23 >= this.len ? EOI : this.text.charAt(i23);
                        if (charAt6 == '+' || charAt6 == '-') {
                            int i24 = i22 + 1;
                            int i25 = this.bp + i22;
                            char charAt7 = i25 >= this.len ? EOI : this.text.charAt(i25);
                            charAt = charAt7;
                            i2 = i24;
                            if (charAt >= '0' && charAt <= '9') {
                                i24 = i2 + 1;
                                int i26 = this.bp + i2;
                                if (i26 < this.len) {
                                    charAt = EOI;
                                    i2 = i24;
                                    if (charAt >= '0') {
                                        i24 = i2 + 1;
                                        int i262 = this.bp + i2;
                                        if (i262 < this.len) {
                                            charAt7 = this.text.charAt(i262);
                                            charAt = charAt7;
                                            i2 = i24;
                                            if (charAt >= '0') {
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            charAt = charAt6;
                            i2 = i22;
                            if (charAt >= '0') {
                            }
                        }
                    }
                    int i27 = ((this.bp + i2) - i15) - 1;
                    if (!z2 && i27 < 10) {
                        double d = i17;
                        double d2 = i3;
                        Double.isNaN(d);
                        Double.isNaN(d2);
                        parseDouble = d / d2;
                        if (z) {
                            parseDouble = -parseDouble;
                        }
                    } else {
                        parseDouble = Double.parseDouble(subString(i15, i27));
                    }
                    if (i13 >= dArr5.length) {
                        double[] dArr6 = new double[(dArr5.length * 3) / 2];
                        System.arraycopy(dArr5, 0, dArr6, 0, i13);
                        dArr5 = dArr6;
                    }
                    int i28 = i13 + 1;
                    dArr5[i13] = parseDouble;
                    if (charAt == ',') {
                        int i29 = i2 + 1;
                        int i30 = this.bp + i2;
                        charAt4 = i30 >= this.len ? EOI : this.text.charAt(i30);
                        i2 = i29;
                        dArr2 = null;
                    } else if (charAt == ']') {
                        int i31 = i2 + 1;
                        int i32 = this.bp + i2;
                        char charAt8 = i32 >= this.len ? EOI : this.text.charAt(i32);
                        if (i28 != dArr5.length) {
                            double[] dArr7 = new double[i28];
                            i4 = 0;
                            System.arraycopy(dArr5, 0, dArr7, 0, i28);
                            dArr5 = dArr7;
                        } else {
                            i4 = 0;
                        }
                        if (i10 >= dArr4.length) {
                            dArr4 = new double[(dArr4.length * 3) / 2];
                            System.arraycopy(dArr5, i4, dArr4, i4, i28);
                        }
                        int i33 = i10 + 1;
                        dArr4[i10] = dArr5;
                        if (charAt8 == ',') {
                            int i34 = i31 + 1;
                            int i35 = this.bp + i31;
                            charAt3 = i35 >= this.len ? EOI : this.text.charAt(i35);
                            i7 = i34;
                            dArr = null;
                        } else if (charAt8 == ']') {
                            int i36 = i31 + 1;
                            int i37 = this.bp + i31;
                            char charAt9 = i37 >= this.len ? EOI : this.text.charAt(i37);
                            if (i33 != dArr4.length) {
                                double[][] dArr8 = new double[i33];
                                System.arraycopy(dArr4, 0, dArr8, 0, i33);
                                dArr4 = dArr8;
                            }
                            if (charAt9 == ',') {
                                this.bp += i36 - 1;
                                next();
                                this.matchStat = 3;
                                this.token = 16;
                                return dArr4;
                            } else if (charAt9 == '}') {
                                int i38 = i36 + 1;
                                char charAt10 = charAt(this.bp + i36);
                                if (charAt10 == ',') {
                                    this.token = 16;
                                    this.bp += i38 - 1;
                                    next();
                                } else if (charAt10 == ']') {
                                    this.token = 15;
                                    this.bp += i38 - 1;
                                    next();
                                } else if (charAt10 == '}') {
                                    this.token = 13;
                                    this.bp += i38 - 1;
                                    next();
                                } else if (charAt10 == 26) {
                                    this.bp += i38 - 1;
                                    this.token = 20;
                                    this.ch = EOI;
                                } else {
                                    this.matchStat = -1;
                                    return null;
                                }
                                this.matchStat = 4;
                                return dArr4;
                            } else {
                                this.matchStat = -1;
                                return null;
                            }
                        } else {
                            dArr = null;
                            charAt3 = charAt8;
                            i7 = i31;
                        }
                        i10 = i33;
                        dArr3 = dArr;
                        c2 = '[';
                        i9 = 16;
                    } else {
                        dArr2 = null;
                        charAt4 = charAt;
                    }
                    dArr3 = dArr2;
                    i11 = i2;
                    i13 = i28;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:232:0x00b4 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:230:0x00b1 -> B:231:0x00b2). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final float scanFieldFloat(long j2) {
        int i2;
        char charAt;
        int i3;
        float parseFloat;
        int i4;
        char charAt2;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        if (matchFieldHash == 0) {
            return 0.0f;
        }
        int i5 = matchFieldHash + 1;
        char charAt3 = charAt(this.bp + matchFieldHash);
        int i6 = this.bp;
        int i7 = (i6 + i5) - 1;
        boolean z = charAt3 == '-';
        if (z) {
            i5++;
            charAt3 = charAt(i6 + i5);
        }
        if (charAt3 >= '0' && charAt3 <= '9') {
            int i8 = charAt3 - '0';
            while (true) {
                i2 = i5 + 1;
                charAt = charAt(this.bp + i5);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i8 = (i8 * 10) + (charAt - '0');
                i5 = i2;
            }
            if (charAt == '.') {
                int i9 = i2 + 1;
                char charAt4 = charAt(this.bp + i2);
                if (charAt4 < '0' || charAt4 > '9') {
                    this.matchStat = -1;
                    return 0.0f;
                }
                i8 = (i8 * 10) + (charAt4 - '0');
                int i10 = 10;
                while (true) {
                    i4 = i9 + 1;
                    charAt2 = charAt(this.bp + i9);
                    if (charAt2 < '0' || charAt2 > '9') {
                        break;
                    }
                    i8 = (i8 * 10) + (charAt2 - '0');
                    i10 *= 10;
                    i9 = i4;
                }
                i2 = i4;
                i3 = i10;
                charAt = charAt2;
            } else {
                i3 = 1;
            }
            boolean z2 = charAt == 'e' || charAt == 'E';
            if (z2) {
                int i11 = i2 + 1;
                charAt = charAt(this.bp + i2);
                if (charAt == '+' || charAt == '-') {
                    int i12 = i11 + 1;
                    charAt = charAt(this.bp + i11);
                    i2 = i12;
                    if (charAt >= '0' && charAt <= '9') {
                        i12 = i2 + 1;
                        charAt = charAt(this.bp + i2);
                        i2 = i12;
                        if (charAt >= '0') {
                            i12 = i2 + 1;
                            charAt = charAt(this.bp + i2);
                            i2 = i12;
                            if (charAt >= '0') {
                            }
                        }
                    }
                } else {
                    i2 = i11;
                    if (charAt >= '0') {
                    }
                }
            }
            int i13 = ((this.bp + i2) - i7) - 1;
            if (z2 || i13 >= 10) {
                parseFloat = Float.parseFloat(subString(i7, i13));
            } else {
                parseFloat = i8 / i3;
                if (z) {
                    parseFloat = -parseFloat;
                }
            }
            if (charAt == ',') {
                this.bp += i2 - 1;
                next();
                this.matchStat = 3;
                this.token = 16;
                return parseFloat;
            } else if (charAt == '}') {
                int i14 = i2 + 1;
                char charAt5 = charAt(this.bp + i2);
                if (charAt5 == ',') {
                    this.token = 16;
                    this.bp += i14 - 1;
                    next();
                } else if (charAt5 == ']') {
                    this.token = 15;
                    this.bp += i14 - 1;
                    next();
                } else if (charAt5 == '}') {
                    this.token = 13;
                    this.bp += i14 - 1;
                    next();
                } else if (charAt5 == 26) {
                    this.bp += i14 - 1;
                    this.token = 20;
                    this.ch = EOI;
                } else {
                    this.matchStat = -1;
                    return 0.0f;
                }
                this.matchStat = 4;
                return parseFloat;
            } else {
                this.matchStat = -1;
                return 0.0f;
            }
        }
        this.matchStat = -1;
        return 0.0f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:370:0x00d3, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:371:0x00d5, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:452:0x021c, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:453:0x021e, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:396:0x011b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0128  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:394:0x0116 -> B:395:0x0119). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final float[] scanFieldFloatArray(long j2) {
        int i2;
        char charAt;
        int i3;
        float parseFloat;
        char charAt2;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        float[] fArr = null;
        if (matchFieldHash == 0) {
            return null;
        }
        int i4 = matchFieldHash + 1;
        int i5 = this.bp + matchFieldHash;
        if ((i5 >= this.len ? EOI : this.text.charAt(i5)) != '[') {
            this.matchStat = -1;
            return null;
        }
        int i6 = i4 + 1;
        int i7 = this.bp + i4;
        char charAt3 = i7 >= this.len ? EOI : this.text.charAt(i7);
        float[] fArr2 = new float[16];
        int i8 = 0;
        while (true) {
            int i9 = this.bp;
            int i10 = (i9 + i6) - 1;
            boolean z = charAt3 == '-';
            if (z) {
                int i11 = i9 + i6;
                i6++;
                charAt3 = i11 >= this.len ? EOI : this.text.charAt(i11);
            }
            if (charAt3 < '0' || charAt3 > '9') {
                break;
            }
            int i12 = charAt3 - '0';
            while (true) {
                i2 = i6 + 1;
                int i13 = this.bp + i6;
                charAt = i13 >= this.len ? EOI : this.text.charAt(i13);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i12 = (i12 * 10) + (charAt - '0');
                i6 = i2;
            }
            if (charAt == '.') {
                int i14 = i2 + 1;
                int i15 = this.bp + i2;
                char charAt4 = i15 >= this.len ? EOI : this.text.charAt(i15);
                if (charAt4 >= '0' && charAt4 <= '9') {
                    i12 = (i12 * 10) + (charAt4 - '0');
                    i3 = 10;
                    while (true) {
                        i2 = i14 + 1;
                        int i16 = this.bp + i14;
                        charAt = i16 >= this.len ? EOI : this.text.charAt(i16);
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i12 = (i12 * 10) + (charAt - '0');
                        i3 *= 10;
                        i14 = i2;
                    }
                } else {
                    break;
                }
            } else {
                i3 = 1;
            }
            boolean z2 = charAt == 'e' || charAt == 'E';
            if (z2) {
                int i17 = i2 + 1;
                int i18 = this.bp + i2;
                char charAt5 = i18 >= this.len ? EOI : this.text.charAt(i18);
                if (charAt5 == '+' || charAt5 == '-') {
                    int i19 = i17 + 1;
                    int i20 = this.bp + i17;
                    if (i20 < this.len) {
                        charAt2 = this.text.charAt(i20);
                        i2 = i19;
                        charAt = charAt2;
                        if (charAt >= '0' && charAt <= '9') {
                            i19 = i2 + 1;
                            int i21 = this.bp + i2;
                            if (i21 < this.len) {
                                charAt2 = this.text.charAt(i21);
                                i2 = i19;
                                charAt = charAt2;
                                if (charAt >= '0') {
                                    i19 = i2 + 1;
                                    int i212 = this.bp + i2;
                                    if (i212 < this.len) {
                                    }
                                }
                            }
                        }
                    }
                    charAt2 = EOI;
                    i2 = i19;
                    charAt = charAt2;
                    if (charAt >= '0') {
                    }
                } else {
                    charAt = charAt5;
                    i2 = i17;
                    if (charAt >= '0') {
                    }
                }
            }
            int i22 = ((this.bp + i2) - i10) - 1;
            if (z2 || i22 >= 10) {
                parseFloat = Float.parseFloat(subString(i10, i22));
            } else {
                parseFloat = i12 / i3;
                if (z) {
                    parseFloat = -parseFloat;
                }
            }
            if (i8 >= fArr2.length) {
                float[] fArr3 = new float[(fArr2.length * 3) / 2];
                System.arraycopy(fArr2, 0, fArr3, 0, i8);
                fArr2 = fArr3;
            }
            int i23 = i8 + 1;
            fArr2[i8] = parseFloat;
            if (charAt == ',') {
                int i24 = i2 + 1;
                int i25 = this.bp + i2;
                charAt = i25 >= this.len ? EOI : this.text.charAt(i25);
                i2 = i24;
            } else if (charAt == ']') {
                int i26 = i2 + 1;
                int i27 = this.bp + i2;
                char charAt6 = i27 >= this.len ? EOI : this.text.charAt(i27);
                if (i23 != fArr2.length) {
                    float[] fArr4 = new float[i23];
                    System.arraycopy(fArr2, 0, fArr4, 0, i23);
                    fArr2 = fArr4;
                }
                if (charAt6 == ',') {
                    this.bp += i26 - 1;
                    next();
                    this.matchStat = 3;
                    this.token = 16;
                    return fArr2;
                } else if (charAt6 == '}') {
                    int i28 = i26 + 1;
                    int i29 = this.bp + i26;
                    char charAt7 = i29 >= this.len ? EOI : this.text.charAt(i29);
                    if (charAt7 == ',') {
                        this.token = 16;
                        this.bp += i28 - 1;
                        next();
                    } else if (charAt7 == ']') {
                        this.token = 15;
                        this.bp += i28 - 1;
                        next();
                    } else if (charAt7 == '}') {
                        this.token = 13;
                        this.bp += i28 - 1;
                        next();
                    } else if (charAt7 == 26) {
                        this.bp += i28 - 1;
                        this.token = 20;
                        this.ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                    this.matchStat = 4;
                    return fArr2;
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            fArr = null;
            charAt3 = charAt;
            i8 = i23;
            i6 = i2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:432:0x00e5, code lost:
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x00e7, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:535:0x0290, code lost:
        r12 = r3;
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:536:0x0293, code lost:
        return r12;
     */
    /* JADX WARN: Removed duplicated region for block: B:459:0x012d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x013c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:457:0x0129 -> B:458:0x012b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final float[][] scanFieldFloatArray2(long j2) {
        int i2;
        char charAt;
        int i3;
        float parseFloat;
        int i4;
        float[][] fArr;
        float[][] fArr2;
        this.matchStat = 0;
        int matchFieldHash = matchFieldHash(j2);
        float[][] fArr3 = null;
        if (matchFieldHash == 0) {
            return null;
        }
        int i5 = matchFieldHash + 1;
        int i6 = this.bp + matchFieldHash;
        char charAt2 = i6 >= this.len ? EOI : this.text.charAt(i6);
        char c2 = '[';
        if (charAt2 != '[') {
            this.matchStat = -1;
            return null;
        }
        int i7 = i5 + 1;
        int i8 = this.bp + i5;
        char charAt3 = i8 >= this.len ? EOI : this.text.charAt(i8);
        int i9 = 16;
        float[][] fArr4 = new float[16];
        int i10 = 0;
        loop0: while (true) {
            if (charAt3 == c2) {
                int i11 = i7 + 1;
                int i12 = this.bp + i7;
                char charAt4 = i12 >= this.len ? EOI : this.text.charAt(i12);
                float[] fArr5 = new float[i9];
                int i13 = 0;
                while (true) {
                    int i14 = this.bp;
                    int i15 = (i14 + i11) - 1;
                    boolean z = charAt4 == '-';
                    if (z) {
                        int i16 = i14 + i11;
                        i11++;
                        charAt4 = i16 >= this.len ? EOI : this.text.charAt(i16);
                    }
                    if (charAt4 < '0' || charAt4 > '9') {
                        break loop0;
                    }
                    int i17 = charAt4 - '0';
                    while (true) {
                        i2 = i11 + 1;
                        int i18 = this.bp + i11;
                        charAt = i18 >= this.len ? EOI : this.text.charAt(i18);
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i17 = (i17 * 10) + (charAt - '0');
                        i11 = i2;
                    }
                    if (charAt == '.') {
                        int i19 = i2 + 1;
                        int i20 = this.bp + i2;
                        char charAt5 = i20 >= this.len ? EOI : this.text.charAt(i20);
                        if (charAt5 >= '0' && charAt5 <= '9') {
                            i17 = (i17 * 10) + (charAt5 - '0');
                            i3 = 10;
                            while (true) {
                                i2 = i19 + 1;
                                int i21 = this.bp + i19;
                                charAt = i21 >= this.len ? EOI : this.text.charAt(i21);
                                if (charAt < '0' || charAt > '9') {
                                    break;
                                }
                                i17 = (i17 * 10) + (charAt - '0');
                                i3 *= 10;
                                i19 = i2;
                            }
                        } else {
                            break loop0;
                        }
                    } else {
                        i3 = 1;
                    }
                    boolean z2 = charAt == 'e' || charAt == 'E';
                    if (z2) {
                        int i22 = i2 + 1;
                        int i23 = this.bp + i2;
                        char charAt6 = i23 >= this.len ? EOI : this.text.charAt(i23);
                        if (charAt6 == '+' || charAt6 == '-') {
                            int i24 = i22 + 1;
                            int i25 = this.bp + i22;
                            char charAt7 = i25 >= this.len ? EOI : this.text.charAt(i25);
                            charAt = charAt7;
                            i2 = i24;
                            if (charAt >= '0' && charAt <= '9') {
                                i24 = i2 + 1;
                                int i26 = this.bp + i2;
                                if (i26 < this.len) {
                                    charAt = EOI;
                                    i2 = i24;
                                    if (charAt >= '0') {
                                        i24 = i2 + 1;
                                        int i262 = this.bp + i2;
                                        if (i262 < this.len) {
                                            charAt7 = this.text.charAt(i262);
                                            charAt = charAt7;
                                            i2 = i24;
                                            if (charAt >= '0') {
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            charAt = charAt6;
                            i2 = i22;
                            if (charAt >= '0') {
                            }
                        }
                    }
                    int i27 = ((this.bp + i2) - i15) - 1;
                    if (z2 || i27 >= 10) {
                        parseFloat = Float.parseFloat(subString(i15, i27));
                    } else {
                        parseFloat = i17 / i3;
                        if (z) {
                            parseFloat = -parseFloat;
                        }
                    }
                    if (i13 >= fArr5.length) {
                        float[] fArr6 = new float[(fArr5.length * 3) / 2];
                        System.arraycopy(fArr5, 0, fArr6, 0, i13);
                        fArr5 = fArr6;
                    }
                    int i28 = i13 + 1;
                    fArr5[i13] = parseFloat;
                    if (charAt == ',') {
                        int i29 = i2 + 1;
                        int i30 = this.bp + i2;
                        charAt4 = i30 >= this.len ? EOI : this.text.charAt(i30);
                        i2 = i29;
                        fArr2 = null;
                    } else if (charAt == ']') {
                        int i31 = i2 + 1;
                        int i32 = this.bp + i2;
                        char charAt8 = i32 >= this.len ? EOI : this.text.charAt(i32);
                        if (i28 != fArr5.length) {
                            float[] fArr7 = new float[i28];
                            i4 = 0;
                            System.arraycopy(fArr5, 0, fArr7, 0, i28);
                            fArr5 = fArr7;
                        } else {
                            i4 = 0;
                        }
                        if (i10 >= fArr4.length) {
                            fArr4 = new float[(fArr4.length * 3) / 2];
                            System.arraycopy(fArr5, i4, fArr4, i4, i28);
                        }
                        int i33 = i10 + 1;
                        fArr4[i10] = fArr5;
                        if (charAt8 == ',') {
                            int i34 = i31 + 1;
                            int i35 = this.bp + i31;
                            charAt3 = i35 >= this.len ? EOI : this.text.charAt(i35);
                            i7 = i34;
                            fArr = null;
                        } else if (charAt8 == ']') {
                            int i36 = i31 + 1;
                            int i37 = this.bp + i31;
                            char charAt9 = i37 >= this.len ? EOI : this.text.charAt(i37);
                            if (i33 != fArr4.length) {
                                float[][] fArr8 = new float[i33];
                                System.arraycopy(fArr4, 0, fArr8, 0, i33);
                                fArr4 = fArr8;
                            }
                            if (charAt9 == ',') {
                                this.bp += i36 - 1;
                                next();
                                this.matchStat = 3;
                                this.token = 16;
                                return fArr4;
                            } else if (charAt9 == '}') {
                                int i38 = i36 + 1;
                                char charAt10 = charAt(this.bp + i36);
                                if (charAt10 == ',') {
                                    this.token = 16;
                                    this.bp += i38 - 1;
                                    next();
                                } else if (charAt10 == ']') {
                                    this.token = 15;
                                    this.bp += i38 - 1;
                                    next();
                                } else if (charAt10 == '}') {
                                    this.token = 13;
                                    this.bp += i38 - 1;
                                    next();
                                } else if (charAt10 == 26) {
                                    this.bp += i38 - 1;
                                    this.token = 20;
                                    this.ch = EOI;
                                } else {
                                    this.matchStat = -1;
                                    return null;
                                }
                                this.matchStat = 4;
                                return fArr4;
                            } else {
                                this.matchStat = -1;
                                return null;
                            }
                        } else {
                            fArr = null;
                            charAt3 = charAt8;
                            i7 = i31;
                        }
                        i10 = i33;
                        fArr3 = fArr;
                        c2 = '[';
                        i9 = 16;
                    } else {
                        fArr2 = null;
                        charAt4 = charAt;
                    }
                    fArr3 = fArr2;
                    i11 = i2;
                    i13 = i28;
                }
            }
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:104:0x00c4, code lost:
        if (r0 != false) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x00c7, code lost:
        return -r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:?, code lost:
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final long scanLongValue() {
        long j2;
        boolean z = false;
        this.np = 0;
        if (this.ch == '-') {
            this.np = 0 + 1;
            int i2 = this.bp + 1;
            this.bp = i2;
            if (i2 < this.len) {
                this.ch = this.text.charAt(i2);
                j2 = Long.MIN_VALUE;
                z = true;
            } else {
                throw new JSONException("syntax error, " + info());
            }
        } else {
            j2 = -9223372036854775807L;
        }
        long j3 = 0;
        while (true) {
            char c2 = this.ch;
            if (c2 < '0' || c2 > '9') {
                break;
            }
            int i3 = c2 - '0';
            if (j3 < -922337203685477580L) {
                throw new JSONException("error long value, " + j3 + ", " + info());
            }
            long j4 = j3 * 10;
            long j5 = i3;
            if (j4 >= j2 + j5) {
                j3 = j4 - j5;
                this.np++;
                int i4 = this.bp + 1;
                this.bp = i4;
                this.ch = i4 >= this.len ? EOI : this.text.charAt(i4);
            } else {
                throw new JSONException("error long value, " + j4 + ", " + info());
            }
        }
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

    /* JADX WARN: Removed duplicated region for block: B:398:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x020d A[Catch: NumberFormatException -> 0x025b, TryCatch #0 {NumberFormatException -> 0x025b, blocks: (B:472:0x01fd, B:478:0x0209, B:480:0x020d, B:486:0x021e, B:483:0x0215, B:485:0x021c, B:489:0x0225, B:492:0x022b, B:497:0x023c, B:477:0x0206, B:499:0x0241, B:501:0x024b, B:503:0x0251), top: B:510:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:489:0x0225 A[Catch: NumberFormatException -> 0x025b, TryCatch #0 {NumberFormatException -> 0x025b, blocks: (B:472:0x01fd, B:478:0x0209, B:480:0x020d, B:486:0x021e, B:483:0x0215, B:485:0x021c, B:489:0x0225, B:492:0x022b, B:497:0x023c, B:477:0x0206, B:499:0x0241, B:501:0x024b, B:503:0x0251), top: B:510:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x024b A[Catch: NumberFormatException -> 0x025b, TryCatch #0 {NumberFormatException -> 0x025b, blocks: (B:472:0x01fd, B:478:0x0209, B:480:0x020d, B:486:0x021e, B:483:0x0215, B:485:0x021c, B:489:0x0225, B:492:0x022b, B:497:0x023c, B:477:0x0206, B:499:0x0241, B:501:0x024b, B:503:0x0251), top: B:510:0x01f9 }] */
    /* JADX WARN: Removed duplicated region for block: B:503:0x0251 A[Catch: NumberFormatException -> 0x025b, TRY_LEAVE, TryCatch #0 {NumberFormatException -> 0x025b, blocks: (B:472:0x01fd, B:478:0x0209, B:480:0x020d, B:486:0x021e, B:483:0x0215, B:485:0x021c, B:489:0x0225, B:492:0x022b, B:497:0x023c, B:477:0x0206, B:499:0x0241, B:501:0x024b, B:503:0x0251), top: B:510:0x01f9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Number scanNumberValue() {
        long j2;
        boolean z;
        char c2;
        boolean z2;
        Number number;
        Number valueOf;
        boolean z3;
        char c3;
        char charAt;
        char c4;
        boolean z4;
        int i2;
        char[] cArr;
        int i3;
        char[] cArr2;
        Number valueOf2;
        int i4;
        int i5 = this.bp;
        this.np = 0;
        if (this.ch == '-') {
            j2 = Long.MIN_VALUE;
            this.np = 0 + 1;
            int i6 = i5 + 1;
            this.bp = i6;
            this.ch = i6 >= this.len ? EOI : this.text.charAt(i6);
            z = true;
        } else {
            j2 = -9223372036854775807L;
            z = false;
        }
        long j3 = 0;
        boolean z5 = false;
        while (true) {
            c2 = this.ch;
            if (c2 < '0' || c2 > '9') {
                break;
            }
            int i7 = c2 - '0';
            if (j3 < -922337203685477580L) {
                z5 = true;
            }
            long j4 = j3 * 10;
            long j5 = i7;
            if (j4 < j2 + j5) {
                z5 = true;
            }
            j3 = j4 - j5;
            this.np++;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = i8 >= this.len ? EOI : this.text.charAt(i8);
        }
        if (!z) {
            j3 = -j3;
        }
        try {
            if (c2 == 'L') {
                this.np++;
                next();
                valueOf = Long.valueOf(j3);
            } else if (c2 == 'S') {
                this.np++;
                next();
                valueOf = Short.valueOf((short) j3);
            } else if (c2 == 'B') {
                this.np++;
                next();
                valueOf = Byte.valueOf((byte) j3);
            } else if (c2 == 'F') {
                this.np++;
                next();
                valueOf = Float.valueOf((float) j3);
            } else {
                if (c2 == 'D') {
                    this.np++;
                    next();
                    z2 = z;
                    number = Double.valueOf(j3);
                } else {
                    z2 = z;
                    number = null;
                }
                if (this.ch != '.') {
                    this.np++;
                    int i9 = this.bp + 1;
                    this.bp = i9;
                    this.ch = i9 >= this.len ? EOI : this.text.charAt(i9);
                    while (true) {
                        char c5 = this.ch;
                        if (c5 < '0' || c5 > '9') {
                            break;
                        }
                        this.np++;
                        int i10 = this.bp + 1;
                        this.bp = i10;
                        this.ch = i10 >= this.len ? EOI : this.text.charAt(i10);
                    }
                    z3 = true;
                } else {
                    z3 = false;
                }
                c3 = this.ch;
                if (c3 != 'e' || c3 == 'E') {
                    this.np++;
                    int i11 = this.bp + 1;
                    this.bp = i11;
                    charAt = i11 < this.len ? EOI : this.text.charAt(i11);
                    this.ch = charAt;
                    if (charAt != '+' || charAt == '-') {
                        this.np++;
                        int i12 = this.bp + 1;
                        this.bp = i12;
                        this.ch = i12 < this.len ? EOI : this.text.charAt(i12);
                    }
                    while (true) {
                        c4 = this.ch;
                        if (c4 < '0' || c4 > '9') {
                            break;
                        }
                        this.np++;
                        int i13 = this.bp + 1;
                        this.bp = i13;
                        this.ch = i13 >= this.len ? EOI : this.text.charAt(i13);
                    }
                    if (c4 != 'D' || c4 == 'F') {
                        this.np++;
                        next();
                    } else {
                        c4 = 0;
                    }
                    z4 = true;
                } else {
                    c4 = 0;
                    z4 = false;
                }
                if (z3 && !z4) {
                    if (z5) {
                        int i14 = this.bp;
                        char[] cArr3 = new char[i14 - i5];
                        this.text.getChars(i5, i14, cArr3, 0);
                        number = new BigInteger(new String(cArr3));
                    }
                    if (number == null) {
                        if (j3 > -2147483648L && j3 < 2147483647L) {
                            return Integer.valueOf((int) j3);
                        }
                        return Long.valueOf(j3);
                    }
                    return number;
                }
                i2 = this.bp - i5;
                if (c4 != 0) {
                    i2--;
                }
                cArr = this.sbuf;
                if (i2 >= cArr.length) {
                    i3 = 0;
                    this.text.getChars(i5, i5 + i2, cArr, 0);
                    cArr2 = this.sbuf;
                } else {
                    i3 = 0;
                    char[] cArr4 = new char[i2];
                    this.text.getChars(i5, i5 + i2, cArr4, 0);
                    cArr2 = cArr4;
                }
                if (z4 && (this.features & Feature.UseBigDecimal.mask) != 0) {
                    return new BigDecimal(cArr2, i3, i2);
                }
                if (i2 > 9 && !z4) {
                    char c6 = cArr2[i3];
                    if (c6 != '-' && c6 != '+') {
                        i4 = 1;
                        int i15 = c6 - '0';
                        int i16 = 0;
                        while (i4 < i2) {
                            char c7 = cArr2[i4];
                            if (c7 == '.') {
                                i16 = 1;
                            } else {
                                i15 = (i15 * 10) + (c7 - '0');
                                if (i16 != 0) {
                                    i16 *= 10;
                                }
                            }
                            i4++;
                        }
                        if (c4 != 'F') {
                            float f2 = i15 / i16;
                            if (z2) {
                                f2 = -f2;
                            }
                            return Float.valueOf(f2);
                        }
                        double d = i15;
                        double d2 = i16;
                        Double.isNaN(d);
                        Double.isNaN(d2);
                        double d3 = d / d2;
                        if (z2) {
                            d3 = -d3;
                        }
                        return Double.valueOf(d3);
                    }
                    i4 = 2;
                    c6 = cArr2[1];
                    int i152 = c6 - '0';
                    int i162 = 0;
                    while (i4 < i2) {
                    }
                    if (c4 != 'F') {
                    }
                } else {
                    String str = new String(cArr2, 0, i2);
                    if (c4 != 'F') {
                        valueOf2 = Float.valueOf(str);
                    } else {
                        valueOf2 = Double.valueOf(Double.parseDouble(str));
                    }
                    return valueOf2;
                }
            }
            if (i2 > 9) {
            }
            String str2 = new String(cArr2, 0, i2);
            if (c4 != 'F') {
            }
            return valueOf2;
        } catch (NumberFormatException e2) {
            throw new JSONException(e2.getMessage() + ", " + info(), e2);
        }
        z2 = z;
        number = valueOf;
        if (this.ch != '.') {
        }
        c3 = this.ch;
        if (c3 != 'e') {
        }
        this.np++;
        int i112 = this.bp + 1;
        this.bp = i112;
        if (i112 < this.len) {
        }
        this.ch = charAt;
        if (charAt != '+') {
        }
        this.np++;
        int i122 = this.bp + 1;
        this.bp = i122;
        this.ch = i122 < this.len ? EOI : this.text.charAt(i122);
        while (true) {
            c4 = this.ch;
            if (c4 < '0') {
                break;
            }
            break;
            this.ch = i13 >= this.len ? EOI : this.text.charAt(i13);
        }
        if (c4 != 'D') {
        }
        this.np++;
        next();
        z4 = true;
        if (z3) {
        }
        i2 = this.bp - i5;
        if (c4 != 0) {
        }
        cArr = this.sbuf;
        if (i2 >= cArr.length) {
        }
        if (z4) {
        }
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

    /* JADX WARN: Removed duplicated region for block: B:759:0x01f9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:761:0x01fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean scanISO8601DateIfMatch(boolean z, int i2) {
        char c2;
        int i3;
        int i4;
        int i5;
        int i6;
        char c3;
        int i7;
        char c4;
        int i8;
        char charAt;
        int i9;
        char charAt2;
        int i10;
        int i11;
        char charAt3;
        char charAt4;
        char charAt5;
        if (!z && i2 > 13) {
            char charAt6 = charAt(this.bp);
            char charAt7 = charAt(this.bp + 1);
            char charAt8 = charAt(this.bp + 2);
            char charAt9 = charAt(this.bp + 3);
            char charAt10 = charAt(this.bp + 4);
            char charAt11 = charAt(this.bp + 5);
            char charAt12 = charAt((this.bp + i2) - 1);
            char charAt13 = charAt((this.bp + i2) - 2);
            if (charAt6 == '/' && charAt7 == 'D' && charAt8 == 'a' && charAt9 == 't' && charAt10 == 'e' && charAt11 == '(' && charAt12 == '/' && charAt13 == ')') {
                int i12 = -1;
                for (int i13 = 6; i13 < i2; i13++) {
                    char charAt14 = charAt(this.bp + i13);
                    if (charAt14 != '+') {
                        if (charAt14 < '0' || charAt14 > '9') {
                            break;
                        }
                    } else {
                        i12 = i13;
                    }
                }
                if (i12 == -1) {
                    return false;
                }
                int i14 = this.bp + 6;
                long parseLong = Long.parseLong(subString(i14, i12 - i14));
                Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
                this.calendar = calendar;
                calendar.setTimeInMillis(parseLong);
                this.token = 5;
                return true;
            }
        }
        if (i2 == 8 || i2 == 14 || ((i2 == 16 && ((charAt5 = charAt(this.bp + 10)) == 'T' || charAt5 == ' ')) || (i2 == 17 && charAt(this.bp + 6) != '-'))) {
            if (z) {
                return false;
            }
            char charAt15 = charAt(this.bp);
            char charAt16 = charAt(this.bp + 1);
            char charAt17 = charAt(this.bp + 2);
            char charAt18 = charAt(this.bp + 3);
            char charAt19 = charAt(this.bp + 4);
            char charAt20 = charAt(this.bp + 5);
            char charAt21 = charAt(this.bp + 6);
            char charAt22 = charAt(this.bp + 7);
            char charAt23 = charAt(this.bp + 8);
            boolean z2 = charAt19 == '-' && charAt22 == '-';
            boolean z3 = z2 && i2 == 16;
            boolean z4 = z2 && i2 == 17;
            if (z4 || z3) {
                charAt22 = charAt(this.bp + 9);
                c2 = charAt23;
            } else {
                c2 = charAt21;
                charAt21 = charAt20;
                charAt20 = charAt19;
            }
            if (checkDate(charAt15, charAt16, charAt17, charAt18, charAt20, charAt21, c2, charAt22)) {
                setCalendar(charAt15, charAt16, charAt17, charAt18, charAt20, charAt21, c2, charAt22);
                if (i2 != 8) {
                    char charAt24 = charAt(this.bp + 9);
                    char charAt25 = charAt(this.bp + 10);
                    char charAt26 = charAt(this.bp + 11);
                    char charAt27 = charAt(this.bp + 12);
                    char charAt28 = charAt(this.bp + 13);
                    if ((z4 && charAt25 == 'T' && charAt28 == ':' && charAt(this.bp + 16) == 'Z') || (z3 && ((charAt25 == ' ' || charAt25 == 'T') && charAt28 == ':'))) {
                        charAt25 = charAt(this.bp + 14);
                        charAt23 = charAt26;
                        charAt28 = '0';
                        charAt26 = charAt(this.bp + 15);
                        charAt24 = charAt27;
                        charAt27 = '0';
                    }
                    if (!checkTime(charAt23, charAt24, charAt25, charAt26, charAt27, charAt28)) {
                        return false;
                    }
                    if (i2 != 17 || z4) {
                        c3 = '0';
                        i7 = 0;
                    } else {
                        char charAt29 = charAt(this.bp + 14);
                        char charAt30 = charAt(this.bp + 15);
                        char charAt31 = charAt(this.bp + 16);
                        if (charAt29 < '0' || charAt29 > '9' || charAt30 < '0' || charAt30 > '9' || charAt31 < '0' || charAt31 > '9') {
                            return false;
                        }
                        i7 = ((charAt29 - '0') * 100) + ((charAt30 - '0') * 10) + (charAt31 - '0');
                        c3 = '0';
                    }
                    int i15 = charAt24 - c3;
                    i4 = ((charAt25 - c3) * 10) + (charAt26 - c3);
                    i5 = ((charAt27 - c3) * 10) + (charAt28 - c3);
                    i3 = i7;
                    i6 = i15 + ((charAt23 - c3) * 10);
                } else {
                    i3 = 0;
                    i4 = 0;
                    i5 = 0;
                    i6 = 0;
                }
                this.calendar.set(11, i6);
                this.calendar.set(12, i4);
                this.calendar.set(13, i5);
                this.calendar.set(14, i3);
                this.token = 5;
                return true;
            }
            return false;
        } else if (i2 < 9) {
            return false;
        } else {
            char charAt32 = charAt(this.bp);
            char charAt33 = charAt(this.bp + 1);
            char charAt34 = charAt(this.bp + 2);
            char charAt35 = charAt(this.bp + 3);
            char charAt36 = charAt(this.bp + 4);
            char charAt37 = charAt(this.bp + 5);
            char charAt38 = charAt(this.bp + 6);
            char charAt39 = charAt(this.bp + 7);
            char charAt40 = charAt(this.bp + 8);
            char charAt41 = charAt(this.bp + 9);
            if ((charAt36 != '-' || charAt39 != '-') && (charAt36 != '/' || charAt39 != '/')) {
                if (charAt36 == '-' && charAt38 == '-') {
                    if (charAt40 == ' ') {
                        charAt41 = charAt35;
                        charAt38 = charAt37;
                        charAt40 = charAt39;
                        c4 = '0';
                        charAt37 = '0';
                        i8 = 8;
                    } else {
                        charAt41 = charAt35;
                        charAt38 = charAt37;
                        c4 = charAt39;
                        charAt37 = '0';
                        i8 = 9;
                    }
                } else if ((charAt34 == '.' && charAt37 == '.') || (charAt34 == '-' && charAt37 == '-')) {
                    charAt37 = charAt35;
                    charAt34 = charAt40;
                    c4 = charAt32;
                    charAt40 = charAt33;
                    charAt33 = charAt39;
                    charAt32 = charAt38;
                    i8 = 10;
                    charAt38 = charAt36;
                } else if (charAt36 != '\u5e74' && charAt36 != '\ub144') {
                    return false;
                } else {
                    if (charAt39 == '\u6708' || charAt39 == '\uc6d4') {
                        if (charAt41 == '\u65e5' || charAt41 == '\uc77c') {
                            charAt41 = charAt35;
                            c4 = '0';
                        } else if (charAt(this.bp + 10) != '\u65e5' && charAt(this.bp + 10) != '\uc77c') {
                            return false;
                        } else {
                            i8 = 11;
                        }
                    } else if (charAt38 != '\u6708' && charAt38 != '\uc6d4') {
                        return false;
                    } else {
                        if (charAt40 == '\u65e5' || charAt40 == '\uc77c') {
                            charAt41 = charAt35;
                            charAt38 = charAt37;
                            charAt40 = charAt39;
                            c4 = '0';
                        } else if (charAt41 != '\u65e5' && charAt41 != '\uc77c') {
                            return false;
                        } else {
                            charAt41 = charAt35;
                            charAt38 = charAt37;
                            c4 = charAt39;
                        }
                        charAt37 = '0';
                    }
                    i8 = 10;
                }
                if (checkDate(charAt32, charAt33, charAt34, charAt41, charAt37, charAt38, c4, charAt40)) {
                    return false;
                }
                setCalendar(charAt32, charAt33, charAt34, charAt41, charAt37, charAt38, c4, charAt40);
                char charAt42 = charAt(this.bp + i8);
                if (charAt42 != 'T' && (charAt42 != ' ' || z)) {
                    if (charAt42 != '\"' && charAt42 != 26 && charAt42 != '\u65e5' && charAt42 != '\uc77c') {
                        if ((charAt42 == '+' || charAt42 == '-') && this.len == i8 + 6 && charAt(this.bp + i8 + 3) == ':' && charAt(this.bp + i8 + 4) == '0' && charAt(this.bp + i8 + 5) == '0') {
                            setTime('0', '0', '0', '0', '0', '0');
                            this.calendar.set(14, 0);
                            setTimeZone(charAt42, charAt(this.bp + i8 + 1), charAt(this.bp + i8 + 2));
                            return true;
                        }
                        return false;
                    }
                    this.calendar.set(11, 0);
                    this.calendar.set(12, 0);
                    this.calendar.set(13, 0);
                    this.calendar.set(14, 0);
                    int i16 = this.bp + i8;
                    this.bp = i16;
                    this.ch = charAt(i16);
                    this.token = 5;
                    return true;
                }
                int i17 = i8 + 9;
                if (i2 >= i17 && charAt(this.bp + i8 + 3) == ':' && charAt(this.bp + i8 + 6) == ':') {
                    char charAt43 = charAt(this.bp + i8 + 1);
                    char charAt44 = charAt(this.bp + i8 + 2);
                    char charAt45 = charAt(this.bp + i8 + 4);
                    char charAt46 = charAt(this.bp + i8 + 5);
                    char charAt47 = charAt(this.bp + i8 + 7);
                    char charAt48 = charAt(this.bp + i8 + 8);
                    if (checkTime(charAt43, charAt44, charAt45, charAt46, charAt47, charAt48)) {
                        setTime(charAt43, charAt44, charAt45, charAt46, charAt47, charAt48);
                        char charAt49 = charAt(this.bp + i8 + 9);
                        if (charAt49 != '.') {
                            this.calendar.set(14, 0);
                            int i18 = this.bp + i17;
                            this.bp = i18;
                            this.ch = charAt(i18);
                            this.token = 5;
                            if (charAt49 == 'Z' && this.calendar.getTimeZone().getRawOffset() != 0) {
                                String[] availableIDs = TimeZone.getAvailableIDs(0);
                                if (availableIDs.length > 0) {
                                    this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
                                }
                            }
                            return true;
                        }
                        int i19 = i8 + 11;
                        if (i2 >= i19 && (charAt = charAt(this.bp + i8 + 10)) >= '0' && charAt <= '9') {
                            int i20 = charAt - '0';
                            if (i2 <= i19 || (charAt4 = charAt(this.bp + i8 + 11)) < '0' || charAt4 > '9') {
                                i9 = 1;
                            } else {
                                i20 = (i20 * 10) + (charAt4 - '0');
                                i9 = 2;
                            }
                            if (i9 == 2 && (charAt3 = charAt(this.bp + i8 + 12)) >= '0' && charAt3 <= '9') {
                                i20 = (i20 * 10) + (charAt3 - '0');
                                i9 = 3;
                            }
                            this.calendar.set(14, i20);
                            char charAt50 = charAt(this.bp + i8 + 10 + i9);
                            if (charAt50 == '+' || charAt50 == '-') {
                                char charAt51 = charAt(this.bp + i8 + 10 + i9 + 1);
                                if (charAt51 >= '0' && charAt51 <= '1' && (charAt2 = charAt(this.bp + i8 + 10 + i9 + 2)) >= '0' && charAt2 <= '9') {
                                    char charAt52 = charAt(this.bp + i8 + 10 + i9 + 3);
                                    if (charAt52 == ':') {
                                        if (charAt(this.bp + i8 + 10 + i9 + 4) != '0' || charAt(this.bp + i8 + 10 + i9 + 5) != '0') {
                                            return false;
                                        }
                                        i10 = 6;
                                    } else if (charAt52 != '0') {
                                        i10 = 3;
                                    } else if (charAt(this.bp + i8 + 10 + i9 + 4) != '0') {
                                        return false;
                                    } else {
                                        i10 = 5;
                                    }
                                    setTimeZone(charAt50, charAt51, charAt2);
                                    i11 = i10;
                                }
                            } else if (charAt50 == 'Z') {
                                if (this.calendar.getTimeZone().getRawOffset() != 0) {
                                    String[] availableIDs2 = TimeZone.getAvailableIDs(0);
                                    if (availableIDs2.length > 0) {
                                        this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs2[0]));
                                    }
                                }
                                i11 = 1;
                            } else {
                                i11 = 0;
                            }
                            int i21 = i8 + 10 + i9 + i11;
                            char charAt53 = charAt(this.bp + i21);
                            if (charAt53 == 26 || charAt53 == '\"') {
                                int i22 = this.bp + i21;
                                this.bp = i22;
                                this.ch = charAt(i22);
                                this.token = 5;
                                return true;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            i8 = 10;
            charAt40 = charAt41;
            charAt41 = charAt35;
            c4 = charAt40;
            if (checkDate(charAt32, charAt33, charAt34, charAt41, charAt37, charAt38, c4, charAt40)) {
            }
        }
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
