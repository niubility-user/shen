package com.jd.lib.cashier.sdk.h.h;

import com.jd.lib.cashier.sdk.core.utils.d0;
import java.util.Arrays;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class l {
    @JvmStatic
    public static final boolean a(@Nullable CharSequence charSequence, @Nullable CharSequence charSequence2) {
        return d0.a(charSequence, charSequence2);
    }

    @JvmStatic
    public static final int b(@NotNull Object... objArr) {
        return Arrays.hashCode(objArr);
    }
}
