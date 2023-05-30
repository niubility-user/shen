package com.facebook.infer.annotation;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class Assertions {
    public static void assertCondition(boolean z) {
        if (!z) {
            throw new AssertionError();
        }
    }

    public static <T> T assertGet(int i2, List<T> list) {
        assertCondition(i2 >= 0 && i2 < list.size(), "Index not in bound");
        return (T) assertNotNull(list.get(i2), "Null value");
    }

    public static <T> T assertNotNull(@Nullable T t, String str) {
        if (t != null) {
            return t;
        }
        throw new AssertionError(str);
    }

    public static AssertionError assertUnreachable() {
        throw new AssertionError();
    }

    public static void assumeCondition(boolean z) {
    }

    public static void assumeCondition(boolean z, String str) {
    }

    public static <T> T assumeNotNull(@Nullable T t) {
        return t;
    }

    public static <T> T assumeNotNull(@Nullable T t, String str) {
        return t;
    }

    public static <T> T nullsafeFIXME(@Nullable T t, String str) {
        return t;
    }

    public static void assertCondition(boolean z, String str) {
        if (!z) {
            throw new AssertionError(str);
        }
    }

    public static <T> T assertNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new AssertionError();
    }

    public static AssertionError assertUnreachable(String str) {
        throw new AssertionError(str);
    }

    public static <K, V> V assertGet(K k2, Map<K, V> map) {
        assertCondition(map.containsKey(k2), "Key not found");
        return (V) assertNotNull(map.get(k2), "Null value");
    }

    public static AssertionError assertUnreachable(Exception exc) {
        throw new AssertionError(exc);
    }
}
