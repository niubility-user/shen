package com.jd.lib.productdetail.core.utils;

import android.util.SparseArray;
import android.view.View;

/* loaded from: classes15.dex */
public class ViewHolder {
    public static <T extends View> T get(View view, int i2) {
        SparseArray sparseArray = (SparseArray) view.getTag();
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            view.setTag(sparseArray);
        }
        T t = (T) sparseArray.get(i2);
        if (t == null) {
            T t2 = (T) view.findViewById(i2);
            sparseArray.put(i2, t2);
            return t2;
        }
        return t;
    }
}
