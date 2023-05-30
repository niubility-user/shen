package com.jingdong.common.recommend.ui.video;

import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;

/* loaded from: classes6.dex */
public abstract class RecommendVideoPlayState implements IPlayerControl.OnPlayerStateListener {
    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public void onCompletion() {
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public void onCreatePlayer() {
    }

    public boolean onCustomCompletion() {
        return false;
    }

    public void onDetach() {
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public boolean onError(int i2, int i3) {
        return false;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public boolean onInfo(int i2, int i3) {
        return false;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public void onPrepared(long j2) {
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public void onSeekComplete() {
    }
}
