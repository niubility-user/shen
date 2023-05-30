package com.huawei.hmf.tasks.a;

import android.app.Fragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes12.dex */
public final class g extends Fragment {

    /* renamed from: g  reason: collision with root package name */
    private final List<WeakReference<g.e.c.a.b<?>>> f1171g = new ArrayList();

    static {
        new WeakHashMap();
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        synchronized (this.f1171g) {
            Iterator<WeakReference<g.e.c.a.b<?>>> it = this.f1171g.iterator();
            while (it.hasNext()) {
                g.e.c.a.b<?> bVar = it.next().get();
                if (bVar != null) {
                    bVar.cancel();
                }
            }
            this.f1171g.clear();
        }
    }
}
