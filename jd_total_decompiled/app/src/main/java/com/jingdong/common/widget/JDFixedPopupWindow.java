package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes12.dex */
public class JDFixedPopupWindow extends PopupWindow {
    public JDFixedPopupWindow(Context context) {
        super(context);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            setHeight(view.getResources().getDisplayMetrics().heightPixels - rect.bottom);
        }
        super.showAsDropDown(view);
    }

    public JDFixedPopupWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JDFixedPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public JDFixedPopupWindow(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    public JDFixedPopupWindow() {
    }

    public JDFixedPopupWindow(View view) {
        super(view);
    }

    public JDFixedPopupWindow(int i2, int i3) {
        super(i2, i3);
    }

    public JDFixedPopupWindow(View view, int i2, int i3) {
        super(view, i2, i3);
    }

    public JDFixedPopupWindow(View view, int i2, int i3, boolean z) {
        super(view, i2, i3, z);
    }
}
