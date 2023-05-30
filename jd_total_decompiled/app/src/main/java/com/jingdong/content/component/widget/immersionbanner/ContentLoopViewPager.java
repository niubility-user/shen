package com.jingdong.content.component.widget.immersionbanner;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class ContentLoopViewPager extends ViewPager implements Handler.Callback {
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int MSG_AUTO_SCROLL = 0;
    private static String TAG = "ContentLoopViewPager";
    private Handler handler;
    private boolean isAutoScroll;
    public boolean isAutoScrollPage;
    private boolean isDetach;
    private boolean isLoop;
    public boolean isPause;
    private boolean isSupportTouchInterrupt;
    private FaxianTJBannerViewAdapter mAdapter;
    private int mDirection;
    private BannerScroller mScroller;
    private int mSlideDuration;
    private int mSlideInterval;
    private SimplePageChangeListener pageChangeListener;
    private DelayResetTask task;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class BannerScroller extends Scroller {
        private int mDuration;

        public BannerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public void setDuration(int i2) {
            this.mDuration = i2;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i2, int i3, int i4, int i5) {
            super.startScroll(i2, i3, i4, i5, this.mDuration);
        }

        @Override // android.widget.Scroller
        public void startScroll(int i2, int i3, int i4, int i5, int i6) {
            super.startScroll(i2, i3, i4, i5, this.mDuration);
        }

        public BannerScroller(Context context, Interpolator interpolator, boolean z) {
            super(context, interpolator, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class DelayResetTask implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            ContentLoopViewPager.this.resetItem();
        }

        private DelayResetTask() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class SimplePageChangeListener extends ViewPager.SimpleOnPageChangeListener {
        public List<ViewPager.OnPageChangeListener> pageChangeListeners;

        private void notifyOnPageScrollStateChanged(int i2) {
            Iterator<ViewPager.OnPageChangeListener> it = this.pageChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().onPageScrollStateChanged(i2);
            }
        }

        private void notifyOnPageScrolled(int i2, float f2, int i3) {
            if (ContentLoopViewPager.this.mAdapter != null) {
                i2 = ContentLoopViewPager.this.mAdapter.getRealPosition(i2);
            }
            Iterator<ViewPager.OnPageChangeListener> it = this.pageChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().onPageScrolled(i2, f2, i3);
            }
        }

        private void notifyOnPageSelected(int i2) {
            if (ContentLoopViewPager.this.mAdapter != null) {
                i2 = ContentLoopViewPager.this.mAdapter.getRealPosition(i2);
            }
            Iterator<ViewPager.OnPageChangeListener> it = this.pageChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().onPageSelected(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            super.onPageScrollStateChanged(i2);
            notifyOnPageScrollStateChanged(i2);
            int superGetCurrentItem = ContentLoopViewPager.this.superGetCurrentItem();
            int count = ContentLoopViewPager.this.mAdapter == null ? 0 : ContentLoopViewPager.this.mAdapter.getCount();
            ContentLoopViewPager.this.handler.removeCallbacks(ContentLoopViewPager.this.task);
            if (i2 == 0) {
                if (superGetCurrentItem == 0 || superGetCurrentItem == count - 1) {
                    ContentLoopViewPager.this.resetItem();
                }
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            super.onPageScrolled(i2, f2, i3);
            notifyOnPageScrolled(i2, f2, i3);
        }

        @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            super.onPageSelected(i2);
            notifyOnPageSelected(i2);
        }

        private SimplePageChangeListener() {
            this.pageChangeListeners = new ArrayList();
        }
    }

    public ContentLoopViewPager(@NonNull Context context) {
        this(context, null);
    }

    private void changeScrollView() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            Field declaredField2 = ViewPager.class.getDeclaredField("sInterpolator");
            declaredField2.setAccessible(true);
            BannerScroller bannerScroller = new BannerScroller(getContext(), (Interpolator) declaredField2.get(null));
            this.mScroller = bannerScroller;
            bannerScroller.setDuration(this.mSlideDuration);
            declaredField.set(this, this.mScroller);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void init() {
        this.pageChangeListener = new SimplePageChangeListener();
        this.handler = new Handler(Looper.getMainLooper(), this);
        this.task = new DelayResetTask();
        super.addOnPageChangeListener(this.pageChangeListener);
    }

    private void initAutoScroll() {
        FaxianTJBannerViewAdapter faxianTJBannerViewAdapter = this.mAdapter;
        if (faxianTJBannerViewAdapter == null || faxianTJBannerViewAdapter.getItemCount() <= 1) {
            return;
        }
        if (this.isAutoScroll) {
            startAutoScroll();
        } else {
            stopAutoScroll();
        }
    }

    private void initGotoFirst() {
        FaxianTJBannerViewAdapter faxianTJBannerViewAdapter = this.mAdapter;
        if (faxianTJBannerViewAdapter == null || faxianTJBannerViewAdapter.getItemCount() <= 1) {
            return;
        }
        superSetCurrentItem(this.mAdapter.getMid(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetItem() {
        FaxianTJBannerViewAdapter faxianTJBannerViewAdapter;
        if (!this.isLoop || (faxianTJBannerViewAdapter = this.mAdapter) == null || faxianTJBannerViewAdapter.getItemCount() < 1) {
            return;
        }
        superSetCurrentItem(this.mAdapter.getMid() + (superGetCurrentItem() % this.mAdapter.getItemCount()), false);
    }

    private void scrollToNext() {
        FaxianTJBannerViewAdapter faxianTJBannerViewAdapter = this.mAdapter;
        if (faxianTJBannerViewAdapter == null || faxianTJBannerViewAdapter.getItemCount() <= 1) {
            return;
        }
        int superGetCurrentItem = superGetCurrentItem();
        int i2 = this.mDirection == 0 ? superGetCurrentItem + 1 : superGetCurrentItem - 1;
        if (!this.isLoop) {
            if (i2 > this.mAdapter.getItemCount() - 1) {
                i2 = 0;
            } else if (i2 < 0) {
                i2 = this.mAdapter.getItemCount() - 1;
            }
        }
        superSetCurrentItem(i2, true);
    }

    private void startAutoScroll() {
        this.handler.removeMessages(0);
        this.handler.sendEmptyMessageDelayed(0, this.mSlideInterval);
    }

    private void stopAutoScroll() {
        this.handler.removeMessages(0);
        this.isAutoScrollPage = false;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void addOnPageChangeListener(@NonNull ViewPager.OnPageChangeListener onPageChangeListener) {
        SimplePageChangeListener simplePageChangeListener = this.pageChangeListener;
        if (simplePageChangeListener != null) {
            simplePageChangeListener.pageChangeListeners.add(onPageChangeListener);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.isSupportTouchInterrupt) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if ((actionMasked == 1 || actionMasked == 3 || actionMasked == 4) && this.isAutoScroll) {
                    startAutoScroll();
                }
            } else if (this.isAutoScroll) {
                if (!this.mScroller.isFinished()) {
                    this.mScroller.forceFinished(true);
                }
                stopAutoScroll();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public int getCurrentItem() {
        int currentItem = super.getCurrentItem();
        FaxianTJBannerViewAdapter faxianTJBannerViewAdapter = this.mAdapter;
        if (faxianTJBannerViewAdapter != null) {
            return faxianTJBannerViewAdapter.getRealPosition(currentItem);
        }
        return 0;
    }

    public int getMClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    @Override // androidx.viewpager.widget.ViewPager
    public int getPageMargin() {
        return super.getPageMargin();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (this.isAutoScroll) {
            startAutoScroll();
        }
        if (!this.isPause && !this.isDetach && this.isAutoScroll) {
            this.isAutoScrollPage = true;
            scrollToNext();
        }
        return true;
    }

    public void isSupportAutoScroll(boolean z) {
        if (this.isAutoScroll != z) {
            this.isAutoScroll = z;
            if (z) {
                startAutoScroll();
            } else {
                stopAutoScroll();
            }
        }
    }

    public void isSupportLoop(boolean z) {
        this.isLoop = z;
        FaxianTJBannerViewAdapter faxianTJBannerViewAdapter = this.mAdapter;
        if (faxianTJBannerViewAdapter != null) {
            faxianTJBannerViewAdapter.isSupportLoop(z);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void isSupportTouchInterrupt(boolean z) {
        this.isSupportTouchInterrupt = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isDetach = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isDetach = true;
    }

    public void onPause() {
        this.isPause = true;
        resetItem();
    }

    public void onResume() {
        this.isPause = false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void removeOnPageChangeListener(@NonNull ViewPager.OnPageChangeListener onPageChangeListener) {
        SimplePageChangeListener simplePageChangeListener = this.pageChangeListener;
        if (simplePageChangeListener != null) {
            simplePageChangeListener.pageChangeListeners.remove(onPageChangeListener);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager
    @Deprecated
    public void setAdapter(@Nullable PagerAdapter pagerAdapter) {
    }

    public void setAdapter(FaxianTJBannerViewAdapter faxianTJBannerViewAdapter) {
        this.mAdapter = faxianTJBannerViewAdapter;
        faxianTJBannerViewAdapter.isSupportLoop(this.isLoop);
        super.setAdapter((PagerAdapter) faxianTJBannerViewAdapter);
        initGotoFirst();
        initAutoScroll();
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i2) {
        FaxianTJBannerViewAdapter faxianTJBannerViewAdapter = this.mAdapter;
        if (faxianTJBannerViewAdapter == null || faxianTJBannerViewAdapter.getItemCount() <= 0) {
            return;
        }
        int superGetCurrentItem = superGetCurrentItem();
        int itemCount = superGetCurrentItem % this.mAdapter.getItemCount();
        super.setCurrentItem(itemCount > i2 ? superGetCurrentItem - (itemCount - i2) : superGetCurrentItem + (i2 - itemCount));
    }

    public void setDirection(int i2) {
        this.mDirection = i2;
    }

    public void setSlideDuration(int i2) {
        this.mSlideDuration = i2;
        changeScrollView();
    }

    public void setSlideInterval(int i2) {
        this.mSlideInterval = i2;
    }

    public void startLoop() {
        if (this.isAutoScroll) {
            startAutoScroll();
        }
    }

    public void stopLoop() {
        stopAutoScroll();
    }

    public int superGetCurrentItem() {
        return super.getCurrentItem();
    }

    public void superSetCurrentItem(int i2, boolean z) {
        super.setCurrentItem(i2, z);
    }

    public ContentLoopViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isLoop = true;
        this.isAutoScroll = true;
        this.isSupportTouchInterrupt = true;
        this.mSlideInterval = 5000;
        this.mSlideDuration = 800;
        this.mDirection = 0;
        this.isPause = false;
        this.isDetach = false;
        this.isAutoScrollPage = false;
        init();
        initAutoScroll();
        changeScrollView();
    }

    private void superSetCurrentItem(int i2) {
        super.setCurrentItem(i2);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public FaxianTJBannerViewAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i2, boolean z) {
        FaxianTJBannerViewAdapter faxianTJBannerViewAdapter = this.mAdapter;
        if (faxianTJBannerViewAdapter == null || faxianTJBannerViewAdapter.getItemCount() <= 0) {
            return;
        }
        int superGetCurrentItem = superGetCurrentItem();
        int itemCount = superGetCurrentItem % this.mAdapter.getItemCount();
        super.setCurrentItem(itemCount > i2 ? superGetCurrentItem - (itemCount - i2) : superGetCurrentItem + (i2 - itemCount), z);
    }
}
