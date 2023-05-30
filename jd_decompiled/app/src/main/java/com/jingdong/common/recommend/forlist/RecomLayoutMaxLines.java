package com.jingdong.common.recommend.forlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecomLayoutMaxLines extends ViewGroup {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int curRowNum;
    private int mHeight;
    private int maxRows;
    private int padH;
    private int padV;
    private ArrayList<Integer> rowHeight;

    public RecomLayoutMaxLines(Context context) {
        super(context);
        this.maxRows = -1;
        this.padH = DPIUtil.dip2px(5.0f);
        this.padV = DPIUtil.dip2px(5.0f);
        this.curRowNum = 0;
        this.rowHeight = new ArrayList<>();
    }

    public int getCurRowNum() {
        return this.curRowNum;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
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
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x009a, code lost:
        if ((r4 + r15) < r1) goto L22;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r14, int r15) {
        /*
            r13 = this;
            int r0 = android.view.View.MeasureSpec.getSize(r14)
            int r1 = r13.getPaddingLeft()
            int r0 = r0 - r1
            int r1 = r13.getPaddingRight()
            int r0 = r0 - r1
            int r1 = android.view.View.MeasureSpec.getSize(r15)
            int r2 = r13.getPaddingTop()
            int r1 = r1 - r2
            int r2 = r13.getPaddingBottom()
            int r1 = r1 - r2
            int r2 = r13.getChildCount()
            int r3 = r13.getPaddingLeft()
            int r4 = r13.getPaddingTop()
            r5 = 0
            r13.mHeight = r5
            r13.curRowNum = r5
            r6 = 0
        L2e:
            if (r6 >= r2) goto L83
            android.view.View r7 = r13.getChildAt(r6)
            int r8 = r7.getVisibility()
            r9 = 8
            if (r8 == r9) goto L80
            r13.measureChild(r7, r14, r15)
            int r8 = r7.getMeasuredWidth()
            int r9 = r3 + r8
            int r10 = r13.getPaddingLeft()
            int r10 = r10 + r0
            if (r9 <= r10) goto L70
            int r9 = r13.curRowNum
            r10 = 1
            int r9 = r9 + r10
            r13.curRowNum = r9
            int r11 = r13.maxRows
            r12 = -1
            if (r11 == r12) goto L5a
            if (r9 >= r11) goto L5a
            goto L5b
        L5a:
            r10 = 0
        L5b:
            if (r11 == r12) goto L5f
            if (r10 == 0) goto L7c
        L5f:
            int r3 = r13.getPaddingLeft()
            int r9 = r13.mHeight
            int r10 = r13.padV
            int r9 = r9 + r10
            int r4 = r4 + r9
            int r7 = r7.getMeasuredHeight()
            r13.mHeight = r7
            goto L7c
        L70:
            int r9 = r13.mHeight
            int r7 = r7.getMeasuredHeight()
            int r7 = java.lang.Math.max(r9, r7)
            r13.mHeight = r7
        L7c:
            int r7 = r13.padH
            int r8 = r8 + r7
            int r3 = r3 + r8
        L80:
            int r6 = r6 + 1
            goto L2e
        L83:
            int r0 = android.view.View.MeasureSpec.getMode(r15)
            if (r0 != 0) goto L8e
            int r15 = r13.mHeight
        L8b:
            int r1 = r4 + r15
            goto L9d
        L8e:
            int r15 = android.view.View.MeasureSpec.getMode(r15)
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r15 != r0) goto L9d
            int r15 = r13.mHeight
            int r0 = r4 + r15
            if (r0 >= r1) goto L9d
            goto L8b
        L9d:
            int r14 = android.view.View.MeasureSpec.getSize(r14)
            int r15 = r13.getPaddingBottom()
            int r1 = r1 + r15
            r13.setMeasuredDimension(r14, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.forlist.RecomLayoutMaxLines.onMeasure(int, int):void");
    }

    public void setCurRowNum(int i2) {
        this.curRowNum = i2;
    }

    public void setMaxRows(int i2) {
        this.maxRows = i2;
    }

    public void setSpace(int i2, int i3) {
        this.padH = i2;
        this.padV = i3;
    }

    public RecomLayoutMaxLines(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxRows = -1;
        this.padH = DPIUtil.dip2px(5.0f);
        this.padV = DPIUtil.dip2px(5.0f);
        this.curRowNum = 0;
        this.rowHeight = new ArrayList<>();
    }
}
