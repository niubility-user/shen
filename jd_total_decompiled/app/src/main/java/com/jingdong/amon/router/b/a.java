package com.jingdong.amon.router.b;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.amon.router.module.RouteMeta;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes18.dex */
public class a {
    private static String a = "ServiceManager";
    private static Context b;

    /* renamed from: c  reason: collision with root package name */
    private static final Map<Class, Object> f7584c = new HashMap();
    private static final Map<Class, Object> d = new HashMap();

    public static Object a(Uri uri) {
        RouteMeta a2 = com.jingdong.amon.router.b.a(uri);
        if (a2 == null) {
            return null;
        }
        return a(Object.class, a2);
    }

    public static <T> T a(Class<T> cls) {
        Map<Class, Object> map = d;
        if (map.containsKey(cls)) {
            return cls.cast(map.get(cls));
        }
        return null;
    }

    public static <T> T a(Class<T> cls, Uri uri) {
        RouteMeta a2 = com.jingdong.amon.router.b.a(uri);
        if (a2 == null) {
            return null;
        }
        return (T) a((Class) cls, a2);
    }

    private static <T> T a(Class<T> cls, RouteMeta routeMeta) {
        try {
            Class<?> cls2 = routeMeta.annotatedClass;
            if (routeMeta.singleton) {
                Map<Class, Object> map = f7584c;
                if (map.containsKey(cls2)) {
                    return cls.cast(map.get(cls2));
                }
                synchronized (map) {
                    if (map.containsKey(cls2)) {
                        return cls.cast(map.get(cls2));
                    }
                    T t = (T) b(cls2, routeMeta);
                    if (t == null) {
                        return null;
                    }
                    map.put(cls2, t);
                    return t;
                }
            }
            return (T) b(cls2, routeMeta);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void a(Application application) {
        b = application.getBaseContext();
    }

    public static <T> void a(Class<T> cls, T t) {
        d.put(cls, t);
    }

    public static Class b(Uri uri) {
        RouteMeta a2 = com.jingdong.amon.router.b.a(uri);
        if (a2 == null) {
            return null;
        }
        return a2.annotatedClass;
    }

    private static <T> T b(Class<T> cls, RouteMeta routeMeta) {
        Method a2 = b.a(cls);
        return a2 != b.a ? (T) a2.invoke(null, new Object[0]) : AnnoConst.Constructor_Context.equals(routeMeta.constructor) ? cls.getConstructor(Context.class).newInstance(b) : cls.newInstance();
    }
}
