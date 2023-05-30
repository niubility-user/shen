package com.jingdong.common.utils;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes6.dex */
public class JDJSONObjectWithDefaultUtils {
    public static boolean getJSONBooleanWithDefault(JDJSONObject jDJSONObject, String str, boolean z) {
        Boolean bool = jDJSONObject.getBoolean(str);
        return bool == null ? z : bool.booleanValue();
    }

    public static int getJSONIntWithDefault(JDJSONObject jDJSONObject, String str, int i2) {
        Integer integer = jDJSONObject.getInteger(str);
        return integer == null ? i2 : integer.intValue();
    }

    public static long getJSONLongWithDefault(JDJSONObject jDJSONObject, String str, long j2) {
        Long l2 = jDJSONObject.getLong(str);
        return l2 == null ? j2 : l2.longValue();
    }

    public static String getJSONStringWithDefault(JDJSONObject jDJSONObject, String str, String str2) {
        String string = jDJSONObject.getString(str);
        return string == null ? str2 : string;
    }

    public static boolean isNull(JDJSONObject jDJSONObject, String str) {
        return jDJSONObject.getString(str) == null;
    }
}
