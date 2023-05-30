package com.jd.lib.un.basewidget.widget.banner.recycle;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;

/* loaded from: classes16.dex */
public class RecycleBin {
    private SparseArray<View> currentScrapViews;
    private SparseArray<View>[] scrapViews;
    private int viewTypeCount;
    private View[] activeViews = new View[0];
    private int[] activeViewTypes = new int[0];

    private void pruneScrapViews() {
        int length = this.activeViews.length;
        int i2 = this.viewTypeCount;
        SparseArray<View>[] sparseArrayArr = this.scrapViews;
        for (int i3 = 0; i3 < i2; i3++) {
            SparseArray<View> sparseArray = sparseArrayArr[i3];
            int size = sparseArray.size();
            int i4 = size - length;
            int i5 = size - 1;
            int i6 = 0;
            while (i6 < i4) {
                sparseArray.remove(sparseArray.keyAt(i5));
                i6++;
                i5--;
            }
        }
    }

    static View retrieveFromScrap(SparseArray<View> sparseArray, int i2) {
        int size = sparseArray.size();
        if (size > 0) {
            for (int i3 = 0; i3 < size; i3++) {
                int keyAt = sparseArray.keyAt(i3);
                View view = sparseArray.get(keyAt);
                if (keyAt == i2) {
                    sparseArray.remove(keyAt);
                    return view;
                }
            }
            int i4 = size - 1;
            View valueAt = sparseArray.valueAt(i4);
            sparseArray.remove(sparseArray.keyAt(i4));
            return valueAt;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addScrapView(View view, int i2, int i3) {
        if (this.viewTypeCount == 1) {
            this.currentScrapViews.put(i2, view);
        } else {
            this.scrapViews[i3].put(i2, view);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            view.setAccessibilityDelegate(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getScrapView(int i2, int i3) {
        if (this.viewTypeCount == 1) {
            return retrieveFromScrap(this.currentScrapViews, i2);
        }
        if (i3 >= 0) {
            SparseArray<View>[] sparseArrayArr = this.scrapViews;
            if (i3 < sparseArrayArr.length) {
                return retrieveFromScrap(sparseArrayArr[i3], i2);
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scrapActiveViews() {
        View[] viewArr = this.activeViews;
        int[] iArr = this.activeViewTypes;
        boolean z = this.viewTypeCount > 1;
        SparseArray<View> sparseArray = this.currentScrapViews;
        for (int length = viewArr.length - 1; length >= 0; length--) {
            View view = viewArr[length];
            if (view != null) {
                int i2 = iArr[length];
                viewArr[length] = null;
                iArr[length] = -1;
                if (shouldRecycleViewType(i2)) {
                    if (z) {
                        sparseArray = this.scrapViews[i2];
                    }
                    sparseArray.put(length, view);
                    if (Build.VERSION.SDK_INT >= 14) {
                        view.setAccessibilityDelegate(null);
                    }
                }
            }
        }
        pruneScrapViews();
    }

    public void setViewTypeCount(int i2) {
        if (i2 >= 1) {
            SparseArray<View>[] sparseArrayArr = new SparseArray[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                sparseArrayArr[i3] = new SparseArray<>();
            }
            this.viewTypeCount = i2;
            this.currentScrapViews = sparseArrayArr[0];
            this.scrapViews = sparseArrayArr;
            return;
        }
        throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
    }

    protected boolean shouldRecycleViewType(int i2) {
        return i2 >= 0;
    }
}
