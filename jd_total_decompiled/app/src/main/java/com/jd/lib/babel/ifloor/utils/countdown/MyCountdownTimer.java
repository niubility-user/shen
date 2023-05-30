package com.jd.lib.babel.ifloor.utils.countdown;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/* loaded from: classes13.dex */
public abstract class MyCountdownTimer {
    private static final int MSG = 1;
    private long mCountdownInterval;
    private long mMillisInFuture;
    private long mStopTimeInFuture;
    private int what;
    public boolean mStart = false;
    private Handler mHandler = new Handler() { // from class: com.jd.lib.babel.ifloor.utils.countdown.MyCountdownTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            synchronized (MyCountdownTimer.this) {
                long elapsedRealtime = MyCountdownTimer.this.mStopTimeInFuture - SystemClock.elapsedRealtime();
                if (elapsedRealtime > 0) {
                    if (elapsedRealtime < MyCountdownTimer.this.mCountdownInterval) {
                        sendMessageDelayed(obtainMessage(i2), elapsedRealtime);
                    } else {
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        MyCountdownTimer.this.onTick(elapsedRealtime, i2);
                        long elapsedRealtime3 = (elapsedRealtime2 + MyCountdownTimer.this.mCountdownInterval) - SystemClock.elapsedRealtime();
                        while (elapsedRealtime3 < 0) {
                            elapsedRealtime3 += MyCountdownTimer.this.mCountdownInterval;
                        }
                        sendMessageDelayed(obtainMessage(i2), elapsedRealtime3);
                    }
                } else {
                    MyCountdownTimer.this.onFinish(i2);
                }
            }
        }
    };

    public MyCountdownTimer(long j2, long j3, int i2) {
        this.mMillisInFuture = j2;
        this.mCountdownInterval = j3;
        this.what = i2;
    }

    public final void cancel(int i2) {
        this.mStart = false;
        this.mHandler.removeMessages(i2);
    }

    public abstract void onFinish(int i2);

    public abstract void onTick(long j2, int i2);

    public final synchronized void reset(long j2, long j3, int i2) {
        this.mMillisInFuture = j2;
        this.mCountdownInterval = j3;
        this.what = i2;
        start();
    }

    public final synchronized MyCountdownTimer start() {
        if (this.mMillisInFuture <= 0) {
            onFinish(this.what);
            return this;
        }
        this.mStart = true;
        this.mStopTimeInFuture = SystemClock.elapsedRealtime() + this.mMillisInFuture;
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(this.what));
        return this;
    }
}
