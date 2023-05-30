package com.jingdong.common.unification.navigationbar;

import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes6.dex */
public class NavigationSkinChangeManager {
    private static final String TAG = "NavigationSkinChangeManager";
    private CopyOnWriteArrayList<OnNavigationSkinChangeListener> onNavigationSkinChangeListeners;

    /* loaded from: classes6.dex */
    public interface OnNavigationSkinChangeListener {
        void onNavigationSkinChanged();
    }

    /* loaded from: classes6.dex */
    private static class SingletonHolder {
        private static final NavigationSkinChangeManager INSTANCE = new NavigationSkinChangeManager();

        private SingletonHolder() {
        }
    }

    public static NavigationSkinChangeManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean addNavigationSkinChangeListener(OnNavigationSkinChangeListener onNavigationSkinChangeListener) {
        if (this.onNavigationSkinChangeListeners == null) {
            this.onNavigationSkinChangeListeners = new CopyOnWriteArrayList<>();
        }
        return this.onNavigationSkinChangeListeners.add(onNavigationSkinChangeListener);
    }

    public void clearNavigationSkinChangeListeners() {
        CopyOnWriteArrayList<OnNavigationSkinChangeListener> copyOnWriteArrayList = this.onNavigationSkinChangeListeners;
        if (copyOnWriteArrayList == null) {
            return;
        }
        copyOnWriteArrayList.clear();
    }

    public void notifyNavigationSkinChanged() {
        CopyOnWriteArrayList<OnNavigationSkinChangeListener> copyOnWriteArrayList = this.onNavigationSkinChangeListeners;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "notifyNavigationSkinChanged=" + this.onNavigationSkinChangeListeners.size());
        }
        for (int size = this.onNavigationSkinChangeListeners.size() - 1; size >= 0; size--) {
            OnNavigationSkinChangeListener onNavigationSkinChangeListener = this.onNavigationSkinChangeListeners.get(size);
            if (onNavigationSkinChangeListener != null) {
                try {
                    onNavigationSkinChangeListener.onNavigationSkinChanged();
                } catch (Exception e2) {
                    if (OKLog.E) {
                        OKLog.d(TAG, e2);
                    }
                }
            }
        }
    }

    public boolean removeNavigationSkinChangeListener(OnNavigationSkinChangeListener onNavigationSkinChangeListener) {
        CopyOnWriteArrayList<OnNavigationSkinChangeListener> copyOnWriteArrayList = this.onNavigationSkinChangeListeners;
        return copyOnWriteArrayList != null && copyOnWriteArrayList.remove(onNavigationSkinChangeListener);
    }

    private NavigationSkinChangeManager() {
    }
}
