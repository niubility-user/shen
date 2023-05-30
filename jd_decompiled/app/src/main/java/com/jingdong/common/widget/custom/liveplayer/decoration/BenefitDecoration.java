package com.jingdong.common.widget.custom.liveplayer.decoration;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.R;
import com.jingdong.common.widget.custom.liveplayer.bean.LiveSmallWindowBean;
import com.jingdong.common.widget.custom.livewidget.util.BorderDrawable;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class BenefitDecoration implements IDecorator {
    public static final String TYPE_COUPON_DRAW_LOTTERY = "coupon_draw_lottery";
    public static final String TYPE_LIMIT_TIME_SKU = "limit_time_sku";
    public static final String TYPE_LIVE_COUPON = "live_coupon";
    public static final String TYPE_REAL_DRAW_LOTTERY = "real_draw_lottery";
    public static final String TYPE_RED_ENVELOPE = "red_envelope";
    private View mBenefitDecorateView;
    private Context mContext;
    private LiveSmallWindowBean mLiveSmallWindowBean;

    public BenefitDecoration(Context context, LiveSmallWindowBean liveSmallWindowBean) {
        this.mContext = context;
        this.mLiveSmallWindowBean = liveSmallWindowBean;
    }

    private void updateBenefit(String str, String str2, boolean z, RelativeLayout relativeLayout) {
        String str3;
        int i2;
        TextView textView = (TextView) this.mBenefitDecorateView.findViewById(R.id.tv_benefit_content);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) this.mBenefitDecorateView.findViewById(R.id.img_benefit);
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.live_smallwindow_img_voice_mute);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        TextView textView2 = (TextView) relativeLayout.findViewById(R.id.live_player_refresh_small);
        LinearLayout.LayoutParams layoutParams2 = textView2 != null ? (LinearLayout.LayoutParams) textView2.getLayoutParams() : null;
        LinearLayout linearLayout = (LinearLayout) relativeLayout.findViewById(R.id.ll_live_player_fail_to_refresh_small_content);
        FrameLayout.LayoutParams layoutParams3 = linearLayout != null ? (FrameLayout.LayoutParams) linearLayout.getLayoutParams() : null;
        try {
            str3 = JDMobileConfig.getInstance().getConfig("liveroom", "config", "smallwindowUseCusBorderDrawable");
        } catch (Exception e2) {
            e2.printStackTrace();
            str3 = "0";
        }
        int[] iArr = {Color.parseColor("#F53D4C"), Color.parseColor(JDDarkUtil.COLOR_FA2C19)};
        BorderDrawable borderDrawable = new BorderDrawable(DPIUtil.dip2px(1.0f), DPIUtil.dip2px(1.0f), DPIUtil.dip2px(1.0f), DPIUtil.dip2px(20.0f));
        borderDrawable.setColors(iArr);
        borderDrawable.setOrientation(BorderDrawable.Orientation.TOP_BOTTOM);
        borderDrawable.setCornerRadii(new float[]{DPIUtil.dip2px(12.0f), DPIUtil.dip2px(12.0f), DPIUtil.dip2px(12.0f), DPIUtil.dip2px(12.0f), DPIUtil.dip2px(12.0f), DPIUtil.dip2px(12.0f), DPIUtil.dip2px(12.0f), DPIUtil.dip2px(12.0f)}, new float[]{DPIUtil.dip2px(11.0f), DPIUtil.dip2px(11.0f), DPIUtil.dip2px(11.0f), DPIUtil.dip2px(11.0f), DPIUtil.dip2px(4.0f), DPIUtil.dip2px(4.0f), DPIUtil.dip2px(4.0f), DPIUtil.dip2px(4.0f)});
        if ("1".equals(str3)) {
            this.mBenefitDecorateView.setBackground(borderDrawable);
        } else {
            View view = this.mBenefitDecorateView;
            if (z) {
                i2 = R.drawable.live_benefit_landscape_red_bg;
            } else {
                i2 = R.drawable.live_benefit_red_bg;
            }
            view.setBackgroundResource(i2);
        }
        if (!TextUtils.isEmpty(str2)) {
            simpleDraweeView.setVisibility(0);
            textView.setText(str2);
        } else {
            textView.setText("\u53bb\u901b\u901b");
            simpleDraweeView.setVisibility(8);
        }
        layoutParams.bottomMargin = DPIUtil.dip2px(28.0f);
        if (layoutParams2 != null && layoutParams3 != null) {
            if (z) {
                layoutParams2.topMargin = DPIUtil.dip2px(10.0f);
                layoutParams3.bottomMargin = DPIUtil.dip2px(10.0f);
            } else {
                layoutParams2.topMargin = DPIUtil.dip2px(12.0f);
                layoutParams3.bottomMargin = 0;
            }
        }
        imageView.setLayoutParams(layoutParams);
        if (textView2 != null) {
            textView2.setLayoutParams(layoutParams2);
        }
        if (linearLayout != null) {
            linearLayout.setLayoutParams(layoutParams3);
        }
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.decoration.IDecorator
    public void setDecorateView(RelativeLayout relativeLayout, RelativeLayout relativeLayout2) {
        LiveSmallWindowBean liveSmallWindowBean;
        if (relativeLayout2 == null || relativeLayout == null || (liveSmallWindowBean = this.mLiveSmallWindowBean) == null) {
            return;
        }
        String str = liveSmallWindowBean.type;
        boolean equals = "1".equals(liveSmallWindowBean.screen);
        View findViewById = relativeLayout.findViewById(R.id.lay_smallwindow_benefit);
        this.mBenefitDecorateView = findViewById;
        if (findViewById == null) {
            this.mBenefitDecorateView = LayoutInflater.from(this.mContext).inflate(R.layout.layout_smallwindow_benefit_decoration, (ViewGroup) null);
            LiveSmallWindowBean liveSmallWindowBean2 = this.mLiveSmallWindowBean;
            updateBenefit(liveSmallWindowBean2.benefitIconUrl, liveSmallWindowBean2.benefitText, equals, relativeLayout2);
            relativeLayout.addView(this.mBenefitDecorateView, new RelativeLayout.LayoutParams(-1, -1));
            return;
        }
        LiveSmallWindowBean liveSmallWindowBean3 = this.mLiveSmallWindowBean;
        updateBenefit(liveSmallWindowBean3.benefitIconUrl, liveSmallWindowBean3.benefitText, equals, relativeLayout2);
    }
}
