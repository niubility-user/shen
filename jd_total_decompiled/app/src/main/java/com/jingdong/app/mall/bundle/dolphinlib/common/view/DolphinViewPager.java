package com.jingdong.app.mall.bundle.dolphinlib.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinGoodsGridViewPagerAdapter;
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinSubsidyViewPagerAdapter;

/* loaded from: classes19.dex */
public class DolphinViewPager extends ViewPager {
    private int gridNumColumn;
    private int gridNumRow;
    private int horizontalSpacing;
    private int verticalSpacing;

    public DolphinViewPager(Context context) {
        super(context);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onInterceptTouchEvent(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            if (childAt instanceof RelativeLayout) {
                int i9 = 0;
                while (true) {
                    RelativeLayout relativeLayout = (RelativeLayout) childAt;
                    if (i9 >= relativeLayout.getChildCount()) {
                        break;
                    }
                    View childAt2 = relativeLayout.getChildAt(i9);
                    if (childAt2 instanceof GridView) {
                        childAt = childAt2;
                        break;
                    }
                    i9++;
                }
            }
            childAt.measure(i2, View.MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = childAt.getMeasuredHeight();
            if (measuredHeight > i7) {
                i7 = measuredHeight;
            }
        }
        if (getAdapter() instanceof DolphinGoodsGridViewPagerAdapter) {
            ((DolphinGoodsGridViewPagerAdapter) getAdapter()).syncHeight(i7);
            i4 = this.gridNumRow;
            i5 = i7 * i4;
            i6 = this.verticalSpacing;
        } else {
            if (getAdapter() instanceof DolphinSubsidyViewPagerAdapter) {
                i4 = this.gridNumRow;
                i5 = i7 * i4;
                i6 = this.verticalSpacing;
            }
            super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(i7, 1073741824));
        }
        i7 = i5 + (i6 * (i4 - 1));
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(i7, 1073741824));
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void setGridProperty(int i2, int i3, int i4, int i5) {
        this.gridNumColumn = i2;
        this.gridNumRow = i3;
        this.horizontalSpacing = i4;
        this.verticalSpacing = i5;
    }

    public DolphinViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
