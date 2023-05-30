package com.jd.lib.cashier.sdk.core.utils;

import java.util.HashMap;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class o0 {
    private static HashMap<String, Long> a = new HashMap<>();

    @JvmStatic
    public static final boolean a(@NotNull String str) {
        return b(str, 1000L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        if ((r0 - r4.longValue()) < r7) goto L20;
     */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean b(@NotNull String str, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap<String, Long> hashMap = a;
        boolean z = true;
        if (hashMap != null && hashMap.containsKey(str)) {
            HashMap<String, Long> hashMap2 = a;
            if ((hashMap2 != null ? hashMap2.get(str) : null) != null) {
                HashMap<String, Long> hashMap3 = a;
                Long l2 = hashMap3 != null ? hashMap3.get(str) : null;
                if (l2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(l2, "timeMillisMap?.get(classNameKey)!!");
            }
        }
        z = false;
        HashMap<String, Long> hashMap4 = a;
        if (hashMap4 != null) {
            hashMap4.put(str, Long.valueOf(currentTimeMillis));
        }
        return z;
    }

    @JvmStatic
    public static final void c() {
        if (a != null) {
            a = null;
        }
    }
}
