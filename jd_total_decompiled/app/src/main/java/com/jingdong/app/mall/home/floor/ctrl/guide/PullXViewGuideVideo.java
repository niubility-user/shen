package com.jingdong.app.mall.home.floor.ctrl.guide;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.i.h;
import com.jingdong.app.mall.home.floor.common.i.j;
import com.jingdong.app.mall.home.floor.view.widget.HomeVideoView;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;

/* loaded from: classes4.dex */
public class PullXViewGuideVideo extends HomeVideoView {

    /* renamed from: g  reason: collision with root package name */
    private boolean f9419g;

    /* renamed from: h  reason: collision with root package name */
    private String f9420h;

    /* renamed from: i  reason: collision with root package name */
    private int f9421i;

    /* renamed from: j  reason: collision with root package name */
    private float f9422j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f9423g;

        a(String str) {
            this.f9423g = str;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            PullXViewGuideVideo.this.f(this.f9423g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.floor.view.a {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f9425g;

        b(String str) {
            this.f9425g = str;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
        }

        @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            if (TextUtils.equals(this.f9425g, PullXViewGuideVideo.this.f9420h)) {
                PullXViewGuideVideo.this.f9419g = false;
                com.jingdong.app.mall.home.floor.ctrl.guide.a.j().g();
            }
            return super.onError(i2, i3);
        }

        @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            if (a(i2)) {
                PullXViewGuideVideo pullXViewGuideVideo = PullXViewGuideVideo.this;
                pullXViewGuideVideo.f9421i = pullXViewGuideVideo.getDuration();
                PullXViewGuideVideo.this.pause();
                if (TextUtils.equals(this.f9425g, PullXViewGuideVideo.this.f9420h)) {
                    PullXViewGuideVideo.this.f9419g = true;
                    com.jingdong.app.mall.home.floor.ctrl.guide.a.j().g();
                }
            }
            return super.onInfo(i2, i3);
        }

        @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            PullXViewGuideVideo.this.setVolume(0.0f);
        }
    }

    public PullXViewGuideVideo(Context context) {
        super(context, "20");
        this.f9422j = -1.0f;
    }

    public static String d(String str) {
        String md5 = Md5Encrypt.md5(str);
        String g2 = j.g("xViewVideo", PreDownLoadManager.VIDEO_SKU_SUFFIX, md5);
        return TextUtils.isEmpty(h.c(!TextUtils.isEmpty(g2) ? new File(g2) : null, md5, h.f9305c)) ? "" : g2;
    }

    public int e() {
        return this.f9421i / 1000;
    }

    public void f(String str) {
        if (TextUtils.equals(str, this.f9420h) && this.f9419g) {
            return;
        }
        if (f.p0()) {
            f.E0(new a(str));
            return;
        }
        this.f9419g = false;
        this.f9420h = str;
        this.f9422j = -1.0f;
        setOnPlayerStateListener(new b(str));
        setVideoPath(str);
    }

    public boolean g() {
        return this.f9419g;
    }

    public void h() {
        pause();
        releaseInThread(true);
    }

    public void i() {
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
        if (this.f9422j == f2) {
            return;
        }
        this.f9422j = f2;
        super.setVolume(f2);
    }
}
