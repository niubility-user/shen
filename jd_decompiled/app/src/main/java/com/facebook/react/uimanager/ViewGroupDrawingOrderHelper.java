package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ViewGroupDrawingOrderHelper {
    @Nullable
    private int[] mDrawingOrderIndices;
    private int mNumberOfChildrenWithZIndex = 0;
    private final ViewGroup mViewGroup;

    public ViewGroupDrawingOrderHelper(ViewGroup viewGroup) {
        this.mViewGroup = viewGroup;
    }

    public int getChildDrawingOrder(int i2, int i3) {
        if (this.mDrawingOrderIndices == null) {
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < i2; i4++) {
                arrayList.add(this.mViewGroup.getChildAt(i4));
            }
            Collections.sort(arrayList, new Comparator<View>() { // from class: com.facebook.react.uimanager.ViewGroupDrawingOrderHelper.1
                @Override // java.util.Comparator
                public int compare(View view, View view2) {
                    Integer viewZIndex = ViewGroupManager.getViewZIndex(view);
                    if (viewZIndex == null) {
                        viewZIndex = r0;
                    }
                    Integer viewZIndex2 = ViewGroupManager.getViewZIndex(view2);
                    return viewZIndex.intValue() - (viewZIndex2 != null ? viewZIndex2 : 0).intValue();
                }
            });
            this.mDrawingOrderIndices = new int[i2];
            for (int i5 = 0; i5 < i2; i5++) {
                this.mDrawingOrderIndices[i5] = this.mViewGroup.indexOfChild((View) arrayList.get(i5));
            }
        }
        return this.mDrawingOrderIndices[i3];
    }

    public void handleAddView(View view) {
        if (ViewGroupManager.getViewZIndex(view) != null) {
            this.mNumberOfChildrenWithZIndex++;
        }
        this.mDrawingOrderIndices = null;
    }

    public void handleRemoveView(View view) {
        if (ViewGroupManager.getViewZIndex(view) != null) {
            this.mNumberOfChildrenWithZIndex--;
        }
        this.mDrawingOrderIndices = null;
    }

    public boolean shouldEnableCustomDrawingOrder() {
        return this.mNumberOfChildrenWithZIndex > 0;
    }

    public void update() {
        this.mNumberOfChildrenWithZIndex = 0;
        for (int i2 = 0; i2 < this.mViewGroup.getChildCount(); i2++) {
            if (ViewGroupManager.getViewZIndex(this.mViewGroup.getChildAt(i2)) != null) {
                this.mNumberOfChildrenWithZIndex++;
            }
        }
        this.mDrawingOrderIndices = null;
    }
}
