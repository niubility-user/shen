package com.jingdong.app.mall.home.floor.bottomfloat;

import android.util.SparseArray;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean b(com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = com.jingdong.app.mall.home.JDHomeFragment.O0()     // Catch: java.lang.Throwable -> L47
            r1 = 0
            if (r0 != 0) goto La
            monitor-exit(r6)
            return r1
        La:
            android.util.SparseArray<com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority> r0 = r6.a     // Catch: java.lang.Throwable -> L47
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L47
            r2 = 1
            if (r0 > 0) goto L15
            monitor-exit(r6)
            return r2
        L15:
            r3 = 0
        L16:
            if (r3 >= r0) goto L44
            android.util.SparseArray<com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority> r4 = r6.a     // Catch: java.lang.Throwable -> L47
            java.lang.Object r4 = r4.valueAt(r3)     // Catch: java.lang.Throwable -> L47
            com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority r4 = (com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority) r4     // Catch: java.lang.Throwable -> L47
            int r5 = r7.f9139e     // Catch: java.lang.Throwable -> L47
            boolean r5 = r4.f(r5)     // Catch: java.lang.Throwable -> L47
            if (r5 != 0) goto L41
            boolean r5 = r4.f9138c     // Catch: java.lang.Throwable -> L47
            if (r5 != 0) goto L41
            boolean r5 = r4.d     // Catch: java.lang.Throwable -> L47
            if (r5 != 0) goto L41
            boolean r5 = r4.a     // Catch: java.lang.Throwable -> L47
            if (r5 != 0) goto L41
            boolean r0 = r7.e()     // Catch: java.lang.Throwable -> L47
            if (r0 == 0) goto L45
            int r7 = r7.f9139e     // Catch: java.lang.Throwable -> L47
            int r0 = r4.f9139e     // Catch: java.lang.Throwable -> L47
            if (r7 <= r0) goto L44
            goto L45
        L41:
            int r3 = r3 + 1
            goto L16
        L44:
            r1 = 1
        L45:
            monitor-exit(r6)
            return r1
        L47:
            r7 = move-exception
            monitor-exit(r6)
            goto L4b
        L4a:
            throw r7
        L4b:
            goto L4a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.bottomfloat.a.b(com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority):boolean");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void e(com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority r3, boolean r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r4 == 0) goto L6
            r2.h(r3)     // Catch: java.lang.Throwable -> L26
        L6:
            android.util.SparseArray<com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority> r3 = r2.a     // Catch: java.lang.Throwable -> L26
            int r3 = r3.size()     // Catch: java.lang.Throwable -> L26
            r4 = 0
        Ld:
            if (r4 >= r3) goto L24
            android.util.SparseArray<com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority> r0 = r2.a     // Catch: java.lang.Throwable -> L26
            java.lang.Object r0 = r0.valueAt(r4)     // Catch: java.lang.Throwable -> L26
            com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority r0 = (com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority) r0     // Catch: java.lang.Throwable -> L26
            boolean r1 = r0.a()     // Catch: java.lang.Throwable -> L26
            if (r1 == 0) goto L21
            r0.l()     // Catch: java.lang.Throwable -> L26
            goto L24
        L21:
            int r4 = r4 + 1
            goto Ld
        L24:
            monitor-exit(r2)
            return
        L26:
            r3 = move-exception
            monitor-exit(r2)
            goto L2a
        L29:
            throw r3
        L2a:
            goto L29
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.bottomfloat.a.e(com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority, boolean):void");
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
