package com.xiaomi.push;

import android.content.Context;

/* loaded from: classes11.dex */
public class x0 {

    /* renamed from: c  reason: collision with root package name */
    private static volatile x0 f19302c;
    private Context a;
    private z0 b;

    private x0(Context context) {
        this.a = context;
    }

    public static x0 a(Context context) {
        if (f19302c == null) {
            synchronized (x0.class) {
                if (f19302c == null) {
                    f19302c = new x0(context);
                }
            }
        }
        return f19302c;
    }

    public void b(String str, String str2, Boolean bool) {
        if (this.b != null) {
            if (bool.booleanValue()) {
                this.b.a(this.a, str2, str);
            } else {
                this.b.b(this.a, str2, str);
            }
        }
    }
}
