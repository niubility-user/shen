package com.jingdong.sdk.lib.puppetlayout.ylayout;

import com.jd.framework.json.JDJSONObject;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class BaseParser implements Parser {
    private static final char ARRAY_END = ']';
    private static final char ARRAY_START = '[';
    private static final char DOT = '.';
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';
    private static final int STATE_ARRAY_END = 4;
    private static final int STATE_ARRAY_START = 3;
    private static final int STATE_COMMON = 2;
    private List<Object> exprFragment = new LinkedList();
    private int state;
    private String value;

    @Override // com.jingdong.sdk.lib.puppetlayout.ylayout.Parser
    public boolean compile(String str) {
        if (str != null && str.length() != 0) {
            this.value = str;
            int length = str.length();
            this.exprFragment.clear();
            if (str.charAt(0) == '{') {
                int i2 = length - 1;
                if (str.charAt(i2) == '}') {
                    StringBuilder sb = new StringBuilder();
                    this.state = 2;
                    for (int i3 = 1; i3 < i2; i3++) {
                        char charAt = str.charAt(i3);
                        if (charAt != '.') {
                            sb.append(charAt);
                        } else {
                            int i4 = this.state;
                            if (i4 == 3) {
                                sb.append(charAt);
                            } else if (i4 == 4) {
                                this.state = 2;
                            } else {
                                String sb2 = sb.toString();
                                try {
                                    this.exprFragment.add(Integer.valueOf(Integer.parseInt(sb2)));
                                } catch (NumberFormatException unused) {
                                    this.exprFragment.add(sb2);
                                }
                                sb.delete(0, sb.length());
                            }
                        }
                    }
                    if (this.state == 2) {
                        String sb3 = sb.toString();
                        try {
                            this.exprFragment.add(Integer.valueOf(Integer.parseInt(sb3)));
                        } catch (NumberFormatException unused2) {
                            this.exprFragment.add(sb3);
                        }
                    }
                    return true;
                }
            }
            if (str.charAt(0) == '(' && str.charAt(length - 1) == ')') {
                try {
                    this.value = str.substring(1, length - 2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.ylayout.Parser
    public String getValue() {
        return this.value;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.ylayout.Parser
    public Object getValueFromEL(Object obj) {
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
                            if (obj instanceof JSONObject) {
                                obj = ((JSONObject) obj).opt(obj4);
                            } else if (!(obj instanceof JDJSONObject)) {
                                return obj2;
                            } else {
                                obj = ((JDJSONObject) obj).get(obj4);
                            }
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
}
