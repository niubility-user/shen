package com.jingdong.common.ui;

import android.util.SparseArray;
import android.view.View;
import com.jingdong.common.UnLog;

/* loaded from: classes6.dex */
public class ViewHolder {
    private static final String TAG = "ViewHolder";

    public static <T extends View> T get(View view, int i2) {
        SparseArray sparseArray;
        try {
            sparseArray = (SparseArray) view.getTag();
        } catch (Exception e2) {
            UnLog.e(TAG, e2.getMessage());
            sparseArray = null;
        }
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
