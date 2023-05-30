package com.jd.viewkit.tool.countdown;

/* loaded from: classes18.dex */
public class CountDownTask {
    public int id;
    public CountDownListener mCountDownListener;
    public long mMillisInFuture;

    /* loaded from: classes18.dex */
    public interface CountDownListener {
        void changed(long j2);

        boolean finish();
    }

    public CountDownTask(int i2, long j2, CountDownListener countDownListener) {
        this.id = i2;
        this.mMillisInFuture = j2;
        this.mCountDownListener = countDownListener;
    }

    public int getId() {
        return this.id;
    }

    public synchronized void setMillisInFuture(long j2) {
        this.mMillisInFuture = j2;
    }
}
