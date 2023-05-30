package com.tencent.mapsdk.internal;

import android.graphics.PointF;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class wf implements v4 {
    private ArrayList<v4> a = new ArrayList<>();

    public synchronized void a(v4 v4Var) {
        if (v4Var != null) {
            if (!this.a.contains(v4Var)) {
                this.a.add(v4Var);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean a() {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).a()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean a(float f2) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).a(f2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean a(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).a(f2, f3)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean a(PointF pointF, PointF pointF2, double d, double d2) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).a(pointF, pointF2, d, d2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean a(PointF pointF, PointF pointF2, float f2) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).a(pointF, pointF2, f2)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void b(v4 v4Var) {
        this.a.remove(v4Var);
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean b() {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).b()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean b(float f2) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).b(f2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean b(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).b(f2, f3)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public void c() {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            this.a.get(size).c();
        }
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean c(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).c(f2, f3)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean d() {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).d()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean d(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).d(f2, f3)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean onDoubleTap(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).onDoubleTap(f2, f3)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean onDown(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).onDown(f2, f3)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean onFling(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).onFling(f2, f3)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean onLongPress(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).onLongPress(f2, f3)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean onScroll(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).onScroll(f2, f3)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean onSingleTap(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).onSingleTap(f2, f3)) {
                return true;
            }
        }
        ma.c("notify onSingleTap");
        for (int size2 = this.a.size() - 1; size2 >= 0; size2--) {
            this.a.get(size2).c();
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public synchronized boolean onUp(float f2, float f3) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            if (this.a.get(size).onUp(f2, f3)) {
                return true;
            }
        }
        return false;
    }
}
