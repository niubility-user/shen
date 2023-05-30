package com.jingdong.sdk.platform.lib.countdown;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes10.dex */
public abstract class PlatformCountdownTimer {
    private static final int MSG = 1;
    private long mCountdownInterval;
    private long mMillisInFuture;
    private long mStopTimeInFuture;
    private int what;
    public boolean mStart = false;
    private Handler mHandler = new Handler() { // from class: com.jingdong.sdk.platform.lib.countdown.PlatformCountdownTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            synchronized (PlatformCountdownTimer.this) {
                long elapsedRealtime = PlatformCountdownTimer.this.mStopTimeInFuture - SystemClock.elapsedRealtime();
                if (elapsedRealtime > 0) {
                    if (elapsedRealtime < PlatformCountdownTimer.this.mCountdownInterval) {
                        sendMessageDelayed(obtainMessage(i2), elapsedRealtime);
                    } else {
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        PlatformCountdownTimer.this.onTick(elapsedRealtime, i2);
                        long elapsedRealtime3 = (elapsedRealtime2 + PlatformCountdownTimer.this.mCountdownInterval) - SystemClock.elapsedRealtime();
                        while (elapsedRealtime3 < 0) {
                            elapsedRealtime3 += PlatformCountdownTimer.this.mCountdownInterval;
                        }
                        sendMessageDelayed(obtainMessage(i2), elapsedRealtime3);
                    }
                } else {
                    PlatformCountdownTimer.this.onFinish(i2);
                }
            }
        }
    };

    public PlatformCountdownTimer(long j2, long j3, int i2) {
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
        if (OKLog.D) {
            OKLog.d("MyCountdownTimer", "reset(); mMillisInFuture=" + this.mMillisInFuture + "\tmCountdownInterval=" + this.mCountdownInterval);
        }
        this.mMillisInFuture = j2;
        this.mCountdownInterval = j3;
        this.what = i2;
        start();
    }

    public final synchronized PlatformCountdownTimer start() {
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
