package com.jingdong.sdk.aac.util;

import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jingdong.sdk.aac.base.SafeRunnable;
import com.jingdong.sdk.aac.data.BaseLiveData;
import com.jingdong.sdk.aac.model.BaseViewModel;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes7.dex */
public final class LifecycleUtil {
    private static long managerKey;
    private static volatile LifecycleUtil sInstances;
    private ActivityStorer storer = new ActivityStorer();

    /* loaded from: classes7.dex */
    public class ActivityStorer {
        private Object mLock = new Object();
        HashMap<String, WeakReference<FragmentActivity>> activities = new HashMap<>(3);
        HashMap<String, WeakReference<Fragment>> fragments = new HashMap<>(3);

        ActivityStorer() {
            LifecycleUtil.this = r2;
        }

        FragmentActivity getActivity(String str) {
            FragmentActivity fragmentActivity;
            WeakReference<FragmentActivity> weakReference;
            synchronized (this.mLock) {
                fragmentActivity = null;
                if (!TextUtils.isEmpty(str) && (weakReference = this.activities.get(str)) != null) {
                    fragmentActivity = weakReference.get();
                }
            }
            return fragmentActivity;
        }

        Fragment getFragment(String str) {
            Fragment fragment;
            WeakReference<Fragment> weakReference;
            synchronized (this.mLock) {
                fragment = null;
                if (!TextUtils.isEmpty(str) && (weakReference = this.fragments.get(str)) != null) {
                    fragment = weakReference.get();
                }
            }
            return fragment;
        }

        void removeActivity(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.activities.remove(str);
        }

        void removeFragment(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.fragments.remove(str);
        }

        boolean setActivity(String str, FragmentActivity fragmentActivity) {
            if (TextUtils.isEmpty(str) || this.activities.containsKey(str) || fragmentActivity == null) {
                return false;
            }
            this.activities.put(str, new WeakReference<>(fragmentActivity));
            return true;
        }

        boolean setFragment(String str, Fragment fragment) {
            if (TextUtils.isEmpty(str) || this.fragments.containsKey(str) || fragment == null) {
                return false;
            }
            this.fragments.put(str, new WeakReference<>(fragment));
            return true;
        }
    }

    private LifecycleUtil() {
    }

    public static synchronized String createKey() {
        String str;
        synchronized (LifecycleUtil.class) {
            managerKey += System.currentTimeMillis();
            str = "LifecycleUtil" + String.valueOf(managerKey);
        }
        return str;
    }

    public static LifecycleUtil getInstance() {
        if (sInstances != null) {
            return sInstances;
        }
        synchronized (LifecycleUtil.class) {
            sInstances = new LifecycleUtil();
        }
        return sInstances;
    }

    public void excuteOnActivity(String str, SafeRunnable safeRunnable) {
        FragmentActivity activity;
        if (TextUtils.isEmpty(str) || safeRunnable == null || (activity = getActivity(str)) == null || activity.isFinishing()) {
            return;
        }
        safeRunnable.runOnActivity(activity);
    }

    public <T extends FragmentActivity> T getActivity(String str) {
        return (T) this.storer.getActivity(str);
    }

    public <T extends Fragment> T getFragment(String str) {
        return (T) this.storer.getFragment(str);
    }

    public BaseLiveData getLiveData() {
        return new BaseLiveData();
    }

    public <T extends ViewModel> T getViewModel(FragmentActivity fragmentActivity, Class<T> cls) {
        return (T) getViewModel(fragmentActivity, cls, (String) null);
    }

    public void removeActivity(String str) {
        this.storer.removeActivity(str);
    }

    public void removeFragment(String str) {
        this.storer.removeFragment(str);
    }

    public boolean setActivity(String str, FragmentActivity fragmentActivity) {
        return this.storer.setActivity(str, fragmentActivity);
    }

    public boolean setFragment(String str, Fragment fragment) {
        return this.storer.setFragment(str, fragment);
    }

    public <T extends ViewModel> T getViewModel(FragmentActivity fragmentActivity, String str, Class<T> cls) {
        return (T) getViewModel(fragmentActivity, str, cls, (String) null);
    }

    public <T extends ViewModel> T getViewModel(Fragment fragment, Class<T> cls) {
        return (T) getViewModel(fragment, cls, (String) null);
    }

    public <T extends ViewModel> T getViewModel(Fragment fragment, String str, Class<T> cls) {
        return (T) getViewModel(fragment, str, cls, (String) null);
    }

    public <T extends ViewModel> T getViewModel(FragmentActivity fragmentActivity, Class<T> cls, String str) {
        if (BaseViewModel.class.isAssignableFrom(cls)) {
            if (!TextUtils.isEmpty(str)) {
                return (T) ViewModelProviders.of(fragmentActivity, new MyFactory(str)).get(cls);
            }
            throw new NullPointerException("managerKey can not be null!");
        }
        return (T) ViewModelProviders.of(fragmentActivity).get(cls);
    }

    public <T extends ViewModel> T getViewModel(FragmentActivity fragmentActivity, String str, Class<T> cls, String str2) {
        if (TextUtils.isEmpty(str)) {
            return (T) getViewModel(fragmentActivity, cls, str2);
        }
        if (BaseViewModel.class.isAssignableFrom(cls)) {
            if (!TextUtils.isEmpty(str2)) {
                return (T) ViewModelProviders.of(fragmentActivity, new MyFactory(str2)).get(str, cls);
            }
            throw new NullPointerException("managerKey can not be null!");
        }
        return (T) ViewModelProviders.of(fragmentActivity).get(str, cls);
    }

    public <T extends ViewModel> T getViewModel(Fragment fragment, Class<T> cls, String str) {
        if (BaseViewModel.class.isAssignableFrom(cls)) {
            if (!TextUtils.isEmpty(str)) {
                return (T) ViewModelProviders.of(fragment, new MyFactory(str)).get(cls);
            }
            throw new NullPointerException("managerKey can not be null!");
        }
        return (T) ViewModelProviders.of(fragment).get(cls);
    }

    public <T extends ViewModel> T getViewModel(Fragment fragment, String str, Class<T> cls, String str2) {
        if (TextUtils.isEmpty(str)) {
            return (T) getViewModel(fragment, cls, str2);
        }
        if (BaseViewModel.class.isAssignableFrom(cls)) {
            if (!TextUtils.isEmpty(str2)) {
                return (T) ViewModelProviders.of(fragment, new MyFactory(str2)).get(str, cls);
            }
            throw new NullPointerException("managerKey can not be null!");
        }
        return (T) ViewModelProviders.of(fragment).get(str, cls);
    }
}
