package com.tencent.mapsdk.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes9.dex */
public class d7 {
    public static <T> Class<? extends T> a(String str, Class<T> cls, ClassLoader classLoader) {
        try {
            Class<? extends T> cls2 = (Class<? extends T>) Class.forName(str, false, classLoader);
            if (cls.isAssignableFrom(cls2)) {
                return cls2;
            }
            return null;
        } catch (ClassNotFoundException e2) {
            ma.b(e2.getMessage(), e2);
            return null;
        }
    }

    public static Class a(String str, ClassLoader classLoader) {
        try {
            return Class.forName(str, false, classLoader);
        } catch (ClassNotFoundException e2) {
            ma.b(e2.getMessage(), e2);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T a(Class<T> cls, Object... objArr) {
        String message;
        InstantiationException instantiationException;
        String message2;
        InvocationTargetException invocationTargetException;
        if (cls != null && !cls.isEnum() && !cls.isInterface() && !cls.isAnnotation() && !cls.isArray()) {
            Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
            for (int i2 = 0; i2 < declaredConstructors.length; i2++) {
                if (a(declaredConstructors[i2].getParameterTypes(), objArr)) {
                    try {
                        declaredConstructors[i2].setAccessible(true);
                        return (T) declaredConstructors[i2].newInstance(objArr);
                    } catch (IllegalAccessException e2) {
                        message2 = e2.getMessage();
                        invocationTargetException = e2;
                        ma.b(message2, invocationTargetException);
                    } catch (InstantiationException e3) {
                        message2 = e3.getMessage();
                        invocationTargetException = e3;
                        ma.b(message2, invocationTargetException);
                    } catch (InvocationTargetException e4) {
                        message2 = e4.getMessage();
                        invocationTargetException = e4;
                        ma.b(message2, invocationTargetException);
                    }
                }
            }
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e5) {
                message = e5.getMessage();
                instantiationException = e5;
                ma.b(message, instantiationException);
                return null;
            } catch (InstantiationException e6) {
                message = e6.getMessage();
                instantiationException = e6;
                ma.b(message, instantiationException);
                return null;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object a(Object obj, String str) {
        String message;
        NoSuchFieldException noSuchFieldException;
        if (obj == null) {
            return null;
        }
        Class<?> cls = obj.getClass();
        if (obj instanceof Class) {
            cls = (Class) obj;
        }
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return Modifier.isStatic(declaredField.getModifiers()) ? declaredField.get(cls) : declaredField.get(obj);
        } catch (IllegalAccessException e2) {
            message = e2.getMessage();
            noSuchFieldException = e2;
            ma.b(message, noSuchFieldException);
            return null;
        } catch (NoSuchFieldException e3) {
            message = e3.getMessage();
            noSuchFieldException = e3;
            ma.b(message, noSuchFieldException);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object a(Object obj, String str, Class[] clsArr, Object[] objArr) {
        String message;
        InvocationTargetException invocationTargetException;
        if (obj == null) {
            return null;
        }
        try {
            Method a = a((Class) obj.getClass(), str, clsArr);
            if (a != null) {
                a.setAccessible(true);
                return a.invoke(obj, objArr);
            }
        } catch (IllegalAccessException e2) {
            message = e2.getMessage();
            invocationTargetException = e2;
            ma.b(message, invocationTargetException);
            return null;
        } catch (InvocationTargetException e3) {
            message = e3.getMessage();
            invocationTargetException = e3;
            ma.b(message, invocationTargetException);
            return null;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object a(Object obj, String str, Object... objArr) {
        String message;
        InvocationTargetException invocationTargetException;
        if (obj == null) {
            return null;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i2 = 0; i2 < length; i2++) {
            clsArr[i2] = objArr[i2].getClass();
        }
        try {
            Method a = a((Class) obj.getClass(), str, clsArr);
            if (a != null) {
                a.setAccessible(true);
                return a.invoke(obj, objArr);
            }
        } catch (IllegalAccessException e2) {
            message = e2.getMessage();
            invocationTargetException = e2;
            ma.b(message, invocationTargetException);
            return null;
        } catch (InvocationTargetException e3) {
            message = e3.getMessage();
            invocationTargetException = e3;
            ma.b(message, invocationTargetException);
            return null;
        }
        return null;
    }

    public static Method a(Class cls, String str, Class[] clsArr) {
        try {
            return cls.getDeclaredMethod(str, clsArr);
        } catch (NoSuchMethodException e2) {
            if (cls.getSuperclass() != Object.class) {
                return a(cls.getSuperclass(), str, clsArr);
            }
            ma.b(e2.getMessage(), e2);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Object obj, String str, Object obj2) {
        String message;
        NoSuchFieldException noSuchFieldException;
        if (obj == null) {
            return;
        }
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException e2) {
            message = e2.getMessage();
            noSuchFieldException = e2;
            ma.b(message, noSuchFieldException);
        } catch (NoSuchFieldException e3) {
            message = e3.getMessage();
            noSuchFieldException = e3;
            ma.b(message, noSuchFieldException);
        }
    }

    private static boolean a(Class<?>[] clsArr, Object[] objArr) {
        if (clsArr.length != objArr.length) {
            return false;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < clsArr.length; i3++) {
            if (clsArr[i3] == objArr[i3].getClass() || clsArr[i3].isAssignableFrom(objArr[i3].getClass())) {
                i2++;
            }
        }
        return i2 == clsArr.length;
    }
}
