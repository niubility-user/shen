package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.skin.lib.CodingConstants;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendTemplate;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class RecommendTemplateOneViewHolder extends BaseRecommendTemplateViewHolder {
    private String bgUrl;
    private String imgUrl;
    private SimpleDraweeView recommendBgIV;
    private SimpleDraweeView recommendImgIV;
    private final SimpleDraweeView recommendNameIcon;
    private final LinearLayout recommendNameLayout;
    private TextView recommendNameTV;
    private TextView recommendSubTitleTV;
    private TextView recommendTitleTV;

    public RecommendTemplateOneViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_template_one_bg);
        this.recommendBgIV = simpleDraweeView;
        simpleDraweeView.setAspectRatio(0.617f);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) view.findViewById(R.id.recommend_template_one_img);
        this.recommendImgIV = simpleDraweeView2;
        simpleDraweeView2.setAspectRatio(1.0f);
        this.recommendNameLayout = (LinearLayout) view.findViewById(R.id.recom_template_one_first_title_layout);
        this.recommendNameIcon = (SimpleDraweeView) view.findViewById(R.id.recom_template_one_first_title_icon);
        this.recommendNameTV = (TextView) view.findViewById(R.id.recommend_template_one_name);
        this.recommendTitleTV = (TextView) view.findViewById(R.id.recommend_template_one_title);
        this.recommendSubTitleTV = (TextView) view.findViewById(R.id.recommend_template_one_subtitle);
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendTemplateViewHolder
    public void resetContent() {
        super.resetContent();
        this.recommendNameTV.setText("");
        this.recommendTitleTV.setText("");
        this.recommendSubTitleTV.setText("");
    }

    public void setData(RecommendTemplate recommendTemplate, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2, int i2, int i3) {
        String str;
        String str2;
        this.itemView.setTag(recommendTemplate);
        if (recommendTemplate == null) {
            return;
        }
        resetContent();
        if (this.recommendBgIV.getDrawable() == null || (str2 = this.bgUrl) == null || !str2.equals(recommendTemplate.background)) {
            String str3 = recommendTemplate.background;
            this.bgUrl = str3;
            JDImageUtils.displayImage(str3, this.recommendBgIV, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateOneViewHolder.1
                {
                    RecommendTemplateOneViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str4, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str4, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str4, View view, JDFailReason jDFailReason) {
                    RecommendTemplateOneViewHolder.this.recommendBgIV.setImageResource(R.drawable.recommend_template_bg);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str4, View view) {
                }
            });
        }
        if (this.recommendImgIV.getDrawable() == null || (str = this.imgUrl) == null || !str.equals(recommendTemplate.imgUrlLocal)) {
            String str4 = recommendTemplate.imgUrlLocal;
            this.imgUrl = str4;
            JDImageUtils.displayImage(str4, this.recommendImgIV, jDDisplayImageOptions);
        }
        if (TextUtils.isEmpty(recommendTemplate.firstTitle)) {
            this.recommendNameTV.setVisibility(8);
        } else {
            if (!TextUtils.isEmpty(recommendTemplate.firstTitleIcon)) {
                JDImageUtils.displayImage(recommendTemplate.firstTitleIcon, this.recommendNameIcon, jDDisplayImageOptions, new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateOneViewHolder.2
                    {
                        RecommendTemplateOneViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str5, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str5, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str5, View view, JDFailReason jDFailReason) {
                        RecommendTemplateOneViewHolder.this.recommendNameIcon.setVisibility(8);
                        RecommendTemplateOneViewHolder.this.recommendNameTV.setPadding(DPIUtil.dip2px(6.0f), 0, DPIUtil.dip2px(6.0f), 0);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str5, View view) {
                    }
                });
                this.recommendNameIcon.setVisibility(0);
                this.recommendNameTV.setPadding(0, 0, DPIUtil.dip2px(6.0f), 0);
            } else {
                this.recommendNameIcon.setVisibility(8);
                this.recommendNameTV.setPadding(DPIUtil.dip2px(6.0f), 0, DPIUtil.dip2px(6.0f), 0);
            }
            this.recommendNameTV.setVisibility(0);
            this.recommendNameTV.setText(recommendTemplate.firstTitle);
            this.recommendNameTV.setTextColor(generateColor(recommendTemplate.firstTitleFont, JDDarkUtil.COLOR_1A1A1A));
            RecommendViewUtil.setRightTextSize(this.recommendNameTV, 12, CodingConstants.T2_Text_Bold, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
            int[] iArr = recommendTemplate.firstTitleColors;
            if (iArr != null && iArr.length > 0) {
                generateGradientDrawable(this.recommendNameLayout, DPIUtil.dip2px(10.0f), recommendTemplate.firstTitleColors);
            } else {
                this.recommendNameLayout.setBackgroundDrawable(null);
            }
        }
        if (TextUtils.isEmpty(recommendTemplate.mainTitle)) {
            this.recommendTitleTV.setVisibility(8);
        } else {
            this.recommendTitleTV.setVisibility(0);
            this.recommendTitleTV.setText(recommendTemplate.mainTitle);
            RecommendViewUtil.setRightTextSize(this.recommendTitleTV, 16, CodingConstants.T4_Text_Bold, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
            this.recommendTitleTV.setTextColor(generateColor(recommendTemplate.mainTitleFont, JDDarkUtil.COLOR_1A1A1A));
        }
        if (TextUtils.isEmpty(recommendTemplate.subTitle)) {
            this.recommendSubTitleTV.setVisibility(8);
        } else {
            this.recommendSubTitleTV.setVisibility(0);
            this.recommendSubTitleTV.setText(recommendTemplate.subTitle);
            this.recommendSubTitleTV.setTextColor(generateColor(recommendTemplate.subTitleFont, "#4965F2"));
            RecommendViewUtil.setRightTextSize(this.recommendSubTitleTV, 14, CodingConstants.T3_Text_regular, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
        }
        setTemplateData(recommendTemplate, i2, jDDisplayImageOptions, expoDataStore2, i3);
    }
}
