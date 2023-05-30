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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r7 = this;
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.String r0 = r0.getName()
            java.lang.String r1 = "main"
            boolean r1 = r0.equals(r1)
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L1d
            java.lang.String r1 = "rejected_handler"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L1b
            goto L1d
        L1b:
            r1 = 0
            goto L1e
        L1d:
            r1 = 1
        L1e:
            if (r1 != 0) goto L29
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> La0
            java.lang.String r5 = r7.f15556h     // Catch: java.lang.Throwable -> La0
            r4.setName(r5)     // Catch: java.lang.Throwable -> La0
        L29:
            com.jingdong.sdk.threadpool.callback.RunnerTaskCallback<T> r4 = r7.f15559k     // Catch: java.lang.Throwable -> La0
            if (r4 == 0) goto L3c
            boolean r5 = r7.f15558j     // Catch: java.lang.Throwable -> La0
            if (r5 == 0) goto L37
            java.lang.String r4 = r7.f15556h     // Catch: java.lang.Throwable -> La0
            r7.h(r4)     // Catch: java.lang.Throwable -> La0
            goto L3c
        L37:
            java.lang.String r5 = r7.f15556h     // Catch: java.lang.Throwable -> La0
            r4.onStart(r5)     // Catch: java.lang.Throwable -> La0
        L3c:
            r4 = 0
            java.lang.Runnable r5 = r7.f15555g     // Catch: java.lang.Throwable -> La0
            if (r5 == 0) goto L5f
            r5.run()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> La0
            goto L81
        L45:
            r2 = move-exception
            com.jingdong.sdk.threadpool.callback.RunnerTaskCallback<T> r5 = r7.f15559k     // Catch: java.lang.Throwable -> La0
            if (r5 == 0) goto L5a
            boolean r6 = r7.f15558j     // Catch: java.lang.Throwable -> La0
            if (r6 == 0) goto L54
            java.lang.String r5 = r7.f15556h     // Catch: java.lang.Throwable -> La0
            r7.g(r5, r2)     // Catch: java.lang.Throwable -> La0
            goto L5d
        L54:
            java.lang.String r6 = r7.f15556h     // Catch: java.lang.Throwable -> La0
            r5.onFailed(r6, r2)     // Catch: java.lang.Throwable -> La0
            goto L5d
        L5a:
            r7.j(r2)     // Catch: java.lang.Throwable -> La0
        L5d:
            r2 = 0
            goto L81
        L5f:
            java.util.concurrent.Callable<T> r5 = r7.f15560l     // Catch: java.lang.Throwable -> La0
            if (r5 == 0) goto L81
            java.lang.Object r4 = r5.call()     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> La0
            goto L81
        L68:
            r2 = move-exception
            com.jingdong.sdk.threadpool.callback.RunnerTaskCallback<T> r5 = r7.f15559k     // Catch: java.lang.Throwable -> La0
            if (r5 == 0) goto L7d
            boolean r6 = r7.f15558j     // Catch: java.lang.Throwable -> La0
            if (r6 == 0) goto L77
            java.lang.String r5 = r7.f15556h     // Catch: java.lang.Throwable -> La0
            r7.g(r5, r2)     // Catch: java.lang.Throwable -> La0
            goto L5d
        L77:
            java.lang.String r6 = r7.f15556h     // Catch: java.lang.Throwable -> La0
            r5.onFailed(r6, r2)     // Catch: java.lang.Throwable -> La0
            goto L5d
        L7d:
            r7.j(r2)     // Catch: java.lang.Throwable -> La0
            goto L5d
        L81:
            if (r2 == 0) goto L96
            com.jingdong.sdk.threadpool.callback.RunnerTaskCallback<T> r2 = r7.f15559k     // Catch: java.lang.Throwable -> La0
            if (r2 == 0) goto L96
            boolean r3 = r7.f15558j     // Catch: java.lang.Throwable -> La0
            if (r3 == 0) goto L91
            java.lang.String r2 = r7.f15556h     // Catch: java.lang.Throwable -> La0
            r7.i(r2, r4)     // Catch: java.lang.Throwable -> La0
            goto L96
        L91:
            java.lang.String r3 = r7.f15556h     // Catch: java.lang.Throwable -> La0
            r2.onSuccess(r3, r4)     // Catch: java.lang.Throwable -> La0
        L96:
            if (r1 != 0) goto L9f
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r1.setName(r0)
        L9f:
            return
        La0:
            r2 = move-exception
            if (r1 != 0) goto Laa
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r1.setName(r0)
        Laa:
            goto Lac
        Lab:
            throw r2
        Lac:
            goto Lab
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.threadpool.common.f.run():void");
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
