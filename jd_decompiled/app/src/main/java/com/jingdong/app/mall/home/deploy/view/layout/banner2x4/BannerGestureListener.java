package com.jingdong.app.mall.home.deploy.view.layout.banner2x4;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.utils.ui.view.CarouselFigureViewPager2;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class BannerGestureListener implements GestureDetector.OnGestureListener {

    /* renamed from: g  reason: collision with root package name */
    private int f8904g = 0;

    /* renamed from: h  reason: collision with root package name */
    private int f8905h = 0;

    /* renamed from: i  reason: collision with root package name */
    private boolean f8906i = true;

    /* renamed from: j  reason: collision with root package name */
    private AtomicBoolean f8907j = new AtomicBoolean(false);

    /* renamed from: k  reason: collision with root package name */
    private CarouselFigureViewPager2 f8908k;

    public BannerGestureListener(CarouselFigureViewPager2 carouselFigureViewPager2) {
        this.f8908k = carouselFigureViewPager2;
    }

    public void a() {
        this.f8905h++;
    }

    public void b() {
        if (this.f8908k == null) {
            return;
        }
        if (this.f8907j.compareAndSet(true, false)) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f8904g);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(this.f8906i ? "0" : "1");
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(this.f8905h);
            JDMtaUtils.onClickWithPageId(this.f8908k.getContext(), "Home_FocusPicSlide", a.f10642k, sb.toString(), RecommendMtaUtils.Home_PageId);
        }
        this.f8905h = 0;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        this.f8904g = this.f8908k.getCurrentItem();
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
        if (this.f8907j.compareAndSet(false, true) && this.f8908k != null) {
            this.f8905h = 0;
            this.f8906i = f2 > 0.0f;
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
}
