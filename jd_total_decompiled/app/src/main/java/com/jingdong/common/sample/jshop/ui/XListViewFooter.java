package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class XListViewFooter extends LinearLayout {
    public static final int STATE_LOADING = 2;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    private int colorId;
    private boolean isLast;
    public boolean isNeedProgressBar;
    private View mContentView;
    private Context mContext;
    private TextView mHintView;
    private View mHintViewContainer;
    private View mProgressBar;
    private int stringId;

    public XListViewFooter(Context context) {
        super(context);
        this.stringId = -1;
        this.colorId = 0;
        this.isLast = false;
        this.isNeedProgressBar = true;
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.jshop_brand_footer, (ViewGroup) null);
        addView(linearLayout);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mContentView = linearLayout.findViewById(R.id.xlistview_footer_content);
        this.mHintViewContainer = linearLayout.findViewById(R.id.jshop_footer_content);
        this.mProgressBar = linearLayout.findViewById(R.id.jshop_progress_bar);
        this.mHintView = (TextView) linearLayout.findViewById(R.id.jshop_brand_footer_tv);
    }

    public int getBottomMargin() {
        return ((LinearLayout.LayoutParams) this.mContentView.getLayoutParams()).bottomMargin;
    }

    public void hide() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mContentView.getLayoutParams();
        layoutParams.height = 0;
        this.mContentView.setLayoutParams(layoutParams);
    }

    public void loading() {
        this.mHintView.setVisibility(8);
        this.mProgressBar.setVisibility(0);
    }

    public void normal() {
        this.mHintView.setVisibility(0);
        this.mProgressBar.setVisibility(8);
    }

    public void setBottomMargin(int i2) {
        if (i2 < 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mContentView.getLayoutParams();
        layoutParams.bottomMargin = i2;
        this.mContentView.setLayoutParams(layoutParams);
    }

    public void setFooterText(int i2, boolean z) {
        this.stringId = i2;
        this.isLast = z;
        TextView textView = this.mHintView;
        if (textView != null) {
            textView.setText(i2);
            if (z) {
                this.mHintView.setCompoundDrawables(null, null, null, null);
                return;
            }
            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.ahh);
            drawable.setBounds(0, 0, DPIUtil.dip2px(17.0f), DPIUtil.dip2px(17.0f));
            this.mHintView.setCompoundDrawables(drawable, null, null, null);
        }
    }

    public void setHintViewVisibility(int i2) {
        View view = this.mHintViewContainer;
        if (view != null) {
            view.setVisibility(i2);
        }
    }

    public void setState(int i2) {
        this.mProgressBar.setVisibility(8);
        this.mHintView.setVisibility(0);
        int i3 = this.stringId;
        if (i3 == -1) {
            this.mHintView.setText(R.string.jshop_brand_footer_flip);
        } else {
            this.mHintView.setText(i3);
        }
        if (this.colorId != 0) {
            this.mHintViewContainer.setBackgroundColor(getResources().getColor(this.colorId));
        }
        if (this.isLast) {
            this.mHintView.setCompoundDrawables(null, null, null, null);
        } else {
            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.ahh);
            drawable.setBounds(0, 0, DPIUtil.dip2px(17.0f), DPIUtil.dip2px(17.0f));
            this.mHintView.setCompoundDrawables(drawable, null, null, null);
        }
        if (i2 == 2 && this.isNeedProgressBar) {
            this.mProgressBar.setVisibility(0);
        } else {
            this.mProgressBar.setVisibility(8);
        }
    }

    public void show() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mContentView.getLayoutParams();
        layoutParams.height = -2;
        this.mContentView.setLayoutParams(layoutParams);
    }

    public XListViewFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.stringId = -1;
        this.colorId = 0;
        this.isLast = false;
        this.isNeedProgressBar = true;
        initView(context);
    }

    public void setFooterText(int i2, int i3, boolean z) {
        if (i3 == 0) {
            setFooterText(i2, z);
            return;
        }
        this.stringId = i2;
        this.colorId = i3;
        this.isLast = z;
        TextView textView = this.mHintView;
        if (textView != null) {
            textView.setText(i2);
            this.mHintViewContainer.setBackgroundColor(getResources().getColor(i3));
            if (z) {
                this.mHintView.setCompoundDrawables(null, null, null, null);
                return;
            }
            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.ahh);
            drawable.setBounds(0, 0, DPIUtil.dip2px(17.0f), DPIUtil.dip2px(17.0f));
            this.mHintView.setCompoundDrawables(drawable, null, null, null);
        }
    }
}
