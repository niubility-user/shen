package com.jd.stat.common.b;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class c {
    private static void a(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            try {
                sb.append('\t');
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
    }

    public static String a(String str) {
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    StringBuilder sb = new StringBuilder();
                    int i2 = 0;
                    char c2 = 0;
                    boolean z = false;
                    int i3 = 0;
                    while (i2 < str.length()) {
                        char charAt = str.charAt(i2);
                        if (charAt == '\"') {
                            if (c2 != '\\') {
                                z = !z;
                            }
                            sb.append(charAt);
                        } else if (charAt != ',') {
                            if (charAt != '[') {
                                if (charAt != ']') {
                                    if (charAt != '{') {
                                        if (charAt != '}') {
                                            sb.append(charAt);
                                        }
                                    }
                                }
                                if (!z) {
                                    sb.append('\n');
                                    i3--;
                                    a(sb, i3);
                                }
                                sb.append(charAt);
                            }
                            sb.append(charAt);
                            if (!z) {
                                sb.append('\n');
                                i3++;
                                a(sb, i3);
                            }
                        } else {
                            sb.append(charAt);
                            if (c2 != '\\' && !z) {
                                sb.append('\n');
                                a(sb, i3);
                            }
                        }
                        i2++;
                        c2 = charAt;
                    }
                    return sb.toString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    public static void a(JSONObject jSONObject, JSONObject... jSONObjectArr) {
        if (jSONObjectArr == null || jSONObject == null) {
            return;
        }
        try {
            for (JSONObject jSONObject2 : jSONObjectArr) {
                if (jSONObject2 != null) {
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String obj = keys.next().toString();
                        try {
                            jSONObject.put(obj, jSONObject2.opt(obj));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }
}
