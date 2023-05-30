package com.jingdong.common.recommend.forlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
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
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendFestivalShortViewHolder extends BaseRecommendViewHolder {
    String bgUrl;
    private SimpleDraweeView bgView;
    private RecommendFestivalData festivalData;
    int from;
    private FestivalItemView leftView;
    private FestivalItemView rightView;
    private TextView titleView;

    /* loaded from: classes6.dex */
    public class FestivalItemView {
        private SimpleDraweeView pdBgView;
        private SimpleDraweeView pdView;
        private TextView titleView;
        public View view;

        FestivalItemView(View view) {
            RecommendFestivalShortViewHolder.this = r1;
            this.view = view;
            this.pdView = (SimpleDraweeView) view.findViewById(R.id.recommend_year_festival_shot_pd_img);
            this.pdBgView = (SimpleDraweeView) view.findViewById(R.id.recommend_year_festival_shot_pd_bg);
            this.titleView = (TextView) view.findViewById(R.id.recommend_year_festival_shot_pd_title);
        }

        public void showData(ExpoDataStore expoDataStore, final RecommendFestivalItemData recommendFestivalItemData, JDDisplayImageOptions jDDisplayImageOptions) {
            if (recommendFestivalItemData != null) {
                RecommendViewUtil.visible(this.view);
                if (!TextUtils.isEmpty(recommendFestivalItemData.bgImgUrl)) {
                    JDImageUtils.displayImage(recommendFestivalItemData.bgImgUrl, this.pdBgView, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendFestivalShortViewHolder.FestivalItemView.1
                        {
                            FestivalItemView.this = this;
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                            FestivalItemView.this.pdBgView.setImageResource(R.drawable.recommend_short_litte_default_bg);
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str, View view) {
                        }
                    });
                } else {
                    this.pdBgView.setImageResource(R.drawable.recommend_short_litte_default_bg);
                }
                JDImageUtils.displayImage(recommendFestivalItemData.imageUrl, this.pdView, jDDisplayImageOptions, (JDImageLoadingListener) null);
                if (!StringUtil.isEmpty(recommendFestivalItemData.mainTitle)) {
                    RecommendViewUtil.visible(this.titleView);
                    RecommendViewUtil.setText(this.titleView, recommendFestivalItemData.mainTitle);
                    RecommendViewUtil.setTextColor(this.titleView, RecommendViewUtil.generateColor(recommendFestivalItemData.mainTitleColor, JDDarkUtil.COLOR_FA2C19));
                } else {
                    RecommendViewUtil.gone(this.titleView);
                }
                this.view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendFestivalShortViewHolder.FestivalItemView.2
                    {
                        FestivalItemView.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (!RecommendFestivalShortViewHolder.this.festivalData.isCacheData()) {
                            Context context = view.getContext();
                            RecommendFestivalItemData recommendFestivalItemData2 = recommendFestivalItemData;
                            RecommendMtaUtils.festivalPromotionClickMta(context, recommendFestivalItemData2.sourceValue, RecommendFestivalShortViewHolder.this.from, recommendFestivalItemData2.extension_id);
                        }
                        if (recommendFestivalItemData.jumpObj != null) {
                            JumpUtil.execJump(view.getContext(), recommendFestivalItemData.jumpObj, 1);
                        }
                    }
                });
                if (expoDataStore == null || TextUtils.isEmpty(recommendFestivalItemData.exposureJsonSourceValue) || RecommendFestivalShortViewHolder.this.festivalData.isCacheData()) {
                    return;
                }
                expoDataStore.putExpoJsonDada(recommendFestivalItemData.exposureJsonSourceValue);
            }
        }
    }

    public RecommendFestivalShortViewHolder(View view) {
        super(view);
        this.bgView = (SimpleDraweeView) view.findViewById(R.id.recommend_year_festival_shot_bg);
        this.titleView = (TextView) view.findViewById(R.id.recommend_year_festival_shot_title);
        this.leftView = new FestivalItemView(view.findViewById(R.id.recommend_year_festival_short_product_left));
        this.rightView = new FestivalItemView(view.findViewById(R.id.recommend_year_festival_short_product_right));
    }

    public void onLoadBgFail(String str) {
        RecommendViewUtil.setText(this.titleView, str);
        RecommendViewUtil.visible(this.titleView);
        this.bgView.setImageResource(R.drawable.recommend_short_default_bg);
    }

    public void setData(final RecommendFestivalData recommendFestivalData, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2) {
        if (recommendFestivalData == null) {
            return;
        }
        this.festivalData = recommendFestivalData;
        this.from = i2;
        RecommendViewUtil.gone(this.titleView);
        if (TextUtils.isEmpty(recommendFestivalData.wareBgUrl)) {
            this.bgUrl = "";
            onLoadBgFail(recommendFestivalData.wname);
        } else if (TextUtils.isEmpty(this.bgUrl) || !this.bgUrl.equals(recommendFestivalData.wareBgUrl)) {
            String str = recommendFestivalData.wareBgUrl;
            this.bgUrl = str;
            JDImageUtils.displayImage(str, this.bgView, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendFestivalShortViewHolder.1
                {
                    RecommendFestivalShortViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    RecommendFestivalShortViewHolder.this.onLoadBgFail(recommendFestivalData.wname);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            });
        }
        RecommendViewUtil.inVisible(this.leftView.view);
        RecommendViewUtil.inVisible(this.rightView.view);
        ArrayList<RecommendFestivalItemData> arrayList = recommendFestivalData.subWareList;
        if (arrayList == null || arrayList.size() <= 1) {
            return;
        }
        this.leftView.showData(expoDataStore, recommendFestivalData.subWareList.get(0), jDDisplayImageOptions);
        this.rightView.showData(expoDataStore, recommendFestivalData.subWareList.get(1), jDDisplayImageOptions);
    }
}
