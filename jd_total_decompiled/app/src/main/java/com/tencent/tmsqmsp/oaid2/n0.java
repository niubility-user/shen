package com.tencent.tmsqmsp.oaid2;

import android.content.Context;
import java.lang.reflect.Method;

/* loaded from: classes9.dex */
public class n0 implements b {
    public static Class b;

    /* renamed from: c  reason: collision with root package name */
    public static Object f18090c;
    public static Method d;

    /* renamed from: e  reason: collision with root package name */
    public static Method f18091e;
    public Context a;

    public n0() {
        try {
            c.c("xm start");
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            b = cls;
            f18090c = cls.newInstance();
            d = b.getMethod("getOAID", Context.class);
            f18091e = b.getMethod("getAAID", Context.class);
        } catch (Exception e2) {
            c.a("xm reflect exception!" + e2);
        }
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public String a() {
        Method method;
        Object obj = f18090c;
        if (obj == null || (method = d) == null) {
            return null;
        }
        return a(this.a, obj, method);
    }

    public final String a(Context context, Object obj, Method method) {
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(obj, context);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public void a(Context context, IVendorCallback iVendorCallback) {
        this.a = context;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public String d() {
        Method method;
        Object obj = f18090c;
        if (obj == null || (method = f18091e) == null) {
            return null;
        }
        return a(this.a, obj, method);
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public boolean e() {
        return (b == null || f18090c == null) ? false : true;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public void j() {
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public boolean k() {
        return true;
    }

    @Override // com.tencent.tmsqmsp.oaid2.b
    public void l() {
    }
}
