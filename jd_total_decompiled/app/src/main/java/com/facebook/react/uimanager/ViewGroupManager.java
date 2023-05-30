package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public abstract class ViewGroupManager<T extends ViewGroup> extends BaseViewManager<T, LayoutShadowNode> {
    private static WeakHashMap<View, Integer> mZIndexHash = new WeakHashMap<>();

    @Nullable
    public static Integer getViewZIndex(View view) {
        return mZIndexHash.get(view);
    }

    public static void reorderChildrenByZIndex(ViewGroup viewGroup) {
        int i2;
        boolean z;
        if (viewGroup != null && (viewGroup instanceof ReactViewGroup) && ((ReactViewGroup) viewGroup).getZindexflag()) {
            Iterator<Integer> it = mZIndexHash.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (it.next().intValue() != 0) {
                    z = true;
                    break;
                }
            }
            if (z) {
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                    arrayList.add(viewGroup.getChildAt(i3));
                }
                Collections.sort(arrayList, new Comparator<View>() { // from class: com.facebook.react.uimanager.ViewGroupManager.1
                    @Override // java.util.Comparator
                    public int compare(View view, View view2) {
                        Integer num = (Integer) ViewGroupManager.mZIndexHash.get(view);
                        if (num == null) {
                            num = r0;
                        }
                        Integer num2 = (Integer) ViewGroupManager.mZIndexHash.get(view2);
                        return num.intValue() - (num2 != null ? num2 : 0).intValue();
                    }
                });
                for (i2 = 0; i2 < arrayList.size(); i2++) {
                    ((View) arrayList.get(i2)).bringToFront();
                }
                viewGroup.invalidate();
            }
        }
    }

    public static void setViewZIndex(View view, int i2) {
        mZIndexHash.put(view, Integer.valueOf(i2));
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            reorderChildrenByZIndex(viewGroup);
        }
    }

    public void addView(T t, View view, int i2) {
        if (t == null || !(t instanceof ScrollView) || t.getChildCount() < 1) {
            t.addView(view, i2);
            reorderChildrenByZIndex(t);
        }
    }

    public void addViews(T t, List<View> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            addView(t, list.get(i2), i2);
        }
    }

    public View getChildAt(T t, int i2) {
        return t.getChildAt(i2);
    }

    public int getChildCount(T t) {
        return t.getChildCount();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return LayoutShadowNode.class;
    }

    public boolean needsCustomLayoutForChildren() {
        return false;
    }

    public void removeAllViews(T t) {
        for (int childCount = getChildCount(t) - 1; childCount >= 0; childCount--) {
            removeViewAt(t, childCount);
        }
    }

    public void removeView(T t, View view) {
        for (int i2 = 0; i2 < getChildCount(t); i2++) {
            if (getChildAt(t, i2) == view) {
                removeViewAt(t, i2);
                return;
            }
        }
    }

    public void removeViewAt(T t, int i2) {
        t.removeViewAt(i2);
    }

    public boolean shouldPromoteGrandchildren() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.ViewManager
    public /* bridge */ /* synthetic */ void updateExtraData(View view, Object obj) {
        updateExtraData((ViewGroupManager<T>) ((ViewGroup) view), obj);
    }

    public void updateExtraData(T t, Object obj) {
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new LayoutShadowNode();
    }
}
