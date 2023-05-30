package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.text.TextUtils;

/* loaded from: classes8.dex */
public class ThreeParser implements Parser {
    private static final char COLON = ':';
    public static final char END_CHAR = ')';
    private static final char EQUAL = '=';
    public static final char LEFT_BRACE = '(';
    private static final char QUESTION = '?';
    public static final char RIGHT_BRACE = ')';
    public static final char START_CHAR = '(';
    private static final int STATE_CONDITION = 1;
    private static final int STATE_CONDITION_EQUAL_1 = 4;
    private static final int STATE_CONDITION_EQUAL_2 = 5;
    private static final int STATE_LEFT_BRACE = 6;
    private static final int STATE_RESULT_1 = 2;
    private static final int STATE_RESULT_2 = 3;
    private static final int STATE_RIGHT_BRACE = 7;
    private BaseParser condition;
    private BaseParser equal1;
    private BaseParser equal2;
    private BaseParser result1;
    private BaseParser result2;
    private int state;
    private String value;
    private int braceDepth = 0;
    boolean isEqual = true;

    private boolean compileEqual(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.length();
        String[] split = str.split("==");
        if (split == null || split.length != 2) {
            split = str.split("<");
            this.isEqual = false;
        }
        if (split.length == 2) {
            BaseParser baseParser = new BaseParser();
            this.equal1 = baseParser;
            baseParser.compile(split[0]);
            BaseParser baseParser2 = new BaseParser();
            this.equal2 = baseParser2;
            baseParser2.compile(split[1]);
            return true;
        }
        return false;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.ylayout.Parser
    public boolean compile(String str) {
        if (str != null && str.length() != 0) {
            this.value = str;
            int length = str.length();
            if (str.charAt(0) == '(') {
                int i2 = length - 1;
                if (str.charAt(i2) == ')') {
                    StringBuilder sb = new StringBuilder();
                    this.state = 1;
                    for (int i3 = 1; i3 < i2; i3++) {
                        char charAt = str.charAt(i3);
                        if (charAt == '(') {
                            this.braceDepth++;
                        } else if (charAt == ')') {
                            this.braceDepth--;
                        } else if (charAt != ':') {
                            if (charAt != '?') {
                                sb.append(charAt);
                            } else if (this.braceDepth > 0) {
                                sb.append(charAt);
                            } else if (this.state == 1) {
                                String sb2 = sb.toString();
                                if (!compileEqual(sb2)) {
                                    BaseParser baseParser = new BaseParser();
                                    this.condition = baseParser;
                                    baseParser.compile(sb2);
                                }
                                sb.delete(0, sb.length());
                                this.state = 2;
                            }
                        } else if (this.braceDepth > 0) {
                            sb.append(charAt);
                        } else if (this.state == 2) {
                            BaseParser baseParser2 = new BaseParser();
                            this.result1 = baseParser2;
                            baseParser2.compile(sb.toString().trim());
                            sb.delete(0, sb.length());
                            this.state = 3;
                        }
                    }
                    if (this.state == 3) {
                        BaseParser baseParser3 = new BaseParser();
                        this.result2 = baseParser3;
                        baseParser3.compile(sb.toString().trim());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.ylayout.Parser
    public String getValue() {
        return this.value;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0055, code lost:
        if (android.text.TextUtils.equals(((java.lang.String) r0).toLowerCase(), com.jd.dynamic.DYConstants.DY_FALSE) != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00a4, code lost:
        if (((java.lang.Boolean) r0).booleanValue() == ((java.lang.Boolean) r3).booleanValue()) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0108, code lost:
        if (((java.lang.Integer) r0).intValue() < r3.intValue()) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0116  */
    @Override // com.jingdong.sdk.lib.puppetlayout.ylayout.Parser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object getValueFromEL(java.lang.Object r6) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.lib.puppetlayout.ylayout.ThreeParser.getValueFromEL(java.lang.Object):java.lang.Object");
    }
}
