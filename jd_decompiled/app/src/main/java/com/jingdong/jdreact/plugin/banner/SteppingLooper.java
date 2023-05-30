package com.jingdong.jdreact.plugin.banner;

/* loaded from: classes13.dex */
public class SteppingLooper extends SpringLooper {
    private long mLastTime;
    private boolean mStarted;

    @Override // com.jingdong.jdreact.plugin.banner.SpringLooper
    public void start() {
        this.mStarted = true;
        this.mLastTime = 0L;
    }

    public boolean step(long j2) {
        BaseSpringSystem baseSpringSystem = this.mSpringSystem;
        if (baseSpringSystem == null || !this.mStarted) {
            return false;
        }
        long j3 = this.mLastTime + j2;
        baseSpringSystem.loop(j3);
        this.mLastTime = j3;
        return this.mSpringSystem.getIsIdle();
    }

    @Override // com.jingdong.jdreact.plugin.banner.SpringLooper
    public void stop() {
        this.mStarted = false;
    }
}
