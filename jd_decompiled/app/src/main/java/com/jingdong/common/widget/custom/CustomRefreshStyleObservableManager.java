package com.jingdong.common.widget.custom;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class CustomRefreshStyleObservableManager {
    private static List<RefreshStyleListener> mObservers;

    /* loaded from: classes12.dex */
    private static class Instance {
        public static final CustomRefreshStyleObservableManager INSTANCE = new CustomRefreshStyleObservableManager();

        private Instance() {
        }
    }

    /* loaded from: classes12.dex */
    public interface RefreshStyleListener {
        void refreshStyleModel(String str);
    }

    public static CustomRefreshStyleObservableManager getInstance() {
        return Instance.INSTANCE;
    }

    public void notifySkinObserver(String str) {
        if (mObservers.size() < 1) {
            return;
        }
        for (RefreshStyleListener refreshStyleListener : mObservers) {
            if (refreshStyleListener != null) {
                refreshStyleListener.refreshStyleModel(str);
            }
        }
    }

    public void registerObserver(RefreshStyleListener refreshStyleListener) {
        if (refreshStyleListener == null) {
            return;
        }
        synchronized (mObservers) {
            if (!mObservers.contains(refreshStyleListener)) {
                mObservers.add(refreshStyleListener);
            }
        }
    }

    public void unregisterObserver(RefreshStyleListener refreshStyleListener) {
        if (refreshStyleListener == null) {
            return;
        }
        synchronized (mObservers) {
            if (mObservers.contains(refreshStyleListener)) {
                mObservers.remove(refreshStyleListener);
            }
        }
    }

    private CustomRefreshStyleObservableManager() {
        mObservers = new ArrayList();
    }
}
