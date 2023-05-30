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
    */
    public static final boolean b(@NotNull String str) {
        int indexOf$default;
        int lastIndexOf$default;
        if (str.length() >= 3 && indexOf$default >= 0) {
            lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) str, '`', 0, false, 6, (Object) null);
            return lastIndexOf$default > indexOf$default + 1;
        }
        return false;
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
