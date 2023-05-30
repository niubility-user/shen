package com.jingdong.sdk.threadpool.common;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.threadpool.ThreadManager;
import com.jingdong.sdk.threadpool.callback.RunnerTaskCallback;
import java.util.concurrent.Callable;

/* loaded from: classes10.dex */
public class f<T> implements Runnable, Comparable<f> {

    /* renamed from: n  reason: collision with root package name */
    private static int f15554n;

    /* renamed from: g  reason: collision with root package name */
    private Runnable f15555g;

    /* renamed from: h  reason: collision with root package name */
    private String f15556h;

    /* renamed from: i  reason: collision with root package name */
    private int f15557i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f15558j;

    /* renamed from: k  reason: collision with root package name */
    private RunnerTaskCallback<T> f15559k;

    /* renamed from: l  reason: collision with root package name */
    private Callable<T> f15560l;

    /* renamed from: m  reason: collision with root package name */
    private int f15561m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15562g;

        a(String str) {
            this.f15562g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.f15559k.onStart(this.f15562g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15564g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Object f15565h;

        b(String str, Object obj) {
            this.f15564g = str;
            this.f15565h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            f.this.f15559k.onSuccess(this.f15564g, this.f15565h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15567g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Throwable f15568h;

        c(String str, Throwable th) {
            this.f15567g = str;
            this.f15568h = th;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.f15559k.onFailed(this.f15567g, this.f15568h);
        }
    }

    public f(Runnable runnable, String str, RunnerTaskCallback<T> runnerTaskCallback, int i2) {
        this.f15561m = 5;
        int i3 = f15554n;
        f15554n = i3 + 1;
        this.f15557i = i3;
        this.f15555g = runnable;
        if (TextUtils.isEmpty(str.trim())) {
            str = "RunnerWrapper_" + this.f15557i;
        }
        this.f15556h = str;
        this.f15559k = runnerTaskCallback;
        b();
        this.f15561m = f(i2);
    }

    private void b() {
        RunnerTaskCallback<T> runnerTaskCallback = this.f15559k;
        if (runnerTaskCallback != null && (runnerTaskCallback instanceof com.jingdong.sdk.threadpool.callback.a)) {
            this.f15558j = true;
        } else {
            this.f15558j = false;
        }
    }

    private int f(int i2) {
        if (i2 < 1) {
            i2 = 1;
        }
        if (i2 > 10) {
            return 10;
        }
        return i2;
    }

    private void g(String str, Throwable th) {
        ThreadManager.getMainHandler().post(new c(str, th));
    }

    private void h(String str) {
        ThreadManager.getMainHandler().post(new a(str));
    }

    private void i(String str, T t) {
        ThreadManager.getMainHandler().post(new b(str, t));
    }

    private void j(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    @Override // java.lang.Comparable
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public int compareTo(f fVar) {
        int e2 = fVar.e();
        int i2 = this.f15561m;
        if (i2 > e2) {
            return -1;
        }
        return (i2 != e2 || this.f15557i > fVar.d()) ? 1 : -1;
    }

    public int d() {
        return this.f15557i;
    }

    public int e() {
        return this.f15561m;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0083 A[Catch: all -> 0x00a0, TryCatch #0 {all -> 0x00a0, blocks: (B:10:0x0020, B:11:0x0029, B:13:0x002d, B:15:0x0031, B:16:0x0037, B:17:0x003c, B:19:0x0041, B:43:0x0083, B:45:0x0087, B:47:0x008b, B:48:0x0091, B:30:0x005f, B:32:0x0063, B:35:0x0069, B:37:0x006d, B:39:0x0071, B:40:0x0077, B:41:0x007d, B:22:0x0046, B:24:0x004a, B:26:0x004e, B:27:0x0054, B:28:0x005a), top: B:58:0x0020, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008b A[Catch: all -> 0x00a0, TryCatch #0 {all -> 0x00a0, blocks: (B:10:0x0020, B:11:0x0029, B:13:0x002d, B:15:0x0031, B:16:0x0037, B:17:0x003c, B:19:0x0041, B:43:0x0083, B:45:0x0087, B:47:0x008b, B:48:0x0091, B:30:0x005f, B:32:0x0063, B:35:0x0069, B:37:0x006d, B:39:0x0071, B:40:0x0077, B:41:0x007d, B:22:0x0046, B:24:0x004a, B:26:0x004e, B:27:0x0054, B:28:0x005a), top: B:58:0x0020, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0091 A[Catch: all -> 0x00a0, TRY_LEAVE, TryCatch #0 {all -> 0x00a0, blocks: (B:10:0x0020, B:11:0x0029, B:13:0x002d, B:15:0x0031, B:16:0x0037, B:17:0x003c, B:19:0x0041, B:43:0x0083, B:45:0x0087, B:47:0x008b, B:48:0x0091, B:30:0x005f, B:32:0x0063, B:35:0x0069, B:37:0x006d, B:39:0x0071, B:40:0x0077, B:41:0x007d, B:22:0x0046, B:24:0x004a, B:26:0x004e, B:27:0x0054, B:28:0x005a), top: B:58:0x0020, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0098 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        RunnerTaskCallback<T> runnerTaskCallback;
        String name = Thread.currentThread().getName();
        boolean z = true;
        boolean z2 = name.equals("main") || name.equals("rejected_handler");
        if (!z2) {
            try {
                Thread.currentThread().setName(this.f15556h);
            } finally {
                if (!z2) {
                    Thread.currentThread().setName(name);
                }
            }
        }
        RunnerTaskCallback<T> runnerTaskCallback2 = this.f15559k;
        if (runnerTaskCallback2 != null) {
            if (this.f15558j) {
                h(this.f15556h);
            } else {
                runnerTaskCallback2.onStart(this.f15556h);
            }
        }
        T t = null;
        Runnable runnable = this.f15555g;
        if (runnable != null) {
            try {
                runnable.run();
            } catch (Exception e2) {
                RunnerTaskCallback<T> runnerTaskCallback3 = this.f15559k;
                if (runnerTaskCallback3 != null) {
                    if (this.f15558j) {
                        g(this.f15556h, e2);
                    } else {
                        runnerTaskCallback3.onFailed(this.f15556h, e2);
                    }
                } else {
                    j(e2);
                }
                z = false;
                if (z) {
                }
            }
            if (z && (runnerTaskCallback = this.f15559k) != null) {
                if (!this.f15558j) {
                    i(this.f15556h, t);
                } else {
                    runnerTaskCallback.onSuccess(this.f15556h, t);
                }
            }
            if (z2) {
                return;
            }
            return;
        }
        Callable<T> callable = this.f15560l;
        if (callable != null) {
            try {
                t = callable.call();
            } catch (Exception e3) {
                RunnerTaskCallback<T> runnerTaskCallback4 = this.f15559k;
                if (runnerTaskCallback4 != null) {
                    if (this.f15558j) {
                        g(this.f15556h, e3);
                    } else {
                        runnerTaskCallback4.onFailed(this.f15556h, e3);
                    }
                } else {
                    j(e3);
                }
                z = false;
                if (z) {
                }
            }
        }
        if (z) {
            if (!this.f15558j) {
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[RunnerWrapper@name:");
        sb.append(this.f15556h);
        sb.append(";mRunnableTask:");
        Runnable runnable = this.f15555g;
        String str = DYConstants.DY_NULL_STR;
        sb.append(runnable != null ? runnable.toString() : DYConstants.DY_NULL_STR);
        sb.append(";mCallableTask:");
        Callable<T> callable = this.f15560l;
        if (callable != null) {
            str = callable.toString();
        }
        sb.append(str);
        sb.append("]");
        return sb.toString();
    }

    public f(Callable<T> callable, String str, RunnerTaskCallback<T> runnerTaskCallback, int i2) {
        this.f15561m = 5;
        int i3 = f15554n;
        f15554n = i3 + 1;
        this.f15557i = i3;
        this.f15560l = callable;
        if (TextUtils.isEmpty(str.trim())) {
            str = "RunnerWrapper_" + this.f15557i;
        }
        this.f15556h = str;
        this.f15559k = runnerTaskCallback;
        b();
        this.f15561m = f(i2);
    }
}
