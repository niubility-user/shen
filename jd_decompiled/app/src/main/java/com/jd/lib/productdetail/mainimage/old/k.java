package com.jd.lib.productdetail.mainimage.old;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PDVedioShareEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdVideoSymbolList;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMasterVideoMarkInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMaterVideoInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTopVideoControl;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.SegmentRadioGroup;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.video.controller.ItemPlayerController;
import com.jingdong.common.unification.video.player.AVideoMtaListener;
import com.jingdong.common.unification.video.player.AVideoPlayStateListener;
import com.jingdong.common.unification.video.player.AVideoViewBtClickListener;
import com.jingdong.common.unification.video.player.IProgrssChangeListener;
import com.jingdong.common.unification.video.player.IVideoViewOnTouchListener;
import com.jingdong.common.unification.video.player.VideoPlayView;
import com.jingdong.common.videoplayer.IVideoPlayerCtrlViewListener;
import com.jingdong.common.videoplayer.IViewPlayerControl;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.utils.PlatformTools;
import com.jingdong.sdk.utils.DPIUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tv.danmaku.ijk.media.example.widget.media.IPlayerControl;

/* loaded from: classes15.dex */
public class k {
    public static final int P = PDUtils.dip2px(80.0f);
    public static final int Q = PDUtils.dip2px(80.0f);
    public static volatile k R = null;
    public static Map<String, k> S = new HashMap(3);
    public final String A;
    public WeakReference<Context> D;
    public boolean F;
    public String G;
    public int I;
    public int J;
    public List<Animator> K;
    public VideoPlayView a;
    public SegmentRadioGroup b;

    /* renamed from: c */
    public SparseArray<RadioButton> f5160c;
    public List<Integer> d;

    /* renamed from: e */
    public Map<String, Integer> f5161e;

    /* renamed from: f */
    public l f5162f;

    /* renamed from: g */
    public p f5163g;

    /* renamed from: h */
    public o f5164h;

    /* renamed from: i */
    public m f5165i;

    /* renamed from: j */
    public InterfaceC0159k f5166j;

    /* renamed from: k */
    public n f5167k;
    public float s;
    public float t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public PdMainImagePresenter z;

    /* renamed from: l */
    public int f5168l = 0;

    /* renamed from: m */
    public int f5169m = 0;

    /* renamed from: n */
    public boolean f5170n = false;
    public boolean o = false;
    public boolean p = false;
    public int q = PDUtils.dip2px(100.0f);
    public int r = PDUtils.dip2px(100.0f);
    public boolean B = false;
    public boolean C = false;
    public WeakReference<ViewGroup> E = null;
    public boolean H = false;
    public IVideoViewOnTouchListener L = new c();
    public IVideoViewOnTouchListener M = new d();
    public View.OnClickListener N = new e();
    public IProgrssChangeListener O = new f();

    /* loaded from: classes15.dex */
    public class a implements IVideoPlayerCtrlViewListener {
        public a() {
            k.this = r1;
        }

        @Override // com.jingdong.common.videoplayer.IVideoPlayerCtrlViewListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDVideoPlayerCtrlViewListener
        public void hide() {
            k.this.f5163g.a();
        }

        @Override // com.jingdong.common.videoplayer.IVideoPlayerCtrlViewListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDVideoPlayerCtrlViewListener
        public void show() {
            k.this.f5163g.b();
        }
    }

    /* loaded from: classes15.dex */
    public class b implements Observer<PDVedioShareEntity> {
        public b() {
            k.this = r1;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable PDVedioShareEntity pDVedioShareEntity) {
            boolean z = k.this.B;
        }
    }

    /* loaded from: classes15.dex */
    public class c implements IVideoViewOnTouchListener {
        public c() {
            k.this = r1;
        }

        @Override // com.jingdong.common.unification.video.player.IVideoViewOnTouchListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDVideoViewOnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            m mVar;
            if (k.this.a == null) {
                return true;
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                k.this.H = false;
                k.this.s = motionEvent.getRawX();
                k.this.t = motionEvent.getRawY();
            } else if (actionMasked == 1) {
                k kVar = k.this;
                if (!kVar.H && (mVar = kVar.f5165i) != null) {
                    ((com.jd.lib.productdetail.mainimage.holder.video.j) mVar).a(kVar.a);
                }
            } else if (actionMasked == 2) {
                float rawX = motionEvent.getRawX() - k.this.s;
                float rawY = motionEvent.getRawY();
                k kVar2 = k.this;
                float f2 = rawY - kVar2.t;
                kVar2.s = motionEvent.getRawX();
                k.this.t = motionEvent.getRawY();
                if (Math.abs(rawX) > 8.0f || Math.abs(f2) > 8.0f) {
                    k.this.H = true;
                }
            }
            return false;
        }
    }

    /* loaded from: classes15.dex */
    public class d implements IVideoViewOnTouchListener {
        public d() {
            k.this = r1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:51:0x0015, code lost:
            if (r7 != 3) goto L79;
         */
        @Override // com.jingdong.common.unification.video.player.IVideoViewOnTouchListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDVideoViewOnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
            /*
                Method dump skipped, instructions count: 344
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.old.k.d.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }
    }

    /* loaded from: classes15.dex */
    public class e implements View.OnClickListener {
        public e() {
            k.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            VideoPlayView videoPlayView = k.this.a;
            if (videoPlayView != null) {
                if (!videoPlayView.isPlaying()) {
                    k.this.a.startPlay();
                }
                k.this.a.seekToPosition(intValue);
            }
            PdMainImagePresenter pdMainImagePresenter = k.this.z;
            if (pdMainImagePresenter != null) {
                pdMainImagePresenter.mtaClick("Productdetail_MainPicVideoTag");
            }
            int i2 = R.id.lib_pd_video_click_time;
            int intValue2 = ((Integer) view.getTag(i2)).intValue() + 1;
            view.setTag(i2, Integer.valueOf(intValue2));
            if (view instanceof RadioButton) {
                String charSequence = ((RadioButton) view).getText().toString();
                if (!TextUtils.isEmpty(charSequence)) {
                    k kVar = k.this;
                    if (kVar.f5161e == null) {
                        kVar.f5161e = new HashMap();
                    }
                    k.this.f5161e.put(charSequence, Integer.valueOf(intValue2));
                }
            }
            Map<String, Integer> map = k.this.f5161e;
            String jSONString = (map == null || map.isEmpty()) ? null : JDJSON.toJSONString(map);
            if (k.this.a != null && !TextUtils.isEmpty(jSONString)) {
                k.this.a.setMark(jSONString);
            }
            InterfaceC0159k interfaceC0159k = k.this.f5166j;
            if (interfaceC0159k != null) {
                interfaceC0159k.a(view);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class f implements IProgrssChangeListener {
        public f() {
            k.this = r1;
        }

        @Override // com.jingdong.common.unification.video.player.IProgrssChangeListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDProgressChangeListener
        public void onProgressChange(int i2, int i3) {
        }

        @Override // com.jingdong.common.unification.video.player.IProgrssChangeListener, tv.danmaku.ijk.media.widget.uniform.inter.IJDProgressChangeListener
        public void onProgressPointSelect(int i2) {
            SparseArray<RadioButton> sparseArray = k.this.f5160c;
            if (sparseArray != null && i2 >= 0 && i2 < sparseArray.size() && k.this.b != null) {
                SparseArray<RadioButton> sparseArray2 = k.this.f5160c;
                RadioButton radioButton = sparseArray2.get(sparseArray2.keyAt(i2));
                if (radioButton != null) {
                    radioButton.setChecked(true);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public class g extends AVideoMtaListener {
        public g() {
            k.this = r1;
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void changeToVideoTail() {
            super.changeToVideoTail();
            k.this.f5167k.b();
            k.this.E(false);
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void clickClose() {
            super.clickClose();
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void clickPauseOrPlay(boolean z) {
            super.clickPauseOrPlay(z);
            PdMainImagePresenter pdMainImagePresenter = k.this.z;
            if (pdMainImagePresenter == null || z) {
                return;
            }
            pdMainImagePresenter.mtaClick("Productdetail_VideoPause", "", "1");
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void clickScreen(boolean z) {
            super.clickScreen(z);
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void clickScreenClose() {
            super.clickScreenClose();
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void clickShare() {
            PdMainImagePresenter pdMainImagePresenter = k.this.z;
            if (pdMainImagePresenter != null) {
                pdMainImagePresenter.mtaClick("Productdetail_MainPicVideoShare");
            }
            super.clickShare();
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void clickVoice(boolean z) {
            super.clickVoice(z);
            k.this.F = z;
            ItemPlayerController.getController().setAbandonAudioFocus(z);
            k.this.f5167k.a(z);
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void doubleClick(boolean z) {
            super.doubleClick(z);
            n nVar = k.this.f5167k;
            if (nVar != null) {
                nVar.b(z);
            }
            PdMainImagePresenter pdMainImagePresenter = k.this.z;
            if (pdMainImagePresenter == null || z) {
                return;
            }
            pdMainImagePresenter.mtaClick("Productdetail_VideoPause", "", "2");
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void progressChangedFromUser(int i2) {
            super.progressChangedFromUser(i2);
        }

        @Override // com.jingdong.common.unification.video.player.AVideoMtaListener
        public void stopTrackingTouch() {
            super.stopTrackingTouch();
            k.this.f5167k.a();
            PdMainImagePresenter pdMainImagePresenter = k.this.z;
            if (pdMainImagePresenter != null) {
                pdMainImagePresenter.mtaClick("Productdetail_VideoJindu");
            }
        }
    }

    /* loaded from: classes15.dex */
    public class h extends AVideoPlayStateListener {

        /* loaded from: classes15.dex */
        public class a implements Runnable {
            public a() {
                h.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                k kVar = k.this;
                kVar.y(kVar.o);
            }
        }

        public h() {
            k.this = r1;
        }

        @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCompletion() {
            k.this.C = true;
            k.this.p = true;
            k.this.f5164h.onVideoFinish();
            k.this.f5168l = 0;
        }

        @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onCreatePlayer() {
            super.onCreatePlayer();
        }

        @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener
        public boolean onCustomCompletion() {
            return true;
        }

        @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onError(int i2, int i3) {
            o oVar = k.this.f5164h;
            if (oVar != null) {
                oVar.a();
            }
            return super.onError(i2, i3);
        }

        @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public boolean onInfo(int i2, int i3) {
            return super.onInfo(i2, i3);
        }

        @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onPrepared(long j2) {
            super.onPrepared(j2);
            if (PDManager.getInstances(k.this.A).getShareStatus("share_status_topvideo", false)) {
                return;
            }
            SyncEventBus.postToMainThread(new a());
        }

        @Override // com.jingdong.common.unification.video.player.AVideoPlayStateListener, tv.danmaku.ijk.media.example.widget.media.IPlayerControl.OnPlayerStateListener
        public void onSeekComplete() {
            super.onSeekComplete();
        }
    }

    /* loaded from: classes15.dex */
    public class i implements IViewPlayerControl {
        public i() {
            k.this = r1;
        }

        @Override // com.jingdong.common.videoplayer.IViewPlayerControl, tv.danmaku.ijk.media.widget.uniform.inter.IJDViewPlayerControl
        public void close() {
            k.this.f5162f.onClose();
        }

        @Override // com.jingdong.common.videoplayer.IViewPlayerControl, tv.danmaku.ijk.media.widget.uniform.inter.IJDViewPlayerControl
        public void dialogClose() {
        }
    }

    /* loaded from: classes15.dex */
    public class j extends AVideoViewBtClickListener {
        public j() {
            k.this = r1;
        }

        @Override // com.jingdong.common.unification.video.player.AVideoViewBtClickListener
        public boolean bigBackClick() {
            k.this.f5162f.b();
            return true;
        }

        @Override // com.jingdong.common.unification.video.player.AVideoViewBtClickListener
        public boolean clickFullScreen(boolean z) {
            k.this.f5162f.a(z);
            return true;
        }

        @Override // com.jingdong.common.unification.video.player.AVideoViewBtClickListener
        public void pauseOrPlayClick(boolean z) {
            k.this.o = z;
            k.this.f5162f.b(z);
            k.this.w(!z);
            super.pauseOrPlayClick(z);
        }

        @Override // com.jingdong.common.unification.video.player.AVideoViewBtClickListener
        public boolean smallCloseClick() {
            k.this.f5162f.a();
            return true;
        }
    }

    /* renamed from: com.jd.lib.productdetail.mainimage.old.k$k */
    /* loaded from: classes15.dex */
    public interface InterfaceC0159k {
        void a(View view);
    }

    /* loaded from: classes15.dex */
    public interface l {
        void a();

        void a(boolean z);

        void b();

        void b(boolean z);

        void onClose();
    }

    /* loaded from: classes15.dex */
    public interface m {
    }

    /* loaded from: classes15.dex */
    public interface n {
        void a();

        void a(boolean z);

        void b();

        void b(boolean z);
    }

    /* loaded from: classes15.dex */
    public interface o {
        void a();

        void a(int i2);

        void onVideoFinish();
    }

    /* loaded from: classes15.dex */
    public interface p {
        void a();

        void b();
    }

    public k(Context context, String str) {
        this.D = null;
        this.D = new WeakReference<>(context);
        this.A = str;
        v();
        if (context instanceof BaseActivity) {
            PDManager.getInstances(str).getVedioShareData().observe((BaseActivity) context, new b());
        }
    }

    public static k b(Context context, String str) {
        if (!S.isEmpty()) {
            R = S.get(str);
        }
        if (R == null) {
            synchronized (k.class) {
                if (R == null) {
                    R = new k(context, str);
                    S.put(str, R);
                }
            }
        }
        return R;
    }

    public void B(boolean z) {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView == null || this.o) {
            return;
        }
        if (z) {
            videoPlayView.onResume();
        } else {
            videoPlayView.startPlay();
        }
        w(true);
    }

    public boolean C() {
        VideoPlayView videoPlayView = this.a;
        return videoPlayView != null && videoPlayView.isPlaying();
    }

    public void E(boolean z) {
        SegmentRadioGroup segmentRadioGroup = this.b;
        if (segmentRadioGroup != null) {
            segmentRadioGroup.setVisibility(z ? 0 : 8);
        }
    }

    public boolean F() {
        VideoPlayView videoPlayView = this.a;
        return videoPlayView != null && videoPlayView.isPlayingTail();
    }

    public final void G() {
        WeakReference<ViewGroup> weakReference = this.E;
        if ((weakReference == null || weakReference.get() == null || this.E.get().findViewWithTag("topImageVideoTag") == null || this.a == null) ? false : true) {
            try {
                ViewGroup viewGroup = this.E.get();
                if (viewGroup != null) {
                    int childCount = viewGroup.getChildCount();
                    int indexOfChild = viewGroup.indexOfChild(this.a);
                    if (indexOfChild < 0 || indexOfChild >= childCount) {
                        return;
                    }
                    viewGroup.removeView(this.a);
                }
            } catch (IndexOutOfBoundsException e2) {
                PlatformTools.catchExceptionAndReportToBugly(e2);
            } catch (NullPointerException e3) {
                PlatformTools.catchExceptionAndReportToBugly(e3);
            }
        }
    }

    public void H(boolean z) {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView != null) {
            videoPlayView.setShowBottomVoice(z, this.F);
            this.a.setShowVoice(z, this.F);
        }
    }

    public void I() {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView == null || this.o) {
            return;
        }
        videoPlayView.startPlay();
        this.p = false;
        w(true);
    }

    public void J() {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView != null) {
            videoPlayView.hideBottomBar(false);
            this.a.setShowBottomProgressBar(true);
            this.a.setKeepBottomProgressBarVisi(false);
            this.a.setBottomProgressBarVisible(false);
        }
    }

    public void K() {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView != null) {
            videoPlayView.hide(false);
            this.a.setBottomProgressBarVisible(true);
            this.a.setShowBottomProgressBar(true);
            this.a.setKeepBottomProgressBarVisi(false);
        }
    }

    public int a(boolean z) {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView == null) {
            return 0;
        }
        return z ? videoPlayView.getNormalVideoDuration() : videoPlayView.getDuration();
    }

    public void c() {
        if (this.a != null) {
            this.f5170n = false;
            this.o = false;
            w(false);
            G();
            Map<String, Integer> map = this.f5161e;
            if (map != null) {
                map.clear();
                this.f5161e = null;
            }
            this.a.onDestroy();
            this.a = null;
            this.f5168l = 0;
            SparseArray<RadioButton> sparseArray = this.f5160c;
            if (sparseArray != null) {
                sparseArray.clear();
                this.f5160c = null;
            }
            List<Integer> list = this.d;
            if (list != null) {
                list.clear();
                this.d = null;
            }
            this.b = null;
        }
    }

    public final void d(int i2) {
        this.f5169m = this.f5168l;
        this.f5168l = i2;
        PDVedioShareEntity value = PDManager.getInstances(this.A).getVedioShareData().getValue();
        if (value != null) {
            value.isDefaultStyle = this.f5168l == 1;
            PDManager.getInstances(this.A).getVedioShareData().postValue(value);
        }
    }

    public void e(ViewGroup viewGroup) {
        if (this.B || this.a == null || viewGroup == null || this.f5168l == 3 || this.C) {
            return;
        }
        this.a.setVideoViewOnTouchListener(null);
        this.a.setBottomSharedEnable(false);
        E(true);
        this.a.changeToScreen(2);
        this.a.hideCloseBt(true);
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.a.setLayoutParams(layoutParams);
        this.a.setTranslationX(0.0f);
        this.a.setTranslationY(0.0f);
        this.a.setShowVoice(true, this.F);
        this.a.getBarPlayerView().setVisibility(0);
        this.a.hideFullscreen(true);
        d(3);
        o oVar = this.f5164h;
        if (oVar != null) {
            oVar.a(this.f5168l);
        }
        K();
        f(viewGroup, -1);
    }

    public synchronized void f(ViewGroup viewGroup, int i2) {
        try {
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        synchronized (this) {
            if (Log.D) {
                Log.d("BusinessVidePlayerComponent", "changeConstainer");
            }
            G();
            if (viewGroup != null) {
                WeakReference<ViewGroup> weakReference = this.E;
                if (weakReference != null && weakReference.get() != null) {
                    this.E.clear();
                    WeakReference<ViewGroup> weakReference2 = new WeakReference<>(viewGroup);
                    this.E = weakReference2;
                    o(weakReference2, i2);
                }
                WeakReference<ViewGroup> weakReference3 = new WeakReference<>(viewGroup);
                this.E = weakReference3;
                o(weakReference3, i2);
            }
        }
    }

    public void g(ViewGroup viewGroup, boolean z) {
        if (this.B || this.a == null || viewGroup == null || this.f5168l == 2 || this.C) {
            return;
        }
        if (this.a.getVideoState() == 333 || this.a.getVideoState() == 334 || this.a.isPlaying() || z) {
            G();
            this.a.setShowBottomProgressBar(false);
            this.a.setBottomSharedEnable(false);
            E(false);
            this.a.changeToScreen(1);
            this.a.hideCloseBt(true);
            f(viewGroup, -1);
            VideoPlayView videoPlayView = this.a;
            if (videoPlayView != null) {
                int videoWidth = videoPlayView.getVideoWidth();
                int videoHeight = this.a.getVideoHeight();
                float f2 = videoWidth / videoHeight;
                if (videoWidth > videoHeight) {
                    int i2 = Q;
                    this.r = i2;
                    this.q = (int) (i2 * f2);
                } else if (videoWidth < videoHeight) {
                    int i3 = P;
                    this.q = i3;
                    this.r = (int) (i3 / f2);
                }
            }
            ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
            layoutParams.width = this.q;
            layoutParams.height = this.r;
            this.a.setLayoutParams(layoutParams);
            Context context = this.D.get();
            if (context instanceof BaseActivity) {
                BaseActivity baseActivity = (BaseActivity) context;
                this.x = (DPIUtil.getAppWidth(baseActivity) - this.q) - PDUtils.dip2px(10.0f);
                this.y = (DPIUtil.getAppWidth(baseActivity) - this.q) / 2;
                this.v = PDUtils.getHeight() - ((this.r + UnStatusBarTintUtil.getNavigationBarHeight(baseActivity)) + PDUtils.dip2px(60.0f));
            }
            this.a.setTranslationX(this.x);
            this.a.setTranslationY(this.u);
            this.a.setVideoViewOnTouchListener(this.M);
            d(2);
            o oVar = this.f5164h;
            if (oVar != null) {
                oVar.a(this.f5168l);
            }
        }
    }

    public void h(WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo, boolean z) {
        if (wareBusinessMaterVideoInfo == null) {
            return;
        }
        if (this.a == null) {
            t(z);
            if (this.a == null) {
                return;
            }
        }
        if (TextUtils.isEmpty(wareBusinessMaterVideoInfo.playUrl)) {
            return;
        }
        if (wareBusinessMaterVideoInfo.isHasExtInfo()) {
            this.a.setTailCoverUrl(wareBusinessMaterVideoInfo.extInfo.trailerImgUrl).setTailSource(wareBusinessMaterVideoInfo.extInfo.trailerPlayUrl);
        }
        this.G = wareBusinessMaterVideoInfo.videoId;
        this.a.setCoverUrl(wareBusinessMaterVideoInfo.imageUrl).setFlowToastAlert(true).setVideoId(wareBusinessMaterVideoInfo.videoId).setJumpFrom(3).setFullScreenBtResource(com.jingdong.common.R.drawable.vd_enlarge_video, com.jingdong.common.R.drawable.vd_shrink_video).setPlaySource(wareBusinessMaterVideoInfo.playUrl);
        this.f5170n = true;
        w(true);
        this.p = false;
    }

    public void i(WareBusinessTopVideoControl wareBusinessTopVideoControl, k kVar, boolean z, String str, String str2, String str3, View view) {
        int normalVideoDuration;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo;
        List<PdVideoSymbolList> list;
        if (kVar == null) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (z) {
            normalVideoDuration = kVar.a(kVar.F());
        } else {
            boolean F = kVar.F();
            VideoPlayView videoPlayView = kVar.a;
            if (videoPlayView == null) {
                normalVideoDuration = 0;
            } else {
                normalVideoDuration = F ? videoPlayView.getNormalVideoDuration() : videoPlayView.getCurrentPosition();
            }
        }
        jDJSONObject.put("ts", (Object) Integer.valueOf(normalVideoDuration));
        jDJSONObject.put("tssum", (Object) Integer.valueOf(kVar.a(kVar.F())));
        jDJSONObject.put("videoid", (Object) this.G);
        if (wareBusinessTopVideoControl != null && (wareBusinessMaterVideoInfo = wareBusinessTopVideoControl.masterVideo) != null && (list = wareBusinessMaterVideoInfo.videoSymbolList) != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i2 = 0; i2 < wareBusinessTopVideoControl.masterVideo.videoSymbolList.size(); i2++) {
                PdVideoSymbolList pdVideoSymbolList = wareBusinessTopVideoControl.masterVideo.videoSymbolList.get(i2);
                if (pdVideoSymbolList != null) {
                    sb.append(pdVideoSymbolList.symbolId);
                    sb2.append(pdVideoSymbolList.name);
                    if (i2 != wareBusinessTopVideoControl.masterVideo.videoSymbolList.size() - 1) {
                        sb.append(CartConstant.KEY_YB_INFO_LINK);
                        sb2.append(CartConstant.KEY_YB_INFO_LINK);
                    }
                }
            }
            jDJSONObject.put("lableid", (Object) sb);
            jDJSONObject.put("lablename", (Object) sb2);
        }
        PdMainImagePresenter pdMainImagePresenter = this.z;
        if (pdMainImagePresenter != null && pdMainImagePresenter.getMainImageParams() != null) {
            PDUtils.setFloorPriceJson(jDJSONObject, this.z.getMainImageParams().floorPriceMta);
            PDUtils.setCardInfo(jDJSONObject, this.z.getMainImageParams().brandId, "bpMainImage", view);
        }
        PDUtils.setFloorCid(jDJSONObject, str, str2, str3);
        PdMainImagePresenter pdMainImagePresenter2 = this.z;
        if (pdMainImagePresenter2 != null) {
            pdMainImagePresenter2.mtaClick("Productdetail_MainPicVideoExitNew", jDJSONObject.toString(), true);
        }
    }

    public void j(l lVar) {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView != null) {
            this.f5162f = lVar;
            videoPlayView.setiViewPlayerControl(new i());
            this.a.setVideoViewBtClickListener(new j());
        }
    }

    public void k(n nVar) {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView != null) {
            this.f5167k = nVar;
            videoPlayView.setMtaListener(new g());
        }
    }

    public void l(o oVar) {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView != null) {
            this.f5164h = oVar;
            videoPlayView.setOnPlayerStateListener(new h());
        }
    }

    public void m(p pVar) {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView != null) {
            this.f5163g = pVar;
            videoPlayView.setCtrlViewListener(new a());
        }
    }

    public void n(String str) {
        k remove;
        Map<String, k> map = S;
        if (map == null || (remove = map.remove(str)) == null) {
            return;
        }
        remove.B = true;
        remove.w(false);
        if (remove.a != null) {
            remove.f5170n = false;
            Map<String, Integer> map2 = remove.f5161e;
            if (map2 != null) {
                map2.clear();
                remove.f5161e = null;
            }
            remove.a.onDestroy();
            remove.a = null;
            remove.f5168l = 0;
            SparseArray<RadioButton> sparseArray = remove.f5160c;
            if (sparseArray != null) {
                sparseArray.clear();
                remove.f5160c = null;
            }
            List<Integer> list = remove.d;
            if (list != null) {
                list.clear();
                remove.d = null;
            }
            remove.b = null;
        }
        if (R != null) {
            R = null;
        }
    }

    public final void o(WeakReference<ViewGroup> weakReference, int i2) {
        ViewGroup viewGroup = weakReference.get();
        if (viewGroup != null) {
            viewGroup.setDescendantFocusability(393216);
            if (i2 != -1) {
                viewGroup.addView(this.a, i2);
            } else {
                viewGroup.addView(this.a);
            }
        }
    }

    @SuppressLint({"InflateParams"})
    public void p(List<WareBusinessMasterVideoMarkInfo> list) {
        int i2;
        if (this.a == null || list == null || list.isEmpty()) {
            return;
        }
        if (this.b == null) {
            SparseArray<RadioButton> sparseArray = this.f5160c;
            if (sparseArray == null) {
                this.f5160c = new SparseArray<>();
            } else {
                sparseArray.clear();
            }
            List<Integer> list2 = this.d;
            if (list2 == null) {
                this.d = new ArrayList();
            } else {
                list2.clear();
            }
            Map<String, Integer> map = this.f5161e;
            if (map != null) {
                map.clear();
                this.f5161e = null;
            }
            Context context = this.D.get();
            if (context != null) {
                this.b = (SegmentRadioGroup) LayoutInflater.from(context).inflate(R.layout.lib_pd_mainimage_topvideo_segment, (ViewGroup) null);
                for (int i3 = 0; i3 < list.size(); i3++) {
                    WareBusinessMasterVideoMarkInfo wareBusinessMasterVideoMarkInfo = list.get(i3);
                    if (wareBusinessMasterVideoMarkInfo != null && !TextUtils.isEmpty(wareBusinessMasterVideoMarkInfo.mark) && (i2 = wareBusinessMasterVideoMarkInfo.startTime) >= 0) {
                        int i4 = i2 * 1000;
                        this.d.add(Integer.valueOf(i4));
                        RadioButton radioButton = (RadioButton) LayoutInflater.from(context).inflate(R.layout.lib_pd_mainimage_topvideo_segment_item, (ViewGroup) null);
                        radioButton.setText(wareBusinessMasterVideoMarkInfo.mark);
                        radioButton.setTag(Integer.valueOf(i4));
                        radioButton.setTag(R.id.lib_pd_video_click_time, 0);
                        radioButton.setOnClickListener(this.N);
                        this.f5160c.put(i3, radioButton);
                        this.b.addView(radioButton);
                    }
                }
            }
        }
        this.a.setPointPositions(this.d);
        this.a.setPointView(this.b);
        this.a.hide(true);
    }

    public void q(boolean z, boolean z2) {
        this.F = z2;
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView != null) {
            videoPlayView.setShowBottomVoice(z, z2);
            this.a.setShowVoice(z, z2);
        }
    }

    public int s() {
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView == null) {
            return -1;
        }
        try {
            Field declaredField = videoPlayView.getClass().getDeclaredField("currentTipState");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this.a);
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
        return -1;
    }

    public void t(boolean z) {
        if (this.a == null) {
            PDManager.getInstances(this.A).getVedioShareData().getValue().reset();
            this.C = false;
            Context context = this.D.get();
            if (context != null) {
                if (z) {
                    IPlayerControl.PlayerOptions playerOptions = new IPlayerControl.PlayerOptions(false);
                    playerOptions.setPlayTypeId("133");
                    playerOptions.setUseCache(true);
                    this.a = new VideoPlayView(context, playerOptions);
                } else {
                    this.a = new VideoPlayView(context);
                }
                this.a.setTag("topImageVideoTag");
                this.a.setFocusable(true);
                this.a.setFocusableInTouchMode(true);
                this.a.setProgrssChangeListener(this.O);
                this.a.hideCloseBt(true);
                PdMainImagePresenter pdMainImagePresenter = this.z;
                if (pdMainImagePresenter != null) {
                    this.a.setSku(pdMainImagePresenter.getMainImageParams().skuId);
                }
            }
        }
    }

    public final void v() {
        Context context = this.D.get();
        if (context != null && (context instanceof BaseActivity)) {
            this.u = UnStatusBarTintUtil.getStatusBarHeight((Activity) ((BaseActivity) context)) + PDUtils.dip2px(60.0f);
        }
        this.w = PDUtils.dip2px(10.0f);
    }

    public final void w(boolean z) {
        VideoPlayView videoPlayView;
        if (TextUtils.isEmpty(this.A)) {
            return;
        }
        PDManager.getInstances(this.A).shareAnyStatus("share_status_topvideo", z);
        if (z) {
            this.p = false;
        }
        if (this.B) {
            return;
        }
        boolean z2 = z || (this.f5170n && (videoPlayView = this.a) != null && videoPlayView.getVideoState() == 335);
        PDVedioShareEntity value = PDManager.getInstances(this.A).getVedioShareData().getValue();
        if (value != null) {
            value.isDefaultStyle = this.f5168l == 1;
            value.isPlay = z2;
            PDManager.getInstances(this.A).getVedioShareData().postValue(value);
        }
    }

    public void y(boolean z) {
        this.o = z;
        VideoPlayView videoPlayView = this.a;
        if (videoPlayView != null) {
            videoPlayView.pausePlay();
            w(false);
        }
    }

    public boolean z() {
        VideoPlayView videoPlayView = this.a;
        return videoPlayView != null && this.f5168l == 1 && videoPlayView.getVisibility() == 0;
    }
}
