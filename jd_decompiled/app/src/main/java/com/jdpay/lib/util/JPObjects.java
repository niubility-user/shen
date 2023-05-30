package com.jdpay.lib.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes18.dex */
public class JPObjects {
    public static <T extends Collection> T cloneCollection(@NonNull T t) {
        T t2 = null;
        try {
            T t3 = (T) t.getClass().newInstance();
            try {
                Iterator it = t.iterator();
                while (it.hasNext()) {
                    t3.add(cloneObject(it.next()));
                }
                return t3;
            } catch (Exception e2) {
                e = e2;
                t2 = t3;
                e.printStackTrace();
                return t2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static <T> T cloneObject(@Nullable T t) {
        if (t == null) {
            return null;
        }
        try {
            Class<?> cls = t.getClass();
            if (cls.isPrimitive() || String.class.isAssignableFrom(cls) || cls == Boolean.class || cls == Character.class || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class) {
                return t;
            }
            if (JPCopy.class.isAssignableFrom(cls)) {
                return (T) ((JPCopy) t).copy();
            }
            if (Collection.class.isAssignableFrom(cls)) {
                return (T) cloneCollection((Collection) t);
            }
            if (cls.isArray()) {
                T t2 = (T) Array.newInstance(cls.getComponentType(), Array.getLength(t));
                int length = Array.getLength(t2);
                for (int i2 = 0; i2 < length; i2++) {
                    Array.set(t2, i2, cloneObject(Array.get(t, i2)));
                }
                return t2;
            } else if (Cloneable.class.isAssignableFrom(cls)) {
                return (T) cls.getMethod("clone", new Class[0]).invoke(t, new Object[0]);
            } else {
                T t3 = (T) cls.newInstance();
                for (Class<?> cls2 = t3.getClass(); cls2 != Object.class; cls2 = cls2.getSuperclass()) {
                    for (Field field : cls2.getDeclaredFields()) {
                        field.setAccessible(true);
                        Class<?> type = field.getType();
                        int modifiers = field.getModifiers();
                        Object obj = field.get(t);
                        if (type.isPrimitive() && !Modifier.isFinal(modifiers)) {
                            field.set(t3, obj);
                        } else if (obj == null) {
                            field.set(t3, null);
                        } else if (type.isArray()) {
                            Object newInstance = Array.newInstance(type.getComponentType(), Array.getLength(obj));
                            int length2 = Array.getLength(newInstance);
                            for (int i3 = 0; i3 < length2; i3++) {
                                Array.set(newInstance, i3, cloneObject(Array.get(obj, i3)));
                            }
                            field.set(t3, newInstance);
                        } else if (JPCopy.class.isAssignableFrom(type) && !Modifier.isFinal(modifiers)) {
                            field.set(t3, ((JPCopy) obj).copy());
                        } else if (Collection.class.isAssignableFrom(type)) {
                            field.set(t3, cloneCollection((Collection) obj));
                        } else if (Cloneable.class.isAssignableFrom(type) && !Modifier.isFinal(modifiers)) {
                            field.set(t3, cloneObject(obj));
                        } else if (!Modifier.isFinal(modifiers)) {
                            field.set(t3, obj);
                        }
                    }
                }
                return t3;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> T copy(@NonNull T t, @NonNull Object obj) {
        Class<?> cls = t.getClass();
        Class<?> cls2 = obj.getClass();
        for (Field field : cls.getFields()) {
            try {
                Field field2 = cls2.getField(field.getName());
                if (field.getType().isAssignableFrom(field2.getType())) {
                    Object obj2 = field2.get(obj);
                    field.set(t, obj2 == null ? null : cloneObject(obj2));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return t;
    }

    public static boolean isArray(@Nullable Object obj) {
        return (obj instanceof Object[]) || (obj instanceof boolean[]) || (obj instanceof byte[]) || (obj instanceof char[]) || (obj instanceof double[]) || (obj instanceof float[]) || (obj instanceof int[]) || (obj instanceof long[]) || (obj instanceof short[]);
    }

    public static boolean isSameClass(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == obj2) {
            return true;
        }
        return (obj == null || obj2 == null || obj.getClass() != obj2.getClass()) ? false : true;
    }

    public static Object newInstance(@Nullable Class cls) {
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (Throwable th) {
                JDPayLog.e(th);
                return null;
            }
        }
        return null;
    }
}
