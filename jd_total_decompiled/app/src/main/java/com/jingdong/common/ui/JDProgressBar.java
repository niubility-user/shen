package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.IThemeChange;
import com.jingdong.common.DpiUtil;

/* loaded from: classes.dex */
public class JDProgressBar extends ProgressBar implements IThemeChange {
    private GlobalThemeController controller;

    public JDProgressBar(Context context) {
        super(context);
        init(context);
        initTheme();
    }

    private void init(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DpiUtil.dip2px(context, 20.0f), DpiUtil.dip2px(context, 20.0f));
        layoutParams.addRule(13);
        setLayoutParams(layoutParams);
        Drawable drawable = getResources().getDrawable(R.drawable.un_progress_bar);
        drawable.setBounds(0, 0, DpiUtil.dip2px(context, 20.0f), DpiUtil.dip2px(context, 20.0f));
        setIndeterminateDrawable(drawable);
        setIndeterminate(true);
    }

    private void initTheme() {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        this.controller = newInstance;
        if (newInstance.isCustomTheme()) {
            customTheme();
        }
    }

    @Override // com.jd.lib.un.global.IThemeChange
    public void customTheme() {
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(DpiUtil.dip2px(getContext(), 20.0f), DpiUtil.dip2px(getContext(), 20.0f));
    }

    public JDProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        initTheme();
    }

    public JDProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }
}
