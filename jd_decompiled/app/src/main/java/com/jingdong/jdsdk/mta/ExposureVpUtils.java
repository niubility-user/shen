package com.jingdong.jdsdk.mta;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Keep;
import androidx.viewpager.widget.ViewPager;

@Keep
/* loaded from: classes14.dex */
public class ExposureVpUtils {
    private static final float LEFT = 0.0f;
    private static final float RIGHT = 1.0f;
    private VpListener mListener;
    private ViewPager mViewPager;
    private long mStartScrollTime = 0;
    private long mEndScrollTime = 0;
    private int mBeforePosition = 0;
    private int mCurPosition = 0;
    private boolean mTouchViewState = false;
    private int mOffsetPixels = 0;

    @Keep
    /* loaded from: classes14.dex */
    public interface VpListener {
        void onFinish(int i2, int i3, boolean z, long j2, long j3, float f2, float f3, float f4, float f5, float f6);
    }

    /* loaded from: classes14.dex */
    class a implements View.OnTouchListener {
        a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 2) {
                ExposureVpUtils.this.mTouchViewState = true;
                return false;
            }
            return false;
        }
    }

    public ExposureVpUtils(ViewPager viewPager, VpListener vpListener) {
        this.mViewPager = viewPager;
        this.mListener = vpListener;
        if (viewPager != null) {
            viewPager.setOnTouchListener(new a());
        }
    }

    public boolean isHalfPercentVisible(int i2) {
        ViewPager viewPager = this.mViewPager;
        if (viewPager == null || i2 >= viewPager.getChildCount() || this.mViewPager.getChildAt(i2) == null) {
            return false;
        }
        View childAt = this.mViewPager.getChildAt(i2);
        Rect rect = new Rect();
        if (childAt.getGlobalVisibleRect(rect)) {
            double measuredWidth = childAt.getMeasuredWidth();
            Double.isNaN(measuredWidth);
            return ((double) rect.width()) >= measuredWidth * 0.5d;
        }
        return false;
    }

    public void listen(Context context, int i2, int i3) {
        if (context == null) {
            return;
        }
        if (i3 != 0 && this.mStartScrollTime == 0) {
            this.mStartScrollTime = System.currentTimeMillis();
            if (this.mOffsetPixels < i3) {
                this.mOffsetPixels = i3;
            }
        } else if (i3 == 0) {
            this.mEndScrollTime = System.currentTimeMillis();
            int i4 = this.mCurPosition;
            if (i4 != i2) {
                this.mBeforePosition = i4;
                this.mCurPosition = i2;
                this.mViewPager.getLocationOnScreen(new int[2]);
                float abs = Math.abs(this.mCurPosition - this.mBeforePosition) * this.mOffsetPixels;
                long j2 = this.mEndScrollTime;
                long j3 = this.mStartScrollTime;
                float f2 = j2 - j3 != 0 ? abs / ((float) (j2 - j3)) : 0.0f;
                int i5 = this.mCurPosition;
                int i6 = this.mBeforePosition;
                float f3 = i5 > i6 ? 1.0f : 0.0f;
                VpListener vpListener = this.mListener;
                if (vpListener != null) {
                    vpListener.onFinish(i6, i5, this.mTouchViewState, j3, j2, abs, f2, r1[0], r1[1], f3);
                }
                this.mStartScrollTime = 0L;
                this.mEndScrollTime = 0L;
                this.mOffsetPixels = 0;
                this.mTouchViewState = false;
            }
        } else if (this.mOffsetPixels < i3) {
            this.mOffsetPixels = i3;
        }
    }
}
