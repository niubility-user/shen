package com.jingdong.common.widget.popupwindow.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.jingdong.common.DpiUtil;

/* loaded from: classes12.dex */
public class CircleButtonLayout extends LinearLayout {
    public CircleButtonLayout(Context context) {
        super(context);
    }

    public void notifyData(BaseAdapter baseAdapter) {
        setAdapter(baseAdapter);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int width = (DpiUtil.getWidth(getContext()) - ((DpiUtil.dip2px(getContext(), 65.0f) * childCount) + (DpiUtil.dip2px(getContext(), 40.0f) * (childCount - 1)))) / 2;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            int dip2px = (DpiUtil.dip2px(getContext(), 65.0f) * i6) + width + (DpiUtil.dip2px(getContext(), 40.0f) * i6);
            childAt.layout(dip2px, 0, childAt.getMeasuredWidth() + dip2px, childAt.getMeasuredHeight());
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        measureChildren(i2, i3);
        int width = DpiUtil.getWidth(getContext());
        int dip2px = DpiUtil.dip2px(getContext(), 65.0f);
        if (mode != 1073741824) {
            size = width;
        }
        if (mode2 != 1073741824) {
            size2 = dip2px;
        }
        setMeasuredDimension(size, size2);
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        int count;
        if (baseAdapter == null || (count = baseAdapter.getCount()) == 0) {
            return;
        }
        removeAllViews();
        for (int i2 = 0; i2 < count; i2++) {
            addView(baseAdapter.getView(i2, null, this));
        }
    }

    public CircleButtonLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircleButtonLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
