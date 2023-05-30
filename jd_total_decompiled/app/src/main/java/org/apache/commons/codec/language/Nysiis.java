package org.apache.commons.codec.language;

import com.jingdong.jdsdk.auraSetting.AuraConstants;
import java.util.regex.Pattern;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* loaded from: classes11.dex */
public class Nysiis implements StringEncoder {
    private static final char SPACE = ' ';
    private static final int TRUE_LENGTH = 6;
    private final boolean strict;
    private static final char[] CHARS_A = {'A'};
    private static final char[] CHARS_AF = {'A', 'F'};
    private static final char[] CHARS_C = {'C'};
    private static final char[] CHARS_FF = {'F', 'F'};
    private static final char[] CHARS_G = {'G'};
    private static final char[] CHARS_N = {'N'};
    private static final char[] CHARS_NN = {'N', 'N'};
    private static final char[] CHARS_S = {'S'};
    private static final char[] CHARS_SSS = {'S', 'S', 'S'};
    private static final Pattern PAT_MAC = Pattern.compile("^MAC");
    private static final Pattern PAT_KN = Pattern.compile("^KN");
    private static final Pattern PAT_K = Pattern.compile("^K");
    private static final Pattern PAT_PH_PF = Pattern.compile("^(PH|PF)");
    private static final Pattern PAT_SCH = Pattern.compile("^SCH");
    private static final Pattern PAT_EE_IE = Pattern.compile("(EE|IE)$");
    private static final Pattern PAT_DT_ETC = Pattern.compile("(DT|RT|RD|NT|ND)$");

    public Nysiis() {
        this(true);
    }

    private static boolean isVowel(char c2) {
        return c2 == 'A' || c2 == 'E' || c2 == 'I' || c2 == 'O' || c2 == 'U';
    }

    private static char[] transcodeRemaining(char c2, char c3, char c4, char c5) {
        if (c3 == 'E' && c4 == 'V') {
            return CHARS_AF;
        }
        if (isVowel(c3)) {
            return CHARS_A;
        }
        if (c3 == 'Q') {
            return CHARS_G;
        }
        if (c3 == 'Z') {
            return CHARS_S;
        }
        if (c3 == 'M') {
            return CHARS_N;
        }
        if (c3 == 'K') {
            if (c4 == 'N') {
                return CHARS_NN;
            }
            return CHARS_C;
        } else if (c3 == 'S' && c4 == 'C' && c5 == 'H') {
            return CHARS_SSS;
        } else {
            if (c3 == 'P' && c4 == 'H') {
                return CHARS_FF;
            }
            return (c3 != 'H' || (isVowel(c2) && isVowel(c4))) ? (c3 == 'W' && isVowel(c2)) ? new char[]{c2} : new char[]{c3} : new char[]{c2};
        }
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return nysiis((String) obj);
        }
        throw new EncoderException("Parameter supplied to Nysiis encode is not of type java.lang.String");
    }

    public boolean isStrict() {
        return this.strict;
    }

    public String nysiis(String str) {
        if (str == null) {
            return null;
        }
        String clean = SoundexUtils.clean(str);
        if (clean.length() == 0) {
            return clean;
        }
        String replaceFirst = PAT_DT_ETC.matcher(PAT_EE_IE.matcher(PAT_SCH.matcher(PAT_PH_PF.matcher(PAT_K.matcher(PAT_KN.matcher(PAT_MAC.matcher(clean).replaceFirst("MCC")).replaceFirst("NN")).replaceFirst("C")).replaceFirst("FF")).replaceFirst("SSS")).replaceFirst("Y")).replaceFirst(AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE);
        StringBuilder sb = new StringBuilder(replaceFirst.length());
        sb.append(replaceFirst.charAt(0));
        char[] charArray = replaceFirst.toCharArray();
        int length = charArray.length;
        int i2 = 1;
        while (i2 < length) {
            int i3 = length - 1;
            char c2 = SPACE;
            char c3 = i2 < i3 ? charArray[i2 + 1] : SPACE;
            if (i2 < length - 2) {
                c2 = charArray[i2 + 2];
            }
            int i4 = i2 - 1;
            char[] transcodeRemaining = transcodeRemaining(charArray[i4], charArray[i2], c3, c2);
            System.arraycopy(transcodeRemaining, 0, charArray, i2, transcodeRemaining.length);
            if (charArray[i2] != charArray[i4]) {
                sb.append(charArray[i2]);
            }
            i2++;
        }
        if (sb.length() > 1) {
            char charAt = sb.charAt(sb.length() - 1);
            if (charAt == 'S') {
                sb.deleteCharAt(sb.length() - 1);
                charAt = sb.charAt(sb.length() - 1);
            }
            if (sb.length() > 2 && sb.charAt(sb.length() - 2) == 'A' && charAt == 'Y') {
                sb.deleteCharAt(sb.length() - 2);
            }
            if (charAt == 'A') {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        String sb2 = sb.toString();
        return isStrict() ? sb2.substring(0, Math.min(6, sb2.length())) : sb2;
    }

    public Nysiis(boolean z) {
        this.strict = z;
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return nysiis(str);
    }
}
