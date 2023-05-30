package com.jingdong.app.mall.home.floor.bottomfloat;

import android.util.SparseArray;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.o.a.f;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class a {
    private SparseArray<BaseFloatPriority> a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.floor.bottomfloat.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0283a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ SparseArray f9188g;

        C0283a(a aVar, SparseArray sparseArray) {
            this.f9188g = sparseArray;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            int i2;
            int size = this.f9188g.size();
            for (int i3 = 0; i3 < size; i3++) {
                BaseFloatPriority baseFloatPriority = (BaseFloatPriority) this.f9188g.valueAt(i3);
                if (baseFloatPriority.f9140f && (i2 = baseFloatPriority.f9139e) < 100) {
                    baseFloatPriority.d(i2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class b {
        static a a = new a(null);
    }

    /* synthetic */ a(C0283a c0283a) {
        this();
    }

    public static a d() {
        return b.a;
    }

    public synchronized void a(@NotNull BaseFloatPriority baseFloatPriority) {
        this.a.put(baseFloatPriority.f9139e, baseFloatPriority);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0038, code lost:
        if (r7.e() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003e, code lost:
        if (r7.f9139e <= r4.f9139e) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean b(BaseFloatPriority baseFloatPriority) {
        boolean z = false;
        if (JDHomeFragment.O0()) {
            int size = this.a.size();
            if (size <= 0) {
                return true;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                BaseFloatPriority valueAt = this.a.valueAt(i2);
                if (!valueAt.f(baseFloatPriority.f9139e) && !valueAt.f9138c && !valueAt.d && !valueAt.a) {
                    break;
                }
                i2++;
            }
            z = true;
            return z;
        }
        return false;
    }

    public void c(int i2) {
        BaseFloatPriority baseFloatPriority = this.a.get(i2);
        if (baseFloatPriority != null) {
            baseFloatPriority.b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001d, code lost:
        r0.l();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void e(BaseFloatPriority baseFloatPriority, boolean z) {
        if (z) {
            h(baseFloatPriority);
        }
        int size = this.a.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            BaseFloatPriority valueAt = this.a.valueAt(i2);
            if (valueAt.a()) {
                break;
            }
            i2++;
        }
    }

    public synchronized void f() {
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            BaseFloatPriority valueAt = this.a.valueAt(i2);
            if (valueAt.b && b(valueAt)) {
                valueAt.l();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void g(BaseFloatPriority baseFloatPriority) {
        baseFloatPriority.h();
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            BaseFloatPriority valueAt = this.a.valueAt(i2);
            if (!valueAt.f(baseFloatPriority.f9139e)) {
                valueAt.d(baseFloatPriority.f9139e);
            }
        }
    }

    synchronized void h(@NotNull BaseFloatPriority baseFloatPriority) {
        this.a.remove(baseFloatPriority.f9139e);
    }

    public synchronized void i() {
        if (this.a.size() > 0) {
            f.E0(new C0283a(this, this.a.clone()));
        }
        this.a.clear();
    }

    private a() {
        this.a = new SparseArray<>();
    }
}
