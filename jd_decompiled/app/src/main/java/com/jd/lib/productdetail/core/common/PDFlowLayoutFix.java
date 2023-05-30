package com.jd.lib.productdetail.core.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jd.lib.productdetail.core.utils.PDUtils;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PDFlowLayoutFix extends ViewGroup {
    private int curRowNum;
    private int mHeight;
    private int maxRows;
    private int padH;
    private int padV;
    private ArrayList<Integer> rowHeight;
    private RowNumListener rowNumListener;

    /* loaded from: classes15.dex */
    public interface RowNumListener {
        void overflow(int i2);
    }

    public PDFlowLayoutFix(Context context) {
        super(context);
        this.maxRows = -1;
        this.padH = PDUtils.dip2px(15.0f);
        this.padV = PDUtils.dip2px(15.0f);
        this.curRowNum = 0;
        this.rowHeight = new ArrayList<>();
    }

    public int getCurRowNum() {
        return this.curRowNum;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        RowNumListener rowNumListener;
        int i6;
        int i7;
        this.rowHeight.clear();
        int i8 = i4 - i2;
        int paddingLeft = (i8 - getPaddingLeft()) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                if (i10 + measuredWidth > paddingLeft) {
                    this.rowHeight.add(Integer.valueOf(i9));
                    i9 = measuredHeight;
                    i10 = 0;
                } else {
                    i9 = Math.max(measuredHeight, i9);
                }
                i10 += measuredWidth + this.padH;
            }
        }
        this.rowHeight.add(Integer.valueOf(i9));
        int paddingLeft2 = getPaddingLeft();
        int i12 = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt2 = getChildAt(i13);
            if (childAt2.getVisibility() != 8) {
                int measuredWidth2 = childAt2.getMeasuredWidth();
                int measuredHeight2 = childAt2.getMeasuredHeight();
                if (paddingLeft2 + measuredWidth2 > i8 - getPaddingRight()) {
                    try {
                        i6 = this.rowHeight.get(i12).intValue();
                    } catch (Exception unused) {
                        i6 = measuredHeight2;
                    }
                    paddingTop += i6 + this.padV;
                    i12++;
                    paddingLeft2 = getPaddingLeft();
                }
                int i14 = this.maxRows;
                if (i14 != -1 && i12 >= i14) {
                    return;
                }
                try {
                    i7 = this.rowHeight.get(i12).intValue() - measuredHeight2;
                } catch (Exception unused2) {
                    i7 = 0;
                }
                childAt2.layout(paddingLeft2, paddingTop + i7, paddingLeft2 + measuredWidth2, measuredHeight2 + paddingTop + i7);
                paddingLeft2 += measuredWidth2 + this.padH;
            }
        }
        if (i12 < this.maxRows || (rowNumListener = this.rowNumListener) == null) {
            return;
        }
        rowNumListener.overflow(i12);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0095, code lost:
        if ((r4 + r13) < r1) goto L19;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r12, int r13) {
        /*
            r11 = this;
            int r0 = android.view.View.MeasureSpec.getSize(r12)
            int r1 = r11.getPaddingLeft()
            int r0 = r0 - r1
            int r1 = r11.getPaddingRight()
            int r0 = r0 - r1
            int r1 = android.view.View.MeasureSpec.getSize(r13)
            int r2 = r11.getPaddingTop()
            int r1 = r1 - r2
            int r2 = r11.getPaddingBottom()
            int r1 = r1 - r2
            int r2 = r11.getChildCount()
            int r3 = r11.getPaddingLeft()
            int r4 = r11.getPaddingTop()
            r5 = 0
            r11.mHeight = r5
            r11.curRowNum = r5
        L2d:
            if (r5 >= r2) goto L7e
            android.view.View r6 = r11.getChildAt(r5)
            int r7 = r6.getVisibility()
            r8 = 8
            if (r7 == r8) goto L7b
            r11.measureChild(r6, r12, r13)
            int r7 = r6.getMeasuredWidth()
            int r8 = r3 + r7
            int r9 = r11.getPaddingLeft()
            int r9 = r9 + r0
            if (r8 <= r9) goto L6b
            int r8 = r11.curRowNum
            int r8 = r8 + 1
            r11.curRowNum = r8
            int r9 = r11.maxRows
            r10 = -1
            if (r9 == r10) goto L5a
            if (r9 == r10) goto L77
            if (r8 >= r9) goto L77
        L5a:
            int r3 = r11.getPaddingLeft()
            int r8 = r11.mHeight
            int r9 = r11.padV
            int r8 = r8 + r9
            int r4 = r4 + r8
            int r6 = r6.getMeasuredHeight()
            r11.mHeight = r6
            goto L77
        L6b:
            int r8 = r11.mHeight
            int r6 = r6.getMeasuredHeight()
            int r6 = java.lang.Math.max(r8, r6)
            r11.mHeight = r6
        L77:
            int r6 = r11.padH
            int r7 = r7 + r6
            int r3 = r3 + r7
        L7b:
            int r5 = r5 + 1
            goto L2d
        L7e:
            int r0 = android.view.View.MeasureSpec.getMode(r13)
            if (r0 != 0) goto L89
            int r13 = r11.mHeight
        L86:
            int r1 = r4 + r13
            goto L98
        L89:
            int r13 = android.view.View.MeasureSpec.getMode(r13)
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r13 != r0) goto L98
            int r13 = r11.mHeight
            int r0 = r4 + r13
            if (r0 >= r1) goto L98
            goto L86
        L98:
            int r12 = android.view.View.MeasureSpec.getSize(r12)
            int r13 = r11.getPaddingBottom()
            int r1 = r1 + r13
            r11.setMeasuredDimension(r12, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.core.common.PDFlowLayoutFix.onMeasure(int, int):void");
    }

    public void setCurRowNum(int i2) {
        this.curRowNum = i2;
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

    public PDFlowLayoutFix(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxRows = -1;
        this.padH = PDUtils.dip2px(15.0f);
        this.padV = PDUtils.dip2px(15.0f);
        this.curRowNum = 0;
        this.rowHeight = new ArrayList<>();
    }
}
