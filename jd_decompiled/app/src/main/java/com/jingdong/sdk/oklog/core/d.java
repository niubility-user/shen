package com.jingdong.sdk.oklog.core;

import com.jd.dynamic.DYConstants;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class d {
    private static String a(Object obj) {
        return obj == null ? DYConstants.DY_NULL_STR : !obj.getClass().isArray() ? obj.toString() : obj instanceof boolean[] ? Arrays.toString((boolean[]) obj) : obj instanceof byte[] ? Arrays.toString((byte[]) obj) : obj instanceof char[] ? Arrays.toString((char[]) obj) : obj instanceof short[] ? Arrays.toString((short[]) obj) : obj instanceof int[] ? Arrays.toString((int[]) obj) : obj instanceof long[] ? Arrays.toString((long[]) obj) : obj instanceof float[] ? Arrays.toString((float[]) obj) : obj instanceof double[] ? Arrays.toString((double[]) obj) : obj instanceof Object[] ? Arrays.deepToString((Object[]) obj) : "";
    }

    public static String a(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        if (1 == objArr.length) {
            return a(objArr[0]);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < objArr.length; i2++) {
            stringBuffer.append("%s, ");
        }
        return String.format(stringBuffer.substring(0, stringBuffer.length() - 2), objArr);
    }
}
