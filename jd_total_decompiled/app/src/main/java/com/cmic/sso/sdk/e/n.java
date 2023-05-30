package com.cmic.sso.sdk.e;

import android.content.Context;
import com.cmic.sso.sdk.auth.AuthnHelper;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class n {
    private static final ExecutorService a = new ThreadPoolExecutor(0, 30, 60, TimeUnit.SECONDS, new SynchronousQueue());

    public static void a(a aVar) {
        try {
            a.execute(aVar);
        } catch (Exception e2) {
            aVar.a.uncaughtException(Thread.currentThread(), e2);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class a implements Runnable {
        private final Thread.UncaughtExceptionHandler a;

        /* JADX INFO: Access modifiers changed from: protected */
        public a() {
            this.a = new Thread.UncaughtExceptionHandler() { // from class: com.cmic.sso.sdk.e.n.a.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    th.printStackTrace();
                }
            };
        }

        protected abstract void a();

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(this.a);
            a();
            Thread.currentThread().setUncaughtExceptionHandler(null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public a(final Context context, final com.cmic.sso.sdk.a aVar) {
            this.a = new Thread.UncaughtExceptionHandler() { // from class: com.cmic.sso.sdk.e.n.a.2
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    aVar.a().a.add(th);
                    AuthnHelper.getInstance(context).callBackResult("200025", "\u53d1\u751f\u672a\u77e5\u9519\u8bef", aVar, null);
                }
            };
        }
    }
}
