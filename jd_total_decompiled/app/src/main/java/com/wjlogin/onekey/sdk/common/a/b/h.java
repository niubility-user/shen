package com.wjlogin.onekey.sdk.common.a.b;

import android.util.Base64;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jd.lib.un.utils.RegexConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes11.dex */
public class h {
    private static final String b = "utf-8";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18350c = "MD5";
    private static final String d = "SHA1";

    /* renamed from: e  reason: collision with root package name */
    private static final String f18351e = "HmacSHA1";

    /* renamed from: f  reason: collision with root package name */
    public static final String f18352f = "44";

    /* renamed from: g  reason: collision with root package name */
    public static final String f18353g = "33";

    /* renamed from: i  reason: collision with root package name */
    private static final String f18355i = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static Random a = new Random();

    /* renamed from: h  reason: collision with root package name */
    private static final char[] f18354h = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: j  reason: collision with root package name */
    private static final Pattern f18356j = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2);

    /* renamed from: k  reason: collision with root package name */
    private static final Pattern f18357k = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2);

    /* renamed from: l  reason: collision with root package name */
    private static final Pattern f18358l = Pattern.compile("<[^>]+>", 2);

    /* renamed from: m  reason: collision with root package name */
    private static final Pattern f18359m = Pattern.compile("&[a-z]+;", 2);

    public static String A(String str) {
        if (m(str)) {
            return str;
        }
        String substring = str.substring(1);
        return substring.substring(0, substring.length() - 1);
    }

    public static String B(String str) {
        return str.replace("&lt;", "<").replace("&gt;", ">").replace("&nbsp;", LangUtils.SINGLE_SPACE).replace("&quot;", "\"").replace("&#39;", "'").replace("&#34;", "\"").replace("&amp;", ContainerUtils.FIELD_DELIMITER);
    }

    public static String C(String str) {
        return a(str, "UTF-8", "GBK");
    }

    public static String D(String str) {
        return a(str, "UTF-8", CharEncoding.ISO_8859_1);
    }

    public static long E(String str) {
        String[] split = str.split("\\.");
        return (Long.parseLong(split[0]) << 24) + 0 + (Long.parseLong(split[1]) << 16) + (Long.parseLong(split[2]) << 8) + Long.parseLong(split[3]);
    }

    private static boolean F(String str) {
        if (!m(str) && str.startsWith(f18352f)) {
            return Pattern.matches("^(447)\\d{9}$", str);
        }
        return false;
    }

    private static boolean G(String str) {
        if (!m(str) && str.startsWith(f18353g)) {
            return Pattern.matches("^(336)\\d{8}$", str);
        }
        return false;
    }

    private static boolean H(String str) {
        if (m(str)) {
            return false;
        }
        return Pattern.matches("^(13|14|15|17|18)\\d{9}$", str);
    }

    public static String a(byte b2) {
        return f(new byte[]{b2});
    }

    public static String b(String str, char c2, int i2) {
        if (str.length() >= i2) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        while (stringBuffer.length() < i2) {
            stringBuffer.append(c2);
        }
        return stringBuffer.toString();
    }

    public static String c(String str, String str2, String str3) {
        return a(str, 0, str2, str3);
    }

    public static byte[] d(byte[] bArr) {
        return b(bArr, (byte[]) null, "MD5");
    }

    public static byte[] e(byte[] bArr) {
        return b(bArr, (byte[]) null, d);
    }

    public static String f(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static String g(String str) {
        return a(str, "GBK", "UTF-8");
    }

    public static int h(String str) {
        if (m(str)) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            i2 = (charAt >= '\u0100' && (charAt < '\uff61' || charAt > '\uff9f') && ((charAt < '\uffa0' || charAt > '\uffbe') && ((charAt < '\uffc2' || charAt > '\uffc7') && ((charAt < '\uffca' || charAt > '\uffcf') && ((charAt < '\uffd2' || charAt > '\uffd7') && ((charAt < '\uffda' || charAt > '\uffdc') && ('\uffe8' > charAt || charAt > '\uffee'))))))) ? i2 + 2 : i2 + 1;
        }
        return i2;
    }

    public static byte[] i(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.replace(0, 2, str.substring(6, 8)).replace(2, 4, str.substring(4, 6)).replace(4, 6, str.substring(2, 4)).replace(6, 8, str.substring(0, 2));
        stringBuffer.replace(9, 11, str.substring(11, 13)).replace(11, 13, str.substring(9, 11));
        stringBuffer.replace(14, 16, str.substring(16, 18)).replace(16, 18, str.substring(14, 16));
        return j(stringBuffer.toString().replace("-", ""));
    }

    public static byte[] j(String str) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (m(str) || str.length() % 2 > 0) {
            return null;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i6 + 1;
            char charAt = str.charAt(i6);
            if (charAt < '0' || charAt > '9') {
                if (charAt < 'a' || charAt > 'f') {
                    if (charAt >= 'A' && charAt <= 'F') {
                        i2 = charAt - 'A';
                    }
                    return null;
                }
                i2 = charAt - 'a';
                i3 = i2 + 10;
            } else {
                i3 = charAt - '0';
            }
            bArr[i7] = (byte) (i3 << 4);
            i6 = i8 + 1;
            char charAt2 = str.charAt(i8);
            if (charAt2 < '0' || charAt2 > '9') {
                if (charAt2 < 'a' || charAt2 > 'f') {
                    if (charAt2 >= 'A' && charAt2 <= 'F') {
                        i4 = charAt2 - 'A';
                    }
                    return null;
                }
                i4 = charAt2 - 'a';
                i5 = i4 + 10;
            } else {
                i5 = charAt2 - '0';
            }
            bArr[i7] = (byte) (bArr[i7] + ((byte) i5));
        }
        return bArr;
    }

    public static boolean k(String str) {
        if (m(str)) {
            return false;
        }
        return Pattern.matches("^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$", str);
    }

    public static boolean l(String str) {
        if (m(str)) {
            return false;
        }
        return Pattern.matches(RegexConstants.REGEX_EMAIL, str);
    }

    public static boolean m(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean n(String str) {
        if (m(str)) {
            return false;
        }
        return Pattern.matches("(.*)\\.(jpeg|jpg|bmp|gif|png)$", str);
    }

    public static boolean o(String str) {
        if (m(str)) {
            return false;
        }
        if (str.startsWith("{") || str.startsWith("[") || str.equals(DYConstants.DY_TRUE) || str.equals(DYConstants.DY_FALSE) || str.equals(DYConstants.DY_NULL_STR)) {
            return true;
        }
        return q(str);
    }

    public static boolean p(String str) {
        if (m(str)) {
            return false;
        }
        return H(str) || F(str) || G(str);
    }

    public static boolean q(String str) {
        if (m(str)) {
            return false;
        }
        return Pattern.matches("^[-]*[0-9\\.]+$", str);
    }

    public static boolean r(String str) {
        if (m(str)) {
            return false;
        }
        return Pattern.matches("[^u4e00-u9fa5]+$", str);
    }

    public static boolean s(String str) {
        if (m(str)) {
            return false;
        }
        return Pattern.matches("^[a-zA-Z\\ \\']+$", str);
    }

    public static boolean t(String str) {
        if (m(str)) {
            return false;
        }
        return Pattern.matches("^[0-9\\-\\(\\)\\ ]+$", str);
    }

    public static boolean u(String str) {
        if (m(str)) {
            return false;
        }
        boolean matches = Pattern.matches("^(https|http|ftp|rtsp|mms)?:\\/\\/[^\\s]*$", str);
        return !matches ? Pattern.matches("^[\\.\\/\\?#a-zA-Z0-9-_=&;,%]*$", str) : matches;
    }

    public static String v(String str) {
        return a(str, CharEncoding.ISO_8859_1, "GBK");
    }

    public static String w(String str) {
        return a(str, CharEncoding.ISO_8859_1, "UTF-8");
    }

    public static String x(String str) {
        return c(str, (String) null);
    }

    public static String y(String str) {
        return str != null ? Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("") : "";
    }

    public static String z(String str) {
        return e(str, null);
    }

    public static String a(byte[] bArr, int i2, int i3) {
        char[] cArr = new char[i3 * 2];
        int i4 = 0;
        while (i2 < i3) {
            int i5 = i4 + 1;
            char[] cArr2 = f18354h;
            cArr[i4] = cArr2[(bArr[i2] & 255) >> 4];
            i4 = i5 + 1;
            cArr[i5] = cArr2[(bArr[i2] & 255) % 16];
            i2++;
        }
        return new String(cArr);
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        try {
            return String.valueOf(Base64.encode(str.getBytes(b), 0));
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (char c2 : str.toCharArray()) {
            if (c2 != '\t') {
                if (c2 == '\n' || c2 == '\r') {
                    sb.append("\\u000");
                    sb.append(Integer.toHexString(c2).toUpperCase());
                } else if (c2 != '\"' && c2 != '\'') {
                    sb.append(c2);
                }
            }
            sb.append("\\u00");
            sb.append(Integer.toHexString(c2).toUpperCase());
        }
        return sb.toString();
    }

    public static String e(String str, String str2) {
        return b(str, str2, d);
    }

    public static String f(String str, String str2) {
        try {
            return f(str.getBytes(str2));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c(byte[] bArr) {
        return a(bArr, (byte[]) null, "MD5");
    }

    public static String e(String str) {
        return str.replace(ContainerUtils.FIELD_DELIMITER, "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\r\n", ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).replace(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "<br>").replace("\t", "    ").replace(LangUtils.SINGLE_SPACE, "&nbsp;").replace("\"", "&quot;").replace("'", "&#39;");
    }

    public static String f(String str) {
        return a(str, "GBK", CharEncoding.ISO_8859_1);
    }

    public static String c(String str, String str2) {
        return b(str, str2, "MD5");
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        return b(bArr, bArr2, d);
    }

    public static String a(String str, String str2, String str3) {
        if (m(str)) {
            return "";
        }
        try {
            return new String(str.getBytes(str2), str3);
        } catch (Exception unused) {
            return str;
        }
    }

    public static String b(int i2, int i3) {
        return a(String.valueOf(i2), '0', i3);
    }

    public static String b(int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(f18355i.charAt(a.nextInt(36)));
        }
        return sb.toString();
    }

    public static String a(String str, char c2, int i2) {
        if (str.length() >= i2) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (stringBuffer.length() < i2 - str.length()) {
            stringBuffer.append(c2);
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static byte[] b(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Base64.decode(str, 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String d(String str, String str2) {
        return str != null ? Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll(str2) : "";
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return b(bArr, bArr2, "MD5");
    }

    public static String b(String str, String str2, String str3) {
        if (m(str)) {
            return "";
        }
        try {
            return a(str.getBytes(b), m(str2) ? null : str2.getBytes(b), str3);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(long j2, int i2) {
        return a(String.valueOf(j2), '0', i2);
    }

    public static String a(String str, int i2) {
        return a(str, i2, "");
    }

    public static String a(String str, int i2, String str2) {
        if (m(str) || i2 <= 0) {
            return "";
        }
        if (i2 >= str.length()) {
            return str + str2;
        }
        return str.substring(0, i2) + str2;
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, String str) {
        byte[] digest;
        if (bArr != null && bArr.length != 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(str);
                messageDigest.update(bArr);
                if (bArr2 != null && bArr2.length != 0) {
                    digest = messageDigest.digest(bArr2);
                    messageDigest.reset();
                    return digest;
                }
                digest = messageDigest.digest();
                messageDigest.reset();
                return digest;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String a(String str, int i2, String str2, String str3) {
        if (m(str) || i2 >= str.length()) {
            return "";
        }
        int length = str.length();
        if (!m(str2)) {
            int indexOf = str.indexOf(str2, i2);
            if (indexOf < 0) {
                return "";
            }
            i2 = indexOf + str2.length();
        }
        if (!m(str3) && i2 < length && (length = str.indexOf(str3, i2)) < 0) {
            length = str.length();
        }
        return str.substring(i2, length);
    }

    public static String b(String str, String str2) {
        Exception e2;
        String str3;
        String str4 = "";
        try {
            str3 = f18356j.matcher(str).replaceAll(str2);
            try {
                str4 = f18357k.matcher(str3).replaceAll(str2);
                return f18359m.matcher(f18358l.matcher(str4).replaceAll(str2)).replaceAll(str2);
            } catch (Exception e3) {
                e2 = e3;
                e2.printStackTrace();
                return str3;
            }
        } catch (Exception e4) {
            e2 = e4;
            str3 = str4;
        }
    }

    public static String a(int i2, int i3) {
        return b(a.nextInt(i2), i3);
    }

    public static int a(int i2) {
        return a.nextInt(i2);
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return String.valueOf(Base64.encode(bArr, 0));
    }

    public static String b(byte[] bArr) {
        return a(bArr, 0);
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new String(b(str), b);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "MD5");
    }

    public static String a(byte[] bArr, byte[] bArr2, String str) {
        byte[] b2 = b(bArr, bArr2, str);
        return b2 == null ? "" : a(b2, 0, b2.length);
    }

    public static String a(String str, String str2) {
        String str3 = "";
        if (m(str)) {
            return "";
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(b), f18351e);
            Mac mac = Mac.getInstance(f18351e);
            mac.init(secretKeySpec);
            byte[] doFinal = mac.doFinal(str.getBytes(b));
            str3 = a(doFinal, 0, doFinal.length);
            mac.reset();
            return str3;
        } catch (Exception unused) {
            return str3;
        }
    }

    public static String a(byte[] bArr, int i2) {
        if (bArr == null || i2 < 0 || i2 + 16 > bArr.length) {
            return "";
        }
        String a2 = a(bArr, i2, 16);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a2.subSequence(6, 8));
        stringBuffer.append(a2.subSequence(4, 6));
        stringBuffer.append(a2.subSequence(2, 4));
        stringBuffer.append(a2.subSequence(0, 2));
        stringBuffer.append("-");
        stringBuffer.append(a2.subSequence(10, 12));
        stringBuffer.append(a2.subSequence(8, 10));
        stringBuffer.append("-");
        stringBuffer.append(a2.subSequence(14, 16));
        stringBuffer.append(a2.subSequence(12, 14));
        stringBuffer.append("-");
        stringBuffer.append(a2.subSequence(16, 20));
        stringBuffer.append("-");
        stringBuffer.append(a2.substring(20));
        return stringBuffer.toString().toUpperCase();
    }

    public static String a(long j2) {
        StringBuilder sb = new StringBuilder();
        sb.insert(0, j2 % 256).insert(0, OrderISVUtil.MONEY_DECIMAL);
        long j3 = j2 >> 8;
        sb.insert(0, j3 % 256).insert(0, OrderISVUtil.MONEY_DECIMAL);
        long j4 = j3 >> 8;
        sb.insert(0, j4 % 256).insert(0, OrderISVUtil.MONEY_DECIMAL);
        sb.insert(0, j4 >> 8);
        return sb.toString();
    }

    public static void a(String[] strArr) {
        System.out.println(y("just do\ti t!\r OK?"));
    }
}
