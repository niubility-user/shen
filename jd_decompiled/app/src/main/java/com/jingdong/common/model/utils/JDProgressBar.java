package com.jingdong.common.model.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.R;

/* loaded from: classes5.dex */
public class JDProgressBar extends ProgressBar {
    public JDProgressBar(Context context) {
        super(context);
        init();
    }

    private void init() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(34, 34);
        layoutParams.addRule(13);
        setLayoutParams(layoutParams);
        setBackgroundResource(R.drawable.xo);
        setIndeterminateDrawable(getResources().getDrawable(R.drawable.gx));
        setIndeterminate(true);
    }

    public JDProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public JDProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }
}
