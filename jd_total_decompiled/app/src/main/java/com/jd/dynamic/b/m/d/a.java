package com.jd.dynamic.b.m.d;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class a {
    public static final a b = new a();
    private static final ConcurrentHashMap<String, HashMap<String, Object>> a = new ConcurrentHashMap<>();

    private a() {
    }

    @Nullable
    public final Object a(@NotNull String str, @NotNull String str2) {
        HashMap<String, Object> hashMap = a.get(str);
        if (hashMap != null) {
            return hashMap.get(str2);
        }
        return null;
    }

    public final void b(@NotNull String str) {
        a.remove(str);
    }

    public final void c(@NotNull String str, @NotNull String str2, @Nullable Object obj) {
        ConcurrentHashMap<String, HashMap<String, Object>> concurrentHashMap = a;
        HashMap<String, Object> hashMap = concurrentHashMap.get(str);
        if (hashMap == null || hashMap.isEmpty()) {
            hashMap = new HashMap<>();
            concurrentHashMap.put(str, hashMap);
        }
        hashMap.put(str2, obj);
    }

    public final void d(@NotNull String str, @NotNull String str2) {
        HashMap<String, Object> hashMap = a.get(str);
        if (hashMap != null) {
            hashMap.remove(str2);
        }
    }
}
