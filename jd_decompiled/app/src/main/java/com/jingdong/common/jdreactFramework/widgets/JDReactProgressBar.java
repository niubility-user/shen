package com.jingdong.common.jdreactFramework.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.jingdong.common.ares.R;

/* loaded from: classes5.dex */
public class JDReactProgressBar extends ProgressBar {
    private static float mDensity = 160.0f;

    public JDReactProgressBar(Context context) {
        super(context);
        init();
    }

    private void init() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip2px(34.0f), dip2px(34.0f));
        layoutParams.addRule(13);
        setLayoutParams(layoutParams);
        setBackgroundResource(R.drawable.jdreact_load_logo);
        setIndeterminateDrawable(getResources().getDrawable(R.drawable.jdreact_progress_small));
        setIndeterminate(true);
    }

    public int dip2px(float f2) {
        return (int) ((f2 * mDensity) + 0.5f);
    }

    public JDReactProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public JDReactProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }
}
