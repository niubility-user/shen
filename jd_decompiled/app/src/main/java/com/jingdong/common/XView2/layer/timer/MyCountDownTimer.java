package com.jingdong.common.XView2.layer.timer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;

/* loaded from: classes5.dex */
public class MyCountDownTimer {
    private static final int MSG = 10000;
    private static volatile MyCountDownTimer instance;
    private ICountdown countdown;
    public long mCountdownInterval;
    private long mMillisInFuture;
    private long mStopTimeInFuture;
    private boolean mCancelled = false;
    private boolean mIsLastCount = false;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.jingdong.common.XView2.layer.timer.MyCountDownTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (MyCountDownTimer.this) {
                if (MyCountDownTimer.this.mCancelled) {
                    return;
                }
                long elapsedRealtime = MyCountDownTimer.this.mStopTimeInFuture - SystemClock.elapsedRealtime();
                long j2 = 0;
                if (elapsedRealtime <= 0) {
                    if (MyCountDownTimer.this.countdown != null) {
                        MyCountDownTimer.this.countdown.onFinish();
                    }
                    MyCountDownTimer.this.mIsLastCount = false;
                } else {
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    if (MyCountDownTimer.this.countdown != null) {
                        MyCountDownTimer.this.countdown.onTick(elapsedRealtime);
                    }
                    long elapsedRealtime3 = SystemClock.elapsedRealtime() - elapsedRealtime2;
                    MyCountDownTimer myCountDownTimer = MyCountDownTimer.this;
                    long j3 = myCountDownTimer.mCountdownInterval;
                    if (elapsedRealtime < j3) {
                        long j4 = elapsedRealtime - elapsedRealtime3;
                        myCountDownTimer.mIsLastCount = true;
                        if (j4 >= 0) {
                            j2 = j4;
                        }
                    } else {
                        long j5 = j3 - elapsedRealtime3;
                        myCountDownTimer.mIsLastCount = false;
                        while (j5 < 0) {
                            j5 += MyCountDownTimer.this.mCountdownInterval;
                        }
                        j2 = j5;
                    }
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "delay " + j2);
                    if (MyCountDownTimer.this.mIsLastCount) {
                        sendMessageDelayed(MyCountDownTimer.this.mHandler.obtainMessage(10000), j2);
                    } else {
                        sendMessageDelayed(MyCountDownTimer.this.mHandler.obtainMessage(10000), 1000L);
                    }
                }
            }
        }
    };

    public MyCountDownTimer(long j2) {
        this.mCountdownInterval = 0L;
        this.mCountdownInterval = j2;
    }

    public final synchronized void cancel() {
        this.mCancelled = true;
        this.mIsLastCount = false;
        this.mHandler.removeMessages(10000);
        this.countdown = null;
    }

    public void destroy() {
        cancel();
        instance = null;
    }

    public final synchronized void start(long j2, ICountdown iCountdown) {
        if (!this.mCancelled) {
            cancel();
        }
        this.countdown = iCountdown;
        if (iCountdown != null) {
            iCountdown.onTick(j2);
        }
        this.mCancelled = false;
        this.mIsLastCount = false;
        if (j2 <= 0 && iCountdown != null) {
            iCountdown.onFinish();
        }
        this.mStopTimeInFuture = SystemClock.elapsedRealtime() + j2;
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(10000));
    }
}
