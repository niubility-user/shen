package c.t.m.g;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class d {
    public static ConcurrentHashMap<String, HandlerThread> a = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Integer> b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    public static ConcurrentHashMap<String, Long> f342c = new ConcurrentHashMap<>();

    public static synchronized HandlerThread a(String str, int i2) {
        HandlerThread handlerThread;
        ConcurrentHashMap<String, Integer> concurrentHashMap;
        int valueOf;
        synchronized (d.class) {
            handlerThread = a.get(str);
            if (handlerThread == null) {
                handlerThread = new HandlerThread(str, i2);
                handlerThread.start();
                a.put(str, handlerThread);
                concurrentHashMap = b;
                valueOf = 1;
            } else {
                concurrentHashMap = b;
                valueOf = Integer.valueOf(concurrentHashMap.get(str).intValue() + 1);
            }
            concurrentHashMap.put(str, valueOf);
        }
        return handlerThread;
    }

    public static void b(String str) {
        c(str, 0L);
    }

    public static synchronized void c(String str, long j2) {
        synchronized (d.class) {
            if (b.containsKey(str)) {
                int intValue = b.get(str).intValue() - 1;
                if (intValue != 0) {
                    b.put(str, Integer.valueOf(intValue));
                    if (j2 != 0) {
                        f342c.put(str, Long.valueOf(Math.max(System.currentTimeMillis() + j2, f342c.containsKey(str) ? f342c.get(str).longValue() : 0L)));
                    }
                    return;
                }
                b.remove(str);
                HandlerThread remove = a.remove(str);
                if (f342c.containsKey(str)) {
                    j2 = Math.max(j2, f342c.remove(str).longValue() - System.currentTimeMillis());
                }
                l.a(remove, null, j2, false);
            }
        }
    }

    public static synchronized void d(String str, Runnable runnable) {
        synchronized (d.class) {
            t.g(new Handler(e(str).getLooper()), runnable);
            b(str);
        }
    }

    public static synchronized HandlerThread e(String str) {
        HandlerThread a2;
        synchronized (d.class) {
            a2 = a(str, 0);
        }
        return a2;
    }
}
