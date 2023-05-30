package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* loaded from: classes12.dex */
public final class Preconditions {
    private Preconditions() {
    }

    private static String badElementIndex(int i2, int i3, @NullableDecl String str) {
        if (i2 < 0) {
            return format("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return format("%s (%s) must be less than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException("negative size: " + i3);
    }

    private static String badPositionIndex(int i2, int i3, @NullableDecl String str) {
        if (i2 < 0) {
            return format("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return format("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException("negative size: " + i3);
    }

    private static String badPositionIndexes(int i2, int i3, int i4) {
        if (i2 < 0 || i2 > i4) {
            return badPositionIndex(i2, i4, "start index");
        }
        return (i3 < 0 || i3 > i4) ? badPositionIndex(i3, i4, "end index") : format("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2));
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i2, int i3) {
        return checkElementIndex(i2, i3, "index");
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i2, int i3) {
        return checkPositionIndex(i2, i3, "index");
    }

    public static void checkPositionIndexes(int i2, int i3, int i4) {
        if (i2 < 0 || i3 < i2 || i3 > i4) {
            throw new IndexOutOfBoundsException(badPositionIndexes(i2, i3, i4));
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String format(@NullableDecl String str, @NullableDecl Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        int i2 = 0;
        if (objArr == null) {
            objArr = new Object[]{"(Object[])null"};
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int i3 = 0;
        while (i2 < objArr.length && (indexOf = valueOf.indexOf("%s", i3)) != -1) {
            sb.append((CharSequence) valueOf, i3, indexOf);
            sb.append(objArr[i2]);
            i3 = indexOf + 2;
            i2++;
        }
        sb.append((CharSequence) valueOf, i3, valueOf.length());
        if (i2 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i2]);
            for (int i4 = i2 + 1; i4 < objArr.length; i4++) {
                sb.append(", ");
                sb.append(objArr[i4]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public static void checkArgument(boolean z, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int i2, int i3, @NullableDecl String str) {
        if (i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException(badElementIndex(i2, i3, str));
        }
        return i2;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int i2, int i3, @NullableDecl String str) {
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException(badPositionIndex(i2, i3, str));
        }
        return i2;
    }

    public static void checkState(boolean z, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(format(str, objArr));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, objArr));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object... objArr) {
        if (!z) {
            throw new IllegalStateException(format(str, objArr));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c2) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i2) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j2) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c2, char c3) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c2), Character.valueOf(c3)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c2, char c3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c2), Character.valueOf(c3)));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c2, char c3) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c2), Character.valueOf(c3)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c2, int i2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c2), Integer.valueOf(i2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c2, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c2), Integer.valueOf(i2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c2, int i2) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c2), Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c2, long j2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c2), Long.valueOf(j2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c2, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c2), Long.valueOf(j2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c2, long j2) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c2), Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, char c2, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c2), obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, char c2, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c2), obj));
    }

    public static void checkState(boolean z, @NullableDecl String str, char c2, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c2), obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i2, char c2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i2), Character.valueOf(c2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i2, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i2), Character.valueOf(c2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i2, char c2) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i2), Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i2, int i3) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i2, int i3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i2, int i3) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i2, long j2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i2), Long.valueOf(j2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i2, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i2), Long.valueOf(j2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i2, long j2) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i2), Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, int i2, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i2), obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, int i2, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i2), obj));
    }

    public static void checkState(boolean z, @NullableDecl String str, int i2, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i2), obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j2, char c2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j2), Character.valueOf(c2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j2, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j2), Character.valueOf(c2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j2, char c2) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j2), Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j2, int i2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j2), Integer.valueOf(i2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j2, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j2), Integer.valueOf(i2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j2, int i2) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j2), Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j2, long j3) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j2, long j3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j2), Long.valueOf(j3)));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j2, long j3) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, long j2, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j2), obj));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, long j2, @NullableDecl Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j2), obj));
    }

    public static void checkState(boolean z, @NullableDecl String str, long j2, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j2), obj));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, char c2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, Character.valueOf(c2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, Character.valueOf(c2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, char c2) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, int i2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, Integer.valueOf(i2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, Integer.valueOf(i2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, int i2) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, long j2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, Long.valueOf(j2)));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, Long.valueOf(j2)));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, long j2) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, obj2));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, obj2));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, obj2));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, obj2, obj3));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, obj2, obj3));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, obj2, obj3));
        }
    }

    public static void checkArgument(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, obj2, obj3, obj4));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T t, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, obj2, obj3, obj4));
    }

    public static void checkState(boolean z, @NullableDecl String str, @NullableDecl Object obj, @NullableDecl Object obj2, @NullableDecl Object obj3, @NullableDecl Object obj4) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, obj2, obj3, obj4));
        }
    }
}
