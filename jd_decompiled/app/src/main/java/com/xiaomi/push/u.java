package com.xiaomi.push;

import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class u implements t, InvocationHandler {
    private static final String[][] r = {new String[]{"com.bun.supplier.IIdentifierListener", "com.bun.supplier.IdSupplier"}, new String[]{"com.bun.miitmdid.core.IIdentifierListener", "com.bun.miitmdid.supplier.IdSupplier"}};

    /* renamed from: m  reason: collision with root package name */
    private Context f19248m;

    /* renamed from: g  reason: collision with root package name */
    private Class f19242g = null;

    /* renamed from: h  reason: collision with root package name */
    private Class f19243h = null;

    /* renamed from: i  reason: collision with root package name */
    private Method f19244i = null;

    /* renamed from: j  reason: collision with root package name */
    private Method f19245j = null;

    /* renamed from: k  reason: collision with root package name */
    private Method f19246k = null;

    /* renamed from: l  reason: collision with root package name */
    private Method f19247l = null;

    /* renamed from: n  reason: collision with root package name */
    private final Object f19249n = new Object();
    private volatile int o = 0;
    private volatile long p = 0;
    private volatile a q = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class a {
        Boolean a;
        String b;

        /* renamed from: c  reason: collision with root package name */
        String f19250c;
        String d;

        /* renamed from: e  reason: collision with root package name */
        String f19251e;

        private a(u uVar) {
            this.a = null;
            this.b = null;
            this.f19250c = null;
            this.d = null;
            this.f19251e = null;
        }

        boolean a() {
            if (!TextUtils.isEmpty(this.b) || !TextUtils.isEmpty(this.f19250c) || !TextUtils.isEmpty(this.d) || !TextUtils.isEmpty(this.f19251e)) {
                this.a = Boolean.TRUE;
            }
            return this.a != null;
        }
    }

    public u(Context context) {
        this.f19248m = context.getApplicationContext();
        e(context);
        h(context);
    }

    private static Class<?> a(Context context, String str) {
        try {
            return r9.c(context, str);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static <T> T b(Method method, Object obj, Object... objArr) {
        if (method != null) {
            try {
                T t = (T) method.invoke(obj, objArr);
                if (t != null) {
                    return t;
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    private static Method c(Class<?> cls, String str, Class<?>... clsArr) {
        if (cls != null) {
            try {
                return cls.getMethod(str, clsArr);
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    private void d() {
        synchronized (this.f19249n) {
            try {
                this.f19249n.notifyAll();
            } catch (Exception unused) {
            }
        }
    }

    private void e(Context context) {
        Class<?> a2 = a(context, "com.bun.miitmdid.core.MdidSdk");
        Class<?> cls = null;
        Class<?> cls2 = null;
        int i2 = 0;
        while (true) {
            String[][] strArr = r;
            if (i2 >= strArr.length) {
                break;
            }
            String[] strArr2 = strArr[i2];
            Class<?> a3 = a(context, strArr2[0]);
            Class<?> a4 = a(context, strArr2[1]);
            if (a3 != null && a4 != null) {
                i("found class in index " + i2);
                cls2 = a4;
                cls = a3;
                break;
            }
            i2++;
            cls2 = a4;
            cls = a3;
        }
        this.f19242g = a2;
        this.f19244i = c(a2, "InitSdk", Context.class, cls);
        this.f19243h = cls;
        this.f19245j = c(cls2, "getOAID", new Class[0]);
        this.f19246k = c(cls2, "isSupported", new Class[0]);
        this.f19247l = c(cls2, "shutDown", new Class[0]);
    }

    private void f(String str) {
        if (this.q != null) {
            return;
        }
        long j2 = this.p;
        long elapsedRealtime = SystemClock.elapsedRealtime() - Math.abs(j2);
        int i2 = this.o;
        if (elapsedRealtime > 3000 && i2 < 3) {
            synchronized (this.f19249n) {
                if (this.p == j2 && this.o == i2) {
                    i("retry, current count is " + i2);
                    this.o = this.o + 1;
                    h(this.f19248m);
                    j2 = this.p;
                    elapsedRealtime = SystemClock.elapsedRealtime() - Math.abs(j2);
                }
            }
        }
        if (this.q != null || j2 < 0 || elapsedRealtime > 3000 || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        synchronized (this.f19249n) {
            if (this.q == null) {
                try {
                    i(str + " wait...");
                    this.f19249n.wait(3000L);
                } catch (Exception unused) {
                }
            }
        }
    }

    private static boolean g(Object obj) {
        return (obj instanceof Boolean) || (obj instanceof Character) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double);
    }

    private void h(Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = -elapsedRealtime;
        Class cls = this.f19243h;
        if (cls != null) {
            try {
                ClassLoader classLoader = cls.getClassLoader();
                if (classLoader == null) {
                    classLoader = context.getClassLoader();
                }
                b(this.f19244i, this.f19242g.newInstance(), context, Proxy.newProxyInstance(classLoader, new Class[]{this.f19243h}, this));
            } catch (Throwable th) {
                i("call init sdk error:" + th);
            }
            this.p = elapsedRealtime;
        }
        elapsedRealtime = j2;
        this.p = elapsedRealtime;
    }

    private static void i(String str) {
        g.j.a.a.a.c.o("mdid:" + str);
    }

    @Override // com.xiaomi.push.t
    public String a() {
        f("getOAID");
        if (this.q == null) {
            return null;
        }
        return this.q.f19250c;
    }

    @Override // com.xiaomi.push.t
    /* renamed from: a */
    public boolean mo30a() {
        f("isSupported");
        return this.q != null && Boolean.TRUE.equals(this.q.a);
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        this.p = SystemClock.elapsedRealtime();
        if (objArr != null) {
            a aVar = new a();
            int length = objArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                Object obj2 = objArr[i2];
                if (obj2 != null && !g(obj2)) {
                    aVar.f19250c = (String) b(this.f19245j, obj2, new Object[0]);
                    aVar.a = (Boolean) b(this.f19246k, obj2, new Object[0]);
                    b(this.f19247l, obj2, new Object[0]);
                    if (aVar.a()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("has get succ, check duplicate:");
                        sb.append(this.q != null);
                        i(sb.toString());
                        synchronized (u.class) {
                            if (this.q == null) {
                                this.q = aVar;
                            }
                        }
                    }
                }
                i2++;
            }
        }
        d();
        return null;
    }
}
