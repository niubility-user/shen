package com.jingdong.common.widget.custom;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class FollowObservableManager {
    private static List<WeakReference<FollowListener>> mObservers;

    /* loaded from: classes12.dex */
    private static class FollowInstance {
        public static final FollowObservableManager INSTANCE = new FollowObservableManager();

        private FollowInstance() {
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class FollowListener {
        private String authorId;

        public FollowListener(String str) {
            this.authorId = str;
        }

        public String getAuthorId() {
            return this.authorId;
        }

        public abstract void updateFollowState(int i2);
    }

    public static FollowObservableManager getFollowObservableManager() {
        return FollowInstance.INSTANCE;
    }

    public void deregisterFollowObserver(FollowListener followListener) {
        if (followListener == null) {
            return;
        }
        synchronized (mObservers) {
            Iterator<WeakReference<FollowListener>> it = mObservers.iterator();
            while (it.hasNext()) {
                FollowListener followListener2 = it.next().get();
                if (followListener2 != null && followListener2 == followListener) {
                    it.remove();
                }
            }
        }
    }

    public void notifyFollowState(String str, int i2) {
        Iterator<WeakReference<FollowListener>> it = mObservers.iterator();
        while (it.hasNext()) {
            FollowListener followListener = it.next().get();
            if (followListener != null && followListener.getAuthorId().equals(str)) {
                followListener.updateFollowState(i2);
            }
        }
    }

    public void registerFollowObserver(FollowListener followListener) {
        if (followListener == null) {
            return;
        }
        synchronized (mObservers) {
            Iterator<WeakReference<FollowListener>> it = mObservers.iterator();
            while (it.hasNext()) {
                if (it.next().get() == followListener) {
                    return;
                }
            }
            mObservers.add(new WeakReference<>(followListener));
        }
    }

    private FollowObservableManager() {
        mObservers = new ArrayList();
    }
}
