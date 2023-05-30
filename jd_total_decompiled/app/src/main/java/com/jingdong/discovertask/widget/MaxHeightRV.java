package com.jingdong.discovertask.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes12.dex */
public class MaxHeightRV extends RecyclerView {
    public static final String TAG = "MaxHeightRV";
    private int mMaxHeight;

    public MaxHeightRV(@NonNull Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4 = this.mMaxHeight;
        if (i4 > 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
        }
        super.onMeasure(i2, i3);
    }

    public void setMaxHeight(int i2) {
        this.mMaxHeight = i2;
        String str = "setMaxHeight: maxHeight = " + i2;
    }

    public MaxHeightRV(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MaxHeightRV(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
