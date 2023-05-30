package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.jingdong.app.mall.R;

/* loaded from: classes6.dex */
public class JshopSignScratchCard extends FrameLayout {
    private Context mContext;

    public JshopSignScratchCard(Context context) {
        this(context, null);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(this.mContext).inflate(R.layout.jshop_sign_scratchcard_frame_layout, (ViewGroup) this, true);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public JshopSignScratchCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    public JshopSignScratchCard(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        init();
    }
}
