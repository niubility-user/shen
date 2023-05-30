package com.sina.weibo.sdk.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* loaded from: classes9.dex */
public abstract class c<Params, Progress, Result> {
    final h<Params, Result> M;
    final FutureTask<Result> N;
    Params[] P;
    volatile int L = b.U;
    int O = 5;
    Handler x = new a(this, Looper.getMainLooper());

    /* loaded from: classes9.dex */
    final class a extends Handler {
        a(c cVar, Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            f fVar = (f) message.obj;
            if (message.what != 1) {
                return;
            }
            c.a(fVar.a, fVar.b[0]);
            message.obj = null;
        }
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* loaded from: classes9.dex */
    public static final class b {
        public static final int U = 1;
        public static final int V = 2;
        public static final int W = 3;
        private static final /* synthetic */ int[] X = {1, 2, 3};

        public static int[] m() {
            return (int[]) X.clone();
        }
    }

    /* renamed from: com.sina.weibo.sdk.a.c$c  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    final class C0774c extends h<Params, Result> {
        C0774c() {
            super((byte) 0);
        }

        @Override // java.util.concurrent.Callable
        public final Result call() {
            Process.setThreadPriority(c.this.O);
            return (Result) c.this.l();
        }
    }

    /* loaded from: classes9.dex */
    final class d extends c<Params, Progress, Result>.g {
        d(h hVar) {
            super(c.this, hVar);
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            return 0;
        }

        @Override // java.util.concurrent.FutureTask
        protected final void done() {
            try {
                Result result = get();
                c cVar = c.this;
                cVar.x.obtainMessage(1, new f(cVar, result)).sendToTarget();
            } catch (InterruptedException unused) {
                throw new RuntimeException("An error occur while execute doInBackground().");
            } catch (CancellationException unused2) {
                c.this.x.obtainMessage(3, new f(c.this, null)).sendToTarget();
            } catch (ExecutionException unused3) {
                throw new RuntimeException("An error occur while execute doInBackground().");
            }
        }
    }

    /* loaded from: classes9.dex */
    static /* synthetic */ class e {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[b.m().length];
            a = iArr;
            try {
                iArr[b.V - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[b.W - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes9.dex */
    static class f<Data> {
        final c a;
        final Data[] b;

        f(c cVar, Data... dataArr) {
            this.a = cVar;
            this.b = dataArr;
        }
    }

    /* loaded from: classes9.dex */
    abstract class g extends FutureTask<Result> implements Comparable<Object> {
        public g(c cVar, h hVar) {
            super(hVar);
            int i2 = hVar.f16076h;
        }
    }

    public c() {
        C0774c c0774c = new C0774c();
        this.M = c0774c;
        this.N = new d(c0774c);
    }

    static /* synthetic */ void a(c cVar, Object obj) {
        cVar.onPostExecute(obj);
        cVar.L = b.W;
    }

    protected abstract Result l();

    protected void onPostExecute(Result result) {
    }

    /* loaded from: classes9.dex */
    static abstract class h<Params, Result> implements Callable<Result> {

        /* renamed from: g  reason: collision with root package name */
        Params[] f16075g;

        /* renamed from: h  reason: collision with root package name */
        int f16076h;

        private h() {
            this.f16076h = 10;
        }

        /* synthetic */ h(byte b) {
            this();
        }
    }
}
