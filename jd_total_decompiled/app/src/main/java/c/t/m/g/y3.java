package c.t.m.g;

import android.content.Context;

/* loaded from: classes.dex */
public class y3 {
    public static volatile Context a;

    public static final synchronized Context a() {
        Context context;
        synchronized (y3.class) {
            if (a == null) {
                throw new NullPointerException("u should init first.");
            }
            context = a;
        }
        return context;
    }

    public static final synchronized void b(Context context) {
        synchronized (y3.class) {
            if (a == null || a.getApplicationContext() == null) {
                if (context == null || context.getApplicationContext() == null) {
                    throw new NullPointerException("context cannot be null.");
                }
                a = context.getApplicationContext();
                d4.b();
            }
        }
    }

    public static final void c(boolean z) {
    }
}
