package com.jingdong.aura.sdk.provided;

import java.util.HashMap;

/* loaded from: classes4.dex */
public final class c {
    public static HashMap<String, Boolean> a = new HashMap<>();
    public static HashMap<String, Integer> b = new HashMap<>();

    /* renamed from: c  reason: collision with root package name */
    public static HashMap<String, Long> f12233c = new HashMap<>();

    public static void a(String str, int i2) {
        HashMap<String, Integer> hashMap = b;
        if (hashMap != null) {
            hashMap.put(str, Integer.valueOf(i2));
        }
    }

    public static void a(String str, long j2) {
        HashMap<String, Long> hashMap = f12233c;
        if (hashMap != null) {
            hashMap.put(str, Long.valueOf(j2));
        }
    }

    public static void a(String str, boolean z) {
        HashMap<String, Boolean> hashMap = a;
        if (hashMap != null) {
            hashMap.put(str, Boolean.valueOf(z));
        }
    }

    public static boolean a(String str) {
        if (a.get(str) != null) {
            return a.get(str).booleanValue();
        }
        return true;
    }

    public static int b(String str) {
        HashMap<String, Integer> hashMap = b;
        if (hashMap == null || hashMap.isEmpty() || str == null || !b.keySet().contains(str) || b.get(str) == null) {
            return 0;
        }
        return b.get(str).intValue();
    }

    public static long c(String str) {
        HashMap<String, Long> hashMap = f12233c;
        if (hashMap == null || hashMap.isEmpty() || str == null || !f12233c.keySet().contains(str) || f12233c.get(str) == null) {
            return 0L;
        }
        return f12233c.get(str).longValue();
    }
}
