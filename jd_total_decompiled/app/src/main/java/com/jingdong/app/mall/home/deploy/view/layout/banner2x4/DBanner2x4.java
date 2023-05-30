package com.jingdong.app.mall.home.deploy.view.layout.banner2x4;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.a;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.banner2x4.BannerAdapter;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.ctrl.d;
import com.jingdong.app.mall.home.floor.ctrl.i;
import com.jingdong.app.mall.home.floor.view.AnimationLinerPagerCursor;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.utils.ui.view.CarouselFigureViewPager2;
import com.jingdong.cleanmvp.common.BaseEvent;

/* loaded from: classes4.dex */
public class DBanner2x4 extends CoreBaseView implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    private static int A;
    private static long B;
    private DBanner2x4Model p;
    private boolean q;
    private long r;
    private boolean s;
    private BannerCursorPresenter t;
    private BannerFigureViewCtrl u;
    private GestureDetector v;
    private BannerGestureListener w;
    private CarouselFigureViewPager2 x;
    private final i y;
    private final Handler z;

    public DBanner2x4(Context context) {
        super(context);
        this.q = true;
        this.r = -1L;
        this.s = false;
        this.t = new BannerCursorPresenter();
        this.y = new d();
        this.z = new Handler(Looper.getMainLooper()) { // from class: com.jingdong.app.mall.home.deploy.view.layout.banner2x4.DBanner2x4.1
            {
                DBanner2x4.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (!DBanner2x4.this.q && DBanner2x4.this.x != null && DBanner2x4.this.x.getChildCount() > 1 && DBanner2x4.this.x.getAdapter() != null && DBanner2x4.this.x.getAdapter().getCount() >= 2 && DBanner2x4.this.x()) {
                    try {
                        if (DBanner2x4.this.r - ((Long) message.obj).longValue() != 0) {
                            return;
                        }
                        DBanner2x4.this.x.next();
                    } catch (Exception unused) {
                    }
                }
            }
        };
        this.o = 0;
        if (this.x == null) {
            CarouselFigureViewPager2 carouselFigureViewPager2 = new CarouselFigureViewPager2(context);
            this.x = carouselFigureViewPager2;
            carouselFigureViewPager2.setId(R.id.mallfloor_banner_pager);
            this.x.setOnPageChangeListener(this);
            this.x.setOnTouchListener(this);
            this.w = new BannerGestureListener(this.x);
        }
        this.u = new BannerFigureViewCtrl();
        this.v = new GestureDetector(context, this.w);
        addView(this.x, new RelativeLayout.LayoutParams(-1, -1));
        f.G0(this);
    }

    public static long A() {
        if (B <= 0) {
            B = SystemClock.elapsedRealtime();
        }
        return B;
    }

    private void D() {
        int d = com.jingdong.app.mall.home.floor.common.d.d(13);
        this.t.a(this.p);
        this.y.h();
        this.y.f(new AnimationLinerPagerCursor(getContext()), this.x.getId(), this.t, d);
        this.y.onPageSelected(0);
        this.y.e(0);
        this.x.init(this, true);
    }

    private synchronized void w(int i2) {
        if (this.x == null) {
            return;
        }
        this.r = System.currentTimeMillis();
        Message obtain = Message.obtain();
        obtain.what = this.x.getCurrentItem();
        obtain.obj = Long.valueOf(this.r);
        this.z.removeCallbacksAndMessages(null);
        this.z.sendMessageDelayed(obtain, i2);
    }

    public static int z() {
        return A;
    }

    public int B() {
        CarouselFigureViewPager2 carouselFigureViewPager2 = this.x;
        if (carouselFigureViewPager2 == null) {
            return 0;
        }
        return carouselFigureViewPager2.getCurrentItem();
    }

    public float C() {
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

    public void E(int i2) {
        if (1 == i2 && this.p.K0()) {
            f.r0(this, "onPause _SCROLL");
            return;
        }
        this.q = true;
        this.z.removeCallbacksAndMessages(null);
        f.r0(this, "onPause");
    }

    public void F() {
        if (this.q && JDHomeFragment.O0()) {
            this.q = false;
            w(this.p.H0());
            f.r0(this, "onResume");
        }
    }

    public void G(BannerAdapter.CarouseFigureImageAdapterListener carouseFigureImageAdapterListener) {
        if (this.x == null || carouseFigureImageAdapterListener == null || carouseFigureImageAdapterListener.getCount() <= 0) {
            return;
        }
        this.x.setAdapter(new BannerAdapter(getContext(), carouseFigureImageAdapterListener));
        i iVar = this.y;
        if (iVar != null) {
            iVar.b(this.x.getRealCount(), this, this.x.getCurrentItem());
        }
        a.u.f(new BannerAnimation(this, this.p));
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void f(boolean z) {
        super.f(z);
        CarouselFigureViewPager2 carouselFigureViewPager2 = this.x;
        if (carouselFigureViewPager2 == null) {
            return;
        }
        PagerAdapter adapter = carouselFigureViewPager2.getAdapter();
        if (adapter instanceof BannerAdapter) {
            ((BannerAdapter) adapter).d(z);
        }
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    protected boolean j(BaseModel baseModel) {
        f.n(baseModel);
        this.p = (DBanner2x4Model) baseModel;
        return baseModel != null;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void m() {
        super.m();
        BannerExpoUtil.d();
        D();
        this.p.N0();
        this.u.d(this, this.p);
        e.d(this, s());
        f.F0(new b() { // from class: com.jingdong.app.mall.home.deploy.view.layout.banner2x4.DBanner2x4.2
            {
                DBanner2x4.this = this;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                DBanner2x4.this.y(0);
            }
        }, 10L);
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void n() {
        super.n();
        this.p.M0(B());
        this.p.T0(B(), C());
        this.p.W0();
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void o() {
        super.o();
        if (this.x == null) {
            return;
        }
        i iVar = this.y;
        if (iVar != null) {
            y(iVar.a());
        }
        if (l()) {
            this.p.y0(this, this.x.getCurrentItem());
            this.p.N0();
        }
    }

    public void onEvent(BaseEvent baseEvent) {
        if ((baseEvent instanceof MallFloorEvent) && TextUtils.equals(baseEvent.getType(), "home_data_back") && l()) {
            this.p.T0(B(), 0.0f);
            this.p.W0();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 3 || action == 4) {
            F();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
        if (i2 == 0) {
            this.w.b();
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        i iVar = this.y;
        if (iVar != null) {
            iVar.onPageScrolled(i2, f2, i3);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        i iVar = this.y;
        if (iVar == null || this.x == null) {
            return;
        }
        iVar.onPageSelected(i2);
        PagerAdapter adapter = this.x.getAdapter();
        if (adapter instanceof BannerAdapter) {
            ((BannerAdapter) adapter).onPageSelected(i2);
        }
        if (!this.q && !this.y.c(i2)) {
            y(i2);
            this.w.a();
        }
        this.y.e(i2);
        if (!this.q) {
            w(this.p.H0());
        }
        A = i2;
        B = SystemClock.elapsedRealtime();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.v.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1 || action == 3) {
                F();
            }
        } else if (!this.q) {
            E(0);
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void p(int i2, int i3) {
        super.p(i2, i3);
        boolean l2 = l();
        int currentItem = this.x.getCurrentItem();
        if (!this.s && l2) {
            this.p.y0(this, currentItem);
        }
        if (l2) {
            this.p.N0();
        } else {
            E(0);
        }
        if (!l2) {
            this.p.M0(currentItem);
            this.p.T0(currentItem, 0.0f);
        }
        this.s = l2;
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView, com.jingdong.app.mall.home.deploy.view.base.BaseView
    public void q() {
        super.q();
    }

    public boolean x() {
        return JDHomeFragment.O0() && l();
    }

    public void y(int i2) {
        if (JDHomeFragment.O0()) {
            if (l()) {
                this.p.y0(this, i2);
                BannerExpoUtil.c(this.p, this, i2);
                this.s = true;
                return;
            }
            this.s = false;
        }
    }
}
