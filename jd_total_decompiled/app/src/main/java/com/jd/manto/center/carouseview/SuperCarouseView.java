package com.jd.manto.center.carouseview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewpager.widget.ViewPager;
import com.jd.manto.center.adapter.SuperConfigPagerAdapter;

/* loaded from: classes.dex */
public class SuperCarouseView extends FrameLayout implements ViewPager.OnPageChangeListener, LifecycleObserver {

    /* renamed from: g  reason: collision with root package name */
    private b f6533g;

    /* renamed from: h  reason: collision with root package name */
    private CarouselFigureViewPager f6534h;

    /* renamed from: i  reason: collision with root package name */
    private int f6535i;

    /* renamed from: j  reason: collision with root package name */
    private long f6536j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f6537k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f6538l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f6539m;

    /* renamed from: n  reason: collision with root package name */
    private Handler f6540n;

    /* loaded from: classes17.dex */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (!SuperCarouseView.this.f6539m && SuperCarouseView.this.f6534h != null && SuperCarouseView.this.f6534h.getChildCount() > 1 && SuperCarouseView.this.f6534h.getAdapter() != null && SuperCarouseView.this.f6534h.getAdapter().getCount() >= 2) {
                try {
                    if (SuperCarouseView.this.f6536j - ((Long) message.obj).longValue() != 0) {
                        return;
                    }
                    int i2 = message.what;
                    if (SuperCarouseView.this.f6537k) {
                        if (i2 != 0 || SuperCarouseView.this.f6534h.getCurrentItem() == 0) {
                            if (i2 == SuperCarouseView.this.f6534h.getRealCount() + 1) {
                                SuperCarouseView.this.f6534h.setCurrentItem(2);
                                return;
                            } else {
                                SuperCarouseView.this.f6534h.setCurrentItem(i2 + 1);
                                return;
                            }
                        }
                        SuperCarouseView.this.f6534h.setCurrentItem(SuperCarouseView.this.f6534h.getRealCount() + 1);
                        return;
                    }
                    SuperCarouseView.this.f6534h.setCurrentItem((i2 + 1) % SuperCarouseView.this.f6534h.getAdapter().getCount());
                } catch (Exception unused) {
                }
            }
        }
    }

    /* loaded from: classes17.dex */
    public interface b {
        void onPageSelect(int i2);
    }

    public SuperCarouseView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private synchronized void e(int i2) {
        if (this.f6534h != null && this.f6538l) {
            this.f6536j = System.currentTimeMillis();
            Message obtain = Message.obtain();
            obtain.what = this.f6534h.getCurrentItem();
            obtain.obj = Long.valueOf(this.f6536j);
            this.f6540n.sendMessageDelayed(obtain, i2);
        }
    }

    public int f() {
        CarouselFigureViewPager carouselFigureViewPager = this.f6534h;
        if (carouselFigureViewPager != null) {
            return carouselFigureViewPager.toRealPosition(carouselFigureViewPager.getCurrentItem());
        }
        return 0;
    }

    public void g(Context context, ViewGroup viewGroup, int i2, int i3, int i4, boolean z, boolean z2, String str) {
        this.f6537k = z;
        this.f6538l = z2;
        try {
            if (TextUtils.isEmpty(str)) {
                this.f6535i = 3000;
            } else {
                int parseInt = Integer.parseInt(str);
                this.f6535i = parseInt;
                if (parseInt < 1000) {
                    this.f6535i = 3000;
                }
            }
        } catch (Exception unused) {
            this.f6535i = 3000;
        }
        if (this.f6534h == null) {
            this.f6534h = new CarouselFigureViewPager(context);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, i2);
            layoutParams.setMargins(i3, 0, i4, 0);
            this.f6534h.setLayoutParams(layoutParams);
            this.f6534h.setOnPageChangeListener(this);
            addView(this.f6534h);
        }
        this.f6534h.init(viewGroup, z);
    }

    public void h(SuperConfigPagerAdapter superConfigPagerAdapter, b bVar) {
        this.f6533g = bVar;
        CarouselFigureViewPager carouselFigureViewPager = this.f6534h;
        if (carouselFigureViewPager == null || superConfigPagerAdapter == null) {
            return;
        }
        carouselFigureViewPager.setAdapter(superConfigPagerAdapter);
        e(this.f6535i);
    }

    public void i(boolean z) {
        CarouselFigureViewPager carouselFigureViewPager = this.f6534h;
        if (carouselFigureViewPager != null) {
            carouselFigureViewPager.setClipChildren(z);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        this.f6540n.removeCallbacksAndMessages(null);
        this.f6540n = null;
        CarouselFigureViewPager carouselFigureViewPager = this.f6534h;
        if (carouselFigureViewPager != null) {
            carouselFigureViewPager.setAdapter(null);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        b bVar = this.f6533g;
        if (bVar != null) {
            bVar.onPageSelect(i2);
        }
        e(this.f6535i);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.f6536j = System.currentTimeMillis();
        this.f6539m = true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        this.f6539m = false;
        e(3000);
    }

    public SuperCarouseView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f6535i = 0;
        this.f6536j = -1L;
        this.f6540n = new a();
        if (context instanceof LifecycleOwner) {
            ((LifecycleOwner) context).getLifecycle().addObserver(this);
        }
    }
}
