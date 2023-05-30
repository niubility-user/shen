package com.jingdong.app.mall.bundle.jdrhsdk.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.bundle.jdrhsdk.R;

/* loaded from: classes2.dex */
public class JDProgressBar extends ProgressBar {
    public JDProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public JDProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    private void a(Context context) {
        try {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(20, 20);
            layoutParams.addRule(13);
            setLayoutParams(layoutParams);
            Drawable drawable = context.getResources().getDrawable(R.drawable.jdrhsdk_progress_bar);
            drawable.setBounds(0, 0, 20, 20);
            setIndeterminateDrawable(drawable);
            setIndeterminate(true);
        } catch (Exception unused) {
        }
    }
}
