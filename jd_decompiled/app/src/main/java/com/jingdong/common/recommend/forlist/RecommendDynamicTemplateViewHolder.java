package com.jingdong.common.recommend.forlist;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendExecCallback;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.dynmic.RecommendDynamicContainer;
import com.jingdong.common.recommend.dynmic.RecommendDynamicFunction;
import com.jingdong.common.recommend.dynmic.RecommendDynamicNewContainer;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendTemplate;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;
import com.jingdong.common.recommend.ui.video.RecommendVideoWidget;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendDynamicTemplateViewHolder extends BaseRecommendMaterialViewHolder {
    protected RecommendDynamicContainer dynamicContainer;
    protected RecommendDynamicNewContainer dynamicNewContainer;
    private ExpoDataStore expoData;
    private RecommendVideoWidget liveVideoLayout;
    protected RecommendItem mRecommendItem;
    private ViewGroup parentLayout;
    private int position;
    private RecommendDynamicFunction recommendDynamicFunction;

    public RecommendDynamicTemplateViewHolder(BaseActivity baseActivity, View view, String str) {
        super(baseActivity, view);
        this.parentLayout = (ViewGroup) view.findViewById(R.id.dynamic_container);
        if (HomeRecommendContentLayout.isUseNewDynApi) {
            RecommendDynamicNewContainer recommendDynamicNewContainer = new RecommendDynamicNewContainer(view.getContext());
            this.dynamicNewContainer = recommendDynamicNewContainer;
            recommendDynamicNewContainer.setCustomFactory(getCustomFunctionFactory());
            this.dynamicNewContainer.createDynamicView(str);
            this.dynamicNewContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.parentLayout.addView(this.dynamicNewContainer, 0);
            return;
        }
        RecommendDynamicContainer recommendDynamicContainer = new RecommendDynamicContainer(view.getContext());
        this.dynamicContainer = recommendDynamicContainer;
        recommendDynamicContainer.init();
        this.dynamicContainer.setMinimumHeight(com.jd.lib.un.basewidget.widget.simple.e.a.a(120.0f));
        this.dynamicContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.parentLayout.addView(this.dynamicContainer, 0);
    }

    /* renamed from: c */
    public /* synthetic */ CommFunction d(String str) {
        if (TextUtils.equals(IExceptionHandler.DynamicExceptionData.TYPE_MTA, str)) {
            if (this.recommendDynamicFunction == null) {
                this.recommendDynamicFunction = new RecommendDynamicFunction(this.clickedListener, this.pageFrom);
            }
            this.recommendDynamicFunction.setPositionAndItem(this.mRecommendItem);
            this.recommendDynamicFunction.setExecCallback(new RecommendExecCallback() { // from class: com.jingdong.common.recommend.forlist.RecommendDynamicTemplateViewHolder.1
                {
                    RecommendDynamicTemplateViewHolder.this = this;
                }

                @Override // com.jingdong.common.recommend.RecommendExecCallback
                public void exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
                    if (!"setVideoScale".equals(jSONObject.optString("fun")) || RecommendDynamicTemplateViewHolder.this.liveVideoLayout == null) {
                        return;
                    }
                    RecommendViewUtil.changeViewHeight(RecommendUtils.parseStringToFloat(jSONObject.optString("videoScale")), RecommendDynamicTemplateViewHolder.this.liveVideoLayout);
                }
            });
            return this.recommendDynamicFunction;
        }
        return null;
    }

    /* renamed from: e */
    public /* synthetic */ void f(RecommendTemplate recommendTemplate, View view) {
        RecommendVideoWidget recommendVideoWidget;
        if (!this.mRecommendItem.isHomeData) {
            RecommendMtaUtils.aggregationClickMtaRealize(this.itemView.getContext(), recommendTemplate.sourceValue, this.pageFrom, recommendTemplate.extension_id);
        }
        onAdClick(recommendTemplate.client_click_url);
        if (TextUtils.isEmpty(recommendTemplate.jump)) {
            return;
        }
        try {
            if (this.mRecommendItem.recommendLiveBean != null && (recommendVideoWidget = this.liveVideoLayout) != null) {
                recommendVideoWidget.jumpToLive(Uri.parse(recommendTemplate.jump));
            } else {
                this.clickedListener.onRecommendJump(recommendTemplate.jump, recommendTemplate.isOpenApp);
            }
        } catch (Exception unused) {
        }
    }

    public void onRefreshData() {
        final RecommendTemplate recommendTemplate;
        RecommendItem recommendItem = this.mRecommendItem;
        if (recommendItem == null || (recommendTemplate = recommendItem.recommendTemplate) == null) {
            return;
        }
        if (recommendItem.isHomeData) {
            if (!TextUtils.isEmpty(recommendTemplate.exposureJsonSourceValue)) {
                JDMtaUtils.sendExposureDataWithExt(this.itemView.getContext(), RecommendMtaUtils.Home_Promotion_Product_Expo, "", RecommendMtaUtils.Home_PageId, "JDHomeFragment", "", recommendTemplate.exposureJsonSourceValue, "", "", "", null);
            }
        } else {
            putExpoData(this.expoData, recommendTemplate);
        }
        setAdData(recommendTemplate);
        setMaterialData(recommendTemplate, this.position);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.c
            {
                RecommendDynamicTemplateViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecommendDynamicTemplateViewHolder.this.f(recommendTemplate, view);
            }
        });
    }

    protected IFunctionFactory getCustomFunctionFactory() {
        return new IFunctionFactory() { // from class: com.jingdong.common.recommend.forlist.f
            {
                RecommendDynamicTemplateViewHolder.this = this;
            }

            @Override // com.jd.dynamic.base.IFunctionFactory
            public final CommFunction createCommFunction(String str) {
                return RecommendDynamicTemplateViewHolder.this.d(str);
            }
        };
    }

    protected void putExpoData(ExpoDataStore expoDataStore, RecommendTemplate recommendTemplate) {
        if (expoDataStore != null) {
            if (!TextUtils.isEmpty(recommendTemplate.exposureJsonSourceValue)) {
                expoDataStore.putExpoJsonDada(recommendTemplate.exposureJsonSourceValue, "-100", this.pageFrom, -1);
            } else if (TextUtils.isEmpty(recommendTemplate.exposureSourceValue)) {
            } else {
                expoDataStore.putExpoData(recommendTemplate.exposureSourceValue, -1);
            }
        }
    }

    public void setData(RecommendItem recommendItem, ExpoDataStore expoDataStore, int i2, int i3) {
        if (recommendItem != null) {
            this.pageFrom = i3;
            this.mRecommendItem = recommendItem;
            this.position = i2;
            this.expoData = expoDataStore;
            View view = this.itemView;
            if (view instanceof CardView) {
                ((CardView) view).setCardBackgroundColor(getColor_FFFFFF());
            }
            if (this.mRecommendItem.recommendLiveBean != null) {
                if (this.liveVideoLayout == null) {
                    RecommendVideoWidget recommendVideoWidget = new RecommendVideoWidget(this.itemView.getContext());
                    this.liveVideoLayout = recommendVideoWidget;
                    recommendVideoWidget.setId(R.id.recommend_widget_new);
                    this.liveVideoLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                    ((ViewGroup) this.itemView).addView(this.liveVideoLayout, 0);
                }
                RecommendViewUtil.visible(this.liveVideoLayout);
                this.liveVideoLayout.setLiVeData(this.mRecommendItem.recommendLiveBean);
            } else {
                RecommendVideoWidget recommendVideoWidget2 = this.liveVideoLayout;
                if (recommendVideoWidget2 != null) {
                    RecommendViewUtil.gone(recommendVideoWidget2);
                }
            }
            this.itemView.setOnClickListener(null);
            this.itemView.setOnLongClickListener(null);
            RecommendViewUtil.gone(this.leftDotView, this.rightDotView);
            RecommendDynamicContainer recommendDynamicContainer = this.dynamicContainer;
            if (recommendDynamicContainer != null) {
                recommendDynamicContainer.setCustomFactory(getCustomFunctionFactory());
                this.dynamicContainer.setDynamicJsonData(recommendItem, recommendItem.bizfield, i2, "");
                this.dynamicContainer.setLoadDataEndListener(new RecommendDynamicContainer.LoadDataEnd() { // from class: com.jingdong.common.recommend.forlist.e
                    {
                        RecommendDynamicTemplateViewHolder.this = this;
                    }

                    @Override // com.jingdong.common.recommend.dynmic.RecommendDynamicContainer.LoadDataEnd
                    public final void loadEndRefreshView() {
                        RecommendDynamicTemplateViewHolder.this.onRefreshData();
                    }
                });
                this.dynamicContainer.newLoadDynamicView(null);
            }
            RecommendDynamicNewContainer recommendDynamicNewContainer = this.dynamicNewContainer;
            if (recommendDynamicNewContainer != null) {
                recommendDynamicNewContainer.setLoadDataEndListener(new RecommendDynamicNewContainer.LoadDataEnd() { // from class: com.jingdong.common.recommend.forlist.d
                    {
                        RecommendDynamicTemplateViewHolder.this = this;
                    }

                    @Override // com.jingdong.common.recommend.dynmic.RecommendDynamicNewContainer.LoadDataEnd
                    public final void loadEndRefreshView() {
                        RecommendDynamicTemplateViewHolder.this.onRefreshData();
                    }
                });
                this.dynamicNewContainer.bindData(recommendItem);
            }
        }
    }
}
