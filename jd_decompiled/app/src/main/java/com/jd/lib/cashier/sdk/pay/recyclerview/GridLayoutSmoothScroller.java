package com.jd.lib.cashier.sdk.pay.recyclerview;

import android.content.Context;
import androidx.recyclerview.widget.LinearSmoothScroller;

/* loaded from: classes14.dex */
public class GridLayoutSmoothScroller extends LinearSmoothScroller {
    public GridLayoutSmoothScroller(Context context) {
        super(context);
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    protected int getHorizontalSnapPreference() {
        return -1;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    protected int getVerticalSnapPreference() {
        return -1;
    }
}
