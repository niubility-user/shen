package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* loaded from: classes11.dex */
public class Metaphone implements StringEncoder {
    private static final String FRONTV = "EIY";
    private static final String VARSON = "CSPTG";
    private static final String VOWELS = "AEIOU";
    private int maxCodeLen = 4;

    private boolean isLastChar(int i2, int i3) {
        return i3 + 1 == i2;
    }

    private boolean isNextChar(StringBuilder sb, int i2, char c2) {
        return i2 >= 0 && i2 < sb.length() - 1 && sb.charAt(i2 + 1) == c2;
    }

    private boolean isPreviousChar(StringBuilder sb, int i2, char c2) {
        return i2 > 0 && i2 < sb.length() && sb.charAt(i2 - 1) == c2;
    }

    private boolean isVowel(StringBuilder sb, int i2) {
        return VOWELS.indexOf(sb.charAt(i2)) >= 0;
    }

    private boolean regionMatch(StringBuilder sb, int i2, String str) {
        if (i2 < 0 || (str.length() + i2) - 1 >= sb.length()) {
            return false;
        }
        return sb.substring(i2, str.length() + i2).equals(str);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return metaphone((String) obj);
        }
        throw new EncoderException("Parameter supplied to Metaphone encode is not of type java.lang.String");
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public boolean isMetaphoneEqual(String str, String str2) {
        return metaphone(str).equals(metaphone(str2));
    }

    public String metaphone(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return "";
        }
        if (length == 1) {
            return str.toUpperCase(Locale.ENGLISH);
        }
        char[] charArray = str.toUpperCase(Locale.ENGLISH).toCharArray();
        StringBuilder sb = new StringBuilder(40);
        StringBuilder sb2 = new StringBuilder(10);
        int i2 = 0;
        char c2 = charArray[0];
        if (c2 != 'A') {
            if (c2 == 'G' || c2 == 'K' || c2 == 'P') {
                if (charArray[1] == 'N') {
                    sb.append(charArray, 1, charArray.length - 1);
                } else {
                    sb.append(charArray);
                }
            } else if (c2 != 'W') {
                if (c2 != 'X') {
                    sb.append(charArray);
                } else {
                    charArray[0] = 'S';
                    sb.append(charArray);
                }
            } else if (charArray[1] == 'R') {
                sb.append(charArray, 1, charArray.length - 1);
            } else if (charArray[1] == 'H') {
                sb.append(charArray, 1, charArray.length - 1);
                sb.setCharAt(0, 'W');
            } else {
                sb.append(charArray);
            }
        } else if (charArray[1] == 'E') {
            sb.append(charArray, 1, charArray.length - 1);
        } else {
            sb.append(charArray);
        }
        int length2 = sb.length();
        while (sb2.length() < getMaxCodeLen() && i2 < length2) {
            char charAt = sb.charAt(i2);
            if (charAt == 'C' || !isPreviousChar(sb, i2, charAt)) {
                switch (charAt) {
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                        if (i2 == 0) {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'B':
                        if (!isPreviousChar(sb, i2, 'M') || !isLastChar(length2, i2)) {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'C':
                        if (!isPreviousChar(sb, i2, 'S') || isLastChar(length2, i2) || FRONTV.indexOf(sb.charAt(i2 + 1)) < 0) {
                            if (regionMatch(sb, i2, "CIA")) {
                                sb2.append('X');
                                break;
                            } else if (!isLastChar(length2, i2) && FRONTV.indexOf(sb.charAt(i2 + 1)) >= 0) {
                                sb2.append('S');
                                break;
                            } else if (isPreviousChar(sb, i2, 'S') && isNextChar(sb, i2, 'H')) {
                                sb2.append('K');
                                break;
                            } else if (isNextChar(sb, i2, 'H')) {
                                if (i2 == 0 && length2 >= 3 && isVowel(sb, 2)) {
                                    sb2.append('K');
                                    break;
                                } else {
                                    sb2.append('X');
                                    break;
                                }
                            } else {
                                sb2.append('K');
                                break;
                            }
                        }
                        break;
                    case 'D':
                        if (!isLastChar(length2, i2 + 1) && isNextChar(sb, i2, 'G')) {
                            int i3 = i2 + 2;
                            if (FRONTV.indexOf(sb.charAt(i3)) >= 0) {
                                sb2.append('J');
                                i2 = i3;
                                break;
                            }
                        }
                        sb2.append('T');
                        break;
                    case 'F':
                    case 'J':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'R':
                        sb2.append(charAt);
                        break;
                    case 'G':
                        int i4 = i2 + 1;
                        if ((!isLastChar(length2, i4) || !isNextChar(sb, i2, 'H')) && ((isLastChar(length2, i4) || !isNextChar(sb, i2, 'H') || isVowel(sb, i2 + 2)) && (i2 <= 0 || (!regionMatch(sb, i2, "GN") && !regionMatch(sb, i2, "GNED"))))) {
                            boolean isPreviousChar = isPreviousChar(sb, i2, 'G');
                            if (!isLastChar(length2, i2) && FRONTV.indexOf(sb.charAt(i4)) >= 0 && !isPreviousChar) {
                                sb2.append('J');
                                break;
                            } else {
                                sb2.append('K');
                                break;
                            }
                        }
                        break;
                    case 'H':
                        if (!isLastChar(length2, i2) && ((i2 <= 0 || VARSON.indexOf(sb.charAt(i2 - 1)) < 0) && isVowel(sb, i2 + 1))) {
                            sb2.append('H');
                            break;
                        }
                        break;
                    case 'K':
                        if (i2 > 0) {
                            if (!isPreviousChar(sb, i2, 'C')) {
                                sb2.append(charAt);
                                break;
                            }
                        } else {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'P':
                        if (isNextChar(sb, i2, 'H')) {
                            sb2.append('F');
                            break;
                        } else {
                            sb2.append(charAt);
                            break;
                        }
                    case 'Q':
                        sb2.append('K');
                        break;
                    case 'S':
                        if (!regionMatch(sb, i2, "SH") && !regionMatch(sb, i2, "SIO") && !regionMatch(sb, i2, "SIA")) {
                            sb2.append('S');
                            break;
                        } else {
                            sb2.append('X');
                            break;
                        }
                        break;
                    case 'T':
                        if (!regionMatch(sb, i2, "TIA") && !regionMatch(sb, i2, "TIO")) {
                            if (!regionMatch(sb, i2, "TCH")) {
                                if (regionMatch(sb, i2, "TH")) {
                                    sb2.append('0');
                                    break;
                                } else {
                                    sb2.append('T');
                                    break;
                                }
                            }
                        } else {
                            sb2.append('X');
                            break;
                        }
                        break;
                    case 'V':
                        sb2.append('F');
                        break;
                    case 'W':
                    case 'Y':
                        if (!isLastChar(length2, i2) && isVowel(sb, i2 + 1)) {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'X':
                        sb2.append('K');
                        sb2.append('S');
                        break;
                    case 'Z':
                        sb2.append('S');
                        break;
                }
                i2++;
            } else {
                i2++;
            }
            if (sb2.length() > getMaxCodeLen()) {
                sb2.setLength(getMaxCodeLen());
            }
        }
        return sb2.toString();
    }

    public void setMaxCodeLen(int i2) {
        this.maxCodeLen = i2;
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return metaphone(str);
    }
}
