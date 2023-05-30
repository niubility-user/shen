package com.jingdong.sdk.platform.lib.countdown;

import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes10.dex */
public class PlatformCountdownUtil {
    public static final int MIAOSHA_BEGINING = 2;
    public static final int MIAOSHA_FINISH = 3;
    public static final int MIAOSHA_WILLBEGIN = 1;
    public static final String TAG = "JDMiaoSha";
    private PlatformCountdownTimer myCountdownTimer;
    private int what = 2;
    private boolean isStop = true;

    /* loaded from: classes10.dex */
    public interface CountDownListener {
        void changed(PlatformCountdownTimer platformCountdownTimer, long j2, long[] jArr, int i2);

        boolean finish(PlatformCountdownTimer platformCountdownTimer, long j2, int i2);
    }

    public void countdownCancel() {
        PlatformCountdownTimer platformCountdownTimer = this.myCountdownTimer;
        if (platformCountdownTimer != null) {
            this.isStop = true;
            platformCountdownTimer.cancel(2);
            this.myCountdownTimer.cancel(1);
            this.myCountdownTimer.cancel(3);
        }
    }

    public long getCountdownTime(long j2, long j3) {
        if (j2 > 0) {
            this.what = 1;
            return j2;
        } else if (j3 > 0 && j2 < 0) {
            this.what = 2;
            return j3;
        } else if (j3 >= 0 || j2 >= 0) {
            return 0L;
        } else {
            this.what = 3;
            return 1L;
        }
    }

    public boolean isStop() {
        return this.isStop;
    }

    public void resetTime(long j2) {
        PlatformCountdownTimer platformCountdownTimer = this.myCountdownTimer;
        if (platformCountdownTimer == null || j2 <= 0) {
            return;
        }
        this.isStop = false;
        platformCountdownTimer.reset(j2, 1000L, this.what);
    }

    public void setCountdown(long j2, final long j3, final CountDownListener countDownListener) {
        long countdownTime = getCountdownTime(j2, j3);
        OKLog.d("JDMiaoSha", " -->>setCountdown countdownTime=" + countdownTime);
        PlatformCountdownTimer platformCountdownTimer = this.myCountdownTimer;
        if (platformCountdownTimer == null) {
            this.myCountdownTimer = new PlatformCountdownTimer(countdownTime, 1000L, this.what) { // from class: com.jingdong.sdk.platform.lib.countdown.PlatformCountdownUtil.1
                @Override // com.jingdong.sdk.platform.lib.countdown.PlatformCountdownTimer
                public void onFinish(int i2) {
                    CountDownListener countDownListener2 = countDownListener;
                    if (countDownListener2 != null) {
                        countDownListener2.finish(this, j3, i2);
                    }
                    PlatformCountdownUtil.this.countdownCancel();
                }

                @Override // com.jingdong.sdk.platform.lib.countdown.PlatformCountdownTimer
                public void onTick(long j4, int i2) {
                    long[] hms = PlatformCountdownUtil.this.toHMS(j4);
                    CountDownListener countDownListener2 = countDownListener;
                    if (countDownListener2 != null) {
                        countDownListener2.changed(this, j4, hms, i2);
                    }
                }
            }.start();
        } else {
            platformCountdownTimer.reset(countdownTime, 1000L, this.what);
        }
        this.isStop = false;
    }

    public void setHMS(long j2) {
        PlatformCountdownTimer platformCountdownTimer = this.myCountdownTimer;
        if (platformCountdownTimer != null) {
            platformCountdownTimer.reset(j2, 1000L, this.what);
        }
    }

    public long[] toHMS(long j2) {
        long j3 = j2 / 1000;
        long j4 = (j3 / 60) / 60;
        long j5 = j4 * 60 * 60;
        long j6 = ((j2 - (j5 * 1000)) / 1000) / 60;
        long j7 = (j3 - j5) - (60 * j6);
        if (j4 < 0) {
            j4 = 0;
        }
        if (j6 < 0) {
            j6 = 0;
        }
        if (j7 < 0) {
            j7 = 0;
        }
        return new long[]{j4, j6, j7};
    }
}
