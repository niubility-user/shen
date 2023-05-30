package com.jd.dynamic.lib.expv2;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.math.BigDecimal;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class g {
    public static final boolean a(@Nullable Object obj) {
        return obj != null && (Intrinsics.areEqual(obj.toString(), DYConstants.DY_NULL_STR) ^ true) && (Intrinsics.areEqual(obj.toString(), "") ^ true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0009, code lost:
        r0 = kotlin.text.StringsKt__StringsKt.indexOf$default((java.lang.CharSequence) r9, '#', 0, false, 6, (java.lang.Object) null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean b(@org.jetbrains.annotations.NotNull java.lang.String r9) {
        /*
            int r0 = r9.length()
            r1 = 0
            r2 = 3
            if (r0 >= r2) goto L9
            return r1
        L9:
            r4 = 35
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            r3 = r9
            int r0 = kotlin.text.StringsKt.indexOf$default(r3, r4, r5, r6, r7, r8)
            r2 = 1
            if (r0 < 0) goto L26
            r4 = 96
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            r3 = r9
            int r9 = kotlin.text.StringsKt.lastIndexOf$default(r3, r4, r5, r6, r7, r8)
            int r0 = r0 + r2
            if (r9 <= r0) goto L26
            r1 = 1
        L26:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.expv2.g.b(java.lang.String):boolean");
    }

    public static final byte c(@NotNull String str) {
        boolean startsWith$default;
        boolean startsWith$default2;
        startsWith$default = StringsKt__StringsKt.startsWith$default((CharSequence) str, '{', false, 2, (Object) null);
        if (startsWith$default) {
            return (byte) 1;
        }
        startsWith$default2 = StringsKt__StringsKt.startsWith$default((CharSequence) str, '[', false, 2, (Object) null);
        return startsWith$default2 ? (byte) 2 : (byte) 0;
    }

    @NotNull
    public static final BigDecimal d(@NotNull String str) {
        if (TextUtils.isEmpty(str)) {
            return new BigDecimal(-1);
        }
        try {
            return new BigDecimal(str);
        } catch (Exception unused) {
            return new BigDecimal(-1);
        }
    }
}
