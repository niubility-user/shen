package com.jingdong.sdk.jdcrashreport.recover;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class e {
    private static volatile e b;
    private List<WeakReference<Activity>> a = new CopyOnWriteArrayList();

    private e() {
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                synchronized (e.class) {
                    if (b == null) {
                        b = new e();
                    }
                }
            }
            eVar = b;
        }
        return eVar;
    }

    private static boolean c(Intent intent) {
        return intent != null && com.jingdong.sdk.jdcrashreport.d.I().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static Class<? extends Activity> f() {
        Class<? extends Activity> c2 = com.jingdong.sdk.jdcrashreport.d.c();
        if (c2 != null) {
            return c2;
        }
        Intent launchIntentForPackage = com.jingdong.sdk.jdcrashreport.d.I().getPackageManager().getLaunchIntentForPackage(com.jingdong.sdk.jdcrashreport.d.I().getPackageName());
        if (launchIntentForPackage != null) {
            try {
                ComponentName component = launchIntentForPackage.getComponent();
                if (component != null) {
                    return Class.forName(component.getClassName());
                }
                return null;
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private boolean h(Activity activity) {
        Activity activity2;
        if (activity == null) {
            return false;
        }
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            WeakReference<Activity> weakReference = this.a.get(i2);
            if (weakReference != null && (activity2 = weakReference.get()) != null && activity == activity2) {
                return true;
            }
        }
        return false;
    }

    public void b(Activity activity) {
        if (h(activity)) {
            return;
        }
        this.a.add(new WeakReference<>(activity));
    }

    public ArrayList<Intent> d() {
        Activity activity;
        ArrayList<Intent> arrayList = new ArrayList<>();
        for (WeakReference<Activity> weakReference : this.a) {
            if (weakReference != null && (activity = weakReference.get()) != null) {
                Intent intent = (Intent) activity.getIntent().clone();
                if (c(intent)) {
                    arrayList.add(intent);
                }
            }
        }
        return arrayList;
    }

    public void e(Activity activity) {
        Activity activity2;
        for (WeakReference<Activity> weakReference : this.a) {
            if (weakReference != null && (activity2 = weakReference.get()) != null && activity2 == activity) {
                this.a.remove(weakReference);
                return;
            }
        }
    }

    public boolean g(Activity activity) {
        return activity == null || com.jingdong.sdk.jdcrashreport.d.d().contains(activity.getClass()) || (activity instanceof RecoverActivity);
    }
}
