package com.jingdong.common.unification.uniwidget.draggable;

import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class DragViewController {
    public static int height;

    public static int getHeight() {
        return height;
    }

    public static void setDragViewHeight(DraggableGridViewPager draggableGridViewPager, int i2) {
        if (draggableGridViewPager == null) {
            return;
        }
        int i3 = i2 / 4;
        if (i2 % 4 > 0) {
            i3++;
        }
        draggableGridViewPager.setRowCount(i3);
        int colCount = draggableGridViewPager.getColCount();
        int gridGap = draggableGridViewPager.getGridGap();
        height = (gridGap * (i3 - 1)) + (((((DPIUtil.getWidth() - ((colCount - 1) * gridGap)) - draggableGridViewPager.getPaddingLeft()) - draggableGridViewPager.getPaddingRight()) / 4) * i3) + draggableGridViewPager.getPaddingTop() + draggableGridViewPager.getPaddingBottom();
        draggableGridViewPager.getLayoutParams().height = height;
    }
}
