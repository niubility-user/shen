package com.jingdong.common.recommend.ui.homerecommend;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.common.BaseActivity;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class HomeCardMainTitleLayout extends ConstraintLayout {
    private SimpleDraweeView img;
    private Info info;
    private TextView title;

    /* loaded from: classes6.dex */
    public static class Info {
        String maintitleColor;
        String showName;
        String showNameImg;
        String showNameImgWidth;

        private Info() {
        }

        public static Info builder() {
            return new Info();
        }

        public Info setMaintitleColor(String str) {
            this.maintitleColor = str;
            return this;
        }

        public Info setShowName(String str) {
            this.showName = str;
            return this;
        }

        public Info setShowNameImg(String str) {
            this.showNameImg = str;
            return this;
        }

        public Info setShowNameImgWidth(String str) {
            this.showNameImgWidth = str;
            return this;
        }
    }

    public HomeCardMainTitleLayout(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        TextView textView = new TextView(context);
        this.title = textView;
        addView(textView);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.img = simpleDraweeView;
        addView(simpleDraweeView);
    }

    private void realBind(BaseActivity baseActivity, Info info) {
        if (!TextUtils.isEmpty(info.showNameImg)) {
            this.title.setVisibility(8);
            this.img.setVisibility(0);
            setImgWidth(info.showNameImgWidth);
            JDImageLoader.display(info.showNameImg, this.img);
            return;
        }
        this.title.setVisibility(0);
        this.img.setVisibility(8);
        if (!TextUtils.isEmpty(info.maintitleColor)) {
            try {
                this.title.setTextColor(Color.parseColor(info.maintitleColor));
            } catch (Exception unused) {
                this.title.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
            }
        } else {
            this.title.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
        }
        if (!TextUtils.isEmpty(info.showName)) {
            this.title.setText(info.showName);
        } else {
            this.title.setText("");
        }
    }

    private void setImgWidth(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.img.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) layoutParams).width = DPIUtil.getWidthByDesignValue750((Activity) ((BaseActivity) getContext()), Integer.parseInt(str));
            this.img.setLayoutParams(layoutParams);
        } catch (Exception unused) {
        }
    }

    private void setViewLayout(BaseActivity baseActivity) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.title.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams).width = -2;
        ((ViewGroup.MarginLayoutParams) layoutParams).height = -1;
        layoutParams.leftToLeft = 0;
        layoutParams.topToTop = 0;
        this.title.setLayoutParams(layoutParams);
        this.title.setGravity(17);
        this.title.setTextSize(0, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 30));
        this.title.getPaint().setFakeBoldText(true);
        this.title.setSingleLine();
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.img.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams2).width = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 112);
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 32);
        layoutParams2.leftToLeft = 0;
        layoutParams2.topToTop = 0;
        layoutParams2.bottomToBottom = 0;
        this.img.setLayoutParams(layoutParams2);
    }

    public void bind(BaseActivity baseActivity, Info info) {
        if (info != null && ((!TextUtils.isEmpty(info.showName) || !TextUtils.isEmpty(info.showNameImg)) && baseActivity != null)) {
            this.info = info;
            try {
                setViewLayout(baseActivity);
                realBind(baseActivity, info);
                return;
            } catch (Exception unused) {
                return;
            }
        }
        setVisibility(8);
    }

    public HomeCardMainTitleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public HomeCardMainTitleLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView(context);
    }
}
