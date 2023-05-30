package com.jingdong.common.recommend.dynmic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IViewBindListener;
import com.jd.dynamic.apis.IViewCreateListener;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.lib.error.DynamicException;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendDynamicNewContainer extends FrameLayout {
    private DynamicContainer container;
    private LoadDataEnd loadDataEndListener;
    private IFunctionFactory mCustomFactory;

    /* loaded from: classes6.dex */
    public interface LoadDataEnd {
        void loadEndRefreshView();
    }

    public RecommendDynamicNewContainer(@NonNull Context context) {
        this(context, null);
    }

    private JSONObject addJsonObject(RecommendItem recommendItem) {
        JSONObject jSONObject = recommendItem.jsonObject;
        if (jSONObject != null) {
            return jSONObject;
        }
        if (recommendItem.localJsonObject != null) {
            return new JSONObject(recommendItem.localJsonObject.getInnerMap());
        }
        return new JSONObject();
    }

    public void bindData(RecommendItem recommendItem) {
        DynamicContainer dynamicContainer = this.container;
        if (dynamicContainer != null) {
            dynamicContainer.setData(addJsonObject(recommendItem));
            this.container.bindViewAsync(new IViewBindListener() { // from class: com.jingdong.common.recommend.dynmic.RecommendDynamicNewContainer.1
                @Override // com.jd.dynamic.apis.IViewBindListener
                public void onDynamicViewBind() {
                    if (RecommendDynamicNewContainer.this.loadDataEndListener != null) {
                        RecommendDynamicNewContainer.this.loadDataEndListener.loadEndRefreshView();
                    }
                }

                @Override // com.jd.dynamic.apis.IViewBindListener
                public void onError(@NotNull DynamicException dynamicException) {
                }
            });
        }
    }

    public void createDynamicView(String str) {
        try {
            DYContainerConfig dYContainerConfig = new DYContainerConfig(getContext(), RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE, str, this.mCustomFactory);
            dYContainerConfig.setPackageName("com.jingdong.common.recommend");
            dYContainerConfig.setSkeletonSetting(true, 120);
            this.container = DynamicSdk.getDriver().createContainer(dYContainerConfig);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            addView(this.container, layoutParams);
            this.container.createViewAsync(new IViewCreateListener() { // from class: com.jingdong.common.recommend.dynmic.RecommendDynamicNewContainer.2
                @Override // com.jd.dynamic.apis.IViewCreateListener
                public void onDynamicViewCreate() {
                }

                @Override // com.jd.dynamic.apis.IViewCreateListener
                public void onError(@NotNull DynamicException dynamicException) {
                    ExceptionReporter.reportExceptionToBugly(new Exception("\u63a8\u8350\u4f4d\u52a8\u6001\u5316\u6a21\u677f\u52a0\u8f7d\u5931\u8d25"));
                }
            });
        } catch (Exception unused) {
        }
    }

    public void setCustomFactory(IFunctionFactory iFunctionFactory) {
        this.mCustomFactory = iFunctionFactory;
    }

    public void setLoadDataEndListener(LoadDataEnd loadDataEnd) {
        this.loadDataEndListener = loadDataEnd;
    }

    public RecommendDynamicNewContainer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendDynamicNewContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @RequiresApi(api = 21)
    public RecommendDynamicNewContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
