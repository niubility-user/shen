package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import com.jingdong.common.UnLog;

/* loaded from: classes6.dex */
public class JDListView extends ListView {
    private static final String TAG = "JDListView";
    private boolean isOnMeasure;

    public JDListView(Context context) {
        super(context);
        this.isOnMeasure = false;
    }

    public boolean isOnMeasure() {
        return this.isOnMeasure;
    }

    @Override // android.widget.ListView, android.widget.AbsListView
    protected void layoutChildren() {
        try {
            super.layoutChildren();
        } catch (Exception e2) {
            UnLog.e(TAG, e2.getMessage());
        }
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        try {
            this.isOnMeasure = false;
            super.onLayout(z, i2, i3, i4, i5);
        } catch (Exception e2) {
            UnLog.e(TAG, e2.getMessage());
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE);
        try {
            this.isOnMeasure = true;
            super.onMeasure(i2, makeMeasureSpec);
        } catch (Exception unused) {
            super.onMeasure(i2, makeMeasureSpec);
        }
    }

    public JDListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isOnMeasure = false;
    }

    public JDListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isOnMeasure = false;
    }
}
