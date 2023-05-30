package com.jingdong.sdk.jdcrashreport.b;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/* loaded from: classes7.dex */
public class q {
    private static int a;
    private static final b b = new b();

    /* loaded from: classes7.dex */
    private static class b extends FragmentManager.FragmentLifecycleCallbacks {
        private b() {
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
            t.f(fragment, "onAttach");
            String str = "FragmentManager: " + fragmentManager;
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
            t.f(fragment, "onCreate");
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            t.f(fragment, "onDestroy");
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentDetached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            t.f(fragment, "onDetach");
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentPaused(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            t.f(fragment, "onPause");
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentResumed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            t.f(fragment, "onResume");
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentSaveInstanceState(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Bundle bundle) {
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentStarted(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            t.f(fragment, "onStart");
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentStopped(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            t.f(fragment, "onStop");
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle) {
            t.f(fragment, "onCreateView");
        }

        @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
        public void onFragmentViewDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            t.f(fragment, "onDestroyView");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class c implements Application.ActivityLifecycleCallbacks {
        private c() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            r.b("JDCrashReport.Lifecycle", "onActivityCreated activity: " + activity);
            if (com.jingdong.sdk.jdcrashreport.d.b() && (activity instanceof FragmentActivity)) {
                ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(q.b, true);
            }
            t.e(activity, "onCreate");
            com.jingdong.sdk.jdcrashreport.b.c.f(activity);
            t.d(activity);
            try {
                Intent intent = new Intent(activity.getIntent());
                if (intent.hasCategory("FROM_RECOVERY_MODE")) {
                    intent.removeCategory("FROM_RECOVERY_MODE");
                    activity.setIntent(intent);
                    if (com.jingdong.sdk.jdcrashreport.d.e() != null) {
                        com.jingdong.sdk.jdcrashreport.d.e().a(activity);
                    }
                }
            } catch (Throwable unused) {
            }
            if (com.jingdong.sdk.jdcrashreport.recover.e.a().g(activity)) {
                return;
            }
            com.jingdong.sdk.jdcrashreport.recover.e.a().b(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            t.e(activity, "onDestroy");
            com.jingdong.sdk.jdcrashreport.recover.e.a().e(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            t.e(activity, "onPause");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            com.jingdong.sdk.jdcrashreport.b.c.i(true);
            t.e(activity, "onResume");
            t.i(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            com.jingdong.sdk.jdcrashreport.b.c.q();
            q.c();
            com.jingdong.sdk.jdcrashreport.b.c.i(q.a > 0);
            t.e(activity, "Start");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            com.jingdong.sdk.jdcrashreport.b.c.q();
            q.e();
            com.jingdong.sdk.jdcrashreport.b.c.i(q.a > 0);
            t.e(activity, "onStop");
        }
    }

    public static void b(Context context) {
        try {
            if (!(context instanceof Application)) {
                context = context.getApplicationContext();
            }
            Application application = (Application) context;
            if (application != null) {
                application.registerActivityLifecycleCallbacks(new c());
            }
        } catch (Throwable th) {
            r.g("LifecycleCallbacks", th);
        }
    }

    static /* synthetic */ int c() {
        int i2 = a;
        a = i2 + 1;
        return i2;
    }

    static /* synthetic */ int e() {
        int i2 = a;
        a = i2 - 1;
        return i2;
    }
}
