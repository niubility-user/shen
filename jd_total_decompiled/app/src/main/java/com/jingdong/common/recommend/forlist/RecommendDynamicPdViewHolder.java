package com.jingdong.common.recommend.forlist;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.cardview.widget.CardView;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendExecCallback;
import com.jingdong.common.recommend.RecommendFeedBackManger;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.dynmic.RecommendDynamicContainer;
import com.jingdong.common.recommend.dynmic.RecommendDynamicFunction;
import com.jingdong.common.recommend.dynmic.RecommendDynamicNewContainer;
import com.jingdong.common.recommend.entity.FeedBackReason;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;
import com.jingdong.common.recommend.ui.video.RecommendVideoWidget;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendDynamicPdViewHolder extends BaseRecommendAdViewHolder {
    private BaseActivity activity;
    private View deleteView;
    private RecommendDynamicContainer dynamicContainer;
    private RecommendDynamicNewContainer dynamicNewContainer;
    private ExpoDataStore expoData;
    private int mFrom;
    private RecommendItem mRecommendItem;
    private ViewGroup parentLayout;
    private RecommendDynamicFunction recommendDynamicFunction;

    public RecommendDynamicPdViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.mFrom = -1;
        this.activity = baseActivity;
        this.deleteView = view.findViewById(R.id.recommend_delete);
        this.parentLayout = (ViewGroup) view.findViewById(R.id.dynamic_container);
        if (HomeRecommendContentLayout.isUseNewDynApi) {
            RecommendDynamicNewContainer recommendDynamicNewContainer = new RecommendDynamicNewContainer(view.getContext());
            this.dynamicNewContainer = recommendDynamicNewContainer;
            recommendDynamicNewContainer.setCustomFactory(getCustomFunctionFactory());
            this.dynamicNewContainer.createDynamicView("recommendItem");
            this.dynamicNewContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            this.parentLayout.addView(this.dynamicNewContainer, 0);
        } else {
            RecommendDynamicContainer recommendDynamicContainer = new RecommendDynamicContainer(view.getContext());
            this.dynamicContainer = recommendDynamicContainer;
            recommendDynamicContainer.init();
            this.dynamicContainer.setJustLoadFromNative(true);
            this.dynamicContainer.setDynamicZip(false);
            this.dynamicContainer.setMinimumHeight(com.jd.lib.un.basewidget.widget.simple.e.a.a(120.0f));
            this.dynamicContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            this.parentLayout.addView(this.dynamicContainer, 0);
        }
        inflateRecommendVideo();
        this.recommendVideoWidget.setVisibility(8);
    }

    /* renamed from: b */
    public /* synthetic */ CommFunction c(String str) {
        if (TextUtils.equals(IExceptionHandler.DynamicExceptionData.TYPE_MTA, str)) {
            if (this.recommendDynamicFunction == null) {
                this.recommendDynamicFunction = new RecommendDynamicFunction(this.clickedListener, this.pageFrom);
            }
            this.recommendDynamicFunction.setPositionAndItem(this.mRecommendItem);
            this.recommendDynamicFunction.setExecCallback(new RecommendExecCallback() { // from class: com.jingdong.common.recommend.forlist.RecommendDynamicPdViewHolder.4
                {
                    RecommendDynamicPdViewHolder.this = this;
                }

                @Override // com.jingdong.common.recommend.RecommendExecCallback
                public void exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
                    String optString = jSONObject.optString("fun");
                    if ("setVideoScale".equals(optString)) {
                        RecommendDynamicPdViewHolder.this.changeVideoViewHeight(RecommendUtils.parseStringToFloat(jSONObject.optString("videoScale")));
                    }
                    if ("showedLabelListMtaReturn".equals(optString)) {
                        try {
                            if (!RecommendDynamicPdViewHolder.this.mRecommendItem.product.isMtaValueChanged) {
                                String optString2 = jSONObject.optString("showedLabelList");
                                String str2 = "";
                                if (!TextUtils.isEmpty(optString2)) {
                                    JSONArray jSONArray = new JSONArray(optString2);
                                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                        str2 = str2 + jSONArray.getString(i2) + "#";
                                    }
                                }
                                RecommendUtils.handleLabelValueMtaJson(str2, RecommendDynamicPdViewHolder.this.mRecommendItem.product);
                                RecommendDynamicPdViewHolder.this.mRecommendItem.product.isMtaValueChanged = true;
                            }
                            if (TextUtils.isEmpty(RecommendDynamicPdViewHolder.this.mRecommendItem.product.exposureJsonSourceValue)) {
                                return;
                            }
                            RecommendDynamicPdViewHolder.this.expoData.putExpoJsonDada(RecommendDynamicPdViewHolder.this.mRecommendItem.product.exposureJsonSourceValue, "-100", RecommendDynamicPdViewHolder.this.pageFrom, -1);
                        } catch (Exception unused) {
                        }
                    }
                }
            });
            return this.recommendDynamicFunction;
        }
        return null;
    }

    private IFunctionFactory getCustomFunctionFactory() {
        return new IFunctionFactory() { // from class: com.jingdong.common.recommend.forlist.b
            {
                RecommendDynamicPdViewHolder.this = this;
            }

            @Override // com.jd.dynamic.base.IFunctionFactory
            public final CommFunction createCommFunction(String str) {
                return RecommendDynamicPdViewHolder.this.c(str);
            }
        };
    }

    private void handlePopupWindowProblem(RecommendFeedBackManger recommendFeedBackManger) {
        recommendFeedBackManger.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.jingdong.common.recommend.forlist.RecommendDynamicPdViewHolder.7
            {
                RecommendDynamicPdViewHolder.this = this;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                RecommendDynamicPdViewHolder.this.parentLayout.requestDisallowInterceptTouchEvent(false);
            }
        });
        this.parentLayout.requestDisallowInterceptTouchEvent(true);
    }

    public void onRefreshData(int i2) {
        RecommendItem recommendItem = this.mRecommendItem;
        if (recommendItem != null) {
            setVideoPlayInfo(recommendItem.product);
            final RecommendProduct recommendProduct = this.mRecommendItem.product;
            if (recommendProduct != null) {
                setAdData(recommendProduct);
                showDelView(recommendProduct, i2);
                this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendDynamicPdViewHolder.3
                    {
                        RecommendDynamicPdViewHolder.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        RecommendDynamicPdViewHolder.this.dealProductClickEvent(recommendProduct);
                    }
                });
            }
        }
    }

    private void setVideoPlayInfo(RecommendProduct recommendProduct) {
        try {
            setVideoData(TextUtils.isEmpty(recommendProduct.playUrl) ? null : recommendProduct.getVideoData(), recommendProduct.imgUrl);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void showDelView(final RecommendProduct recommendProduct, final int i2) {
        if (recommendProduct.isCanNegFeedback()) {
            RecommendViewUtil.visible(this.deleteView);
            this.deleteView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendDynamicPdViewHolder.5
                {
                    RecommendDynamicPdViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendDynamicPdViewHolder.this.showFeedBackView(recommendProduct, i2, 1);
                }
            });
            this.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendDynamicPdViewHolder.6
                {
                    RecommendDynamicPdViewHolder.this = this;
                }

                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    RecommendDynamicPdViewHolder.this.showFeedBackView(recommendProduct, i2, 2);
                    return true;
                }
            });
        }
    }

    public void showFeedBackView(RecommendProduct recommendProduct, int i2, int i3) {
        if (recommendProduct != null) {
            List<FeedBackReason> list = recommendProduct.feedBackReason;
            if (list != null && !list.isEmpty()) {
                RecommendFeedBackManger recommendFeedBackManger = RecommendFeedBackManger.getInstance();
                handlePopupWindowProblem(recommendFeedBackManger);
                recommendFeedBackManger.showTipPopupWindow(this.activity, this.deleteView, recommendProduct, i2, this.clickedListener, i3);
            }
            RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = this.clickedListener;
            if (onRecommendClickedListener != null) {
                onRecommendClickedListener.onDotMoreMta(1, recommendProduct.feedbackSourceValue);
            }
        }
    }

    public void changeVideoViewVisible(boolean z) {
        this.recommendVideoWidget.setVisibility(z ? 0 : 8);
    }

    public void dealProductClickEvent(RecommendProduct recommendProduct) {
        if (recommendProduct == null || RecommendUtils.isTooFastClick()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(recommendProduct.sourceValue);
            if (!TextUtils.isEmpty(recommendProduct.playUrl)) {
                RecommendVideoWidget recommendVideoWidget = this.recommendVideoWidget;
                if (recommendVideoWidget != null && recommendVideoWidget.isPlaying()) {
                    jSONObject.put("videoplay", 1);
                } else {
                    jSONObject.put("videoplay", 0);
                }
            } else {
                jSONObject.put("videoplay", "-100");
            }
            if (recommendProduct.rootUETJson != null) {
                RecommendMtaUtils.handleTrackingNode(RecommendMtaUtils.getProductClickEventId(this.pageFrom), recommendProduct.jdjsonObject, jSONObject, recommendProduct.rootUETJson, true, true);
            }
            recommendProduct.sourceValue = jSONObject.toString();
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        if (this.clickedListener != null) {
            if (!TextUtils.isEmpty(recommendProduct.client_click_url)) {
                OKLog.d("RecommendAd", "\u52a8\u6001\u5316\u5e7f\u544a=====\u70b9\u51fb");
                this.clickedListener.onRecommendMoneyExpo(recommendProduct.client_click_url);
            }
            if ("1".equals(recommendProduct.isStoreGoods)) {
                RecommendMtaUtils.productClickMta(this.itemView.getContext(), recommendProduct, this.pageFrom, 0);
                this.clickedListener.onRecommendJump(recommendProduct.channelJumpUrl, recommendProduct.isOpenApp);
            } else if (this.pageFrom == 9) {
                this.clickedListener.onProductClick(recommendProduct, this.mRecommendItem);
            } else {
                this.clickedListener.onProductClick(recommendProduct);
            }
        }
    }

    public void setAdData(RecommendProduct recommendProduct) {
        if (recommendProduct != null) {
            if (recommendProduct.showAdDot()) {
                RecommendViewUtil.visible(this.rightDotView);
            } else {
                RecommendViewUtil.gone(this.rightDotView);
            }
            if (recommendProduct.showMonetizedDot()) {
                RecommendViewUtil.visible(this.leftDotView);
            } else {
                RecommendViewUtil.gone(this.leftDotView);
            }
            if (TextUtils.isEmpty(recommendProduct.client_exposal_url) || recommendProduct.hasRealExpo) {
                return;
            }
            realExpo(recommendProduct.client_exposal_url);
            OKLog.d("RecommendAd", "\u52a8\u6001\u5316\u5e7f\u544a=====\u66dd\u5149");
            recommendProduct.hasRealExpo = true;
        }
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void setClickedListener(RecommendUtil.OnRecommendClickedListener onRecommendClickedListener) {
        super.setClickedListener(onRecommendClickedListener);
    }

    public void setData(RecommendItem recommendItem, ExpoDataStore expoDataStore, final int i2, int i3) {
        this.pageFrom = i3;
        this.mRecommendItem = recommendItem;
        this.expoData = expoDataStore;
        View view = this.itemView;
        if (view instanceof CardView) {
            ((CardView) view).setCardBackgroundColor(getColor_FFFFFF());
        }
        this.itemView.setOnClickListener(null);
        this.itemView.setOnLongClickListener(null);
        RecommendViewUtil.gone(this.leftDotView, this.rightDotView, this.deleteView);
        if (recommendItem != null) {
            RecommendDynamicContainer recommendDynamicContainer = this.dynamicContainer;
            if (recommendDynamicContainer != null) {
                recommendDynamicContainer.setCustomFactory(getCustomFunctionFactory());
                this.dynamicContainer.setDynamicJsonData(recommendItem, recommendItem.bizfield, i2, "dynamic_product_item_local.xml");
                this.dynamicContainer.setLoadDataEndListener(new RecommendDynamicContainer.LoadDataEnd() { // from class: com.jingdong.common.recommend.forlist.RecommendDynamicPdViewHolder.1
                    {
                        RecommendDynamicPdViewHolder.this = this;
                    }

                    @Override // com.jingdong.common.recommend.dynmic.RecommendDynamicContainer.LoadDataEnd
                    public void loadEndRefreshView() {
                        RecommendDynamicPdViewHolder.this.onRefreshData(i2);
                    }
                });
                this.dynamicContainer.newLoadDynamicView(null);
                return;
            }
            this.dynamicNewContainer.setLoadDataEndListener(new RecommendDynamicNewContainer.LoadDataEnd() { // from class: com.jingdong.common.recommend.forlist.RecommendDynamicPdViewHolder.2
                {
                    RecommendDynamicPdViewHolder.this = this;
                }

                @Override // com.jingdong.common.recommend.dynmic.RecommendDynamicNewContainer.LoadDataEnd
                public void loadEndRefreshView() {
                    RecommendDynamicPdViewHolder.this.onRefreshData(i2);
                }
            });
            this.dynamicNewContainer.bindData(recommendItem);
        }
    }
}
