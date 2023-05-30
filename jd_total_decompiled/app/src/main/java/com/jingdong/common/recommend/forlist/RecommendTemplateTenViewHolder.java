package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
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
public class RecommendTemplateTenViewHolder extends BaseRecommendMaterialViewHolder {
    private BaseActivity activity;
    private TextView attentionNumTV;
    private TextView entranceTV;
    protected int from;
    private String imgUrl;
    private View.OnClickListener jumpClickListener;
    private RelativeLayout lastTitleBg;
    private SimpleDraweeView lastTitleIcon;
    private SimpleDraweeView mainImg;
    private TextView mainTitleTV;
    private SimpleDraweeView storeLogo;

    public RecommendTemplateTenViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.jumpClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateTenViewHolder.2
            {
                RecommendTemplateTenViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
                RecommendTemplate recommendTemplate = (RecommendTemplate) view2.getTag();
                if (recommendTemplate == null || (onRecommendClickedListener = RecommendTemplateTenViewHolder.this.clickedListener) == null) {
                    return;
                }
                onRecommendClickedListener.onRecommendJump(recommendTemplate.jump, recommendTemplate.isOpenApp);
                if (recommendTemplate.isFromCache) {
                    return;
                }
                RecommendTemplateTenViewHolder.this.onAdClick(recommendTemplate.client_click_url);
                RecommendMtaUtils.aggregationClickMtaRealize(RecommendTemplateTenViewHolder.this.itemView.getContext(), recommendTemplate.sourceValue, RecommendTemplateTenViewHolder.this.from, recommendTemplate.extension_id);
            }
        };
        this.activity = baseActivity;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_template_store_img);
        this.mainImg = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.storeLogo = (SimpleDraweeView) view.findViewById(R.id.recommend_template_store_logo);
        this.mainTitleTV = (TextView) view.findViewById(R.id.recommend_template_store_title);
        this.entranceTV = (TextView) view.findViewById(R.id.recommend_template_store_entrance);
        this.lastTitleIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_template_store_icon);
        this.lastTitleBg = (RelativeLayout) view.findViewById(R.id.recommend_template_entrance_bg);
        this.attentionNumTV = (TextView) view.findViewById(R.id.recommend_template_store_attention_num);
    }

    private void resetView() {
        this.storeLogo.setVisibility(8);
        this.mainTitleTV.setVisibility(8);
        this.attentionNumTV.setVisibility(8);
        this.lastTitleBg.setVisibility(8);
    }

    public void setData(RecommendTemplate recommendTemplate, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2, int i3) {
        String str;
        if (recommendTemplate != null) {
            resetView();
            this.from = i2;
            this.itemView.setTag(recommendTemplate);
            setAdData(recommendTemplate);
            this.itemView.setOnClickListener(this.jumpClickListener);
            if (this.mainImg.getDrawable() == null || (str = this.imgUrl) == null || !str.equals(recommendTemplate.imgUrlLocal)) {
                String str2 = recommendTemplate.imgUrlLocal;
                this.imgUrl = str2;
                JDImageUtils.displayImage(str2, this.mainImg, jDDisplayImageOptions);
            }
            if (!TextUtils.isEmpty(recommendTemplate.itemPic)) {
                this.storeLogo.setVisibility(0);
                JDImageUtils.displayImage(recommendTemplate.itemPic, this.storeLogo, jDDisplayImageOptions);
            }
            if (!TextUtils.isEmpty(recommendTemplate.mainTitle)) {
                this.mainTitleTV.setVisibility(0);
                this.mainTitleTV.setText(recommendTemplate.mainTitle);
                this.mainTitleTV.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.mainTitleFont, JDDarkUtil.COLOR_1A1A1A));
            }
            if (!TextUtils.isEmpty(recommendTemplate.subTitle)) {
                this.attentionNumTV.setVisibility(0);
                this.attentionNumTV.setText(recommendTemplate.subTitle);
                this.attentionNumTV.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.subTitleFont, JDDarkUtil.COLOR_808080));
            }
            if (!TextUtils.isEmpty(recommendTemplate.lastTitle.get(0))) {
                this.lastTitleBg.setVisibility(0);
                this.entranceTV.setText(recommendTemplate.lastTitle.get(0));
                JDImageUtils.displayImage(recommendTemplate.lastTitleIcon, this.lastTitleIcon, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateTenViewHolder.1
                    {
                        RecommendTemplateTenViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str3, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                        RecommendTemplateTenViewHolder.this.lastTitleIcon.setImageResource(R.drawable.recommend_template_store_icon);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str3, View view) {
                    }
                });
                this.entranceTV.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.lastTitleFont, "#5C5C5C"));
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(DPIUtil.dip2px(19.0f));
                gradientDrawable.setColor(RecommendViewUtil.generateColor(recommendTemplate.lastTitleBg, JDDarkUtil.COLOR_F5F5F5));
                this.lastTitleBg.setBackgroundDrawable(gradientDrawable);
            }
            if (expoDataStore != null && !recommendTemplate.isFromCache) {
                if (!TextUtils.isEmpty(recommendTemplate.exposureJsonSourceValue)) {
                    expoDataStore.putExpoJsonDada(recommendTemplate.exposureJsonSourceValue);
                } else if (!TextUtils.isEmpty(recommendTemplate.exposureSourceValue)) {
                    expoDataStore.putExpoData(recommendTemplate.exposureSourceValue);
                }
            }
            setMaterialData(recommendTemplate, i3);
        }
    }
}
