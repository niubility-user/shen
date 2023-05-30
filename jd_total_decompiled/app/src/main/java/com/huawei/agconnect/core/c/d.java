package com.huawei.agconnect.core.c;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public final class d {
    private static Map<Class<?>, com.huawei.agconnect.core.a> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private static Map<Class<?>, Object> f1144c = new HashMap();
    private Map<Class<?>, com.huawei.agconnect.core.a> a = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(List<com.huawei.agconnect.core.a> list, Context context) {
        new HashMap();
        c(list, context);
    }

    private static Constructor a(Class cls, Class... clsArr) {
        boolean z = false;
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == clsArr.length) {
                for (int i2 = 0; i2 < clsArr.length; i2++) {
                    z = parameterTypes[i2] == clsArr[i2];
                }
                if (z) {
                    return constructor;
                }
            }
        }
        return null;
    }

    private void b(String str, Exception exc) {
        String str2 = "Instantiate shared service " + str + exc.getLocalizedMessage();
        StringBuilder sb = new StringBuilder();
        sb.append("cause message:");
        sb.append(exc.getCause() != null ? exc.getCause().getMessage() : "");
        sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005b A[Catch: InvocationTargetException -> 0x0076, InstantiationException -> 0x007a, IllegalAccessException -> 0x007e, TryCatch #2 {IllegalAccessException -> 0x007e, InstantiationException -> 0x007a, InvocationTargetException -> 0x0076, blocks: (B:20:0x0049, B:22:0x005b, B:24:0x006c, B:23:0x0064), top: B:34:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064 A[Catch: InvocationTargetException -> 0x0076, InstantiationException -> 0x007a, IllegalAccessException -> 0x007e, TryCatch #2 {IllegalAccessException -> 0x007e, InstantiationException -> 0x007a, InvocationTargetException -> 0x0076, blocks: (B:20:0x0049, B:22:0x005b, B:24:0x006c, B:23:0x0064), top: B:34:0x0049 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void c(List<com.huawei.agconnect.core.a> list, Context context) {
        Map<Class<?>, com.huawei.agconnect.core.a> map;
        String str;
        if (list == null) {
            return;
        }
        for (com.huawei.agconnect.core.a aVar : list) {
            if (aVar.d()) {
                if (!b.containsKey(aVar.a())) {
                    map = b;
                }
                if (aVar.c() && aVar.b() != null && !f1144c.containsKey(aVar.a())) {
                    try {
                        Constructor a = a(aVar.b(), Context.class);
                        f1144c.put(aVar.a(), a == null ? a.newInstance(context) : aVar.b().newInstance());
                    } catch (IllegalAccessException e2) {
                        e = e2;
                        str = "AccessException";
                        b(str, e);
                    } catch (InstantiationException e3) {
                        e = e3;
                        str = "InstantiationException";
                        b(str, e);
                    } catch (InvocationTargetException e4) {
                        e = e4;
                        str = "TargetException";
                        b(str, e);
                    }
                }
            } else {
                map = this.a;
            }
            map.put(aVar.a(), aVar);
            if (aVar.c()) {
                Constructor a2 = a(aVar.b(), Context.class);
                f1144c.put(aVar.a(), a2 == null ? a2.newInstance(context) : aVar.b().newInstance());
            }
        }
    }
}
