package com.jingdong.common.recommend.ui.product;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.ui.RecommendEmptyView;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class PDRecommendStateView extends RecommendEmptyView {
    private SimpleDraweeView errorIcon;
    private TextView errorTextView;
    private boolean isLightModel;
    private SimpleDraweeView noDataIcon;
    private TextView noDataText;

    public PDRecommendStateView(Context context) {
        this(context, null);
    }

    private void changeViewDarkModel() {
        TextView textView = this.errorTextView;
        boolean z = this.isLightModel;
        String str = JDDarkUtil.COLOR_808080;
        textView.setTextColor(Color.parseColor(z ? JDDarkUtil.COLOR_808080 : JDDarkUtil.COLOR_ECECEC));
        TextView textView2 = this.retryButton;
        boolean z2 = this.isLightModel;
        String str2 = JDDarkUtil.COLOR_FA2C19;
        textView2.setTextColor(Color.parseColor(z2 ? JDDarkUtil.COLOR_FA2C19 : JDDarkUtil.COLOR_ECECEC));
        GradientDrawable gradientDrawable = (GradientDrawable) this.retryButton.getBackground();
        int dip2px = DpiUtil.dip2px(getContext(), 0.5f);
        if (!this.isLightModel) {
            str2 = JDDarkUtil.COLOR_ECECEC;
        }
        gradientDrawable.setStroke(dip2px, Color.parseColor(str2));
        TextView textView3 = this.noDataText;
        if (!this.isLightModel) {
            str = JDDarkUtil.COLOR_ECECEC;
        }
        textView3.setTextColor(Color.parseColor(str));
        this.mLoadingView.setIndeterminateDrawableTiled(getResources().getDrawable(this.isLightModel ? R.drawable.recommend_pd_loading_light : R.drawable.recommend_pd_loading));
        this.errorIcon.setImageResource(this.isLightModel ? R.drawable.pd_feeds_common_error_unknown_light : R.drawable.pd_feeds_common_error_unknown);
        this.noDataIcon.setImageResource(this.isLightModel ? R.drawable.pd_feeds_common_error_nonet_light : R.drawable.pd_feeds_common_error_nonet);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendEmptyView
    public void addErrorLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.errorDataLayout = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.errorDataLayout.setOrientation(1);
        this.errorDataLayout.setGravity(1);
        addView(this.errorDataLayout);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        this.errorIcon = simpleDraweeView;
        simpleDraweeView.setLayoutParams(new RelativeLayout.LayoutParams(DPIUtil.dip2px(160.0f), DPIUtil.dip2px(160.0f)));
        if (this.errorIcon.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this.errorIcon.getLayoutParams()).topMargin = DPIUtil.dip2px(96.0f);
        }
        this.errorDataLayout.addView(this.errorIcon);
        TextView textView = new TextView(getContext());
        this.errorTextView = textView;
        textView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        this.errorTextView.setSingleLine(true);
        this.errorTextView.setTextSize(14.0f);
        this.errorTextView.setGravity(17);
        this.errorTextView.setText("\u4f60\u8bbf\u95ee\u7684\u5185\u5bb9\u98de\u5230\u592a\u7a7a\u4e86");
        this.errorDataLayout.addView(this.errorTextView);
        TextView textView2 = new TextView(getContext());
        this.retryButton = textView2;
        textView2.setLayoutParams(new RelativeLayout.LayoutParams(DPIUtil.dip2px(80.0f), DPIUtil.dip2px(32.0f)));
        if (this.retryButton.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this.retryButton.getLayoutParams()).topMargin = DPIUtil.dip2px(18.0f);
        }
        this.retryButton.setBackgroundResource(R.drawable.recommend_pd_retry_bg);
        this.retryButton.setTextSize(12.0f);
        this.retryButton.setGravity(17);
        this.retryButton.setText("\u70b9\u51fb\u5237\u65b0");
        this.retryButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.ui.product.PDRecommendStateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (((RecommendEmptyView) PDRecommendStateView.this).retryListener != null) {
                    ((RecommendEmptyView) PDRecommendStateView.this).retryListener.emptyRetry();
                }
            }
        });
        this.errorDataLayout.addView(this.retryButton);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendEmptyView
    public void addNoData() {
        if (this.noDataLayout == null) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            this.noDataLayout = linearLayout;
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.noDataLayout.setOrientation(1);
            this.noDataLayout.setGravity(1);
            addView(this.noDataLayout);
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
            this.noDataIcon = simpleDraweeView;
            simpleDraweeView.setLayoutParams(new RelativeLayout.LayoutParams(DPIUtil.dip2px(160.0f), DPIUtil.dip2px(160.0f)));
            if (this.noDataIcon.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) this.noDataIcon.getLayoutParams()).topMargin = DPIUtil.dip2px(96.0f);
            }
            this.noDataLayout.addView(this.noDataIcon);
            TextView textView = new TextView(getContext());
            this.noDataText = textView;
            textView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            this.noDataText.setSingleLine(true);
            this.noDataText.setTextSize(14.0f);
            this.noDataText.setGravity(17);
            this.noDataText.setText("\u6682\u65e0\u66f4\u591a\u63a8\u8350");
            this.noDataLayout.addView(this.noDataText);
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendEmptyView
    @RequiresApi(api = 21)
    public void createView() {
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        setGravity(1);
        addErrorLayout();
        ProgressBar progressBar = new ProgressBar(getContext());
        this.mLoadingView = progressBar;
        addView(progressBar, new RelativeLayout.LayoutParams(DPIUtil.dip2px(64.0f), DPIUtil.dip2px(64.0f)));
        if (this.mLoadingView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this.mLoadingView.getLayoutParams()).topMargin = DPIUtil.dip2px(220.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendEmptyView
    public void footerStateChange(int i2) {
        if (i2 == 1001) {
            RecommendViewUtil.gone(this.mLoadingView);
            RecommendViewUtil.gone(this.errorDataLayout);
            RecommendViewUtil.visible(this.noDataLayout);
            return;
        }
        super.footerStateChange(i2);
    }

    public void setDarkModel(boolean z) {
        this.isLightModel = !z;
        changeViewDarkModel();
    }

    public PDRecommendStateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
