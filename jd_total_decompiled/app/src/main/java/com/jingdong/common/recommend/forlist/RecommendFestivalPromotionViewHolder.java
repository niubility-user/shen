package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendFestivalData;
import com.jingdong.common.recommend.entity.RecommendFestivalItemData;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendFestivalPromotionViewHolder extends BaseRecommendViewHolder {
    private SimpleDraweeView background;
    private String bgUrl;
    private FestivalItemView bottomLeftView;
    private FestivalItemView bottomRightView;
    private RecommendFestivalData festivalData;
    protected int from;
    private TextView titleTV;
    private FestivalItemView topLeftView;
    private FestivalItemView topRightView;

    /* loaded from: classes6.dex */
    public class FestivalItemView {
        private SimpleDraweeView coverImage;
        private String coverUrl;
        private SimpleDraweeView image;
        private View itemView;
        private View.OnClickListener jumpClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendFestivalPromotionViewHolder.FestivalItemView.2
            {
                FestivalItemView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendFestivalItemData recommendFestivalItemData = (RecommendFestivalItemData) view.getTag();
                if (recommendFestivalItemData != null) {
                    if (!RecommendFestivalPromotionViewHolder.this.festivalData.isCacheData()) {
                        RecommendMtaUtils.festivalPromotionClickMta(view.getContext(), recommendFestivalItemData.sourceValue, RecommendFestivalPromotionViewHolder.this.from, recommendFestivalItemData.extension_id);
                    }
                    if (recommendFestivalItemData.jumpObj != null) {
                        JumpUtil.execJump(view.getContext(), recommendFestivalItemData.jumpObj, 1);
                    }
                }
            }
        };
        private TextView mainTitleTV;
        private TextView subtitleTV;

        protected FestivalItemView(View view) {
            RecommendFestivalPromotionViewHolder.this = r1;
            this.itemView = view;
            this.image = (SimpleDraweeView) view.findViewById(R.id.recommend_promotion_image);
            this.coverImage = (SimpleDraweeView) view.findViewById(R.id.recommend_promotion_cover);
            this.subtitleTV = (TextView) view.findViewById(R.id.recommend_promotion_subtitle);
            this.mainTitleTV = (TextView) view.findViewById(R.id.recommend_promotion_main_title);
        }

        public void setFestivalItemData(RecommendFestivalItemData recommendFestivalItemData, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore) {
            if (recommendFestivalItemData != null) {
                JDImageUtils.displayImage(recommendFestivalItemData.imageUrl, (ImageView) this.image, jDDisplayImageOptions, true);
                if (StringUtil.isEmpty(this.coverUrl) || !this.coverUrl.equals(recommendFestivalItemData.bgImgUrl)) {
                    String str = recommendFestivalItemData.bgImgUrl;
                    this.coverUrl = str;
                    JDImageUtils.displayImage(str, this.coverImage, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendFestivalPromotionViewHolder.FestivalItemView.1
                        {
                            FestivalItemView.this = this;
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str2, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                            FestivalItemView.this.coverImage.setImageResource(R.drawable.recommend_festival_product_background);
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str2, View view) {
                        }
                    });
                }
                if (!StringUtil.isEmpty(recommendFestivalItemData.subTitle)) {
                    this.subtitleTV.setVisibility(0);
                    this.subtitleTV.setText(recommendFestivalItemData.subTitle);
                    this.subtitleTV.setTextColor(RecommendViewUtil.generateColor(recommendFestivalItemData.subTitleColor, "#FFFFFF"));
                    ArrayList<String> arrayList = recommendFestivalItemData.subTitleBgColor;
                    if (arrayList != null && arrayList.size() > 1) {
                        RecommendViewUtil.generateGradientDrawableLR(this.subtitleTV, DPIUtil.dip2px(9.0f), recommendFestivalItemData.subTitleBgColors);
                    } else {
                        ArrayList<String> arrayList2 = recommendFestivalItemData.subTitleBgColor;
                        String str2 = (arrayList2 == null || arrayList2.size() != 1) ? "#FD572D" : recommendFestivalItemData.subTitleBgColor.get(0);
                        GradientDrawable gradientDrawable = new GradientDrawable();
                        gradientDrawable.setCornerRadius(DPIUtil.dip2px(9.0f));
                        gradientDrawable.setColor(Color.parseColor(str2));
                        this.subtitleTV.setBackgroundDrawable(gradientDrawable);
                    }
                } else {
                    this.subtitleTV.setVisibility(8);
                }
                if (!StringUtil.isEmpty(recommendFestivalItemData.mainTitle)) {
                    this.mainTitleTV.setVisibility(0);
                    this.mainTitleTV.setText(recommendFestivalItemData.mainTitle);
                    this.mainTitleTV.setTextColor(RecommendViewUtil.generateColor(recommendFestivalItemData.mainTitleColor, JDDarkUtil.COLOR_FA2C19));
                } else {
                    this.mainTitleTV.setVisibility(8);
                }
                if (expoDataStore != null && !TextUtils.isEmpty(recommendFestivalItemData.exposureJsonSourceValue) && !RecommendFestivalPromotionViewHolder.this.festivalData.isCacheData()) {
                    expoDataStore.putExpoJsonDada(recommendFestivalItemData.exposureJsonSourceValue);
                }
                this.itemView.setTag(recommendFestivalItemData);
                this.itemView.setOnClickListener(this.jumpClickListener);
            }
        }
    }

    public RecommendFestivalPromotionViewHolder(View view) {
        super(view);
        this.background = (SimpleDraweeView) view.findViewById(R.id.recommend_promotion_background);
        this.titleTV = (TextView) view.findViewById(R.id.recommend_promotion_title);
        this.topLeftView = new FestivalItemView(view.findViewById(R.id.recommend_promotion_content_top_left));
        this.topRightView = new FestivalItemView(view.findViewById(R.id.recommend_promotion_content_top_right));
        this.bottomLeftView = new FestivalItemView(view.findViewById(R.id.recommend_promotion_content_bottom_left));
        this.bottomRightView = new FestivalItemView(view.findViewById(R.id.recommend_promotion_content_bottom_right));
    }

    public void setData(final RecommendFestivalData recommendFestivalData, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2) {
        if (recommendFestivalData != null) {
            this.festivalData = recommendFestivalData;
            this.titleTV.setText("");
            this.from = i2;
            if (TextUtils.isEmpty(recommendFestivalData.wareBgUrl)) {
                this.bgUrl = "";
                this.background.setImageResource(R.drawable.recommend_festival_item_background);
                this.titleTV.setText(recommendFestivalData.wname);
            } else if (TextUtils.isEmpty(this.bgUrl) || !this.bgUrl.equals(recommendFestivalData.wareBgUrl)) {
                String str = recommendFestivalData.wareBgUrl;
                this.bgUrl = str;
                JDImageUtils.displayImage(str, this.background, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendFestivalPromotionViewHolder.1
                    {
                        RecommendFestivalPromotionViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str2, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                        RecommendFestivalPromotionViewHolder.this.background.setImageResource(R.drawable.recommend_festival_item_background);
                        RecommendFestivalPromotionViewHolder.this.titleTV.setText(recommendFestivalData.wname);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str2, View view) {
                    }
                });
            }
            ArrayList<RecommendFestivalItemData> arrayList = recommendFestivalData.subWareList;
            if (arrayList == null || arrayList.size() <= 3) {
                return;
            }
            this.topLeftView.setFestivalItemData(recommendFestivalData.subWareList.get(0), jDDisplayImageOptions, expoDataStore);
            this.topRightView.setFestivalItemData(recommendFestivalData.subWareList.get(1), jDDisplayImageOptions, expoDataStore);
            this.bottomLeftView.setFestivalItemData(recommendFestivalData.subWareList.get(2), jDDisplayImageOptions, expoDataStore);
            this.bottomRightView.setFestivalItemData(recommendFestivalData.subWareList.get(3), jDDisplayImageOptions, expoDataStore);
        }
    }
}
