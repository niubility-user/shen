package c.t.m.g;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class y6 {
    public final Class<?> a;
    public final Method b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f792c;

    public y6(Class<?> cls, Method method, Object obj, boolean z) {
        this.a = cls;
        this.b = method;
        this.f792c = obj;
    }

    public Method a() {
        return this.b;
    }

    public boolean b(Object obj) {
        return obj.getClass().equals(this.a);
    }

    public Object c() {
        return this.f792c;
    }
}
