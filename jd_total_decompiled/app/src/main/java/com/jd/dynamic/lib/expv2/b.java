package com.jd.dynamic.lib.expv2;

import androidx.collection.LruCache;
import com.jd.dynamic.lib.expv2.c.i;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes13.dex */
public final class b {

    /* renamed from: c  reason: collision with root package name */
    public static final b f2233c = new b();
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static final HashMap<String, i> b = new HashMap<>();

    private b() {
    }

    public final void a() {
        a.set(false);
        b.clear();
    }

    public final void b(int i2) {
        AtomicBoolean atomicBoolean = a;
        if (atomicBoolean.get()) {
            return;
        }
        new LruCache(i2);
        atomicBoolean.set(true);
    }
}
