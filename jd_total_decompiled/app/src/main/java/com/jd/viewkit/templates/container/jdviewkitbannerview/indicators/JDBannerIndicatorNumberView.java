package com.jd.viewkit.templates.container.jdviewkitbannerview.indicators;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.viewkit.jdviewkit.R;
import com.jd.viewkit.tool.ColorTool;

/* loaded from: classes18.dex */
public class JDBannerIndicatorNumberView extends JDBannerIndicatorView<String> {
    private static final float DEFAULT_ALPHA = 1.0f;
    private TextView currentNumView;
    private TextView totalNumView;

    public JDBannerIndicatorNumberView(Context context) {
        this(context, null);
    }

    private void initNumberIndicator(String str, String str2) {
        removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        TextView textView = new TextView(getContext());
        this.currentNumView = textView;
        textView.setLayoutParams(layoutParams);
        this.currentNumView.setTextColor(ColorTool.parseColor(str2, getContext().getResources().getColor(R.color.default_dot_number_active_color)));
        this.currentNumView.setTextSize(0, getContext().getResources().getDimension(R.dimen.banner_dot_number_active_font_sp));
        addView(this.currentNumView);
        TextView textView2 = new TextView(getContext());
        this.totalNumView = textView2;
        textView2.setLayoutParams(layoutParams);
        this.totalNumView.setTextColor(ColorTool.parseColor(str, getContext().getResources().getColor(R.color.default_dot_number_color)));
        this.totalNumView.setTextSize(0, getContext().getResources().getDimension(R.dimen.banner_dot_number_font_sp));
        this.totalNumView.setAlpha(1.0f);
        addView(this.totalNumView);
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.IndicatorListener
    public void update(int i2) {
        this.currentNumView.setText((i2 + 1) + "/");
    }

    public JDBannerIndicatorNumberView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.IndicatorListener
    public void initIndicator(int i2, int i3, String str, String str2) {
        initNumberIndicator(str, str2);
        this.currentNumView.setText((i2 + 1) + "/");
        this.totalNumView.setText(String.valueOf(i3));
        this.total = i3;
    }

    public JDBannerIndicatorNumberView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
