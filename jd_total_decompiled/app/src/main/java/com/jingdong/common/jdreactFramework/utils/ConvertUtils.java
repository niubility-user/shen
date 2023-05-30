package com.jingdong.common.jdreactFramework.utils;

import android.os.Bundle;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class ConvertUtils {
    public static Bundle mapToBundle(Map map) {
        Set<String> keySet = map.keySet();
        Bundle bundle = new Bundle();
        if (keySet != null) {
            for (String str : keySet) {
                Object obj = map.get(str);
                if (obj != null) {
                    if (obj instanceof Boolean) {
                        bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof String) {
                        bundle.putString(str, String.valueOf(obj));
                    } else if (obj instanceof Byte) {
                        bundle.putByte(str, ((Byte) obj).byteValue());
                    } else if (obj instanceof Float) {
                        bundle.putFloat(str, ((Float) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        bundle.putInt(str, ((Integer) obj).intValue());
                    } else if (obj instanceof Short) {
                        bundle.putShort(str, ((Short) obj).shortValue());
                    } else if (obj instanceof Double) {
                        bundle.putDouble(str, ((Double) obj).doubleValue());
                    } else if (obj instanceof Long) {
                        bundle.putLong(str, ((Long) obj).longValue());
                    } else if (obj instanceof Map) {
                        bundle.putBundle(str, paseMaptoBundle((Map) obj));
                    } else {
                        bundle.putString(str, obj.toString());
                    }
                }
            }
        }
        return bundle;
    }

    public static Bundle paseMaptoBundle(Map map) {
        Bundle bundle = new Bundle();
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = map.get(str);
                if (obj != null) {
                    if (obj instanceof Boolean) {
                        bundle.putBoolean(str, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof String) {
                        bundle.putString(str, String.valueOf(obj));
                    } else if (obj instanceof Byte) {
                        bundle.putByte(str, ((Byte) obj).byteValue());
                    } else if (obj instanceof Float) {
                        bundle.putFloat(str, ((Float) obj).floatValue());
                    } else if (obj instanceof Integer) {
                        bundle.putInt(str, ((Integer) obj).intValue());
                    } else if (obj instanceof Short) {
                        bundle.putShort(str, ((Short) obj).shortValue());
                    } else if (obj instanceof Double) {
                        bundle.putDouble(str, ((Double) obj).doubleValue());
                    } else if (obj instanceof Long) {
                        bundle.putLong(str, ((Long) obj).longValue());
                    } else {
                        bundle.putString(str, (String) obj);
                    }
                }
            }
        }
        return bundle;
    }
}
