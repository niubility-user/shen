package com.jingdong.common.utils;

import com.jingdong.common.frame.IMyActivity;

/* loaded from: classes6.dex */
public class DefaultEffectHandle {
    private int counter;
    private DefaultEffectHttpListener defaultEffectHttpListener;

    public DefaultEffectHandle(IMyActivity iMyActivity) {
        this.defaultEffectHttpListener = new DefaultEffectHttpListener(null, iMyActivity);
    }

    public void addModal() {
        this.defaultEffectHttpListener.onStart();
        this.counter++;
    }

    public void removeModal() {
        while (this.counter > 0) {
            this.defaultEffectHttpListener.onEnd(null);
            this.counter--;
        }
    }
}
