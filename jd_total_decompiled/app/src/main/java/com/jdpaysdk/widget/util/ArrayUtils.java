package com.jdpaysdk.widget.util;

import java.lang.reflect.Array;

/* loaded from: classes18.dex */
public class ArrayUtils {
    private static final int CACHE_SIZE = 73;
    public static final Object[] OBJECT = new Object[0];
    private static Object[] sCache = new Object[73];

    public static <T> T[] emptyArray(Class<T> cls) {
        if (cls == Object.class) {
            return (T[]) OBJECT;
        }
        int hashCode = (cls.hashCode() & Integer.MAX_VALUE) % 73;
        Object obj = sCache[hashCode];
        if (obj == null || obj.getClass().getComponentType() != cls) {
            obj = Array.newInstance((Class<?>) cls, 0);
            sCache[hashCode] = obj;
        }
        return (T[]) ((Object[]) obj);
    }
}
