package com.jd.dynamic.b.g;

import java.util.concurrent.atomic.AtomicLong;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public abstract class f {
    private final AtomicLong a = new AtomicLong(0);

    private final void c(c cVar) {
        cVar.setTaskId$lib_release(this.a.getAndIncrement());
    }

    protected abstract void a(@NotNull c cVar);

    public final void b(@NotNull c cVar) {
        c(cVar);
        a(cVar);
    }
}
