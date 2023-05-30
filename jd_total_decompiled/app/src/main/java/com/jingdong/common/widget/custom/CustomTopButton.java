package com.jingdong.common.widget.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.appcompat.widget.AppCompatImageView;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class CustomTopButton extends AppCompatImageView {

    /* loaded from: classes12.dex */
    public interface ITopButtonListener {
        void onClick(int i2);

        void onShow();
    }

    public CustomTopButton(Context context) {
        this(context, null);
    }

    public CustomTopButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int dip2px = DPIUtil.dip2px(12.0f);
        setPadding(dip2px, dip2px, dip2px, dip2px);
        setBackgroundResource(R.drawable.button_m_01);
        setVisibility(8);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, DPIUtil.dip2px(10.0f), DPIUtil.dip2px(60.0f));
        layoutParams.gravity = 85;
        setLayoutParams(layoutParams);
    }
}
