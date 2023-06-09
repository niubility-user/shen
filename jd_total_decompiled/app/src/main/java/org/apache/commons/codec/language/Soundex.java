package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* loaded from: classes11.dex */
public class Soundex implements StringEncoder {
    @Deprecated
    private int maxLength;
    private final char[] soundexMapping;
    public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";
    private static final char[] US_ENGLISH_MAPPING = US_ENGLISH_MAPPING_STRING.toCharArray();
    public static final Soundex US_ENGLISH = new Soundex();

    public Soundex() {
        this.maxLength = 4;
        this.soundexMapping = US_ENGLISH_MAPPING;
    }

    private char getMappingCode(String str, int i2) {
        char charAt;
        char map = map(str.charAt(i2));
        if (i2 > 1 && map != '0' && ('H' == (charAt = str.charAt(i2 - 1)) || 'W' == charAt)) {
            char charAt2 = str.charAt(i2 - 2);
            if (map(charAt2) == map || 'H' == charAt2 || 'W' == charAt2) {
                return (char) 0;
            }
        }
        return map;
    }

    private char[] getSoundexMapping() {
        return this.soundexMapping;
    }

    private char map(char c2) {
        int i2 = c2 - 'A';
        if (i2 >= 0 && i2 < getSoundexMapping().length) {
            return getSoundexMapping()[i2];
        }
        throw new IllegalArgumentException("The character is not mapped: " + c2);
    }

    public int difference(String str, String str2) throws EncoderException {
        return SoundexUtils.difference(this, str, str2);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return soundex((String) obj);
        }
        throw new EncoderException("Parameter supplied to Soundex encode is not of type java.lang.String");
    }

    @Deprecated
    public int getMaxLength() {
        return this.maxLength;
    }

    @Deprecated
    public void setMaxLength(int i2) {
        this.maxLength = i2;
    }

    public String soundex(String str) {
        if (str == null) {
            return null;
        }
        String clean = SoundexUtils.clean(str);
        if (clean.length() == 0) {
            return clean;
        }
        char[] cArr = {'0', '0', '0', '0'};
        cArr[0] = clean.charAt(0);
        char mappingCode = getMappingCode(clean, 0);
        int i2 = 1;
        int i3 = 1;
        while (i2 < clean.length() && i3 < 4) {
            int i4 = i2 + 1;
            char mappingCode2 = getMappingCode(clean, i2);
            if (mappingCode2 != 0) {
                if (mappingCode2 != '0' && mappingCode2 != mappingCode) {
                    cArr[i3] = mappingCode2;
                    i3++;
                }
                mappingCode = mappingCode2;
            }
            i2 = i4;
        }
        return new String(cArr);
    }

    public Soundex(char[] cArr) {
        this.maxLength = 4;
        char[] cArr2 = new char[cArr.length];
        this.soundexMapping = cArr2;
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return soundex(str);
    }

    public Soundex(String str) {
        this.maxLength = 4;
        this.soundexMapping = str.toCharArray();
    }
}
