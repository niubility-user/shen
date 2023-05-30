package com.jingdong.app.mall.home.floor.view;

import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;

/* loaded from: classes4.dex */
public class a implements IPlayerControl.OnPlayerStateListener {
    public boolean a(int i2) {
        return i2 == 3;
    }

    public boolean b(int i2) {
        return i2 == 10008;
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
    public void onCreatePlayer() {
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
