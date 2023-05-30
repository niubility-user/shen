package com.jingdong.common.recommend.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class RecommendEmptyView extends RelativeLayout {
    public static final int FOOTER_EMPTY = 1001;
    public static final int FOOTER_ERROR = 1003;
    public static final int FOOTER_LOADING = 1002;
    public static final int LOADING_TOP_MARGIN = (DPIUtil.getHeight() * 2) / 5;
    protected LinearLayout errorDataLayout;
    private int footerState;
    protected ProgressBar mLoadingView;
    protected LinearLayout noDataLayout;
    protected TextView retryButton;
    protected RetryListener retryListener;

    /* loaded from: classes6.dex */
    public interface RetryListener {
        void emptyRetry();
    }

    public RecommendEmptyView(Context context) {
        this(context, null);
    }

    private void setViewState(final int i2) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            post(new Runnable() { // from class: com.jingdong.common.recommend.ui.RecommendEmptyView.2
                @Override // java.lang.Runnable
                public void run() {
                    RecommendEmptyView.this.footerStateChange(i2);
                }
            });
        } else {
            footerStateChange(i2);
        }
    }

    public void addErrorLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.errorDataLayout = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.errorDataLayout.setOrientation(1);
        this.errorDataLayout.setGravity(1);
        addView(this.errorDataLayout);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        simpleDraweeView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        if (simpleDraweeView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) simpleDraweeView.getLayoutParams()).topMargin = DPIUtil.dip2px(100.0f);
        }
        simpleDraweeView.setBackgroundResource(R.drawable.y_03);
        this.errorDataLayout.addView(simpleDraweeView);
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        textView.setSingleLine(true);
        textView.setTextColor(Color.parseColor("#BFBFBF"));
        textView.setTextSize(17.0f);
        textView.setGravity(17);
        textView.setText("\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25");
        this.errorDataLayout.addView(textView);
        TextView textView2 = new TextView(getContext());
        textView2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        textView2.setSingleLine(true);
        if (textView2.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) textView2.getLayoutParams()).topMargin = DPIUtil.dip2px(6.0f);
        }
        textView2.setGravity(17);
        textView2.setTextColor(Color.parseColor("#BFBFBF"));
        textView2.setText("\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc");
        textView2.setTextSize(14.0f);
        this.errorDataLayout.addView(textView2);
        Button button = new Button(getContext());
        this.retryButton = button;
        button.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.retryButton.setPadding(DPIUtil.dip2px(30.0f), DPIUtil.dip2px(10.0f), DPIUtil.dip2px(30.0f), DPIUtil.dip2px(10.0f));
        if (this.retryButton.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this.retryButton.getLayoutParams()).topMargin = DPIUtil.dip2px(26.0f);
        }
        this.retryButton.setTextSize(14.0f);
        this.retryButton.setTextColor(Color.parseColor("#686868"));
        this.retryButton.setBackgroundResource(R.drawable.recommend_retry_bg);
        this.retryButton.setText("\u91cd\u65b0\u52a0\u8f7d");
        this.retryButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.ui.RecommendEmptyView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RetryListener retryListener = RecommendEmptyView.this.retryListener;
                if (retryListener != null) {
                    retryListener.emptyRetry();
                }
            }
        });
        this.errorDataLayout.addView(this.retryButton);
    }

    public void addNoData() {
    }

    protected void createView() {
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addErrorLayout();
        JDProgressBar jDProgressBar = new JDProgressBar(getContext());
        this.mLoadingView = jDProgressBar;
        if (jDProgressBar.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) this.mLoadingView.getLayoutParams()).topMargin = LOADING_TOP_MARGIN;
        }
        addView(this.mLoadingView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void footerStateChange(int i2) {
        if (i2 == 1002) {
            this.mLoadingView.setVisibility(0);
            this.errorDataLayout.setVisibility(8);
            RecommendViewUtil.gone(this.noDataLayout);
        } else if (i2 != 1003) {
            RecommendViewUtil.gone(this.mLoadingView);
            RecommendViewUtil.gone(this.errorDataLayout);
            RecommendViewUtil.gone(this.noDataLayout);
        } else {
            this.mLoadingView.setVisibility(8);
            this.errorDataLayout.setVisibility(0);
            RecommendViewUtil.gone(this.noDataLayout);
        }
    }

    public void setFooterState(int i2) {
        if (this.footerState != i2) {
            this.footerState = i2;
            setViewState(i2);
        }
    }

    public void setRetryListener(RetryListener retryListener) {
        this.retryListener = retryListener;
    }

    public RecommendEmptyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        createView();
    }
}
