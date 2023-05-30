package com.jingdong.app.mall.home.floor.minitop.mintop;

import com.jingdong.app.mall.home.floor.view.widget.HomeVideoView;

/* loaded from: classes4.dex */
public class MiniTopVideo extends HomeVideoView {

    /* renamed from: g */
    private boolean f9628g;

    /* renamed from: h */
    private float f9629h;

    public boolean a() {
        return this.f9628g;
    }

    public void b() {
        seekTo(0);
        start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setVolume(0.0f);
    }

    @Override // tv.danmaku.ijk.media.example.widget.media.JDVideoView, tv.danmaku.ijk.media.player.IJDVideoPlayer
    public void setVolume(float f2) {
        if (this.f9629h == f2) {
            return;
        }
        this.f9629h = f2;
        super.setVolume(f2);
    }
}
