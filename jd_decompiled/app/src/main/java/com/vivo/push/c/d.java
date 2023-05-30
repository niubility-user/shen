package com.vivo.push.c;

import android.content.Context;
import com.vivo.push.util.ContextDelegate;

/* loaded from: classes11.dex */
public class d {
    private static volatile d d;
    private b a;
    private c b;

    /* renamed from: c  reason: collision with root package name */
    private Context f18253c;

    private d(Context context) {
        if (this.a == null) {
            this.f18253c = ContextDelegate.getContext(context.getApplicationContext());
            this.a = new e(this.f18253c);
        }
        if (this.b == null) {
            this.b = new a();
        }
    }

    public static d a(Context context) {
        if (d == null) {
            synchronized (d.class) {
                if (d == null && context != null) {
                    d = new d(context);
                }
            }
        }
        return d;
    }

    public final b a() {
        return this.a;
    }
}
