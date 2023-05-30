package com.jingdong.common.asyncLayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public class AsyncInflateItem {
    OnInflateFinishedCallback callback;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    public View inflatedView;
    private boolean inflating;
    int layoutResId;
    int mPageHashCode;
    ViewGroup parent;

    public AsyncInflateItem(int i2, int i3) {
        this.mPageHashCode = i2;
        this.layoutResId = i3;
    }

    public CountDownLatch getCountDownLatch() {
        return this.countDownLatch;
    }

    public boolean isInflating() {
        return this.inflating;
    }

    public void setInflating(boolean z) {
        this.inflating = z;
    }

    public AsyncInflateItem(int i2, OnInflateFinishedCallback onInflateFinishedCallback) {
        this.layoutResId = i2;
        this.callback = onInflateFinishedCallback;
    }
}
