package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: classes6.dex */
public class JDGridView extends GridView {
    private boolean isOnMeasure;

    public JDGridView(Context context) {
        super(context);
        this.isOnMeasure = false;
    }

    public boolean isOnMeasure() {
        return this.isOnMeasure;
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.isOnMeasure = false;
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE);
        this.isOnMeasure = true;
        super.onMeasure(i2, makeMeasureSpec);
    }

    public JDGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isOnMeasure = false;
    }

    public JDGridView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isOnMeasure = false;
    }
}
