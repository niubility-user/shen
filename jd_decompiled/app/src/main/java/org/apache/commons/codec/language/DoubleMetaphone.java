package org.apache.commons.codec.language;

import com.jd.dynamic.DYConstants;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.tencent.mapsdk.internal.la;
import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;

/* loaded from: classes11.dex */
public class DoubleMetaphone implements StringEncoder {
    private static final String VOWELS = "AEIOUY";
    private int maxCodeLen = 4;
    private static final String[] SILENT_START = {"GN", "KN", "PN", "WR", "PS"};
    private static final String[] L_R_N_M_B_H_F_V_W_SPACE = {"L", "R", AuraConstants.MESSAGE_COUPON_TYPE_NEW, "M", "B", DYConstants.LETTER_H, "F", "V", "W", LangUtils.SINGLE_SPACE};
    private static final String[] ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER = {"ES", "EP", "EB", "EL", "EY", "IB", "IL", "IN", "IE", "EI", "ER"};
    private static final String[] L_T_K_S_N_M_B_Z = {"L", "T", "K", "S", AuraConstants.MESSAGE_COUPON_TYPE_NEW, "M", "B", "Z"};

    private String cleanInput(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        return trim.toUpperCase(Locale.ENGLISH);
    }

    private boolean conditionC0(String str, int i2) {
        if (contains(str, i2, 4, "CHIA")) {
            return true;
        }
        if (i2 <= 1) {
            return false;
        }
        int i3 = i2 - 2;
        if (!isVowel(charAt(str, i3)) && contains(str, i2 - 1, 3, "ACH")) {
            char charAt = charAt(str, i2 + 2);
            return !(charAt == 'I' || charAt == 'E') || contains(str, i3, 6, "BACHER", "MACHER");
        }
        return false;
    }

    private boolean conditionCH0(String str, int i2) {
        if (i2 != 0) {
            return false;
        }
        int i3 = i2 + 1;
        return (contains(str, i3, 5, "HARAC", "HARIS") || contains(str, i3, 3, "HOR", "HYM", "HIA", "HEM")) && !contains(str, 0, 5, "CHORE");
    }

    private boolean conditionCH1(String str, int i2) {
        if (!contains(str, 0, 4, "VAN ", "VON ") && !contains(str, 0, 3, "SCH") && !contains(str, i2 - 2, 6, "ORCHES", "ARCHIT", "ORCHID")) {
            int i3 = i2 + 2;
            if (!contains(str, i3, 1, "T", "S")) {
                if (!contains(str, i2 - 1, 1, "A", IShareAdapter.SHARE_ACTION_OPEN, "U", "E") && i2 != 0) {
                    return false;
                }
                if (!contains(str, i3, 1, L_R_N_M_B_H_F_V_W_SPACE) && i2 + 1 != str.length() - 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean conditionL0(String str, int i2) {
        if (i2 == str.length() - 3 && contains(str, i2 - 1, 4, "ILLO", "ILLA", "ALLE")) {
            return true;
        }
        return (contains(str, str.length() - 2, 2, "AS", "OS") || contains(str, str.length() - 1, 1, "A", IShareAdapter.SHARE_ACTION_OPEN)) && contains(str, i2 - 1, 4, "ALLE");
    }

    private boolean conditionM0(String str, int i2) {
        int i3 = i2 + 1;
        if (charAt(str, i3) == 'M') {
            return true;
        }
        return contains(str, i2 + (-1), 3, "UMB") && (i3 == str.length() - 1 || contains(str, i2 + 2, 2, "ER"));
    }

    protected static boolean contains(String str, int i2, int i3, String... strArr) {
        int i4;
        if (i2 < 0 || (i4 = i3 + i2) > str.length()) {
            return false;
        }
        String substring = str.substring(i2, i4);
        for (String str2 : strArr) {
            if (substring.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    private int handleAEIOUY(DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (i2 == 0) {
            doubleMetaphoneResult.append('A');
        }
        return i2 + 1;
    }

    private int handleC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (conditionC0(str, i2)) {
            doubleMetaphoneResult.append('K');
        } else if (i2 == 0 && contains(str, i2, 6, "CAESAR")) {
            doubleMetaphoneResult.append('S');
        } else if (contains(str, i2, 2, "CH")) {
            return handleCH(str, doubleMetaphoneResult, i2);
        } else {
            if (contains(str, i2, 2, "CZ") && !contains(str, i2 - 2, 4, "WICZ")) {
                doubleMetaphoneResult.append('S', 'X');
            } else {
                int i3 = i2 + 1;
                if (contains(str, i3, 3, "CIA")) {
                    doubleMetaphoneResult.append('X');
                } else if (contains(str, i2, 2, "CC") && (i2 != 1 || charAt(str, 0) != 'M')) {
                    return handleCC(str, doubleMetaphoneResult, i2);
                } else {
                    if (contains(str, i2, 2, "CK", "CG", "CQ")) {
                        doubleMetaphoneResult.append('K');
                    } else if (contains(str, i2, 2, "CI", "CE", "CY")) {
                        if (contains(str, i2, 3, "CIO", "CIE", "CIA")) {
                            doubleMetaphoneResult.append('S', 'X');
                        } else {
                            doubleMetaphoneResult.append('S');
                        }
                    } else {
                        doubleMetaphoneResult.append('K');
                        if (!contains(str, i3, 2, " C", " Q", " G")) {
                            if (!contains(str, i3, 1, "C", "K", "Q") || contains(str, i3, 2, "CE", "CI")) {
                                return i3;
                            }
                        }
                    }
                }
                return i2 + 3;
            }
        }
        return i2 + 2;
    }

    private int handleCC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        int i3 = i2 + 2;
        if (contains(str, i3, 1, "I", "E", DYConstants.LETTER_H) && !contains(str, i3, 2, "HU")) {
            if ((i2 == 1 && charAt(str, i2 - 1) == 'A') || contains(str, i2 - 1, 5, "UCCEE", "UCCES")) {
                doubleMetaphoneResult.append("KS");
            } else {
                doubleMetaphoneResult.append('X');
            }
            return i2 + 3;
        }
        doubleMetaphoneResult.append('K');
        return i3;
    }

    private int handleCH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (i2 > 0 && contains(str, i2, 4, "CHAE")) {
            doubleMetaphoneResult.append('K', 'X');
        } else if (conditionCH0(str, i2)) {
            doubleMetaphoneResult.append('K');
        } else if (!conditionCH1(str, i2)) {
            if (i2 > 0) {
                if (contains(str, 0, 2, la.q)) {
                    doubleMetaphoneResult.append('K');
                } else {
                    doubleMetaphoneResult.append('X', 'K');
                }
            } else {
                doubleMetaphoneResult.append('X');
            }
            return i2 + 2;
        } else {
            doubleMetaphoneResult.append('K');
        }
        return i2 + 2;
    }

    private int handleD(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (contains(str, i2, 2, "DG")) {
            int i3 = i2 + 2;
            if (contains(str, i3, 1, "I", "E", "Y")) {
                doubleMetaphoneResult.append('J');
                return i2 + 3;
            }
            doubleMetaphoneResult.append("TK");
            return i3;
        } else if (contains(str, i2, 2, "DT", "DD")) {
            doubleMetaphoneResult.append('T');
            return i2 + 2;
        } else {
            doubleMetaphoneResult.append('T');
            return i2 + 1;
        }
    }

    private int handleG(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        int i3 = i2 + 1;
        if (charAt(str, i3) == 'H') {
            return handleGH(str, doubleMetaphoneResult, i2);
        }
        if (charAt(str, i3) == 'N') {
            if (i2 == 1 && isVowel(charAt(str, 0)) && !z) {
                doubleMetaphoneResult.append("KN", AuraConstants.MESSAGE_COUPON_TYPE_NEW);
            } else if (!contains(str, i2 + 2, 2, "EY") && charAt(str, i3) != 'Y' && !z) {
                doubleMetaphoneResult.append(AuraConstants.MESSAGE_COUPON_TYPE_NEW, "KN");
            } else {
                doubleMetaphoneResult.append("KN");
            }
        } else if (contains(str, i3, 2, "LI") && !z) {
            doubleMetaphoneResult.append("KL", "L");
        } else if (i2 == 0 && (charAt(str, i3) == 'Y' || contains(str, i3, 2, ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER))) {
            doubleMetaphoneResult.append('K', 'J');
        } else {
            if ((contains(str, i3, 2, "ER") || charAt(str, i3) == 'Y') && !contains(str, 0, 6, "DANGER", "RANGER", "MANGER")) {
                int i4 = i2 - 1;
                if (!contains(str, i4, 1, "E", "I") && !contains(str, i4, 3, "RGY", "OGY")) {
                    doubleMetaphoneResult.append('K', 'J');
                }
            }
            if (!contains(str, i3, 1, "E", "I", "Y") && !contains(str, i2 - 1, 4, "AGGI", "OGGI")) {
                if (charAt(str, i3) == 'G') {
                    int i5 = i2 + 2;
                    doubleMetaphoneResult.append('K');
                    return i5;
                }
                doubleMetaphoneResult.append('K');
                return i3;
            } else if (!contains(str, 0, 4, "VAN ", "VON ") && !contains(str, 0, 3, "SCH") && !contains(str, i3, 2, "ET")) {
                if (contains(str, i3, 3, "IER")) {
                    doubleMetaphoneResult.append('J');
                } else {
                    doubleMetaphoneResult.append('J', 'K');
                }
            } else {
                doubleMetaphoneResult.append('K');
            }
        }
        return i2 + 2;
    }

    private int handleGH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (i2 > 0 && !isVowel(charAt(str, i2 - 1))) {
            doubleMetaphoneResult.append('K');
        } else if (i2 == 0) {
            int i3 = i2 + 2;
            if (charAt(str, i3) == 'I') {
                doubleMetaphoneResult.append('J');
                return i3;
            }
            doubleMetaphoneResult.append('K');
            return i3;
        } else if ((i2 <= 1 || !contains(str, i2 - 2, 1, "B", DYConstants.LETTER_H, AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE)) && ((i2 <= 2 || !contains(str, i2 - 3, 1, "B", DYConstants.LETTER_H, AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE)) && (i2 <= 3 || !contains(str, i2 - 4, 1, "B", DYConstants.LETTER_H)))) {
            if (i2 > 2 && charAt(str, i2 - 1) == 'U' && contains(str, i2 - 3, 1, "C", "G", "L", "R", "T")) {
                doubleMetaphoneResult.append('F');
            } else if (i2 > 0 && charAt(str, i2 - 1) != 'I') {
                doubleMetaphoneResult.append('K');
            }
        }
        return i2 + 2;
    }

    private int handleH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if ((i2 == 0 || isVowel(charAt(str, i2 - 1))) && isVowel(charAt(str, i2 + 1))) {
            doubleMetaphoneResult.append('H');
            return i2 + 2;
        }
        return i2 + 1;
    }

    private int handleJ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        if (!contains(str, i2, 4, "JOSE") && !contains(str, 0, 4, "SAN ")) {
            if (i2 == 0 && !contains(str, i2, 4, "JOSE")) {
                doubleMetaphoneResult.append('J', 'A');
            } else {
                int i3 = i2 - 1;
                if (isVowel(charAt(str, i3)) && !z) {
                    int i4 = i2 + 1;
                    if (charAt(str, i4) == 'A' || charAt(str, i4) == 'O') {
                        doubleMetaphoneResult.append('J', 'H');
                    }
                }
                if (i2 == str.length() - 1) {
                    doubleMetaphoneResult.append('J', ' ');
                } else if (!contains(str, i2 + 1, 1, L_T_K_S_N_M_B_Z) && !contains(str, i3, 1, "S", "K", "L")) {
                    doubleMetaphoneResult.append('J');
                }
            }
            int i5 = i2 + 1;
            return charAt(str, i5) == 'J' ? i2 + 2 : i5;
        }
        if ((i2 != 0 || charAt(str, i2 + 4) != ' ') && str.length() != 4 && !contains(str, 0, 4, "SAN ")) {
            doubleMetaphoneResult.append('J', 'H');
        } else {
            doubleMetaphoneResult.append('H');
        }
        return i2 + 1;
    }

    private int handleL(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        int i3 = i2 + 1;
        if (charAt(str, i3) == 'L') {
            if (conditionL0(str, i2)) {
                doubleMetaphoneResult.appendPrimary('L');
            } else {
                doubleMetaphoneResult.append('L');
            }
            return i2 + 2;
        }
        doubleMetaphoneResult.append('L');
        return i3;
    }

    private int handleP(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        int i3 = i2 + 1;
        if (charAt(str, i3) == 'H') {
            doubleMetaphoneResult.append('F');
            return i2 + 2;
        }
        doubleMetaphoneResult.append('P');
        if (contains(str, i3, 1, IShareAdapter.SHARE_ACTION_PANE, "B")) {
            i3 = i2 + 2;
        }
        return i3;
    }

    private int handleR(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        if (i2 == str.length() - 1 && !z && contains(str, i2 - 2, 2, "IE") && !contains(str, i2 - 4, 2, "ME", "MA")) {
            doubleMetaphoneResult.appendAlternate('R');
        } else {
            doubleMetaphoneResult.append('R');
        }
        int i3 = i2 + 1;
        return charAt(str, i3) == 'R' ? i2 + 2 : i3;
    }

    private int handleS(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        if (!contains(str, i2 - 1, 3, "ISL", "YSL")) {
            if (i2 == 0 && contains(str, i2, 5, "SUGAR")) {
                doubleMetaphoneResult.append('X', 'S');
            } else {
                if (contains(str, i2, 2, "SH")) {
                    if (contains(str, i2 + 1, 4, "HEIM", "HOEK", "HOLM", "HOLZ")) {
                        doubleMetaphoneResult.append('S');
                    } else {
                        doubleMetaphoneResult.append('X');
                    }
                } else if (contains(str, i2, 3, "SIO", "SIA") || contains(str, i2, 4, "SIAN")) {
                    if (z) {
                        doubleMetaphoneResult.append('S');
                    } else {
                        doubleMetaphoneResult.append('S', 'X');
                    }
                    return i2 + 3;
                } else {
                    if (i2 != 0 || !contains(str, i2 + 1, 1, "M", AuraConstants.MESSAGE_COUPON_TYPE_NEW, "L", "W")) {
                        int i3 = i2 + 1;
                        if (!contains(str, i3, 1, "Z")) {
                            if (contains(str, i2, 2, "SC")) {
                                return handleSC(str, doubleMetaphoneResult, i2);
                            }
                            if (i2 == str.length() - 1 && contains(str, i2 - 2, 2, "AI", "OI")) {
                                doubleMetaphoneResult.appendAlternate('S');
                            } else {
                                doubleMetaphoneResult.append('S');
                            }
                            if (!contains(str, i3, 1, "S", "Z")) {
                                return i3;
                            }
                        }
                    }
                    doubleMetaphoneResult.append('S', 'X');
                    int i4 = i2 + 1;
                    if (!contains(str, i4, 1, "Z")) {
                        return i4;
                    }
                }
                return i2 + 2;
            }
        }
        return i2 + 1;
    }

    private int handleSC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        int i3 = i2 + 2;
        if (charAt(str, i3) == 'H') {
            int i4 = i2 + 3;
            if (contains(str, i4, 2, "OO", "ER", "EN", "UY", "ED", "EM")) {
                if (contains(str, i4, 2, "ER", "EN")) {
                    doubleMetaphoneResult.append("X", "SK");
                } else {
                    doubleMetaphoneResult.append("SK");
                }
            } else if (i2 == 0 && !isVowel(charAt(str, 3)) && charAt(str, 3) != 'W') {
                doubleMetaphoneResult.append('X', 'S');
            } else {
                doubleMetaphoneResult.append('X');
            }
        } else if (contains(str, i3, 1, "I", "E", "Y")) {
            doubleMetaphoneResult.append('S');
        } else {
            doubleMetaphoneResult.append("SK");
        }
        return i2 + 3;
    }

    private int handleT(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (contains(str, i2, 4, "TION")) {
            doubleMetaphoneResult.append('X');
        } else if (contains(str, i2, 3, "TIA", "TCH")) {
            doubleMetaphoneResult.append('X');
        } else if (!contains(str, i2, 2, "TH") && !contains(str, i2, 3, "TTH")) {
            doubleMetaphoneResult.append('T');
            int i3 = i2 + 1;
            return contains(str, i3, 1, "T", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE) ? i2 + 2 : i3;
        } else {
            int i4 = i2 + 2;
            if (!contains(str, i4, 2, la.u, "AM") && !contains(str, 0, 4, "VAN ", "VON ") && !contains(str, 0, 3, "SCH")) {
                doubleMetaphoneResult.append('0', 'T');
                return i4;
            }
            doubleMetaphoneResult.append('T');
            return i4;
        }
        return i2 + 3;
    }

    private int handleW(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (contains(str, i2, 2, "WR")) {
            doubleMetaphoneResult.append('R');
            return i2 + 2;
        }
        if (i2 == 0) {
            int i3 = i2 + 1;
            if (isVowel(charAt(str, i3)) || contains(str, i2, 2, "WH")) {
                if (isVowel(charAt(str, i3))) {
                    doubleMetaphoneResult.append('A', 'F');
                } else {
                    doubleMetaphoneResult.append('A');
                }
                return i3;
            }
        }
        if ((i2 != str.length() - 1 || !isVowel(charAt(str, i2 - 1))) && !contains(str, i2 - 1, 5, "EWSKI", "EWSKY", "OWSKI", "OWSKY") && !contains(str, 0, 3, "SCH")) {
            if (contains(str, i2, 4, "WICZ", "WITZ")) {
                doubleMetaphoneResult.append(la.f16820g, "FX");
                return i2 + 4;
            }
        } else {
            doubleMetaphoneResult.appendAlternate('F');
        }
        return i2 + 1;
    }

    private int handleX(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (i2 == 0) {
            doubleMetaphoneResult.append('S');
            return i2 + 1;
        }
        if (i2 != str.length() - 1 || (!contains(str, i2 - 3, 3, "IAU", "EAU") && !contains(str, i2 - 2, 2, "AU", "OU"))) {
            doubleMetaphoneResult.append("KS");
        }
        int i3 = i2 + 1;
        return contains(str, i3, 1, "C", "X") ? i2 + 2 : i3;
    }

    private int handleZ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        int i3 = i2 + 1;
        if (charAt(str, i3) == 'H') {
            doubleMetaphoneResult.append('J');
            return i2 + 2;
        }
        if (!contains(str, i3, 2, "ZO", "ZI", "ZA") && (!z || i2 <= 0 || charAt(str, i2 - 1) == 'T')) {
            doubleMetaphoneResult.append('S');
        } else {
            doubleMetaphoneResult.append("S", la.f16820g);
        }
        if (charAt(str, i3) == 'Z') {
            i3 = i2 + 2;
        }
        return i3;
    }

    private boolean isSilentStart(String str) {
        for (String str2 : SILENT_START) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSlavoGermanic(String str) {
        return str.indexOf(87) > -1 || str.indexOf(75) > -1 || str.indexOf("CZ") > -1 || str.indexOf("WITZ") > -1;
    }

    private boolean isVowel(char c2) {
        return VOWELS.indexOf(c2) != -1;
    }

    protected char charAt(String str, int i2) {
        if (i2 < 0 || i2 >= str.length()) {
            return (char) 0;
        }
        return str.charAt(i2);
    }

    public String doubleMetaphone(String str) {
        return doubleMetaphone(str, false);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return doubleMetaphone((String) obj);
        }
        throw new EncoderException("DoubleMetaphone encode parameter is not of type String");
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2) {
        return isDoubleMetaphoneEqual(str, str2, false);
    }

    public void setMaxCodeLen(int i2) {
        this.maxCodeLen = i2;
    }

    /* loaded from: classes11.dex */
    public class DoubleMetaphoneResult {
        private final StringBuilder alternate;
        private final int maxLength;
        private final StringBuilder primary;

        public DoubleMetaphoneResult(int i2) {
            this.primary = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.alternate = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.maxLength = i2;
        }

        public void append(char c2) {
            appendPrimary(c2);
            appendAlternate(c2);
        }

        public void appendAlternate(char c2) {
            if (this.alternate.length() < this.maxLength) {
                this.alternate.append(c2);
            }
        }

        public void appendPrimary(char c2) {
            if (this.primary.length() < this.maxLength) {
                this.primary.append(c2);
            }
        }

        public String getAlternate() {
            return this.alternate.toString();
        }

        public String getPrimary() {
            return this.primary.toString();
        }

        public boolean isComplete() {
            return this.primary.length() >= this.maxLength && this.alternate.length() >= this.maxLength;
        }

        public void append(char c2, char c3) {
            appendPrimary(c2);
            appendAlternate(c3);
        }

        public void appendAlternate(String str) {
            int length = this.maxLength - this.alternate.length();
            if (str.length() <= length) {
                this.alternate.append(str);
            } else {
                this.alternate.append(str.substring(0, length));
            }
        }

        public void appendPrimary(String str) {
            int length = this.maxLength - this.primary.length();
            if (str.length() <= length) {
                this.primary.append(str);
            } else {
                this.primary.append(str.substring(0, length));
            }
        }

        public void append(String str) {
            appendPrimary(str);
            appendAlternate(str);
        }

        public void append(String str, String str2) {
            appendPrimary(str);
            appendAlternate(str2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v13, types: [int] */
    /* JADX WARN: Type inference failed for: r1v14, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15, types: [int] */
    /* JADX WARN: Type inference failed for: r1v16, types: [int] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18, types: [int] */
    /* JADX WARN: Type inference failed for: r1v19, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8, types: [int] */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    /* JADX WARN: Type inference failed for: r7v0, types: [org.apache.commons.codec.language.DoubleMetaphone] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.String] */
    public String doubleMetaphone(String str, boolean z) {
        int i2;
        ?? cleanInput = cleanInput(str);
        if (cleanInput == 0) {
            return null;
        }
        boolean isSlavoGermanic = isSlavoGermanic(cleanInput);
        ?? isSilentStart = isSilentStart(cleanInput);
        DoubleMetaphoneResult doubleMetaphoneResult = new DoubleMetaphoneResult(getMaxCodeLen());
        while (!doubleMetaphoneResult.isComplete() && isSilentStart <= cleanInput.length() - 1) {
            char charAt = cleanInput.charAt(isSilentStart);
            if (charAt == '\u00c7') {
                doubleMetaphoneResult.append('S');
            } else if (charAt != '\u00d1') {
                switch (charAt) {
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                    case 'Y':
                        isSilentStart = handleAEIOUY(doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'B':
                        doubleMetaphoneResult.append('P');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'B') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'C':
                        isSilentStart = handleC(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'D':
                        isSilentStart = handleD(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'F':
                        doubleMetaphoneResult.append('F');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'F') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'G':
                        isSilentStart = handleG(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'H':
                        isSilentStart = handleH(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'J':
                        isSilentStart = handleJ(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'K':
                        doubleMetaphoneResult.append('K');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'K') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'L':
                        isSilentStart = handleL(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'M':
                        doubleMetaphoneResult.append('M');
                        if (!conditionM0(cleanInput, isSilentStart)) {
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'N':
                        doubleMetaphoneResult.append('N');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'N') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'P':
                        isSilentStart = handleP(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'Q':
                        doubleMetaphoneResult.append('K');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'Q') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'R':
                        isSilentStart = handleR(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'S':
                        isSilentStart = handleS(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'T':
                        isSilentStart = handleT(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'V':
                        doubleMetaphoneResult.append('F');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'V') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'W':
                        isSilentStart = handleW(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'X':
                        isSilentStart = handleX(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'Z':
                        isSilentStart = handleZ(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                }
            } else {
                doubleMetaphoneResult.append('N');
            }
            isSilentStart++;
        }
        return z ? doubleMetaphoneResult.getAlternate() : doubleMetaphoneResult.getPrimary();
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2, boolean z) {
        return StringUtils.equals(doubleMetaphone(str, z), doubleMetaphone(str2, z));
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return doubleMetaphone(str);
    }
}
