package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class CarouselFigureViewPager2 extends androidx.viewpager.widget.LoopViewPager implements View.OnTouchListener {
    private static final String TAG = "CarouselFigureViewPager2";
    protected boolean isCarousel;
    private boolean mFirstLayout;
    private boolean mOnTouchFlag;
    private View.OnTouchListener onTouchListener;
    private ViewGroup parent;

    public CarouselFigureViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFirstLayout = true;
        this.isCarousel = false;
        this.mOnTouchFlag = false;
    }

    private int getRealItem(int i2) {
        int count;
        PagerAdapter adapter = getAdapter();
        return (adapter != null && (count = adapter.getCount()) > 0) ? i2 % count : i2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View.OnTouchListener onTouchListener;
        if (motionEvent.getAction() == 0 && (onTouchListener = this.onTouchListener) != null) {
            onTouchListener.onTouch(null, motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getRealCount() {
        if (getAdapter() == null) {
            return 0;
        }
        return getAdapter().getCount();
    }

    public void init(ViewGroup viewGroup, boolean z) {
        this.parent = viewGroup;
        this.isCarousel = z;
        super.setOnTouchListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.LoopViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (this.mFirstLayout) {
            super.onAttachedToWindow();
        }
        this.mFirstLayout = false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.mOnTouchFlag) {
            return onTouchEvent(motionEvent);
        }
        View.OnTouchListener onTouchListener = this.onTouchListener;
        if (onTouchListener != null) {
            onTouchListener.onTouch(view, motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 1 && action != 3) {
            ViewGroup viewGroup = this.parent;
            if (viewGroup != null) {
                viewGroup.requestDisallowInterceptTouchEvent(true);
            }
        } else {
            ViewGroup viewGroup2 = this.parent;
            if (viewGroup2 != null) {
                viewGroup2.requestDisallowInterceptTouchEvent(false);
            }
            View.OnTouchListener onTouchListener2 = this.onTouchListener;
            if (onTouchListener2 != null) {
                onTouchListener2.onTouch(view, motionEvent);
            }
        }
        try {
            onTouchEvent(motionEvent);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
        return true;
    }

    @Override // androidx.viewpager.widget.LoopViewPager
    public void setAdapter(PagerAdapter pagerAdapter) {
        super.setAdapter(pagerAdapter);
        if (pagerAdapter == null || !this.isCarousel || pagerAdapter.getCount() <= 1) {
            return;
        }
        setCurrentItem(0, false);
    }

    @Override // androidx.viewpager.widget.LoopViewPager
    public void setCurrentItem(int i2, boolean z) {
        if (OKLog.D) {
            OKLog.d(TAG, "setCurrentItem " + i2);
        }
        super.setCurrentItem(getRealItem(i2), z);
    }

    public void setOnTouchFlag(boolean z) {
        this.mOnTouchFlag = z;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Deprecated
    public int toRealPosition(int i2) {
        return i2;
    }

    @Override // androidx.viewpager.widget.LoopViewPager
    public void setCurrentItem(int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, "setCurrentItem " + i2);
        }
        super.setCurrentItem(getRealItem(i2));
    }

    public CarouselFigureViewPager2(Context context) {
        super(context);
        this.mFirstLayout = true;
        this.isCarousel = false;
        this.mOnTouchFlag = false;
    }
}
