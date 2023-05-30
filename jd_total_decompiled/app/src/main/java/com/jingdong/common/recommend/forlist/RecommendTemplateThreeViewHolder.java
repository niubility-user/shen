package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendTemplate;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes6.dex */
public class RecommendTemplateThreeViewHolder extends BaseRecommendTemplateViewHolder {
    private String bgUrl;
    private String imgUrl;
    private SimpleDraweeView recommendBgIV;
    private SimpleDraweeView recommendImgIV;
    private TextView recommendSubTitleTV;
    private View recommendTitleContainer;
    private SimpleDraweeView recommendTitleIcon;
    private TextView recommendTitleTV;

    public RecommendTemplateThreeViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_template_bg);
        this.recommendBgIV = simpleDraweeView;
        simpleDraweeView.setAspectRatio(0.66f);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) view.findViewById(R.id.recommend_template_img);
        this.recommendImgIV = simpleDraweeView2;
        simpleDraweeView2.setAspectRatio(1.0f);
        this.recommendTitleContainer = view.findViewById(R.id.recommend_template_title_container);
        this.recommendTitleIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_template_title_img);
        this.recommendTitleTV = (TextView) view.findViewById(R.id.recommend_template_title);
        this.recommendSubTitleTV = (TextView) view.findViewById(R.id.recommend_template_subtitle);
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendTemplateViewHolder
    public void resetContent() {
        super.resetContent();
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
            JDImageUtils.displayImage(str3, this.recommendBgIV, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateThreeViewHolder.1
                {
                    RecommendTemplateThreeViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str4, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str4, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str4, View view, JDFailReason jDFailReason) {
                    RecommendTemplateThreeViewHolder.this.recommendBgIV.setImageResource(R.drawable.recommend_template_bg);
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
        if (TextUtils.isEmpty(recommendTemplate.mainTitle)) {
            this.recommendTitleContainer.setVisibility(8);
        } else {
            this.recommendTitleContainer.setVisibility(0);
            this.recommendTitleTV.setText(recommendTemplate.mainTitle);
            this.recommendTitleTV.setTextColor(generateColor(recommendTemplate.mainTitleFont, JDDarkUtil.COLOR_1A1A1A));
            if (TextUtils.isEmpty(recommendTemplate.itemPic)) {
                this.recommendTitleIcon.setVisibility(8);
            } else {
                this.recommendTitleIcon.setVisibility(0);
                JDImageUtils.displayImage(recommendTemplate.itemPic, this.recommendTitleIcon, jDDisplayImageOptions);
            }
            RecommendViewUtil.setRightTextSize(this.recommendTitleTV, 15, "", RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
        }
        if (TextUtils.isEmpty(recommendTemplate.subTitle)) {
            this.recommendSubTitleTV.setVisibility(8);
        } else {
            this.recommendSubTitleTV.setVisibility(0);
            this.recommendSubTitleTV.setText(recommendTemplate.subTitle);
            this.recommendSubTitleTV.setTextColor(generateColor(recommendTemplate.subTitleFont, "#4965F2"));
            RecommendViewUtil.setRightTextSize(this.recommendSubTitleTV, 14, "", RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
        }
        setTemplateData(recommendTemplate, i2, jDDisplayImageOptions, expoDataStore2, i3);
    }
}
