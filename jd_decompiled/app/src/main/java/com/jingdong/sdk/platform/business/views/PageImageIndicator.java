package com.jingdong.sdk.platform.business.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.jingdong.sdk.platform.business.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public class PageImageIndicator extends LinearLayout {
    private int mCount;
    private int mIndicatorSpacing;
    private int mPoint;
    private int mResSelected;
    private int mResUnselected;

    public PageImageIndicator(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        resolveAttr(context, attributeSet);
    }

    private List<View> copyChildViews() {
        ArrayList arrayList = new ArrayList();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            arrayList.add(getChildAt(i2));
        }
        return arrayList;
    }

    private void resolveAttr(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PageImageIndicator);
        this.mCount = obtainStyledAttributes.getInt(R.styleable.PageImageIndicator_pageCount, 5);
        this.mResSelected = obtainStyledAttributes.getResourceId(R.styleable.PageImageIndicator_resSelected, R.drawable.platform_res_select_indictor);
        this.mResUnselected = obtainStyledAttributes.getResourceId(R.styleable.PageImageIndicator_resUnselected, R.drawable.platform_res_unselect_indictor);
        this.mPoint = obtainStyledAttributes.getInt(R.styleable.PageImageIndicator_pageIndex, 0);
        this.mIndicatorSpacing = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PageImageIndicator_indicatorSpacing, 0);
        update();
        obtainStyledAttributes.recycle();
    }

    private void update() {
        List<View> copyChildViews = copyChildViews();
        removeAllViews();
        if (this.mCount > 0) {
            for (int i2 = 0; i2 < this.mCount; i2++) {
                if (i2 < copyChildViews.size()) {
                    addView((ImageView) copyChildViews.get(i2), new LinearLayout.LayoutParams(-2, -2));
                } else {
                    ImageView imageView = new ImageView(getContext());
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.weight = 1.0f;
                    addView(imageView, layoutParams);
                }
                ImageView imageView2 = (ImageView) getChildAt(i2);
                if (i2 == 0) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) imageView2.getLayoutParams();
                    layoutParams2.leftMargin = 0;
                    layoutParams2.rightMargin = 0;
                } else {
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) imageView2.getLayoutParams();
                    layoutParams3.leftMargin = this.mIndicatorSpacing;
                    layoutParams3.rightMargin = 0;
                }
                if (i2 == this.mPoint) {
                    imageView2.setImageResource(this.mResSelected);
                } else {
                    imageView2.setImageResource(this.mResUnselected);
                }
            }
        }
    }

    public void setPageCount(int i2) {
        this.mCount = i2;
        update();
    }

    public void setPageIndex(int i2) {
        this.mPoint = i2;
        update();
    }

    public void setResSelected(int i2) {
        this.mResSelected = i2;
        update();
    }

    public void setResUnselected(int i2) {
        this.mResUnselected = i2;
        update();
    }

    public PageImageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        resolveAttr(context, attributeSet);
    }

    public PageImageIndicator(Context context) {
        super(context);
        resolveAttr(context, null);
    }
}
