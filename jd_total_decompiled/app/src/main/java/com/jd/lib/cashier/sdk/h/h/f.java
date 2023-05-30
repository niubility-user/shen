package com.jd.lib.cashier.sdk.h.h;

import java.text.DecimalFormat;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class f {
    @NotNull
    public static final String a(float f2) {
        String format = new DecimalFormat("#.00").format(Float.valueOf(f2));
        Intrinsics.checkExpressionValueIsNotNull(format, "DecimalFormat(\"#.00\").format(this)");
        return format;
    }
}
