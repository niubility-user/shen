package com.jd.viewkit.tool.countdown;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/* loaded from: classes18.dex */
public abstract class InfinityCountDownTimer {
    private static final long COUNT_DOWN_INTERVAL = 1000;
    private static final int MSG = 1;
    private long lastStart;
    private boolean mCancelled = false;
    private Handler mHandler = new Handler() { // from class: com.jd.viewkit.tool.countdown.InfinityCountDownTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (InfinityCountDownTimer.this) {
                if (InfinityCountDownTimer.this.mCancelled) {
                    return;
                }
                long elapsedRealtime = SystemClock.elapsedRealtime();
                InfinityCountDownTimer.this.lastStart = elapsedRealtime;
                InfinityCountDownTimer.this.onTick(elapsedRealtime - InfinityCountDownTimer.this.lastStart);
                long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
                long j2 = 0;
                if (elapsedRealtime2 < 1000) {
                    long j3 = 1000 - elapsedRealtime2;
                    if (j3 >= 0) {
                        j2 = j3;
                    }
                } else {
                    j2 = elapsedRealtime2 - 1000;
                    while (j2 > 1000) {
                        j2 -= 1000;
                    }
                }
                sendMessageDelayed(obtainMessage(1), j2);
            }
        }
    };

    public final synchronized void cancel() {
        this.mCancelled = true;
        this.mHandler.removeMessages(1);
    }

    public boolean isCancelled() {
        return this.mCancelled;
    }

    public abstract void onTick(long j2);

    public final synchronized InfinityCountDownTimer start() {
        this.mCancelled = false;
        this.lastStart = SystemClock.elapsedRealtime();
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1), 1000L);
        return this;
    }
}
