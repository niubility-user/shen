package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.jingdong.sdk.platform.lib.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class FlexboxLayout extends ViewGroup {
    public static final int ALIGN_CONTENT_CENTER = 2;
    public static final int ALIGN_CONTENT_FLEX_END = 1;
    public static final int ALIGN_CONTENT_FLEX_START = 0;
    public static final int ALIGN_CONTENT_SPACE_AROUND = 4;
    public static final int ALIGN_CONTENT_SPACE_BETWEEN = 3;
    public static final int ALIGN_CONTENT_STRETCH = 5;
    public static final int ALIGN_ITEMS_BASELINE = 3;
    public static final int ALIGN_ITEMS_CENTER = 2;
    public static final int ALIGN_ITEMS_FLEX_END = 1;
    public static final int ALIGN_ITEMS_FLEX_START = 0;
    public static final int ALIGN_ITEMS_STRETCH = 4;
    public static final int FLEX_DIRECTION_COLUMN = 2;
    public static final int FLEX_DIRECTION_COLUMN_REVERSE = 3;
    public static final int FLEX_DIRECTION_ROW = 0;
    public static final int FLEX_DIRECTION_ROW_REVERSE = 1;
    public static final int FLEX_WRAP_NOWRAP = 0;
    public static final int FLEX_WRAP_WRAP = 1;
    public static final int FLEX_WRAP_WRAP_REVERSE = 2;
    public static final int JUSTIFY_CONTENT_CENTER = 2;
    public static final int JUSTIFY_CONTENT_FLEX_END = 1;
    public static final int JUSTIFY_CONTENT_FLEX_START = 0;
    public static final int JUSTIFY_CONTENT_SPACE_AROUND = 4;
    public static final int JUSTIFY_CONTENT_SPACE_BETWEEN = 3;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    private int mAlignContent;
    private int mAlignItems;
    private boolean[] mChildrenFrozen;
    private Drawable mDividerDrawableHorizontal;
    private Drawable mDividerDrawableVertical;
    private int mDividerHorizontalHeight;
    private int mDividerVerticalWidth;
    private int mFlexDirection;
    private List<FlexLine> mFlexLines;
    private int mFlexWrap;
    private int mJustifyContent;
    private SparseIntArray mOrderCache;
    private int[] mReorderedIndices;
    private int mShowDividerHorizontal;
    private int mShowDividerVertical;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AlignContent {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AlignItems {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FlexDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface FlexWrap {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface JustifyContent {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class Order implements Comparable<Order> {
        int index;
        int order;

        private Order() {
        }

        public String toString() {
            return "Order{order=" + this.order + ", index=" + this.index + '}';
        }

        @Override // java.lang.Comparable
        public int compareTo(@NonNull Order order) {
            int i2 = this.order;
            int i3 = order.order;
            return i2 != i3 ? i2 - i3 : this.index - order.index;
        }
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    private void addFlexLine(FlexLine flexLine) {
        if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
            if ((this.mShowDividerVertical & 4) > 0) {
                int i2 = flexLine.mMainSize;
                int i3 = this.mDividerVerticalWidth;
                flexLine.mMainSize = i2 + i3;
                flexLine.mDividerLengthInMainSize += i3;
            }
        } else if ((this.mShowDividerHorizontal & 4) > 0) {
            int i4 = flexLine.mMainSize;
            int i5 = this.mDividerHorizontalHeight;
            flexLine.mMainSize = i4 + i5;
            flexLine.mDividerLengthInMainSize += i5;
        }
        this.mFlexLines.add(flexLine);
    }

    private void addFlexLineIfLastFlexItem(int i2, int i3, FlexLine flexLine) {
        if (i2 != i3 - 1 || flexLine.getItemCountNotGone() == 0) {
            return;
        }
        addFlexLine(flexLine);
    }

    private boolean allFlexLinesAreDummyBefore(int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.mFlexLines.get(i3).getItemCountNotGone() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean allViewsAreGoneBefore(int i2, int i3) {
        for (int i4 = 1; i4 <= i3; i4++) {
            View reorderedChildAt = getReorderedChildAt(i2 - i4);
            if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkSizeConstraints(android.view.View r7) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexboxLayout$LayoutParams r0 = (com.google.android.flexbox.FlexboxLayout.LayoutParams) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r7.getMeasuredWidth()
            int r4 = r0.minWidth
            r5 = 1
            if (r3 >= r4) goto L1a
        L17:
            r1 = r4
            r3 = 1
            goto L24
        L1a:
            int r3 = r7.getMeasuredWidth()
            int r4 = r0.maxWidth
            if (r3 <= r4) goto L23
            goto L17
        L23:
            r3 = 0
        L24:
            int r4 = r0.minHeight
            if (r2 >= r4) goto L2a
            r2 = r4
            goto L31
        L2a:
            int r0 = r0.maxHeight
            if (r2 <= r0) goto L30
            r2 = r0
            goto L31
        L30:
            r5 = r3
        L31:
            if (r5 == 0) goto L40
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
        L40:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.checkSizeConstraints(android.view.View):void");
    }

    @NonNull
    private List<Order> createOrders(int i2) {
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            Order order = new Order();
            order.order = ((LayoutParams) getChildAt(i3).getLayoutParams()).order;
            order.index = i3;
            arrayList.add(order);
        }
        return arrayList;
    }

    private int[] createReorderedIndices(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        int childCount = getChildCount();
        List<Order> createOrders = createOrders(childCount);
        Order order = new Order();
        if (view != null && (layoutParams instanceof LayoutParams)) {
            order.order = ((LayoutParams) layoutParams).order;
        } else {
            order.order = 1;
        }
        if (i2 != -1 && i2 != childCount) {
            if (i2 < getChildCount()) {
                order.index = i2;
                while (i2 < childCount) {
                    createOrders.get(i2).index++;
                    i2++;
                }
            } else {
                order.index = childCount;
            }
        } else {
            order.index = childCount;
        }
        createOrders.add(order);
        return sortOrdersIntoReorderedIndices(childCount + 1, createOrders);
    }

    private void determineCrossSize(int i2, int i3, int i4, int i5) {
        int mode;
        int size;
        if (i2 == 0 || i2 == 1) {
            mode = View.MeasureSpec.getMode(i4);
            size = View.MeasureSpec.getSize(i4);
        } else if (i2 != 2 && i2 != 3) {
            throw new IllegalArgumentException("Invalid flex direction: " + i2);
        } else {
            mode = View.MeasureSpec.getMode(i3);
            size = View.MeasureSpec.getSize(i3);
        }
        if (mode == 1073741824) {
            int sumOfCrossSize = getSumOfCrossSize() + i5;
            int i6 = 0;
            if (this.mFlexLines.size() == 1) {
                this.mFlexLines.get(0).mCrossSize = size - i5;
            } else if (this.mFlexLines.size() < 2 || sumOfCrossSize >= size) {
            } else {
                int i7 = this.mAlignContent;
                if (i7 == 1) {
                    int i8 = size - sumOfCrossSize;
                    FlexLine flexLine = new FlexLine();
                    flexLine.mCrossSize = i8;
                    this.mFlexLines.add(0, flexLine);
                } else if (i7 == 2) {
                    ArrayList arrayList = new ArrayList();
                    FlexLine flexLine2 = new FlexLine();
                    flexLine2.mCrossSize = (size - sumOfCrossSize) / 2;
                    int size2 = this.mFlexLines.size();
                    while (i6 < size2) {
                        if (i6 == 0) {
                            arrayList.add(flexLine2);
                        }
                        arrayList.add(this.mFlexLines.get(i6));
                        if (i6 == this.mFlexLines.size() - 1) {
                            arrayList.add(flexLine2);
                        }
                        i6++;
                    }
                    this.mFlexLines = arrayList;
                } else if (i7 == 3) {
                    float size3 = (size - sumOfCrossSize) / (this.mFlexLines.size() - 1);
                    ArrayList arrayList2 = new ArrayList();
                    int size4 = this.mFlexLines.size();
                    float f2 = 0.0f;
                    while (i6 < size4) {
                        arrayList2.add(this.mFlexLines.get(i6));
                        if (i6 != this.mFlexLines.size() - 1) {
                            FlexLine flexLine3 = new FlexLine();
                            if (i6 == this.mFlexLines.size() - 2) {
                                flexLine3.mCrossSize = Math.round(f2 + size3);
                                f2 = 0.0f;
                            } else {
                                flexLine3.mCrossSize = Math.round(size3);
                            }
                            int i9 = flexLine3.mCrossSize;
                            f2 += size3 - i9;
                            if (f2 > 1.0f) {
                                flexLine3.mCrossSize = i9 + 1;
                                f2 -= 1.0f;
                            } else if (f2 < -1.0f) {
                                flexLine3.mCrossSize = i9 - 1;
                                f2 += 1.0f;
                            }
                            arrayList2.add(flexLine3);
                        }
                        i6++;
                    }
                    this.mFlexLines = arrayList2;
                } else if (i7 == 4) {
                    int size5 = (size - sumOfCrossSize) / (this.mFlexLines.size() * 2);
                    ArrayList arrayList3 = new ArrayList();
                    FlexLine flexLine4 = new FlexLine();
                    flexLine4.mCrossSize = size5;
                    Iterator<FlexLine> it = this.mFlexLines.iterator();
                    while (it.hasNext()) {
                        arrayList3.add(flexLine4);
                        arrayList3.add(it.next());
                        arrayList3.add(flexLine4);
                    }
                    this.mFlexLines = arrayList3;
                } else if (i7 != 5) {
                } else {
                    float size6 = (size - sumOfCrossSize) / this.mFlexLines.size();
                    int size7 = this.mFlexLines.size();
                    float f3 = 0.0f;
                    while (i6 < size7) {
                        FlexLine flexLine5 = this.mFlexLines.get(i6);
                        float f4 = flexLine5.mCrossSize + size6;
                        if (i6 == this.mFlexLines.size() - 1) {
                            f4 += f3;
                            f3 = 0.0f;
                        }
                        int round = Math.round(f4);
                        f3 += f4 - round;
                        if (f3 > 1.0f) {
                            round++;
                            f3 -= 1.0f;
                        } else if (f3 < -1.0f) {
                            round--;
                            f3 += 1.0f;
                        }
                        flexLine5.mCrossSize = round;
                        i6++;
                    }
                }
            }
        }
    }

    private void determineMainSize(int i2, int i3, int i4) {
        int size;
        int paddingLeft;
        int paddingRight;
        int shrinkFlexItems;
        if (i2 == 0 || i2 == 1) {
            int mode = View.MeasureSpec.getMode(i3);
            size = View.MeasureSpec.getSize(i3);
            if (mode != 1073741824) {
                size = getLargestMainSize();
            }
            paddingLeft = getPaddingLeft();
            paddingRight = getPaddingRight();
        } else if (i2 != 2 && i2 != 3) {
            throw new IllegalArgumentException("Invalid flex direction: " + i2);
        } else {
            int mode2 = View.MeasureSpec.getMode(i4);
            size = View.MeasureSpec.getSize(i4);
            if (mode2 != 1073741824) {
                size = getLargestMainSize();
            }
            paddingLeft = getPaddingTop();
            paddingRight = getPaddingBottom();
        }
        int i5 = paddingLeft + paddingRight;
        int i6 = size;
        int i7 = 0;
        for (FlexLine flexLine : this.mFlexLines) {
            if (flexLine.mMainSize < i6) {
                shrinkFlexItems = expandFlexItems(i3, i4, flexLine, i2, i6, i5, i7, false);
            } else {
                shrinkFlexItems = shrinkFlexItems(i3, i4, flexLine, i2, i6, i5, i7, false);
            }
            i7 = shrinkFlexItems;
        }
    }

    private void drawDividersHorizontal(Canvas canvas, boolean z, boolean z2) {
        int i2;
        int i3;
        int right;
        int left;
        int paddingLeft = getPaddingLeft();
        int max = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.mFlexLines.size();
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            FlexLine flexLine = this.mFlexLines.get(i5);
            for (int i6 = 0; i6 < flexLine.mItemCount; i6++) {
                View reorderedChildAt = getReorderedChildAt(i4);
                if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(i4, i6)) {
                        if (z) {
                            left = reorderedChildAt.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        } else {
                            left = (reorderedChildAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.mDividerVerticalWidth;
                        }
                        drawVerticalDivider(canvas, left, flexLine.mTop, flexLine.mCrossSize);
                    }
                    if (i6 == flexLine.mItemCount - 1 && (this.mShowDividerVertical & 4) > 0) {
                        if (z) {
                            right = (reorderedChildAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.mDividerVerticalWidth;
                        } else {
                            right = reorderedChildAt.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        }
                        drawVerticalDivider(canvas, right, flexLine.mTop, flexLine.mCrossSize);
                    }
                    i4++;
                }
            }
            if (hasDividerBeforeFlexLine(i5)) {
                if (z2) {
                    i3 = flexLine.mBottom;
                } else {
                    i3 = flexLine.mTop - this.mDividerHorizontalHeight;
                }
                drawHorizontalDivider(canvas, paddingLeft, i3, max);
            }
            if (hasEndDividerAfterFlexLine(i5) && (this.mShowDividerHorizontal & 4) > 0) {
                if (z2) {
                    i2 = flexLine.mTop - this.mDividerHorizontalHeight;
                } else {
                    i2 = flexLine.mBottom;
                }
                drawHorizontalDivider(canvas, paddingLeft, i2, max);
            }
        }
    }

    private void drawDividersVertical(Canvas canvas, boolean z, boolean z2) {
        int i2;
        int i3;
        int bottom;
        int top;
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.mFlexLines.size();
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            FlexLine flexLine = this.mFlexLines.get(i5);
            for (int i6 = 0; i6 < flexLine.mItemCount; i6++) {
                View reorderedChildAt = getReorderedChildAt(i4);
                if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(i4, i6)) {
                        if (z2) {
                            top = reorderedChildAt.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                        } else {
                            top = (reorderedChildAt.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.mDividerHorizontalHeight;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, top, flexLine.mCrossSize);
                    }
                    if (i6 == flexLine.mItemCount - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                        if (z2) {
                            bottom = (reorderedChildAt.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.mDividerHorizontalHeight;
                        } else {
                            bottom = reorderedChildAt.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                        }
                        drawHorizontalDivider(canvas, flexLine.mLeft, bottom, flexLine.mCrossSize);
                    }
                    i4++;
                }
            }
            if (hasDividerBeforeFlexLine(i5)) {
                if (z) {
                    i3 = flexLine.mRight;
                } else {
                    i3 = flexLine.mLeft - this.mDividerVerticalWidth;
                }
                drawVerticalDivider(canvas, i3, paddingTop, max);
            }
            if (hasEndDividerAfterFlexLine(i5) && (this.mShowDividerVertical & 4) > 0) {
                if (z) {
                    i2 = flexLine.mLeft - this.mDividerVerticalWidth;
                } else {
                    i2 = flexLine.mRight;
                }
                drawVerticalDivider(canvas, i2, paddingTop, max);
            }
        }
    }

    private void drawHorizontalDivider(Canvas canvas, int i2, int i3, int i4) {
        Drawable drawable = this.mDividerDrawableHorizontal;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i2, i3, i4 + i2, this.mDividerHorizontalHeight + i3);
        this.mDividerDrawableHorizontal.draw(canvas);
    }

    private void drawVerticalDivider(Canvas canvas, int i2, int i3, int i4) {
        Drawable drawable = this.mDividerDrawableVertical;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i2, i3, this.mDividerVerticalWidth + i2, i4 + i3);
        this.mDividerDrawableVertical.draw(canvas);
    }

    private int expandFlexItems(int i2, int i3, FlexLine flexLine, int i4, int i5, int i6, int i7, boolean z) {
        int i8;
        double d;
        int i9;
        double d2;
        int i10;
        float f2 = flexLine.mTotalFlexGrow;
        if (f2 > 0.0f && i5 >= (i8 = flexLine.mMainSize)) {
            float f3 = (i5 - i8) / f2;
            flexLine.mMainSize = i6 + flexLine.mDividerLengthInMainSize;
            if (!z) {
                flexLine.mCrossSize = Integer.MIN_VALUE;
            }
            int i11 = i7;
            boolean z2 = false;
            int i12 = 0;
            float f4 = 0.0f;
            for (int i13 = 0; i13 < flexLine.mItemCount; i13++) {
                View reorderedChildAt = getReorderedChildAt(i11);
                if (reorderedChildAt != null) {
                    if (reorderedChildAt.getVisibility() == 8) {
                        i11++;
                    } else {
                        LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                        if (isMainAxisDirectionHorizontal(i4)) {
                            if (!this.mChildrenFrozen[i11]) {
                                float measuredWidth = reorderedChildAt.getMeasuredWidth() + (layoutParams.flexGrow * f3);
                                if (i13 == flexLine.mItemCount - 1) {
                                    measuredWidth += f4;
                                    f4 = 0.0f;
                                }
                                int round = Math.round(measuredWidth);
                                int i14 = layoutParams.maxWidth;
                                if (round > i14) {
                                    this.mChildrenFrozen[i11] = true;
                                    flexLine.mTotalFlexGrow -= layoutParams.flexGrow;
                                    i10 = i3;
                                    round = i14;
                                    z2 = true;
                                } else {
                                    f4 += measuredWidth - round;
                                    double d3 = f4;
                                    if (d3 > 1.0d) {
                                        round++;
                                        Double.isNaN(d3);
                                        d2 = d3 - 1.0d;
                                    } else {
                                        if (d3 < -1.0d) {
                                            round--;
                                            Double.isNaN(d3);
                                            d2 = d3 + 1.0d;
                                        }
                                        i10 = i3;
                                    }
                                    f4 = (float) d2;
                                    i10 = i3;
                                }
                                reorderedChildAt.measure(View.MeasureSpec.makeMeasureSpec(round, 1073741824), getChildHeightMeasureSpec(i10, layoutParams));
                                i12 = Math.max(i12, reorderedChildAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                            }
                            flexLine.mMainSize += reorderedChildAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        } else {
                            if (!this.mChildrenFrozen[i11]) {
                                float measuredHeight = reorderedChildAt.getMeasuredHeight() + (layoutParams.flexGrow * f3);
                                if (i13 == flexLine.mItemCount - 1) {
                                    measuredHeight += f4;
                                    f4 = 0.0f;
                                }
                                int round2 = Math.round(measuredHeight);
                                int i15 = layoutParams.maxHeight;
                                if (round2 > i15) {
                                    this.mChildrenFrozen[i11] = true;
                                    flexLine.mTotalFlexGrow -= layoutParams.flexGrow;
                                    i9 = i2;
                                    round2 = i15;
                                    z2 = true;
                                } else {
                                    f4 += measuredHeight - round2;
                                    double d4 = f4;
                                    if (d4 > 1.0d) {
                                        round2++;
                                        Double.isNaN(d4);
                                        d = d4 - 1.0d;
                                    } else {
                                        if (d4 < -1.0d) {
                                            round2--;
                                            Double.isNaN(d4);
                                            d = d4 + 1.0d;
                                        }
                                        i9 = i2;
                                    }
                                    f4 = (float) d;
                                    i9 = i2;
                                }
                                reorderedChildAt.measure(getChildWidthMeasureSpec(i9, layoutParams), View.MeasureSpec.makeMeasureSpec(round2, 1073741824));
                                i12 = Math.max(i12, reorderedChildAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
                            }
                            flexLine.mMainSize += reorderedChildAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                        }
                        flexLine.mCrossSize = Math.max(flexLine.mCrossSize, i12);
                        i11++;
                    }
                }
            }
            if (z2 && i8 != flexLine.mMainSize) {
                expandFlexItems(i2, i3, flexLine, i4, i5, i6, i7, true);
            }
            return i11;
        }
        return i7 + flexLine.mItemCount;
    }

    private int getChildHeightMeasureSpec(int i2, LayoutParams layoutParams) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, ((ViewGroup.MarginLayoutParams) layoutParams).height);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        int i3 = layoutParams.maxHeight;
        if (size > i3) {
            return View.MeasureSpec.makeMeasureSpec(i3, View.MeasureSpec.getMode(childMeasureSpec));
        }
        int i4 = layoutParams.minHeight;
        return size < i4 ? View.MeasureSpec.makeMeasureSpec(i4, View.MeasureSpec.getMode(childMeasureSpec)) : childMeasureSpec;
    }

    private int getChildWidthMeasureSpec(int i2, LayoutParams layoutParams) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, ((ViewGroup.MarginLayoutParams) layoutParams).width);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        int i3 = layoutParams.maxWidth;
        if (size > i3) {
            return View.MeasureSpec.makeMeasureSpec(i3, View.MeasureSpec.getMode(childMeasureSpec));
        }
        int i4 = layoutParams.minWidth;
        return size < i4 ? View.MeasureSpec.makeMeasureSpec(i4, View.MeasureSpec.getMode(childMeasureSpec)) : childMeasureSpec;
    }

    private int getLargestMainSize() {
        Iterator<FlexLine> it = this.mFlexLines.iterator();
        int i2 = Integer.MIN_VALUE;
        while (it.hasNext()) {
            i2 = Math.max(i2, it.next().mMainSize);
        }
        return i2;
    }

    private int getSumOfCrossSize() {
        int i2;
        int i3;
        int size = this.mFlexLines.size();
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            FlexLine flexLine = this.mFlexLines.get(i5);
            if (hasDividerBeforeFlexLine(i5)) {
                if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
                    i3 = this.mDividerHorizontalHeight;
                } else {
                    i3 = this.mDividerVerticalWidth;
                }
                i4 += i3;
            }
            if (hasEndDividerAfterFlexLine(i5)) {
                if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
                    i2 = this.mDividerHorizontalHeight;
                } else {
                    i2 = this.mDividerVerticalWidth;
                }
                i4 += i2;
            }
            i4 += flexLine.mCrossSize;
        }
        return i4;
    }

    private boolean hasDividerBeforeChildAtAlongMainAxis(int i2, int i3) {
        return allViewsAreGoneBefore(i2, i3) ? isMainAxisDirectionHorizontal(this.mFlexDirection) ? (this.mShowDividerVertical & 1) != 0 : (this.mShowDividerHorizontal & 1) != 0 : isMainAxisDirectionHorizontal(this.mFlexDirection) ? (this.mShowDividerVertical & 2) != 0 : (this.mShowDividerHorizontal & 2) != 0;
    }

    private boolean hasDividerBeforeFlexLine(int i2) {
        if (i2 < 0 || i2 >= this.mFlexLines.size()) {
            return false;
        }
        return allFlexLinesAreDummyBefore(i2) ? isMainAxisDirectionHorizontal(this.mFlexDirection) ? (this.mShowDividerHorizontal & 1) != 0 : (this.mShowDividerVertical & 1) != 0 : isMainAxisDirectionHorizontal(this.mFlexDirection) ? (this.mShowDividerHorizontal & 2) != 0 : (this.mShowDividerVertical & 2) != 0;
    }

    private boolean hasEndDividerAfterFlexLine(int i2) {
        if (i2 < 0 || i2 >= this.mFlexLines.size()) {
            return false;
        }
        for (int i3 = i2 + 1; i3 < this.mFlexLines.size(); i3++) {
            if (this.mFlexLines.get(i3).getItemCountNotGone() > 0) {
                return false;
            }
        }
        return isMainAxisDirectionHorizontal(this.mFlexDirection) ? (this.mShowDividerHorizontal & 4) != 0 : (this.mShowDividerVertical & 4) != 0;
    }

    private boolean isMainAxisDirectionHorizontal(int i2) {
        return i2 == 0 || i2 == 1;
    }

    private boolean isOrderChangedFromLastMeasurement() {
        int childCount = getChildCount();
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(childCount);
        }
        if (this.mOrderCache.size() != childCount) {
            return true;
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && ((LayoutParams) childAt.getLayoutParams()).order != this.mOrderCache.get(i2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x003e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isWrapRequired(int r3, int r4, int r5, int r6, com.google.android.flexbox.FlexboxLayout.LayoutParams r7, int r8, int r9) {
        /*
            r2 = this;
            int r0 = r2.mFlexWrap
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r7 = r7.wrapBefore
            r0 = 1
            if (r7 == 0) goto Lc
            return r0
        Lc:
            if (r3 != 0) goto Lf
            return r1
        Lf:
            int r3 = r2.mFlexDirection
            boolean r3 = r2.isMainAxisDirectionHorizontal(r3)
            if (r3 == 0) goto L29
            boolean r3 = r2.hasDividerBeforeChildAtAlongMainAxis(r8, r9)
            if (r3 == 0) goto L20
            int r3 = r2.mDividerVerticalWidth
            int r6 = r6 + r3
        L20:
            int r3 = r2.mShowDividerVertical
            r3 = r3 & 4
            if (r3 <= 0) goto L3b
            int r3 = r2.mDividerVerticalWidth
            goto L3a
        L29:
            boolean r3 = r2.hasDividerBeforeChildAtAlongMainAxis(r8, r9)
            if (r3 == 0) goto L32
            int r3 = r2.mDividerHorizontalHeight
            int r6 = r6 + r3
        L32:
            int r3 = r2.mShowDividerHorizontal
            r3 = r3 & 4
            if (r3 <= 0) goto L3b
            int r3 = r2.mDividerHorizontalHeight
        L3a:
            int r6 = r6 + r3
        L3b:
            int r5 = r5 + r6
            if (r4 >= r5) goto L3f
            r1 = 1
        L3f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.isWrapRequired(int, int, int, int, com.google.android.flexbox.FlexboxLayout$LayoutParams, int, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void layoutHorizontal(boolean r28, int r29, int r30, int r31, int r32) {
        /*
            Method dump skipped, instructions count: 550
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.layoutHorizontal(boolean, int, int, int, int):void");
    }

    private void layoutSingleChildHorizontal(View view, FlexLine flexLine, int i2, int i3, int i4, int i5, int i6, int i7) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i8 = layoutParams.alignSelf;
        if (i8 != -1) {
            i3 = i8;
        }
        int i9 = flexLine.mCrossSize;
        if (i3 != 0) {
            if (i3 == 1) {
                if (i2 != 2) {
                    int i10 = i5 + i9;
                    int i11 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                    view.layout(i4, (i10 - view.getMeasuredHeight()) - i11, i6, i10 - i11);
                    return;
                }
                view.layout(i4, (i5 - i9) + view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, i6, (i7 - i9) + view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin);
                return;
            } else if (i3 == 2) {
                int measuredHeight = (((i9 - view.getMeasuredHeight()) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) / 2;
                if (i2 != 2) {
                    int i12 = i5 + measuredHeight;
                    view.layout(i4, i12, i6, view.getMeasuredHeight() + i12);
                    return;
                }
                int i13 = i5 - measuredHeight;
                view.layout(i4, i13, i6, view.getMeasuredHeight() + i13);
                return;
            } else if (i3 == 3) {
                if (i2 != 2) {
                    int max = Math.max(flexLine.mMaxBaseline - view.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams).topMargin);
                    view.layout(i4, i5 + max, i6, i7 + max);
                    return;
                }
                int max2 = Math.max((flexLine.mMaxBaseline - view.getMeasuredHeight()) + view.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                view.layout(i4, i5 - max2, i6, i7 - max2);
                return;
            } else if (i3 != 4) {
                return;
            }
        }
        if (i2 != 2) {
            int i14 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            view.layout(i4, i5 + i14, i6, i7 + i14);
            return;
        }
        int i15 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        view.layout(i4, i5 - i15, i6, i7 - i15);
    }

    private void layoutSingleChildVertical(View view, FlexLine flexLine, boolean z, int i2, int i3, int i4, int i5, int i6) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i7 = layoutParams.alignSelf;
        if (i7 != -1) {
            i2 = i7;
        }
        int i8 = flexLine.mCrossSize;
        if (i2 != 0) {
            if (i2 == 1) {
                if (!z) {
                    view.layout(((i3 + i8) - view.getMeasuredWidth()) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, i4, ((i5 + i8) - view.getMeasuredWidth()) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, i6);
                    return;
                } else {
                    view.layout((i3 - i8) + view.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i4, (i5 - i8) + view.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i6);
                    return;
                }
            } else if (i2 == 2) {
                int measuredWidth = (((i8 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(layoutParams)) - MarginLayoutParamsCompat.getMarginEnd(layoutParams)) / 2;
                if (!z) {
                    view.layout(i3 + measuredWidth, i4, i5 + measuredWidth, i6);
                    return;
                } else {
                    view.layout(i3 - measuredWidth, i4, i5 - measuredWidth, i6);
                    return;
                }
            } else if (i2 != 3 && i2 != 4) {
                return;
            }
        }
        if (!z) {
            int i9 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            view.layout(i3 + i9, i4, i5 + i9, i6);
            return;
        }
        int i10 = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        view.layout(i3 - i10, i4, i5 - i10, i6);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void layoutVertical(boolean r26, boolean r27, int r28, int r29, int r30, int r31) {
        /*
            Method dump skipped, instructions count: 532
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.layoutVertical(boolean, boolean, int, int, int, int):void");
    }

    private void measureHorizontal(int i2, int i3) {
        int i4;
        int i5;
        LayoutParams layoutParams;
        int i6;
        int i7;
        int i8;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        this.mFlexLines.clear();
        int childCount = getChildCount();
        int paddingStart = ViewCompat.getPaddingStart(this);
        int paddingEnd = ViewCompat.getPaddingEnd(this);
        FlexLine flexLine = new FlexLine();
        int i9 = paddingStart + paddingEnd;
        flexLine.mMainSize = i9;
        int i10 = 0;
        FlexLine flexLine2 = flexLine;
        int i11 = 0;
        int i12 = Integer.MIN_VALUE;
        int i13 = 0;
        int i14 = 0;
        while (i13 < childCount) {
            View reorderedChildAt = getReorderedChildAt(i13);
            if (reorderedChildAt == null) {
                addFlexLineIfLastFlexItem(i13, childCount, flexLine2);
            } else if (reorderedChildAt.getVisibility() == 8) {
                flexLine2.mItemCount++;
                flexLine2.mGoneItemCount++;
                addFlexLineIfLastFlexItem(i13, childCount, flexLine2);
            } else {
                LayoutParams layoutParams2 = (LayoutParams) reorderedChildAt.getLayoutParams();
                if (layoutParams2.alignSelf == 4) {
                    flexLine2.mIndicesAlignSelfStretch.add(Integer.valueOf(i13));
                }
                int i15 = ((ViewGroup.MarginLayoutParams) layoutParams2).width;
                float f2 = layoutParams2.flexBasisPercent;
                if (f2 != -1.0f && mode == 1073741824) {
                    i15 = Math.round(size * f2);
                }
                reorderedChildAt.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin, i15), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin, ((ViewGroup.MarginLayoutParams) layoutParams2).height));
                checkSizeConstraints(reorderedChildAt);
                int combineMeasuredStates = ViewCompat.combineMeasuredStates(i11, ViewCompat.getMeasuredState(reorderedChildAt));
                int max = Math.max(i12, reorderedChildAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin);
                i5 = mode;
                int i16 = i13;
                FlexLine flexLine3 = flexLine2;
                if (isWrapRequired(mode, size, flexLine2.mMainSize, reorderedChildAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin, layoutParams2, i13, i14)) {
                    if (flexLine3.getItemCountNotGone() > 0) {
                        addFlexLine(flexLine3);
                    }
                    flexLine2 = new FlexLine();
                    flexLine2.mItemCount = 1;
                    flexLine2.mMainSize = i9;
                    layoutParams = layoutParams2;
                    i7 = reorderedChildAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                    i6 = 0;
                } else {
                    layoutParams = layoutParams2;
                    flexLine3.mItemCount++;
                    flexLine2 = flexLine3;
                    i6 = i14 + 1;
                    i7 = max;
                }
                flexLine2.mMainSize += reorderedChildAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                flexLine2.mTotalFlexGrow += layoutParams.flexGrow;
                flexLine2.mTotalFlexShrink += layoutParams.flexShrink;
                flexLine2.mCrossSize = Math.max(flexLine2.mCrossSize, i7);
                i8 = i16;
                if (hasDividerBeforeChildAtAlongMainAxis(i8, i6)) {
                    int i17 = flexLine2.mMainSize;
                    int i18 = this.mDividerVerticalWidth;
                    flexLine2.mMainSize = i17 + i18;
                    flexLine2.mDividerLengthInMainSize += i18;
                }
                if (this.mFlexWrap != 2) {
                    flexLine2.mMaxBaseline = Math.max(flexLine2.mMaxBaseline, reorderedChildAt.getBaseline() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin);
                } else {
                    flexLine2.mMaxBaseline = Math.max(flexLine2.mMaxBaseline, (reorderedChildAt.getMeasuredHeight() - reorderedChildAt.getBaseline()) + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                }
                addFlexLineIfLastFlexItem(i8, childCount, flexLine2);
                i12 = i7;
                i14 = i6;
                i11 = combineMeasuredStates;
                i13 = i8 + 1;
                mode = i5;
            }
            i8 = i13;
            i5 = mode;
            i13 = i8 + 1;
            mode = i5;
        }
        determineMainSize(this.mFlexDirection, i2, i3);
        if (this.mAlignItems == 3) {
            for (FlexLine flexLine4 : this.mFlexLines) {
                int i19 = i10;
                int i20 = Integer.MIN_VALUE;
                while (true) {
                    i4 = flexLine4.mItemCount;
                    if (i19 < i10 + i4) {
                        View reorderedChildAt2 = getReorderedChildAt(i19);
                        if (reorderedChildAt2 != null) {
                            LayoutParams layoutParams3 = (LayoutParams) reorderedChildAt2.getLayoutParams();
                            if (this.mFlexWrap != 2) {
                                i20 = Math.max(i20, reorderedChildAt2.getHeight() + Math.max(flexLine4.mMaxBaseline - reorderedChildAt2.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin) + ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin);
                            } else {
                                i20 = Math.max(i20, reorderedChildAt2.getHeight() + ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin + Math.max((flexLine4.mMaxBaseline - reorderedChildAt2.getMeasuredHeight()) + reorderedChildAt2.getBaseline(), ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin));
                            }
                        }
                        i19++;
                    }
                }
                flexLine4.mCrossSize = i20;
                i10 += i4;
            }
        }
        determineCrossSize(this.mFlexDirection, i2, i3, getPaddingTop() + getPaddingBottom());
        stretchViews(this.mFlexDirection, this.mAlignItems);
        setMeasuredDimensionForFlex(this.mFlexDirection, i2, i3, i11);
    }

    private void measureVertical(int i2, int i3) {
        int combineMeasuredStates;
        int i4;
        LayoutParams layoutParams;
        int i5;
        int i6;
        int i7;
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        this.mFlexLines.clear();
        int childCount = getChildCount();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        FlexLine flexLine = new FlexLine();
        int i8 = paddingTop + paddingBottom;
        flexLine.mMainSize = i8;
        int i9 = Integer.MIN_VALUE;
        FlexLine flexLine2 = flexLine;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (i11 < childCount) {
            View reorderedChildAt = getReorderedChildAt(i11);
            if (reorderedChildAt == null) {
                addFlexLineIfLastFlexItem(i11, childCount, flexLine2);
            } else if (reorderedChildAt.getVisibility() == 8) {
                flexLine2.mItemCount++;
                flexLine2.mGoneItemCount++;
                addFlexLineIfLastFlexItem(i11, childCount, flexLine2);
            } else {
                LayoutParams layoutParams2 = (LayoutParams) reorderedChildAt.getLayoutParams();
                if (layoutParams2.alignSelf == 4) {
                    flexLine2.mIndicesAlignSelfStretch.add(Integer.valueOf(i11));
                }
                int i13 = ((ViewGroup.MarginLayoutParams) layoutParams2).height;
                float f2 = layoutParams2.flexBasisPercent;
                if (f2 != -1.0f && mode == 1073741824) {
                    i13 = Math.round(size * f2);
                }
                int i14 = i11;
                reorderedChildAt.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin, ((ViewGroup.MarginLayoutParams) layoutParams2).width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin, i13));
                checkSizeConstraints(reorderedChildAt);
                combineMeasuredStates = ViewCompat.combineMeasuredStates(i10, ViewCompat.getMeasuredState(reorderedChildAt));
                int max = Math.max(i9, reorderedChildAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin);
                i4 = mode;
                FlexLine flexLine3 = flexLine2;
                if (isWrapRequired(mode, size, flexLine2.mMainSize, reorderedChildAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin, layoutParams2, i14, i12)) {
                    if (flexLine3.getItemCountNotGone() > 0) {
                        addFlexLine(flexLine3);
                    }
                    flexLine2 = new FlexLine();
                    flexLine2.mItemCount = 1;
                    flexLine2.mMainSize = i8;
                    layoutParams = layoutParams2;
                    i6 = reorderedChildAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                    i5 = 0;
                } else {
                    layoutParams = layoutParams2;
                    flexLine3.mItemCount++;
                    flexLine2 = flexLine3;
                    i5 = i12 + 1;
                    i6 = max;
                }
                flexLine2.mMainSize += reorderedChildAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                flexLine2.mTotalFlexGrow += layoutParams.flexGrow;
                flexLine2.mTotalFlexShrink += layoutParams.flexShrink;
                flexLine2.mCrossSize = Math.max(flexLine2.mCrossSize, i6);
                i7 = i14;
                if (hasDividerBeforeChildAtAlongMainAxis(i7, i5)) {
                    flexLine2.mMainSize += this.mDividerHorizontalHeight;
                }
                addFlexLineIfLastFlexItem(i7, childCount, flexLine2);
                i12 = i5;
                i9 = i6;
                i11 = i7 + 1;
                i10 = combineMeasuredStates;
                mode = i4;
            }
            combineMeasuredStates = i10;
            i7 = i11;
            i4 = mode;
            i11 = i7 + 1;
            i10 = combineMeasuredStates;
            mode = i4;
        }
        determineMainSize(this.mFlexDirection, i2, i3);
        determineCrossSize(this.mFlexDirection, i2, i3, getPaddingLeft() + getPaddingRight());
        stretchViews(this.mFlexDirection, this.mAlignItems);
        setMeasuredDimensionForFlex(this.mFlexDirection, i2, i3, i10);
    }

    private void setMeasuredDimensionForFlex(int i2, int i3, int i4, int i5) {
        int sumOfCrossSize;
        int largestMainSize;
        int resolveSizeAndState;
        int resolveSizeAndState2;
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        int mode2 = View.MeasureSpec.getMode(i4);
        int size2 = View.MeasureSpec.getSize(i4);
        if (i2 == 0 || i2 == 1) {
            sumOfCrossSize = getSumOfCrossSize() + getPaddingTop() + getPaddingBottom();
            largestMainSize = getLargestMainSize();
        } else if (i2 != 2 && i2 != 3) {
            throw new IllegalArgumentException("Invalid flex direction: " + i2);
        } else {
            sumOfCrossSize = getLargestMainSize();
            largestMainSize = getSumOfCrossSize() + getPaddingLeft() + getPaddingRight();
        }
        if (mode == Integer.MIN_VALUE) {
            if (size < largestMainSize) {
                i5 = ViewCompat.combineMeasuredStates(i5, 16777216);
            } else {
                size = largestMainSize;
            }
            resolveSizeAndState = ViewCompat.resolveSizeAndState(size, i3, i5);
        } else if (mode == 0) {
            resolveSizeAndState = ViewCompat.resolveSizeAndState(largestMainSize, i3, i5);
        } else if (mode == 1073741824) {
            if (size < largestMainSize) {
                i5 = ViewCompat.combineMeasuredStates(i5, 16777216);
            }
            resolveSizeAndState = ViewCompat.resolveSizeAndState(size, i3, i5);
        } else {
            throw new IllegalStateException("Unknown width mode is set: " + mode);
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (size2 < sumOfCrossSize) {
                i5 = ViewCompat.combineMeasuredStates(i5, 256);
            } else {
                size2 = sumOfCrossSize;
            }
            resolveSizeAndState2 = ViewCompat.resolveSizeAndState(size2, i4, i5);
        } else if (mode2 == 0) {
            resolveSizeAndState2 = ViewCompat.resolveSizeAndState(sumOfCrossSize, i4, i5);
        } else if (mode2 == 1073741824) {
            if (size2 < sumOfCrossSize) {
                i5 = ViewCompat.combineMeasuredStates(i5, 256);
            }
            resolveSizeAndState2 = ViewCompat.resolveSizeAndState(size2, i4, i5);
        } else {
            throw new IllegalStateException("Unknown height mode is set: " + mode2);
        }
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    private void setWillNotDrawFlag() {
        if (this.mDividerDrawableHorizontal == null && this.mDividerDrawableVertical == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private int shrinkFlexItems(int i2, int i3, FlexLine flexLine, int i4, int i5, int i6, int i7, boolean z) {
        int i8;
        int i9;
        int i10 = flexLine.mMainSize;
        float f2 = flexLine.mTotalFlexShrink;
        if (f2 > 0.0f && i5 <= i10) {
            float f3 = (i10 - i5) / f2;
            flexLine.mMainSize = i6 + flexLine.mDividerLengthInMainSize;
            if (!z) {
                flexLine.mCrossSize = Integer.MIN_VALUE;
            }
            int i11 = i7;
            boolean z2 = false;
            int i12 = 0;
            float f4 = 0.0f;
            for (int i13 = 0; i13 < flexLine.mItemCount; i13++) {
                View reorderedChildAt = getReorderedChildAt(i11);
                if (reorderedChildAt != null) {
                    if (reorderedChildAt.getVisibility() == 8) {
                        i11++;
                    } else {
                        LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                        if (isMainAxisDirectionHorizontal(i4)) {
                            if (!this.mChildrenFrozen[i11]) {
                                float measuredWidth = reorderedChildAt.getMeasuredWidth() - (layoutParams.flexShrink * f3);
                                if (i13 == flexLine.mItemCount - 1) {
                                    measuredWidth += f4;
                                    f4 = 0.0f;
                                }
                                int round = Math.round(measuredWidth);
                                int i14 = layoutParams.minWidth;
                                if (round < i14) {
                                    this.mChildrenFrozen[i11] = true;
                                    flexLine.mTotalFlexShrink -= layoutParams.flexShrink;
                                    i9 = i3;
                                    round = i14;
                                    z2 = true;
                                } else {
                                    f4 += measuredWidth - round;
                                    double d = f4;
                                    if (d > 1.0d) {
                                        round++;
                                        f4 -= 1.0f;
                                    } else if (d < -1.0d) {
                                        round--;
                                        f4 += 1.0f;
                                    }
                                    i9 = i3;
                                }
                                reorderedChildAt.measure(View.MeasureSpec.makeMeasureSpec(round, 1073741824), getChildHeightMeasureSpec(i9, layoutParams));
                                i12 = Math.max(i12, reorderedChildAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                            }
                            flexLine.mMainSize += reorderedChildAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                        } else {
                            if (!this.mChildrenFrozen[i11]) {
                                float measuredHeight = reorderedChildAt.getMeasuredHeight() - (layoutParams.flexShrink * f3);
                                if (i13 == flexLine.mItemCount - 1) {
                                    measuredHeight += f4;
                                    f4 = 0.0f;
                                }
                                int round2 = Math.round(measuredHeight);
                                int i15 = layoutParams.minHeight;
                                if (round2 < i15) {
                                    this.mChildrenFrozen[i11] = true;
                                    flexLine.mTotalFlexShrink -= layoutParams.flexShrink;
                                    i8 = i2;
                                    round2 = i15;
                                    z2 = true;
                                } else {
                                    f4 += measuredHeight - round2;
                                    double d2 = f4;
                                    if (d2 > 1.0d) {
                                        round2++;
                                        f4 -= 1.0f;
                                    } else if (d2 < -1.0d) {
                                        round2--;
                                        f4 += 1.0f;
                                    }
                                    i8 = i2;
                                }
                                reorderedChildAt.measure(getChildWidthMeasureSpec(i8, layoutParams), View.MeasureSpec.makeMeasureSpec(round2, 1073741824));
                                i12 = Math.max(i12, reorderedChildAt.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
                            }
                            flexLine.mMainSize += reorderedChildAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                        }
                        flexLine.mCrossSize = Math.max(flexLine.mCrossSize, i12);
                        i11++;
                    }
                }
            }
            if (z2 && i10 != flexLine.mMainSize) {
                shrinkFlexItems(i2, i3, flexLine, i4, i5, i6, i7, true);
            }
            return i11;
        }
        return i7 + flexLine.mItemCount;
    }

    private int[] sortOrdersIntoReorderedIndices(int i2, List<Order> list) {
        Collections.sort(list);
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(i2);
        }
        this.mOrderCache.clear();
        int[] iArr = new int[i2];
        int i3 = 0;
        for (Order order : list) {
            iArr[i3] = order.index;
            this.mOrderCache.append(i3, order.order);
            i3++;
        }
        return iArr;
    }

    private void stretchViewHorizontally(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec(Math.max((i2 - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, 0), 1073741824), View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
    }

    private void stretchViewVertically(View view, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(Math.max((i2 - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, 0), 1073741824));
    }

    private void stretchViews(int i2, int i3) {
        if (i3 == 4) {
            int i4 = 0;
            for (FlexLine flexLine : this.mFlexLines) {
                int i5 = 0;
                while (i5 < flexLine.mItemCount) {
                    View reorderedChildAt = getReorderedChildAt(i4);
                    int i6 = ((LayoutParams) reorderedChildAt.getLayoutParams()).alignSelf;
                    if (i6 == -1 || i6 == 4) {
                        if (i2 == 0 || i2 == 1) {
                            stretchViewVertically(reorderedChildAt, flexLine.mCrossSize);
                        } else if (i2 != 2 && i2 != 3) {
                            throw new IllegalArgumentException("Invalid flex direction: " + i2);
                        } else {
                            stretchViewHorizontally(reorderedChildAt, flexLine.mCrossSize);
                        }
                    }
                    i5++;
                    i4++;
                }
            }
            return;
        }
        for (FlexLine flexLine2 : this.mFlexLines) {
            Iterator<Integer> it = flexLine2.mIndicesAlignSelfStretch.iterator();
            while (it.hasNext()) {
                View reorderedChildAt2 = getReorderedChildAt(it.next().intValue());
                if (i2 == 0 || i2 == 1) {
                    stretchViewVertically(reorderedChildAt2, flexLine2.mCrossSize);
                } else if (i2 != 2 && i2 != 3) {
                    throw new IllegalArgumentException("Invalid flex direction: " + i2);
                } else {
                    stretchViewHorizontally(reorderedChildAt2, flexLine2.mCrossSize);
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        this.mReorderedIndices = createReorderedIndices(view, i2, layoutParams);
        super.addView(view, i2, layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public int getAlignContent() {
        return this.mAlignContent;
    }

    public int getAlignItems() {
        return this.mAlignItems;
    }

    public Drawable getDividerDrawableHorizontal() {
        return this.mDividerDrawableHorizontal;
    }

    public Drawable getDividerDrawableVertical() {
        return this.mDividerDrawableVertical;
    }

    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public List<FlexLine> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.mFlexLines.size());
        for (FlexLine flexLine : this.mFlexLines) {
            if (flexLine.getItemCountNotGone() != 0) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    public View getReorderedChildAt(int i2) {
        if (i2 >= 0) {
            int[] iArr = this.mReorderedIndices;
            if (i2 >= iArr.length) {
                return null;
            }
            return getChildAt(iArr[i2]);
        }
        return null;
    }

    public int getShowDividerHorizontal() {
        return this.mShowDividerHorizontal;
    }

    public int getShowDividerVertical() {
        return this.mShowDividerVertical;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDividerDrawableVertical == null && this.mDividerDrawableHorizontal == null) {
            return;
        }
        if (this.mShowDividerHorizontal == 0 && this.mShowDividerVertical == 0) {
            return;
        }
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int i2 = this.mFlexDirection;
        if (i2 == 0) {
            drawDividersHorizontal(canvas, layoutDirection == 1, this.mFlexWrap == 2);
        } else if (i2 == 1) {
            drawDividersHorizontal(canvas, layoutDirection != 1, this.mFlexWrap == 2);
        } else if (i2 == 2) {
            boolean z = layoutDirection == 1;
            if (this.mFlexWrap == 2) {
                z = !z;
            }
            drawDividersVertical(canvas, z, false);
        } else if (i2 != 3) {
        } else {
            boolean z2 = layoutDirection == 1;
            if (this.mFlexWrap == 2) {
                z2 = !z2;
            }
            drawDividersVertical(canvas, z2, true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        boolean z2;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int i6 = this.mFlexDirection;
        if (i6 == 0) {
            layoutHorizontal(layoutDirection == 1, i2, i3, i4, i5);
        } else if (i6 == 1) {
            layoutHorizontal(layoutDirection != 1, i2, i3, i4, i5);
        } else if (i6 == 2) {
            z2 = layoutDirection == 1;
            layoutVertical(this.mFlexWrap == 2 ? !z2 : z2, false, i2, i3, i4, i5);
        } else if (i6 == 3) {
            z2 = layoutDirection == 1;
            layoutVertical(this.mFlexWrap == 2 ? !z2 : z2, true, i2, i3, i4, i5);
        } else {
            throw new IllegalStateException("Invalid flex direction is set: " + this.mFlexDirection);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (isOrderChangedFromLastMeasurement()) {
            this.mReorderedIndices = createReorderedIndices();
        }
        boolean[] zArr = this.mChildrenFrozen;
        if (zArr == null || zArr.length < getChildCount()) {
            this.mChildrenFrozen = new boolean[getChildCount()];
        }
        int i4 = this.mFlexDirection;
        if (i4 == 0 || i4 == 1) {
            measureHorizontal(i2, i3);
        } else if (i4 != 2 && i4 != 3) {
            throw new IllegalStateException("Invalid value for the flex direction is set: " + this.mFlexDirection);
        } else {
            measureVertical(i2, i3);
        }
        Arrays.fill(this.mChildrenFrozen, false);
    }

    public void setAlignContent(int i2) {
        if (this.mAlignContent != i2) {
            this.mAlignContent = i2;
            requestLayout();
        }
    }

    public void setAlignItems(int i2) {
        if (this.mAlignItems != i2) {
            this.mAlignItems = i2;
            requestLayout();
        }
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(Drawable drawable) {
        if (drawable == this.mDividerDrawableHorizontal) {
            return;
        }
        this.mDividerDrawableHorizontal = drawable;
        if (drawable != null) {
            this.mDividerHorizontalHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerHorizontalHeight = 0;
        }
        setWillNotDrawFlag();
        requestLayout();
    }

    public void setDividerDrawableVertical(Drawable drawable) {
        if (drawable == this.mDividerDrawableVertical) {
            return;
        }
        this.mDividerDrawableVertical = drawable;
        if (drawable != null) {
            this.mDividerVerticalWidth = drawable.getIntrinsicWidth();
        } else {
            this.mDividerVerticalWidth = 0;
        }
        setWillNotDrawFlag();
        requestLayout();
    }

    public void setFlexDirection(int i2) {
        if (this.mFlexDirection != i2) {
            this.mFlexDirection = i2;
            requestLayout();
        }
    }

    public void setFlexWrap(int i2) {
        if (this.mFlexWrap != i2) {
            this.mFlexWrap = i2;
            requestLayout();
        }
    }

    public void setJustifyContent(int i2) {
        if (this.mJustifyContent != i2) {
            this.mJustifyContent = i2;
            requestLayout();
        }
    }

    public void setShowDivider(int i2) {
        setShowDividerVertical(i2);
        setShowDividerHorizontal(i2);
    }

    public void setShowDividerHorizontal(int i2) {
        if (i2 != this.mShowDividerHorizontal) {
            this.mShowDividerHorizontal = i2;
            requestLayout();
        }
    }

    public void setShowDividerVertical(int i2) {
        if (i2 != this.mShowDividerVertical) {
            this.mShowDividerVertical = i2;
            requestLayout();
        }
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mFlexLines = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout, i2, 0);
        this.mFlexDirection = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexDirection, 0);
        this.mFlexWrap = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexWrap, 0);
        this.mJustifyContent = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_justifyContent, 0);
        this.mAlignItems = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignItems, 4);
        this.mAlignContent = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignContent, 5);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDivider, 0);
        if (i3 != 0) {
            this.mShowDividerVertical = i3;
            this.mShowDividerHorizontal = i3;
        }
        int i4 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (i4 != 0) {
            this.mShowDividerVertical = i4;
        }
        int i5 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (i5 != 0) {
            this.mShowDividerHorizontal = i5;
        }
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    private int[] createReorderedIndices() {
        int childCount = getChildCount();
        return sortOrdersIntoReorderedIndices(childCount, createOrders(childCount));
    }

    /* loaded from: classes12.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int ALIGN_SELF_AUTO = -1;
        public static final int ALIGN_SELF_BASELINE = 3;
        public static final int ALIGN_SELF_CENTER = 2;
        public static final int ALIGN_SELF_FLEX_END = 1;
        public static final int ALIGN_SELF_FLEX_START = 0;
        public static final int ALIGN_SELF_STRETCH = 4;
        public static final float FLEX_BASIS_PERCENT_DEFAULT = -1.0f;
        private static final float FLEX_GROW_DEFAULT = 0.0f;
        private static final float FLEX_SHRINK_DEFAULT = 1.0f;
        private static final int MAX_SIZE = 16777215;
        private static final int ORDER_DEFAULT = 1;
        public int alignSelf;
        public float flexBasisPercent;
        public float flexGrow;
        public float flexShrink;
        public int maxHeight;
        public int maxWidth;
        public int minHeight;
        public int minWidth;
        public int order;
        public boolean wrapBefore;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.order = 1;
            this.flexGrow = 0.0f;
            this.flexShrink = 1.0f;
            this.alignSelf = -1;
            this.flexBasisPercent = -1.0f;
            this.maxWidth = 16777215;
            this.maxHeight = 16777215;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout_Layout);
            this.order = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.flexGrow = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.flexShrink = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.alignSelf = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.flexBasisPercent = obtainStyledAttributes.getFraction(R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.minWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minWidth, 0);
            this.minHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minHeight, 0);
            this.maxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.maxHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.wrapBefore = obtainStyledAttributes.getBoolean(R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.order = 1;
            this.flexGrow = 0.0f;
            this.flexShrink = 1.0f;
            this.alignSelf = -1;
            this.flexBasisPercent = -1.0f;
            this.maxWidth = 16777215;
            this.maxHeight = 16777215;
            this.order = layoutParams.order;
            this.flexGrow = layoutParams.flexGrow;
            this.flexShrink = layoutParams.flexShrink;
            this.alignSelf = layoutParams.alignSelf;
            this.flexBasisPercent = layoutParams.flexBasisPercent;
            this.minWidth = layoutParams.minWidth;
            this.minHeight = layoutParams.minHeight;
            this.maxWidth = layoutParams.maxWidth;
            this.maxHeight = layoutParams.maxHeight;
            this.wrapBefore = layoutParams.wrapBefore;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.order = 1;
            this.flexGrow = 0.0f;
            this.flexShrink = 1.0f;
            this.alignSelf = -1;
            this.flexBasisPercent = -1.0f;
            this.maxWidth = 16777215;
            this.maxHeight = 16777215;
        }

        public LayoutParams(int i2, int i3) {
            super(new ViewGroup.LayoutParams(i2, i3));
            this.order = 1;
            this.flexGrow = 0.0f;
            this.flexShrink = 1.0f;
            this.alignSelf = -1;
            this.flexBasisPercent = -1.0f;
            this.maxWidth = 16777215;
            this.maxHeight = 16777215;
        }
    }
}
