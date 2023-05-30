package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import org.json.JSONArray;
import org.json.JSONObject;

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
    */
    public Object getValueFromEL(Object obj) {
        BaseParser baseParser = this.condition;
        if (baseParser == null && (this.equal1 == null || this.equal2 == null || this.result1 == null || this.result2 == null)) {
            return this.value;
        }
        if (obj == null) {
            return null;
        }
        boolean z = true;
        boolean z2 = false;
        if (baseParser != null) {
            Object valueFromEL = baseParser.getValueFromEL(obj);
            if (valueFromEL != null) {
                if (valueFromEL instanceof Boolean) {
                    z = ((Boolean) valueFromEL).booleanValue();
                } else if (valueFromEL instanceof String) {
                    CharSequence charSequence = (CharSequence) valueFromEL;
                    if (!TextUtils.isEmpty(charSequence)) {
                        if (!TextUtils.equals(charSequence, DYConstants.DY_NULL_STR)) {
                        }
                    }
                } else if (!(valueFromEL instanceof JSONObject)) {
                }
                z2 = z;
                if (z2) {
                    return this.result1.getValueFromEL(obj);
                }
                return this.result2.getValueFromEL(obj);
            }
            z = false;
            z2 = z;
            if (z2) {
            }
        } else {
            Object valueFromEL2 = this.equal1.getValueFromEL(obj);
            Object valueFromEL3 = this.equal2.getValueFromEL(obj);
            if (valueFromEL2 != null && valueFromEL3 != null) {
                if (!(valueFromEL2 instanceof Boolean) || !(valueFromEL3 instanceof Boolean)) {
                    if ((valueFromEL2 instanceof String) && (valueFromEL3 instanceof String)) {
                        z2 = TextUtils.equals((CharSequence) valueFromEL2, (CharSequence) valueFromEL3);
                    } else if ((valueFromEL2 instanceof JSONObject) && (valueFromEL3 instanceof JSONObject)) {
                        z2 = ((JSONObject) valueFromEL2).equals((JSONObject) valueFromEL3);
                    } else if ((valueFromEL2 instanceof JSONArray) && (valueFromEL3 instanceof JSONArray)) {
                        z2 = ((JSONArray) valueFromEL2).equals((JSONArray) valueFromEL3);
                    } else if ((valueFromEL2 instanceof Integer) && (valueFromEL3 instanceof String)) {
                        try {
                            Integer valueOf = Integer.valueOf(Integer.parseInt((String) valueFromEL3));
                            if (this.isEqual) {
                                if (((Integer) valueFromEL2).intValue() == valueOf.intValue()) {
                                    z2 = z;
                                }
                                z = false;
                                z2 = z;
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
            }
            if (z2) {
            }
        }
    }
}
