package com.tencent.map.lib.models;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class MapExploreByTouchHelper extends ExploreByTouchHelper {
    public static final int MAP_ACTION_CLICKED = 1;
    public static final int NO_ITEM = -1;
    public List<AccessibleTouchItem> accessibleTouchItems;

    public MapExploreByTouchHelper(@NonNull View view) {
        super(view);
        this.accessibleTouchItems = new ArrayList();
    }

    public abstract int getTargetPoiItemIdx(float f2, float f3);

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public int getVirtualViewAt(float f2, float f3) {
        int targetPoiItemIdx = getTargetPoiItemIdx(f2, f3);
        if (targetPoiItemIdx == -1) {
            return Integer.MIN_VALUE;
        }
        return targetPoiItemIdx;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void getVisibleVirtualViews(List<Integer> list) {
        for (int i2 = 0; i2 < this.accessibleTouchItems.size(); i2++) {
            list.add(Integer.valueOf(i2));
        }
    }

    public abstract boolean onItemClicked(int i2);

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public boolean onPerformActionForVirtualView(int i2, int i3, Bundle bundle) {
        if (i3 == 16) {
            return onItemClicked(i2);
        }
        return false;
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void onPopulateEventForVirtualView(int i2, AccessibilityEvent accessibilityEvent) {
        if (i2 >= this.accessibleTouchItems.size()) {
            accessibilityEvent.getText().add("");
            return;
        }
        AccessibleTouchItem accessibleTouchItem = this.accessibleTouchItems.get(i2);
        if (accessibleTouchItem == null) {
            throw new IllegalArgumentException("Invalid virtual view id");
        }
        accessibilityEvent.getText().add(accessibleTouchItem.getContentDescription());
    }

    @Override // androidx.customview.widget.ExploreByTouchHelper
    public void onPopulateNodeForVirtualView(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (i2 >= this.accessibleTouchItems.size()) {
            accessibilityNodeInfoCompat.setText("");
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect());
            return;
        }
        AccessibleTouchItem accessibleTouchItem = this.accessibleTouchItems.get(i2);
        if (accessibleTouchItem == null) {
            throw new IllegalArgumentException("Invalid virtual view id");
        }
        accessibilityNodeInfoCompat.setText(accessibleTouchItem.getContentDescription());
        accessibilityNodeInfoCompat.setBoundsInParent(accessibleTouchItem.getBounds());
        accessibilityNodeInfoCompat.addAction(16);
    }

    public void onTalkBackActivate(View view) {
        ViewCompat.setAccessibilityDelegate(view, this);
    }

    public void onTalkBackDeActivate(View view) {
        ViewCompat.setAccessibilityDelegate(view, null);
    }
}
