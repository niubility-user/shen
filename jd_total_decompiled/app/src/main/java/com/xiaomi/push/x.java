package com.xiaomi.push;

import android.content.Context;
import java.lang.reflect.Method;

/* loaded from: classes11.dex */
class x implements t {

    /* renamed from: g  reason: collision with root package name */
    private Context f19298g;

    /* renamed from: h  reason: collision with root package name */
    private Class<?> f19299h;

    /* renamed from: i  reason: collision with root package name */
    private Object f19300i;

    /* renamed from: j  reason: collision with root package name */
    private Method f19301j = null;

    public x(Context context) {
        this.f19298g = context;
        c(context);
    }

    private String b(Context context, Method method) {
        Object obj = this.f19300i;
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(obj, context);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Exception e2) {
            g.j.a.a.a.c.q("miui invoke error", e2);
            return null;
        }
    }

    private void c(Context context) {
        try {
            Class<?> c2 = r9.c(context, "com.android.id.impl.IdProviderImpl");
            this.f19299h = c2;
            this.f19300i = c2.newInstance();
            this.f19301j = this.f19299h.getMethod("getOAID", Context.class);
        } catch (Exception e2) {
            g.j.a.a.a.c.q("miui load class error", e2);
        }
    }

    @Override // com.xiaomi.push.t
    public String a() {
        return b(this.f19298g, this.f19301j);
    }

    @Override // com.xiaomi.push.t
    /* renamed from: a */
    public boolean mo30a() {
        return (this.f19299h == null || this.f19300i == null) ? false : true;
    }
}
