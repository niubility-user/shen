package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
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
import com.jingdong.common.recommend.entity.SubSkuData;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.StringUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTemplateNineViewHolder extends BaseRecommendMaterialViewHolder {
    private BaseActivity activity;
    private SimpleDraweeView background;
    private String bgUrl;
    private SimpleDraweeView bottomIcon;
    private SimpleDraweeView firstImg;
    private SimpleDraweeView flame1;
    private SimpleDraweeView flame2;
    private SimpleDraweeView flame3;
    protected int from;
    private SimpleDraweeView hintBg1;
    private SimpleDraweeView hintBg2;
    private SimpleDraweeView hintBg3;
    private SimpleDraweeView icon;
    private SimpleDraweeView icon1;
    private SimpleDraweeView icon2;
    private SimpleDraweeView icon3;
    private View.OnClickListener jumpClickListener;
    private TextView name1;
    private TextView name2;
    private TextView name3;
    private TextView num1;
    private TextView num2;
    private TextView num3;
    private SimpleDraweeView secondImg;
    private SimpleDraweeView thirdImg;
    private TextView title;

    public RecommendTemplateNineViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.jumpClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateNineViewHolder.2
            {
                RecommendTemplateNineViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
                RecommendTemplate recommendTemplate = (RecommendTemplate) view2.getTag();
                if (recommendTemplate == null || (onRecommendClickedListener = RecommendTemplateNineViewHolder.this.clickedListener) == null) {
                    return;
                }
                onRecommendClickedListener.onRecommendJump(recommendTemplate.jump, recommendTemplate.isOpenApp);
                if (recommendTemplate.isFromCache) {
                    return;
                }
                RecommendTemplateNineViewHolder.this.onAdClick(recommendTemplate.client_click_url);
                RecommendMtaUtils.aggregationClickMtaRealize(RecommendTemplateNineViewHolder.this.itemView.getContext(), recommendTemplate.sourceValue, RecommendTemplateNineViewHolder.this.from, recommendTemplate.extension_id);
            }
        };
        this.activity = baseActivity;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_goods_background);
        this.background = simpleDraweeView;
        simpleDraweeView.setAspectRatio(0.615f);
        this.icon = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_goods_icon);
        this.title = (TextView) view.findViewById(R.id.recommend_selling_goods_title);
        this.firstImg = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_goods_first_img);
        this.secondImg = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_goods_second_img);
        this.thirdImg = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_goods_third_img);
        this.icon1 = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_left_top_icon1);
        this.icon2 = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_left_top_icon2);
        this.icon3 = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_left_top_icon3);
        this.bottomIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_left_bottom_icon);
        this.name1 = (TextView) view.findViewById(R.id.recommend_selling_goods_name1);
        this.name2 = (TextView) view.findViewById(R.id.recommend_selling_goods_name2);
        this.name3 = (TextView) view.findViewById(R.id.recommend_selling_goods_name3);
        this.hintBg1 = (SimpleDraweeView) view.findViewById(R.id.recommend_bought_hint_bg1);
        this.hintBg2 = (SimpleDraweeView) view.findViewById(R.id.recommend_bought_hint_bg2);
        this.hintBg3 = (SimpleDraweeView) view.findViewById(R.id.recommend_bought_hint_bg3);
        this.flame1 = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_list_flame1);
        this.flame2 = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_list_flame2);
        this.flame3 = (SimpleDraweeView) view.findViewById(R.id.recommend_selling_list_flame3);
        this.num1 = (TextView) view.findViewById(R.id.recommend_selling_list_bought_num1);
        this.num2 = (TextView) view.findViewById(R.id.recommend_selling_list_bought_num2);
        this.num3 = (TextView) view.findViewById(R.id.recommend_selling_list_bought_num3);
    }

    private void reset() {
        this.firstImg.setVisibility(8);
        this.secondImg.setVisibility(8);
        this.thirdImg.setVisibility(8);
        this.name1.setVisibility(8);
        this.name2.setVisibility(8);
        this.name3.setVisibility(8);
        this.icon1.setVisibility(8);
        this.icon2.setVisibility(8);
        this.icon3.setVisibility(8);
        this.bottomIcon.setVisibility(8);
        this.hintBg1.setVisibility(8);
        this.hintBg2.setVisibility(8);
        this.hintBg3.setVisibility(8);
        this.flame1.setVisibility(8);
        this.flame2.setVisibility(8);
        this.flame3.setVisibility(8);
        this.num1.setVisibility(8);
        this.num2.setVisibility(8);
        this.num3.setVisibility(8);
    }

    public void setData(RecommendTemplate recommendTemplate, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2, int i2, int i3) {
        String str;
        this.itemView.setTag(recommendTemplate);
        this.from = i2;
        setFrom(i2);
        if (recommendTemplate == null) {
            return;
        }
        reset();
        setAdData(recommendTemplate);
        this.itemView.setOnClickListener(this.jumpClickListener);
        if (this.background.getDrawable() == null || (str = this.bgUrl) == null || !str.equals(recommendTemplate.background)) {
            String str2 = recommendTemplate.background;
            this.bgUrl = str2;
            JDImageUtils.displayImage(str2, this.background, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateNineViewHolder.1
                {
                    RecommendTemplateNineViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str3, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                    RecommendTemplateNineViewHolder.this.background.setImageResource(R.drawable.recommend_template_nine_bg);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str3, View view) {
                }
            });
        }
        if (!StringUtil.isEmpty(recommendTemplate.itemPic)) {
            this.icon.setVisibility(0);
            JDImageUtils.displayImage(recommendTemplate.itemPic, this.icon, jDDisplayImageOptions);
        } else {
            this.icon.setVisibility(8);
        }
        if (!StringUtil.isEmpty(recommendTemplate.firstTitle)) {
            this.title.setVisibility(0);
            this.title.setText(recommendTemplate.firstTitle);
            this.title.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.firstTitleFont, "#CC9C0A"));
        } else {
            this.title.setVisibility(8);
        }
        ArrayList<SubSkuData> arrayList = recommendTemplate.subsku;
        if (arrayList != null && arrayList.size() > 2) {
            if (recommendTemplate.subsku.get(0) != null) {
                SubSkuData subSkuData = recommendTemplate.subsku.get(0);
                this.firstImg.setVisibility(0);
                JDImageUtils.displayImage(subSkuData.img, (ImageView) this.firstImg, jDDisplayImageOptions, true);
                if (!StringUtil.isEmpty(subSkuData.hit)) {
                    this.name1.setVisibility(0);
                    this.name1.setText(subSkuData.hit);
                    this.name1.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.mainTitleFont, JDDarkUtil.COLOR_1A1A1A));
                } else {
                    this.name1.setVisibility(8);
                }
                if (!StringUtil.isEmpty(subSkuData.leftTopIcon)) {
                    this.icon1.setVisibility(0);
                    JDImageUtils.displayImage(subSkuData.leftTopIcon, this.icon1, jDDisplayImageOptions);
                } else {
                    this.icon1.setVisibility(8);
                }
                if (!StringUtil.isEmpty(subSkuData.leftBottomIcon)) {
                    this.bottomIcon.setVisibility(0);
                    JDImageUtils.displayImage(subSkuData.leftBottomIcon, this.bottomIcon, jDDisplayImageOptions);
                } else {
                    this.bottomIcon.setVisibility(8);
                }
                if (!StringUtil.isEmpty(recommendTemplate.lastTitleBg)) {
                    this.hintBg1.setVisibility(0);
                    JDImageUtils.displayImage(recommendTemplate.lastTitleBg, this.hintBg1, jDDisplayImageOptions);
                } else {
                    this.hintBg1.setVisibility(8);
                }
                if (!StringUtil.isEmpty(recommendTemplate.skuIcon)) {
                    this.flame1.setVisibility(0);
                    JDImageUtils.displayImage(recommendTemplate.skuIcon, this.flame1, jDDisplayImageOptions);
                } else {
                    this.flame1.setVisibility(8);
                }
                if (!StringUtil.isEmpty(subSkuData.buyedCount)) {
                    this.num1.setVisibility(0);
                    this.num1.setText(subSkuData.buyedCount);
                    this.num1.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.lastTitleFont, "#CC9C0A"));
                } else {
                    this.num1.setVisibility(8);
                }
            }
            if (recommendTemplate.subsku.get(1) != null) {
                SubSkuData subSkuData2 = recommendTemplate.subsku.get(1);
                this.secondImg.setVisibility(0);
                JDImageUtils.displayImage(subSkuData2.img, (ImageView) this.secondImg, jDDisplayImageOptions, true);
                if (!StringUtil.isEmpty(subSkuData2.hit)) {
                    this.name2.setVisibility(0);
                    this.name2.setText(subSkuData2.hit);
                    this.name2.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.mainTitleFont, JDDarkUtil.COLOR_1A1A1A));
                } else {
                    this.name2.setVisibility(8);
                }
                if (!StringUtil.isEmpty(subSkuData2.leftTopIcon)) {
                    this.icon2.setVisibility(0);
                    JDImageUtils.displayImage(subSkuData2.leftTopIcon, this.icon2, jDDisplayImageOptions);
                } else {
                    this.icon2.setVisibility(8);
                }
                if (!StringUtil.isEmpty(recommendTemplate.lastTitleBg)) {
                    this.hintBg2.setVisibility(0);
                    JDImageUtils.displayImage(recommendTemplate.lastTitleBg, this.hintBg2, jDDisplayImageOptions);
                } else {
                    this.hintBg2.setVisibility(8);
                }
                if (!StringUtil.isEmpty(recommendTemplate.skuIcon)) {
                    this.flame2.setVisibility(0);
                    JDImageUtils.displayImage(recommendTemplate.skuIcon, this.flame2, jDDisplayImageOptions);
                } else {
                    this.flame2.setVisibility(8);
                }
                if (!StringUtil.isEmpty(subSkuData2.buyedCount)) {
                    this.num2.setVisibility(0);
                    this.num2.setText(subSkuData2.buyedCount);
                    this.num2.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.lastTitleFont, "#CC9C0A"));
                } else {
                    this.num2.setVisibility(8);
                }
            }
            if (recommendTemplate.subsku.get(2) != null) {
                SubSkuData subSkuData3 = recommendTemplate.subsku.get(2);
                this.thirdImg.setVisibility(0);
                JDImageUtils.displayImage(subSkuData3.img, (ImageView) this.thirdImg, jDDisplayImageOptions, true);
                if (!StringUtil.isEmpty(subSkuData3.hit)) {
                    this.name3.setVisibility(0);
                    this.name3.setText(subSkuData3.hit);
                    this.name3.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.mainTitleFont, JDDarkUtil.COLOR_1A1A1A));
                } else {
                    this.name3.setVisibility(8);
                }
                if (!StringUtil.isEmpty(subSkuData3.leftTopIcon)) {
                    this.icon3.setVisibility(0);
                    JDImageUtils.displayImage(subSkuData3.leftTopIcon, this.icon3, jDDisplayImageOptions);
                } else {
                    this.icon3.setVisibility(8);
                }
                if (!StringUtil.isEmpty(recommendTemplate.lastTitleBg)) {
                    this.hintBg3.setVisibility(0);
                    JDImageUtils.displayImage(recommendTemplate.lastTitleBg, this.hintBg3, jDDisplayImageOptions);
                } else {
                    this.hintBg3.setVisibility(8);
                }
                if (!StringUtil.isEmpty(recommendTemplate.skuIcon)) {
                    this.flame3.setVisibility(0);
                    JDImageUtils.displayImage(recommendTemplate.skuIcon, this.flame3, jDDisplayImageOptions);
                } else {
                    this.flame3.setVisibility(8);
                }
                if (!StringUtil.isEmpty(subSkuData3.buyedCount)) {
                    this.num3.setVisibility(0);
                    this.num3.setText(subSkuData3.buyedCount);
                    this.num3.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.lastTitleFont, "#CC9C0A"));
                } else {
                    this.num3.setVisibility(8);
                }
            }
        }
        if (expoDataStore2 != null && !recommendTemplate.isFromCache) {
            if (!TextUtils.isEmpty(recommendTemplate.exposureJsonSourceValue)) {
                expoDataStore2.putExpoJsonDada(recommendTemplate.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendTemplate.exposureSourceValue)) {
                expoDataStore2.putExpoData(recommendTemplate.exposureSourceValue);
            }
        }
        setMaterialData(recommendTemplate, i3);
    }
}
