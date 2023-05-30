package cn.com.union.fido.util;

import android.util.Base64;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

/* loaded from: classes.dex */
public class StringTools {
    public static final String combine(String[] strArr, String str) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : strArr) {
            stringBuffer.append(str);
            stringBuffer.append(str2);
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.delete(0, str.length());
        }
        return stringBuffer.toString();
    }

    public static String[] getArg(String str, int i2, String str2) {
        String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 < i2 - 1) {
                strArr[i3] = str.substring(0, str.indexOf(str2));
                str = str.substring(str.indexOf(str2) + 1, str.length());
            } else {
                strArr[i3] = str;
            }
        }
        return strArr;
    }

    public static String getFormatStr(String str, int i2) {
        if (str == null || str.equals("")) {
            return "";
        }
        int indexOf = str.indexOf("E");
        if (indexOf < 0) {
            int indexOf2 = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
            return indexOf2 < 0 ? str : str.substring(0, indexOf2);
        }
        String substring = str.substring(0, indexOf);
        int indexOf3 = substring.indexOf(OrderISVUtil.MONEY_DECIMAL);
        String str2 = substring.substring(0, indexOf3) + substring.substring(indexOf3 + 1, substring.length());
        int length = str2.length();
        for (int i3 = 0; i3 < i2 - length; i3++) {
            str2 = str2 + "0";
        }
        return str2;
    }

    public static Vector getMultiRow(String str, int i2) {
        Vector vector = new Vector();
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        if (length <= i2) {
            vector.add(str);
            return vector;
        }
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + i2 > length ? length - i3 : i2;
            byte[] bArr = new byte[i4];
            System.arraycopy(bytes, i3, bArr, 0, i4);
            int i5 = 0;
            for (int i6 = 0; i6 < i4; i6++) {
                if (bArr[i6] < 0) {
                    i5++;
                }
            }
            if (i5 % 2 == 1) {
                i4--;
            }
            vector.add(new String(bytes, i3, i4));
            i3 += i4;
        }
        return vector;
    }

    public static String[] getNameArg(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        int countTokens = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            strArr[i2] = stringTokenizer.nextToken();
        }
        return strArr;
    }

    public static String[][] getNameArg2(String str) {
        String[] nameArg = getNameArg(str);
        int length = nameArg.length;
        int countTokens = new StringTokenizer(nameArg[0], DYConstants.DY_REGEX_COMMA).countTokens();
        String[][] strArr = (String[][]) Array.newInstance(String.class, length, countTokens);
        for (int i2 = 0; i2 < length; i2++) {
            StringTokenizer stringTokenizer = new StringTokenizer(nameArg[i2], DYConstants.DY_REGEX_COMMA);
            for (int i3 = 0; i3 < countTokens; i3++) {
                strArr[i2][i3] = stringTokenizer.nextToken();
            }
        }
        return strArr;
    }

    public static String[][] getNameArg3(String str, String str2, String str3) {
        String[] splitStrFlag = splitStrFlag(str, str2);
        int length = splitStrFlag.length;
        int countTokens = new StringTokenizer(splitStrFlag[0], str3).countTokens();
        String[][] strArr = (String[][]) Array.newInstance(String.class, length, countTokens);
        for (int i2 = 0; i2 < length; i2++) {
            StringTokenizer stringTokenizer = new StringTokenizer(splitStrFlag[i2], str3);
            for (int i3 = 0; i3 < countTokens; i3++) {
                strArr[i2][i3] = stringTokenizer.nextToken();
            }
        }
        return strArr;
    }

    public static final boolean isBlankString(String str) {
        return str == null || str.length() <= 0;
    }

    public static final boolean isDoubleByte(char c2) {
        return (c2 >>> '\b') == 0;
    }

    public static boolean isHexNumberRex(String str) {
        return str.matches("(?i)[0-9a-f]+");
    }

    public static final boolean isValidateString(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static final String lPad(String str, int i2, String str2) {
        StringBuffer stringBuffer = new StringBuffer(i2);
        int length = i2 - str.length();
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            stringBuffer.append(str2);
        }
        stringBuffer.append(str);
        int length2 = stringBuffer.length() - i2;
        return length2 > 0 ? stringBuffer.substring(length2) : stringBuffer.toString();
    }

    public static final String lReplace(String str, String str2, String str3) {
        if (str != null) {
            return str.replaceAll("^(" + str2 + ")+", str3);
        }
        return str;
    }

    public static final String lTrim(String str) {
        return lTrim(str, "");
    }

    public static final String lTrim(String str, String str2) {
        return str != null ? str.replaceAll("^[\\s]+", str2) : str;
    }

    public static int length(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return new String(str.getBytes("GBK"), "8859_1").length();
        } catch (UnsupportedEncodingException unused) {
            return -1;
        }
    }

    public static String objectToString(Object obj) {
        return obj != null ? obj.toString() : new String();
    }

    public static final String rPad(String str, int i2, String str2) {
        StringBuffer stringBuffer = new StringBuffer(i2);
        stringBuffer.append(str);
        int length = i2 - str.length();
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            stringBuffer.append(str2);
        }
        return stringBuffer.length() - i2 > 0 ? stringBuffer.substring(0, i2) : stringBuffer.toString();
    }

    public static final String rReplace(String str, String str2, String str3) {
        if (str != null) {
            return str.replaceAll("(" + str2 + ")+$", str3);
        }
        return str;
    }

    public static final String rTrim(String str) {
        return rTrim(str, "");
    }

    public static final String rTrim(String str, String str2) {
        return str != null ? str.replaceAll("[\\s]+$", str2) : str;
    }

    public static final StringBuffer replace(StringBuffer stringBuffer, String str, String str2) {
        if (stringBuffer != null && stringBuffer.length() > 0 && !isBlankString(str)) {
            int indexOf = stringBuffer.indexOf(str);
            int length = str.length();
            while (indexOf >= 0) {
                stringBuffer.replace(indexOf, length, str2);
            }
        }
        return stringBuffer;
    }

    public static final String replaceIgnoreCase(String str, String str2, String str3) {
        if (str == null || isBlankString(str2)) {
            return str;
        }
        String lowerCase = str.toLowerCase();
        String lowerCase2 = str2.toLowerCase();
        int i2 = 0;
        int indexOf = lowerCase.indexOf(lowerCase2, 0);
        int length = lowerCase2.length();
        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer(str.length());
        while (indexOf >= 0) {
            stringBuffer.append(charArray, i2, indexOf - i2);
            stringBuffer.append(str3);
            i2 = indexOf + length;
            indexOf = lowerCase.indexOf(lowerCase2, i2);
        }
        stringBuffer.append(charArray, i2, charArray.length - i2);
        return stringBuffer.toString();
    }

    public static String replaceStr(String str, String str2, String str3) {
        StringBuilder sb;
        int lastIndexOf = str.lastIndexOf(str2);
        while (lastIndexOf >= 0) {
            if (lastIndexOf == 0) {
                sb = new StringBuilder();
            } else {
                sb = new StringBuilder();
                sb.append(str.substring(0, lastIndexOf));
            }
            sb.append(str3);
            sb.append(str.substring(lastIndexOf + 1, str.length()));
            str = sb.toString();
            lastIndexOf = str.lastIndexOf(str2, lastIndexOf - 1);
        }
        return str;
    }

    public static String[] splitStrFlag(String str, String str2) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        int countTokens = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            strArr[i2] = stringTokenizer.nextToken();
        }
        return strArr;
    }

    public static boolean stringEqual(String str, String str2) {
        return isValidateString(str) && str.equals(str2);
    }

    public static boolean stringNullorEqual(String str, String str2) {
        return str.equals(str2) || str.isEmpty() || str.equals(null) || str == "";
    }

    public static final String subString(String str, int i2) {
        String validateString = toValidateString(str);
        return validateString.length() >= i2 ? validateString.substring(0, i2) : validateString;
    }

    public static String subStringByByte(String str, int i2) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            if (bytes.length <= i2) {
                return str;
            }
            if (i2 > 0) {
                String str2 = new String(bytes, 0, i2);
                int length = str2.length();
                int i3 = length - 1;
                if (str.charAt(i3) == str2.charAt(i3)) {
                    return str2;
                }
                if (length >= 2) {
                    return str2.substring(0, i3);
                }
            }
        }
        return null;
    }

    public static final String toHtml(String str) {
        String str2;
        if (isBlankString(str)) {
            return "";
        }
        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer(charArray.length);
        for (char c2 : charArray) {
            if (c2 == '\n') {
                str2 = "<br />";
            } else if (c2 == '\t') {
                str2 = "&nbsp;&nbsp;&nbsp;&nbsp;";
            } else if (c2 == ' ') {
                str2 = "&nbsp;";
            } else {
                stringBuffer.append(c2);
            }
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }

    public static final ArrayList toList(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                arrayList.add(toValidateString(str));
            }
        }
        return arrayList;
    }

    public static final String toTitleCase(String str) {
        if (str == null || str.length() <= 0) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length());
        StringBuffer stringBuffer2 = new StringBuffer();
        StringTokenizer stringTokenizer = new StringTokenizer(str, LangUtils.SINGLE_SPACE);
        while (stringTokenizer.hasMoreTokens()) {
            stringBuffer2.append(stringTokenizer.nextToken());
            stringBuffer2.setCharAt(0, Character.toUpperCase(stringBuffer2.charAt(0)));
            stringBuffer.append(stringBuffer2.toString());
            stringBuffer.append(' ');
            stringBuffer2.delete(0, stringBuffer2.length());
        }
        return stringBuffer.toString();
    }

    public static final String toToggleCase(String str) {
        if (str == null || str.length() <= 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            char c2 = charArray[i2];
            charArray[i2] = Character.isUpperCase(c2) ? Character.toLowerCase(c2) : Character.toUpperCase(c2);
        }
        return new String(charArray);
    }

    public static final String toValidateString(String str) {
        return str != null ? str.trim() : "";
    }

    public static final String toValidateString(String str, String str2) {
        return str != null ? str.trim() : str2;
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    public static final String trim(String str, String str2, String str3) {
        return str.replaceAll("(" + str2 + ")+", str3);
    }

    public static byte[] urlSafeBase64Dec(String str) {
        try {
            return Base64.decode(str, 11);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] urlSafeBase64Dec4Byte(byte[] bArr) {
        try {
            return Base64.decode(bArr, 11);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String urlSafeBase64Enc(String str) {
        try {
            return Base64.encodeToString(str.getBytes(), 11);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String urlSafeBase64Enc(byte[] bArr) {
        try {
            return Base64.encodeToString(bArr, 11);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] urlSafeBase64Enc2Byte(byte[] bArr) {
        try {
            return Base64.encode(bArr, 11);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
