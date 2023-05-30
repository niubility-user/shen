package com.jingdong.discovertask.model.inter;

/* loaded from: classes12.dex */
public interface OnTimeStatusChangedListener {
    void onTimeOver(long j2, long j3);

    void onTimePause(long j2, long j3);

    void onTimeResume(long j2, long j3);

    void onTimeStart(long j2, long j3);
}
