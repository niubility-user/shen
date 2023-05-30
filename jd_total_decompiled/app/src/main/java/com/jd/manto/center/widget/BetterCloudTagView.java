package com.jd.manto.center.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jingdong.common.tagcloud.CloudTagConfiguration;

/* loaded from: classes17.dex */
public class BetterCloudTagView extends ViewGroup {

    /* renamed from: g  reason: collision with root package name */
    private int f6554g;

    /* renamed from: h  reason: collision with root package name */
    private int f6555h;

    /* renamed from: i  reason: collision with root package name */
    private int f6556i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f6557j;

    /* renamed from: k  reason: collision with root package name */
    private int f6558k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f6559l;

    /* renamed from: m  reason: collision with root package name */
    private int f6560m;

    /* renamed from: n  reason: collision with root package name */
    private int f6561n;
    private BaseAdapter o;
    private a p;

    /* loaded from: classes17.dex */
    class a extends DataSetObserver {
        a() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            BetterCloudTagView.this.b();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    public BetterCloudTagView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        BaseAdapter baseAdapter = this.o;
        if (baseAdapter == null || baseAdapter.getCount() == 0) {
            return;
        }
        removeAllViews();
        this.f6561n = 0;
        for (int i2 = 0; i2 < this.o.getCount(); i2++) {
            addView(this.o.getView(i2, null, null));
        }
    }

    private void e(Context context, AttributeSet attributeSet, int i2) {
        CloudTagConfiguration cloudTagConfiguration = new CloudTagConfiguration(context, attributeSet);
        this.f6554g = cloudTagConfiguration.getLineSpacing();
        this.f6555h = cloudTagConfiguration.getTagSpacing();
        this.f6556i = cloudTagConfiguration.getColumnSize();
        this.f6557j = cloudTagConfiguration.isRight();
    }

    public int c() {
        return this.f6561n;
    }

    public boolean d() {
        return this.f6559l;
    }

    public void f(BaseAdapter baseAdapter) {
        if (this.o == null) {
            this.o = baseAdapter;
            if (this.p == null) {
                a aVar = new a();
                this.p = aVar;
                this.o.registerDataSetObserver(aVar);
            }
            b();
        }
    }

    public void g(boolean z) {
        this.f6559l = z;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.LayoutParams(getContext(), attributeSet);
    }

    public void h(int i2) {
        this.f6560m = i2;
    }

    public void i(int i2) {
        this.f6556i = i2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int i7 = this.f6558k + paddingLeft;
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                i8 = Math.max(measuredHeight, i8);
                if (i7 + measuredWidth + paddingRight > i6) {
                    paddingTop += this.f6554g + i8;
                    i7 = paddingLeft;
                    i8 = measuredHeight;
                }
                childAt.layout(i7, paddingTop, i7 + measuredWidth, measuredHeight + paddingTop);
                i7 += measuredWidth + this.f6555h;
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int resolveSize = ViewGroup.resolveSize(0, i2);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i4 = paddingLeft;
        int i5 = paddingTop;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            if (i6 >= getChildCount()) {
                break;
            }
            View childAt = getChildAt(i6);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            int i9 = paddingLeft;
            childAt.measure(ViewGroup.getChildMeasureSpec(i2, paddingLeft + paddingRight, layoutParams.width), ViewGroup.getChildMeasureSpec(i3, paddingTop + paddingBottom, layoutParams.height));
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredWidth = childAt.getMeasuredWidth();
            i7 = Math.max(measuredHeight, i7);
            int i10 = i6 + 1;
            this.f6561n = i10;
            if (i4 + measuredWidth + paddingRight > resolveSize) {
                i8++;
                if (!this.f6559l && i8 > this.f6560m - 1) {
                    this.f6561n = i6;
                    break;
                }
                int i11 = this.f6556i;
                if (i11 > 0 && i8 == i11) {
                    this.f6561n = i6;
                    break;
                }
                i5 += this.f6554g + measuredHeight;
                i7 = measuredHeight;
                i4 = i9;
            }
            i4 += measuredWidth + this.f6555h;
            i6 = i10;
            paddingLeft = i9;
        }
        if (this.f6556i == 1 && this.f6557j) {
            this.f6558k = resolveSize - i4;
        }
        setMeasuredDimension(resolveSize, ViewGroup.resolveSize(0 + i5 + i7 + paddingBottom, i3));
    }

    public BetterCloudTagView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f6559l = true;
        this.f6560m = 2;
        e(context, attributeSet, i2);
    }
}
