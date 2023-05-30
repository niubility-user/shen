package com.googlecode.mp4parser.authoring;

/* loaded from: classes12.dex */
public class Edit {
    private double mediaRate;
    private long mediaTime;
    private double segmentDuration;
    private long timeScale;

    public Edit(long j2, long j3, double d, double d2) {
        this.timeScale = j3;
        this.segmentDuration = d2;
        this.mediaTime = j2;
        this.mediaRate = d;
    }

    public double getMediaRate() {
        return this.mediaRate;
    }

    public long getMediaTime() {
        return this.mediaTime;
    }

    public double getSegmentDuration() {
        return this.segmentDuration;
    }

    public long getTimeScale() {
        return this.timeScale;
    }
}
