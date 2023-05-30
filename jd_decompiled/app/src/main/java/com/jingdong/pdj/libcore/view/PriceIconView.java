package com.jingdong.pdj.libcore.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.pdj.libcore.utils.HourlyGoColorUtil;
import com.jingdong.pdj.libcore.utils.HourlyGoFontUtils;
import com.jingdong.pdj.libcore.utils.HourlyGoLibConstant;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes7.dex */
public class PriceIconView extends LinearLayout {
    TextView disCountView;
    private final Context mContext;
    SimpleDraweeView simpleDraweeView;
    TextView subPriceView;

    public PriceIconView(Context context) {
        this(context, null);
    }

    private void addPriceView() {
        this.disCountView = new TextView(this.mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 80;
        this.disCountView.setMaxLines(1);
        this.disCountView.setIncludeFontPadding(false);
        this.disCountView.setGravity(80);
        addView(this.disCountView, layoutParams);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.mContext);
        this.simpleDraweeView = simpleDraweeView;
        addView(simpleDraweeView);
        this.subPriceView = new TextView(this.mContext);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 80;
        this.subPriceView.setMaxLines(1);
        this.subPriceView.setIncludeFontPadding(false);
        this.subPriceView.setGravity(80);
        this.subPriceView.setVisibility(8);
        addView(this.subPriceView, layoutParams2);
    }

    public void setPriceTextAndSize(String str, int i2, int i3, int i4, int i5) {
        if (this.disCountView != null) {
            if (!TextUtils.isEmpty(str)) {
                HourlyGoFontUtils.setPrice(this.disCountView, str, i2, i3, i4);
            }
            if (i5 == 2) {
                this.disCountView.getPaint().setFlags(17);
            }
        }
    }

    public void setSubPriceTextAndSize(String str, int i2, int i3, int i4, boolean z) {
        if (this.subPriceView != null) {
            if (!TextUtils.isEmpty(str)) {
                this.subPriceView.setVisibility(0);
                HourlyGoFontUtils.setPrice(this.subPriceView, str, i2, i3, i4);
            } else {
                this.subPriceView.setVisibility(8);
            }
            if (z) {
                this.subPriceView.getPaint().setFlags(17);
            }
        }
    }

    public void setSubTextColor(boolean z, String str, String str2) {
        try {
            if (z) {
                if (!TextUtils.isEmpty(str2)) {
                    this.subPriceView.setTextColor(HourlyGoColorUtil.parseColor(str2));
                } else {
                    this.subPriceView.setTextColor(-1);
                }
            } else if (!TextUtils.isEmpty(str)) {
                this.subPriceView.setTextColor(HourlyGoColorUtil.parseColor(str));
            } else {
                this.subPriceView.setTextColor(-1);
            }
        } catch (Exception e2) {
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
        }
    }

    public void setTextColor(boolean z, String str, String str2) {
        try {
            if (z) {
                if (!TextUtils.isEmpty(str2)) {
                    this.disCountView.setTextColor(HourlyGoColorUtil.parseColor(str2));
                } else {
                    this.disCountView.setTextColor(-1);
                }
            } else if (!TextUtils.isEmpty(str)) {
                this.disCountView.setTextColor(HourlyGoColorUtil.parseColor(str));
            } else {
                this.disCountView.setTextColor(-1);
            }
        } catch (Exception e2) {
            OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
        }
    }

    public void setTextTag(String str, int i2, int i3) {
        if (this.simpleDraweeView == null || TextUtils.isEmpty(str)) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.simpleDraweeView.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            if (i2 == 0) {
                i2 = -2;
            }
            layoutParams2.width = i2;
            if (i3 == 0) {
                i3 = -2;
            }
            layoutParams2.height = i3;
            layoutParams2.gravity = 80;
            TextView textView = this.disCountView;
            if (textView != null) {
                layoutParams2.bottomMargin = (int) textView.getPaint().descent();
            }
            this.simpleDraweeView.setLayoutParams(layoutParams2);
            try {
                JDImageUtils.displayImage(str, this.simpleDraweeView);
            } catch (Exception e2) {
                OKLog.e(HourlyGoLibConstant.HOURLY_GO_EXCEPTION, Log.getStackTraceString(e2));
            }
            this.simpleDraweeView.setLayoutParams(layoutParams2);
        }
    }

    public PriceIconView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PriceIconView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        setOrientation(0);
        addPriceView();
    }
}
