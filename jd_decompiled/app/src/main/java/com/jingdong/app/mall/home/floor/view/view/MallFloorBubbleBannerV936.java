package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.ctrl.i;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor;
import com.jingdong.app.mall.home.floor.view.widget.bubblebanner936.BubbleAdapter;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.f.a.h;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class MallFloorBubbleBannerV936 extends BaseMallFloor<h> implements IMallFloorUI, ViewPager.OnPageChangeListener {
    private Handler handler;
    private BubbleAdapter mBubbleAdapter;
    private BubblePager mBubblePager;
    private i mCursorContentViewCtrl;
    private boolean mManualScroll;
    private boolean mScrollRunning;
    private FitTopImage mSdvBg;
    private long token;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class BubblePager extends ViewPager {
        private float lastTouchX;

        public BubblePager(Context context) {
            super(context);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.lastTouchX = motionEvent.getX();
                MallFloorBubbleBannerV936.this.manualScroll(true);
            } else if (action == 1) {
                if (this.lastTouchX - motionEvent.getX() > d.d(R2.anim.slide_in_from_bottom)) {
                    a.s("Home_AirbubbleSlide", "", "");
                }
                MallFloorBubbleBannerV936.this.manualScroll(false);
            }
            return super.dispatchTouchEvent(motionEvent);
        }
    }

    public MallFloorBubbleBannerV936(Context context) {
        super(context);
        this.token = -1L;
        this.mCursorContentViewCtrl = new com.jingdong.app.mall.home.floor.ctrl.d();
        this.mManualScroll = false;
        this.mScrollRunning = false;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBubbleBannerV936.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (MallFloorBubbleBannerV936.this.mBubblePager != null && MallFloorBubbleBannerV936.this.mBubblePager.getChildCount() > 1 && MallFloorBubbleBannerV936.this.mBubblePager.getAdapter() != null && MallFloorBubbleBannerV936.this.mBubblePager.getAdapter().getCount() >= 2) {
                    try {
                        if (MallFloorBubbleBannerV936.this.token - ((Long) message.obj).longValue() != 0) {
                            return;
                        }
                        MallFloorBubbleBannerV936.this.mBubblePager.setCurrentItem(MallFloorBubbleBannerV936.this.mBubblePager.getCurrentItem() + 1, true);
                    } catch (Exception unused) {
                    }
                }
            }
        };
    }

    private void initBg() {
        if (this.mSdvBg == null) {
            this.mSdvBg = new FitTopImage(this.mContext);
        }
        this.mSdvBg.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        m.b(this, this.mSdvBg, 0);
        e.m(this.mSdvBg, ((h) this.mPresenter).T(), e.b);
    }

    private void initCursor() {
        this.mCursorContentViewCtrl.h();
        i iVar = this.mCursorContentViewCtrl;
        LinerPagerCursor linerPagerCursor = new LinerPagerCursor(this.mContext);
        int id = this.mBubblePager.getId();
        P p = this.mPresenter;
        iVar.f(linerPagerCursor, id, (ICursorContentViewPresenter) p, ((h) p).getCursorMarginBottom());
        this.mCursorContentViewCtrl.onPageSelected(0);
        this.mCursorContentViewCtrl.e(0);
    }

    private void initFloorView() {
        resetState();
        initBg();
        initViewPager();
        initCursor();
        setAdapter();
    }

    private void initViewPager() {
        if (this.mBubblePager == null) {
            BubblePager bubblePager = new BubblePager(this.mContext);
            this.mBubblePager = bubblePager;
            bubblePager.setId(R.id.bubble_banner_pager);
            addView(this.mBubblePager, new RelativeLayout.LayoutParams(-1, -1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void manualScroll(boolean z) {
        if (z) {
            if (this.mManualScroll) {
                return;
            }
            this.mManualScroll = true;
            stopScroll();
            return;
        }
        this.mManualScroll = false;
        startChangeViewPagerPosition(((h) this.mPresenter).S());
    }

    private void resetState() {
        this.handler.removeCallbacksAndMessages(null);
        this.mManualScroll = false;
        this.mScrollRunning = false;
    }

    private void setAdapter() {
        BubbleAdapter bubbleAdapter = new BubbleAdapter(this.mContext, (h) this.mPresenter);
        this.mBubbleAdapter = bubbleAdapter;
        this.mBubblePager.setAdapter(bubbleAdapter);
        int a0 = ((h) this.mPresenter).a0();
        this.mBubblePager.setCurrentItem(a0);
        ((h) this.mPresenter).c0(a0);
        this.mBubblePager.addOnPageChangeListener(this);
        i iVar = this.mCursorContentViewCtrl;
        if (iVar != null) {
            iVar.b(((h) this.mPresenter).Y(), this, 0);
        }
    }

    private void startChangeViewPagerPosition(int i2) {
        if (this.mScrollRunning || this.mBubblePager == null || !((h) this.mPresenter).P() || this.mManualScroll) {
            return;
        }
        this.token = System.currentTimeMillis();
        Message obtain = Message.obtain();
        obtain.what = this.mBubblePager.getCurrentItem();
        obtain.obj = Long.valueOf(this.token);
        this.handler.removeCallbacksAndMessages(null);
        this.handler.sendMessageDelayed(obtain, i2 * 1000);
        this.mScrollRunning = true;
    }

    private void stopScroll() {
        this.handler.removeCallbacksAndMessages(null);
        this.mScrollRunning = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            BubblePager bubblePager = this.mBubblePager;
            bubblePager.setCurrentItem(bubblePager.getCurrentItem(), true);
            this.mBubblePager.requestLayout();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        stopScroll();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void onHomeResume(int i2, int i3) {
        super.onHomeResume(i2, i3);
        if (getIsDisplayInScreen()) {
            startChangeViewPagerPosition(((h) this.mPresenter).S());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        if (isFloorDisplay()) {
            ((h) this.mPresenter).Q();
        }
        super.onHomeScrollStop(i2, i3);
        if (m.I(this, i2, i3, true)) {
            this.mManualScroll = false;
            startChangeViewPagerPosition(((h) this.mPresenter).R());
            return;
        }
        stopScroll();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        i iVar = this.mCursorContentViewCtrl;
        if (iVar != null) {
            iVar.onPageScrolled(i2 % ((h) this.mPresenter).Y(), f2, i3);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        if (this.mCursorContentViewCtrl == null) {
            return;
        }
        ((h) this.mPresenter).c0(i2);
        ((h) this.mPresenter).Q();
        this.mScrollRunning = false;
        int Y = i2 % ((h) this.mPresenter).Y();
        this.mCursorContentViewCtrl.onPageSelected(Y);
        this.mCursorContentViewCtrl.e(Y);
        startChangeViewPagerPosition(((h) this.mPresenter).S());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        super.onRefreshViewOnMainThread();
        initFloorView();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean useBgMarginColor() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public h createPresenter() {
        return new h();
    }
}
