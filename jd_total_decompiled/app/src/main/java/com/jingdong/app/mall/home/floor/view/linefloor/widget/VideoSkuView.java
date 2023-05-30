package com.jingdong.app.mall.home.floor.view.linefloor.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.app.mall.home.floor.common.i.j;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.widget.HomeVideoView;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.utils.CommonBase;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class VideoSkuView extends HomeVideoView {
    private static ConcurrentHashMap<String, VideoSkuView> o = new ConcurrentHashMap<>();

    /* renamed from: g  reason: collision with root package name */
    private boolean f9961g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f9962h;

    /* renamed from: i  reason: collision with root package name */
    private String f9963i;

    /* renamed from: j  reason: collision with root package name */
    private String f9964j;

    /* renamed from: k  reason: collision with root package name */
    private View f9965k;

    /* renamed from: l  reason: collision with root package name */
    private com.jingdong.app.mall.home.floor.view.linefloor.base.b f9966l;

    /* renamed from: m  reason: collision with root package name */
    private BaseLineLayout f9967m;

    /* renamed from: n  reason: collision with root package name */
    private AtomicBoolean f9968n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.floor.view.a {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f9969g;

        a(int i2) {
            this.f9969g = i2;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            if (VideoSkuView.this.f9966l.u0(this.f9969g)) {
                VideoSkuView.this.f9966l.O(this.f9969g);
                VideoSkuView.this.t();
                return;
            }
            VideoSkuView.this.f9966l.E0(true);
            VideoSkuView.this.q();
        }

        @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            return super.onError(i2, i3);
        }

        @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            if (a(i2)) {
                VideoSkuView.this.f9965k.animate().alpha(0.0f).start();
                if (!VideoSkuView.this.f9966l.B0()) {
                    VideoSkuView.this.setVolume(0.0f);
                    VideoSkuView.this.f9966l.O(this.f9969g);
                }
                VideoSkuView.this.f9966l.F0(true);
                VideoSkuView.this.f9962h = true;
                VideoSkuView.this.setVisibility(0);
            }
            return super.onInfo(i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends j.c {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.j.c
        public void a(boolean z, String str) {
            if (z) {
                File file = new File(str);
                if (!file.exists() || file.length() <= 0) {
                    return;
                }
                VideoSkuView.this.y(str);
                VideoSkuView.this.w(this.a, file);
            }
        }

        @Override // com.jingdong.app.mall.home.floor.common.i.j.c
        public void b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f9971g;

        c(String str) {
            this.f9971g = str;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (VideoSkuView.this.f9967m == null) {
                return;
            }
            if (!VideoSkuView.this.f9961g) {
                VideoSkuView.this.f9963i = null;
                VideoSkuView.this.setVisibility(0);
                VideoSkuView.this.setVideoPath(this.f9971g);
                return;
            }
            VideoSkuView.this.f9963i = this.f9971g;
        }
    }

    public VideoSkuView(Context context) {
        super(context, "61");
        this.f9968n = new AtomicBoolean(false);
    }

    private void j() {
        if (this.f9967m == null) {
            return;
        }
        if (l()) {
            p();
        } else {
            o();
        }
    }

    private void k(String str, String str2) {
        j.e("homeSkuVideo", PreDownLoadManager.VIDEO_SKU_SUFFIX, str, str2, new b(str));
    }

    private boolean l() {
        if (getVisibility() == 0 || !TextUtils.isEmpty(this.f9963i)) {
            return m.H(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, 0, true);
        }
        return false;
    }

    private boolean m(String str, File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        String string = jdSharedPreferences.getString("PATH_".concat(str), null);
        long j2 = jdSharedPreferences.getLong("SIZE_".concat(str), 0L);
        return j2 > 0 && j2 == file.length() && !TextUtils.isEmpty(string) && TextUtils.equals(string, file.getAbsolutePath());
    }

    public static VideoSkuView n(Context context, View view, String str, BaseLineLayout baseLineLayout) {
        VideoSkuView videoSkuView = o.get(str);
        if (videoSkuView != null) {
            videoSkuView.x(baseLineLayout, view);
            return videoSkuView;
        }
        VideoSkuView videoSkuView2 = new VideoSkuView(context);
        videoSkuView2.x(baseLineLayout, view);
        o.put(str, videoSkuView2);
        return videoSkuView2;
    }

    private void o() {
        this.f9961g = true;
        pause();
    }

    private void p() {
        if (this.f9967m == null || this.f9968n.get()) {
            return;
        }
        this.f9961g = false;
        if (!TextUtils.isEmpty(this.f9963i)) {
            y(this.f9963i);
        } else if (!this.f9962h || isPlaying()) {
        } else {
            start();
            r();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.f9967m == null) {
            return;
        }
        this.f9968n.set(true);
        v();
    }

    private void r() {
        if (this.f9967m == null) {
            return;
        }
        setVisibility(0);
        this.f9965k.setAlpha(0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(String str, File file) {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.putString("PATH_".concat(str), file.getAbsolutePath());
        edit.putLong("SIZE_".concat(str), file.length());
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(String str) {
        f.E0(new c(str));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        j();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        o();
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -1158331917:
                if (type.equals("homePageXViewDisplay")) {
                    c2 = 0;
                    break;
                }
                break;
            case -277321843:
                if (type.equals("home_resume")) {
                    c2 = 1;
                    break;
                }
                break;
            case 815832937:
                if (type.equals("homePageXViewClose")) {
                    c2 = 2;
                    break;
                }
                break;
            case 881725140:
                if (type.equals("home_scroll_stop")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1236015766:
                if (type.equals("home_pause")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 4:
                o();
                return;
            case 1:
            case 2:
            case 3:
                j();
                return;
            default:
                return;
        }
    }

    public void s(com.jingdong.app.mall.home.floor.view.linefloor.base.b bVar, int i2, String str, String str2, String str3) {
        this.f9966l = bVar;
        if (!bVar.A0() && this.f9966l.u0(i2)) {
            this.f9968n.set(false);
            if (this.f9962h && TextUtils.equals(str, this.f9964j)) {
                if (!this.f9966l.B0()) {
                    this.f9966l.O(i2);
                }
                this.f9966l.F0(true);
                t();
                return;
            }
            this.f9961g = false;
            this.f9964j = str;
            this.f9962h = false;
            setOnPlayerStateListener(new a(i2));
            String g2 = j.g("homeSkuVideo", PreDownLoadManager.VIDEO_SKU_SUFFIX, str);
            if (m(str, TextUtils.isEmpty(g2) ? null : new File(g2))) {
                y(g2);
                return;
            } else {
                k(str, str3);
                return;
            }
        }
        q();
    }

    public void t() {
        seekTo(0);
        start();
        r();
    }

    public void u() {
        o();
        v();
        this.f9967m = null;
        f.H0(this);
    }

    public void v() {
        View view = this.f9965k;
        if (view != null) {
            view.animate().cancel();
            this.f9965k.setAlpha(1.0f);
        }
        setVisibility(8);
    }

    public void x(BaseLineLayout baseLineLayout, View view) {
        f.G0(this);
        this.f9967m = baseLineLayout;
        this.f9965k = view;
    }
}
