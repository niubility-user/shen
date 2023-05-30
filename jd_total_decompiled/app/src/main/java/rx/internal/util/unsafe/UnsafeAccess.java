package rx.internal.util.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* loaded from: classes11.dex */
public final class UnsafeAccess {
    private static final boolean DISABLED_BY_USER;
    public static final Unsafe UNSAFE;

    static {
        DISABLED_BY_USER = System.getProperty("rx.unsafe-disable") != null;
        Unsafe unsafe = null;
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            unsafe = (Unsafe) declaredField.get(null);
        } catch (Throwable unused) {
        }
        UNSAFE = unsafe;
    }

    private UnsafeAccess() {
        throw new IllegalStateException("No instances!");
    }

    public static long addressOf(Class<?> cls, String str) {
        try {
            return UNSAFE.objectFieldOffset(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e2) {
            InternalError internalError = new InternalError();
            internalError.initCause(e2);
            throw internalError;
        }
    }

    public static boolean compareAndSwapInt(Object obj, long j2, int i2, int i3) {
        return UNSAFE.compareAndSwapInt(obj, j2, i2, i3);
    }

    public static int getAndAddInt(Object obj, long j2, int i2) {
        Unsafe unsafe;
        int intVolatile;
        do {
            unsafe = UNSAFE;
            intVolatile = unsafe.getIntVolatile(obj, j2);
        } while (!unsafe.compareAndSwapInt(obj, j2, intVolatile, intVolatile + i2));
        return intVolatile;
    }

    public static int getAndIncrementInt(Object obj, long j2) {
        Unsafe unsafe;
        int intVolatile;
        do {
            unsafe = UNSAFE;
            intVolatile = unsafe.getIntVolatile(obj, j2);
        } while (!unsafe.compareAndSwapInt(obj, j2, intVolatile, intVolatile + 1));
        return intVolatile;
    }

    public static int getAndSetInt(Object obj, long j2, int i2) {
        Unsafe unsafe;
        int intVolatile;
        do {
            unsafe = UNSAFE;
            intVolatile = unsafe.getIntVolatile(obj, j2);
        } while (!unsafe.compareAndSwapInt(obj, j2, intVolatile, i2));
        return intVolatile;
    }

    public static boolean isUnsafeAvailable() {
        return (UNSAFE == null || DISABLED_BY_USER) ? false : true;
    }
}
