package com.jingdong.common.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import com.jingdong.common.unification.statusbar.UnStatusBarWindowConfigUtils;

/* loaded from: classes12.dex */
public class JDBasePopupWindow extends PopupWindow {
    private boolean autoConfig;

    public JDBasePopupWindow(Context context) {
        super(context);
        this.autoConfig = true;
    }

    private void onAfterShowConfig() {
        if (this.autoConfig) {
            UnStatusBarWindowConfigUtils.onAfterShowConfig(this);
        }
    }

    private void onPreShowConfig() {
        if (this.autoConfig) {
            UnStatusBarWindowConfigUtils.onPreShowConfig(this);
        }
    }

    public void setAutoConfig(boolean z) {
        this.autoConfig = z;
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3, int i4) {
        onPreShowConfig();
        super.showAsDropDown(view, i2, i3, i4);
        onAfterShowConfig();
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View view, int i2, int i3, int i4) {
        onPreShowConfig();
        super.showAtLocation(view, i2, i3, i4);
        onAfterShowConfig();
    }

    public JDBasePopupWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.autoConfig = true;
    }

    public JDBasePopupWindow(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.autoConfig = true;
    }

    public JDBasePopupWindow(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.autoConfig = true;
    }

    public JDBasePopupWindow() {
        this.autoConfig = true;
    }

    public JDBasePopupWindow(View view) {
        super(view);
        this.autoConfig = true;
    }

    public JDBasePopupWindow(int i2, int i3) {
        super(i2, i3);
        this.autoConfig = true;
    }

    public JDBasePopupWindow(View view, int i2, int i3) {
        super(view, i2, i3);
        this.autoConfig = true;
    }

    public JDBasePopupWindow(View view, int i2, int i3, boolean z) {
        super(view, i2, i3, z);
        this.autoConfig = true;
    }
}
