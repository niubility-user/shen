package org.apache.commons.codec.language;

import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.tencent.connect.common.Constants;
import com.tencent.mapsdk.internal.la;
import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* loaded from: classes11.dex */
public class MatchRatingApproachEncoder implements StringEncoder {
    private static final String[] DOUBLE_CONSONANT = {"BB", "CC", "DD", "FF", "GG", "HH", "JJ", "KK", "LL", "MM", "NN", "PP", Constants.SOURCE_QQ, "RR", "SS", la.d, "VV", "WW", "XX", "YY", "ZZ"};
    private static final int EIGHT = 8;
    private static final int ELEVEN = 11;
    private static final String EMPTY = "";
    private static final int FIVE = 5;
    private static final int FOUR = 4;
    private static final int ONE = 1;
    private static final String PLAIN_ASCII = "AaEeIiOoUuAaEeIiOoUuYyAaEeIiOoUuYyAaOoNnAaEeIiOoUuYyAaCcOoUu";
    private static final int SEVEN = 7;
    private static final int SIX = 6;
    private static final String SPACE = " ";
    private static final int THREE = 3;
    private static final int TWELVE = 12;
    private static final int TWO = 2;
    private static final String UNICODE = "\u00c0\u00e0\u00c8\u00e8\u00cc\u00ec\u00d2\u00f2\u00d9\u00f9\u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00dd\u00fd\u00c2\u00e2\u00ca\u00ea\u00ce\u00ee\u00d4\u00f4\u00db\u00fb\u0176\u0177\u00c3\u00e3\u00d5\u00f5\u00d1\u00f1\u00c4\u00e4\u00cb\u00eb\u00cf\u00ef\u00d6\u00f6\u00dc\u00fc\u0178\u00ff\u00c5\u00e5\u00c7\u00e7\u0150\u0151\u0170\u0171";

    String cleanName(String str) {
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        String[] strArr = {"\\-", "[&]", "\\'", "\\.", "[\\,]"};
        for (int i2 = 0; i2 < 5; i2++) {
            upperCase = upperCase.replaceAll(strArr[i2], "");
        }
        return removeAccents(upperCase).replaceAll("\\s+", "");
    }

    @Override // org.apache.commons.codec.Encoder
    public final Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("Parameter supplied to Match Rating Approach encoder is not of type java.lang.String");
    }

    String getFirst3Last3(String str) {
        int length = str.length();
        if (length > 6) {
            return str.substring(0, 3) + str.substring(length - 3, length);
        }
        return str;
    }

    int getMinRating(int i2) {
        if (i2 <= 4) {
            return 5;
        }
        if (i2 < 5 || i2 > 7) {
            if (i2 < 8 || i2 > 11) {
                return i2 == 12 ? 2 : 1;
            }
            return 3;
        }
        return 4;
    }

    public boolean isEncodeEquals(String str, String str2) {
        if (str == null || "".equalsIgnoreCase(str) || " ".equalsIgnoreCase(str) || str2 == null || "".equalsIgnoreCase(str2) || " ".equalsIgnoreCase(str2) || str.length() == 1 || str2.length() == 1) {
            return false;
        }
        if (str.equalsIgnoreCase(str2)) {
            return true;
        }
        String cleanName = cleanName(str);
        String cleanName2 = cleanName(str2);
        String removeVowels = removeVowels(cleanName);
        String removeVowels2 = removeVowels(cleanName2);
        String removeDoubleConsonants = removeDoubleConsonants(removeVowels);
        String removeDoubleConsonants2 = removeDoubleConsonants(removeVowels2);
        String first3Last3 = getFirst3Last3(removeDoubleConsonants);
        String first3Last32 = getFirst3Last3(removeDoubleConsonants2);
        if (Math.abs(first3Last3.length() - first3Last32.length()) >= 3) {
            return false;
        }
        return leftToRightThenRightToLeftProcessing(first3Last3, first3Last32) >= getMinRating(Math.abs(first3Last3.length() + first3Last32.length()));
    }

    boolean isVowel(String str) {
        return str.equalsIgnoreCase("E") || str.equalsIgnoreCase("A") || str.equalsIgnoreCase(IShareAdapter.SHARE_ACTION_OPEN) || str.equalsIgnoreCase("I") || str.equalsIgnoreCase("U");
    }

    int leftToRightThenRightToLeftProcessing(String str, String str2) {
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int length = str.length() - 1;
        int length2 = str2.length() - 1;
        int i2 = 0;
        while (i2 < charArray.length && i2 <= length2) {
            int i3 = i2 + 1;
            String substring = str.substring(i2, i3);
            int i4 = length - i2;
            String substring2 = str.substring(i4, i4 + 1);
            String substring3 = str2.substring(i2, i3);
            int i5 = length2 - i2;
            String substring4 = str2.substring(i5, i5 + 1);
            if (substring.equals(substring3)) {
                charArray[i2] = ' ';
                charArray2[i2] = ' ';
            }
            if (substring2.equals(substring4)) {
                charArray[i4] = ' ';
                charArray2[i5] = ' ';
            }
            i2 = i3;
        }
        String replaceAll = new String(charArray).replaceAll("\\s+", "");
        String replaceAll2 = new String(charArray2).replaceAll("\\s+", "");
        if (replaceAll.length() > replaceAll2.length()) {
            return Math.abs(6 - replaceAll.length());
        }
        return Math.abs(6 - replaceAll2.length());
    }

    String removeAccents(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            int indexOf = UNICODE.indexOf(charAt);
            if (indexOf > -1) {
                sb.append(PLAIN_ASCII.charAt(indexOf));
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    String removeDoubleConsonants(String str) {
        String upperCase = str.toUpperCase();
        for (String str2 : DOUBLE_CONSONANT) {
            if (upperCase.contains(str2)) {
                upperCase = upperCase.replace(str2, str2.substring(0, 1));
            }
        }
        return upperCase;
    }

    String removeVowels(String str) {
        String substring = str.substring(0, 1);
        String replaceAll = str.replaceAll("A", "").replaceAll("E", "").replaceAll("I", "").replaceAll(IShareAdapter.SHARE_ACTION_OPEN, "").replaceAll("U", "").replaceAll("\\s{2,}\\b", " ");
        if (isVowel(substring)) {
            return substring + replaceAll;
        }
        return replaceAll;
    }

    @Override // org.apache.commons.codec.StringEncoder
    public final String encode(String str) {
        return (str == null || "".equalsIgnoreCase(str) || " ".equalsIgnoreCase(str) || str.length() == 1) ? "" : getFirst3Last3(removeDoubleConsonants(removeVowels(cleanName(str))));
    }
}
