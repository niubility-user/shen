package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.skin.lib.CodingConstants;
import com.jd.skin.lib.JDSkinSDK;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.LabelInfo;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.entity.SubSkuData;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.unification.uniconfig.UnSpannaleUtils;
import com.jingdong.common.utils.JDImageUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendTemplateNineteenViewHolder extends BaseRecommendMaterialViewHolder {
    private BaseActivity activity;
    private SimpleDraweeView authorImg;
    private TextView authorName;
    private ViewGroup authorRoot;
    private TextView boughtCountTV;
    private TextView boughtCountTV22;
    private int from;
    private SimpleDraweeView productImg;
    private SimpleDraweeView productImg22;
    private TextView productPriceTV;
    private TextView productPriceTV22;
    private View recommendSpace;
    private RelativeLayout skuDoneRoot;
    private RelativeLayout skuDoneRoot19;
    private RelativeLayout skuDoneRoot22;
    private TextView videoDescription;
    private LinearLayout videoDourationRoot22;
    private LinearLayout videoDourationRootRound22;
    private TextView videoDuration;
    private LinearLayout videoDurationRoot;
    private TextView videoPlayCount22;
    private SimpleDraweeView videoPlayIcon22;
    private SimpleDraweeView videoPlayIconRound22;

    public RecommendTemplateNineteenViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.activity = baseActivity;
        this.videoDurationRoot = (LinearLayout) view.findViewById(R.id.recommend_video_duration_root);
        this.videoDuration = (TextView) view.findViewById(R.id.recommend_video_duration);
        this.videoDescription = (TextView) view.findViewById(R.id.recommend_video_des);
        this.productImg = (SimpleDraweeView) view.findViewById(R.id.recommend_product_img);
        TextView textView = (TextView) view.findViewById(R.id.recommend_product_price);
        this.productPriceTV = textView;
        RecommendFontUtils.changeFont(textView, 4099);
        this.boughtCountTV = (TextView) view.findViewById(R.id.recommend_product_bought_count);
        this.skuDoneRoot = (RelativeLayout) view.findViewById(R.id.recomend_sku_done_root);
        this.videoDourationRoot22 = (LinearLayout) view.findViewById(R.id.recommend_video_duration_root_22);
        this.videoDourationRootRound22 = (LinearLayout) view.findViewById(R.id.recommend_video_duration_root_22_single);
        this.videoPlayIcon22 = (SimpleDraweeView) view.findViewById(R.id.recommend_video_play_icon);
        this.videoPlayIconRound22 = (SimpleDraweeView) view.findViewById(R.id.recommend_video_play_icon_round);
        this.videoPlayCount22 = (TextView) view.findViewById(R.id.recommend_video_play_count);
        this.skuDoneRoot19 = (RelativeLayout) view.findViewById(R.id.recomment_sku_19);
        this.skuDoneRoot22 = (RelativeLayout) view.findViewById(R.id.recomment_sku_22);
        this.productImg22 = (SimpleDraweeView) view.findViewById(R.id.recommend_product_img_22);
        this.productPriceTV22 = (TextView) view.findViewById(R.id.recommend_product_price_22);
        this.boughtCountTV22 = (TextView) view.findViewById(R.id.recommend_product_bought_count_22);
        this.recommendSpace = view.findViewById(R.id.recommend_space);
        this.authorRoot = (ViewGroup) view.findViewById(R.id.recommend_video_author_root);
        this.authorImg = (SimpleDraweeView) view.findViewById(R.id.recommend_author_pic);
        this.authorName = (TextView) view.findViewById(R.id.recommend_author_name);
        inflateRecommendVideo();
    }

    private float getViewRatio(RecommendVideo recommendVideo, int i2) {
        String str = "1";
        if (i2 == 1022) {
            if (!TextUtils.isEmpty(recommendVideo.videoLayout) && !"0".equals(recommendVideo.videoLayout)) {
                str = "0.75";
            }
        } else {
            str = recommendVideo.nonWareImageScale;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException unused) {
            }
        }
        return 1.0f;
    }

    public void jump(View view, RecommendVideo recommendVideo) {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
        if (recommendVideo == null || (onRecommendClickedListener = this.clickedListener) == null) {
            return;
        }
        onRecommendClickedListener.onRecommendJump(recommendVideo.jump, recommendVideo.isOpenApp);
        if (recommendVideo.isFromCache) {
            return;
        }
        RecommendMtaUtils.aggregationClickMtaRealize(this.itemView.getContext(), recommendVideo.sourceValue, this.from, recommendVideo.extension_id);
        onAdClick(recommendVideo.client_click_url);
    }

    private void resetViewState() {
        RecommendViewUtil.gone(this.authorRoot);
    }

    private void setJDPrice(String str, TextView textView) {
        String str2 = this.activity.getResources().getString(R.string.yangjiao) + str;
        SpannableString spannableString = new SpannableString(str2);
        try {
            int indexOf = str2.indexOf(OrderISVUtil.MONEY_DECIMAL);
            spannableString.setSpan(new AbsoluteSizeSpan(13, true), 0, 1, 33);
            if (indexOf != -1) {
                spannableString.setSpan(new AbsoluteSizeSpan(16, true), 1, indexOf, 33);
                spannableString.setSpan(new AbsoluteSizeSpan(13, true), indexOf, spannableString.length(), 33);
            }
        } catch (Exception unused) {
        }
        textView.setText(spannableString);
    }

    private void setMainTitle(RecommendVideo recommendVideo, int i2) {
        String str;
        String str2;
        if (i2 == 1022) {
            String str3 = !TextUtils.isEmpty(recommendVideo.mainTitle) ? recommendVideo.mainTitle : "";
            if (recommendVideo != null) {
                try {
                    List<LabelInfo> list = recommendVideo.labels;
                    if (list != null && list.size() > 0) {
                        LabelInfo labelInfo = recommendVideo.labels.get(0);
                        if (labelInfo != null && (str = labelInfo.labelIcon) != null && (str2 = labelInfo.labelName) != null) {
                            Bitmap drawable2Bitmap = RecommendUtils.drawable2Bitmap(UnIconConfigHelper.getTextDrawable(str, str2));
                            if (drawable2Bitmap != null) {
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(drawable2Bitmap);
                                TextView textView = this.videoDescription;
                                textView.setText(UnSpannaleUtils.getImageSpan(textView.getContext(), arrayList, str3));
                            } else {
                                this.videoDescription.setText(str3);
                            }
                        } else {
                            this.videoDescription.setText(str3);
                        }
                        return;
                    }
                } catch (Exception unused) {
                    this.videoDescription.setText(str3);
                    return;
                }
            }
            this.videoDescription.setText(str3);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(recommendVideo.icon2);
        this.videoDescription.setText(UnIconConfigHelper.getSpanableString(arrayList2, recommendVideo.mainTitle, this.videoDescription));
    }

    private void setVideoPlayListener(final RecommendVideo recommendVideo) {
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateNineteenViewHolder.2
            {
                RecommendTemplateNineteenViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendTemplateNineteenViewHolder.this.jump(view, recommendVideo);
            }
        });
    }

    private void showUserRoot(RecommendVideo recommendVideo, JDDisplayImageOptions jDDisplayImageOptions) {
        if (!TextUtils.isEmpty(recommendVideo.authorName)) {
            this.authorRoot.setVisibility(0);
            JDImageUtils.displayImage(recommendVideo.authorPic, this.authorImg, jDDisplayImageOptions);
            this.authorName.setText(recommendVideo.authorName);
            return;
        }
        this.authorRoot.setVisibility(8);
    }

    public void setData(RecommendVideo recommendVideo, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2, int i3, int i4) {
        String str;
        SimpleDraweeView simpleDraweeView;
        if (recommendVideo != null) {
            this.from = i2;
            setFrom(i2);
            changeVideoViewHeight(getViewRatio(recommendVideo, i4));
            resetViewState();
            if (i4 == 1022) {
                this.videoDurationRoot.setVisibility(8);
                if (!TextUtils.isEmpty(recommendVideo.pv)) {
                    this.videoPlayCount22.setTextColor(RecommendViewUtil.generateColor(JDSkinSDK.getInstance().getColor(CodingConstants.C_Gray_01, false), JDDarkUtil.COLOR_FFFFFFF));
                    JDSkinSDK.getInstance().setFontAndSize(CodingConstants.T1_Text_regular, this.videoPlayCount22);
                    this.videoPlayCount22.setText(recommendVideo.pv);
                    str = recommendVideo.pvIcon;
                    simpleDraweeView = this.videoPlayIcon22;
                    this.videoDourationRootRound22.setVisibility(8);
                    this.videoDourationRoot22.setVisibility(0);
                } else {
                    str = recommendVideo.pvIconRound;
                    simpleDraweeView = this.videoPlayIconRound22;
                    this.videoDourationRootRound22.setVisibility(0);
                    this.videoDourationRoot22.setVisibility(8);
                }
                JDImageUtils.displayImage(str, simpleDraweeView, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateNineteenViewHolder.1
                    {
                        RecommendTemplateNineteenViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str2, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                        RecommendTemplateNineteenViewHolder.this.videoDourationRoot22.setVisibility(8);
                        RecommendTemplateNineteenViewHolder.this.videoDourationRootRound22.setVisibility(8);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str2, View view) {
                    }
                });
            } else if (i4 == 1019) {
                this.videoDourationRoot22.setVisibility(8);
                this.videoDurationRoot.setVisibility(0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                if (!TextUtils.isEmpty(recommendVideo.videoDuration)) {
                    this.videoDurationRoot.setVisibility(0);
                    this.videoDuration.setText(simpleDateFormat.format(Long.valueOf(Long.parseLong(recommendVideo.videoDuration) * 1000)));
                } else {
                    this.videoDurationRoot.setVisibility(8);
                }
            }
            setVideoData(recommendVideo, recommendVideo.img);
            if (i4 == 1022) {
                ArrayList<SubSkuData> arrayList = recommendVideo.subsku;
                if (arrayList != null && arrayList.size() > 0) {
                    SubSkuData subSkuData = recommendVideo.subsku.get(0);
                    if (!TextUtils.isEmpty(subSkuData.img) && !TextUtils.isEmpty(subSkuData.jp)) {
                        this.skuDoneRoot.setVisibility(0);
                        this.skuDoneRoot19.setVisibility(8);
                        this.recommendSpace.setVisibility(8);
                        this.skuDoneRoot22.setVisibility(0);
                        JDImageUtils.displayImage(subSkuData.img, (ImageView) this.productImg22, jDDisplayImageOptions, true);
                        JDSkinSDK.getInstance().setFontAndSize(CodingConstants.T4_JDzhenghHT_regular, this.productPriceTV22);
                        this.productPriceTV22.setTextColor(RecommendViewUtil.generateColor(JDSkinSDK.getInstance().getColor(CodingConstants.C_Brand_01, false), JDDarkUtil.COLOR_FA2C19));
                        setJDPrice(RecommendUtils.getJdPrice(subSkuData.jp), this.productPriceTV22);
                        if (!TextUtils.isEmpty(subSkuData.buyedCount)) {
                            this.boughtCountTV22.setVisibility(0);
                            JDSkinSDK.getInstance().setFontAndSize(CodingConstants.T1_Text_regular, this.boughtCountTV22);
                            this.boughtCountTV22.setTextColor(RecommendViewUtil.generateColor(JDSkinSDK.getInstance().getColor(CodingConstants.C_Gray_07, false), JDDarkUtil.COLOR_808080));
                            this.boughtCountTV22.setText(subSkuData.buyedCount);
                        } else {
                            this.boughtCountTV22.setVisibility(8);
                        }
                    } else {
                        showUserRoot(recommendVideo, jDDisplayImageOptions);
                        this.skuDoneRoot.setVisibility(8);
                        this.recommendSpace.setVisibility(0);
                    }
                } else {
                    showUserRoot(recommendVideo, jDDisplayImageOptions);
                    this.skuDoneRoot.setVisibility(8);
                    this.recommendSpace.setVisibility(0);
                }
            } else if (i4 == 1019) {
                this.skuDoneRoot.setVisibility(0);
                this.skuDoneRoot19.setVisibility(0);
                this.skuDoneRoot22.setVisibility(8);
                this.recommendSpace.setVisibility(8);
                ArrayList<SubSkuData> arrayList2 = recommendVideo.subsku;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    SubSkuData subSkuData2 = recommendVideo.subsku.get(0);
                    JDImageUtils.displayImage(subSkuData2.img, (ImageView) this.productImg, jDDisplayImageOptions, true);
                    this.boughtCountTV.setText(subSkuData2.buyedCount);
                    setJDPrice(RecommendUtils.getJdPrice(subSkuData2.jp), this.productPriceTV);
                } else {
                    JDImageUtils.displayImage("", (ImageView) this.productImg, jDDisplayImageOptions, true);
                }
            }
            setMainTitle(recommendVideo, i4);
            setVideoPlayListener(recommendVideo);
            if (expoDataStore != null) {
                if (!TextUtils.isEmpty(recommendVideo.exposureJsonSourceValue)) {
                    expoDataStore.putExpoJsonDada(recommendVideo.exposureJsonSourceValue);
                } else if (!TextUtils.isEmpty(recommendVideo.exposureSourceValue)) {
                    expoDataStore.putExpoData(recommendVideo.exposureSourceValue);
                }
            }
            setAdData(recommendVideo);
            setMaterialData(recommendVideo, i3);
        }
    }

    public void setPlayDurationVisible(boolean z, int i2) {
        if (i2 == 1019) {
            if (z) {
                this.videoDurationRoot.setVisibility(0);
            } else {
                this.videoDurationRoot.setVisibility(8);
            }
        }
    }
}
