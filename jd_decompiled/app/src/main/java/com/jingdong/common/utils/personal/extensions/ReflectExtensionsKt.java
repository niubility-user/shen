package com.jingdong.common.utils.personal.extensions;

import com.jingdong.common.search.FilterConstant;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\u001a\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a%\u0010\u0007\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0006*\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0004\b\u0007\u0010\b\u001a#\u0010\u000b\u001a\u00020\n*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\t\u001a\u0004\u0018\u00010\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001a\u001b\u0010\u000e\u001a\u0004\u0018\u00010\r*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a=\u0010\u0012\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0006*\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00000\u0010\"\u0004\u0018\u00010\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"", "", "name", "Ljava/lang/reflect/Field;", FilterConstant.FIELD, "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/reflect/Field;", "T", "getFieldValue", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", "value", "", "setFieldValue", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V", "Ljava/lang/reflect/Method;", "method", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/reflect/Method;", "", "args", "invokeMethod", "(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", "personallib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class ReflectExtensionsKt {
    @Nullable
    public static final Field field(@NotNull Object obj, @NotNull String str) {
        try {
            Field it = (obj instanceof Class ? (Class) obj : obj.getClass()).getDeclaredField(str);
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            it.setAccessible(true);
            return it;
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public static final <T> T getFieldValue(@NotNull Object obj, @NotNull String str) {
        Field field = field(obj, str);
        if (field != null) {
            if (obj instanceof Class) {
                obj = null;
            }
            try {
                return (T) field.get(obj);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    @Nullable
    public static final <T> T invokeMethod(@NotNull Object obj, @NotNull String str, @NotNull Object... objArr) {
        Method method = method(obj, str);
        if (method != null) {
            if (obj instanceof Class) {
                obj = null;
            }
            try {
                return (T) method.invoke(obj, Arrays.copyOf(objArr, objArr.length));
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Nullable
    public static final Method method(@NotNull Object obj, @NotNull String str) {
        Method method;
        Method[] declaredMethods = (obj instanceof Class ? (Class) obj : obj.getClass()).getDeclaredMethods();
        Intrinsics.checkExpressionValueIsNotNull(declaredMethods, "clazz.declaredMethods");
        int length = declaredMethods.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                method = null;
                break;
            }
            method = declaredMethods[i2];
            Intrinsics.checkExpressionValueIsNotNull(method, "method");
            if (Intrinsics.areEqual(method.getName(), str)) {
                break;
            }
            i2++;
        }
        if (method != null) {
            method.setAccessible(true);
            return method;
        }
        return null;
    }

    public static final void setFieldValue(@NotNull Object obj, @NotNull String str, @Nullable Object obj2) {
        Field field = field(obj, str);
        if (field != null) {
            if (obj instanceof Class) {
                obj = null;
            }
            field.set(obj, obj2);
        }
    }
}
