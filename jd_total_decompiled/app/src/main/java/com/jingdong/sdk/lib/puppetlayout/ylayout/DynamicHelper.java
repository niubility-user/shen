package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.lib.puppetlayout.g.b;
import com.jingdong.sdk.lib.puppetlayout.h.a;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes8.dex */
public class DynamicHelper {
    public static List<String> getDynamicList(a aVar, String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("\\{(.*?)\\}").matcher(str);
        while (matcher.find()) {
            arrayList.add(matcher.group(1));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getValueFromData(PuppetContext puppetContext, String str, JDJSONObject jDJSONObject) {
        String[] split;
        int i2;
        int i3;
        JDJSONObject jDJSONObject2;
        String str2;
        JDJSONArray jDJSONArray;
        if (jDJSONObject == null) {
            return null;
        }
        try {
            split = str.split("\\.");
            i3 = -1;
            jDJSONObject2 = null;
            str2 = null;
            jDJSONArray = null;
        } catch (Exception e2) {
            if (b.a) {
            }
            if (b.a) {
            }
            return null;
        }
        for (i2 = 0; i2 < split.length; i2++) {
            if (split[i2].startsWith("[") && split[i2].endsWith("]")) {
                try {
                    int intValue = Integer.valueOf(split[i2].substring(1, split[i2].length() - 1)).intValue();
                    if (intValue >= 0 && i2 == i3 + 1 && jDJSONArray != null && intValue < jDJSONArray.size()) {
                        jDJSONObject2 = jDJSONArray.optJSONObject(intValue);
                        if (i2 == split.length - 1) {
                            str2 = jDJSONObject2.toJSONString();
                        }
                    }
                } catch (Exception e3) {
                    if (b.a) {
                        e3.printStackTrace();
                    }
                }
            } else {
                if (i2 == 0) {
                    if (i2 == split.length - 1) {
                        return jDJSONObject.optString(split[i2], null);
                    }
                    Object obj = jDJSONObject.get(split[i2]);
                    if (obj != null) {
                        if (obj instanceof JDJSONObject) {
                            jDJSONObject2 = (JDJSONObject) obj;
                        } else if (obj instanceof JDJSONArray) {
                            jDJSONArray = (JDJSONArray) obj;
                        }
                    }
                } else if (jDJSONObject2 == null) {
                    return str2;
                } else {
                    if (i2 == split.length - 1) {
                        str2 = jDJSONObject2.optString(split[i2], null);
                    } else {
                        Object obj2 = jDJSONObject2.get(split[i2]);
                        if (obj2 != null) {
                            if (obj2 instanceof JDJSONObject) {
                                jDJSONObject2 = (JDJSONObject) obj2;
                                jDJSONArray = null;
                                i3 = -1;
                            } else if (obj2 instanceof JDJSONArray) {
                                jDJSONArray = (JDJSONArray) obj2;
                                jDJSONObject2 = null;
                            }
                        }
                    }
                }
                i3 = i2;
            }
            if (b.a) {
                e2.printStackTrace();
            }
            if (b.a) {
                b.a(Action.ActionType_COMMON, "Action Exception : " + e2);
            }
            return null;
        }
        return str2;
    }

    public static boolean isDynamic(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !(str.startsWith("[") && str.endsWith("]")) && str.length() > 2 && str.contains("{") && str.contains("}");
    }

    public static boolean isThreeUnknown(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        boolean z = str.charAt(0) == '(' && str.charAt(length + (-1)) == ')';
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 1; i4 < length - 1; i4++) {
            if (str.charAt(i4) == '?') {
                if (i2 == -1) {
                    i2 = i4;
                }
            } else if (str.charAt(i4) == ':' && i2 != -1 && i3 == -1) {
                i3 = i4;
            }
        }
        return z && i2 != -1 && i3 != -1 && i2 < i3;
    }

    public static String replaceAllValue(String str, HashMap<String, String> hashMap) {
        if (hashMap != null) {
            try {
                if (hashMap.size() > 0 && !TextUtils.isEmpty(str)) {
                    for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                        str = str.replace("{" + entry.getKey() + "}", entry.getValue());
                    }
                }
            } catch (Exception e2) {
                if (b.a) {
                    e2.printStackTrace();
                }
            }
        }
        return str;
    }
}
