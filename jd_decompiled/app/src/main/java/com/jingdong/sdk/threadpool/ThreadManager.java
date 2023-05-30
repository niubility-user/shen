package com.jingdong.sdk.threadpool;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.jingdong.sdk.threadpool.common.ThreadExecutor;
import com.jingdong.sdk.threadpool.common.d;
import com.jingdong.sdk.threadpool.common.g;
import com.jingdong.sdk.threadpool.utils.LogUtil;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes.dex */
public final class ThreadManager {
    private static Handler a;
    private static Handler b;

    /* renamed from: c */
    private static Object f15537c = new Object();
    private static ThreadPoolExecutor d = new g(Math.max(Runtime.getRuntime().availableProcessors(), 6), 200, 5, new SynchronousQueue(true), new d("Light_Thread_Pool", 5));

    /* renamed from: e */
    private static ThreadPoolExecutor f15538e = new g(3, 10, 2, new com.jingdong.sdk.threadpool.common.a(100), new d("Heavy_Thread_Pool", 2));

    /* renamed from: f */
    private static ScheduledThreadPoolExecutor f15539f = new ScheduledThreadPoolExecutor(1, new d("Single_Thread_Pool", 5));

    /* renamed from: g */
    private static ThreadExecutor f15540g = new ThreadExecutor(d);

    /* renamed from: h */
    private static ThreadExecutor f15541h = new ThreadExecutor(f15538e);

    /* renamed from: i */
    private static ThreadExecutor f15542i = new ThreadExecutor(f15539f);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    /* loaded from: classes10.dex */
    private static final class c {
        private static ThreadManager a = new ThreadManager(null);
    }

    /* synthetic */ ThreadManager(a aVar) {
        this();
    }

    public static HandlerThread createThreadHandler(String str, int i2) {
        return new HandlerThread(str, i2);
    }

    public static Timer createTimer(String str, boolean z) {
        return new Timer(str, z);
    }

    @Deprecated
    public static ThreadManager get() {
        return c.a;
    }

    public static Handler getMainHandler() {
        if (a == null) {
            synchronized (f15537c) {
                if (a == null) {
                    a = new a(Looper.getMainLooper());
                }
            }
        }
        return a;
    }

    public static Handler getRejectedHandler() {
        if (b == null) {
            synchronized (f15537c) {
                if (b == null) {
                    HandlerThread handlerThread = new HandlerThread("rejected_handler", 10);
                    handlerThread.start();
                    b = new b(handlerThread.getLooper());
                }
            }
        }
        return b;
    }

    public static ThreadExecutor heavy() {
        return f15541h;
    }

    public static ThreadExecutor light() {
        return f15540g;
    }

    public static void printLog(boolean z) {
        LogUtil.printLog(z);
    }

    public static ThreadExecutor single() {
        return f15542i;
    }

    private ThreadManager() {
    }
}
