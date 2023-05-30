package com.jingdong.manto.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes16.dex */
public class a implements Application.ActivityLifecycleCallbacks {
    private static volatile a d;
    private boolean b;
    private volatile int a = 0;

    /* renamed from: c  reason: collision with root package name */
    private ArrayList<InterfaceC0688a> f14299c = new ArrayList<>();

    /* renamed from: com.jingdong.manto.utils.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public interface InterfaceC0688a {
        void a(Context context);

        void b(Context context);
    }

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (d == null) {
                d = new a();
            }
            aVar = d;
        }
        return aVar;
    }

    public void a(Application application) {
        if (application == null) {
            return;
        }
        this.b = true;
        application.registerActivityLifecycleCallbacks(this);
    }

    public synchronized void a(InterfaceC0688a interfaceC0688a) {
        if (!this.f14299c.contains(interfaceC0688a)) {
            this.f14299c.add(interfaceC0688a);
        }
    }

    public synchronized void b(InterfaceC0688a interfaceC0688a) {
        this.f14299c.remove(interfaceC0688a);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (this.a == 0) {
            if (this.b) {
                this.b = false;
            } else {
                synchronized (this) {
                    Iterator<InterfaceC0688a> it = this.f14299c.iterator();
                    while (it.hasNext()) {
                        it.next().b(activity);
                    }
                }
            }
        }
        this.a++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        this.a--;
        if (this.a == 0) {
            synchronized (this) {
                Iterator<InterfaceC0688a> it = this.f14299c.iterator();
                while (it.hasNext()) {
                    it.next().a(activity);
                }
            }
        }
    }
}
