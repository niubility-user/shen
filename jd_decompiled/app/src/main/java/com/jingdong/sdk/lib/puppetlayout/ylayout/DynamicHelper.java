package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.text.TextUtils;
import com.jingdong.sdk.lib.puppetlayout.g.b;
import com.jingdong.sdk.lib.puppetlayout.h.a;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getValueFromData(com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext r9, java.lang.String r10, com.jd.framework.json.JDJSONObject r11) {
        /*
            Method dump skipped, instructions count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.lib.puppetlayout.ylayout.DynamicHelper.getValueFromData(com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext, java.lang.String, com.jd.framework.json.JDJSONObject):java.lang.String");
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
