package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendTemplate;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class RecommendTemplateTwelveViewHolder extends BaseRecommendMaterialViewHolder {
    private String bgUrl;
    protected int from;
    private String imgUrl;
    private View.OnClickListener jumpClickListener;
    private SimpleDraweeView recommendBgIV;
    private SimpleDraweeView recommendImgIV;
    private TextView recommendNameTV;
    private LinearLayout recommendNameTvContainer;
    private SimpleDraweeView recommendNameTvIcon;
    private TextView recommendSubTitleTV;
    private TextView recommendTitleTV;

    public RecommendTemplateTwelveViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.jumpClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateTwelveViewHolder.2
            {
                RecommendTemplateTwelveViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
                RecommendTemplate recommendTemplate = (RecommendTemplate) view2.getTag();
                if (recommendTemplate == null || (onRecommendClickedListener = RecommendTemplateTwelveViewHolder.this.clickedListener) == null) {
                    return;
                }
                onRecommendClickedListener.onRecommendJump(recommendTemplate.jump, recommendTemplate.isOpenApp);
                if (recommendTemplate.isFromCache) {
                    return;
                }
                RecommendTemplateTwelveViewHolder.this.onAdClick(recommendTemplate.client_click_url);
                RecommendMtaUtils.aggregationClickMtaRealize(RecommendTemplateTwelveViewHolder.this.itemView.getContext(), recommendTemplate.sourceValue, RecommendTemplateTwelveViewHolder.this.from, recommendTemplate.extension_id);
            }
        };
        this.recommendBgIV = (SimpleDraweeView) view.findViewById(R.id.recommend_template_twelve_bg);
        this.recommendImgIV = (SimpleDraweeView) view.findViewById(R.id.recommend_template_twelve_img);
        this.recommendNameTV = (TextView) view.findViewById(R.id.recommend_template_twelve_name);
        this.recommendTitleTV = (TextView) view.findViewById(R.id.recommend_template_twelve_title);
        this.recommendSubTitleTV = (TextView) view.findViewById(R.id.recommend_template_twelve_subtitle);
        this.recommendNameTvContainer = (LinearLayout) view.findViewById(R.id.recommend_template_twelve_name_container);
        this.recommendNameTvIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_template_twelve_name_icon);
    }

    public void setData(RecommendTemplate recommendTemplate, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2, int i2, int i3) {
        String str;
        String str2;
        this.itemView.setTag(recommendTemplate);
        if (recommendTemplate == null) {
            return;
        }
        this.from = i2;
        this.recommendNameTV.setText("");
        this.recommendTitleTV.setText("");
        this.recommendSubTitleTV.setText("");
        if (this.recommendBgIV.getDrawable() == null || (str2 = this.bgUrl) == null || !str2.equals(recommendTemplate.background)) {
            String str3 = recommendTemplate.background;
            this.bgUrl = str3;
            JDImageUtils.displayImage(str3, this.recommendBgIV, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateTwelveViewHolder.1
                {
                    RecommendTemplateTwelveViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str4, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str4, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str4, View view, JDFailReason jDFailReason) {
                    RecommendTemplateTwelveViewHolder.this.recommendBgIV.setImageResource(R.drawable.recommend_template_bg);
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
            this.recommendNameTvContainer.setVisibility(8);
        } else {
            this.recommendNameTvContainer.setVisibility(0);
            this.recommendNameTV.setText(recommendTemplate.firstTitle);
            this.recommendNameTV.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.firstTitleFont, JDDarkUtil.COLOR_1A1A1A));
            int[] iArr = recommendTemplate.firstTitleColors;
            if (iArr != null && iArr.length > 0) {
                RecommendViewUtil.generateGradientDrawable(this.recommendNameTvContainer, DPIUtil.dip2px(10.0f), recommendTemplate.firstTitleColors);
            } else {
                this.recommendNameTvContainer.setBackgroundDrawable(null);
            }
            if (!TextUtils.isEmpty(recommendTemplate.firstTitleIcon)) {
                this.recommendNameTvIcon.setVisibility(0);
                JDImageUtils.displayImage(recommendTemplate.firstTitleIcon, this.recommendNameTvIcon, jDDisplayImageOptions);
            } else {
                this.recommendNameTvIcon.setVisibility(8);
            }
        }
        if (TextUtils.isEmpty(recommendTemplate.mainTitle)) {
            this.recommendTitleTV.setVisibility(8);
        } else {
            this.recommendTitleTV.setVisibility(0);
            this.recommendTitleTV.setText(recommendTemplate.mainTitle);
            this.recommendTitleTV.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.mainTitleFont, JDDarkUtil.COLOR_1A1A1A));
        }
        if (TextUtils.isEmpty(recommendTemplate.subTitle)) {
            this.recommendSubTitleTV.setVisibility(8);
        } else {
            this.recommendSubTitleTV.setVisibility(0);
            this.recommendSubTitleTV.setText(recommendTemplate.subTitle);
            this.recommendSubTitleTV.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.subTitleFont, "#4965F2"));
        }
        this.itemView.setOnClickListener(this.jumpClickListener);
        setMaterialData(recommendTemplate, i3);
        setAdData(recommendTemplate);
        if (expoDataStore2 == null || recommendTemplate.isFromCache) {
            return;
        }
        if (!TextUtils.isEmpty(recommendTemplate.exposureJsonSourceValue)) {
            expoDataStore2.putExpoJsonDada(recommendTemplate.exposureJsonSourceValue);
        } else if (TextUtils.isEmpty(recommendTemplate.exposureSourceValue)) {
        } else {
            expoDataStore2.putExpoData(recommendTemplate.exposureSourceValue);
        }
    }
}
