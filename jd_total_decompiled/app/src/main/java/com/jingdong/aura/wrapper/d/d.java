package com.jingdong.aura.wrapper.d;

import android.os.AsyncTask;
import android.os.Build;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class d extends ThreadPoolExecutor {
    private static final HashSet<Thread> a;

    /* loaded from: classes4.dex */
    class a implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "SaturativeThread #" + this.a.getAndIncrement());
            d.a(thread);
            return thread;
        }
    }

    static {
        Pattern.compile("cpu[0-9]+");
        new a();
        a = new HashSet<>();
    }

    public static final boolean a(ThreadPoolExecutor threadPoolExecutor) {
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                Field declaredField = AsyncTask.class.getDeclaredField("THREAD_POOL_EXECUTOR");
                declaredField.setAccessible(true);
                declaredField.set(null, threadPoolExecutor);
            } catch (Exception unused) {
            }
        }
        try {
            try {
                try {
                    Method method = AsyncTask.class.getMethod("setDefaultExecutor", Executor.class);
                    method.setAccessible(true);
                    method.invoke(null, threadPoolExecutor);
                    return true;
                } catch (Exception unused2) {
                    return false;
                }
            } catch (Exception unused3) {
                Field declaredField2 = AsyncTask.class.getDeclaredField("sExecutor");
                declaredField2.setAccessible(true);
                declaredField2.set(null, threadPoolExecutor);
                return true;
            }
        } catch (Exception unused4) {
            Field declaredField3 = AsyncTask.class.getDeclaredField("sDefaultExecutor");
            declaredField3.setAccessible(true);
            declaredField3.set(null, threadPoolExecutor);
            return true;
        }
    }

    public static void a(Thread thread) {
        HashSet<Thread> hashSet = a;
        synchronized (hashSet) {
            hashSet.add(thread);
        }
    }
}
