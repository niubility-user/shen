package com.jingdong.aura.core.runing.resource;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

/* loaded from: classes4.dex */
public abstract class a {
    private static Object a = new Object();
    private static a b;

    /* renamed from: com.jingdong.aura.core.runing.resource.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    private static class RunnableC0396a implements Runnable {
        Application a;
        String b;

        /* renamed from: c  reason: collision with root package name */
        Resources f12161c;

        public RunnableC0396a(Application application, Resources resources, String str) {
            this.a = application;
            this.f12161c = resources;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a.b().b(this.a, this.f12161c, this.b);
                synchronized (a.a) {
                    a.a.notify();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                synchronized (a.a) {
                    a.a.notify();
                }
            } catch (Throwable unused) {
                synchronized (a.a) {
                    a.a.notify();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized a b() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                if (com.jingdong.aura.a.b.c.C()) {
                    b = new f();
                } else {
                    b = new g();
                }
            }
            aVar = b;
        }
        return aVar;
    }

    public static int c() {
        a aVar = b;
        if (aVar == null) {
            return 0;
        }
        if (aVar instanceof f) {
            return 1;
        }
        return aVar instanceof g ? 2 : -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract b a(AssetManager assetManager, Resources resources);

    protected abstract void b(Application application, Resources resources, String str);

    public static void a(Application application, Resources resources, String str) {
        if (com.jingdong.aura.a.b.c.B()) {
            if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
                b().b(application, resources, str);
                return;
            }
            synchronized (a) {
                new Handler(Looper.getMainLooper()).post(new RunnableC0396a(application, resources, str));
                a.wait();
            }
            return;
        }
        b().b(application, resources, str);
    }
}
