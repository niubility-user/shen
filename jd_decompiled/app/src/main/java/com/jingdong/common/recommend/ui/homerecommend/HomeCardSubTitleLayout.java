package com.jingdong.common.recommend.ui.homerecommend;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.RecommendImageUtils;
import com.jingdong.common.recommend.RecommendTextScaleUtils;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class HomeCardSubTitleLayout extends LinearLayout {
    private final int TITLE_MAX_WIDTH;
    private Info info;
    private TextView title;

    /* loaded from: classes6.dex */
    public static class Info {
        int maxWidth = 0;
        String slogan;
        String sloganColor;
        String sloganTagImg;

        private Info() {
        }

        public static Info builder() {
            return new Info();
        }

        public Info setMaxWidth(int i2) {
            this.maxWidth = i2;
            return this;
        }

        public Info setSlogan(String str) {
            this.slogan = str;
            return this;
        }

        public Info setSloganColor(String str) {
            this.sloganColor = str;
            return this;
        }

        public Info setSloganTagImg(String str) {
            this.sloganTagImg = str;
            return this;
        }
    }

    public HomeCardSubTitleLayout(Context context) {
        super(context);
        this.TITLE_MAX_WIDTH = 154;
        initView(context);
    }

    private GradientDrawable getTitleDefaultBg(BaseActivity baseActivity) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 8));
        gradientDrawable.setColor(Color.parseColor("#FF404F"));
        return gradientDrawable;
    }

    private void initView(Context context) {
        TextView textView = new TextView(context);
        this.title = textView;
        addView(textView);
    }

    private void realBind(BaseActivity baseActivity, Info info) {
        if (!TextUtils.isEmpty(info.sloganTagImg)) {
            RecommendImageUtils.downloadAnddisplay(info.sloganTagImg, this);
        } else {
            setBackground(getTitleDefaultBg(baseActivity));
        }
        if (!TextUtils.isEmpty(info.sloganColor)) {
            try {
                this.title.setTextColor(Color.parseColor(info.sloganColor));
            } catch (Exception unused) {
            }
        }
        if (!TextUtils.isEmpty(info.slogan)) {
            TextView textView = this.title;
            String str = info.slogan;
            int i2 = info.maxWidth;
            textView.setText(RecommendTextScaleUtils.subStringForTextViewToDraw(str, 0, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, i2 == 0 ? 154 : i2 - 16), this.title));
            return;
        }
        this.title.setText("");
    }

    private void setViewLayout(BaseActivity baseActivity) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.title.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -1;
        this.title.setLayoutParams(layoutParams);
        this.title.setGravity(17);
        this.title.setPadding(DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 8), -10, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 8), -10);
        this.title.setTextSize(0, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 22));
        this.title.setTextColor(-1);
        this.title.setSingleLine();
        this.title.setIncludeFontPadding(false);
    }

    public void bind(BaseActivity baseActivity, Info info) {
        if (info != null) {
            if ((TextUtils.isEmpty(info.slogan) && TextUtils.isEmpty(info.sloganTagImg)) || baseActivity == null) {
                return;
            }
            this.info = info;
            try {
                setViewLayout(baseActivity);
                realBind(baseActivity, info);
            } catch (Exception unused) {
            }
        }
    }

    public HomeCardSubTitleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TITLE_MAX_WIDTH = 154;
        initView(context);
    }

    public HomeCardSubTitleLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TITLE_MAX_WIDTH = 154;
        initView(context);
    }
}
