package com.jingdong.common.recommend.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.common.recommend.R;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class RecommendFooterView extends FrameLayout {
    public static final int FOOTER_END = 2;
    public static final int FOOTER_ERROR = 1;
    public static final int FOOTER_NODATA = 3;
    public static final int FOOTER_NORMAL = 0;
    private LinearLayout endLayout;
    private TextView errorLayout;
    private LinearLayout loadingLayout;

    public RecommendFooterView(@NonNull Context context) {
        this(context, null);
    }

    private void createView() {
        setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.loadingLayout = linearLayout;
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, DPIUtil.dip2px(50.0f)));
        this.loadingLayout.setGravity(17);
        JDProgressBar jDProgressBar = new JDProgressBar(getContext());
        jDProgressBar.setLayoutParams(new FrameLayout.LayoutParams(DPIUtil.dip2px(34.0f), DPIUtil.dip2px(34.0f)));
        this.loadingLayout.addView(jDProgressBar);
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        textView.setText("\u52a0\u8f7d\u4e2d...");
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(Color.parseColor("#848689"));
        layoutParams.leftMargin = DPIUtil.dip2px(4.0f);
        layoutParams.gravity = 16;
        this.loadingLayout.addView(textView);
        addView(this.loadingLayout);
        this.errorLayout = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, DPIUtil.dip2px(50.0f));
        this.errorLayout.setText("\u7f51\u7edc\u4e0d\u7ed9\u529b\u54e6\uff0c\u8bf7\u91cd\u8bd5\uff01");
        this.errorLayout.setGravity(17);
        this.errorLayout.setTextColor(Color.parseColor("#848689"));
        addView(this.errorLayout, layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        this.endLayout = linearLayout2;
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.endLayout.setOrientation(1);
        this.endLayout.setGravity(17);
        this.endLayout.setPadding(0, DPIUtil.dip2px(15.0f), 0, DPIUtil.dip2px(15.0f));
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        imageView.setImageResource(R.drawable.recommend_footer_joy);
        this.endLayout.addView(imageView);
        TextView textView2 = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        textView2.setText("\u6ca1\u6709\u66f4\u591a\u4e86~");
        textView2.setTextSize(11.0f);
        textView2.setTextColor(Color.parseColor("#BFBFBF"));
        layoutParams3.topMargin = DPIUtil.dip2px(5.0f);
        this.endLayout.addView(textView2);
        addView(this.endLayout);
    }

    public void setFooterState(int i2) {
        LinearLayout linearLayout = this.loadingLayout;
        if (linearLayout == null || this.endLayout == null || this.errorLayout == null) {
            return;
        }
        if (i2 == 0) {
            linearLayout.setVisibility(0);
            this.endLayout.setVisibility(8);
            this.errorLayout.setVisibility(8);
        } else if (i2 == 1) {
            linearLayout.setVisibility(8);
            this.endLayout.setVisibility(8);
            this.errorLayout.setVisibility(0);
        } else if (i2 == 2) {
            linearLayout.setVisibility(8);
            this.errorLayout.setVisibility(8);
            this.endLayout.setVisibility(0);
        } else if (i2 != 3) {
        } else {
            linearLayout.setVisibility(8);
            this.endLayout.setVisibility(8);
            this.errorLayout.setVisibility(0);
        }
    }

    public void setOnErrorLayoutClickLinstener(View.OnClickListener onClickListener) {
        TextView textView = this.errorLayout;
        if (textView != null) {
            textView.setOnClickListener(onClickListener);
        }
    }

    public RecommendFooterView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendFooterView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        createView();
    }
}
