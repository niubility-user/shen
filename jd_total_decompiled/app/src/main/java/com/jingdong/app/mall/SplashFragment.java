package com.jingdong.app.mall;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.mall.ad.c;
import com.jingdong.app.mall.ad.widget.AdButtonLottieView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.ctrl.q;
import com.jingdong.app.mall.home.widget.HomeSurfaceParent;
import com.jingdong.app.mall.home.widget.HomeSurfaceView;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDSharedCommandUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.widget.video.VideoInfoReporter;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;

/* loaded from: classes19.dex */
public class SplashFragment extends BaseFragment implements View.OnTouchListener {
    private volatile SurfaceHolder B;
    private long C;
    private volatile boolean F;
    private volatile v G;
    private boolean K;
    private float L;

    /* renamed from: g  reason: collision with root package name */
    private View f7844g;

    /* renamed from: h  reason: collision with root package name */
    private RelativeLayout f7845h;

    /* renamed from: i  reason: collision with root package name */
    private HomeSurfaceParent f7846i;

    /* renamed from: j  reason: collision with root package name */
    private RelativeLayout f7847j;

    /* renamed from: k  reason: collision with root package name */
    private View f7848k;

    /* renamed from: l  reason: collision with root package name */
    private RelativeLayout f7849l;

    /* renamed from: m  reason: collision with root package name */
    private TextView f7850m;

    /* renamed from: n  reason: collision with root package name */
    private SimpleDraweeView f7851n;
    private com.jingdong.app.mall.ad.d v;
    private String o = "StartPhoto_Main";
    private String p = "";
    private String q = "";
    private int r = 0;
    private long s = 0;
    private long t = 0;
    private long u = SystemClock.elapsedRealtime();
    private MediaPlayer w = null;
    private VideoInfoReporter x = null;
    private long y = -1;
    private long z = -1;
    private HomeSurfaceView A = null;
    private int D = 0;
    private int E = 1;
    private Handler H = new Handler(Looper.getMainLooper());
    private boolean I = false;
    private Runnable J = new k();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements MediaPlayer.OnCompletionListener {
        a() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            if (Log.D) {
                Log.d(XView2Constants.SPLASHFRAGMENT, "===>>> mMediaPlayer onCompletion");
            }
            if (SplashFragment.this.x != null) {
                SplashFragment.this.x.onCompletion();
            }
            if (!TextUtils.isEmpty(SplashFragment.this.v.q) && !TextUtils.isEmpty(SplashFragment.this.v.r) && com.jingdong.app.mall.home.o.a.f.M0()) {
                SplashFragment.this.E0();
            } else {
                SplashFragment.this.i0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b implements MediaPlayer.OnInfoListener {
        b() {
        }

        @Override // android.media.MediaPlayer.OnInfoListener
        public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
            if (SplashFragment.this.x != null) {
                SplashFragment.this.x.onInfo(i2, i3);
                return false;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class c implements MediaPlayer.OnErrorListener {
        c() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
            if (SplashFragment.this.x != null) {
                SplashFragment.this.x.onError(i2, i3);
                return false;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class d implements View.OnTouchListener {
        d() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1 && SplashFragment.this.k0()) {
                SplashFragment.this.t0();
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class e implements MediaPlayer.OnVideoSizeChangedListener {
        e() {
        }

        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
            SplashFragment.this.Y(i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SplashFragment.this.k0()) {
                SplashFragment.this.t0();
                SplashFragment.this.x0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class g implements SurfaceHolder.Callback {
        g() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            try {
                if (SplashFragment.this.w != null && SplashFragment.this.A != null) {
                    SplashFragment.this.w.setDisplay(SplashFragment.this.A.getHolder());
                    SplashFragment.this.w.setDataSource(SplashFragment.this.v.f7907h);
                    SplashFragment.this.w.prepare();
                    SplashFragment.this.y = SystemClock.elapsedRealtime();
                    if (SplashFragment.this.x != null) {
                        SplashFragment.this.x.onCreatePlayer();
                    }
                }
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
                SplashFragment.this.i0();
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class h extends com.jingdong.app.mall.home.o.a.b {

        /* loaded from: classes19.dex */
        class a implements q.f {
            a() {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.q.f
            public void finish() {
                SplashFragment.this.i0();
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.q.f
            public void start() {
                if (SplashFragment.this.f7846i != null) {
                    SplashFragment.this.f7846i.a(true);
                }
                if (SplashFragment.this.f7844g != null) {
                    SplashFragment.this.f7844g.setAlpha(0.0f);
                }
                if (SplashFragment.this.f7849l != null) {
                    SplashFragment.this.f7849l.setAlpha(0.0f);
                }
            }
        }

        h() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (SplashFragment.this.f7847j != null) {
                SplashFragment.this.f7847j.setBackgroundColor(0);
            }
            new com.jingdong.app.mall.home.floor.ctrl.q(SplashFragment.this.A, SplashFragment.this.f7846i, SplashFragment.this.v.q, SplashFragment.this.v.r, new a()).o();
            com.jingdong.app.mall.ad.c.l().y(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class i implements View.OnClickListener {
        i() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SplashFragment.this.k0()) {
                SplashFragment.this.t0();
                SplashFragment.this.x0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class j implements View.OnClickListener {
        j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (Log.I) {
                Log.i(XView2Constants.SPLASHFRAGMENT, "click skip button.");
            }
            SplashFragment.this.i0();
            if (TextUtils.isEmpty(SplashFragment.this.p)) {
                SplashFragment.this.p = "1_1_" + SplashFragment.this.n0(0) + "_0_" + SplashFragment.this.r + CartConstant.KEY_YB_INFO_LINK + SplashFragment.this.E;
                SplashFragment.this.q = com.jingdong.app.mall.ad.c.l().n(SplashFragment.this.v, "1", "0", SplashFragment.this.E);
            }
            SplashFragment.this.x0();
            com.jingdong.app.mall.home.r.c.a.t("StartPhoto_Skip", SplashFragment.this.p, SplashFragment.this.v.t, SplashFragment.this.o);
            new com.jingdong.app.mall.home.q.a("\u542f\u52a8\u56fe\u8df3\u8fc7", SplashFragment.this.v.p).b();
        }
    }

    /* loaded from: classes19.dex */
    class k implements Runnable {
        k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SplashFragment.this.f7845h != null) {
                SplashFragment.this.f7845h.setVisibility(8);
            }
            SplashFragment.this.i0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class l implements SurfaceHolder.Callback {
        l() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (Log.D) {
                Log.d(XView2Constants.SPLASHFRAGMENT, "mCountDownView => surfaceCreated");
            }
            SplashFragment.this.B = surfaceHolder;
            if (SplashFragment.this.G != null) {
                SplashFragment.this.G.b();
                return;
            }
            SplashFragment splashFragment = SplashFragment.this;
            splashFragment.G = new v();
            new Thread(SplashFragment.this.G).start();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (Log.D) {
                Log.d(XView2Constants.SPLASHFRAGMENT, "mCountDownView => surfaceDestroyed");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class m implements Runnable {
        m() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SplashFragment.this.c0();
        }
    }

    /* loaded from: classes19.dex */
    class n implements View.OnTouchListener {
        n() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                SplashFragment.this.i0();
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class o implements View.OnClickListener {
        o() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SplashFragment.this.b0()) {
                SplashFragment.this.t0();
                SplashFragment.this.x0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class p implements ImageRequestListener<ImageInfo> {
        p() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(ImageInfo imageInfo) {
            SplashFragment.this.f7850m.setVisibility(8);
            SplashFragment.this.f7851n.setVisibility(0);
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
            SplashFragment.this.f7850m.setVisibility(0);
            SplashFragment.this.f7851n.setVisibility(8);
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
            SplashFragment.this.f7850m.setVisibility(0);
            SplashFragment.this.f7851n.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class q implements View.OnClickListener {
        q() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SplashFragment.this.b0()) {
                SplashFragment.this.t0();
                SplashFragment.this.x0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class r implements View.OnClickListener {
        r() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SplashFragment.this.b0()) {
                SplashFragment.this.t0();
                SplashFragment.this.x0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class s implements View.OnClickListener {
        s() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SplashFragment.this.C0()) {
                SplashFragment.this.t0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class t implements c.InterfaceC0236c {

        /* loaded from: classes19.dex */
        class a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ Bitmap f7870g;

            a(Bitmap bitmap) {
                this.f7870g = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                SplashFragment.this.d0(this.f7870g);
            }
        }

        t() {
        }

        @Override // com.jingdong.app.mall.ad.c.InterfaceC0236c
        public void a(Bitmap bitmap) {
            if (bitmap == null) {
                SplashFragment.this.i0();
            } else {
                SplashFragment.this.H.post(new a(bitmap));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class u implements MediaPlayer.OnPreparedListener {

        /* loaded from: classes19.dex */
        class a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ MediaPlayer f7873g;

            a(MediaPlayer mediaPlayer) {
                this.f7873g = mediaPlayer;
            }

            @Override // java.lang.Runnable
            public void run() {
                SplashFragment.this.f7847j.setBackgroundResource(com.jingdong.app.mall.home.R.drawable.launch_theme_bg);
                SplashFragment.this.f7848k.setVisibility(8);
                SplashFragment splashFragment = SplashFragment.this;
                splashFragment.B0(splashFragment.o0(this.f7873g.getVideoWidth(), this.f7873g.getVideoHeight(), SplashFragment.this.f7846i.getWidth(), SplashFragment.this.f7846i.getHeight()));
                if (SplashFragment.this.v.f7903c == 1 || SplashFragment.this.v.f7903c == 2) {
                    SplashFragment.this.e0(true);
                }
            }
        }

        u() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            try {
                SplashFragment.this.C = mediaPlayer.getDuration();
                SplashFragment.this.w.start();
                SplashFragment.this.H.postDelayed(new a(mediaPlayer), 10L);
                if (SplashFragment.this.y > 0 && SplashFragment.this.x != null) {
                    SplashFragment.this.x.onPrepared(SystemClock.elapsedRealtime() - SplashFragment.this.y);
                    SplashFragment.this.y = -1L;
                    SplashFragment.this.x.setVideoLength(SplashFragment.this.w.getDuration());
                }
                SplashFragment.this.z = SystemClock.elapsedRealtime();
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
                SplashFragment.this.i0();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes19.dex */
    public class v implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public volatile boolean f7875g = false;

        /* renamed from: h  reason: collision with root package name */
        private Paint f7876h;

        public v() {
            Paint paint = new Paint();
            this.f7876h = paint;
            paint.setAntiAlias(true);
            this.f7876h.setTextAlign(Paint.Align.CENTER);
            this.f7876h.setColor(-1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized boolean b() {
            SplashFragment splashFragment = SplashFragment.this;
            splashFragment.s = (splashFragment.u + SplashFragment.this.t) - SystemClock.elapsedRealtime();
            if (Log.D) {
                Log.d(XView2Constants.SPLASHFRAGMENT, "mLeftTime => " + SplashFragment.this.s);
            }
            if (SplashFragment.this.B == null) {
                return true;
            }
            SurfaceHolder surfaceHolder = SplashFragment.this.B;
            this.f7876h.setTextSize(SplashFragment.this.l0(32));
            try {
                Canvas lockCanvas = surfaceHolder.lockCanvas();
                if (lockCanvas == null) {
                    boolean z = SplashFragment.this.s <= 0;
                    c(lockCanvas, surfaceHolder);
                    return z;
                }
                lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                String str = "0";
                if (SplashFragment.this.s > 0) {
                    double d = SplashFragment.this.s;
                    Double.isNaN(d);
                    str = String.valueOf((int) Math.ceil(d / 1000.0d));
                }
                Paint.FontMetrics fontMetrics = this.f7876h.getFontMetrics();
                int l0 = SplashFragment.this.l0(50) >> 1;
                float f2 = fontMetrics.bottom;
                float l02 = ((SplashFragment.this.l0(50) / 2.0f) + (((f2 - fontMetrics.top) / 2.0f) - f2)) - SplashFragment.this.l0(2);
                if (SplashFragment.this.F) {
                    str = "";
                }
                lockCanvas.drawText(str, l0, l02, this.f7876h);
                c(lockCanvas, surfaceHolder);
                return SplashFragment.this.s <= 0;
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
                c(null, surfaceHolder);
                return true;
            }
        }

        private void c(Canvas canvas, SurfaceHolder surfaceHolder) {
            if (canvas == null || surfaceHolder == null) {
                return;
            }
            try {
                surfaceHolder.unlockCanvasAndPost(canvas);
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            while (SplashFragment.this.s > 0 && !this.f7875g) {
                if (b()) {
                    SplashFragment.this.y0(0);
                    return;
                }
                try {
                    Thread.sleep(500L);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void A0() {
        try {
            BaseActivity baseActivity = this.thisActivity;
            if (baseActivity != null && !baseActivity.isFinishing()) {
                FragmentTransaction beginTransaction = this.thisActivity.getSupportFragmentManager().beginTransaction();
                beginTransaction.remove(this);
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B0(int i2) {
        if (this.f7849l == null) {
            return;
        }
        if (!b0() && !C0()) {
            this.f7849l.setVisibility(8);
            return;
        }
        int max = Math.max(i2, l0(SwitchQueryFetcher.getSwitchIntValue("mp_ad_bottom_min_margin", 0)));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f7849l.getLayoutParams();
        layoutParams.bottomMargin = max;
        this.f7849l.setLayoutParams(layoutParams);
        this.f7849l.setAlpha(1.0f);
    }

    private void D0() {
        com.jingdong.app.mall.ad.c.l().x(this.v.f7904e);
        this.E = com.jingdong.app.mall.ad.c.l().m("start_image_show_times");
        if (this.v.f7910k.size() == 1) {
            com.jingdong.app.mall.ad.d dVar = this.v;
            this.D = dVar.d;
            postUrl(dVar.f7911l);
            new com.jingdong.app.mall.home.q.a("\u542f\u52a8\u56fe\u66dd\u5149", this.v.f7913n).b();
            if (this.D == 3 && TextUtils.isEmpty(this.v.f7907h)) {
                this.D = 0;
            }
            int i2 = this.D;
            if (i2 == 3) {
                this.p = "2_null_" + n0(0) + "_0_" + this.r + CartConstant.KEY_YB_INFO_LINK + this.E;
                this.q = com.jingdong.app.mall.ad.c.l().n(this.v, "2", "0", this.E);
                f0();
                this.rootView.setBackgroundColor(0);
            } else if (i2 == 0) {
                this.p = "0_null_" + n0(0) + "_0_" + this.r + CartConstant.KEY_YB_INFO_LINK + this.E;
                this.q = com.jingdong.app.mall.ad.c.l().n(this.v, "0", "0", this.E);
                com.jingdong.app.mall.ad.b m0 = m0(0);
                if (m0 != null) {
                    com.jingdong.app.mall.ad.c.l().j(m0, new t());
                }
            } else {
                g0();
                return;
            }
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "StartPhoto_Popup", this.p, this.o, getClass().getName(), "", this.q, com.jingdong.app.mall.ad.c.l().k());
            if (TextUtils.isEmpty(this.v.x)) {
                return;
            }
            JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "StartPhoto_PopupAD", this.p, this.o, getClass().getName(), "", this.q, com.jingdong.app.mall.ad.c.l().k());
            return;
        }
        g0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E0() {
        this.H.post(new h());
    }

    private void U() {
        TextView textView = new TextView(JdSdk.getInstance().getApplicationContext());
        this.f7850m = textView;
        textView.setSingleLine();
        this.f7850m.setGravity(17);
        this.f7850m.setEllipsize(TextUtils.TruncateAt.END);
        this.f7850m.setTextSize(0, l0(40));
        this.f7850m.setTextColor(-1);
        this.f7850m.setText(this.v.A);
        this.f7850m.setBackgroundResource(com.jingdong.app.mall.home.R.drawable.splash_skip_btn_bg);
        Drawable drawable = getResources().getDrawable(com.jingdong.app.mall.home.R.drawable.splash_skip_btn_arrow);
        drawable.setBounds(0, 0, l0(32), l0(28));
        this.f7850m.setCompoundDrawables(null, null, drawable, null);
        this.f7850m.setCompoundDrawablePadding(l0(16));
        this.f7850m.setPadding(l0(100), 0, l0(100), 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, l0(112));
        layoutParams.addRule(13);
        this.f7849l.addView(this.f7850m, layoutParams);
        this.f7850m.setVisibility(8);
        HomeDraweeView homeDraweeView = new HomeDraweeView(JdSdk.getInstance().getApplicationContext());
        this.f7851n = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(l0(R2.attr.calendarViewShown), l0(112));
        layoutParams2.addRule(13);
        this.f7849l.addView(this.f7851n, layoutParams2);
        JDImageLoader.display(this.v.B, this.f7851n, com.jingdong.app.mall.home.floor.ctrl.e.f9402h, new p());
        this.f7850m.setOnClickListener(new q());
        this.f7851n.setOnClickListener(new r());
    }

    private void V(int i2, int i3) {
        RelativeLayout relativeLayout = (RelativeLayout) this.rootView.findViewById(com.jingdong.app.mall.home.R.id.splash_content);
        View view = new View(JdSdk.getInstance().getApplicationContext());
        int i4 = com.jingdong.app.mall.home.R.id.view;
        view.setId(i4);
        view.setVisibility(4);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, l0(1));
        layoutParams.addRule(12);
        relativeLayout.addView(view, layoutParams);
        this.f7849l = new RelativeLayout(JdSdk.getInstance().getApplicationContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i2, i3);
        layoutParams2.addRule(14);
        layoutParams2.addRule(2, i4);
        relativeLayout.addView(this.f7849l, layoutParams2);
        this.f7849l.setAlpha(0.0f);
    }

    private void W() {
        AdButtonLottieView adButtonLottieView = new AdButtonLottieView(JdSdk.getInstance().getApplicationContext(), getClass().getSimpleName());
        TextView textView = new TextView(JdSdk.getInstance().getApplicationContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(l0(R2.attr.calendarViewShown), l0(132));
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        layoutParams.bottomMargin = l0(40);
        this.f7849l.addView(textView, layoutParams);
        textView.setOnTouchListener(this);
        textView.setOnClickListener(new s());
        if (adButtonLottieView.e()) {
            this.f7849l.addView(adButtonLottieView, new RelativeLayout.LayoutParams(-1, -1));
            adButtonLottieView.setText(p0());
            adButtonLottieView.playAnimation();
            return;
        }
        textView.setSingleLine();
        textView.setGravity(17);
        textView.setTextSize(0, l0(40));
        textView.setTextColor(-1);
        textView.setText(p0());
        textView.setBackgroundResource(com.jingdong.app.mall.home.R.drawable.splash_skip_btn_bg);
    }

    private void X(boolean z) {
        if (z) {
            return;
        }
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "StartPhoto_ToBackstage", this.p, this.o, getClass().getName(), "", this.v.t, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y(float f2, float f3) {
        try {
            int width = this.f7846i.getWidth();
            int height = this.f7846i.getHeight();
            float max = Math.max(width / f2, height / f3);
            this.A.g(width, height, (int) ((f2 * max) + 0.5f), (int) ((max * f3) + 0.5f));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void Z() {
        try {
            View view = this.rootView;
            if (view != null) {
                view.setAlpha(0.0f);
            }
            View view2 = this.f7844g;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            RelativeLayout relativeLayout = this.f7849l;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(8);
            }
            RelativeLayout relativeLayout2 = this.f7845h;
            if (relativeLayout2 != null) {
                relativeLayout2.setVisibility(8);
            }
            HomeSurfaceView homeSurfaceView = this.A;
            if (homeSurfaceView != null) {
                homeSurfaceView.setZOrderOnTop(true);
                this.A.setBackgroundColor(0);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a0(boolean z) {
        if (this.v.b > 0) {
            this.t = r0 * 1000;
        } else {
            this.t = 0L;
        }
        if (this.D == 3) {
            this.s = this.C;
        } else {
            this.s = (this.u + this.t) - SystemClock.elapsedRealtime();
        }
        if (Log.D) {
            Log.d(XView2Constants.SPLASHFRAGMENT, "mLeftTime " + this.s);
        }
        if (z && this.s > 0) {
            this.H.post(new m());
        } else {
            y0((int) this.s);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0() {
        if (this.f7845h == null) {
            return;
        }
        SurfaceView surfaceView = new SurfaceView(this.thisActivity);
        surfaceView.setZOrderOnTop(true);
        surfaceView.getHolder().setFormat(-3);
        surfaceView.getHolder().addCallback(new l());
        int l0 = l0(50);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f7845h.getLayoutParams();
        layoutParams.width = l0;
        layoutParams.height = l0;
        this.f7845h.addView(surfaceView, new RelativeLayout.LayoutParams(l0, l0));
        this.f7845h.bringToFront();
        this.f7845h.setVisibility(0);
        View view = this.f7844g;
        if (view != null) {
            int i2 = this.v.f7903c;
            if (i2 == 1 || i2 == 2) {
                view.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(Bitmap bitmap) {
        if (bitmap == null) {
            g0();
            return;
        }
        HomeDraweeView homeDraweeView = new HomeDraweeView(this.thisActivity);
        homeDraweeView.setContentDescription("\u542f\u52a8\u56fe\u5e7f\u544a");
        homeDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        homeDraweeView.setImageBitmap(bitmap);
        homeDraweeView.setOnTouchListener(this);
        homeDraweeView.setOnClickListener(new i());
        this.f7846i.addView(homeDraweeView, new RelativeLayout.LayoutParams(-1, -1));
        B0(o0(bitmap.getWidth(), bitmap.getHeight(), this.f7846i.getWidth(), this.f7846i.getHeight()));
        int i2 = this.v.f7903c;
        boolean z = false;
        if (i2 == 1 || i2 == 2) {
            e0(false);
            TextView textView = (TextView) this.f7844g.findViewById(com.jingdong.app.mall.home.R.id.splash_skip_txt);
            if (this.v.a == 1) {
                textView.setPadding(l0(56), 0, l0(20), l0(1));
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f7845h.getLayoutParams();
                int i3 = this.v.f7903c == 2 ? 8 : 6;
                int i4 = com.jingdong.app.mall.home.R.id.splash_skip;
                layoutParams.addRule(i3, i4);
                layoutParams.addRule(5, i4);
                this.f7845h.setLayoutParams(layoutParams);
                z = true;
            } else {
                textView.setPadding(l0(20), 0, l0(20), l0(1));
            }
        }
        a0(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0(boolean z) {
        View findViewById = this.rootView.findViewById(com.jingdong.app.mall.home.R.id.splash_skip);
        this.f7844g = findViewById;
        findViewById.setContentDescription("\u8df3\u8fc7");
        this.f7844g.setOnClickListener(new j());
        TextView textView = (TextView) this.f7844g.findViewById(com.jingdong.app.mall.home.R.id.splash_skip_txt);
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.height = l0(50);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(0, l0(28));
        textView.setPadding(l0(20), 0, l0(20), l0(1));
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f7844g.getLayoutParams();
        layoutParams2.addRule(11);
        layoutParams2.addRule(this.v.f7903c == 2 ? 10 : 12);
        this.f7844g.setLayoutParams(layoutParams2);
        this.f7844g.setPadding(0, this.v.f7903c == 2 ? l0(128) : 0, l0(32), this.v.f7903c == 2 ? 0 : l0(50));
        if (z || this.v.a != 1) {
            this.f7844g.setVisibility(0);
        }
    }

    private void f0() {
        if (Log.D) {
            Log.d(XView2Constants.SPLASHFRAGMENT, "===>>> displayVideo");
        }
        this.f7848k.setVisibility(0);
        com.jingdong.app.mall.ad.d dVar = this.v;
        this.x = new VideoInfoReporter(dVar.f7906g, "20", dVar.f7908i, RecommendMtaUtils.Home_PageId, "", "", "", "");
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.w = mediaPlayer;
        mediaPlayer.setVolume(0.0f, 0.0f);
        this.w.setOnPreparedListener(new u());
        this.w.setOnCompletionListener(new a());
        this.w.setOnInfoListener(new b());
        this.w.setOnErrorListener(new c());
        this.f7846i.setContentDescription("\u542f\u52a8\u56fe\u5e7f\u544a");
        this.f7846i.setOnTouchListener(new d());
        this.w.setOnVideoSizeChangedListener(new e());
        HomeSurfaceView homeSurfaceView = (HomeSurfaceView) this.rootView.findViewById(com.jingdong.app.mall.home.R.id.splash_surface_view);
        this.A = homeSurfaceView;
        homeSurfaceView.setContentDescription("\u542f\u52a8\u56fe\u5e7f\u544a");
        this.A.setOnTouchListener(this);
        this.A.setOnClickListener(new f());
        this.A.getHolder().addCallback(new g());
        this.A.setVisibility(0);
    }

    private void g0() {
        try {
            h0(false);
        } catch (Exception unused) {
            A0();
        }
    }

    private void h0(boolean z) {
        v0(z);
        JDSharedCommandUtils.getInstance().notifyJDShareOpen();
        this.H.removeCallbacksAndMessages(null);
        if (this.G != null) {
            this.G.f7875g = true;
        }
        this.B = null;
        this.G = null;
        com.jingdong.app.mall.ad.c.f();
        A0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int l0(int i2) {
        return com.jingdong.app.mall.home.floor.common.d.b(this.thisActivity, i2);
    }

    private com.jingdong.app.mall.ad.b m0(int i2) {
        if (this.v.f7910k.size() > i2) {
            return this.v.f7910k.get(i2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String n0(int i2) {
        return this.v.f7910k.size() > i2 ? this.v.f7910k.get(i2).a : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int o0(float f2, float f3, float f4, float f5) {
        int l0 = l0(R2.anim.popup_center_enter);
        if (f2 != 0.0f && f3 != 0.0f && f4 != 0.0f && f5 != 0.0f) {
            try {
                l0 = (int) (l0 - (((int) ((f3 * Math.max(f4 / f2, f5 / f3)) - f5)) >> 1));
                if (l0 < l0(20)) {
                    this.K = true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                this.K = true;
            }
        }
        return l0;
    }

    private String p0() {
        com.jingdong.app.mall.ad.d dVar = this.v;
        return (dVar == null || TextUtils.isEmpty(dVar.A)) ? "\u6ed1\u52a8\u6216\u70b9\u51fb\u67e5\u770b\u8be6\u60c5" : com.jingdong.app.mall.home.o.a.f.j(10, this.v.A);
    }

    private void q0() {
        if (b0()) {
            V(-1, l0(this.v.z == 1 ? 286 : 220));
            if (this.v.z == 0) {
                U();
            } else {
                this.f7849l.setOnClickListener(new o());
            }
        } else if (C0()) {
            V(l0(600), l0(300));
            W();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t0() {
        u0(false);
    }

    private void u0(boolean z) {
        JumpUtil.execJump(this.thisActivity, this.v.f7909j, 1);
        String str = z ? "StartPhoto_Slide" : "StartPhoto_StartPic";
        postUrl(this.v.f7912m);
        new com.jingdong.app.mall.home.q.a("\u542f\u52a8\u56fe\u70b9\u51fb", this.v.o).b();
        com.jingdong.app.mall.home.r.c.a.u(str, this.p, this.q, this.o, com.jingdong.app.mall.ad.c.l().k(), "");
        com.jingdong.app.mall.home.floor.ctrl.t.i.p().C(false);
        j0(true);
    }

    private void v0(boolean z) {
        com.jingdong.app.mall.home.floor.ctrl.t.i.p().E(false);
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            return;
        }
        z0.U0(z);
    }

    private void w0() {
        com.jingdong.app.mall.home.floor.ctrl.t.i.p().E(true);
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 == null) {
            return;
        }
        z0.V0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x0() {
        if ("1".equals(this.v.v)) {
            SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
            edit.putInt("noise_reduction" + this.v.s, 1);
            edit.apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y0(int i2) {
        this.H.removeCallbacks(this.J);
        this.H.postDelayed(this.J, i2);
    }

    private void z0() {
        try {
            MediaPlayer mediaPlayer = this.w;
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                this.w.release();
                this.w = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean C0() {
        com.jingdong.app.mall.ad.d dVar = this.v;
        return dVar != null && dVar.y == 3;
    }

    public boolean b0() {
        com.jingdong.app.mall.ad.d dVar = this.v;
        return (dVar == null || dVar.y != 2 || s0() || r0()) ? false : true;
    }

    public void i0() {
        j0(false);
    }

    public void j0(boolean z) {
        this.I = true;
        if (Log.I) {
            Log.i(XView2Constants.SPLASHFRAGMENT, "finish.");
        }
        Z();
        h0(z);
        z0();
        if (this.x != null) {
            long j2 = -1;
            if (this.z > 0) {
                this.z = -1L;
                j2 = SystemClock.elapsedRealtime() - this.z;
            }
            this.x.report(j2);
        }
    }

    public boolean k0() {
        com.jingdong.app.mall.ad.d dVar = this.v;
        if (dVar == null) {
            return false;
        }
        return dVar.y == 1 || s0() || r0();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        ArrayList<com.jingdong.app.mall.ad.b> arrayList;
        JDSharedCommandUtils.getInstance().setJdShareCommandWaitOutside();
        setIsUseBasePV(false);
        if (!CommonBase.getJdSharedPreferences().getBoolean(BaseFrameUtil.getPreName(), false)) {
            g0();
            return null;
        }
        com.jingdong.app.mall.ad.d i2 = com.jingdong.app.mall.ad.c.l().i();
        this.v = i2;
        if (i2 != null && (arrayList = i2.f7910k) != null && arrayList.size() != 0) {
            w0();
            View inflate = layoutInflater.inflate(com.jingdong.app.mall.home.R.layout.splash_fragment, (ViewGroup) null);
            this.rootView = inflate;
            this.f7845h = (RelativeLayout) inflate.findViewById(com.jingdong.app.mall.home.R.id.splash_countdown);
            this.f7847j = (RelativeLayout) this.rootView.findViewById(com.jingdong.app.mall.home.R.id.splash_surface_parent);
            this.f7848k = this.rootView.findViewById(com.jingdong.app.mall.home.R.id.splash_surface_loading);
            HomeSurfaceParent homeSurfaceParent = (HomeSurfaceParent) this.rootView.findViewById(com.jingdong.app.mall.home.R.id.splash_ad_layout);
            this.f7846i = homeSurfaceParent;
            homeSurfaceParent.setOnTouchListener(new n());
            q0();
            this.u = SystemClock.elapsedRealtime();
            D0();
            if (TextUtils.equals(this.v.u, "1")) {
                com.jingdong.app.mall.ad.c.l().y(true);
            }
            return this.rootView;
        }
        g0();
        return null;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.thisActivity.setNetworkModel(true);
        BaseActivity baseActivity = this.thisActivity;
        baseActivity.needCheckNet = true;
        baseActivity.checkNetwork(1);
        super.onDestroy();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        X(this.I);
        this.F = true;
        if (this.G != null) {
            this.G.b();
        }
        super.onPause();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.F = false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.L = motionEvent.getY();
            return false;
        } else if (action != 1) {
            return false;
        } else {
            if (this.L - motionEvent.getY() <= l0(100) || !C0()) {
                return false;
            }
            u0(true);
            return true;
        }
    }

    public void postUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setPost(false);
        httpSetting.setType(6000);
        httpSetting.setCacheMode(2);
        httpSetting.setEffect(0);
        ((BaseActivity) getActivity()).getHttpGroupaAsynPool().add(httpSetting);
    }

    public boolean r0() {
        com.jingdong.app.mall.ad.d dVar = this.v;
        return dVar != null && dVar.y == 2 && dVar.z == 1 && this.K;
    }

    public boolean s0() {
        com.jingdong.app.mall.ad.d dVar = this.v;
        if (dVar == null) {
            return true;
        }
        return dVar.y == 2 && dVar.z == 0 && TextUtils.isEmpty(dVar.A);
    }
}
