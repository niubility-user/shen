package com.jd.viewkit.templates.container.jdviewkitbannerview.indicators;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.jdviewkit.R;
import com.jd.viewkit.tool.ColorTool;

/* loaded from: classes18.dex */
public class JDBannerIndicatorNewNumberView extends JDBannerIndicatorView<String> {
    private JDViewKitChannelModel channelModel;
    private TextView currentNumView;
    private String dotColor;

    public JDBannerIndicatorNewNumberView(Context context, JDViewKitChannelModel jDViewKitChannelModel) {
        this(context, null, 0, jDViewKitChannelModel);
    }

    private void drawIndicator(int i2) {
        int i3;
        if (this.total <= 0) {
            this.currentNumView.setText("");
            return;
        }
        StringBuilder sb = new StringBuilder(i2 + "/" + this.total);
        int indexOf = sb.indexOf("/");
        int length = sb.length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(sb);
        if (indexOf > 0 && (i3 = indexOf + 1) <= spannableStringBuilder.length()) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(0.67f), i3, length, 33);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ColorTool.parseColor(this.dotColor, getContext().getResources().getColor(R.color.default_dot_number_color))), i3, length, 33);
        }
        this.currentNumView.setText(spannableStringBuilder);
    }

    private void initNumberIndicator(String str, String str2) {
        this.dotColor = str;
        removeAllViews();
        TextView textView = new TextView(getContext());
        this.currentNumView = textView;
        textView.setGravity(17);
        this.currentNumView.setTextColor(ColorTool.parseColor(str2, getContext().getResources().getColor(R.color.default_dot_number_active_color)));
        this.currentNumView.setTextSize(0, GlobalManage.getInstance().getRealPx(48, this.channelModel));
        addView(this.currentNumView, new LinearLayout.LayoutParams(-2, GlobalManage.getInstance().getRealPx(60, this.channelModel)));
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.IndicatorListener
    public void update(int i2) {
        drawIndicator(i2 + 1);
    }

    public JDBannerIndicatorNewNumberView(Context context, @Nullable AttributeSet attributeSet, JDViewKitChannelModel jDViewKitChannelModel) {
        this(context, attributeSet, 0, jDViewKitChannelModel);
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.IndicatorListener
    public void initIndicator(int i2, int i3, String str, String str2) {
        initNumberIndicator(str, str2);
        this.total = i3;
        drawIndicator(i2 + 1);
    }

    public JDBannerIndicatorNewNumberView(Context context, @Nullable AttributeSet attributeSet, int i2, JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, attributeSet, i2);
        setGravity(17);
        this.channelModel = jDViewKitChannelModel;
    }
}
