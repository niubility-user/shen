package com.jd.lib.un.basewidget.widget.banner.indicator;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jd.lib.un.basewidget.widget.banner.BannerAdapter;
import com.jd.lib.un.basewidget.widget.banner.BannerView;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public abstract class BaseIndicator extends View implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
    protected List<IndicatorPoint> indicatorPoints;
    private boolean isInitDone;
    private boolean isNormal;
    protected boolean isSupportLoop;
    private PagerAdapter mAdapter;
    private BannerAdapter mBannerAdapter;
    private BannerView mBannerView;
    private ViewPager mViewPager;

    public BaseIndicator(Context context) {
        super(context);
        this.isNormal = true;
        this.isInitDone = false;
        this.isSupportLoop = true;
        this.indicatorPoints = new ArrayList();
    }

    private void onTouchSelected(int i2) {
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.setCurrentItem(i2, true);
            return;
        }
        BannerView bannerView = this.mBannerView;
        if (bannerView != null) {
            bannerView.setCurrentItem(i2, true);
        }
    }

    private boolean tryToDispatch(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (IndicatorPoint indicatorPoint : this.indicatorPoints) {
            RectF rectF = indicatorPoint.rect;
            if (rectF != null && rectF.contains(x, y)) {
                onTouchSelected(indicatorPoint.index);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addIndicatorPoint(IndicatorPoint indicatorPoint) {
        this.indicatorPoints.add(indicatorPoint);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clearIndicatorPoint() {
        this.indicatorPoints.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int dp2px(int i2) {
        return (int) ((UnDeviceInfo.getDensity() * i2) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getCount() {
        if (this.isNormal) {
            PagerAdapter pagerAdapter = this.mAdapter;
            if (pagerAdapter != null) {
                return pagerAdapter.getCount();
            }
            return 0;
        }
        BannerAdapter bannerAdapter = this.mBannerAdapter;
        if (bannerAdapter != null) {
            return bannerAdapter.getItemCount();
        }
        return 0;
    }

    protected abstract void initSelected(int i2);

    @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
    public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter pagerAdapter, @Nullable PagerAdapter pagerAdapter2) {
        if (pagerAdapter2 instanceof BannerAdapter) {
            BannerAdapter bannerAdapter = (BannerAdapter) pagerAdapter2;
            this.mBannerAdapter = bannerAdapter;
            if (bannerAdapter.getItemCount() > 0) {
                this.mBannerView.setCurrentItem(0);
                initSelected(0);
                return;
            }
            return;
        }
        this.mAdapter = pagerAdapter2;
        if (pagerAdapter2.getCount() > 0) {
            this.mViewPager.setCurrentItem(0);
            initSelected(0);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0 && tryToDispatch(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    protected void removeIndicatorPoint(IndicatorPoint indicatorPoint) {
        this.indicatorPoints.remove(indicatorPoint);
    }

    public void setBannerView(BannerView bannerView) {
        this.mBannerView = bannerView;
        this.mBannerAdapter = bannerView.getAdapter();
        this.mBannerView.removeOnPageChangeListener(this);
        this.mBannerView.addOnPageChangeListener(this);
        this.mBannerView.removeOnAdapterChangeListener(this);
        this.mBannerView.addOnAdapterChangeListener(this);
        this.isNormal = false;
        this.isInitDone = true;
        BannerView bannerView2 = this.mBannerView;
        initSelected(bannerView2 != null ? bannerView2.getCurrentItem() : 0);
    }

    public void setIsSupportLoop(boolean z) {
        this.isSupportLoop = z;
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
        this.mAdapter = viewPager.getAdapter();
        this.mViewPager.removeOnPageChangeListener(this);
        this.mViewPager.addOnPageChangeListener(this);
        this.mViewPager.removeOnAdapterChangeListener(this);
        this.mViewPager.addOnAdapterChangeListener(this);
        this.isNormal = true;
        this.isInitDone = true;
        ViewPager viewPager2 = this.mViewPager;
        initSelected(viewPager2 != null ? viewPager2.getCurrentItem() : 0);
    }

    public BaseIndicator(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isNormal = true;
        this.isInitDone = false;
        this.isSupportLoop = true;
        this.indicatorPoints = new ArrayList();
    }

    /* loaded from: classes16.dex */
    public class IndicatorPoint {
        public int bottom;
        public int index;
        public int left;
        public RectF rect;
        public int right;
        public int top;

        public IndicatorPoint(int i2, int i3, int i4, int i5, int i6) {
            this.index = i2;
            this.left = i3;
            this.top = i4;
            this.right = i5;
            this.bottom = i6;
            this.rect = new RectF(i3, i4, i5, i6);
        }

        public IndicatorPoint() {
        }
    }

    public BaseIndicator(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isNormal = true;
        this.isInitDone = false;
        this.isSupportLoop = true;
        this.indicatorPoints = new ArrayList();
    }
}
