package com.jingdong.union.b;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.jingdong.union.R;

/* loaded from: classes12.dex */
public class a extends ProgressBar {
    public a(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.jingdong.union.a.a.b(context, 34.0f), com.jingdong.union.a.a.b(context, 34.0f));
        layoutParams.addRule(13);
        setLayoutParams(layoutParams);
        setBackgroundResource(R.drawable.union_load_logo);
        setIndeterminateDrawable(getResources().getDrawable(R.drawable.union_progress_small));
        setIndeterminate(true);
    }
}
