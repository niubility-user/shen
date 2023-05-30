package com.tencent.mapsdk.internal;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Printer;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: classes9.dex */
public class ba {
    private static final Handler a;
    private static e b;

    /* loaded from: classes9.dex */
    public static class a extends i<Void> {
        public final /* synthetic */ Runnable b;

        public a(Runnable runnable) {
            this.b = runnable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void call() {
            this.b.run();
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public static class b implements Printer {
        @Override // android.util.Printer
        public void println(String str) {
            ma.c(la.s, str);
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class c<T> implements Callback<T>, Runnable {
        @Override // com.tencent.map.tools.Callback
        public abstract void callback(T t);

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    /* loaded from: classes9.dex */
    public static class d extends HandlerThread {

        /* renamed from: g  reason: collision with root package name */
        public static final String f16304g = "tms-dsp";

        /* renamed from: h  reason: collision with root package name */
        private static final int f16305h = 1;

        /* renamed from: i  reason: collision with root package name */
        private static final int f16306i = 300;
        private boolean a;
        private boolean b;

        /* renamed from: c  reason: collision with root package name */
        private volatile boolean f16307c;
        private Handler d;

        /* renamed from: e  reason: collision with root package name */
        private final ConcurrentLinkedQueue<b> f16308e;

        /* renamed from: f  reason: collision with root package name */
        private final List<b> f16309f;

        /* loaded from: classes9.dex */
        public class a extends Handler {
            public a(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void dispatchMessage(Message message) {
                super.dispatchMessage(message);
                if (message.what != 1) {
                    return;
                }
                try {
                    if (d.this.b()) {
                        d.this.d.sendEmptyMessageDelayed(1, 300L);
                    }
                } finally {
                }
            }
        }

        /* loaded from: classes9.dex */
        public class b<T> {

            /* renamed from: j  reason: collision with root package name */
            public static final int f16310j = 0;

            /* renamed from: k  reason: collision with root package name */
            public static final int f16311k = 1;

            /* renamed from: l  reason: collision with root package name */
            public static final int f16312l = 2;

            /* renamed from: m  reason: collision with root package name */
            public static final int f16313m = 3;

            /* renamed from: n  reason: collision with root package name */
            public static final int f16314n = 4;
            private boolean a;
            private f<T> b;

            /* renamed from: c  reason: collision with root package name */
            private final i<T> f16315c;
            private c<T> d;

            /* renamed from: e  reason: collision with root package name */
            private Future<T> f16316e;

            /* renamed from: f  reason: collision with root package name */
            private T f16317f;

            /* renamed from: g  reason: collision with root package name */
            private int f16318g;

            /* renamed from: h  reason: collision with root package name */
            private int f16319h;

            /* loaded from: classes9.dex */
            public class a extends c<T> {
                public a() {
                }

                @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
                public void callback(T t) {
                    b.this.f16317f = t;
                }
            }

            private b(i<T> iVar) {
                this.f16319h = 0;
                this.f16315c = iVar;
                if (iVar == null || d.this.f16307c) {
                    this.a = true;
                }
            }

            public /* synthetic */ b(d dVar, i iVar, a aVar) {
                this(iVar);
            }

            public b<T> a(f<T> fVar) {
                if (fVar != null) {
                    d.this.f16308e.add(this);
                    qa.g(la.s).a("dispatchHandler:" + d.this.d);
                    b((f) fVar);
                }
                return this;
            }

            public void a() {
                qa.g(la.s).a(new Object[0]);
                this.a = true;
                Future<T> future = this.f16316e;
                if (future != null) {
                    future.cancel(false);
                }
                this.f16319h = 4;
            }

            public void a(c<T> cVar) {
                this.d = cVar;
                e();
            }

            public void a(T t) {
                a((f) new g(t)).e();
            }

            public void a(T t, c<T> cVar) {
                a((f) new g(t)).a((c) cVar);
            }

            public void b() {
                qa.g(la.s).a(new Object[0]);
                Future<T> future = this.f16316e;
                if (future == null || this.a) {
                    return;
                }
                if (future.isDone()) {
                    this.f16319h = 3;
                } else if (this.f16316e.isCancelled()) {
                    this.f16317f = this.f16316e.get();
                    a();
                }
            }

            public void b(f<T> fVar) {
                this.b = fVar;
                i<T> iVar = this.f16315c;
                if (iVar != null) {
                    ((i) iVar).a = new a();
                }
            }

            public void b(T t) {
                if (d.this.f16307c) {
                    return;
                }
                a((f) new h(t)).e();
            }

            public void b(T t, c<T> cVar) {
                if (d.this.f16307c) {
                    return;
                }
                a((f) new h(t)).a((c) cVar);
            }

            public void c() {
                qa.g(la.s).a("result:" + this.f16317f, "userCallback:" + this.d);
                c<T> cVar = this.d;
                if (cVar != null) {
                    cVar.callback(this.f16317f);
                }
            }

            public boolean d() {
                this.f16318g++;
                qa.g(la.s).a("try time:" + this.f16318g);
                if (this.f16318g < 2) {
                    this.a = false;
                    e();
                    return true;
                }
                return false;
            }

            public void e() {
                qa.g(la.s).a(new Object[0]);
                if (this.a || d.this.f16307c) {
                    qa.g(la.s).a("cancelled...");
                    return;
                }
                this.f16319h = 1;
                if (d.this.d != null) {
                    d.this.d.sendEmptyMessage(1);
                }
            }

            public void f() {
                i<T> iVar;
                qa.g(la.s).a(new Object[0]);
                f<T> fVar = this.b;
                if (fVar != null && (iVar = this.f16315c) != null) {
                    this.f16316e = fVar.a(iVar);
                }
                qa.g(la.s).a(new Object[0]);
                this.f16319h = 2;
            }
        }

        public d() {
            super(f16304g);
            this.f16308e = new ConcurrentLinkedQueue<>();
            this.f16309f = new LinkedList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            b poll;
            if (!this.f16308e.isEmpty() && (poll = this.f16308e.poll()) != null && poll.f16319h == 1) {
                poll.f();
                this.f16309f.add(poll);
            }
            Iterator<b> it = this.f16309f.iterator();
            while (it.hasNext()) {
                b next = it.next();
                if (next != null) {
                    int i2 = next.f16319h;
                    if (i2 != 2) {
                        if (i2 == 3) {
                            next.c();
                        } else if (i2 == 4) {
                            if (next.d()) {
                                this.f16308e.offer(next);
                            }
                        }
                        it.remove();
                    } else {
                        try {
                            next.b();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        } catch (ExecutionException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
            return !this.f16309f.isEmpty();
        }

        public synchronized <T> b<T> a(i<T> iVar) {
            qa.g(la.s).a("prepared:" + this.a);
            if (!this.a && !this.b && !this.f16307c) {
                start();
                this.b = true;
            }
            return new b<>(this, iVar, null);
        }

        public void a() {
            Iterator<b> it = this.f16308e.iterator();
            while (it.hasNext()) {
                b next = it.next();
                if (next != null) {
                    next.a();
                }
            }
            for (b bVar : this.f16309f) {
                if (bVar != null) {
                    bVar.a();
                }
            }
            this.f16308e.clear();
            this.f16309f.clear();
            qa.g(la.s).a("cancelAll...");
            Handler handler = this.d;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
        }

        public void a(e eVar) {
            if (eVar != null) {
                eVar.a(f16304g, this);
            }
        }

        @Override // android.os.HandlerThread
        public void onLooperPrepared() {
            super.onLooperPrepared();
            this.a = true;
            this.d = new a(getLooper());
            qa.g(la.s).a("looper is prepared...");
            this.d.sendEmptyMessage(1);
        }

        @Override // android.os.HandlerThread
        public boolean quit() {
            boolean quit = super.quit();
            if (quit) {
                a();
                this.f16307c = true;
            }
            return quit;
        }

        @Override // android.os.HandlerThread
        public boolean quitSafely() {
            boolean quitSafely = super.quitSafely();
            if (quitSafely) {
                a();
                this.f16307c = true;
            }
            return quitSafely;
        }
    }

    /* loaded from: classes9.dex */
    public static class e {
        private final Map<String, HandlerThread> a = new HashMap();
        private final d b = new d();

        /* loaded from: classes9.dex */
        public class a extends c<Map.Entry<String, HandlerThread>> {
            public final /* synthetic */ List a;

            public a(List list) {
                this.a = list;
            }

            @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public void callback(Map.Entry<String, HandlerThread> entry) {
                HandlerThread value;
                if (entry == null || (value = entry.getValue()) == null) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    value.quitSafely();
                } else {
                    value.quit();
                }
                this.a.add(entry.getKey());
            }
        }

        /* loaded from: classes9.dex */
        public class b extends c<String> {
            public b() {
            }

            @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public void callback(String str) {
                if (str != null) {
                    e.this.a.remove(str);
                }
            }
        }

        public Looper a(String str) {
            String str2 = "tms-" + str;
            HandlerThread handlerThread = this.a.get(str2);
            if (handlerThread == null || handlerThread.getThreadId() == -1) {
                handlerThread = new HandlerThread(str2);
                handlerThread.start();
                a(str2, handlerThread);
            }
            return handlerThread.getLooper();
        }

        public d a() {
            return this.b;
        }

        public void a(String str, HandlerThread handlerThread) {
            if (handlerThread == null || str == null || str.isEmpty()) {
                return;
            }
            this.a.put(str, handlerThread);
        }

        public void b() {
            this.b.a(this);
        }

        public void c() {
            if (this.a.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            Util.foreach(this.a.entrySet(), new a(arrayList));
            Util.foreach(arrayList, new b());
        }
    }

    /* loaded from: classes9.dex */
    public interface f<T> {
        Future<T> a(i<T> iVar);
    }

    /* loaded from: classes9.dex */
    public static class g<T> implements f<T> {
        public T a;

        public g(T t) {
            this.a = t;
        }

        @Override // com.tencent.mapsdk.internal.ba.f
        public Future<T> a(i<T> iVar) {
            return g7.d().submit(iVar, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public static class h<T> implements f<T> {
        public T a;

        public h(T t) {
            this.a = t;
        }

        @Override // com.tencent.mapsdk.internal.ba.f
        public Future<T> a(i<T> iVar) {
            return g7.e().submit(iVar, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class i<T> implements Runnable, Callable<T> {
        private c<T> a;

        @Override // java.lang.Runnable
        public final void run() {
            try {
                T call = call();
                c<T> cVar = this.a;
                if (cVar != null) {
                    cVar.callback(call);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    static {
        qa.f(la.s);
        a = new Handler(Looper.getMainLooper());
    }

    public static Looper a() {
        e eVar = b;
        if (eVar == null) {
            return null;
        }
        return eVar.a().getLooper();
    }

    public static Looper a(String str) {
        e eVar = b;
        if (eVar == null) {
            return null;
        }
        return eVar.a(str);
    }

    public static <T> d.b<T> a(i<T> iVar) {
        qa.g(la.s).a(iVar);
        return b.a().a(iVar);
    }

    public static void a(e eVar) {
        if (eVar != null) {
            eVar.c();
        }
        a.removeCallbacksAndMessages(null);
    }

    public static void a(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        a((i) new a(runnable)).a((d.b) null);
    }

    public static void a(Runnable runnable, long j2) {
        if (runnable == null) {
            return;
        }
        a.postDelayed(runnable, j2);
    }

    public static e b() {
        return new e();
    }

    public static void b(e eVar) {
        if (eVar == null) {
            return;
        }
        b = eVar;
        eVar.b();
        Looper looper = b.a().getLooper();
        if (looper != null) {
            looper.setMessageLogging(new b());
        }
    }

    public static void b(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            a.post(runnable);
        } else {
            runnable.run();
        }
    }
}
