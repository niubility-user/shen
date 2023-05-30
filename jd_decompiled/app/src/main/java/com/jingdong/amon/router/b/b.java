package com.jingdong.amon.router.b;

import com.jingdong.amon.router.annotation.RouteServiceProvider;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes18.dex */
class b {
    private static final HashMap<Class, Method> b = new HashMap<>();
    static final Method a = b.class.getDeclaredMethods()[0];

    b() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Method a(Class<T> cls) {
        HashMap<Class, Method> hashMap = b;
        Method method = hashMap.get(cls);
        if (method == null) {
            synchronized (hashMap) {
                method = hashMap.get(cls);
                if (method == null) {
                    method = b(cls);
                    hashMap.put(cls, method);
                }
            }
        }
        return method;
    }

    private static Method b(Class cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getAnnotation(RouteServiceProvider.class) != null) {
                return method;
            }
        }
        return a;
    }
}
