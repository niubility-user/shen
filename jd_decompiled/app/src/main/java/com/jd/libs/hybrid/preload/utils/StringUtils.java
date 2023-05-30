package com.jd.libs.hybrid.preload.utils;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;

/* loaded from: classes16.dex */
public class StringUtils {
    public static boolean isjson(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Object parse = JDJSON.parse(str);
            return (parse instanceof JDJSONObject) || (parse instanceof JDJSONArray);
        } catch (Exception unused) {
            return false;
        }
    }
}
