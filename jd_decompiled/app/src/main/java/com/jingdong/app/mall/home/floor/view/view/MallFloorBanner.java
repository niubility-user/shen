package com.jingdong.app.mall.home.floor.view.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.LoopViewPager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.a;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.ctrl.d;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.i;
import com.jingdong.app.mall.home.floor.ctrl.p;
import com.jingdong.app.mall.home.floor.model.entity.BannerFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.BannerFloorEngine;
import com.jingdong.app.mall.home.floor.view.AnimationLinerPagerCursor;
import com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureImagePagerWithEndLoadingAdapter;
import com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureLayoutPagerAdapter;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.baseui.IMallBannerFloorUI;
import com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor;
import com.jingdong.app.mall.home.r.a.b;
import com.jingdong.app.mall.home.r.a.c;
import com.jingdong.app.mall.home.r.c.c;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.f;
import com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter;
import com.jingdong.app.mall.utils.ui.view.CarouselFigureViewPager2;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class MallFloorBanner extends BaseMallFloor<f> implements IMallBannerFloorUI, ViewPager.OnPageChangeListener, View.OnTouchListener, b, GestureDetector.OnGestureListener {
    private static final long LEFTANIMDURATIONDEFAULT = 600;
    private static final long RIGHTANIMDURATIONDEFAULT = 800;
    private static final long STARTDELAYDEFAULT = 500;
    private static final String TAG = "MallFloor_Banner";
    private static int sCurrentBannerFrameIndex;
    private static long sCurrentBannerSelectedTime;
    private PagerAdapter adapter;
    private ObjectAnimator anim1;
    private ObjectAnimator anim2;
    private AnimatorSet animSet;
    private SimpleDraweeView animView1;
    private SimpleDraweeView animView2;
    private boolean[] exposalUrlRecords;
    private Handler handler;
    private boolean initialExposalUrlRecord;
    private boolean isImg2Ready;
    private boolean isImg3Ready;
    private boolean isPause;
    private boolean isPreScrollStopVisible;
    private long leftAnimDuration;
    private PagerAdapter mAdapterBeforeDestroy;
    private Animator.AnimatorListener mAnimatorListener;
    private i mCursorContentViewCtrl;
    private GestureDetector mGestureDetector;
    private boolean mIsSlideDirectionLeft;
    private boolean mIsTonglangAnimAllLoaded;
    private int mSelectedIndexBeforeDestroy;
    private int mSlidePageCount;
    private AtomicBoolean mStartGestureSlide;
    private int mStartSlidePos;
    private int mTonglanAnimPriority;
    private int midLinePosition;
    private p newCarouselFigureViewCtrl;
    private CarouselFigureViewPager2 pager;
    private long rightAnimDuration;
    private long startDelay;
    private long token;
    private int widthAnimView1;
    private int widthAnimView2;

    /* loaded from: classes4.dex */
    public class ViewPagerScroller extends Scroller {
        private int mScrollDuration;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewPagerScroller(Context context) {
            super(context);
            MallFloorBanner.this = r1;
            this.mScrollDuration = 2000;
        }

        public void initViewPagerScroll(LoopViewPager loopViewPager) {
            try {
                Field declaredField = LoopViewPager.class.getDeclaredField("mScroller");
                declaredField.setAccessible(true);
                declaredField.set(loopViewPager, this);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void setScrollDuration(int i2) {
            this.mScrollDuration = i2;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i2, int i3, int i4, int i5, int i6) {
            startScroll(i2, i3, i4, i5);
        }

        @Override // android.widget.Scroller
        public void startScroll(int i2, int i3, int i4, int i5) {
            super.startScroll(i2, i3, i4, i5, this.mScrollDuration);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
            MallFloorBanner.this = r1;
            this.mScrollDuration = 2000;
        }
    }

    public MallFloorBanner(Context context) {
        super(context);
        this.adapter = null;
        this.mAdapterBeforeDestroy = null;
        this.mSelectedIndexBeforeDestroy = 0;
        this.mStartSlidePos = 0;
        this.mSlidePageCount = 0;
        this.mIsSlideDirectionLeft = true;
        this.mGestureDetector = null;
        this.mStartGestureSlide = new AtomicBoolean(false);
        this.leftAnimDuration = LEFTANIMDURATIONDEFAULT;
        this.rightAnimDuration = RIGHTANIMDURATIONDEFAULT;
        this.startDelay = 500L;
        this.mCursorContentViewCtrl = new d();
        this.token = -1L;
        this.initialExposalUrlRecord = false;
        this.isPause = true;
        this.isPreScrollStopVisible = false;
        this.mAnimatorListener = null;
        this.mTonglanAnimPriority = 1;
        this.mIsTonglangAnimAllLoaded = false;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner.1
            {
                MallFloorBanner.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (!MallFloorBanner.this.isPause && MallFloorBanner.this.pager != null && MallFloorBanner.this.pager.getChildCount() > 1 && MallFloorBanner.this.pager.getAdapter() != null && MallFloorBanner.this.pager.getAdapter().getCount() >= 2) {
                    try {
                        if (MallFloorBanner.this.token - ((Long) message.obj).longValue() != 0) {
                            return;
                        }
                        MallFloorBanner.this.pager.next();
                    } catch (Exception unused) {
                    }
                }
            }
        };
    }

    private synchronized void addEntryAnimationToCtrl() {
        if (((f) this.mPresenter).e0()) {
            if (!a.u.y(((f) this.mPresenter).Z()) || !a.u.r(this)) {
                if (Log.D) {
                    Log.d(TAG, "EntryAnim-addEntryAnimationToCtrl:" + ((f) this.mPresenter).i());
                }
                setAnimationToEnd();
            }
            this.mIsTonglangAnimAllLoaded = true;
            a.u.h(this);
        }
    }

    private void addSlideMta(boolean z) {
        if (this.pager != null && this.mStartGestureSlide.compareAndSet(true, false)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mStartSlidePos);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(z ? "0" : "1");
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(String.valueOf(this.mSlidePageCount));
            JDMtaUtils.onClickWithPageId(getContext(), ((f) this.mPresenter).a0(), com.jingdong.app.mall.home.r.c.a.f10642k, sb.toString(), RecommendMtaUtils.Home_PageId);
        }
    }

    private synchronized void autoChangeViewPagerPosition(int i2) {
        if (this.pager != null && ((f) this.mPresenter).f0()) {
            this.token = System.currentTimeMillis();
            Message obtain = Message.obtain();
            obtain.what = this.pager.getCurrentItem();
            obtain.obj = Long.valueOf(this.token);
            this.handler.removeCallbacksAndMessages(null);
            this.handler.sendMessageDelayed(obtain, i2);
        }
    }

    public static int getCurrentBannerFrameIndex() {
        return sCurrentBannerFrameIndex;
    }

    public static long getCurrentBannerSelectedTime() {
        if (sCurrentBannerSelectedTime <= 0) {
            sCurrentBannerSelectedTime = SystemClock.elapsedRealtime();
        }
        return sCurrentBannerSelectedTime;
    }

    private boolean isEntryAnimMode() {
        if (((f) this.mPresenter).e0()) {
            if (!((f) this.mPresenter).f0()) {
                return true;
            }
            CarouselFigureViewPager2 carouselFigureViewPager2 = this.pager;
            if (carouselFigureViewPager2 != null && carouselFigureViewPager2.getAdapter() != null && this.pager.getAdapter().getCount() == 1) {
                return true;
            }
        }
        return false;
    }

    public void loadEntryImageSuccess(boolean z) {
        if (z) {
            this.isImg2Ready = true;
        } else {
            this.isImg3Ready = true;
        }
        if (z) {
            if (!this.isImg3Ready) {
                return;
            }
        } else if (!this.isImg2Ready) {
            return;
        }
        addEntryAnimationToCtrl();
    }

    private void loadEntryImageWithUrlOnMainThread(SimpleDraweeView simpleDraweeView, String str, final boolean z) {
        Object tag;
        if (simpleDraweeView != null && (tag = simpleDraweeView.getTag(e.d)) != null && (tag instanceof String) && ((String) tag).equals(str) && simpleDraweeView.getTag(JDImageUtils.STATUS_TAG).equals(2)) {
            loadEntryImageSuccess(z);
        } else {
            e.k(str, simpleDraweeView, new JDSimpleImageLoadingListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner.8
                {
                    MallFloorBanner.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                    super.onLoadingComplete(str2, view, bitmap);
                    view.setTag(e.d, str2);
                    MallFloorBanner.this.loadEntryImageSuccess(z);
                }
            });
        }
    }

    public void loadImageWithUrl() {
        if (isMainThread()) {
            loadImageWithUrlOnMainThread();
        } else {
            postToMainThread("loadImageWithUrlOnMainThread", null, new Object[0]);
        }
    }

    private void loadImageWithUrlOnMainThread() {
        this.isImg2Ready = false;
        this.isImg3Ready = false;
        String X = ((f) this.mPresenter).X();
        String Y = ((f) this.mPresenter).Y();
        loadEntryImageWithUrlOnMainThread(this.animView1, X, true);
        loadEntryImageWithUrlOnMainThread(this.animView2, Y, false);
    }

    private void postExpoSalUrl(int i2) {
        if (((f) this.mPresenter).g0()) {
            if (isCache() && i2 == 0) {
                return;
            }
            if (!this.initialExposalUrlRecord) {
                if (this.exposalUrlRecords != null) {
                    this.exposalUrlRecords = null;
                }
                this.exposalUrlRecords = new boolean[((f) this.mPresenter).W()];
                this.initialExposalUrlRecord = true;
                if (Log.D) {
                    Log.d("HHH", "exposal url, reset.");
                }
            }
            boolean[] zArr = this.exposalUrlRecords;
            if (zArr == null) {
                return;
            }
            try {
                if (zArr.length > i2 && !zArr[i2]) {
                    String V = ((f) this.mPresenter).V(i2);
                    String U = ((f) this.mPresenter).U(i2);
                    final String str = i2 + "";
                    P p = this.mPresenter;
                    float t0 = ((f) p).t0(((f) p).R(), V, 0L);
                    com.jingdong.app.mall.home.r.c.a.y("Home_FPicAdsRequest", "", ((f) this.mPresenter).R().toString());
                    if (!TextUtils.isEmpty(U)) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("extension_id", U);
                        com.jingdong.app.mall.home.r.c.a.B("Home_FocusPicAD_Expo", "", ((f) this.mPresenter).R().toString(), RecommendMtaUtils.Home_PageId, "", hashMap);
                    }
                    if (!TextUtils.isEmpty(V)) {
                        String g2 = com.jingdong.app.mall.home.o.a.f.g((f) this.mPresenter, t0, V);
                        postUrl(g2, new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner.5
                            {
                                MallFloorBanner.this = this;
                            }

                            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                            public void onEnd(HttpResponse httpResponse) {
                                com.jingdong.app.mall.home.r.c.a.y("Home_FPicAdsRequestSuccess", str, "");
                            }

                            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                            public void onError(HttpError httpError) {
                                Throwable exception = httpError.getException();
                                StringBuilder sb = new StringBuilder();
                                sb.append(str.concat(CartConstant.KEY_YB_INFO_LINK));
                                sb.append(httpError.getErrorCode());
                                sb.append(CartConstant.KEY_YB_INFO_LINK);
                                sb.append(exception == null ? "" : exception.getMessage());
                                com.jingdong.app.mall.home.r.c.a.y("Home_FPicAdsRequestFail", sb.toString(), "");
                            }

                            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                            public void onProgress(int i3, int i4) {
                            }

                            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                            public void onStart() {
                            }
                        });
                        if (Log.D) {
                            Log.d("HHH", "exposal url, position:" + i2 + ", post: " + g2);
                        }
                    }
                    this.exposalUrlRecords[i2] = true;
                    ((f) this.mPresenter).m0(i2);
                }
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private synchronized void reportExpoUrl(f fVar, int i2) {
        if (!((f) this.mPresenter).j0() && !fVar.g0()) {
            postUrl(fVar.V(i2));
            ((f) this.mPresenter).s0(true);
            fVar.m0(i2);
        }
    }

    public void setAnimationToEnd() {
        if (isMainThread()) {
            setAnimationToEndOnMainThread();
        } else {
            postToMainThread("setAnimationToEndOnMainThread", null, new Object[0]);
        }
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.mAnimatorListener = animatorListener;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallBannerFloorUI
    public void addFloorMaiDianData(String str, String str2, String str3) {
        c cVar = new c(str2, "", com.jingdong.app.mall.home.r.c.a.f10642k);
        cVar.c(str3);
        com.jingdong.app.mall.home.r.c.a.i().b(str, cVar);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void afterInitData(h hVar, @NotNull com.jingdong.app.mall.home.r.e.d dVar) {
        super.afterInitData(hVar, dVar);
        try {
            if (((f) this.mPresenter).g0()) {
                if (this.newCarouselFigureViewCtrl == null) {
                    this.newCarouselFigureViewCtrl = new p();
                }
                this.newCarouselFigureViewCtrl.f(this, (f) this.mPresenter);
                return;
            }
            com.jingdong.app.mall.home.o.a.f.u0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner.2
                {
                    MallFloorBanner.this = this;
                }

                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    MallFloorBanner mallFloorBanner = MallFloorBanner.this;
                    mallFloorBanner.reportExpoUrlAfterRefresh((f) ((BaseMallColorFloor) mallFloorBanner).mPresenter, 0);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void autoPullClick() {
        CarouselFigureViewPager2 carouselFigureViewPager2;
        if (this.mCursorContentViewCtrl == null || (carouselFigureViewPager2 = this.pager) == null) {
            return;
        }
        PagerAdapter adapter = carouselFigureViewPager2.getAdapter();
        if (adapter instanceof CarouseFigureLayoutPagerAdapter) {
            ((CarouseFigureLayoutPagerAdapter) adapter).i(this.mCursorContentViewCtrl.a());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void beforeInitData(h hVar, com.jingdong.app.mall.home.r.e.d dVar, boolean z) {
        super.beforeInitData(hVar, dVar, z);
        this.mGestureDetector = new GestureDetector(getContext(), this);
        ((f) this.mPresenter).r0(dVar.q == t.CAROUSELFIGURE_BANNER);
    }

    public void checkAndAddMaiDian(int i2) {
        if (((f) this.mPresenter).l0() && !JDHomeFragment.O0()) {
            if (Log.D) {
                Log.i(TAG, "\u9996\u9875\u4e0d\u53ef\u89c1\u547d\u4e2d103\u5b9e\u9a8c" + i2);
            }
        } else if (isFloorDisplay()) {
            if (Log.D) {
                Log.i(TAG, "checkAndAddMaiDian-" + i2 + ":" + getFloorId());
            }
            ((f) this.mPresenter).P(i2);
            postExpoSalUrl(i2);
            this.isPreScrollStopVisible = true;
        } else {
            this.isPreScrollStopVisible = false;
        }
    }

    public void checkAutoAnimate() {
        boolean isInDisplayArea = isInDisplayArea(a.f8560i, a.f8562k);
        if (isInDisplayArea != this.isPause) {
            return;
        }
        if (isInDisplayArea) {
            onResume();
        } else {
            onPause();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallBannerFloorUI
    public void clearEntryAnim() {
        if (isMainThread()) {
            clearEntryAnimOnMainThread();
        } else {
            postToMainThread("clearEntryAnimOnMainThread", null, new Object[0]);
        }
    }

    protected void clearEntryAnimOnMainThread() {
        if (Log.D) {
            Log.d(TAG, "EntryAnim-clearEntryAnimOnMainThread:" + ((f) this.mPresenter).i());
        }
        SimpleDraweeView simpleDraweeView = this.animView1;
        if (simpleDraweeView != null && simpleDraweeView.getParent() != null) {
            removeView(this.animView1);
            this.animView1 = null;
        }
        SimpleDraweeView simpleDraweeView2 = this.animView2;
        if (simpleDraweeView2 == null || simpleDraweeView2.getParent() == null) {
            return;
        }
        removeView(this.animView2);
        this.animView2 = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (Log.D) {
            Log.i(TAG, "expandXView-banner dispatch event:" + motionEvent.getAction());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void endAnim() {
        if (isMainThread()) {
            endAnimOnMainThread();
        } else {
            postToMainThread("endAnimOnMainThread", null, new Object[0]);
        }
    }

    public void endAnimOnMainThread() {
        if (this.animSet != null) {
            if (Log.D) {
                Log.d(TAG, "EntryAnim-endAnim");
            }
            this.animSet.end();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void floorDisplayInScreen(boolean z) {
        super.floorDisplayInScreen(z);
        CarouselFigureViewPager2 carouselFigureViewPager2 = this.pager;
        if (carouselFigureViewPager2 == null || !(carouselFigureViewPager2.getAdapter() instanceof CarouseFigureLayoutPagerAdapter)) {
            return;
        }
        ((CarouseFigureLayoutPagerAdapter) this.pager.getAdapter()).h(z);
    }

    public int getCurrentPos() {
        CarouselFigureViewPager2 carouselFigureViewPager2 = this.pager;
        if (carouselFigureViewPager2 == null) {
            return 0;
        }
        return carouselFigureViewPager2.getCurrentItem();
    }

    public float getDisplayRatio() {
        float height = getHeight();
        if (height > 0.0f && getVisibility() == 0) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            float j2 = a.j() + a.f8561j;
            float f2 = a.f8562k;
            float f3 = iArr[1];
            float f4 = f3 + height;
            if (f4 >= j2 && f3 <= f2) {
                float f5 = f3 < j2 ? (f4 - j2) / height : 1.0f;
                if (f4 > f2) {
                    f5 = ((f2 + height) - f4) / height;
                }
                return Math.max(0.0f, f5);
            }
        }
        return 0.0f;
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public String getModelId() {
        return ((f) this.mPresenter).Z();
    }

    public p getNewCarouselFigureViewCtrl() {
        return this.newCarouselFigureViewCtrl;
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public int getPriority() {
        CarouselFigureViewPager2 carouselFigureViewPager2;
        boolean z = true;
        if (!((f) this.mPresenter).e0() || (((f) this.mPresenter).f0() && ((carouselFigureViewPager2 = this.pager) == null || carouselFigureViewPager2.getAdapter() == null || this.pager.getAdapter().getCount() != 1))) {
            z = false;
        }
        if (z) {
            return this.mTonglanAnimPriority;
        }
        return 4;
    }

    @Override // com.jingdong.app.mall.home.r.a.b
    public int getSubPriority() {
        return 5;
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public c.e getType() {
        if (isEntryAnimMode()) {
            if (Log.D) {
                Log.d(TAG, "EntryAnim-getType():Bereplaced");
            }
            return c.e.Bereplaced;
        }
        return c.e.Other;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallBannerFloorUI
    public void initAnimView(final boolean z) {
        String Z = ((f) this.mPresenter).Z();
        final int p = ((f) this.mPresenter).p();
        this.midLinePosition = getLeft() + (p >> 1);
        this.widthAnimView1 = com.jingdong.app.mall.home.floor.common.d.d(333);
        this.widthAnimView2 = com.jingdong.app.mall.home.floor.common.d.d(333);
        if (a.u.y(Z)) {
            if (Log.D) {
                Log.d(TAG, "EntryAnim-isTonglanInited:" + ((f) this.mPresenter).i());
            }
            setAnimationToEnd();
            loadImageWithUrl();
            return;
        }
        if (Log.D) {
            Log.d(TAG, "initAnimView  needAnim:" + z);
        }
        a.u.K(Z, getFloorPos());
        com.jingdong.app.mall.home.o.a.f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner.6
            {
                MallFloorBanner.this = this;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                if (MallFloorBanner.this.animView1 == null) {
                    if (Log.D) {
                        Log.d(MallFloorBanner.TAG, "EntryAnim-before setAnimationToEnd-new animView1:" + ((f) ((BaseMallColorFloor) MallFloorBanner.this).mPresenter).i());
                    }
                    MallFloorBanner.this.animView1 = new HomeDraweeView(MallFloorBanner.this.getContext());
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(MallFloorBanner.this.widthAnimView1, -1);
                    layoutParams.addRule(15, -1);
                    MallFloorBanner.this.animView1.setLayoutParams(layoutParams);
                    MallFloorBanner.this.animView1.setScaleType(ImageView.ScaleType.FIT_XY);
                    MallFloorBanner mallFloorBanner = MallFloorBanner.this;
                    mallFloorBanner.addView(mallFloorBanner.animView1);
                }
                if (MallFloorBanner.this.animView2 == null) {
                    MallFloorBanner.this.animView2 = new HomeDraweeView(MallFloorBanner.this.getContext());
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(MallFloorBanner.this.widthAnimView2, -1);
                    layoutParams2.addRule(15, -1);
                    MallFloorBanner.this.animView2.setLayoutParams(layoutParams2);
                    MallFloorBanner.this.animView2.setScaleType(ImageView.ScaleType.FIT_XY);
                    MallFloorBanner mallFloorBanner2 = MallFloorBanner.this;
                    mallFloorBanner2.addView(mallFloorBanner2.animView2);
                }
                if (z && a.u.l(MallFloorBanner.this.getFloorPos()) && !com.jingdong.app.mall.home.r.a.c.s(((f) ((BaseMallColorFloor) MallFloorBanner.this).mPresenter).Z())) {
                    if (Log.D) {
                        Log.d(MallFloorBanner.TAG, "EntryAnim-before reset");
                    }
                    MallFloorBanner.this.animView1.setX(-MallFloorBanner.this.widthAnimView1);
                    MallFloorBanner.this.animView2.setX(p);
                } else {
                    if (Log.D) {
                        Log.d(MallFloorBanner.TAG, "EntryAnim-before setAnimationToEnd");
                    }
                    MallFloorBanner.this.setAnimationToEnd();
                }
                MallFloorBanner.this.loadImageWithUrl();
            }
        });
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallBannerFloorUI
    public void initViewData(int i2, int i3, int i4) {
        a.u.e(((f) this.mPresenter).Z());
        if (isMainThread()) {
            initViewDataOnMainThread(i2, i3, i4);
            return;
        }
        Class cls = Integer.TYPE;
        postToMainThread("initViewDataOnMainThread", new Class[]{cls, cls, cls}, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
    }

    public void initViewDataOnMainThread(int i2, int i3, int i4) {
        ((f) this.mPresenter).o0();
        PagerAdapter pagerAdapter = this.adapter;
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
        if (this.pager == null) {
            CarouselFigureViewPager2 carouselFigureViewPager2 = new CarouselFigureViewPager2(getContext());
            this.pager = carouselFigureViewPager2;
            carouselFigureViewPager2.setId(R.id.mallfloor_banner_pager);
            this.pager.setOnPageChangeListener(this);
            this.pager.setOnTouchListener(this);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams.setMargins(0, 0, 0, 0);
        this.pager.setLayoutParams(layoutParams);
        if (this.pager.getParent() == null) {
            addView(this.pager, 0);
        }
        int cursorMarginBottom = ((f) this.mPresenter).getCursorMarginBottom();
        this.mCursorContentViewCtrl.h();
        this.mCursorContentViewCtrl.f(((f) this.mPresenter).k0() ? new AnimationLinerPagerCursor(getContext()) : new LinerPagerCursor(getContext()), this.pager.getId(), (ICursorContentViewPresenter) this.mPresenter, cursorMarginBottom);
        this.mCursorContentViewCtrl.onPageSelected(0);
        this.mCursorContentViewCtrl.e(0);
        this.pager.init(this, ((f) this.mPresenter).i0());
        if (i4 > 0) {
            ViewPagerScroller viewPagerScroller = new ViewPagerScroller(getContext());
            viewPagerScroller.setScrollDuration(i4);
            viewPagerScroller.initViewPagerScroll(this.pager);
        }
        if (!((f) this.mPresenter).g0()) {
            CarouseFigureImagePagerWithEndLoadingAdapter carouseFigureImagePagerWithEndLoadingAdapter = new CarouseFigureImagePagerWithEndLoadingAdapter(getContext(), false, (CarouseFigureImagePagerAdapter.CarouseFigureImageAdapterListener) this.mPresenter, new CarouseFigureImagePagerWithEndLoadingAdapter.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner.3
                {
                    MallFloorBanner.this = this;
                }

                @Override // com.jingdong.app.mall.home.floor.view.adapter.CarouseFigureImagePagerWithEndLoadingAdapter.b
                public void onPagerEndLoading(int i5) {
                    MallFloorBanner.this.onBackgroundImageEndLoading(i5);
                }
            });
            this.adapter = carouseFigureImagePagerWithEndLoadingAdapter;
            setAdapter(carouseFigureImagePagerWithEndLoadingAdapter);
            CarouselFigureViewPager2 carouselFigureViewPager22 = this.pager;
            if ((((f) this.mPresenter).f0() && (carouselFigureViewPager22 != null && carouselFigureViewPager22.getAdapter() != null) && this.pager.getAdapter().getCount() > 1) || isEntryAnimMode()) {
                a.u.f(this);
                return;
            }
            return;
        }
        ((f) this.mPresenter).p0();
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public boolean isDictator() {
        return isEntryAnimMode();
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public boolean isInDisplayArea(int i2, int i3) {
        if (isEntryAnimMode()) {
            return m.G(this, a.j() + i2, i3, 50);
        }
        return isFloorDisplay();
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public boolean isMatchOtherStartCondition() {
        CarouselFigureViewPager2 carouselFigureViewPager2;
        if ((((f) this.mPresenter).e0() && !(((f) this.mPresenter).f0() && ((carouselFigureViewPager2 = this.pager) == null || carouselFigureViewPager2.getAdapter() == null || this.pager.getAdapter().getCount() != 1))) && com.jingdong.app.mall.home.r.a.c.s(((f) this.mPresenter).Z())) {
            if (Log.D) {
                Log.d(TAG, "EntryAnim-hasPlayed:" + ((f) this.mPresenter).i());
            }
            setAnimationToEnd();
            return false;
        }
        return true;
    }

    @Override // com.jingdong.app.mall.home.r.a.b
    public boolean isNeedWait() {
        if (isEntryAnimMode()) {
            return (this.mIsTonglangAnimAllLoaded && ((f) this.mPresenter).x()) ? false : true;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.r.a.b
    public boolean isSplashAnimation() {
        return false;
    }

    public void onBackgroundImageEndLoading(int i2) {
        if (isEntryAnimMode()) {
            a.u.h(this);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallBannerFloorUI
    public void onClick(com.jingdong.app.mall.home.r.e.f fVar, int i2) {
        if (getHeight() < com.jingdong.app.mall.home.floor.common.d.d(50)) {
            return;
        }
        String e2 = fVar.e();
        if (!TextUtils.isEmpty(e2)) {
            postUrl(e2);
        }
        new com.jingdong.app.mall.home.q.a("\u7a84\u901a\u680f\u70b9\u51fb", fVar.f()).b();
        JumpEntity jump = fVar.getJump();
        if (jump == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(JDMtaUtils.ABTKEY, fVar.a());
        hashMap.put("extension_id", fVar.o());
        l.onClickJsonEvent(getContext(), jump, "", fVar.b0(), jump.getSrvJson(), 0, hashMap);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        this.mStartSlidePos = this.pager.getCurrentItem();
        if (Log.D) {
            Log.d(TAG, "on touch down:" + this.mStartSlidePos);
            return false;
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onFloorInitEnd() {
        super.onFloorInitEnd();
        com.jingdong.app.mall.home.o.a.f.u0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner.4
            {
                MallFloorBanner.this = this;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                MallFloorBanner.this.checkAndAddMaiDian(0);
            }
        });
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void onHomeResume(int i2, int i3) {
        super.onHomeResume(i2, i3);
        CarouselFigureViewPager2 carouselFigureViewPager2 = this.pager;
        if (carouselFigureViewPager2 == null) {
            return;
        }
        PagerAdapter pagerAdapter = this.mAdapterBeforeDestroy;
        if (pagerAdapter != null) {
            carouselFigureViewPager2.setAdapter(pagerAdapter);
            this.pager.setCurrentItem(this.mSelectedIndexBeforeDestroy);
        }
        if (this.mCursorContentViewCtrl != null && ((f) this.mPresenter).l0()) {
            checkAndAddMaiDian(this.mCursorContentViewCtrl.a());
        }
        if (isFloorDisplay()) {
            if (Log.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("onHomeResume-");
                CarouselFigureViewPager2 carouselFigureViewPager22 = this.pager;
                sb.append(carouselFigureViewPager22.toRealPosition(carouselFigureViewPager22.getCurrentItem()));
                sb.append(":");
                sb.append(getFloorId());
                Log.i(TAG, sb.toString());
            }
            int currentItem = this.pager.getCurrentItem();
            ((f) this.mPresenter).P(currentItem);
            reportExpoUrl((f) this.mPresenter, currentItem);
            ((f) this.mPresenter).p0();
        }
        String str = i2 + "";
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        super.onHomeScrollStop(i2, i3);
        boolean isFloorDisplay = isFloorDisplay();
        int currentItem = this.pager.getCurrentItem();
        if (Log.D) {
            Log.i(TAG, "onHomeScrollStop-" + isFloorDisplay + "-" + this.isPreScrollStopVisible + ":" + getFloorId());
        }
        if (!this.isPreScrollStopVisible && isFloorDisplay) {
            ((f) this.mPresenter).P(currentItem);
            reportExpoUrl((f) this.mPresenter, currentItem);
        }
        if (isFloorDisplay) {
            ((f) this.mPresenter).p0();
        }
        if (!isFloorDisplay) {
            ((f) this.mPresenter).n0(currentItem);
            ((f) this.mPresenter).u0(currentItem, 0.0f);
        }
        this.isPreScrollStopVisible = isFloorDisplay;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 4) {
            onResume();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
        if (i2 == 0) {
            addSlideMta(this.mIsSlideDirectionLeft);
            this.mSlidePageCount = 0;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        i iVar = this.mCursorContentViewCtrl;
        if (iVar != null) {
            iVar.onPageScrolled(i2, f2, i3);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        i iVar = this.mCursorContentViewCtrl;
        if (iVar == null || this.pager == null) {
            return;
        }
        iVar.onPageSelected(i2);
        PagerAdapter adapter = this.pager.getAdapter();
        if (adapter instanceof CarouseFigureLayoutPagerAdapter) {
            ((CarouseFigureLayoutPagerAdapter) adapter).onPageSelected(i2);
        }
        if (!this.isPause && !this.mCursorContentViewCtrl.c(i2)) {
            if (Log.D) {
                Log.i(TAG, "onPageSelected-" + i2 + ":" + getFloorId());
            }
            checkAndAddMaiDian(i2);
            this.mSlidePageCount++;
        }
        this.mCursorContentViewCtrl.e(i2);
        if (!this.isPause) {
            autoChangeViewPagerPosition(((f) this.mPresenter).b0());
        }
        if (((f) this.mPresenter).g0()) {
            sCurrentBannerFrameIndex = i2;
            sCurrentBannerSelectedTime = SystemClock.elapsedRealtime();
        }
    }

    public void onPause() {
        this.isPause = true;
        this.handler.removeCallbacksAndMessages(null);
    }

    public void onResume() {
        this.isPause = false;
        if (isEntryAnimMode()) {
            startAnim();
        } else {
            autoChangeViewPagerPosition(((f) this.mPresenter).b0());
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (this.mStartGestureSlide.compareAndSet(false, true) && this.pager != null) {
            this.mSlidePageCount = 0;
            this.mIsSlideDirectionLeft = f2 > 0.0f;
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            if (this.isPause) {
                return false;
            }
            onPause();
            return false;
        } else if (action == 1 || action == 3) {
            onResume();
            return false;
        } else {
            return false;
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onViewBindData(com.jingdong.app.mall.home.r.e.d dVar) {
        super.onViewBindData(dVar);
        if (((f) this.mPresenter).g0()) {
            setInitialExpoSalUrlRecord(false);
            setTag(R.id.mallfloor_bottom_offset_padding, Integer.valueOf(BannerFloorEntity.getBannerCoveredHeight()));
        }
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public void pause(int i2) {
        CarouselFigureViewPager2 carouselFigureViewPager2;
        if (!((f) this.mPresenter).f0() || (carouselFigureViewPager2 = this.pager) == null || carouselFigureViewPager2.getAdapter() == null || this.pager.getAdapter().getCount() <= 1) {
            return;
        }
        onPause();
    }

    public synchronized void reportExpoUrlAfterRefresh(f fVar, int i2) {
        if (!fVar.g0() && !((f) this.mPresenter).j0() && isInDisplayArea(com.jingdong.app.mall.home.r.c.a.i().h(), com.jingdong.app.mall.home.r.c.a.i().g())) {
            postUrl(fVar.V(i2));
            ((f) this.mPresenter).s0(true);
            fVar.m0(i2);
        }
    }

    protected void setAdapter(PagerAdapter pagerAdapter) {
        if (pagerAdapter != null) {
            this.pager.setAdapter(pagerAdapter);
            i iVar = this.mCursorContentViewCtrl;
            if (iVar != null) {
                iVar.b(this.pager.getRealCount(), this, this.pager.getCurrentItem());
            }
        }
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
    }

    protected void setAnimationToEndOnMainThread() {
        if (Log.D) {
            Log.d(TAG, "EntryAnim-setAnimationToEndOnMainThread:" + ((f) this.mPresenter).i() + ";animView1.getX():" + this.animView1.getX() + ";animView2.getX():" + this.animView2.getX());
        }
        if (this.midLinePosition == 0) {
            this.midLinePosition = getLeft() + (((f) this.mPresenter).p() >> 1);
            this.widthAnimView1 = com.jingdong.app.mall.home.floor.common.d.d(333);
            this.widthAnimView2 = com.jingdong.app.mall.home.floor.common.d.d(333);
        }
        Log.d(TAG, "EntryAnim-setAnimationToEndOnMainThread2:" + ((f) this.mPresenter).i() + ";midLinePosition:" + this.midLinePosition + ";widthAnimView1:" + this.widthAnimView1);
        float x = this.animView1.getX();
        int i2 = this.midLinePosition;
        int i3 = this.widthAnimView1;
        if (x != i2 - i3) {
            this.animView1.setX(i2 - i3);
        }
        float x2 = this.animView2.getX();
        int i4 = this.midLinePosition;
        if (x2 != i4) {
            this.animView2.setX(i4);
        }
    }

    public void setCarouseFigureImageAdapterListener(CarouseFigureLayoutPagerAdapter.c cVar) {
        CarouselFigureViewPager2 carouselFigureViewPager2;
        if (this.pager == null || cVar == null || cVar.getCount() <= 0) {
            return;
        }
        this.pager.setAdapter(new CarouseFigureLayoutPagerAdapter(getContext(), cVar));
        i iVar = this.mCursorContentViewCtrl;
        if (iVar != null) {
            iVar.b(this.pager.getRealCount(), this, this.pager.getCurrentItem());
        }
        if (!((f) this.mPresenter).f0() || (carouselFigureViewPager2 = this.pager) == null || carouselFigureViewPager2.getAdapter() == null || this.pager.getAdapter().getCount() <= 1) {
            return;
        }
        a.u.f(this);
    }

    public void setInitialExpoSalUrlRecord(boolean z) {
        if (((f) this.mPresenter).g0()) {
            this.initialExposalUrlRecord = z;
            if (Log.D) {
                Log.d("HHH", "expoSal url, set initialRecord.");
            }
        }
    }

    public void setModelId(String str) {
    }

    public void setPriority(int i2) {
        this.mTonglanAnimPriority = i2;
    }

    public void startAnim() {
        if (isMainThread()) {
            startAnimOnMainThread();
        } else {
            postToMainThread("startAnimOnMainThread", null, new Object[0]);
        }
    }

    public synchronized void startAnimOnMainThread() {
        if (com.jingdong.app.mall.home.r.a.c.t != 1.0f) {
            this.leftAnimDuration = 600.0f / r0;
            this.rightAnimDuration = 800.0f / r0;
            this.startDelay = 500.0f / r0;
        }
        Log.d(TAG, "EntryAnim-startAnim: image2Ready:" + this.isImg2Ready + "   image3Ready:" + this.isImg3Ready);
        SimpleDraweeView simpleDraweeView = this.animView1;
        if (simpleDraweeView != null && this.animView2 != null) {
            if (this.anim1 == null) {
                this.anim1 = ObjectAnimator.ofFloat(simpleDraweeView, JshopConst.JSHOP_PROMOTIO_X, simpleDraweeView.getX(), this.midLinePosition - this.widthAnimView1).setDuration(this.leftAnimDuration);
            }
            if (this.anim2 == null) {
                SimpleDraweeView simpleDraweeView2 = this.animView2;
                this.anim2 = ObjectAnimator.ofFloat(simpleDraweeView2, JshopConst.JSHOP_PROMOTIO_X, simpleDraweeView2.getX(), this.midLinePosition).setDuration(this.rightAnimDuration);
            }
            if (this.animSet == null) {
                AnimatorSet animatorSet = new AnimatorSet();
                this.animSet = animatorSet;
                animatorSet.playTogether(this.anim1, this.anim2);
                this.animSet.setStartDelay(this.startDelay);
                this.animSet.addListener(new Animator.AnimatorListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner.7
                    {
                        MallFloorBanner.this = this;
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator) {
                        if (MallFloorBanner.this.mAnimatorListener != null) {
                            MallFloorBanner.this.mAnimatorListener.onAnimationCancel(animator);
                        }
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        if (MallFloorBanner.this.mAnimatorListener != null) {
                            MallFloorBanner.this.mAnimatorListener.onAnimationEnd(animator);
                        }
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationRepeat(Animator animator) {
                        if (MallFloorBanner.this.mAnimatorListener != null) {
                            MallFloorBanner.this.mAnimatorListener.onAnimationRepeat(animator);
                        }
                    }

                    @Override // android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        if (MallFloorBanner.this.mAnimatorListener != null) {
                            MallFloorBanner.this.mAnimatorListener.onAnimationStart(animator);
                        }
                    }
                });
            }
            Log.d(TAG, "anim start");
            if (!this.animSet.isRunning()) {
                this.animSet.start();
                try {
                    JDMtaUtils.sendCommonData(getContext(), "Home_TLFloorDynaEffect", ((f) this.mPresenter).i() + CartConstant.KEY_YB_INFO_LINK + ((f) this.mPresenter).Z(), "", this, "", "", "", RecommendMtaUtils.Home_PageId);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public void startPlay() {
        onResume();
    }

    public void startPlayBannerFlipper(int i2) {
        autoChangeViewPagerPosition(i2);
    }

    @Override // com.jingdong.app.mall.home.r.a.a
    public void stopPlay() {
        onPause();
        if (isEntryAnimMode()) {
            endAnim();
        }
    }

    public void stopPlayBanner() {
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    @Nullable
    public f createPresenter() {
        return new f(BannerFloorEntity.class, BannerFloorEngine.class);
    }
}
