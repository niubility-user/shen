package com.jd.lib.productdetail.core.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes15.dex */
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

    /* loaded from: classes15.dex */
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

    /* JADX WARN: Code restructure failed: missing block: B:53:0x0115, code lost:
        if ((r5 + r1) < r2) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e3  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onMeasure(int i2, int i3) {
        int makeMeasureSpec;
        int i4;
        RowNumListener rowNumListener;
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
        this.mHeight = 0;
        this.curRowNum = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                if (this.assignChildWidth && childAt.getLayoutParams() != null) {
                    measureChildHorizontal(childAt, childAt.getLayoutParams(), i2, size2);
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), makeMeasureSpec);
                }
                int measuredWidth = childAt.getMeasuredWidth();
                this.mHeight = Math.max(this.mHeight, childAt.getMeasuredHeight() + this.padV);
                boolean z = true;
                if (paddingLeft + measuredWidth > size) {
                    int i6 = this.curRowNum + 1;
                    this.curRowNum = i6;
                    int i7 = this.maxRows;
                    if (i7 == -1 || (i7 != -1 && i6 < i7)) {
                        paddingLeft = getPaddingLeft();
                        paddingTop += this.mHeight;
                    }
                }
                if (i5 == this.checkIndex) {
                    if (Log.D) {
                        Log.d("checkIndex", "--" + this.checkIndex + "--" + this.curRowNum + "--" + this.maxRows);
                    }
                    int i8 = this.curRowNum;
                    if (i8 >= this.defaultRows) {
                        this.autoExpan = true;
                        rowNumListener = this.rowNumListener;
                        if (rowNumListener != null) {
                            if (this.defaultRows == Integer.MAX_VALUE) {
                                this.autoExpan = false;
                            }
                            rowNumListener.overflow(this.curRowNum, z, this.autoExpan);
                        }
                        paddingLeft += measuredWidth + this.padH;
                    } else if (i8 >= this.maxRows) {
                        this.autoExpan = true;
                    } else {
                        this.autoExpan = false;
                    }
                } else {
                    this.autoExpan = false;
                }
                z = false;
                rowNumListener = this.rowNumListener;
                if (rowNumListener != null) {
                }
                paddingLeft += measuredWidth + this.padH;
            }
        }
        if (View.MeasureSpec.getMode(i3) == 0) {
            i4 = this.mHeight;
        } else {
            if (View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE) {
                i4 = this.mHeight;
            }
            setMeasuredDimension(View.MeasureSpec.getSize(i2), size2 + getPaddingBottom());
        }
        size2 = paddingTop + i4;
        setMeasuredDimension(View.MeasureSpec.getSize(i2), size2 + getPaddingBottom());
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
