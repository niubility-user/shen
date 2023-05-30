package com.jingdong.common.recommend;

import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendMtaELParser {
    private static final char ARRAY_END = ']';
    private static final char ARRAY_START = '[';
    private static final char DOT = '.';
    private static final int STATE_ARRAY_END = 4;
    private static final int STATE_ARRAY_START = 3;
    private static final int STATE_COMMON = 2;
    private List<Object> exprFragment = new LinkedList();
    private String value;

    public static int parseInt(String str) {
        return parseInt(str, 10);
    }

    public void compile(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        this.value = str;
        int length = str.length();
        this.exprFragment.clear();
        StringBuilder sb = new StringBuilder();
        char c2 = 2;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt != '.') {
                if (charAt != '[') {
                    if (charAt != ']') {
                        sb.append(charAt);
                    } else if (c2 != 3) {
                        return;
                    } else {
                        String sb2 = sb.toString();
                        int parseInt = parseInt(sb2);
                        if (parseInt != Integer.MIN_VALUE) {
                            this.exprFragment.add(Integer.valueOf(parseInt));
                        } else {
                            this.exprFragment.add(sb2);
                        }
                        sb.delete(0, sb.length());
                        c2 = 4;
                    }
                } else if (c2 != 2) {
                    return;
                } else {
                    if (sb.length() > 0) {
                        String sb3 = sb.toString();
                        int parseInt2 = parseInt(sb3);
                        if (parseInt2 != Integer.MIN_VALUE) {
                            this.exprFragment.add(Integer.valueOf(parseInt2));
                        } else {
                            this.exprFragment.add(sb3);
                        }
                        sb.delete(0, sb.length());
                    }
                    c2 = 3;
                }
            } else if (c2 == 3) {
                sb.append(charAt);
            } else if (c2 == 4) {
                c2 = 2;
            } else {
                this.exprFragment.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }
        if (c2 == 2) {
            this.exprFragment.add(sb.toString());
        }
    }

    public String getValue() {
        return this.value;
    }

    public Object getValueFromEl(Object obj) {
        Object obj2 = null;
        if (this.exprFragment.size() > 0) {
            if (obj != null) {
                int i2 = 0;
                int size = this.exprFragment.size();
                while (i2 < size) {
                    Object obj3 = this.exprFragment.get(i2);
                    if (obj3 instanceof String) {
                        String obj4 = obj3.toString();
                        if (!obj4.equalsIgnoreCase("this")) {
                            if (!(obj instanceof JSONObject)) {
                                return obj2;
                            }
                            obj = ((JSONObject) obj).opt(obj4);
                        }
                    } else if (!(obj3 instanceof Integer)) {
                        continue;
                        i2++;
                        obj = obj2;
                    } else if (!(obj instanceof JSONArray)) {
                        return obj2;
                    } else {
                        obj = ((JSONArray) obj).opt(((Integer) obj3).intValue());
                    }
                    obj2 = obj;
                    i2++;
                    obj = obj2;
                }
                return obj2;
            }
            return null;
        }
        return this.value;
    }

    public static int parseInt(String str, int i2) {
        boolean z;
        int i3;
        if (str != null && i2 >= 2 && i2 <= 36) {
            int length = str.length();
            int i4 = -2147483647;
            if (length > 0) {
                int i5 = 0;
                char charAt = str.charAt(0);
                int i6 = 1;
                if (charAt < '0') {
                    if (charAt == '-') {
                        i4 = Integer.MIN_VALUE;
                        z = true;
                    } else if (charAt != '+') {
                        return Integer.MIN_VALUE;
                    } else {
                        z = false;
                    }
                    if (length == 1) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    z = false;
                    i6 = 0;
                }
                int i7 = i4 / i2;
                while (i6 < length) {
                    int i8 = i6 + 1;
                    int digit = Character.digit(str.charAt(i6), i2);
                    if (digit < 0 || i5 < i7 || (i3 = i5 * i2) < i4 + digit) {
                        return Integer.MIN_VALUE;
                    }
                    i5 = i3 - digit;
                    i6 = i8;
                }
                return z ? i5 : -i5;
            }
            return Integer.MIN_VALUE;
        }
        return Integer.MIN_VALUE;
    }
}
