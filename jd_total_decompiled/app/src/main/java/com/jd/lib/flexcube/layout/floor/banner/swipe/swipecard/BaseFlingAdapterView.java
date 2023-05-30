package com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;

/* loaded from: classes14.dex */
abstract class BaseFlingAdapterView extends AdapterView {

    /* renamed from: g  reason: collision with root package name */
    private int f4340g;

    /* renamed from: h  reason: collision with root package name */
    private int f4341h;

    public BaseFlingAdapterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int a() {
        return this.f4340g;
    }

    public int b() {
        return this.f4341h;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.f4341h = i2;
        this.f4340g = i3;
    }

    @Override // android.widget.AdapterView
    public void setSelection(int i2) {
        throw new UnsupportedOperationException("Not supported");
    }

    public BaseFlingAdapterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
