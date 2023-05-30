package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.gray.JDGrayModelUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendInteractionBViewHolder;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.ExpoData;
import com.jingdong.common.recommend.entity.RecommendAdData;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendFestivalData;
import com.jingdong.common.recommend.entity.RecommendGuide;
import com.jingdong.common.recommend.entity.RecommendHomeTabTemp;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendOtherData;
import com.jingdong.common.recommend.entity.RecommendPdProductFor2;
import com.jingdong.common.recommend.entity.RecommendPdTitle;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendTemplate;
import com.jingdong.common.recommend.entity.RecommendType;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendViewHolder extends BaseRecommendViewHolder {
    private RecommendAutoPlayViewHolder autoPlayViewHolder;
    public RecommendBannerBViewHolder bannerBViewHolder;
    private RecommendChannelViewHolder channelViewHolder;
    private RecommendDynamicPdViewHolder dynamicPDViewHolder;
    private RecommendDynamicTemplateViewHolder dynamicTemplateViewHolder;
    private ExpoDataStore expoDataShop;
    private RecommendFestivalPromotionViewHolder festivalPromotionViewHolder;
    private SimpleDraweeView firstView;
    private RecommendHomeCardOneChannelViewHolder homeCardOneChannelViewHolder;
    private RecommendHomeCardTwoChannelViewHolder homeCardTwoChannelViewHolder;
    private RecommendProductHomeTabViewHolder homeTabNineNewViewHolder;
    private String imgUrl;
    public BaseRecommendViewHolder inUseViewHolder;
    private RecommendInteractionBViewHolder interactionBViewHolder;
    private RecommendInteractionViewHolder interactionViewHolder;
    private boolean isNeedShowGuide;
    private boolean isNeedShowTestGuide;
    private final View layout;
    private Recommend9ProductViewHolder leftProductViewHolder;
    private RecommendHomeLiveVideoWithOneChannelViewHolder liveVideoOneChannelViewHolder;
    private RecommendHomeLiveVideoWithTwoChannelViewHolder liveVideoTwoChannelViewHolder;
    private RecommendHomeLiveViewHolder liveVideoViewHolder;
    private RecommendLiveViewHolder liveViewHolder;
    private int mFrom;
    private Handler mMainHandler;
    protected RecommendConfig mRecommendConfig;
    private RecommendContentMaterialViewHolder materialViewHolder;
    private RecommendMonetizationViewHolder monetizationViewHolder;
    private RecommendPDCouponViewHolder pdCouponViewHolder;
    public int positionInDatas;
    private RecommendPdProduct2ViewHolder product2ViewHolder;
    private RecommendQuesNairViewHolder questionnairViewHolder;
    private ExpoDataStore realAggreationExpoDataStore;
    private ExpoDataStore realProductExpoDataStore;
    private RecommendAdsShopViewHolder recommendAdsShopViewHolder;
    private RecommendFestivalShortViewHolder recommendFestivalShortViewHolder;
    private RecommendHomeTabTempViewHolder recommendHomeTabTempViewHolder;
    private RecommendItem recommendItemBean;
    private RecommendLineTwoBean recommendLineTwoBean;
    public RecommendProductListViewHolder recommendProductListViewHolder;
    private Recommend9ProductViewHolder rightProductViewHolder;
    private RecommendSceneLabelViewHolder sceneLabelViewHolder;
    private RecommendAutoPlayViewHolder templateEightViewHolder;
    private RecommendTemplateEighteenViewHolder templateEighteenViewHolder;
    private RecommendTemplateElevenViewHolder templateElevenViewHolder;
    private RecommendTemplateFifteenViewHolder templateFifteenViewHolder;
    private RecommendTemplateVideoViewHolder templateFiveViewHolder;
    private RecommendTemplateFourViewHolder templateFourViewHolder;
    private RecommendTemplateThirteenViewHolder templateFourteenViewHolder;
    private RecommendTemplateLiveViewHolder templateLiveViewHolder;
    private RecommendTemplateNineViewHolder templateNineViewHolder;
    private RecommendTemplateNineteenViewHolder templateNineteenViewHolder;
    private RecommendTemplateOneViewHolder templateOneViewHolder;
    private RecommendTemplateSixViewHolder templateSixViewHolder;
    private RecommendTemplateTenViewHolder templateTenViewHolder;
    private RecommendTemplateThirteenViewHolder templateThirteenViewHolder;
    private RecommendTemplateThreeViewHolder templateThreeViewHolder;
    private RecommendTemplateTwelveViewHolder templateTwelveViewHolder;
    private RecommendTemplateTwoViewHolder templateTwoViewHolder;
    private final ImageView testinGuide;
    private RecommendTitleViewHolder titleViewHolder;
    private RecommendUgcViewHolder ugcViewHolder;
    private int viewType;

    /* loaded from: classes6.dex */
    public static class RecommendLineTwoBean {
        public boolean hasRecommendExpo = false;
        public ArrayList<RecommendItem> recommendItemBeans = new ArrayList<>();
    }

    public RecommendViewHolder(BaseActivity baseActivity, View view, int i2, int i3, RecommendOtherData recommendOtherData, HashSet<String> hashSet) {
        super(view);
        ViewStub viewStub;
        ViewStub viewStub2;
        this.isNeedShowTestGuide = false;
        this.positionInDatas = -1;
        this.layout = view;
        this.firstView = (SimpleDraweeView) view.findViewById(R.id.guideView);
        this.testinGuide = (ImageView) view.findViewById(R.id.recommend_testin_guide);
        this.mMainHandler = new Handler(Looper.getMainLooper());
        showGuide(recommendOtherData);
        this.realExpoHashSet = hashSet;
        if (i2 == 0 && (viewStub2 = (ViewStub) view.findViewById(R.id.recommend_product_left)) != null) {
            Recommend9ProductViewHolder recommend9ProductViewHolder = new Recommend9ProductViewHolder(baseActivity, viewStub2.inflate());
            this.leftProductViewHolder = recommend9ProductViewHolder;
            recommend9ProductViewHolder.setRealExpoHashSet(this.realExpoHashSet);
        }
        if (i3 == 0 && (viewStub = (ViewStub) view.findViewById(R.id.recommend_product_right)) != null) {
            Recommend9ProductViewHolder recommend9ProductViewHolder2 = new Recommend9ProductViewHolder(baseActivity, viewStub.inflate());
            this.rightProductViewHolder = recommend9ProductViewHolder2;
            recommend9ProductViewHolder2.setRealExpoHashSet(this.realExpoHashSet);
        }
    }

    private void doItemExpo(RecommendItem recommendItem) {
        if (recommendItem == null || recommendItem.hasRecommendExpo) {
            return;
        }
        recommendItem.hasRecommendExpo = true;
        if (this.viewType == 999) {
            RecommendMtaUtils.realAggreatioExpo(this.mFrom, this.itemView.getContext(), recommendItem.expoData);
        } else if (isCacheData()) {
            RecommendMtaUtils.realProductExpo(this.mFrom, this.itemView.getContext(), recommendItem.expoData);
        } else if (RecommendType.isAggreationType(this.viewType)) {
            OKLog.d("RecommendTest", "=\u975e\u5546\u54c1\u9732\u51fa50% \u7acb\u523b\u4e0a\u62a5===" + this.positionInDatas);
            RecommendMtaUtils.realAggreatioExpo(this.mFrom, this.itemView.getContext(), recommendItem.expoData);
        } else if (RecommendType.isProduct(this.viewType)) {
            OKLog.d("RecommendTest", "=\u5546\u54c1\u9732\u51fa50% \u7acb\u523b\u4e0a\u62a5===" + this.positionInDatas);
            if (recommendItem.expoDatas != null) {
                for (int i2 = 0; i2 < recommendItem.expoDatas.size(); i2++) {
                    ExpoData expoData = recommendItem.expoDatas.get(i2);
                    if (expoData != null) {
                        try {
                            RecommendProduct recommendProduct = expoData.productRef;
                            if (recommendProduct != null) {
                                RecommendMtaUtils.appHeightToExpo_product(recommendProduct, this.itemView.getHeight());
                                expoData.appendJson = expoData.productRef.appendMtaJson_real;
                            }
                        } catch (Exception unused) {
                        }
                    }
                    RecommendMtaUtils.realProductExpo(this.mFrom, this.itemView.getContext(), expoData);
                }
            } else if (recommendItem.expoData != null) {
                RecommendMtaUtils.appHeightToExpo(recommendItem, this.itemView.getHeight());
                RecommendProduct recommendProduct2 = recommendItem.product;
                if (recommendProduct2 != null) {
                    recommendItem.expoData.appendJson = recommendProduct2.appendMtaJson_real;
                }
                RecommendMtaUtils.realProductExpo(this.mFrom, this.itemView.getContext(), recommendItem.expoData);
            }
        }
    }

    private boolean isCacheData() {
        RecommendItem recommendItem = this.recommendItemBean;
        if (recommendItem != null) {
            RecommendAdData recommendAdData = recommendItem.type66Data;
            if (recommendAdData == null || !recommendAdData.isFromCache) {
                RecommendProduct recommendProduct = recommendItem.product;
                return recommendProduct != null && recommendProduct.isBackUp == 1;
            }
            return true;
        }
        return false;
    }

    private void showGuide(RecommendOtherData recommendOtherData) {
        if (recommendOtherData == null) {
            return;
        }
        if (TextUtils.equals("1", recommendOtherData.getPublicTestBubble())) {
            String publicTestBubbleTimestamp = recommendOtherData.getPublicTestBubbleTimestamp();
            String string = CommonBase.getJdSharedPreferences().getString("Recommend_Test_ShowTime", "-1");
            if (!TextUtils.equals(string, publicTestBubbleTimestamp)) {
                CommonBase.getJdSharedPreferences().edit().putString("Recommend_Test_ShowTime", publicTestBubbleTimestamp).apply();
                if (TextUtils.equals(string, "-1")) {
                    this.isNeedShowTestGuide = false;
                } else {
                    this.isNeedShowTestGuide = true;
                }
            } else {
                this.isNeedShowTestGuide = false;
            }
        }
        RecommendGuide recommendGuide = recommendOtherData.getRecommendGuide();
        if (recommendGuide == null) {
            return;
        }
        this.imgUrl = recommendGuide.imgUrl;
        int i2 = recommendGuide.timestamp;
        int i3 = CommonBase.getJdSharedPreferences().getInt("Recommend_ShowTime", -1);
        if (i3 != i2) {
            CommonBase.getJdSharedPreferences().edit().putInt("Recommend_ShowTime", i2).apply();
            if (i3 == -1) {
                this.isNeedShowGuide = false;
                return;
            } else {
                this.isNeedShowGuide = true;
                return;
            }
        }
        this.isNeedShowGuide = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDownTime(final ImageView imageView) {
        new CountDownTimer(Final.FIVE_SECOND, 1000L) { // from class: com.jingdong.common.recommend.forlist.RecommendViewHolder.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                imageView.setVisibility(8);
                RecommendViewHolder.this.isNeedShowGuide = false;
                RecommendViewHolder.this.isNeedShowTestGuide = false;
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
            }
        }.start();
    }

    public void bindNewRecommendViewHolder(ArrayList<RecommendItem> arrayList, RecommendOtherData recommendOtherData, int i2, ExpoDataStore expoDataStore, int i3, int i4, boolean z, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore2, ExpoDataStore expoDataStore3, ExpoDataStore expoDataStore4, ExpoDataStore expoDataStore5, ExpoDataStore expoDataStore6) {
        boolean z2;
        if (i2 < 2 && ((z2 = this.isNeedShowGuide) || this.isNeedShowTestGuide)) {
            if (this.isNeedShowTestGuide && i2 == 0) {
                this.testinGuide.setVisibility(0);
                startDownTime(this.testinGuide);
                this.firstView.setVisibility(8);
            } else if (i2 == 1 && z2) {
                this.testinGuide.setVisibility(8);
                this.firstView.setVisibility(0);
                JDImageUtils.displayImage(this.imgUrl, this.firstView, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendViewHolder.3
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                        RecommendViewHolder.this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendViewHolder.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (RecommendViewHolder.this.firstView == null) {
                                    return;
                                }
                                RecommendViewHolder recommendViewHolder = RecommendViewHolder.this;
                                recommendViewHolder.startDownTime(recommendViewHolder.firstView);
                            }
                        });
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                });
            }
        } else {
            this.firstView.setVisibility(8);
            this.testinGuide.setVisibility(8);
        }
        if (i3 == 9 || i3 == 36 || i3 == 0 || i3 == 18) {
            this.layout.setBackgroundResource(R.color.transparent);
        }
        if (i2 < 0 || arrayList == null || arrayList.size() <= i2) {
            return;
        }
        this.mFrom = i3;
        this.positionInDatas = i2;
        this.recommendItemBean = arrayList.get(i2);
        BaseRecommendViewHolder baseRecommendViewHolder = this.inUseViewHolder;
        if (baseRecommendViewHolder != null) {
            baseRecommendViewHolder.setmRecommendConfig(this.mRecommendConfig);
            if (this.mFrom == 9) {
                RecommendViewUtil.setGrayView(this.itemView, i2 < recommendOtherData.recommendGrayNumber && JDGrayModelUtils.getInstance().isGrayModel());
            }
        }
        int i5 = arrayList.get(i2).type;
        setViewType(i5);
        if (i5 != 0) {
            if (i5 != 26) {
                if (i5 != 1022) {
                    if (i5 == 9302) {
                        RecommendDna recommendDna = arrayList.get(i2).dna;
                        this.interactionBViewHolder.setmRecommendConfig(this.mRecommendConfig);
                        this.interactionBViewHolder.setIsAdRealExpo(false);
                        this.interactionBViewHolder.setDna(recommendDna, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3, i2);
                        return;
                    } else if (i5 == 11440) {
                        this.recommendProductListViewHolder.setmRecommendConfig(this.mRecommendConfig);
                        RecommendProduct recommendProduct = arrayList.get(i2).product;
                        this.recommendProductListViewHolder.setProduct(recommendProduct, i2, expoDataStore, i3, jDDisplayImageOptions);
                        this.recommendProductListViewHolder.setRecommendItem(arrayList.get(i2));
                        ExpoData expoData = this.recommendItemBean.expoData;
                        if (expoData == null || recommendProduct == null) {
                            return;
                        }
                        expoData.isBackUp = recommendProduct.isBackUp;
                        expoData.exposureSourceValue = recommendProduct.exposureJsonSourceValue;
                        return;
                    } else if (i5 != 20000) {
                        if (i5 != 15) {
                            if (i5 == 16) {
                                RecommendProduct recommendProduct2 = arrayList.get(i2).product;
                                RecommendProductHomeTabViewHolder recommendProductHomeTabViewHolder = this.homeTabNineNewViewHolder;
                                if (recommendProductHomeTabViewHolder != null) {
                                    recommendProductHomeTabViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                    this.homeTabNineNewViewHolder.setWaterFallStrategy(z);
                                    this.homeTabNineNewViewHolder.setProduct(recommendProduct2, i2, expoDataStore, i3, jDDisplayImageOptions);
                                    return;
                                }
                                return;
                            } else if (i5 != 23) {
                                if (i5 == 24) {
                                    RecommendDna recommendDna2 = arrayList.get(i2).dna;
                                    this.materialViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                    this.materialViewHolder.setData(recommendDna2, jDDisplayImageOptions, expoDataStore2, i3);
                                    return;
                                } else if (i5 == 36) {
                                    RecommendVideo recommendVideo = arrayList.get(i2).recommendVideo;
                                    this.ugcViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                    this.ugcViewHolder.setData(recommendVideo, expoDataStore2, i3);
                                    return;
                                } else if (i5 != 37) {
                                    if (i5 == 1018) {
                                        RecommendTemplate recommendTemplate = arrayList.get(i2).recommendTemplate;
                                        this.templateEighteenViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                        this.templateEighteenViewHolder.setData(recommendTemplate, jDDisplayImageOptions, expoDataStore2, i3, i2);
                                        return;
                                    } else if (i5 != 1019) {
                                        switch (i5) {
                                            case 18:
                                                RecommendVideo recommendVideo2 = arrayList.get(i2).recommendVideo;
                                                this.liveViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                this.liveViewHolder.setData(recommendVideo2, i2, expoDataStore2, i3);
                                                return;
                                            case 19:
                                                break;
                                            case 20:
                                                RecommendDna recommendDna3 = arrayList.get(i2).dna;
                                                this.recommendAdsShopViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                this.recommendAdsShopViewHolder.setData(recommendDna3, jDDisplayImageOptions, expoDataStore2, i3);
                                                return;
                                            default:
                                                switch (i5) {
                                                    case 31:
                                                        RecommendDna recommendDna4 = arrayList.get(i2).dna;
                                                        this.sceneLabelViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                        this.sceneLabelViewHolder.setSceneLabelInfo(recommendDna4, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3);
                                                        return;
                                                    case 32:
                                                        RecommendDna recommendDna5 = arrayList.get(i2).dna;
                                                        this.monetizationViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                        this.monetizationViewHolder.setDna(recommendDna5, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3);
                                                        return;
                                                    case 33:
                                                        RecommendDna recommendDna6 = arrayList.get(i2).dna;
                                                        this.interactionViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                        this.interactionViewHolder.setIsAdRealExpo(false);
                                                        this.interactionViewHolder.setDna(recommendDna6, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3, i2);
                                                        return;
                                                    case 34:
                                                        RecommendDna recommendDna7 = arrayList.get(i2).dna;
                                                        this.channelViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                        this.channelViewHolder.setDna(recommendDna7, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3);
                                                        return;
                                                    default:
                                                        switch (i5) {
                                                            case 999:
                                                                this.questionnairViewHolder.setData(arrayList.get(i2).dna, expoDataStore4);
                                                                return;
                                                            case 1000:
                                                                RecommendDna recommendDna8 = arrayList.get(i2).dna;
                                                                this.bannerBViewHolder.setIsAdRealExpo(false);
                                                                this.bannerBViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.bannerBViewHolder.setData(recommendDna8, jDDisplayImageOptions, i3);
                                                                return;
                                                            case 1001:
                                                                RecommendTemplate recommendTemplate2 = arrayList.get(i2).recommendTemplate;
                                                                this.templateOneViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateOneViewHolder.setData(recommendTemplate2, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3, i2);
                                                                return;
                                                            case 1002:
                                                                RecommendTemplate recommendTemplate3 = arrayList.get(i2).recommendTemplate;
                                                                this.templateTwoViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateTwoViewHolder.setData(recommendTemplate3, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3, i2);
                                                                return;
                                                            case 1003:
                                                                RecommendDna recommendDna9 = arrayList.get(i2).dna;
                                                                this.pdCouponViewHolder.setIsAdRealExpo(false);
                                                                this.pdCouponViewHolder.setData(recommendDna9, jDDisplayImageOptions, expoDataStore6, i3);
                                                                return;
                                                            case 1004:
                                                                RecommendTemplate recommendTemplate4 = arrayList.get(i2).recommendTemplate;
                                                                this.templateFourViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateFourViewHolder.setData(recommendTemplate4, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3, i2);
                                                                return;
                                                            case 1005:
                                                                RecommendVideo recommendVideo3 = arrayList.get(i2).recommendVideo;
                                                                this.templateFiveViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateFiveViewHolder.setData(recommendVideo3, expoDataStore2, i3, i2, jDDisplayImageOptions);
                                                                return;
                                                            case 1006:
                                                                RecommendVideo recommendVideo4 = arrayList.get(i2).recommendVideo;
                                                                this.templateSixViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateSixViewHolder.setData(recommendVideo4, expoDataStore2, i3, i2, jDDisplayImageOptions);
                                                                return;
                                                            case 1007:
                                                                RecommendVideo recommendVideo5 = arrayList.get(i2).recommendVideo;
                                                                this.templateLiveViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateLiveViewHolder.setData(recommendVideo5, expoDataStore2, i3, i2, jDDisplayImageOptions);
                                                                return;
                                                            case 1008:
                                                                RecommendDna recommendDna10 = arrayList.get(i2).dna;
                                                                this.templateEightViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateEightViewHolder.setTemplateEightData(recommendDna10, jDDisplayImageOptions, expoDataStore2, i3);
                                                                return;
                                                            case 1009:
                                                                RecommendTemplate recommendTemplate5 = arrayList.get(i2).recommendTemplate;
                                                                this.templateNineViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateNineViewHolder.setData(recommendTemplate5, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3, i2);
                                                                return;
                                                            case 1010:
                                                                RecommendTemplate recommendTemplate6 = arrayList.get(i2).recommendTemplate;
                                                                this.templateTenViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateTenViewHolder.setData(recommendTemplate6, jDDisplayImageOptions, expoDataStore2, i3, i2);
                                                                return;
                                                            case 1011:
                                                                RecommendTemplate recommendTemplate7 = arrayList.get(i2).recommendTemplate;
                                                                this.templateElevenViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateElevenViewHolder.setData(recommendTemplate7, jDDisplayImageOptions, expoDataStore2, i3, i2);
                                                                return;
                                                            case 1012:
                                                                RecommendTemplate recommendTemplate8 = arrayList.get(i2).recommendTemplate;
                                                                this.templateTwelveViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateTwelveViewHolder.setData(recommendTemplate8, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3, i2);
                                                                return;
                                                            case 1013:
                                                                RecommendVideo recommendVideo6 = arrayList.get(i2).recommendVideo;
                                                                this.templateThirteenViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateThirteenViewHolder.setData(recommendVideo6, expoDataStore2, i3, i5, i2, jDDisplayImageOptions);
                                                                return;
                                                            case 1014:
                                                                RecommendVideo recommendVideo7 = arrayList.get(i2).recommendVideo;
                                                                this.templateFourteenViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateFourteenViewHolder.setData(recommendVideo7, expoDataStore2, i3, i5, i2, jDDisplayImageOptions);
                                                                return;
                                                            case 1015:
                                                                RecommendVideo recommendVideo8 = arrayList.get(i2).recommendVideo;
                                                                this.templateFifteenViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                this.templateFifteenViewHolder.setData(recommendVideo8, jDDisplayImageOptions, expoDataStore2, i3, i2);
                                                                return;
                                                            default:
                                                                try {
                                                                    switch (i5) {
                                                                        case 2001:
                                                                            RecommendFestivalData recommendFestivalData = arrayList.get(i2).recommendFestivalData;
                                                                            this.festivalPromotionViewHolder.setIsAdRealExpo(false);
                                                                            this.festivalPromotionViewHolder.setData(recommendFestivalData, jDDisplayImageOptions, expoDataStore5, i3);
                                                                            return;
                                                                        case 2002:
                                                                            RecommendFestivalData recommendFestivalData2 = arrayList.get(i2).recommendFestivalData;
                                                                            this.recommendFestivalShortViewHolder.setIsAdRealExpo(false);
                                                                            this.recommendFestivalShortViewHolder.setData(recommendFestivalData2, jDDisplayImageOptions, expoDataStore5, i3);
                                                                            return;
                                                                        case 2003:
                                                                            this.homeCardTwoChannelViewHolder.setData(arrayList.get(i2).recommendHomeCardBean, jDDisplayImageOptions);
                                                                            break;
                                                                        case 2004:
                                                                            this.homeCardOneChannelViewHolder.setData(arrayList.get(i2).recommendHomeCardBean, jDDisplayImageOptions);
                                                                            break;
                                                                        case 2005:
                                                                            this.liveVideoTwoChannelViewHolder.setData(arrayList.get(i2).recommendHomeCardBean, jDDisplayImageOptions);
                                                                            break;
                                                                        case 2006:
                                                                            this.liveVideoOneChannelViewHolder.setData(arrayList.get(i2).recommendHomeCardBean, jDDisplayImageOptions);
                                                                            break;
                                                                        case 2007:
                                                                            this.liveVideoViewHolder.setData(arrayList.get(i2).recommendHomeCardBean, jDDisplayImageOptions);
                                                                            break;
                                                                        default:
                                                                            switch (i5) {
                                                                                case 10001:
                                                                                    RecommendPdTitle recommendPdTitle = arrayList.get(i2).recommendPdTitle;
                                                                                    this.titleViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                                    this.titleViewHolder.setData(recommendPdTitle, i2, expoDataStore, i3);
                                                                                    return;
                                                                                case 10002:
                                                                                    RecommendPdProductFor2 recommendPdProductFor2 = arrayList.get(i2).recommendPdProductFor2;
                                                                                    Recommend9ProductViewHolder recommend9ProductViewHolder = this.product2ViewHolder.leftViewHolder;
                                                                                    if (recommend9ProductViewHolder != null) {
                                                                                        recommend9ProductViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                                        this.product2ViewHolder.leftViewHolder.setWaterFallStrategy(false);
                                                                                    }
                                                                                    Recommend9ProductViewHolder recommend9ProductViewHolder2 = this.product2ViewHolder.rightViewHolder;
                                                                                    if (recommend9ProductViewHolder2 != null) {
                                                                                        recommend9ProductViewHolder2.setmRecommendConfig(this.mRecommendConfig);
                                                                                        this.product2ViewHolder.rightViewHolder.setWaterFallStrategy(false);
                                                                                    }
                                                                                    this.product2ViewHolder.setProduct2(recommendPdProductFor2, this.recommendItemBean, i2, expoDataStore, expoDataStore3, i3, jDDisplayImageOptions);
                                                                                    return;
                                                                                case 10003:
                                                                                    RecommendTemplate recommendTemplate9 = arrayList.get(i2).recommendTemplate;
                                                                                    this.templateThreeViewHolder.setmRecommendConfig(this.mRecommendConfig);
                                                                                    this.templateThreeViewHolder.setData(recommendTemplate9, jDDisplayImageOptions, expoDataStore, expoDataStore2, i3, i2);
                                                                                    return;
                                                                                default:
                                                                                    if (RecommendType.recom_dynamic_types.contains(Integer.valueOf(i5))) {
                                                                                        this.dynamicTemplateViewHolder.setData(this.recommendItemBean, expoDataStore2, i2, i3);
                                                                                        return;
                                                                                    }
                                                                                    return;
                                                                            }
                                                                    }
                                                                    return;
                                                                } catch (Exception unused) {
                                                                    return;
                                                                }
                                                        }
                                                }
                                        }
                                    }
                                }
                            }
                        }
                        RecommendHomeTabTemp recommendHomeTabTemp = arrayList.get(i2).recommendHomeTabTemp;
                        this.recommendHomeTabTempViewHolder.setmRecommendConfig(this.mRecommendConfig);
                        this.recommendHomeTabTempViewHolder.setData(recommendHomeTabTemp, jDDisplayImageOptions, expoDataStore2, i3);
                        return;
                    } else {
                        this.dynamicPDViewHolder.setData(this.recommendItemBean, expoDataStore, i2, i3);
                        return;
                    }
                }
                RecommendVideo recommendVideo9 = arrayList.get(i2).recommendVideo;
                this.templateNineteenViewHolder.setmRecommendConfig(this.mRecommendConfig);
                this.templateNineteenViewHolder.setData(recommendVideo9, jDDisplayImageOptions, expoDataStore2, i3, i2, i5);
                return;
            }
            RecommendDna recommendDna11 = arrayList.get(i2).dna;
            this.autoPlayViewHolder.setmRecommendConfig(this.mRecommendConfig);
            this.autoPlayViewHolder.setData(recommendDna11, jDDisplayImageOptions, expoDataStore2, i3);
            return;
        }
        this.leftProductViewHolder.setmRecommendConfig(this.mRecommendConfig);
        this.leftProductViewHolder.setWaterFallStrategy(z);
        if (recommendOtherData != null) {
            this.leftProductViewHolder.setDisplayPlan(recommendOtherData.get924UIStrategy());
        }
        RecommendProduct recommendProduct3 = arrayList.get(i2).product;
        this.leftProductViewHolder.setRecommendItem(arrayList.get(i2));
        if (recommendProduct3 != null && !TextUtils.isEmpty(recommendProduct3.isSimilarGoods) && "1".equals(recommendProduct3.isSimilarGoods)) {
            this.leftProductViewHolder.setProduct(recommendProduct3, i2, expoDataStore3, i3, jDDisplayImageOptions);
        } else {
            this.leftProductViewHolder.setProduct(recommendProduct3, i2, expoDataStore, i3, jDDisplayImageOptions);
        }
        ExpoData expoData2 = this.recommendItemBean.expoData;
        if (expoData2 == null || recommendProduct3 == null) {
            return;
        }
        expoData2.isBackUp = recommendProduct3.isBackUp;
        expoData2.exposureSourceValue = recommendProduct3.exposureJsonSourceValue;
    }

    public void bindRecommendViewHolder(ArrayList<RecommendItem> arrayList, int i2, ExpoDataStore expoDataStore, int i3, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore2, ExpoDataStore expoDataStore3) {
        int i4;
        RecommendProduct recommendProduct;
        RecommendItem recommendItem;
        ExpoData expoData;
        boolean z;
        this.mFrom = i3;
        if (i2 < 2 && ((z = this.isNeedShowGuide) || this.isNeedShowTestGuide)) {
            if (this.isNeedShowTestGuide && i2 == 0) {
                this.testinGuide.setVisibility(0);
                startDownTime(this.testinGuide);
                this.firstView.setVisibility(8);
            } else if (i2 == 1 && z) {
                this.testinGuide.setVisibility(8);
                this.firstView.setVisibility(0);
                JDImageUtils.displayImage(this.imgUrl, this.firstView, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendViewHolder.2
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                        RecommendViewHolder.this.mMainHandler.post(new Runnable() { // from class: com.jingdong.common.recommend.forlist.RecommendViewHolder.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (RecommendViewHolder.this.firstView == null) {
                                    return;
                                }
                                RecommendViewHolder recommendViewHolder = RecommendViewHolder.this;
                                recommendViewHolder.startDownTime(recommendViewHolder.firstView);
                            }
                        });
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                });
            }
        } else {
            this.firstView.setVisibility(8);
            this.testinGuide.setVisibility(8);
        }
        if (i3 == 9) {
            this.layout.setBackgroundResource(R.color.transparent);
        } else {
            RecommendConfig recommendConfig = this.mRecommendConfig;
            if (recommendConfig != null && (recommendConfig.isDarkEnable() || this.mRecommendConfig.isDarkTheme())) {
                this.layout.setBackgroundColor(DeepDarkUtils.getDarkColor_F2F2F2_bg1());
            } else {
                this.layout.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_F6F6F6));
            }
        }
        if (i2 < 0 || arrayList == null || arrayList.size() <= (i4 = i2 * 2)) {
            return;
        }
        this.recommendLineTwoBean = new RecommendLineTwoBean();
        int i5 = arrayList.get(i4).type;
        int i6 = i4 + 1;
        int i7 = arrayList.size() > i6 ? arrayList.get(i6).type : 0;
        if (i5 == 0) {
            RecommendProduct recommendProduct2 = arrayList.get(i4).product;
            this.leftProductViewHolder.setmRecommendConfig(this.mRecommendConfig);
            this.leftProductViewHolder.setWaterFallStrategy(false);
            if (recommendProduct2 != null && !TextUtils.isEmpty(recommendProduct2.isSimilarGoods) && "1".equals(recommendProduct2.isSimilarGoods)) {
                this.leftProductViewHolder.setProduct(recommendProduct2, i4, expoDataStore3, i3, jDDisplayImageOptions);
            } else {
                this.leftProductViewHolder.setProduct(recommendProduct2, i4, expoDataStore, i3, jDDisplayImageOptions);
            }
            RecommendItem recommendItem2 = arrayList.get(i4);
            if (recommendItem2 != null && (expoData = recommendItem2.expoData) != null && recommendProduct2 != null) {
                expoData.exposureSourceValue = recommendProduct2.exposureJsonSourceValue;
                this.recommendLineTwoBean.recommendItemBeans.add(recommendItem2);
            }
        }
        if (i7 != 0) {
            return;
        }
        if (arrayList.size() > i6) {
            RecommendItem recommendItem3 = arrayList.get(i6);
            recommendProduct = recommendItem3.product;
            recommendItem = recommendItem3;
        } else {
            recommendProduct = null;
            recommendItem = null;
        }
        this.rightProductViewHolder.setmRecommendConfig(this.mRecommendConfig);
        this.rightProductViewHolder.setWaterFallStrategy(false);
        if (recommendProduct != null && !TextUtils.isEmpty(recommendProduct.isSimilarGoods) && "1".equals(recommendProduct.isSimilarGoods)) {
            this.rightProductViewHolder.setProduct(recommendProduct, i6, expoDataStore3, i3, jDDisplayImageOptions);
        } else {
            this.rightProductViewHolder.setProduct(recommendProduct, i6, expoDataStore, i3, jDDisplayImageOptions);
        }
        if (recommendItem == null || recommendProduct == null) {
            return;
        }
        ExpoData expoData2 = recommendItem.expoData;
        if (expoData2 != null) {
            expoData2.exposureSourceValue = recommendProduct.exposureJsonSourceValue;
        }
        this.recommendLineTwoBean.recommendItemBeans.add(recommendItem);
    }

    public void doAdRealExpo() {
        try {
            RecommendItem recommendItem = this.recommendItemBean;
            if (recommendItem == null || recommendItem.hasAdExpo) {
                return;
            }
            recommendItem.hasAdExpo = true;
            ArrayList<String> arrayList = recommendItem.ad_exposal_url;
            if (arrayList != null && !arrayList.isEmpty()) {
                Iterator<String> it = this.recommendItemBean.ad_exposal_url.iterator();
                while (it.hasNext()) {
                    this.clickedListener.onRecommendMoneyExpo(it.next());
                    OKLog.d("RecommendAd", "==\u5e7f\u544a\u771f\u662f\u4e0a\u62a5====" + this.positionInDatas);
                }
            } else if (TextUtils.isEmpty(this.recommendItemBean.client_exposal_url) || this.clickedListener == null || isCacheData()) {
            } else {
                this.clickedListener.onRecommendMoneyExpo(this.recommendItemBean.client_exposal_url);
                OKLog.d("RecommendAd", "==\u5e7f\u544a\u771f\u662f\u4e0a\u62a5====" + this.positionInDatas + "\\/n" + this.recommendItemBean.client_exposal_url);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public void doRealExpo() {
        RecommendProduct recommendProduct;
        try {
            RecommendItem recommendItem = this.recommendItemBean;
            if (recommendItem == null || recommendItem.hasRecommendExpo) {
                return;
            }
            if (this.viewType == 999) {
                doRealRightExpo();
            } else if (isCacheData()) {
                doRealRightExpo();
            } else {
                RecommendItem recommendItem2 = this.recommendItemBean;
                recommendItem2.hasRecommendExpo = true;
                if (!recommendItem2.isHomeData && RecommendType.isAggreationType(this.viewType)) {
                    ExpoDataStore expoDataStore = this.realAggreationExpoDataStore;
                    if (expoDataStore != null) {
                        expoDataStore.putExoJsonData(-1, this.recommendItemBean.expoData);
                        return;
                    }
                    return;
                }
                int i2 = this.viewType;
                if ((i2 == 0 || i2 == 11440) && this.realProductExpoDataStore != null) {
                    try {
                        RecommendMtaUtils.appHeightToExpo(this.recommendItemBean, this.itemView.getHeight());
                        RecommendItem recommendItem3 = this.recommendItemBean;
                        ExpoData expoData = recommendItem3.expoData;
                        if (expoData != null && (recommendProduct = recommendItem3.product) != null) {
                            expoData.appendJson = recommendProduct.appendMtaJson_real;
                        }
                    } catch (Exception unused) {
                    }
                    this.realProductExpoDataStore.putExoJsonData(this.mFrom, this.recommendItemBean.expoData);
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public void doRealRightExpo() {
        try {
            if (this.itemView != null) {
                RecommendLineTwoBean recommendLineTwoBean = this.recommendLineTwoBean;
                if (recommendLineTwoBean != null) {
                    Iterator<RecommendItem> it = recommendLineTwoBean.recommendItemBeans.iterator();
                    while (it.hasNext()) {
                        doItemExpo(it.next());
                    }
                    this.recommendLineTwoBean.hasRecommendExpo = true;
                    return;
                }
                RecommendItem recommendItem = this.recommendItemBean;
                if (recommendItem != null) {
                    doItemExpo(recommendItem);
                    this.recommendItemBean.hasRecommendExpo = true;
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public void doRealRightExpoForHomeFloor(double d) {
        BaseRecommendViewHolder baseRecommendViewHolder = this.inUseViewHolder;
        if (baseRecommendViewHolder != null) {
            baseRecommendViewHolder.exposurePercent(d);
        }
    }

    public RecommendLineTwoBean getLineTwoBean() {
        return this.recommendLineTwoBean;
    }

    public int getViewType() {
        return this.viewType;
    }

    public boolean needAdRealExpo() {
        RecommendItem recommendItem = this.recommendItemBean;
        if (recommendItem == null || TextUtils.isEmpty(recommendItem.client_exposal_url)) {
            return false;
        }
        return !this.recommendItemBean.hasAdExpo;
    }

    public boolean needRealExpo() {
        if (this.recommendLineTwoBean != null) {
            return !r0.hasRecommendExpo;
        }
        if (this.recommendItemBean != null) {
            return !r0.hasRecommendExpo;
        }
        return false;
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void setClickedListener(RecommendUtil.OnRecommendClickedListener onRecommendClickedListener) {
        super.setClickedListener(onRecommendClickedListener);
        Recommend9ProductViewHolder recommend9ProductViewHolder = this.leftProductViewHolder;
        if (recommend9ProductViewHolder != null) {
            recommend9ProductViewHolder.setClickedListener(onRecommendClickedListener);
        }
        Recommend9ProductViewHolder recommend9ProductViewHolder2 = this.rightProductViewHolder;
        if (recommend9ProductViewHolder2 != null) {
            recommend9ProductViewHolder2.setClickedListener(onRecommendClickedListener);
        }
        RecommendProductListViewHolder recommendProductListViewHolder = this.recommendProductListViewHolder;
        if (recommendProductListViewHolder != null) {
            recommendProductListViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendProductHomeTabViewHolder recommendProductHomeTabViewHolder = this.homeTabNineNewViewHolder;
        if (recommendProductHomeTabViewHolder != null) {
            recommendProductHomeTabViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendSceneLabelViewHolder recommendSceneLabelViewHolder = this.sceneLabelViewHolder;
        if (recommendSceneLabelViewHolder != null) {
            recommendSceneLabelViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendChannelViewHolder recommendChannelViewHolder = this.channelViewHolder;
        if (recommendChannelViewHolder != null) {
            recommendChannelViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendInteractionViewHolder recommendInteractionViewHolder = this.interactionViewHolder;
        if (recommendInteractionViewHolder != null) {
            recommendInteractionViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendInteractionBViewHolder recommendInteractionBViewHolder = this.interactionBViewHolder;
        if (recommendInteractionBViewHolder != null) {
            recommendInteractionBViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendQuesNairViewHolder recommendQuesNairViewHolder = this.questionnairViewHolder;
        if (recommendQuesNairViewHolder != null) {
            recommendQuesNairViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendContentMaterialViewHolder recommendContentMaterialViewHolder = this.materialViewHolder;
        if (recommendContentMaterialViewHolder != null) {
            recommendContentMaterialViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendMonetizationViewHolder recommendMonetizationViewHolder = this.monetizationViewHolder;
        if (recommendMonetizationViewHolder != null) {
            recommendMonetizationViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendAdsShopViewHolder recommendAdsShopViewHolder = this.recommendAdsShopViewHolder;
        if (recommendAdsShopViewHolder != null) {
            recommendAdsShopViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendAutoPlayViewHolder recommendAutoPlayViewHolder = this.autoPlayViewHolder;
        if (recommendAutoPlayViewHolder != null) {
            recommendAutoPlayViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendAutoPlayViewHolder recommendAutoPlayViewHolder2 = this.templateEightViewHolder;
        if (recommendAutoPlayViewHolder2 != null) {
            recommendAutoPlayViewHolder2.setClickedListener(onRecommendClickedListener);
        }
        RecommendUgcViewHolder recommendUgcViewHolder = this.ugcViewHolder;
        if (recommendUgcViewHolder != null) {
            recommendUgcViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendLiveViewHolder recommendLiveViewHolder = this.liveViewHolder;
        if (recommendLiveViewHolder != null) {
            recommendLiveViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateOneViewHolder recommendTemplateOneViewHolder = this.templateOneViewHolder;
        if (recommendTemplateOneViewHolder != null) {
            recommendTemplateOneViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateTwoViewHolder recommendTemplateTwoViewHolder = this.templateTwoViewHolder;
        if (recommendTemplateTwoViewHolder != null) {
            recommendTemplateTwoViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateThreeViewHolder recommendTemplateThreeViewHolder = this.templateThreeViewHolder;
        if (recommendTemplateThreeViewHolder != null) {
            recommendTemplateThreeViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateFourViewHolder recommendTemplateFourViewHolder = this.templateFourViewHolder;
        if (recommendTemplateFourViewHolder != null) {
            recommendTemplateFourViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateLiveViewHolder recommendTemplateLiveViewHolder = this.templateLiveViewHolder;
        if (recommendTemplateLiveViewHolder != null) {
            recommendTemplateLiveViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateVideoViewHolder recommendTemplateVideoViewHolder = this.templateFiveViewHolder;
        if (recommendTemplateVideoViewHolder != null) {
            recommendTemplateVideoViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateSixViewHolder recommendTemplateSixViewHolder = this.templateSixViewHolder;
        if (recommendTemplateSixViewHolder != null) {
            recommendTemplateSixViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateNineViewHolder recommendTemplateNineViewHolder = this.templateNineViewHolder;
        if (recommendTemplateNineViewHolder != null) {
            recommendTemplateNineViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateTenViewHolder recommendTemplateTenViewHolder = this.templateTenViewHolder;
        if (recommendTemplateTenViewHolder != null) {
            recommendTemplateTenViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateElevenViewHolder recommendTemplateElevenViewHolder = this.templateElevenViewHolder;
        if (recommendTemplateElevenViewHolder != null) {
            recommendTemplateElevenViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateTwelveViewHolder recommendTemplateTwelveViewHolder = this.templateTwelveViewHolder;
        if (recommendTemplateTwelveViewHolder != null) {
            recommendTemplateTwelveViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateThirteenViewHolder recommendTemplateThirteenViewHolder = this.templateThirteenViewHolder;
        if (recommendTemplateThirteenViewHolder != null) {
            recommendTemplateThirteenViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateThirteenViewHolder recommendTemplateThirteenViewHolder2 = this.templateFourteenViewHolder;
        if (recommendTemplateThirteenViewHolder2 != null) {
            recommendTemplateThirteenViewHolder2.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateFifteenViewHolder recommendTemplateFifteenViewHolder = this.templateFifteenViewHolder;
        if (recommendTemplateFifteenViewHolder != null) {
            recommendTemplateFifteenViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateEighteenViewHolder recommendTemplateEighteenViewHolder = this.templateEighteenViewHolder;
        if (recommendTemplateEighteenViewHolder != null) {
            recommendTemplateEighteenViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTemplateNineteenViewHolder recommendTemplateNineteenViewHolder = this.templateNineteenViewHolder;
        if (recommendTemplateNineteenViewHolder != null) {
            recommendTemplateNineteenViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendHomeTabTempViewHolder recommendHomeTabTempViewHolder = this.recommendHomeTabTempViewHolder;
        if (recommendHomeTabTempViewHolder != null) {
            recommendHomeTabTempViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendTitleViewHolder recommendTitleViewHolder = this.titleViewHolder;
        if (recommendTitleViewHolder != null) {
            recommendTitleViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendPdProduct2ViewHolder recommendPdProduct2ViewHolder = this.product2ViewHolder;
        if (recommendPdProduct2ViewHolder != null) {
            recommendPdProduct2ViewHolder.leftViewHolder.setClickedListener(onRecommendClickedListener);
            this.product2ViewHolder.rightViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendPDCouponViewHolder recommendPDCouponViewHolder = this.pdCouponViewHolder;
        if (recommendPDCouponViewHolder != null) {
            recommendPDCouponViewHolder.setClickedListener(onRecommendClickedListener);
        }
        BaseRecommendViewHolder baseRecommendViewHolder = this.inUseViewHolder;
        if (baseRecommendViewHolder != null) {
            baseRecommendViewHolder.setClickedListener(onRecommendClickedListener);
        }
    }

    public void setExpoDataShop(ExpoDataStore expoDataStore) {
        this.expoDataShop = expoDataStore;
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void setHomePageTestPlanLoader(RecommendUtil.IRecommendHomePageTestPlanLoader iRecommendHomePageTestPlanLoader) {
        super.setHomePageTestPlanLoader(iRecommendHomePageTestPlanLoader);
        Recommend9ProductViewHolder recommend9ProductViewHolder = this.leftProductViewHolder;
        if (recommend9ProductViewHolder != null) {
            recommend9ProductViewHolder.setHomePageTestPlanLoader(iRecommendHomePageTestPlanLoader);
        }
        Recommend9ProductViewHolder recommend9ProductViewHolder2 = this.rightProductViewHolder;
        if (recommend9ProductViewHolder2 != null) {
            recommend9ProductViewHolder2.setHomePageTestPlanLoader(iRecommendHomePageTestPlanLoader);
        }
        RecommendInteractionViewHolder recommendInteractionViewHolder = this.interactionViewHolder;
        if (recommendInteractionViewHolder != null) {
            recommendInteractionViewHolder.setHomePageTestPlanLoader(iRecommendHomePageTestPlanLoader);
        }
        RecommendInteractionBViewHolder recommendInteractionBViewHolder = this.interactionBViewHolder;
        if (recommendInteractionBViewHolder != null) {
            recommendInteractionBViewHolder.setHomePageTestPlanLoader(iRecommendHomePageTestPlanLoader);
        }
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void setIsAdRealExpo(boolean z) {
        BaseRecommendViewHolder baseRecommendViewHolder = this.inUseViewHolder;
        if (baseRecommendViewHolder != null) {
            baseRecommendViewHolder.setIsAdRealExpo(z);
        }
    }

    public void setRealExpoDataStore(ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2) {
        this.realProductExpoDataStore = expoDataStore;
        this.realAggreationExpoDataStore = expoDataStore2;
    }

    public void setViewType(int i2) {
        this.viewType = i2;
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void setmRecommendConfig(RecommendConfig recommendConfig) {
        this.mRecommendConfig = recommendConfig;
    }

    public RecommendViewHolder(BaseActivity baseActivity, View view, int i2, RecommendOtherData recommendOtherData, int i3, int i4, HashSet<String> hashSet) {
        super(view);
        ViewStub viewStub;
        this.isNeedShowTestGuide = false;
        this.positionInDatas = -1;
        this.realExpoHashSet = hashSet;
        this.layout = view;
        this.firstView = (SimpleDraweeView) view.findViewById(R.id.guideView);
        this.testinGuide = (ImageView) view.findViewById(R.id.recommend_testin_guide);
        this.mMainHandler = new Handler(Looper.getMainLooper());
        showGuide(recommendOtherData);
        if (i2 != 0) {
            if (i2 != 26) {
                if (i2 != 1022) {
                    if (i2 == 9302) {
                        ViewStub viewStub2 = (ViewStub) view.findViewById(R.id.recommend_interaction_b);
                        if (viewStub2 != null) {
                            RecommendInteractionBViewHolder recommendInteractionBViewHolder = new RecommendInteractionBViewHolder(baseActivity, viewStub2.inflate());
                            this.interactionBViewHolder = recommendInteractionBViewHolder;
                            recommendInteractionBViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                            this.inUseViewHolder = this.interactionBViewHolder;
                            return;
                        }
                        return;
                    } else if (i2 == 11440) {
                        ViewStub viewStub3 = (ViewStub) view.findViewById(R.id.recommend_product_list);
                        if (viewStub3 != null) {
                            RecommendProductListViewHolder recommendProductListViewHolder = new RecommendProductListViewHolder(baseActivity, viewStub3.inflate(), i2);
                            this.recommendProductListViewHolder = recommendProductListViewHolder;
                            recommendProductListViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                            this.inUseViewHolder = this.recommendProductListViewHolder;
                            return;
                        }
                        return;
                    } else if (i2 != 20000) {
                        if (i2 != 15) {
                            if (i2 == 16) {
                                ViewStub viewStub4 = (ViewStub) view.findViewById(R.id.recommend_home_tab_nine);
                                if (viewStub4 != null) {
                                    RecommendProductHomeTabViewHolder recommendProductHomeTabViewHolder = new RecommendProductHomeTabViewHolder(baseActivity, viewStub4.inflate());
                                    this.homeTabNineNewViewHolder = recommendProductHomeTabViewHolder;
                                    recommendProductHomeTabViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                    this.inUseViewHolder = this.homeTabNineNewViewHolder;
                                    return;
                                }
                                return;
                            } else if (i2 != 23) {
                                if (i2 == 24) {
                                    ViewStub viewStub5 = (ViewStub) view.findViewById(R.id.recommend_content_material);
                                    if (viewStub5 != null) {
                                        RecommendContentMaterialViewHolder recommendContentMaterialViewHolder = new RecommendContentMaterialViewHolder(viewStub5.inflate());
                                        this.materialViewHolder = recommendContentMaterialViewHolder;
                                        recommendContentMaterialViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                        this.inUseViewHolder = this.materialViewHolder;
                                        return;
                                    }
                                    return;
                                } else if (i2 == 36) {
                                    ViewStub viewStub6 = (ViewStub) view.findViewById(R.id.recommend_ugc);
                                    if (viewStub6 != null) {
                                        RecommendUgcViewHolder recommendUgcViewHolder = new RecommendUgcViewHolder(viewStub6.inflate());
                                        this.ugcViewHolder = recommendUgcViewHolder;
                                        recommendUgcViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                        this.inUseViewHolder = this.ugcViewHolder;
                                        return;
                                    }
                                    return;
                                } else if (i2 != 37) {
                                    if (i2 == 1018) {
                                        ViewStub viewStub7 = (ViewStub) view.findViewById(R.id.recommend_template_eighteen);
                                        if (viewStub7 != null) {
                                            RecommendTemplateEighteenViewHolder recommendTemplateEighteenViewHolder = new RecommendTemplateEighteenViewHolder(baseActivity, viewStub7.inflate());
                                            this.templateEighteenViewHolder = recommendTemplateEighteenViewHolder;
                                            recommendTemplateEighteenViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                            this.inUseViewHolder = this.templateEighteenViewHolder;
                                            return;
                                        }
                                        return;
                                    } else if (i2 != 1019) {
                                        switch (i2) {
                                            case 18:
                                                ViewStub viewStub8 = (ViewStub) view.findViewById(R.id.recommend_live);
                                                if (viewStub8 != null) {
                                                    RecommendLiveViewHolder recommendLiveViewHolder = new RecommendLiveViewHolder(viewStub8.inflate());
                                                    this.liveViewHolder = recommendLiveViewHolder;
                                                    recommendLiveViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                    this.inUseViewHolder = this.liveViewHolder;
                                                    return;
                                                }
                                                return;
                                            case 19:
                                                break;
                                            case 20:
                                                ViewStub viewStub9 = (ViewStub) view.findViewById(R.id.recommend_adsshop);
                                                if (viewStub9 != null) {
                                                    RecommendAdsShopViewHolder recommendAdsShopViewHolder = new RecommendAdsShopViewHolder(viewStub9.inflate());
                                                    this.recommendAdsShopViewHolder = recommendAdsShopViewHolder;
                                                    recommendAdsShopViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                    this.inUseViewHolder = this.recommendAdsShopViewHolder;
                                                    return;
                                                }
                                                return;
                                            default:
                                                switch (i2) {
                                                    case 31:
                                                        ViewStub viewStub10 = (ViewStub) view.findViewById(R.id.recommend_scene_label);
                                                        if (viewStub10 != null) {
                                                            RecommendSceneLabelViewHolder recommendSceneLabelViewHolder = new RecommendSceneLabelViewHolder(baseActivity, viewStub10.inflate());
                                                            this.sceneLabelViewHolder = recommendSceneLabelViewHolder;
                                                            recommendSceneLabelViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                            this.inUseViewHolder = this.sceneLabelViewHolder;
                                                            return;
                                                        }
                                                        return;
                                                    case 32:
                                                        ViewStub viewStub11 = (ViewStub) view.findViewById(R.id.recommend_monetization);
                                                        if (viewStub11 != null) {
                                                            RecommendMonetizationViewHolder recommendMonetizationViewHolder = new RecommendMonetizationViewHolder(viewStub11.inflate());
                                                            this.monetizationViewHolder = recommendMonetizationViewHolder;
                                                            recommendMonetizationViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                            this.inUseViewHolder = this.monetizationViewHolder;
                                                            return;
                                                        }
                                                        return;
                                                    case 33:
                                                        ViewStub viewStub12 = (ViewStub) view.findViewById(R.id.recommend_interaction);
                                                        if (viewStub12 != null) {
                                                            RecommendInteractionViewHolder recommendInteractionViewHolder = new RecommendInteractionViewHolder(baseActivity, viewStub12.inflate());
                                                            this.interactionViewHolder = recommendInteractionViewHolder;
                                                            recommendInteractionViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                            this.inUseViewHolder = this.interactionViewHolder;
                                                            return;
                                                        }
                                                        return;
                                                    case 34:
                                                        ViewStub viewStub13 = (ViewStub) view.findViewById(R.id.recommend_channel);
                                                        if (viewStub13 != null) {
                                                            RecommendChannelViewHolder recommendChannelViewHolder = new RecommendChannelViewHolder(viewStub13.inflate());
                                                            this.channelViewHolder = recommendChannelViewHolder;
                                                            recommendChannelViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                            this.inUseViewHolder = this.channelViewHolder;
                                                            return;
                                                        }
                                                        return;
                                                    default:
                                                        switch (i2) {
                                                            case 999:
                                                                ViewStub viewStub14 = (ViewStub) view.findViewById(R.id.recommend_questionnair);
                                                                if (viewStub14 != null) {
                                                                    RecommendQuesNairViewHolder recommendQuesNairViewHolder = new RecommendQuesNairViewHolder(viewStub14.inflate());
                                                                    this.questionnairViewHolder = recommendQuesNairViewHolder;
                                                                    this.inUseViewHolder = recommendQuesNairViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1000:
                                                                ViewStub viewStub15 = (ViewStub) view.findViewById(R.id.recommend_banner);
                                                                if (viewStub15 != null) {
                                                                    RecommendBannerBViewHolder recommendBannerBViewHolder = new RecommendBannerBViewHolder(viewStub15.inflate());
                                                                    this.bannerBViewHolder = recommendBannerBViewHolder;
                                                                    recommendBannerBViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.bannerBViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1001:
                                                                ViewStub viewStub16 = (ViewStub) view.findViewById(R.id.recommend_template_one);
                                                                if (viewStub16 != null) {
                                                                    RecommendTemplateOneViewHolder recommendTemplateOneViewHolder = new RecommendTemplateOneViewHolder(baseActivity, viewStub16.inflate());
                                                                    this.templateOneViewHolder = recommendTemplateOneViewHolder;
                                                                    recommendTemplateOneViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateOneViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1002:
                                                                ViewStub viewStub17 = (ViewStub) view.findViewById(R.id.recommend_template_two);
                                                                if (viewStub17 != null) {
                                                                    RecommendTemplateTwoViewHolder recommendTemplateTwoViewHolder = new RecommendTemplateTwoViewHolder(baseActivity, viewStub17.inflate());
                                                                    this.templateTwoViewHolder = recommendTemplateTwoViewHolder;
                                                                    recommendTemplateTwoViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateTwoViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1003:
                                                                ViewStub viewStub18 = (ViewStub) view.findViewById(R.id.recommend_phone_recycle_coupon);
                                                                if (viewStub18 != null) {
                                                                    RecommendPDCouponViewHolder recommendPDCouponViewHolder = new RecommendPDCouponViewHolder(baseActivity, viewStub18.inflate());
                                                                    this.pdCouponViewHolder = recommendPDCouponViewHolder;
                                                                    this.inUseViewHolder = recommendPDCouponViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1004:
                                                                ViewStub viewStub19 = (ViewStub) view.findViewById(R.id.recommend_template_four);
                                                                if (viewStub19 != null) {
                                                                    RecommendTemplateFourViewHolder recommendTemplateFourViewHolder = new RecommendTemplateFourViewHolder(baseActivity, viewStub19.inflate());
                                                                    this.templateFourViewHolder = recommendTemplateFourViewHolder;
                                                                    recommendTemplateFourViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateFourViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1005:
                                                                ViewStub viewStub20 = (ViewStub) view.findViewById(R.id.recommend_template_five);
                                                                if (viewStub20 != null) {
                                                                    RecommendTemplateVideoViewHolder recommendTemplateVideoViewHolder = new RecommendTemplateVideoViewHolder(baseActivity, viewStub20.inflate());
                                                                    this.templateFiveViewHolder = recommendTemplateVideoViewHolder;
                                                                    recommendTemplateVideoViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateFiveViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1006:
                                                                ViewStub viewStub21 = (ViewStub) view.findViewById(R.id.recommend_template_six);
                                                                if (viewStub21 != null) {
                                                                    RecommendTemplateSixViewHolder recommendTemplateSixViewHolder = new RecommendTemplateSixViewHolder(baseActivity, viewStub21.inflate());
                                                                    this.templateSixViewHolder = recommendTemplateSixViewHolder;
                                                                    recommendTemplateSixViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateSixViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1007:
                                                                ViewStub viewStub22 = (ViewStub) view.findViewById(R.id.recommend_template_live);
                                                                if (viewStub22 != null) {
                                                                    RecommendTemplateLiveViewHolder recommendTemplateLiveViewHolder = new RecommendTemplateLiveViewHolder(baseActivity, viewStub22.inflate());
                                                                    this.templateLiveViewHolder = recommendTemplateLiveViewHolder;
                                                                    recommendTemplateLiveViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateLiveViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1008:
                                                                ViewStub viewStub23 = (ViewStub) view.findViewById(R.id.recommend_auto_play);
                                                                if (viewStub23 != null) {
                                                                    RecommendAutoPlayViewHolder recommendAutoPlayViewHolder = new RecommendAutoPlayViewHolder(viewStub23.inflate());
                                                                    this.templateEightViewHolder = recommendAutoPlayViewHolder;
                                                                    recommendAutoPlayViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateEightViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1009:
                                                                ViewStub viewStub24 = (ViewStub) view.findViewById(R.id.recommend_template_nine);
                                                                if (viewStub24 != null) {
                                                                    RecommendTemplateNineViewHolder recommendTemplateNineViewHolder = new RecommendTemplateNineViewHolder(baseActivity, viewStub24.inflate());
                                                                    this.templateNineViewHolder = recommendTemplateNineViewHolder;
                                                                    recommendTemplateNineViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateNineViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1010:
                                                                ViewStub viewStub25 = (ViewStub) view.findViewById(R.id.recommend_template_ten);
                                                                if (viewStub25 != null) {
                                                                    RecommendTemplateTenViewHolder recommendTemplateTenViewHolder = new RecommendTemplateTenViewHolder(baseActivity, viewStub25.inflate());
                                                                    this.templateTenViewHolder = recommendTemplateTenViewHolder;
                                                                    recommendTemplateTenViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateTenViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1011:
                                                                ViewStub viewStub26 = (ViewStub) view.findViewById(R.id.recommend_template_eleven);
                                                                if (viewStub26 != null) {
                                                                    RecommendTemplateElevenViewHolder recommendTemplateElevenViewHolder = new RecommendTemplateElevenViewHolder(baseActivity, viewStub26.inflate());
                                                                    this.templateElevenViewHolder = recommendTemplateElevenViewHolder;
                                                                    recommendTemplateElevenViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateElevenViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1012:
                                                                ViewStub viewStub27 = (ViewStub) view.findViewById(R.id.recommend_template_twelve);
                                                                if (viewStub27 != null) {
                                                                    RecommendTemplateTwelveViewHolder recommendTemplateTwelveViewHolder = new RecommendTemplateTwelveViewHolder(baseActivity, viewStub27.inflate());
                                                                    this.templateTwelveViewHolder = recommendTemplateTwelveViewHolder;
                                                                    recommendTemplateTwelveViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateTwelveViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1013:
                                                                ViewStub viewStub28 = (ViewStub) view.findViewById(R.id.recommend_template_thirteen);
                                                                if (viewStub28 != null) {
                                                                    RecommendTemplateThirteenViewHolder recommendTemplateThirteenViewHolder = new RecommendTemplateThirteenViewHolder(baseActivity, viewStub28.inflate());
                                                                    this.templateThirteenViewHolder = recommendTemplateThirteenViewHolder;
                                                                    recommendTemplateThirteenViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateThirteenViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1014:
                                                                ViewStub viewStub29 = (ViewStub) view.findViewById(R.id.recommend_template_fourteen);
                                                                if (viewStub29 != null) {
                                                                    RecommendTemplateThirteenViewHolder recommendTemplateThirteenViewHolder2 = new RecommendTemplateThirteenViewHolder(baseActivity, viewStub29.inflate());
                                                                    this.templateFourteenViewHolder = recommendTemplateThirteenViewHolder2;
                                                                    recommendTemplateThirteenViewHolder2.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateFourteenViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            case 1015:
                                                                ViewStub viewStub30 = (ViewStub) view.findViewById(R.id.recommend_template_fifteen);
                                                                if (viewStub30 != null) {
                                                                    RecommendTemplateFifteenViewHolder recommendTemplateFifteenViewHolder = new RecommendTemplateFifteenViewHolder(baseActivity, viewStub30.inflate());
                                                                    this.templateFifteenViewHolder = recommendTemplateFifteenViewHolder;
                                                                    recommendTemplateFifteenViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                    this.inUseViewHolder = this.templateFifteenViewHolder;
                                                                    return;
                                                                }
                                                                return;
                                                            default:
                                                                switch (i2) {
                                                                    case 2001:
                                                                        ViewStub viewStub31 = (ViewStub) view.findViewById(R.id.recommend_festival_promotion);
                                                                        if (viewStub31 != null) {
                                                                            RecommendFestivalPromotionViewHolder recommendFestivalPromotionViewHolder = new RecommendFestivalPromotionViewHolder(viewStub31.inflate());
                                                                            this.festivalPromotionViewHolder = recommendFestivalPromotionViewHolder;
                                                                            this.inUseViewHolder = recommendFestivalPromotionViewHolder;
                                                                            return;
                                                                        }
                                                                        return;
                                                                    case 2002:
                                                                        ViewStub viewStub32 = (ViewStub) view.findViewById(R.id.recommend_festival_short);
                                                                        if (viewStub32 != null) {
                                                                            RecommendFestivalShortViewHolder recommendFestivalShortViewHolder = new RecommendFestivalShortViewHolder(viewStub32.inflate());
                                                                            this.recommendFestivalShortViewHolder = recommendFestivalShortViewHolder;
                                                                            this.inUseViewHolder = recommendFestivalShortViewHolder;
                                                                            return;
                                                                        }
                                                                        return;
                                                                    case 2003:
                                                                        ViewStub viewStub33 = (ViewStub) view.findViewById(R.id.recommend_home_card_two_channel);
                                                                        if (viewStub33 != null) {
                                                                            RecommendHomeCardTwoChannelViewHolder recommendHomeCardTwoChannelViewHolder = new RecommendHomeCardTwoChannelViewHolder(baseActivity, viewStub33.inflate());
                                                                            this.homeCardTwoChannelViewHolder = recommendHomeCardTwoChannelViewHolder;
                                                                            this.inUseViewHolder = recommendHomeCardTwoChannelViewHolder;
                                                                            return;
                                                                        }
                                                                        return;
                                                                    case 2004:
                                                                        ViewStub viewStub34 = (ViewStub) view.findViewById(R.id.recommend_home_card_one_channel);
                                                                        if (viewStub34 != null) {
                                                                            RecommendHomeCardOneChannelViewHolder recommendHomeCardOneChannelViewHolder = new RecommendHomeCardOneChannelViewHolder(baseActivity, viewStub34.inflate());
                                                                            this.homeCardOneChannelViewHolder = recommendHomeCardOneChannelViewHolder;
                                                                            this.inUseViewHolder = recommendHomeCardOneChannelViewHolder;
                                                                            return;
                                                                        }
                                                                        return;
                                                                    case 2005:
                                                                        ViewStub viewStub35 = (ViewStub) view.findViewById(R.id.recommend_home_live_video_two_channel);
                                                                        if (viewStub35 != null) {
                                                                            RecommendHomeLiveVideoWithTwoChannelViewHolder recommendHomeLiveVideoWithTwoChannelViewHolder = new RecommendHomeLiveVideoWithTwoChannelViewHolder(baseActivity, viewStub35.inflate());
                                                                            this.liveVideoTwoChannelViewHolder = recommendHomeLiveVideoWithTwoChannelViewHolder;
                                                                            this.inUseViewHolder = recommendHomeLiveVideoWithTwoChannelViewHolder;
                                                                            return;
                                                                        }
                                                                        return;
                                                                    case 2006:
                                                                        ViewStub viewStub36 = (ViewStub) view.findViewById(R.id.recommend_home_live_video_one_channel);
                                                                        if (viewStub36 != null) {
                                                                            RecommendHomeLiveVideoWithOneChannelViewHolder recommendHomeLiveVideoWithOneChannelViewHolder = new RecommendHomeLiveVideoWithOneChannelViewHolder(baseActivity, viewStub36.inflate());
                                                                            this.liveVideoOneChannelViewHolder = recommendHomeLiveVideoWithOneChannelViewHolder;
                                                                            this.inUseViewHolder = recommendHomeLiveVideoWithOneChannelViewHolder;
                                                                            return;
                                                                        }
                                                                        return;
                                                                    case 2007:
                                                                        ViewStub viewStub37 = (ViewStub) view.findViewById(R.id.recommend_home_live_video);
                                                                        if (viewStub37 != null) {
                                                                            RecommendHomeLiveViewHolder recommendHomeLiveViewHolder = new RecommendHomeLiveViewHolder(baseActivity, viewStub37.inflate());
                                                                            this.liveVideoViewHolder = recommendHomeLiveViewHolder;
                                                                            this.inUseViewHolder = recommendHomeLiveViewHolder;
                                                                            return;
                                                                        }
                                                                        return;
                                                                    default:
                                                                        switch (i2) {
                                                                            case 10001:
                                                                                ViewStub viewStub38 = (ViewStub) view.findViewById(R.id.recommend_pd_title);
                                                                                if (viewStub38 != null) {
                                                                                    RecommendTitleViewHolder recommendTitleViewHolder = new RecommendTitleViewHolder(baseActivity, viewStub38.inflate());
                                                                                    this.titleViewHolder = recommendTitleViewHolder;
                                                                                    recommendTitleViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                                    this.inUseViewHolder = this.titleViewHolder;
                                                                                    return;
                                                                                }
                                                                                return;
                                                                            case 10002:
                                                                                ViewStub viewStub39 = (ViewStub) view.findViewById(R.id.recommend_product_2);
                                                                                if (viewStub39 != null) {
                                                                                    RecommendPdProduct2ViewHolder recommendPdProduct2ViewHolder = new RecommendPdProduct2ViewHolder(baseActivity, viewStub39.inflate());
                                                                                    this.product2ViewHolder = recommendPdProduct2ViewHolder;
                                                                                    Recommend9ProductViewHolder recommend9ProductViewHolder = recommendPdProduct2ViewHolder.leftViewHolder;
                                                                                    if (recommend9ProductViewHolder != null) {
                                                                                        recommend9ProductViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                                    }
                                                                                    Recommend9ProductViewHolder recommend9ProductViewHolder2 = this.product2ViewHolder.rightViewHolder;
                                                                                    if (recommend9ProductViewHolder2 != null) {
                                                                                        recommend9ProductViewHolder2.setRealExpoHashSet(this.realExpoHashSet);
                                                                                    }
                                                                                    this.inUseViewHolder = this.product2ViewHolder;
                                                                                    return;
                                                                                }
                                                                                return;
                                                                            case 10003:
                                                                                ViewStub viewStub40 = (ViewStub) view.findViewById(R.id.recommend_template_three);
                                                                                if (viewStub40 != null) {
                                                                                    RecommendTemplateThreeViewHolder recommendTemplateThreeViewHolder = new RecommendTemplateThreeViewHolder(baseActivity, viewStub40.inflate());
                                                                                    this.templateThreeViewHolder = recommendTemplateThreeViewHolder;
                                                                                    recommendTemplateThreeViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                                                                                    this.inUseViewHolder = this.templateThreeViewHolder;
                                                                                    return;
                                                                                }
                                                                                return;
                                                                            default:
                                                                                if (!RecommendType.recom_dynamic_types.contains(Integer.valueOf(i2)) || (viewStub = (ViewStub) view.findViewById(R.id.recommend_dynamic)) == null) {
                                                                                    return;
                                                                                }
                                                                                RecommendDynamicTemplateViewHolder recommendDynamicTemplateViewHolder = new RecommendDynamicTemplateViewHolder(baseActivity, viewStub.inflate(), RecommendType.typeDynamicMap.get(Integer.valueOf(i2)));
                                                                                this.dynamicTemplateViewHolder = recommendDynamicTemplateViewHolder;
                                                                                this.inUseViewHolder = recommendDynamicTemplateViewHolder;
                                                                                return;
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                    }
                                }
                            }
                        }
                        ViewStub viewStub41 = (ViewStub) view.findViewById(R.id.recommend_home_tab_temp);
                        if (viewStub41 != null) {
                            RecommendHomeTabTempViewHolder recommendHomeTabTempViewHolder = new RecommendHomeTabTempViewHolder(viewStub41.inflate());
                            this.recommendHomeTabTempViewHolder = recommendHomeTabTempViewHolder;
                            recommendHomeTabTempViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                            this.inUseViewHolder = this.recommendHomeTabTempViewHolder;
                            return;
                        }
                        return;
                    } else {
                        ViewStub viewStub42 = (ViewStub) view.findViewById(R.id.recommend_product_dynamic);
                        if (viewStub42 != null) {
                            RecommendDynamicPdViewHolder recommendDynamicPdViewHolder = new RecommendDynamicPdViewHolder(baseActivity, viewStub42.inflate());
                            this.dynamicPDViewHolder = recommendDynamicPdViewHolder;
                            recommendDynamicPdViewHolder.setRealExpoHashSet(this.realExpoHashSet);
                            this.inUseViewHolder = this.dynamicPDViewHolder;
                            return;
                        }
                        return;
                    }
                }
                ViewStub viewStub43 = (ViewStub) view.findViewById(R.id.recommend_template_nineteen);
                if (viewStub43 != null) {
                    RecommendTemplateNineteenViewHolder recommendTemplateNineteenViewHolder = new RecommendTemplateNineteenViewHolder(baseActivity, viewStub43.inflate());
                    this.templateNineteenViewHolder = recommendTemplateNineteenViewHolder;
                    this.inUseViewHolder = recommendTemplateNineteenViewHolder;
                    return;
                }
                return;
            }
            ViewStub viewStub44 = (ViewStub) view.findViewById(R.id.recommend_auto_play);
            if (viewStub44 != null) {
                RecommendAutoPlayViewHolder recommendAutoPlayViewHolder2 = new RecommendAutoPlayViewHolder(viewStub44.inflate());
                this.autoPlayViewHolder = recommendAutoPlayViewHolder2;
                recommendAutoPlayViewHolder2.setRealExpoHashSet(this.realExpoHashSet);
                this.inUseViewHolder = this.autoPlayViewHolder;
                return;
            }
            return;
        }
        ViewStub viewStub45 = (ViewStub) view.findViewById(R.id.recommend_product_b);
        if (viewStub45 != null) {
            Recommend9ProductViewHolder recommend9ProductViewHolder3 = new Recommend9ProductViewHolder(baseActivity, viewStub45.inflate(), i2);
            this.leftProductViewHolder = recommend9ProductViewHolder3;
            recommend9ProductViewHolder3.setRealExpoHashSet(this.realExpoHashSet);
            this.inUseViewHolder = this.leftProductViewHolder;
        }
    }

    public void bindNewRecommendViewHolder(List list, ArrayList<RecommendItem> arrayList, int i2, ExpoDataStore expoDataStore, int i3, int i4, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore2, ExpoDataStore expoDataStore3, ExpoDataStore expoDataStore4) {
        if (i2 < 0 || arrayList == null || arrayList.size() <= i2) {
            return;
        }
        int i5 = arrayList.get(i2).type;
        if (i5 == 0) {
            if (this.leftProductViewHolder == null || list == null || list.size() == 0) {
                return;
            }
            Object obj = list.get(list.size() - 1);
            if (obj instanceof Integer) {
                this.leftProductViewHolder.refreshCountDownTime(((Integer) obj).intValue());
            }
            if (obj instanceof Boolean) {
                this.leftProductViewHolder.showVideoPlayView(((Boolean) obj).booleanValue());
            }
        } else if (i5 != 1005) {
            if (i5 != 1022) {
                if (i5 == 20000) {
                    if (this.dynamicPDViewHolder != null) {
                        if (list.get(list.size() - 1) instanceof Boolean) {
                            this.dynamicPDViewHolder.changeVideoViewVisible(!((Boolean) r1).booleanValue());
                            return;
                        }
                        return;
                    }
                    return;
                } else if (i5 == 1013) {
                    if (this.templateThirteenViewHolder == null || list == null || list.size() == 0) {
                        return;
                    }
                    Object obj2 = list.get(list.size() - 1);
                    if (obj2 instanceof Boolean) {
                        this.templateThirteenViewHolder.setPlayDurationVisible(((Boolean) obj2).booleanValue());
                        return;
                    }
                    return;
                } else if (i5 == 1014) {
                    if (this.templateFourteenViewHolder == null || list == null || list.size() == 0) {
                        return;
                    }
                    Object obj3 = list.get(list.size() - 1);
                    if (obj3 instanceof Boolean) {
                        this.templateFourteenViewHolder.setPlayDurationVisible(((Boolean) obj3).booleanValue());
                        return;
                    }
                    return;
                } else if (i5 == 1018) {
                    if (this.templateEighteenViewHolder == null || list == null || list.size() == 0) {
                        return;
                    }
                    Object obj4 = list.get(list.size() - 1);
                    if (obj4 instanceof Boolean) {
                        this.templateEighteenViewHolder.showVideoPlayView(((Boolean) obj4).booleanValue());
                        return;
                    }
                    return;
                } else if (i5 != 1019) {
                    if (i5 != 2005) {
                        if (i5 != 2006 || this.liveVideoOneChannelViewHolder == null || list == null || list.size() == 0) {
                            return;
                        }
                        Object obj5 = list.get(list.size() - 1);
                        if (obj5 instanceof Boolean) {
                            this.liveVideoOneChannelViewHolder.setPlayUiVisible(((Boolean) obj5).booleanValue());
                            return;
                        }
                        return;
                    } else if (this.liveVideoTwoChannelViewHolder == null || list == null || list.size() == 0) {
                        return;
                    } else {
                        Object obj6 = list.get(list.size() - 1);
                        if (obj6 instanceof Boolean) {
                            this.liveVideoTwoChannelViewHolder.setPlayUiVisible(((Boolean) obj6).booleanValue());
                            return;
                        }
                        return;
                    }
                }
            }
            if (this.templateNineteenViewHolder == null || list == null || list.size() == 0) {
                return;
            }
            Object obj7 = list.get(list.size() - 1);
            if (obj7 instanceof Boolean) {
                this.templateNineteenViewHolder.setPlayDurationVisible(((Boolean) obj7).booleanValue(), i5);
            }
        } else if (this.templateFiveViewHolder == null || list == null || list.size() == 0) {
        } else {
            Object obj8 = list.get(list.size() - 1);
            if (obj8 instanceof Boolean) {
                this.templateFiveViewHolder.setPlayUiVisible(((Boolean) obj8).booleanValue());
            }
        }
    }
}
