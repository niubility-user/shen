package com.jingdong.common.jdmiaosha.utils.cache;

/* loaded from: classes5.dex */
public abstract class BaseRunnable implements Runnable {
    private static final String TAG = BaseRunnable.class.getName();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            safeRun();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected abstract void safeRun();
}
