package com.jingdong.manto.u;

import android.util.SparseArray;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Function;
import com.eclipsesource.v8.V8Object;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes16.dex */
public class j {
    private SparseArray<a> a = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a {
        int a;
        int b;

        /* renamed from: c  reason: collision with root package name */
        boolean f14225c;
        Timer d = new Timer();

        /* renamed from: e  reason: collision with root package name */
        TimerTask f14226e;

        /* renamed from: com.jingdong.manto.u.j$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        class C0678a extends TimerTask {
            final /* synthetic */ d a;
            final /* synthetic */ V8Function b;

            /* renamed from: c  reason: collision with root package name */
            final /* synthetic */ V8Object f14228c;
            final /* synthetic */ V8Array d;

            /* renamed from: e  reason: collision with root package name */
            final /* synthetic */ boolean f14229e;

            /* renamed from: f  reason: collision with root package name */
            final /* synthetic */ int f14230f;

            /* renamed from: com.jingdong.manto.u.j$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes16.dex */
            class RunnableC0679a implements Runnable {
                RunnableC0679a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (!C0678a.this.b.isReleased()) {
                        C0678a c0678a = C0678a.this;
                        c0678a.b.call(c0678a.f14228c, c0678a.d);
                    }
                    C0678a c0678a2 = C0678a.this;
                    if (c0678a2.f14229e) {
                        return;
                    }
                    a.this.d.cancel();
                    j.this.a.remove(C0678a.this.f14230f);
                }
            }

            C0678a(j jVar, d dVar, V8Function v8Function, V8Object v8Object, V8Array v8Array, boolean z, int i2) {
                this.a = dVar;
                this.b = v8Function;
                this.f14228c = v8Object;
                this.d = v8Array;
                this.f14229e = z;
                this.f14230f = i2;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                d dVar = this.a;
                if (dVar == null) {
                    return;
                }
                dVar.a(new RunnableC0679a());
            }
        }

        public a(d dVar, V8Object v8Object, V8Array v8Array, V8Function v8Function, int i2, int i3, boolean z) {
            this.a = i2;
            this.b = i3;
            this.f14225c = z;
            this.f14226e = new C0678a(j.this, dVar, v8Function, v8Object, v8Array, z, i2);
        }

        public void a() {
            this.d.cancel();
        }

        public void b() {
            if (this.f14225c) {
                Timer timer = this.d;
                TimerTask timerTask = this.f14226e;
                long j2 = this.b;
                timer.schedule(timerTask, j2, j2);
            } else {
                this.d.schedule(this.f14226e, this.b);
            }
            j.this.a.put(this.a, this);
        }
    }

    public a a(d dVar, V8Object v8Object, V8Array v8Array, V8Function v8Function, int i2, int i3, boolean z) {
        return new a(dVar, v8Object, v8Array, v8Function, i2, i3, z);
    }

    public void a() {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            this.a.valueAt(i2).a();
        }
        this.a.clear();
    }

    public void a(int i2) {
        a aVar;
        SparseArray<a> sparseArray = this.a;
        if (sparseArray == null || (aVar = sparseArray.get(i2)) == null) {
            return;
        }
        aVar.a();
        this.a.remove(i2);
    }
}
