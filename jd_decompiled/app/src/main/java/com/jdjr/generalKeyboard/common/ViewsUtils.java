package com.jdjr.generalKeyboard.common;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* loaded from: classes18.dex */
public class ViewsUtils {
    public static void addToParent(View view, View view2) {
        if (view == null || !(view instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) view).addView(view2);
    }

    public static void removeAllFromParent(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof ViewGroup) {
            int i2 = 0;
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i2 >= viewGroup.getChildCount()) {
                    break;
                }
                viewGroup.removeView(viewGroup.getChildAt(i2));
                i2++;
            }
        }
        removeFromParent(view);
    }

    public static void removeFromParent(View view) {
        ViewParent parent;
        if (view == null || (parent = view.getParent()) == null || !(parent instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) view.getParent()).removeView(view);
    }
}
