package com.jingdong.common.cart;

import com.jd.framework.json.JDJSONObject;

/* loaded from: classes5.dex */
public class CartJsonUtil {
    public static Integer optInt(JDJSONObject jDJSONObject, String str, Integer num) {
        Integer num2;
        if (jDJSONObject != null) {
            try {
                num2 = jDJSONObject.getInteger(str);
            } catch (Exception unused) {
                num2 = num;
            }
        } else {
            num2 = null;
        }
        return num2 != null ? num2 : num;
    }

    public static Long optLong(JDJSONObject jDJSONObject, String str, Long l2) {
        Long l3;
        if (jDJSONObject != null) {
            try {
                l3 = jDJSONObject.getLong(str);
            } catch (Exception unused) {
                l3 = l2;
            }
        } else {
            l3 = null;
        }
        return l3 != null ? l3 : l2;
    }

    public static Integer optInt(JDJSONObject jDJSONObject, String str) {
        return optInt(jDJSONObject, str, null);
    }

    public static Long optLong(JDJSONObject jDJSONObject, String str) {
        return optLong(jDJSONObject, str, null);
    }
}
