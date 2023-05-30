package com.jd.lib.un.utils;

/* loaded from: classes16.dex */
public class UnArrayUtil {
    public static int getValueIndex(Object[] objArr, Object obj) {
        if (objArr == null || objArr.length <= 0 || obj == null) {
            return -1;
        }
        for (int i2 = 0; i2 < objArr.length; i2++) {
            Object obj2 = objArr[i2];
            if (obj == obj2 || obj.equals(obj2)) {
                return i2;
            }
        }
        return -1;
    }

    public static Object getValueOfArray(Object[] objArr, int i2, Object obj) {
        if (objArr != null && objArr.length >= 1 && i2 >= 0 && i2 <= objArr.length - 1) {
            try {
                return objArr[i2];
            } catch (Exception unused) {
            }
        }
        return obj;
    }
}
