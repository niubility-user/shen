package com.tencent.mapsdk.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class f1 {
    private final CopyOnWriteArrayList<t4> a = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<t4> b = new CopyOnWriteArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    private final CopyOnWriteArrayList<t4> f16489c = new CopyOnWriteArrayList<>();
    private final e1 d;

    public f1(e1 e1Var) {
        this.d = e1Var;
    }

    private void a() {
        int N;
        synchronized (this.a) {
            ArrayList arrayList = new ArrayList();
            int size = this.f16489c.size();
            for (int i2 = 0; i2 < size; i2++) {
                t4 t4Var = this.f16489c.get(i2);
                if ((t4Var instanceof hg) && (N = ((hg) t4Var).N()) >= 0) {
                    arrayList.add(Integer.valueOf(N));
                }
            }
            int size2 = arrayList.size();
            int[] iArr = new int[size2];
            for (int i3 = 0; i3 < size2; i3++) {
                iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
            }
            this.d.f().a(iArr, size2);
            this.f16489c.clear();
        }
    }

    public void a(t4 t4Var) {
        synchronized (this.a) {
            if (this.a.contains(t4Var)) {
                return;
            }
            this.a.add(t4Var);
            this.d.h().a();
        }
    }

    public boolean a(float f2, float f3) {
        synchronized (this.a) {
            for (int size = this.a.size() - 1; size >= 0; size--) {
                t4 t4Var = this.a.get(size);
                if (t4Var != null && t4Var.onTap(f2, f3)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Deprecated
    public boolean a(GL10 gl10) {
        a();
        this.b.clear();
        synchronized (this.a) {
            this.b.addAll(this.a);
        }
        Iterator<t4> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().a(gl10);
        }
        return true;
    }

    public void b(t4 t4Var) {
        if (t4Var == null) {
            return;
        }
        synchronized (this.a) {
            if (this.a.remove(t4Var)) {
                this.d.h().a();
            }
            this.f16489c.add(t4Var);
        }
    }
}
