package com.jingdong.manto.preload;

import android.os.Handler;
import android.os.Looper;
import com.jingdong.manto.h;
import com.jingdong.manto.q.n;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes16.dex */
public class b {
    private static volatile LinkedList<d> a = new LinkedList<>();
    private static volatile List<e> b = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.preload.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class RunnableC0654b implements Runnable {
        final /* synthetic */ d a;

        RunnableC0654b(d dVar) {
            this.a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.a.add(this.a);
            if (b.b.size() > 0) {
                b.f();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements Runnable {
        final /* synthetic */ n a;

        c(n nVar) {
            this.a = nVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(com.jingdong.manto.c.a());
        }
    }

    /* loaded from: classes16.dex */
    public static class d {
        public final h a;
        public n b;

        public d(h hVar, n nVar) {
            this.a = hVar;
            this.b = nVar;
        }
    }

    /* loaded from: classes16.dex */
    public interface e {
        void onReady();
    }

    public static synchronized void a(e eVar) {
        synchronized (b.class) {
            if (a.size() > 0) {
                if (eVar != null) {
                    eVar.onReady();
                }
                return;
            }
            if (eVar != null) {
                b.add(eVar);
            }
            ThreadManager.heavy().post(new a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        d dVar = new d(i(), h());
        if (dVar.b == null || dVar.a == null) {
            return;
        }
        synchronized (b.class) {
            new Handler(Looper.getMainLooper()).post(new RunnableC0654b(dVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void f() {
        synchronized (b.class) {
            Iterator<e> it = b.iterator();
            while (it.hasNext()) {
                it.next().onReady();
            }
            b.clear();
        }
    }

    public static synchronized d g() {
        d pop;
        synchronized (b.class) {
            pop = !a.isEmpty() ? a.pop() : null;
            if (a.isEmpty()) {
                a(null);
            }
        }
        return pop;
    }

    private static n h() {
        n nVar = new n();
        new Handler(Looper.getMainLooper()).post(new c(nVar));
        return nVar;
    }

    private static h i() {
        return new h();
    }

    public static synchronized void j() {
        synchronized (b.class) {
            try {
                if (a != null && a.size() > 0) {
                    d peek = a.peek();
                    if (peek != null) {
                        h hVar = peek.a;
                        if (hVar != null) {
                            hVar.i();
                        }
                        n nVar = peek.b;
                        if (nVar != null) {
                            nVar.i();
                        }
                    }
                    a.clear();
                }
                if (b != null) {
                    b.clear();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
