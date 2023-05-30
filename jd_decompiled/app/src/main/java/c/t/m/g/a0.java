package c.t.m.g;

import android.content.Context;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class a0 {
    public Context a;
    public Class b;

    /* renamed from: c  reason: collision with root package name */
    public Object f287c;
    public Method d;

    public a0(Context context) {
        this.a = context;
        try {
            Class<?> cls = Class.forName("com.android.id.impl.IdProviderImpl");
            this.b = cls;
            this.f287c = cls.newInstance();
        } catch (Exception unused) {
        }
        try {
            this.d = this.b.getMethod("getOAID", Context.class);
        } catch (Exception unused2) {
        }
    }

    public String a() {
        return b(this.a, this.d);
    }

    public final String b(Context context, Method method) {
        Object obj = this.f287c;
        if (obj != null && method != null) {
            try {
                return (String) method.invoke(obj, context);
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
