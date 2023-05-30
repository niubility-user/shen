package com.jingdong.app.mall.bundle.styleinfoview.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;

/* loaded from: classes3.dex */
public class FlowLayout extends ViewGroup {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean assignChildWidth;
    private boolean autoExpan;
    private int checkIndex;
    private int curRowNum;
    private int defaultRows;
    private int mHeight;
    private int maxRows;
    private int padH;
    private int padV;
    private RowNumListener rowNumListener;

    /* loaded from: classes3.dex */
    public interface RowNumListener {
        void overflow(int i2, boolean z, boolean z2);
    }

    public FlowLayout(Context context) {
        super(context);
        this.maxRows = -1;
        this.padH = PDUtils.dip2px(15.0f);
        this.padV = PDUtils.dip2px(15.0f);
        this.curRowNum = 0;
        this.assignChildWidth = false;
        this.checkIndex = -1;
        this.autoExpan = false;
    }

    private void measureChildHorizontal(View view, ViewGroup.LayoutParams layoutParams, int i2, int i3) {
        int makeMeasureSpec;
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, 0, layoutParams.width);
        if (i3 < 0) {
            int i4 = layoutParams.height;
            if (i4 >= 0) {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
            } else {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, i3), layoutParams.width != -1 ? Integer.MIN_VALUE : 1073741824);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
    }

    public int getCurRowNum() {
        return this.curRowNum;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int paddingLeft = ((i4 - i2) - getPaddingLeft()) - getPaddingRight();
        int paddingLeft2 = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i6 = 0;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (paddingLeft2 + measuredWidth > paddingLeft) {
                    i6++;
                    paddingLeft2 = getPaddingLeft();
                    paddingTop += this.mHeight;
                }
                int i8 = this.maxRows;
                if (i8 != -1 && i6 >= i8) {
                    return;
                }
                childAt.layout(paddingLeft2, paddingTop, paddingLeft2 + measuredWidth, measuredHeight + paddingTop);
                paddingLeft2 += measuredWidth + this.padH;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00e3  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.styleinfoview.common.FlowLayout.onMeasure(int, int):void");
    }

    public void setAssignChildWidth(boolean z) {
        this.assignChildWidth = z;
    }

    public void setCheckIndex(int i2) {
        this.checkIndex = i2;
    }

    public void setCurRowNum(int i2) {
        this.curRowNum = i2;
    }

    public void setDefaultRows(int i2) {
        this.defaultRows = i2;
        this.autoExpan = false;
    }

    public void setMaxRows(int i2) {
        this.maxRows = i2;
    }

    public void setRowNumListener(RowNumListener rowNumListener) {
        this.rowNumListener = rowNumListener;
    }

    public void setSpace(int i2, int i3) {
        this.padH = i2;
        this.padV = i3;
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxRows = -1;
        this.padH = PDUtils.dip2px(15.0f);
        this.padV = PDUtils.dip2px(15.0f);
        this.curRowNum = 0;
        this.assignChildWidth = false;
        this.checkIndex = -1;
        this.autoExpan = false;
    }
}
