package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.util.HashMap;

/* loaded from: classes11.dex */
public final class r3 {

    /* renamed from: g */
    private static volatile r3 f18969g;
    private Context a;
    private HashMap<t3, u3> b;

    /* renamed from: c */
    private String f18970c;
    private String d;

    /* renamed from: e */
    private int f18971e;

    /* renamed from: f */
    private v3 f18972f;

    private r3(Context context) {
        HashMap<t3, u3> hashMap = new HashMap<>();
        this.b = hashMap;
        this.a = context;
        hashMap.put(t3.SERVICE_ACTION, new x3());
        this.b.put(t3.SERVICE_COMPONENT, new y3());
        this.b.put(t3.ACTIVITY, new p3());
        this.b.put(t3.PROVIDER, new w3());
    }

    public static r3 b(Context context) {
        if (f18969g == null) {
            synchronized (r3.class) {
                if (f18969g == null) {
                    f18969g = new r3(context);
                }
            }
        }
        return f18969g;
    }

    public void i(t3 t3Var, Context context, q3 q3Var) {
        this.b.get(t3Var).b(context, q3Var);
    }

    public static boolean m(Context context) {
        return com.xiaomi.push.service.n.G(context, context.getPackageName());
    }

    public int a() {
        return this.f18971e;
    }

    public v3 c() {
        return this.f18972f;
    }

    public String d() {
        return this.f18970c;
    }

    public void e(int i2) {
        this.f18971e = i2;
    }

    public void f(Context context, String str, int i2, String str2, String str3) {
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            e(i2);
            i.b(this.a).g(new s3(this, str, context, str2, str3));
            return;
        }
        n3.a(context, "" + str, 1008, "A receive a incorrect message");
    }

    public void h(t3 t3Var, Context context, Intent intent, String str) {
        if (t3Var != null) {
            this.b.get(t3Var).a(context, intent, str);
        } else {
            n3.a(context, DYConstants.DY_NULL_STR, 1008, "A receive a incorrect message with empty type");
        }
    }

    public void j(v3 v3Var) {
        this.f18972f = v3Var;
    }

    public void k(String str) {
        this.f18970c = str;
    }

    public void l(String str, String str2, int i2, v3 v3Var) {
        k(str);
        o(str2);
        e(i2);
        j(v3Var);
    }

    public String n() {
        return this.d;
    }

    public void o(String str) {
        this.d = str;
    }
}
