package com.jingdong.aura.wrapper.d;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Debug;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class b {
    private static final Executor a;
    private static final BlockingQueue<Runnable> b;

    /* loaded from: classes4.dex */
    class a implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "CoordTask #" + this.a.getAndIncrement());
        }
    }

    /* renamed from: com.jingdong.aura.wrapper.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public static class RejectedExecutionHandlerC0402b implements RejectedExecutionHandler {
        private Object a(Object obj) {
            try {
                Field declaredField = obj.getClass().getDeclaredField("this$0");
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return obj;
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
                return obj;
            } catch (NoSuchFieldException e4) {
                e4.printStackTrace();
                return obj;
            }
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            Object[] array = b.b.toArray();
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (Object obj : array) {
                if (obj.getClass().isAnonymousClass()) {
                    sb.append(a(obj));
                    sb.append(',');
                    sb.append(' ');
                } else {
                    sb.append(obj.getClass());
                    sb.append(',');
                    sb.append(' ');
                }
            }
            sb.append(']');
            throw new RejectedExecutionException("Task " + runnable.toString() + " rejected from " + threadPoolExecutor.toString() + " in " + sb.toString());
        }
    }

    /* loaded from: classes4.dex */
    static class c extends AsyncTask<Void, Void, Void> {
        private final d a;

        public c(d dVar) {
            this.a = dVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            return b(voidArr);
        }

        protected Void b(Void... voidArr) {
            b.c(this.a);
            return null;
        }

        public String toString() {
            return c.class.getSimpleName() + DYConstants.DY_REGEX_AT + this.a;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class d implements Runnable {
        public final String a;

        public d(String str) {
            this.a = str;
        }

        public String toString() {
            return getClass().getName() + DYConstants.DY_REGEX_AT + this.a;
        }
    }

    static {
        new LinkedList();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(128);
        b = linkedBlockingQueue;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 1L, TimeUnit.SECONDS, linkedBlockingQueue, new a(), new RejectedExecutionHandlerC0402b());
        a = threadPoolExecutor;
        com.jingdong.aura.wrapper.d.d.a(threadPoolExecutor);
    }

    @TargetApi(11)
    public static void b(d dVar) {
        c cVar = new c(dVar);
        if (Build.VERSION.SDK_INT < 11) {
            cVar.execute(new Void[0]);
        } else {
            cVar.executeOnExecutor(a, new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(d dVar) {
        long nanoTime = System.nanoTime();
        long threadCpuTimeNanos = Debug.threadCpuTimeNanos();
        try {
            dVar.run();
            System.out.println("Timing - " + Thread.currentThread().getName() + LangUtils.SINGLE_SPACE + dVar.a + ": " + ((Debug.threadCpuTimeNanos() - threadCpuTimeNanos) / 1000000) + "ms (cpu) / " + ((System.nanoTime() - nanoTime) / 1000000) + "ms (real)");
        } catch (RuntimeException unused) {
            System.out.println("Exception in " + dVar.a);
            System.out.println("Timing - " + Thread.currentThread().getName() + LangUtils.SINGLE_SPACE + dVar.a + " (failed): " + ((Debug.threadCpuTimeNanos() - threadCpuTimeNanos) / 1000000) + "ms (cpu) / " + ((System.nanoTime() - nanoTime) / 1000000) + "ms (real)");
        } catch (Throwable th) {
            th.printStackTrace();
            System.out.println("Timing - " + Thread.currentThread().getName() + LangUtils.SINGLE_SPACE + dVar.a + " (failed): " + ((Debug.threadCpuTimeNanos() - threadCpuTimeNanos) / 1000000) + "ms (cpu) / " + ((System.nanoTime() - nanoTime) / 1000000) + "ms (real)");
        }
    }
}
