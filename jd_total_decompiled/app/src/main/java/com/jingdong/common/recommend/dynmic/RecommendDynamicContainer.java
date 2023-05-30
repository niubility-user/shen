package com.jingdong.common.recommend.dynmic;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.base.DynamicFetcher;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.base.XMLParse;
import com.jd.dynamic.base.interfaces.IFinishAddView;
import com.jd.dynamic.entity.ViewNode;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.dynmic.RecommendDynamicContainer;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendDynamicContainer extends FrameLayout {
    private String bizField;
    private DynamicTemplateEngine engine;
    private FrameLayout frameLayout;
    private boolean isDynamicZip;
    private boolean isJustLoadFromNative;
    private LoadDataEnd loadDataEndListener;
    private View loadingView;
    private String localTemplateFileName;
    private IFunctionFactory mCustomFactory;
    private int position;
    private RecommendItem recommendItem;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.recommend.dynmic.RecommendDynamicContainer$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements DynamicFetcher.Listener {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b() {
            RecommendViewUtil.gone(RecommendDynamicContainer.this.loadingView);
            RecommendViewUtil.visible(RecommendDynamicContainer.this.frameLayout);
            if (RecommendDynamicContainer.this.loadDataEndListener != null) {
                RecommendDynamicContainer.this.loadDataEndListener.loadEndRefreshView();
            }
        }

        @Override // com.jd.dynamic.base.DynamicFetcher.Listener
        public void onEnd(ViewNode viewNode, String str) {
            if (viewNode != null) {
                if (RecommendDynamicContainer.this.getContext() instanceof Activity) {
                    ((Activity) RecommendDynamicContainer.this.getContext()).runOnUiThread(new Runnable() { // from class: com.jingdong.common.recommend.dynmic.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            RecommendDynamicContainer.AnonymousClass1.this.b();
                        }
                    });
                }
                if (RecommendDynamicContainer.this.getContext() instanceof Activity) {
                    RecommendDynamicContainer.this.engine = new DynamicTemplateEngine("com.jingdong.common.recommend", (Activity) RecommendDynamicContainer.this.getContext(), RecommendDynamicContainer.this.frameLayout, RecommendDynamicContainer.this.mCustomFactory);
                    RecommendDynamicContainer.this.engine.setBizField(RecommendDynamicContainer.this.bizField);
                    RecommendDynamicContainer.this.engine.setSystemCode(RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE);
                    JSONObject addJsonObject = RecommendDynamicContainer.this.addJsonObject();
                    RecommendDynamicContainer.this.engine.shouldAutoListenDarkStatus(false);
                    RecommendDynamicContainer.this.engine.newInitTemplate(viewNode, addJsonObject, str, null);
                    return;
                }
                return;
            }
            RecommendDynamicContainer.this.loadLocalTemplateXml();
        }

        @Override // com.jd.dynamic.base.DynamicFetcher.Listener
        public void onError(Exception exc) {
            ExceptionReporter.reportExceptionToBugly(exc);
            RecommendDynamicContainer.this.loadLocalTemplateXml();
        }

        @Override // com.jd.dynamic.base.DynamicFetcher.Listener
        public void onStart() {
        }
    }

    /* loaded from: classes6.dex */
    public interface LoadDataEnd {
        void loadEndRefreshView();
    }

    public RecommendDynamicContainer(@NonNull Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b() {
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(8);
        }
        FrameLayout frameLayout = this.frameLayout;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
        LoadDataEnd loadDataEnd = this.loadDataEndListener;
        if (loadDataEnd != null) {
            loadDataEnd.loadEndRefreshView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject addJsonObject() {
        if (this.recommendItem.localJsonObject != null) {
            return new JSONObject(this.recommendItem.localJsonObject.getInnerMap());
        }
        return new JSONObject();
    }

    private void newDownloadDynamicView() {
        RecommendViewUtil.visible(this.loadingView);
        RecommendViewUtil.gone(this.frameLayout);
        try {
            if (HomeRecommendContentLayout.isUseDynamicZip) {
                DynamicContainer createContainer = DynamicSdk.getDriver().createContainer(new DYContainerConfig(getContext(), RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE, this.bizField, this.mCustomFactory));
                if (createContainer != null) {
                    this.frameLayout.removeAllViews();
                    this.frameLayout.addView(createContainer, new FrameLayout.LayoutParams(-1, -1));
                    createContainer.setData(addJsonObject());
                    if (createContainer.load()) {
                        RecommendViewUtil.gone(this.loadingView);
                        RecommendViewUtil.visible(this.frameLayout);
                        LoadDataEnd loadDataEnd = this.loadDataEndListener;
                        if (loadDataEnd != null) {
                            loadDataEnd.loadEndRefreshView();
                        }
                    } else {
                        ExceptionReporter.reportExceptionToBugly(new Exception("\u63a8\u8350\u4f4d\u52a8\u6001\u5316\u6a21\u677f\u52a0\u8f7d\u5931\u8d25"));
                    }
                }
            } else {
                DynamicFetcher.requestDynamicConfigByBizField(RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE, this.bizField, null, new AnonymousClass1());
            }
        } catch (Exception unused) {
            loadLocalTemplateXml();
        }
    }

    public void init() {
        if (getChildCount() > 0) {
            removeAllViews();
        }
        JDProgressBar jDProgressBar = new JDProgressBar(getContext());
        this.loadingView = jDProgressBar;
        jDProgressBar.setMinimumHeight(com.jd.lib.un.basewidget.widget.simple.e.a.a(120.0f));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(com.jd.lib.un.basewidget.widget.simple.e.a.a(20.0f), com.jd.lib.un.basewidget.widget.simple.e.a.a(20.0f));
        layoutParams.gravity = 17;
        addView(this.loadingView, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.frameLayout = frameLayout;
        frameLayout.setMinimumHeight(com.jd.lib.un.basewidget.widget.simple.e.a.a(120.0f));
        addView(this.frameLayout, layoutParams2);
        RecommendViewUtil.gone(this.frameLayout);
    }

    public void loadLocalTemplateXml() {
        if (StringUtil.isEmpty(this.localTemplateFileName)) {
            return;
        }
        try {
            XMLParse xMLParse = new XMLParse(getContext().getAssets().open(this.localTemplateFileName));
            ViewNode parse = xMLParse.parse();
            parse.unBindMaps = xMLParse.unBindMaps;
            post(new Runnable() { // from class: com.jingdong.common.recommend.dynmic.b
                @Override // java.lang.Runnable
                public final void run() {
                    RecommendDynamicContainer.this.b();
                }
            });
            if (getContext() instanceof Activity) {
                DynamicTemplateEngine dynamicTemplateEngine = new DynamicTemplateEngine("com.jingdong.common.recommend", (Activity) getContext(), this.frameLayout, this.mCustomFactory);
                this.engine = dynamicTemplateEngine;
                dynamicTemplateEngine.shouldAutoListenDarkStatus(false);
                this.engine.setBizField(this.bizField);
                this.engine.setSystemCode("recommend");
                this.engine.newInitTemplate(parse, addJsonObject(), null, null);
            }
        } catch (Exception e2) {
            e2.toString();
        }
    }

    public void newLoadDynamicView(IFinishAddView iFinishAddView) {
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        if (dynamicTemplateEngine != null && TextUtils.equals(this.bizField, dynamicTemplateEngine.getBizField())) {
            this.engine.newRefreshView(addJsonObject());
            RecommendViewUtil.gone(this.loadingView);
            RecommendViewUtil.visible(this.frameLayout);
            LoadDataEnd loadDataEnd = this.loadDataEndListener;
            if (loadDataEnd != null) {
                loadDataEnd.loadEndRefreshView();
                return;
            }
            return;
        }
        DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
        if (dynamicTemplateEngine2 != null) {
            dynamicTemplateEngine2.release();
        }
        if (!TextUtils.isEmpty(this.localTemplateFileName) && this.isJustLoadFromNative && !DynamicSdk.getEngine().hasCachedTempFile("recommend", this.bizField)) {
            loadLocalTemplateXml();
        } else {
            newDownloadDynamicView();
        }
    }

    public void setCustomFactory(IFunctionFactory iFunctionFactory) {
        this.mCustomFactory = iFunctionFactory;
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        if (dynamicTemplateEngine != null) {
            dynamicTemplateEngine.setCustomFactory(iFunctionFactory);
        }
    }

    public void setDynamicJsonData(RecommendItem recommendItem, String str, int i2, String str2) {
        this.recommendItem = recommendItem;
        this.bizField = str;
        this.position = i2;
        this.localTemplateFileName = str2;
    }

    public void setDynamicZip(boolean z) {
        this.isDynamicZip = z;
    }

    public void setJustLoadFromNative(boolean z) {
        this.isJustLoadFromNative = z;
    }

    public void setLoadDataEndListener(LoadDataEnd loadDataEnd) {
        this.loadDataEndListener = loadDataEnd;
    }

    public RecommendDynamicContainer(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendDynamicContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isDynamicZip = true;
        this.isJustLoadFromNative = false;
    }

    @RequiresApi(api = 21)
    public RecommendDynamicContainer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.isDynamicZip = true;
        this.isJustLoadFromNative = false;
    }
}
