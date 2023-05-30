package com.jd.lib.cashier.sdk.core.utils;

import java.util.HashMap;
import kotlin.jvm.JvmStatic;
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
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean b(@org.jetbrains.annotations.NotNull java.lang.String r6, long r7) {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            java.util.HashMap<java.lang.String, java.lang.Long> r2 = com.jd.lib.cashier.sdk.core.utils.o0.a
            r3 = 1
            if (r2 == 0) goto L3e
            boolean r2 = r2.containsKey(r6)
            if (r2 != r3) goto L3e
            java.util.HashMap<java.lang.String, java.lang.Long> r2 = com.jd.lib.cashier.sdk.core.utils.o0.a
            r4 = 0
            if (r2 == 0) goto L1b
            java.lang.Object r2 = r2.get(r6)
            java.lang.Long r2 = (java.lang.Long) r2
            goto L1c
        L1b:
            r2 = r4
        L1c:
            if (r2 == 0) goto L3e
            java.util.HashMap<java.lang.String, java.lang.Long> r2 = com.jd.lib.cashier.sdk.core.utils.o0.a
            if (r2 == 0) goto L29
            java.lang.Object r2 = r2.get(r6)
            r4 = r2
            java.lang.Long r4 = (java.lang.Long) r4
        L29:
            if (r4 != 0) goto L2e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L2e:
            java.lang.String r2 = "timeMillisMap?.get(classNameKey)!!"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r2)
            long r4 = r4.longValue()
            long r4 = r0 - r4
            int r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r2 >= 0) goto L3e
            goto L3f
        L3e:
            r3 = 0
        L3f:
            java.util.HashMap<java.lang.String, java.lang.Long> r7 = com.jd.lib.cashier.sdk.core.utils.o0.a
            if (r7 == 0) goto L4a
            java.lang.Long r8 = java.lang.Long.valueOf(r0)
            r7.put(r6, r8)
        L4a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.core.utils.o0.b(java.lang.String, long):boolean");
    }

    @JvmStatic
    public static final void c() {
        if (a != null) {
            a = null;
        }
    }
}
