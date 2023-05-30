package com.huawei.agconnect.core.c;

import android.content.Context;
import java.lang.reflect.Constructor;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(java.util.List<com.huawei.agconnect.core.a> r7, android.content.Context r8) {
        /*
            r6 = this;
            if (r7 != 0) goto L3
            return
        L3:
            java.util.Iterator r7 = r7.iterator()
        L7:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L85
            java.lang.Object r0 = r7.next()
            com.huawei.agconnect.core.a r0 = (com.huawei.agconnect.core.a) r0
            boolean r1 = r0.d()
            if (r1 == 0) goto L28
            java.util.Map<java.lang.Class<?>, com.huawei.agconnect.core.a> r1 = com.huawei.agconnect.core.c.d.b
            java.lang.Class r2 = r0.a()
            boolean r1 = r1.containsKey(r2)
            if (r1 != 0) goto L31
            java.util.Map<java.lang.Class<?>, com.huawei.agconnect.core.a> r1 = com.huawei.agconnect.core.c.d.b
            goto L2a
        L28:
            java.util.Map<java.lang.Class<?>, com.huawei.agconnect.core.a> r1 = r6.a
        L2a:
            java.lang.Class r2 = r0.a()
            r1.put(r2, r0)
        L31:
            boolean r1 = r0.c()
            if (r1 == 0) goto L7
            java.lang.Class r1 = r0.b()
            if (r1 == 0) goto L7
            java.util.Map<java.lang.Class<?>, java.lang.Object> r1 = com.huawei.agconnect.core.c.d.f1144c
            java.lang.Class r2 = r0.a()
            boolean r1 = r1.containsKey(r2)
            if (r1 != 0) goto L7
            java.lang.Class r1 = r0.b()     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.reflect.Constructor r1 = a(r1, r3)     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            if (r1 == 0) goto L64
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            r2[r5] = r8     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.Object r1 = r1.newInstance(r2)     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            goto L6c
        L64:
            java.lang.Class r1 = r0.b()     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.Object r1 = r1.newInstance()     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
        L6c:
            java.util.Map<java.lang.Class<?>, java.lang.Object> r2 = com.huawei.agconnect.core.c.d.f1144c     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            java.lang.Class r0 = r0.a()     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            r2.put(r0, r1)     // Catch: java.lang.reflect.InvocationTargetException -> L76 java.lang.InstantiationException -> L7a java.lang.IllegalAccessException -> L7e
            goto L7
        L76:
            r0 = move-exception
            java.lang.String r1 = "TargetException"
            goto L81
        L7a:
            r0 = move-exception
            java.lang.String r1 = "InstantiationException"
            goto L81
        L7e:
            r0 = move-exception
            java.lang.String r1 = "AccessException"
        L81:
            r6.b(r1, r0)
            goto L7
        L85:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.core.c.d.c(java.util.List, android.content.Context):void");
    }
}
