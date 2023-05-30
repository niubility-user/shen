package com.jd.lib.productdetail.tradein.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.jd.lib.productdetail.tradein.R;

/* loaded from: classes16.dex */
public class TradeInMaxHeightScrollView extends ScrollView {

    /* renamed from: g  reason: collision with root package name */
    public int f5635g;

    public TradeInMaxHeightScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.tradein_max_height_scrollview);
        this.f5635g = obtainStyledAttributes.getLayoutDimension(R.styleable.tradein_max_height_scrollview_maxHeight, this.f5635g);
        obtainStyledAttributes.recycle();
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4 = this.f5635g;
        if (i4 > 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
        }
        super.onMeasure(i2, i3);
    }

    public TradeInMaxHeightScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
    }
}
