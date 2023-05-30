package tv.danmaku.ijk.media.alpha.util;

/* loaded from: classes11.dex */
public class SpeedControlUtil {
    private static final long TIMESTAMP = 1000000;
    private long fixedFrameDurationUsec;
    private boolean loopReset = true;
    private long prevMonoUsec;
    private long prevPresentUsec;

    public void preRender(long j2) {
        long j3 = this.prevMonoUsec;
        if (j3 == 0) {
            this.prevMonoUsec = System.nanoTime() / 1000;
            this.prevPresentUsec = j2;
            return;
        }
        if (this.loopReset) {
            this.prevPresentUsec = j2 - 33333;
            this.loopReset = false;
        }
        long j4 = this.fixedFrameDurationUsec;
        if (j4 == 0) {
            j4 = j2 - this.prevPresentUsec;
        }
        long j5 = j4 >= 0 ? j4 > 10000000 ? 5000000L : j4 : 0L;
        long j6 = j3 + j5;
        long nanoTime = System.nanoTime();
        while (true) {
            long j7 = nanoTime / 1000;
            if (j7 < j6 - 100) {
                long j8 = j6 - j7;
                if (j8 > 500000) {
                    j8 = 500000;
                }
                try {
                    Thread.sleep(j8 / 1000, ((int) (j8 % 1000)) * 1000);
                } catch (InterruptedException unused) {
                }
                nanoTime = System.nanoTime();
            } else {
                this.prevMonoUsec += j5;
                this.prevPresentUsec += j5;
                return;
            }
        }
    }

    public void reset() {
        this.prevPresentUsec = 0L;
        this.prevMonoUsec = 0L;
    }

    public void setFixedPlaybackRate(int i2) {
        if (i2 <= 0) {
            return;
        }
        this.fixedFrameDurationUsec = TIMESTAMP / i2;
    }
}
