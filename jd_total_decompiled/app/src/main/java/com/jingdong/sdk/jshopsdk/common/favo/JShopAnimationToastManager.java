package com.jingdong.sdk.jshopsdk.common.favo;

import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes7.dex */
public class JShopAnimationToastManager {
    private static JShopAnimationToastManager ourInstance = new JShopAnimationToastManager();
    private Object data;
    private JShopAnimationToast toast;
    private final LinkedBlockingQueue<JShopAnimationToastTask> mQueue = new LinkedBlockingQueue<>();
    private boolean isShowing = false;

    /* loaded from: classes7.dex */
    public static class JShopAnimationToastTask {
        public Object data;
        public JShopAnimationToast toast;

        public JShopAnimationToastTask(JShopAnimationToast jShopAnimationToast, Object obj) {
            this.toast = jShopAnimationToast;
            this.data = obj;
        }
    }

    private JShopAnimationToastManager() {
    }

    public static JShopAnimationToastManager getInstance() {
        return ourInstance;
    }

    private void showNextTask() {
        if (this.mQueue.isEmpty()) {
            this.isShowing = false;
            this.toast = null;
            this.data = null;
            return;
        }
        this.isShowing = true;
        JShopAnimationToastTask poll = this.mQueue.poll();
        JShopAnimationToast jShopAnimationToast = poll.toast;
        this.toast = jShopAnimationToast;
        Object obj = poll.data;
        this.data = obj;
        jShopAnimationToast.showData(obj);
    }

    public void add(JShopAnimationToast jShopAnimationToast, Object obj) {
        this.mQueue.add(new JShopAnimationToastTask(jShopAnimationToast, obj));
        if (this.isShowing) {
            return;
        }
        showNextTask();
    }

    public void onEnd(JShopAnimationToast jShopAnimationToast) {
        if (jShopAnimationToast == this.toast) {
            showNextTask();
        }
    }
}
