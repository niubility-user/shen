package com.jingdong.common.network;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class InternalActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    public static final String TAG = "JDHttpTookit";
    private ArrayList<IDestroyListener> destroyListenerList = new ArrayList<>();

    /* loaded from: classes5.dex */
    public interface IDestroyListener {
        boolean isActivityHolder(Activity activity);

        void onDestroy();
    }

    public void addDestroyListener(IDestroyListener iDestroyListener) {
        synchronized (this) {
            ArrayList<IDestroyListener> arrayList = this.destroyListenerList;
            if (arrayList != null) {
                arrayList.add(iDestroyListener);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        if (OKLog.D) {
            OKLog.d("JDHttpTookit", "Activity Destroy!");
        }
        try {
            synchronized (this) {
                ArrayList<IDestroyListener> arrayList = this.destroyListenerList;
                if (arrayList != null) {
                    Iterator<IDestroyListener> it = arrayList.iterator();
                    while (it.hasNext()) {
                        IDestroyListener next = it.next();
                        if (next.isActivityHolder(activity)) {
                            if (OKLog.D) {
                                OKLog.d("JDHttpTookit", "activity has been hold, then release");
                            }
                            next.onDestroy();
                            if (OKLog.D) {
                                OKLog.d("JDHttpTookit", "release the IDestroyListener");
                            }
                            it.remove();
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        if (JDHttpTookit.getEngine().getGuardVerifyPlugin() != null) {
            JDHttpTookit.getEngine().getGuardVerifyPlugin().onActivityDestroyed(activity);
        }
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
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    public void removeDestroyListener(IDestroyListener iDestroyListener) {
        synchronized (this) {
            if (iDestroyListener != null) {
                ArrayList<IDestroyListener> arrayList = this.destroyListenerList;
                if (arrayList != null) {
                    arrayList.remove(iDestroyListener);
                }
            }
        }
    }
}
