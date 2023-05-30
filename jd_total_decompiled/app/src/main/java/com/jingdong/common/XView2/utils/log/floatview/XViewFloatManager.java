package com.jingdong.common.XView2.utils.log.floatview;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes5.dex */
public class XViewFloatManager {
    private static volatile XViewFloatManager instance;
    private final IFloatViewFactory floatViewFactory = new FloatViewFactoryImpl();
    private XViewFloatView mFloatView;

    public static XViewFloatManager getInstance() {
        if (instance == null) {
            synchronized (XViewFloatManager.class) {
                if (instance == null) {
                    instance = new XViewFloatManager();
                }
            }
        }
        return instance;
    }

    public void hideFloatView() {
    }

    public void registerLifecycle(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.jingdong.common.XView2.utils.log.floatview.XViewFloatManager.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(@NonNull Activity activity) {
                XViewFloatManager.this.hideFloatView();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(@NonNull Activity activity) {
                XViewFloatManager.this.showFloatView(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(@NonNull Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(@NonNull Activity activity) {
            }
        });
    }

    public void showFloatView(Context context) {
    }

    public void showXViewLog(String str) {
        XViewFloatView xViewFloatView = this.mFloatView;
        if (xViewFloatView != null) {
            xViewFloatView.showLog(str);
        }
    }
}
