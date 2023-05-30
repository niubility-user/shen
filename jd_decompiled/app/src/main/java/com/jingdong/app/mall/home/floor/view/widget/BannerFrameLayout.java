package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.state.dark.DarkMaskImageView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.app.util.image.placeholder.JDPlaceholderDrawable;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class BannerFrameLayout extends RelativeLayout {
    private static HashMap<Integer, HomeVideoView> x = new HashMap<>();
    private static final int y = R.id.image_last_url;

    /* renamed from: g */
    private final String f9993g;

    /* renamed from: h */
    private AtomicBoolean f9994h;

    /* renamed from: i */
    private AtomicBoolean f9995i;

    /* renamed from: j */
    private AtomicBoolean f9996j;

    /* renamed from: k */
    private AtomicBoolean f9997k;

    /* renamed from: l */
    private AtomicBoolean f9998l;

    /* renamed from: m */
    private JDDisplayImageOptions f9999m;

    /* renamed from: n */
    private int f10000n;
    private DarkMaskImageView o;
    private SimpleDraweeView p;
    private SimpleDraweeView q;
    private int r;
    private f s;
    private HomeVideoView t;
    private CarouseFigureLayoutPagerAdapter.e u;
    private e v;
    private MallFloorBannerItem w;

    /* loaded from: classes4.dex */
    public class a extends ViewOutlineProvider {
        final /* synthetic */ float a;

        a(BannerFrameLayout bannerFrameLayout, float f2) {
            this.a = f2;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(com.jingdong.app.mall.home.floor.common.d.d(24), 0, com.jingdong.app.mall.home.floor.common.d.d(R2.attr.currentState), com.jingdong.app.mall.home.floor.common.d.d(h.u), this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends JDSimpleImageLoadingListener {

        /* renamed from: g */
        final /* synthetic */ boolean f10001g;

        /* renamed from: h */
        final /* synthetic */ MallFloorBannerItem f10002h;

        b(boolean z, MallFloorBannerItem mallFloorBannerItem) {
            BannerFrameLayout.this = r1;
            this.f10001g = z;
            this.f10002h = mallFloorBannerItem;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            BannerFrameLayout.this.f9998l.set(bitmap != null);
            BannerFrameLayout.this.q(this.f10001g, this.f10002h);
            BannerFrameLayout.this.z();
        }
    }

    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.floor.view.a {
        c() {
            BannerFrameLayout.this = r1;
        }

        @Override // tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            BannerFrameLayout.this.f9995i.set(true);
        }

        @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            return super.onError(i2, i3);
        }

        @Override // com.jingdong.app.mall.home.floor.view.a, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            if (a(i2) && !BannerFrameLayout.this.f9997k.get()) {
                BannerFrameLayout.this.t.setVolume(0.0f);
                BannerFrameLayout.this.v();
                BannerFrameLayout.this.f9997k.set(true);
                if (BannerFrameLayout.this.f9996j.get() && !i.i()) {
                    BannerFrameLayout.this.w(true);
                } else {
                    BannerFrameLayout.this.w(false);
                }
            }
            return super.onInfo(i2, i3);
        }
    }

    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ e f10005g;

        d(e eVar) {
            BannerFrameLayout.this = r1;
            this.f10005g = eVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            BannerFrameLayout.this.t.setVisibility(0);
            BannerFrameLayout.this.t.setVideoPath(this.f10005g.a.getAbsolutePath());
        }
    }

    /* loaded from: classes4.dex */
    public static class e {
        public File a;
        public String b;

        public e(File file, String str, String str2) {
            this.a = file;
            this.b = str2;
        }
    }

    public BannerFrameLayout(Context context) {
        super(context);
        this.f9993g = BannerFrameLayout.class.getSimpleName();
        this.f9994h = new AtomicBoolean(false);
        this.f9995i = new AtomicBoolean(false);
        this.f9996j = new AtomicBoolean(false);
        this.f9997k = new AtomicBoolean(false);
        this.f9998l = new AtomicBoolean(false);
    }

    private void A() {
        if (this.t == null || !this.f9997k.get()) {
            return;
        }
        if (this.f9996j.get()) {
            j();
            this.t.setVisibility(0);
            this.t.seekTo(0);
            this.t.start();
            B();
            return;
        }
        this.t.seekTo(0);
        this.t.pause();
    }

    private void B() {
        int i2;
        int m2 = m.m("HOME_BANNER_VIDEO_DAILY_TIME" + this.v.b) + 1;
        MallFloorBannerItem mallFloorBannerItem = this.w;
        if (mallFloorBannerItem == null || (i2 = mallFloorBannerItem.videoLimit) <= 0 || i2 >= m2) {
            Log.d("updateDisplayTimes", "\u5df2\u7ecf\u64ad\u653e\u4e86" + m2);
            m.M("HOME_BANNER_VIDEO_DAILY_TIME" + this.v.b, m2);
        }
    }

    private void j() {
        this.t.bringToFront();
        View findViewById = findViewById(R.id.mallfloor_banner_adtag);
        if (findViewById != null) {
            findViewById.bringToFront();
        }
    }

    private void l(MallFloorBannerItem mallFloorBannerItem) {
        SimpleDraweeView simpleDraweeView = this.p;
        if (simpleDraweeView != null) {
            simpleDraweeView.setAlpha(0.0f);
        }
        if (mallFloorBannerItem.isUseMask()) {
            if (this.p == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.p = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.height = com.jingdong.app.mall.home.floor.common.d.d(h.u);
                layoutParams.setMargins(com.jingdong.app.mall.home.floor.common.d.d(24), 0, com.jingdong.app.mall.home.floor.common.d.d(24), 0);
                addView(this.p, layoutParams);
            }
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.p, mallFloorBannerItem.getMaskUrl());
        }
    }

    private void n() {
        DarkMaskImageView darkMaskImageView = new DarkMaskImageView(getContext());
        this.o = darkMaskImageView;
        darkMaskImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.height = com.jingdong.app.mall.home.floor.common.d.d(h.u);
        layoutParams.setMargins(com.jingdong.app.mall.home.floor.common.d.d(24), 0, com.jingdong.app.mall.home.floor.common.d.d(24), 0);
        addView(this.o, layoutParams);
    }

    private void o() {
        this.f9994h.set(false);
        this.t.setOnPlayerStateListener(new c());
    }

    private void p(float f2) {
        if (com.jingdong.app.mall.home.o.a.f.M0()) {
            setOutlineProvider(new a(this, f2));
            setClipToOutline(true);
        }
    }

    public void q(boolean z, MallFloorBannerItem mallFloorBannerItem) {
        if (z && mallFloorBannerItem == this.w) {
            int i2 = mallFloorBannerItem.rightSku ? R2.attr.bg : 94;
            if (this.q == null) {
                this.r = i2;
                this.s = new f(200, 200);
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.q = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                this.s.E(i2, 0, 0, 0);
                RelativeLayout.LayoutParams u = this.s.u(this.q);
                u.addRule(15);
                addView(this.q, u);
            }
            if (this.r != i2) {
                this.r = i2;
                this.s.E(i2, 0, 0, 0);
                f.d(this.q, this.s, true);
            } else {
                f.c(this.q, this.s);
            }
            com.jingdong.app.mall.home.n.h.e.d(this.q, com.jingdong.app.mall.home.floor.common.d.d(12));
            this.q.setAlpha(this.f9998l.get() ? 1.0f : 0.0f);
            com.jingdong.app.mall.home.floor.ctrl.e.m(this.q, mallFloorBannerItem.skuImg, com.jingdong.app.mall.home.floor.ctrl.e.w());
            return;
        }
        SimpleDraweeView simpleDraweeView = this.q;
        if (simpleDraweeView != null) {
            simpleDraweeView.setAlpha(0.0f);
        }
    }

    private void r(boolean z, int i2) {
        if (com.jingdong.app.mall.home.o.a.f.M0()) {
            HomeVideoView homeVideoView = x.get(Integer.valueOf(i2));
            this.t = homeVideoView;
            if (homeVideoView == null && z) {
                this.t = new HomeVideoView(getContext(), "15");
                x.put(Integer.valueOf(i2), this.t);
            } else if (homeVideoView == null) {
                return;
            } else {
                ViewParent parent = homeVideoView.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.t);
                }
            }
            if (z) {
                com.jingdong.app.mall.home.o.a.f.G0(this);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(702), com.jingdong.app.mall.home.floor.common.d.d(h.u));
                layoutParams.setMargins(com.jingdong.app.mall.home.floor.common.d.d(24), 0, com.jingdong.app.mall.home.floor.common.d.d(24), 0);
                addView(this.t, 0, layoutParams);
            }
        }
    }

    public void v() {
    }

    private boolean y(e eVar) {
        CarouseFigureLayoutPagerAdapter.e eVar2 = this.u;
        if (eVar2 == null) {
            return false;
        }
        this.f9996j.set(eVar2.a());
        o();
        com.jingdong.app.mall.home.o.a.f.E0(new d(eVar));
        return true;
    }

    public void z() {
        SimpleDraweeView simpleDraweeView = this.p;
        if (simpleDraweeView != null) {
            simpleDraweeView.setAlpha(1.0f);
        }
    }

    public void i(MallFloorBannerItem mallFloorBannerItem) {
        this.w = mallFloorBannerItem;
    }

    public void k(MallFloorBannerItem mallFloorBannerItem, int i2, boolean z) {
        if (this.o == null || mallFloorBannerItem == null) {
            return;
        }
        l(mallFloorBannerItem);
        if (this.f9999m == null || this.f10000n != com.jingdong.app.mall.home.floor.common.d.f9279g) {
            this.f10000n = com.jingdong.app.mall.home.floor.common.d.f9279g;
            this.f9999m = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(true).showImageForEmptyUri(new JDPlaceholderDrawable(21)).showImageOnLoading(new JDPlaceholderDrawable(21)).showImageOnFail(new JDPlaceholderDrawable(21)).setPlaceholder(21);
        }
        boolean z2 = !TextUtils.isEmpty(mallFloorBannerItem.skuImg);
        this.f9999m.bitmapConfig(Bitmap.Config.RGB_565);
        this.f9999m.isScale(false);
        String h2 = com.jingdong.app.mall.home.o.a.f.h((!z || TextUtils.isEmpty(mallFloorBannerItem.transitionImg)) ? mallFloorBannerItem.horizontalImg : mallFloorBannerItem.transitionImg);
        DarkMaskImageView darkMaskImageView = this.o;
        int i3 = y;
        Object tag = darkMaskImageView.getTag(i3);
        Object tag2 = this.o.getTag(JDImageUtils.STATUS_TAG);
        this.f9998l.set(false);
        if (h2 != null && h2.equals(tag) && tag2 != null) {
            if (tag2.equals(2)) {
                this.f9998l.set(true);
                z();
            }
            if (!tag2.equals(3)) {
                q(z2, mallFloorBannerItem);
                return;
            }
        }
        q(z2, mallFloorBannerItem);
        this.o.setTag(i3, h2);
        b bVar = new b(z2, mallFloorBannerItem);
        String b2 = com.jingdong.app.mall.home.m.a.b(h2);
        String str = !TextUtils.isEmpty(b2) ? b2 : h2;
        if (tag != null && i2 == 0) {
            com.jingdong.app.mall.home.floor.ctrl.f.h(str, this.o, this.f9999m, false, bVar, null);
        } else {
            com.jingdong.app.mall.home.floor.ctrl.f.h(str, this.o, this.f9999m, true, bVar, null);
        }
    }

    public DarkMaskImageView m() {
        return this.o;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        if (this.t == null) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.G0(this);
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        if (this.t == null) {
            return;
        }
        super.onDetachedFromWindow();
        if (getParent() == null || getParent().getParent() == null) {
            com.jingdong.app.mall.home.o.a.f.H0(this);
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (this.u == null) {
            return;
        }
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -277321843:
                if (type.equals("home_resume")) {
                    c2 = 0;
                    break;
                }
                break;
            case 436492672:
                if (type.equals("home_splash_close")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1236015766:
                if (type.equals("home_pause")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
                if (Log.D) {
                    Log.d(this.f9993g, "BannerVideo can start");
                }
                if (this.t != null) {
                    w(this.u.a());
                    return;
                }
                return;
            case 2:
                if (Log.D) {
                    Log.d(this.f9993g, "BannerVideo can stop");
                }
                if (this.t != null) {
                    w(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void s(boolean z, int i2, float f2) {
        p(f2);
        r(z, i2);
        n();
    }

    public boolean t(e eVar) {
        HomeVideoView homeVideoView;
        if (eVar == null || (homeVideoView = this.t) == null || eVar.a == null) {
            return false;
        }
        this.v = eVar;
        homeVideoView.release();
        this.t.setVisibility(8);
        return y(eVar);
    }

    public void u() {
        HomeVideoView homeVideoView = this.t;
        if (homeVideoView != null) {
            homeVideoView.release();
            ViewParent parent = this.t.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.t);
            }
            this.t = null;
        }
    }

    public void w(boolean z) {
        this.f9996j.set(z);
        A();
    }

    public void x(CarouseFigureLayoutPagerAdapter.e eVar) {
        this.u = eVar;
    }
}
