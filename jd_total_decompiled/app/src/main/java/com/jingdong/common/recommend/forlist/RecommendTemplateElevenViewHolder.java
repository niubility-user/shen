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
public class RecommendTemplateElevenViewHolder extends BaseRecommendMaterialViewHolder {
    private BaseActivity activity;
    private SimpleDraweeView background;
    private String bgUrl;
    private TextView boughtCount1;
    private TextView boughtCount2;
    private TextView boughtCount3;
    private SimpleDraweeView firstImg;
    private View firstItemView;
    protected int from;
    private SimpleDraweeView icon;
    private SimpleDraweeView icon1;
    private SimpleDraweeView icon2;
    private SimpleDraweeView icon3;
    private View.OnClickListener jumpClickListener;
    private TextView name1;
    private TextView name2;
    private TextView name3;
    private SimpleDraweeView secondImg;
    private View secondItemView;
    private TextView subTitle;
    private SimpleDraweeView thirdImg;
    private View thirdItemView;
    private TextView title;
    private SimpleDraweeView topIcon;

    public RecommendTemplateElevenViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.jumpClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateElevenViewHolder.2
            {
                RecommendTemplateElevenViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
                RecommendTemplate recommendTemplate = (RecommendTemplate) view2.getTag();
                if (recommendTemplate == null || (onRecommendClickedListener = RecommendTemplateElevenViewHolder.this.clickedListener) == null) {
                    return;
                }
                onRecommendClickedListener.onRecommendJump(recommendTemplate.jump, recommendTemplate.isOpenApp);
                if (recommendTemplate.isFromCache) {
                    return;
                }
                RecommendMtaUtils.aggregationClickMtaRealize(RecommendTemplateElevenViewHolder.this.itemView.getContext(), recommendTemplate.sourceValue, RecommendTemplateElevenViewHolder.this.from, recommendTemplate.extension_id);
                RecommendTemplateElevenViewHolder.this.onAdClick(recommendTemplate.client_click_url);
            }
        };
        this.activity = baseActivity;
        View findViewById = view.findViewById(R.id.recommend_commodity_first);
        this.firstItemView = findViewById;
        int i2 = R.id.recommend_commodity_goods;
        this.firstImg = (SimpleDraweeView) findViewById.findViewById(i2);
        View view2 = this.firstItemView;
        int i3 = R.id.recommend_commodity_left_top_icon;
        this.icon1 = (SimpleDraweeView) view2.findViewById(i3);
        this.topIcon = (SimpleDraweeView) this.firstItemView.findViewById(R.id.recommend_commodity_left_bottom_icon);
        View view3 = this.firstItemView;
        int i4 = R.id.recommend_commodity_name;
        this.name1 = (TextView) view3.findViewById(i4);
        View view4 = this.firstItemView;
        int i5 = R.id.recommend_commodity_list_bought_count;
        this.boughtCount1 = (TextView) view4.findViewById(i5);
        View findViewById2 = view.findViewById(R.id.recommend_commodity_second);
        this.secondItemView = findViewById2;
        this.secondImg = (SimpleDraweeView) findViewById2.findViewById(i2);
        this.icon2 = (SimpleDraweeView) this.secondItemView.findViewById(i3);
        this.name2 = (TextView) this.secondItemView.findViewById(i4);
        this.boughtCount2 = (TextView) this.secondItemView.findViewById(i5);
        View findViewById3 = view.findViewById(R.id.recommend_commodity_third);
        this.thirdItemView = findViewById3;
        this.thirdImg = (SimpleDraweeView) findViewById3.findViewById(i2);
        this.icon3 = (SimpleDraweeView) this.thirdItemView.findViewById(i3);
        this.name3 = (TextView) this.thirdItemView.findViewById(i4);
        this.boughtCount3 = (TextView) this.thirdItemView.findViewById(i5);
        this.icon = (SimpleDraweeView) view.findViewById(R.id.recommend_commodity_sales_icon);
        this.title = (TextView) view.findViewById(R.id.recommend_commodity_sales_title);
        this.subTitle = (TextView) view.findViewById(R.id.recommend_commodity_sales_information);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_commodity_bottom_background);
        this.background = simpleDraweeView;
        simpleDraweeView.setAspectRatio(2.18f);
    }

    private void resetView() {
        this.firstImg.setVisibility(8);
        this.secondImg.setVisibility(8);
        this.thirdImg.setVisibility(8);
        this.icon1.setVisibility(8);
        this.icon2.setVisibility(8);
        this.icon3.setVisibility(8);
        this.topIcon.setVisibility(8);
        this.name1.setVisibility(8);
        this.name2.setVisibility(8);
        this.name3.setVisibility(8);
        this.boughtCount1.setVisibility(8);
        this.boughtCount2.setVisibility(8);
        this.boughtCount3.setVisibility(8);
        this.icon.setVisibility(8);
        this.title.setVisibility(8);
        this.subTitle.setVisibility(8);
    }

    private void setProductItem(RecommendTemplate recommendTemplate, SubSkuData subSkuData, SimpleDraweeView simpleDraweeView, JDDisplayImageOptions jDDisplayImageOptions, SimpleDraweeView simpleDraweeView2, TextView textView, TextView textView2) {
        simpleDraweeView.setVisibility(0);
        JDImageUtils.displayImage(subSkuData.img, (ImageView) simpleDraweeView, jDDisplayImageOptions, true);
        if (!StringUtil.isEmpty(subSkuData.hit)) {
            textView.setVisibility(0);
            textView.setText(subSkuData.hit);
            textView.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.mainTitleFont, JDDarkUtil.COLOR_1A1A1A));
        }
        if (!StringUtil.isEmpty(subSkuData.leftTopIcon)) {
            simpleDraweeView2.setVisibility(0);
            JDImageUtils.displayImage(subSkuData.leftTopIcon, simpleDraweeView2, jDDisplayImageOptions);
        }
        if (!StringUtil.isEmpty(subSkuData.leftBottomIcon)) {
            this.topIcon.setVisibility(0);
            JDImageUtils.displayImage(subSkuData.leftBottomIcon, this.topIcon, jDDisplayImageOptions);
        }
        if (StringUtil.isEmpty(subSkuData.buyedCount)) {
            return;
        }
        textView2.setVisibility(0);
        textView2.setText(subSkuData.buyedCount);
        textView2.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.lastTitleFont, "#CC9C0A"));
    }

    public void setData(RecommendTemplate recommendTemplate, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2, int i3) {
        String str;
        if (recommendTemplate != null) {
            this.itemView.setTag(recommendTemplate);
            this.from = i2;
            setFrom(i2);
            resetView();
            setAdData(recommendTemplate);
            this.itemView.setOnClickListener(this.jumpClickListener);
            ArrayList<SubSkuData> arrayList = recommendTemplate.subsku;
            if (arrayList != null && arrayList.size() > 2) {
                if (recommendTemplate.subsku.get(0) != null) {
                    setProductItem(recommendTemplate, recommendTemplate.subsku.get(0), this.firstImg, jDDisplayImageOptions, this.icon1, this.name1, this.boughtCount1);
                }
                if (recommendTemplate.subsku.get(1) != null) {
                    setProductItem(recommendTemplate, recommendTemplate.subsku.get(1), this.secondImg, jDDisplayImageOptions, this.icon2, this.name2, this.boughtCount2);
                }
                if (recommendTemplate.subsku.get(2) != null) {
                    setProductItem(recommendTemplate, recommendTemplate.subsku.get(2), this.thirdImg, jDDisplayImageOptions, this.icon3, this.name3, this.boughtCount3);
                }
            }
            if (!StringUtil.isEmpty(recommendTemplate.itemPic)) {
                this.icon.setVisibility(0);
                JDImageUtils.displayImage(recommendTemplate.itemPic, this.icon, jDDisplayImageOptions);
            }
            if (!StringUtil.isEmpty(recommendTemplate.firstTitle)) {
                this.title.setVisibility(0);
                this.title.setText(recommendTemplate.firstTitle);
                this.title.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.firstTitleFont, "#CC9C0A"));
            }
            if (!StringUtil.isEmpty(recommendTemplate.subTitle)) {
                this.subTitle.setVisibility(0);
                this.subTitle.setText(recommendTemplate.subTitle);
                this.subTitle.setTextColor(RecommendViewUtil.generateColor(recommendTemplate.subTitleFont, "#D78F36"));
            }
            if (this.background.getDrawable() == null || (str = this.bgUrl) == null || !str.equals(recommendTemplate.background)) {
                String str2 = recommendTemplate.background;
                this.bgUrl = str2;
                JDImageUtils.displayImage(str2, this.background, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateElevenViewHolder.1
                    {
                        RecommendTemplateElevenViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str3, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                        RecommendTemplateElevenViewHolder.this.background.setImageResource(R.drawable.recommend_template_eleven_bottom_bg);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str3, View view) {
                    }
                });
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
