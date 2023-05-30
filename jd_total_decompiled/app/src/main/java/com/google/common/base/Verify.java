package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* loaded from: classes12.dex */
public final class Verify {
    private Verify() {
    }

    public static void verify(boolean z) {
        if (!z) {
            throw new VerifyException();
        }
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(@NullableDecl T t) {
        return (T) verifyNotNull(t, "expected a non-null reference", new Object[0]);
    }

    public static void verify(boolean z, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, objArr));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(@NullableDecl T t, @NullableDecl String str, @NullableDecl Object... objArr) {
        verify(t != null, str, objArr);
        return t;
    }

    public static void verify(boolean z, @NullableDecl String str, char c2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, int i2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Integer.valueOf(i2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, long j2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Long.valueOf(j2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, @NullableDecl Object obj) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, obj));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, char c2, char c3) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Character.valueOf(c2), Character.valueOf(c3)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, int i2, char c2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Integer.valueOf(i2), Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, long j2, char c2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Long.valueOf(j2), Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, @NullableDecl Object obj, char c2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, obj, Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, char c2, int i2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Character.valueOf(c2), Integer.valueOf(i2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, int i2, int i3) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, long j2, int i2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Long.valueOf(j2), Integer.valueOf(i2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, @NullableDecl Object obj, int i2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, obj, Integer.valueOf(i2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, char c2, long j2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Character.valueOf(c2), Long.valueOf(j2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, int i2, long j2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Integer.valueOf(i2), Long.valueOf(j2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, long j2, long j3) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, @NullableDecl Object obj, long j2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, obj, Long.valueOf(j2)));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, char c2, @NullableDecl Object obj) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Character.valueOf(c2), obj));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, int i2, @NullableDecl Object obj) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Integer.valueOf(i2), obj));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, long j2, @NullableDecl Object obj) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, Long.valueOf(j2), obj));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, obj, obj2));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, obj, obj2, obj3));
        }
    }

    public static void verify(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (!z) {
            throw new VerifyException(Preconditions.format(str, obj, obj2, obj3, obj4));
        }
    }
}
