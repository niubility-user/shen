package com.jingdong.common.widget.custom;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class CustomSkinObservableManager {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_TOP = 1;
    private static int mCachedStatus;
    private static List<SkinListener> mObservers;

    /* loaded from: classes12.dex */
    private static class Instance {
        public static final CustomSkinObservableManager INSTANCE = new CustomSkinObservableManager();

        private Instance() {
        }
    }

    /* loaded from: classes12.dex */
    public interface SkinListener {
        void skinStatus(int i2);
    }

    public static CustomSkinObservableManager getInstance() {
        return Instance.INSTANCE;
    }

    public int getCachedStatus() {
        return mCachedStatus;
    }

    public void notifySkinObserver(@SkinStatus int i2) {
        if (mCachedStatus == i2) {
            return;
        }
        mCachedStatus = i2;
        if (mObservers.size() < 1) {
            return;
        }
        for (SkinListener skinListener : mObservers) {
            if (skinListener != null) {
                skinListener.skinStatus(i2);
            }
        }
    }

    public void registerObserver(SkinListener skinListener) {
        if (skinListener == null) {
            return;
        }
        synchronized (mObservers) {
            if (!mObservers.contains(skinListener)) {
                mObservers.add(skinListener);
            }
        }
    }

    public void unregisterObserver(SkinListener skinListener) {
        if (skinListener == null) {
            return;
        }
        synchronized (mObservers) {
            if (mObservers.contains(skinListener)) {
                mObservers.remove(skinListener);
            }
        }
    }

    private CustomSkinObservableManager() {
        mObservers = new ArrayList();
    }
}
