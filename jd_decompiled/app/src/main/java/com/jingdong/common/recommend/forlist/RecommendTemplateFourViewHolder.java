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
import com.jingdong.common.recommend.entity.SubSkuData;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTemplateFourViewHolder extends BaseRecommendTemplateViewHolder {
    private String bgUrl;
    private View constraintRoot;
    private String imgUrl;
    private SimpleDraweeView leftBottomViewStub;
    private SimpleDraweeView leftTopViewStub;
    private SimpleDraweeView recommendBgIV;
    private SimpleDraweeView recommendImgIV;
    private TextView recommendNameTV;
    private TextView recommendSubTitleTV;
    private TextView recommendTitleTV;
    private SimpleDraweeView rightBottomViewStub;
    private SimpleDraweeView rightTopViewStub;

    public RecommendTemplateFourViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_template_bg);
        this.recommendBgIV = simpleDraweeView;
        simpleDraweeView.setAspectRatio(0.617f);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) view.findViewById(R.id.recommend_template_img);
        this.recommendImgIV = simpleDraweeView2;
        simpleDraweeView2.setAspectRatio(1.0f);
        this.recommendNameTV = (TextView) view.findViewById(R.id.recommend_template_name);
        this.recommendTitleTV = (TextView) view.findViewById(R.id.recommend_template_title);
        this.recommendSubTitleTV = (TextView) view.findViewById(R.id.recommend_template_subtitle);
        this.constraintRoot = view.findViewById(R.id.recommend_template_four_constraintlayout);
        this.leftTopViewStub = (SimpleDraweeView) view.findViewById(R.id.recommend_template_left_top_viewstub);
        this.rightTopViewStub = (SimpleDraweeView) view.findViewById(R.id.recommend_template_right_top_viewstub);
        this.leftBottomViewStub = (SimpleDraweeView) view.findViewById(R.id.recommend_template_left_bottom_viewstub);
        this.rightBottomViewStub = (SimpleDraweeView) view.findViewById(R.id.recommend_template_right_bottom_viewstub);
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
            JDImageUtils.displayImage(str3, this.recommendBgIV, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateFourViewHolder.1
                {
                    RecommendTemplateFourViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str4, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str4, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str4, View view, JDFailReason jDFailReason) {
                    RecommendTemplateFourViewHolder.this.recommendBgIV.setImageResource(R.drawable.recommend_template_bg);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str4, View view) {
                }
            });
        }
        ArrayList<SubSkuData> arrayList = recommendTemplate.subsku;
        if (arrayList != null && arrayList.size() > 3) {
            this.recommendImgIV.setVisibility(4);
            this.constraintRoot.setVisibility(0);
            if (recommendTemplate.subsku.get(0) != null) {
                JDImageUtils.displayImage(recommendTemplate.subsku.get(0).img, this.leftTopViewStub, jDDisplayImageOptions);
            }
            if (recommendTemplate.subsku.get(1) != null) {
                JDImageUtils.displayImage(recommendTemplate.subsku.get(1).img, this.rightTopViewStub, jDDisplayImageOptions);
            }
            if (recommendTemplate.subsku.get(2) != null) {
                JDImageUtils.displayImage(recommendTemplate.subsku.get(2).img, this.leftBottomViewStub, jDDisplayImageOptions);
            }
            if (recommendTemplate.subsku.get(3) != null) {
                JDImageUtils.displayImage(recommendTemplate.subsku.get(3).img, this.rightBottomViewStub, jDDisplayImageOptions);
            }
        } else if (this.recommendImgIV.getDrawable() == null || (str = this.imgUrl) == null || !str.equals(recommendTemplate.imgUrlLocal)) {
            this.constraintRoot.setVisibility(8);
            this.recommendImgIV.setVisibility(0);
            String str4 = recommendTemplate.imgUrlLocal;
            this.imgUrl = str4;
            JDImageUtils.displayImage(str4, this.recommendImgIV, jDDisplayImageOptions);
        }
        if (TextUtils.isEmpty(recommendTemplate.firstTitle)) {
            this.recommendNameTV.setVisibility(8);
        } else {
            this.recommendNameTV.setVisibility(0);
            this.recommendNameTV.setText(recommendTemplate.firstTitle);
            this.recommendNameTV.setTextColor(generateColor(recommendTemplate.firstTitleFont, JDDarkUtil.COLOR_1A1A1A));
            int[] iArr = recommendTemplate.firstTitleColors;
            if (iArr != null && iArr.length > 0) {
                generateGradientDrawable(this.recommendNameTV, DPIUtil.dip2px(10.0f), recommendTemplate.firstTitleColors);
            } else {
                this.recommendNameTV.setBackgroundDrawable(null);
            }
            RecommendViewUtil.setRightTextSize(this.recommendNameTV, 12, "", RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
        }
        if (TextUtils.isEmpty(recommendTemplate.mainTitle)) {
            this.recommendTitleTV.setVisibility(8);
        } else {
            this.recommendTitleTV.setVisibility(0);
            RecommendViewUtil.setRightTextSize(this.recommendTitleTV, 15, "", RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig));
            this.recommendTitleTV.setText(recommendTemplate.mainTitle);
            this.recommendTitleTV.setTextColor(generateColor(recommendTemplate.mainTitleFont, JDDarkUtil.COLOR_1A1A1A));
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
