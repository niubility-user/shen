package com.jingdong.app.mall.open;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class c implements Application.ActivityLifecycleCallbacks {

    /* renamed from: l  reason: collision with root package name */
    private static volatile c f11452l;

    /* renamed from: i  reason: collision with root package name */
    private boolean f11455i;

    /* renamed from: j  reason: collision with root package name */
    private String f11456j;

    /* renamed from: g  reason: collision with root package name */
    private AtomicBoolean f11453g = new AtomicBoolean(false);

    /* renamed from: h  reason: collision with root package name */
    private AtomicInteger f11454h = new AtomicInteger();

    /* renamed from: k  reason: collision with root package name */
    private boolean f11457k = false;

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (f11452l == null) {
                f11452l = new c();
            }
            cVar = f11452l;
        }
        return cVar;
    }

    private boolean c() {
        return this.f11454h.get() > 0;
    }

    public void b(Application application, boolean z) {
        if (application == null || !this.f11453g.compareAndSet(false, true)) {
            return;
        }
        this.f11455i = z;
        if (z) {
            this.f11454h.set(1);
        }
        application.registerActivityLifecycleCallbacks(this);
    }

    public void d(Activity activity, Intent intent) {
        String str;
        String callingPackage;
        if (intent == null) {
            return;
        }
        String str2 = null;
        if (intent != null) {
            str2 = intent.getDataString();
            str = String.valueOf(intent.getFlags());
        } else {
            str = null;
        }
        boolean c2 = c();
        if (c2) {
            callingPackage = this.f11456j;
        } else {
            callingPackage = activity.getCallingPackage();
        }
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("isInner", (Object) (c2 ? "1" : "0"));
            if (callingPackage == null) {
                callingPackage = "";
            }
            jDJSONObject.put("from", (Object) callingPackage);
            if (str2 == null) {
                str2 = "";
            }
            jDJSONObject.put("protocol", (Object) str2);
            jDJSONObject.put("flag", (Object) str);
            JDMtaUtils.sendExposureDataWithExt(activity, "OpenApp_CallFrom_Status", "", "", activity.getClass().getSimpleName(), "", jDJSONObject.toString(), null);
            jDJSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void e(Activity activity, Intent intent) {
        if (!this.f11455i || this.f11457k) {
            return;
        }
        d(activity, intent);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        this.f11457k = true;
        if (activity instanceof InterfaceActivity) {
            d(activity, activity.getIntent());
        }
        this.f11456j = null;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NonNull Activity activity) {
        if (activity != null) {
            this.f11456j = activity.getClass().getName();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NonNull Activity activity) {
        this.f11454h.incrementAndGet();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(@NonNull Activity activity) {
        this.f11454h.decrementAndGet();
    }
}
