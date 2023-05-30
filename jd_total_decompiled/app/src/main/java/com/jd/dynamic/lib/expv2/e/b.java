package com.jd.dynamic.lib.expv2.e;

import com.jd.dynamic.DYConstants;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class b {
    private final char[] a;
    private final int b;

    /* renamed from: c  reason: collision with root package name */
    private int f2241c;
    private final String d;

    public b(@NotNull String str) {
        this.d = str;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        char[] charArray = str.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
        this.a = charArray;
        this.b = str.length();
    }

    private final boolean d(char[] cArr, char c2) {
        for (char c3 : cArr) {
            if (c3 == c2) {
                return true;
            }
        }
        return false;
    }

    public final int a(@NotNull String str, char c2) {
        char[] charArray = str.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
        ArraysKt___ArraysKt.reverse(charArray);
        int length = charArray.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 1;
            if (charArray[i2] == c2) {
                return (charArray.length - 1) - i3;
            }
            i2++;
            i3 = i4;
        }
        return -1;
    }

    @Nullable
    public final String b(@NotNull char[] cArr) {
        int i2 = this.f2241c;
        if (i2 > this.b) {
            DYConstants.DYLog("scan index not valid ");
            return null;
        }
        while (i2 < this.b) {
            if (d(cArr, this.a[i2])) {
                String str = this.d;
                int i3 = this.f2241c;
                int i4 = i2 + 1;
                if (str != null) {
                    String substring = str.substring(i3, i4);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    this.f2241c = i4;
                    return substring;
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            i2++;
        }
        this.f2241c = i2 + 1;
        return null;
    }

    public final boolean c() {
        return this.b > this.f2241c;
    }
}
