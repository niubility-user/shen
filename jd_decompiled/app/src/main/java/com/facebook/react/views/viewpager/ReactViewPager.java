package com.facebook.react.views.viewpager;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class ReactViewPager extends ViewPager {
    private final EventDispatcher mEventDispatcher;
    private boolean mIsCurrentItemFromJs;
    private boolean mScrollEnabled;
    private final Runnable measureAndLayout;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class Adapter extends PagerAdapter {
        private boolean mIsViewPagerInIntentionallyInconsistentState;
        private final List<View> mViews;

        private Adapter() {
            this.mViews = new ArrayList();
            this.mIsViewPagerInIntentionallyInconsistentState = false;
        }

        void addView(View view, int i2) {
            this.mViews.add(i2, view);
            notifyDataSetChanged();
            ReactViewPager.this.setOffscreenPageLimit(this.mViews.size());
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.mViews.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            if (this.mIsViewPagerInIntentionallyInconsistentState || !this.mViews.contains(obj)) {
                return -2;
            }
            return this.mViews.indexOf(obj);
        }

        View getViewAt(int i2) {
            return this.mViews.get(i2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i2) {
            View view = this.mViews.get(i2);
            viewGroup.addView(view, 0, ReactViewPager.this.generateDefaultLayoutParams());
            return view;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        void removeAllViewsFromAdapter(ViewPager viewPager) {
            this.mViews.clear();
            viewPager.removeAllViews();
            this.mIsViewPagerInIntentionallyInconsistentState = true;
        }

        void removeViewAt(int i2) {
            this.mViews.remove(i2);
            notifyDataSetChanged();
            ReactViewPager.this.setOffscreenPageLimit(this.mViews.size());
        }

        void setViews(List<View> list) {
            this.mViews.clear();
            this.mViews.addAll(list);
            notifyDataSetChanged();
            this.mIsViewPagerInIntentionallyInconsistentState = false;
        }
    }

    /* loaded from: classes12.dex */
    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        private PageChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            String str;
            if (i2 == 0) {
                str = "idle";
            } else if (i2 == 1) {
                str = "dragging";
            } else if (i2 != 2) {
                throw new IllegalStateException("Unsupported pageScrollState");
            } else {
                str = "settling";
            }
            ReactViewPager.this.mEventDispatcher.dispatchEvent(new PageScrollStateChangedEvent(ReactViewPager.this.getId(), str));
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            ReactViewPager.this.mEventDispatcher.dispatchEvent(new PageScrollEvent(ReactViewPager.this.getId(), i2, f2));
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (ReactViewPager.this.mIsCurrentItemFromJs) {
                return;
            }
            ReactViewPager.this.mEventDispatcher.dispatchEvent(new PageSelectedEvent(ReactViewPager.this.getId(), i2));
        }
    }

    public ReactViewPager(ReactContext reactContext) {
        super(reactContext);
        this.mScrollEnabled = true;
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.viewpager.ReactViewPager.1
            @Override // java.lang.Runnable
            public void run() {
                ReactViewPager reactViewPager = ReactViewPager.this;
                reactViewPager.measure(View.MeasureSpec.makeMeasureSpec(reactViewPager.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactViewPager.this.getHeight(), 1073741824));
                ReactViewPager reactViewPager2 = ReactViewPager.this;
                reactViewPager2.layout(reactViewPager2.getLeft(), ReactViewPager.this.getTop(), ReactViewPager.this.getRight(), ReactViewPager.this.getBottom());
            }
        };
        this.mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        this.mIsCurrentItemFromJs = false;
        setOnPageChangeListener(new PageChangeListener());
        setAdapter(new Adapter());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addViewToAdapter(View view, int i2) {
        getAdapter().addView(view, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getViewCountInAdapter() {
        return getAdapter().getCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getViewFromAdapter(int i2) {
        return getAdapter().getViewAt(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestLayout();
        post(this.measureAndLayout);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollEnabled) {
            try {
                if (super.onInterceptTouchEvent(motionEvent)) {
                    NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                    return true;
                }
            } catch (IllegalArgumentException e2) {
                FLog.w(ReactConstants.TAG, "Error intercepting touch event.", e2);
            }
            return false;
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollEnabled) {
            try {
                return super.onTouchEvent(motionEvent);
            } catch (IllegalArgumentException e2) {
                FLog.w(ReactConstants.TAG, "Error handling touch event.", e2);
                return false;
            }
        }
        return false;
    }

    public void removeAllViewsFromAdapter() {
        getAdapter().removeAllViewsFromAdapter(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeViewFromAdapter(int i2) {
        getAdapter().removeViewAt(i2);
    }

    public void setCurrentItemFromJs(int i2, boolean z) {
        this.mIsCurrentItemFromJs = true;
        setCurrentItem(i2, z);
        this.mIsCurrentItemFromJs = false;
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public void setViews(List<View> list) {
        getAdapter().setViews(list);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public Adapter getAdapter() {
        return (Adapter) super.getAdapter();
    }
}
