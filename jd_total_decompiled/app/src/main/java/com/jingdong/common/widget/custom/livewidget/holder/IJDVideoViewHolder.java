package com.jingdong.common.widget.custom.livewidget.holder;

import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;
import tv.danmaku.ijk.media.example.widget.media.IjkVideoView;

/* loaded from: classes12.dex */
public abstract class IJDVideoViewHolder {
    public abstract long getTcpSpeed();

    public abstract IjkVideoView getView();

    public abstract void initRenders();

    public void onDestroy() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public abstract void removeListener();

    public abstract void setDataSource(String str);

    public void setMutePlay(boolean z) {
    }

    public abstract void setOnPlayerStateListener(IPlayerControl.OnPlayerStateListener onPlayerStateListener);

    public abstract void setOnVideoSizeChanged(IPlayerControl.OnVideoSizeChangedListener onVideoSizeChangedListener);

    public abstract void setReplayUrl(String str);

    public void stopAndRelease() {
    }

    public void suspendPlay() {
    }
}
