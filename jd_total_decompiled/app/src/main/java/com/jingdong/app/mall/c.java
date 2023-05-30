package com.jingdong.app.mall;

import android.content.Intent;
import com.doupo.linker.a;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class c {
    public static long a;
    public static a.C0019a b;

    /* renamed from: c  reason: collision with root package name */
    private static final AtomicBoolean f8311c = new AtomicBoolean(false);

    public static Intent a() {
        a.C0019a c0019a = b;
        if (c0019a != null) {
            return c0019a.a();
        }
        return null;
    }

    public static long b() {
        return a;
    }

    public static void c(a.C0019a c0019a) {
        if (f8311c.getAndSet(true)) {
            return;
        }
        b = c0019a;
    }
}
