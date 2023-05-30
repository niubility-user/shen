package com.jingdong.common.unification.video.mta;

/* loaded from: classes6.dex */
public interface INewVideoMtaInfo extends IVideoMtaInfo {
    long getAvgSpeed();

    String getErrMsg();

    long getFirstPlayTime();

    long getInitPlayerTime();

    long getInitTime();

    int getLostFramesCnt();

    String getLostFramesInfo();

    long getMaxSpeed();

    long getMinSpeed();

    int getStuckCnt();

    int getVideoType();
}
