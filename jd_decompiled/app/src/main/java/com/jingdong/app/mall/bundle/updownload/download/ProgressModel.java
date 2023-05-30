package com.jingdong.app.mall.bundle.updownload.download;

/* loaded from: classes3.dex */
public class ProgressModel {
    private long contentLength;
    private long currentBytes;
    private boolean done;

    public ProgressModel(long j2, long j3, boolean z) {
        this.currentBytes = j2;
        this.contentLength = j3;
        this.done = z;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public long getCurrentBytes() {
        return this.currentBytes;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setContentLength(long j2) {
        this.contentLength = j2;
    }

    public void setCurrentBytes(long j2) {
        this.currentBytes = j2;
    }

    public void setDone(boolean z) {
        this.done = z;
    }

    public String toString() {
        return "ProgressModel{currentBytes=" + this.currentBytes + ", contentLength=" + this.contentLength + ", done=" + this.done + '}';
    }
}
