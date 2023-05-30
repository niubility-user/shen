package com.jingdong.common.listui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.jingdong.listui.R;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class JDProgressBar extends ProgressBar {
    public JDProgressBar(Context context) {
        super(context);
        init();
    }

    private void init() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DPIUtil.dip2px(34.0f), DPIUtil.dip2px(34.0f));
        layoutParams.addRule(13);
        setLayoutParams(layoutParams);
        setBackgroundResource(R.drawable.liui_load_logo);
        setIndeterminateDrawable(getResources().getDrawable(R.drawable.liui_progress_small));
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
