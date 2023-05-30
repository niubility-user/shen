package com.jd.dynamic.lib.expv2.e;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class c {
    @NotNull
    public static final a a(@NotNull String str, char c2) {
        int i2 = 0;
        a aVar = new a(i2, i2, 3, null);
        char[] charArray = str.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
        int length = charArray.length;
        int i3 = 0;
        int i4 = 0;
        int i5 = -1;
        while (i2 < length) {
            int i6 = i4 + 1;
            if (c2 == charArray[i2]) {
                i3++;
                if (i5 == -1) {
                    i5 = i4;
                }
            }
            i2++;
            i4 = i6;
        }
        aVar.c(i5);
        aVar.b(i3);
        return aVar;
    }
}
