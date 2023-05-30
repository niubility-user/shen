package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jd.lib.productdetail.core.utils.PDUtils;

/* loaded from: classes15.dex */
public class PdMCommentLabelFlowLayout extends ViewGroup {

    /* renamed from: m  reason: collision with root package name */
    public static final /* synthetic */ boolean f4986m = true;

    /* renamed from: g  reason: collision with root package name */
    public int f4987g;

    /* renamed from: h  reason: collision with root package name */
    public int f4988h;

    /* renamed from: i  reason: collision with root package name */
    public int f4989i;

    /* renamed from: j  reason: collision with root package name */
    public int f4990j;

    /* renamed from: k  reason: collision with root package name */
    public int f4991k;

    /* renamed from: l  reason: collision with root package name */
    public a f4992l;

    /* loaded from: classes15.dex */
    public interface a {
        void a(int i2);
    }

    public PdMCommentLabelFlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4987g = -1;
        this.f4988h = PDUtils.dip2px(15.0f);
        this.f4989i = PDUtils.dip2px(15.0f);
        this.f4991k = 0;
    }

    public void a(int i2) {
        this.f4987g = i2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        a aVar;
        int paddingLeft = ((i4 - i2) - getPaddingLeft()) - getPaddingRight();
        int paddingLeft2 = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i6 = 0;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int i8 = this.f4987g;
                if (i8 != -1 && i6 >= i8) {
                    childAt.setVisibility(8);
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (paddingLeft2 + measuredWidth > paddingLeft) {
                        i6++;
                        paddingLeft2 = getPaddingLeft();
                        paddingTop += this.f4990j;
                    }
                    int i9 = this.f4987g;
                    if (i9 != -1 && i6 >= i9) {
                        childAt.setVisibility(8);
                    } else {
                        int i10 = this.f4990j - (this.f4989i + measuredHeight);
                        childAt.layout(paddingLeft2, paddingTop + i10, paddingLeft2 + measuredWidth, measuredHeight + paddingTop + i10);
                        paddingLeft2 += measuredWidth + this.f4988h;
                    }
                }
            }
        }
        if (i6 < this.f4987g || (aVar = this.f4992l) == null) {
            return;
        }
        aVar.a(i6);
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int makeMeasureSpec;
        int i4;
        if (!f4986m && View.MeasureSpec.getMode(i2) == 0) {
            throw new AssertionError();
        }
        int size = (View.MeasureSpec.getSize(i2) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i3) - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        this.f4990j = 0;
        this.f4991k = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), makeMeasureSpec);
                int measuredWidth = childAt.getMeasuredWidth();
                this.f4990j = Math.max(this.f4990j, childAt.getMeasuredHeight() + this.f4989i);
                if (paddingLeft + measuredWidth > size) {
                    int i6 = this.f4991k + 1;
                    this.f4991k = i6;
                    int i7 = this.f4987g;
                    if (i7 == -1 || (i7 != -1 && i6 < i7)) {
                        paddingLeft = getPaddingLeft();
                        paddingTop += this.f4990j;
                    }
                }
                paddingLeft += measuredWidth + this.f4988h;
            }
        }
        if (View.MeasureSpec.getMode(i3) == 0) {
            size2 = paddingTop + this.f4990j;
        } else if (View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE && (i4 = paddingTop + this.f4990j) < size2) {
            size2 = i4;
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i2), size2 + getPaddingBottom());
    }
}
