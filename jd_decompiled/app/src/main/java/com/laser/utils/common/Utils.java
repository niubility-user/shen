package com.laser.utils.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.laser.utils.common.PermissionUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes13.dex */
public final class Utils {
    private static final ActivityLifecycleImpl ACTIVITY_LIFECYCLE = new ActivityLifecycleImpl();
    @SuppressLint({"StaticFieldLeak"})
    private static Application sApplication;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {
        final LinkedList<Activity> mActivityList = new LinkedList<>();
        final HashMap<Object, OnAppStatusChangedListener> mStatusListenerMap = new HashMap<>();
        private int mForegroundCount = 0;
        private int mConfigCount = 0;

        ActivityLifecycleImpl() {
        }

        private void postStatus(boolean z) {
            OnAppStatusChangedListener next;
            if (this.mStatusListenerMap.isEmpty()) {
                return;
            }
            Iterator<OnAppStatusChangedListener> it = this.mStatusListenerMap.values().iterator();
            while (it.hasNext() && (next = it.next()) != null) {
                if (z) {
                    next.onForeground();
                } else {
                    next.onBackground();
                }
            }
        }

        private void setTopActivity(Activity activity) {
            if (activity.getClass() == PermissionUtils.PermissionActivity.class) {
                return;
            }
            if (this.mActivityList.contains(activity)) {
                if (this.mActivityList.getLast().equals(activity)) {
                    return;
                }
                this.mActivityList.remove(activity);
                this.mActivityList.addLast(activity);
                return;
            }
            this.mActivityList.addLast(activity);
        }

        void addListener(Object obj, OnAppStatusChangedListener onAppStatusChangedListener) {
            this.mStatusListenerMap.put(obj, onAppStatusChangedListener);
        }

        Activity getTopActivity() {
            Map map;
            Activity last;
            if (this.mActivityList.isEmpty() || (last = this.mActivityList.getLast()) == null) {
                try {
                    Class<?> cls = Class.forName("android.app.ActivityThread");
                    Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                    Field declaredField = cls.getDeclaredField("mActivityList");
                    declaredField.setAccessible(true);
                    map = (Map) declaredField.get(invoke);
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                } catch (NoSuchFieldException e4) {
                    e4.printStackTrace();
                } catch (NoSuchMethodException e5) {
                    e5.printStackTrace();
                } catch (InvocationTargetException e6) {
                    e6.printStackTrace();
                }
                if (map == null) {
                    return null;
                }
                for (Object obj : map.values()) {
                    Class<?> cls2 = obj.getClass();
                    Field declaredField2 = cls2.getDeclaredField("paused");
                    declaredField2.setAccessible(true);
                    if (!declaredField2.getBoolean(obj)) {
                        Field declaredField3 = cls2.getDeclaredField("activity");
                        declaredField3.setAccessible(true);
                        Activity activity = (Activity) declaredField3.get(obj);
                        setTopActivity(activity);
                        return activity;
                    }
                }
                return null;
            }
            return last;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            setTopActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            this.mActivityList.remove(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            setTopActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            setTopActivity(activity);
            if (this.mForegroundCount <= 0) {
                postStatus(true);
            }
            int i2 = this.mConfigCount;
            if (i2 < 0) {
                this.mConfigCount = i2 + 1;
            } else {
                this.mForegroundCount++;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                this.mConfigCount--;
                return;
            }
            int i2 = this.mForegroundCount - 1;
            this.mForegroundCount = i2;
            if (i2 <= 0) {
                postStatus(false);
            }
        }

        void removeListener(Object obj) {
            this.mStatusListenerMap.remove(obj);
        }
    }

    /* loaded from: classes13.dex */
    public interface OnAppStatusChangedListener {
        void onBackground();

        void onForeground();
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    static ActivityLifecycleImpl getActivityLifecycle() {
        return ACTIVITY_LIFECYCLE;
    }

    static LinkedList<Activity> getActivityList() {
        return ACTIVITY_LIFECYCLE.mActivityList;
    }

    public static Application getApp() {
        Application application = sApplication;
        if (application != null) {
            return application;
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (invoke != null) {
                init((Application) invoke);
                return sApplication;
            }
            throw new NullPointerException("u should init first");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }

    public static void init(@NonNull Context context) {
        init((Application) context.getApplicationContext());
    }

    public static void init(@NonNull Application application) {
        if (sApplication == null) {
            sApplication = application;
            application.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
        }
    }
}
