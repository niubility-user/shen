package com.jd.viewkit.templates.container.jdviewkitbannerview.indicators;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.jd.viewkit.jdviewkit.R;
import com.jd.viewkit.tool.ColorTool;

/* loaded from: classes18.dex */
public class JDBannerIndicatorRoundView extends JDBannerIndicatorView<String> {
    private static final float DEFAULT_ALPHA = 1.0f;
    private GradientDrawable activeDrawable;
    private GradientDrawable normalDrawable;

    public JDBannerIndicatorRoundView(Context context) {
        this(context, null);
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.IndicatorListener
    public void update(int i2) {
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) instanceof ImageView) {
                ImageView imageView = (ImageView) getChildAt(i3);
                if (i3 == i2) {
                    imageView.setImageDrawable(this.activeDrawable);
                    imageView.setAlpha(1.0f);
                } else {
                    imageView.setImageDrawable(this.normalDrawable);
                    imageView.setAlpha(1.0f);
                }
            }
        }
    }

    public JDBannerIndicatorRoundView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.IndicatorListener
    public void initIndicator(int i2, int i3, String str, String str2) {
        removeAllViews();
        this.total = i3;
        this.normalDrawable.setColor(ColorTool.parseColor(str, getContext().getResources().getColor(R.color.defalut_dot_round_color)));
        this.activeDrawable.setColor(ColorTool.parseColor(str2, getContext().getResources().getColor(R.color.defalut_dot_round_active_color)));
        this.activeDrawable.setShape(0);
        for (int i4 = 0; i4 < i3; i4++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            imageView.setPadding(0, 0, 15, 0);
            if (i4 == i2) {
                imageView.setImageDrawable(this.activeDrawable);
                imageView.setAlpha(1.0f);
            } else {
                imageView.setImageDrawable(this.normalDrawable);
                imageView.setAlpha(1.0f);
            }
            addView(imageView);
        }
    }

    public JDBannerIndicatorRoundView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.normalDrawable = new GradientDrawable();
        this.activeDrawable = new GradientDrawable();
        this.normalDrawable.setShape(1);
        this.normalDrawable.setSize(18, 18);
        this.activeDrawable.setCornerRadius(12.0f);
        this.activeDrawable.setSize(51, 18);
    }
}
