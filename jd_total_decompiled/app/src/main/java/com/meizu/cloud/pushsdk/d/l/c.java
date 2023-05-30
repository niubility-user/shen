package com.meizu.cloud.pushsdk.d.l;

import com.meizu.cloud.pushinternal.DebugLogger;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class c {
    private static final HashMap<String, Method> d = new HashMap<>();
    private final com.meizu.cloud.pushsdk.d.l.a a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private Class<?>[] f15728c;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes14.dex */
    public class a {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(com.meizu.cloud.pushsdk.d.l.a aVar, String str, Class<?>... clsArr) {
        this.a = aVar;
        this.b = str;
        this.f15728c = clsArr;
    }

    private Class<?> c(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        return cls.isPrimitive() ? Boolean.TYPE == cls ? Boolean.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : Byte.TYPE == cls ? Byte.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Character.TYPE == cls ? Character.class : Void.TYPE == cls ? Void.class : cls : cls;
    }

    private String d() throws ClassNotFoundException {
        StringBuilder sb = new StringBuilder(this.a.e().getName());
        sb.append(this.b);
        for (Class<?> cls : this.f15728c) {
            sb.append(cls.getName());
        }
        return sb.toString();
    }

    private boolean e(Method method, String str, Class<?>[] clsArr) {
        return method.getName().equals(str) && f(method.getParameterTypes(), clsArr);
    }

    private boolean f(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length == clsArr2.length) {
            for (int i2 = 0; i2 < clsArr2.length; i2++) {
                if (clsArr2[i2] != a.class && !c(clsArr[i2]).isAssignableFrom(c(clsArr2[i2]))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private Method g() throws NoSuchMethodException, ClassNotFoundException {
        Class<?> e2 = this.a.e();
        for (Method method : e2.getMethods()) {
            if (e(method, this.b, this.f15728c)) {
                return method;
            }
        }
        for (Method method2 : e2.getDeclaredMethods()) {
            if (e(method2, this.b, this.f15728c)) {
                return method2;
            }
        }
        throw new NoSuchMethodException("No similar method " + this.b + " with params " + Arrays.toString(this.f15728c) + " could be found on type " + e2);
    }

    public <T> d<T> a(Object obj, Object... objArr) {
        d<T> dVar = new d<>();
        try {
            String d2 = d();
            Method method = d.get(d2);
            if (method == null) {
                if (this.f15728c.length == objArr.length) {
                    method = this.a.e().getMethod(this.b, this.f15728c);
                } else {
                    if (objArr.length > 0) {
                        this.f15728c = new Class[objArr.length];
                        for (int i2 = 0; i2 < objArr.length; i2++) {
                            this.f15728c[i2] = objArr[i2].getClass();
                        }
                    }
                    method = g();
                }
                d.put(d2, method);
            }
            method.setAccessible(true);
            dVar.b = (T) method.invoke(obj, objArr);
            dVar.a = true;
        } catch (Exception e2) {
            DebugLogger.d("ReflectMethod", "invoke exception, " + e2.getMessage());
        }
        return dVar;
    }

    public <T> d<T> b(Object... objArr) {
        try {
            return a(this.a.e(), objArr);
        } catch (ClassNotFoundException unused) {
            return new d<>();
        }
    }
}
