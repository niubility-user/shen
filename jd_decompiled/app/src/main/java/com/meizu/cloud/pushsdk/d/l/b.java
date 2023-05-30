package com.meizu.cloud.pushsdk.d.l;

import com.meizu.cloud.pushinternal.DebugLogger;
import java.lang.reflect.Constructor;

/* loaded from: classes14.dex */
public class b {
    private final a a;
    private final Class<?>[] b;

    public b(a aVar, Class<?>... clsArr) {
        this.a = aVar;
        this.b = clsArr;
    }

    public <T> d<T> a(Object... objArr) {
        d<T> dVar = new d<>();
        try {
            Constructor<?> declaredConstructor = this.a.e().getDeclaredConstructor(this.b);
            declaredConstructor.setAccessible(true);
            dVar.b = (T) declaredConstructor.newInstance(objArr);
            dVar.a = true;
        } catch (Exception e2) {
            DebugLogger.e("ReflectConstructor", "newInstance", e2);
        }
        return dVar;
    }
}
