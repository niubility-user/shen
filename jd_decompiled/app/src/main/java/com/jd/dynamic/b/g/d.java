package com.jd.dynamic.b.g;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class d {
    private final HashMap<String, b> a = new HashMap<>();
    private final AtomicBoolean b = new AtomicBoolean(false);

    public final void a() {
        if (this.b.get()) {
            return;
        }
        Iterator<Map.Entry<String, b>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().b();
        }
        this.b.set(true);
    }

    public final void b(@NotNull b bVar) {
        this.a.put(bVar.a(), bVar);
    }
}
