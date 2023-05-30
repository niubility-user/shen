package com.jingdong.common.XView2.layer.flexcube;

import android.app.Activity;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.hybrid.downloader.m.b;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IClickEvent;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.owidgets.view.close.CloseButton;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.animation.XViewAnimationManager;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;
import com.jingdong.common.XView2.layer.IBaseLayer;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.layer.flexcube.view.XViewCountDownView;
import com.jingdong.common.XView2.layer.flexcube.view.XViewWebView;
import com.jingdong.common.XView2.strategy.downloader.XViewCache;
import com.jingdong.common.XView2.utils.FileUtil;
import com.jingdong.common.XView2.utils.LayerUtil;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.XViewMtaUtil;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.utils.DPIUtil;
import java.io.File;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FlexCubeLayer extends BaseLayer {
    private ILayerCallBack flexCubeLayerCallBack;
    private LinearLayout mLayout;
    private AtomicBoolean mStartLoading;

    public FlexCubeLayer(Activity activity, Object obj, XView2 xView2) {
        super(activity, obj, xView2);
        this.mStartLoading = new AtomicBoolean(false);
        FlexCubeLayerHelper flexCubeLayerHelper = new FlexCubeLayerHelper(this.mContext, this);
        this.mHelper = flexCubeLayerHelper;
        flexCubeLayerHelper.mBabelScope.register(IClickEvent.class, new IClickEvent() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer.1
            @Override // com.jd.lib.flexcube.iwidget.ui.IClickEvent
            public void onClick(View view, ClickEvent clickEvent) {
                FlexCubeLayer.this.dealClick(view, clickEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void addFlexcubeView(View view, FlexCubeModel flexCubeModel) {
        XView2Container xView2Container;
        boolean z;
        LinearLayout linearLayout;
        XViewConfigEntity.LayersEntity layersEntity;
        if (view != null && (xView2Container = this.mXViewContainer) != null && xView2Container.mContentContainer != null) {
            XViewConfigEntity.LayoutEntity layoutEntity = this.layersEntity.renderData.layout;
            if (layoutEntity != null && "1".equals(layoutEntity.contentFillMode)) {
                FloorConfig floorConfig = flexCubeModel.floorConfig;
                if (floorConfig.w > 0 && floorConfig.f4266h > 0) {
                    z = true;
                    int flexCubeWidth = flexCubeModel.getFlexCubeWidth();
                    int round = Math.round(flexCubeModel.floorConfig.f4266h * flexCubeModel.getMultiple());
                    if (!z) {
                        view.setLayoutParams(new FrameLayout.LayoutParams(flexCubeWidth, round));
                    } else {
                        view.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
                    }
                    linearLayout = this.mLayout;
                    if (linearLayout != null && linearLayout.getParent() != null) {
                        this.mXViewContainer.mContentContainer.removeView(this.mLayout);
                    }
                    this.mXViewContainer.setClipToPadding(false);
                    this.mXViewContainer.setClipChildren(false);
                    this.mXViewContainer.mContentContainer.setClipToPadding(false);
                    this.mXViewContainer.mContentContainer.setClipChildren(false);
                    this.mXViewContainer.mContentContainer.addView(view);
                    this.mCurrentLayer = view;
                    if (this.mXViewContainer.mContentContainer.getLayoutParams() != null) {
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mXViewContainer.mContentContainer.getLayoutParams();
                        if (!z) {
                            flexCubeWidth = -2;
                        }
                        layoutParams.width = flexCubeWidth;
                        if (!z) {
                            round = -2;
                        }
                        layoutParams.height = round;
                        layoutParams.gravity = dealAlign(this.layersEntity, layoutParams);
                        this.mXViewContainer.mContentContainer.setLayoutParams(layoutParams);
                    }
                    layersEntity = this.layersEntity;
                    if (layersEntity == null && !XView2Utils.isConvertBool(layersEntity.userInteractionEnabled)) {
                        this.mXViewContainer.mContentContainer.setControlDispatchTouchEvent(true, false);
                    } else {
                        this.mXViewContainer.mContentContainer.setControlDispatchTouchEvent(false, false);
                    }
                    if (this.mHelper.xViewWebView != null) {
                        this.mLayerState = BaseLayer.LAYERESTATE.READY;
                        realShowSuccess();
                        return;
                    }
                    return;
                }
            }
            z = false;
            int flexCubeWidth2 = flexCubeModel.getFlexCubeWidth();
            int round2 = Math.round(flexCubeModel.floorConfig.f4266h * flexCubeModel.getMultiple());
            if (!z) {
            }
            linearLayout = this.mLayout;
            if (linearLayout != null) {
                this.mXViewContainer.mContentContainer.removeView(this.mLayout);
            }
            this.mXViewContainer.setClipToPadding(false);
            this.mXViewContainer.setClipChildren(false);
            this.mXViewContainer.mContentContainer.setClipToPadding(false);
            this.mXViewContainer.mContentContainer.setClipChildren(false);
            this.mXViewContainer.mContentContainer.addView(view);
            this.mCurrentLayer = view;
            if (this.mXViewContainer.mContentContainer.getLayoutParams() != null) {
            }
            layersEntity = this.layersEntity;
            if (layersEntity == null) {
            }
            this.mXViewContainer.mContentContainer.setControlDispatchTouchEvent(false, false);
            if (this.mHelper.xViewWebView != null) {
            }
        } else {
            closeFlexCubeLayer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeFlexCubeLayer() {
        if (this.layersEntity != null) {
            XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
            XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
            manager.notifyLayerShowError(layersEntity.layerId, layersEntity.logicKey);
            closeLayer(this.layersEntity.layerId, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealClick(View view, ClickEvent clickEvent) {
        JDJSONObject parseObject;
        XViewConfigEntity.LayersEntity layersEntity;
        if (clickEvent == null) {
            return;
        }
        if (TextUtils.isEmpty(clickEvent.type)) {
            if (!(view instanceof CloseButton) || (layersEntity = this.layersEntity) == null) {
                return;
            }
            closeLayer(layersEntity.layerId, 3);
            try {
                JDJSONObject parseObject2 = JDJSON.parseObject(clickEvent.customParams);
                if (parseObject2 == null) {
                    parseObject2 = new JDJSONObject();
                    parseObject2.put("controlType", (Object) "1");
                }
                XViewMtaUtil.controlClickMta(this.mContext, this.layersEntity, this.mTargetsEntity, parseObject2);
                return;
            } catch (Exception unused) {
                return;
            }
        }
        try {
            if ("xview_openapp".equals(clickEvent.type)) {
                JDJSONObject parseObject3 = JDJSON.parseObject(clickEvent.customParams);
                if (parseObject3 == null) {
                    return;
                }
                String optString = parseObject3.optString("name");
                String openapp = this.mHelper.getOpenapp(this.mBaseLayerDelegate.getStateMap(), parseObject3);
                handleClickCallBack(openapp, 3, optString);
                if (TextUtils.isEmpty(openapp)) {
                    return;
                }
                XView2Utils.clickJump(this.mContext, openapp);
                XViewMtaUtil.controlClickMta(this.mContext, this.layersEntity, this.mTargetsEntity, parseObject3);
                if (XView2Utils.isConvertBool(this.layersEntity.jmpCls)) {
                    closeLayer(this.layersEntity.layerId, 1);
                }
            } else if ("xview_close".equals(clickEvent.type)) {
                JDJSONObject parseObject4 = JDJSON.parseObject(clickEvent.customParams);
                if (parseObject4 == null) {
                    return;
                }
                String optString2 = parseObject4.optString("name");
                String openapp2 = this.mHelper.getOpenapp(this.mBaseLayerDelegate.getStateMap(), parseObject4);
                handleClickCallBack(openapp2, 1, optString2);
                if (!TextUtils.isEmpty(openapp2)) {
                    XView2Utils.clickJump(this.mContext, openapp2);
                }
                XViewConfigEntity.LayersEntity layersEntity2 = this.layersEntity;
                if (layersEntity2 != null) {
                    closeLayer(layersEntity2.layerId, 2);
                    XViewMtaUtil.controlClickMta(this.mContext, this.layersEntity, this.mTargetsEntity, parseObject4);
                }
            } else if (!TextUtils.equals(clickEvent.type, XViewCountDownView.EVENT_FINISH_TYPE) || (parseObject = JDJSON.parseObject(clickEvent.customParams)) == null) {
            } else {
                String openapp3 = this.mHelper.getOpenapp(this.mBaseLayerDelegate.getStateMap(), parseObject);
                String optString3 = parseObject.optString("type");
                if (XViewCountDownView.EVENT_COUNTDOWN_REFRESH_TYPE.equals(optString3)) {
                    return;
                }
                if (XViewCountDownView.EVENT_COUNTDOWN_CLOSE_TYPE.equals(optString3)) {
                    XViewConfigEntity.LayersEntity layersEntity3 = this.layersEntity;
                    if (layersEntity3 != null) {
                        closeLayer(layersEntity3.layerId, 16);
                        XViewMtaUtil.controlClickMta(this.mContext, this.layersEntity, this.mTargetsEntity, parseObject);
                    }
                } else if (!XViewCountDownView.EVENT_COUNTDOWN_OPENAPP_TYPE.equals(optString3) || TextUtils.isEmpty(openapp3)) {
                } else {
                    XView2Utils.clickJump(this.mContext, openapp3);
                }
            }
        } catch (Throwable unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doException() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            closeFlexCubeLayer();
        } else {
            this.mHandler.post(new Runnable() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer.3
                @Override // java.lang.Runnable
                public void run() {
                    FlexCubeLayer.this.closeFlexCubeLayer();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleJsonData(String str) {
        try {
            final FlexCubeModel flexCubeModel = (FlexCubeModel) JDJSON.parseObject(str, FlexCubeModel.class);
            if (flexCubeModel != null) {
                flexCubeModel.materialGroupList = this.layersEntity.renderData.materialGroupList;
                if (flexCubeModel.handleData() && flexCubeModel.widgetList != null) {
                    this.mHelper.dealFlexCubeModel(flexCubeModel, this.layersEntity);
                    int dealSize = this.mHelper.dealSize(flexCubeModel, this.layersEntity);
                    if (dealSize <= 0) {
                        dealSize = DPIUtil.getAppWidth((Activity) this.mContext);
                    }
                    flexCubeModel.setFlexCubeWidth(dealSize);
                    this.mFlexCubeModel = flexCubeModel;
                    this.mHandler.post(new Runnable() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer.4
                        @Override // java.lang.Runnable
                        public void run() {
                            FlexCubeLayer flexCubeLayer = FlexCubeLayer.this;
                            flexCubeLayer.mFlexCubeView = ((BaseLayer) flexCubeLayer).mHelper.getFlexcubeView(flexCubeModel);
                            FlexCubeLayer flexCubeLayer2 = FlexCubeLayer.this;
                            if (flexCubeLayer2.mFlexCubeView == null) {
                                flexCubeLayer2.reportDslDataException("mFlexCubeView == null");
                            }
                            FlexCubeLayer flexCubeLayer3 = FlexCubeLayer.this;
                            flexCubeLayer3.addFlexcubeView(flexCubeLayer3.mFlexCubeView, flexCubeModel);
                        }
                    });
                    return;
                }
                doException();
                reportDslDataException("!floorModel.handleData() || null == floorModel.widgetList");
                return;
            }
            doException();
            reportDslDataException("floorModel == null");
        } catch (Exception e2) {
            doException();
            reportDslDataException(e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realShowSuccess() {
        ViewGroup animateTargetView;
        if (isRenderSuccess() || !this.mCouldPop.get() || this.layersEntity == null || this.mXViewContainer == null) {
            return;
        }
        if (isPopUpAnimType() && (animateTargetView = LayerUtil.getAnimateTargetView(getLayersEntity(), getContainer())) != null) {
            animateTargetView.setAlpha(0.0f);
        }
        this.mXViewContainer.setVisibility(0);
        XView2Utils.setXViewGrayMode(this.mXViewContainer);
        this.mLayerState = BaseLayer.LAYERESTATE.DISPLAYED;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        onLayerDisplayed(layersEntity.layerId, layersEntity.logicKey);
        Iterator<IBaseLayer> it = this.mHelper.list.iterator();
        while (it.hasNext()) {
            XViewConfigEntity.LayersEntity layersEntity2 = this.layersEntity;
            it.next().onLayerDisplayed(layersEntity2.layerId, layersEntity2.logicKey);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportDslDataException(String str) {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        XView2Utils.reportXView2ErrorWithSwitch("dslDataErr", "NXViewException", (layersEntity == null || (renderDataEntity = layersEntity.renderData) == null) ? "" : renderDataEntity.url, str, layersEntity != null ? layersEntity.layerId : "", layersEntity != null ? layersEntity.name : "", "");
    }

    public void changeSize(int i2, int i3) {
        XViewConfigEntity.LayersEntity layersEntity;
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        XViewConfigEntity.LayoutEntity layoutEntity;
        XView2Container xView2Container = this.mXViewContainer;
        if (xView2Container == null || xView2Container.mContentContainer == null || this.mHelper.xViewWebView == null || this.mFlexCubeModel == null || (layersEntity = this.layersEntity) == null || (renderDataEntity = layersEntity.renderData) == null || (layoutEntity = renderDataEntity.layout) == null || !"1".equals(layoutEntity.contentFillMode) || !this.mHelper.changeSize(this.mCurrentLayer, this.mFlexCubeModel, i2, i3) || this.mXViewContainer.mContentContainer.getLayoutParams() == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mXViewContainer.mContentContainer.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        layoutParams.gravity = dealAlign(this.layersEntity, layoutParams);
        this.mXViewContainer.mContentContainer.setLayoutParams(layoutParams);
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void closeLayer(String str, int i2) {
        Iterator<IBaseLayer> it = this.mHelper.list.iterator();
        while (it.hasNext()) {
            it.next().closeLayer(str, i2);
        }
        super.closeLayer(str, i2);
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer
    public View createLayer(XView2Container xView2Container, Object obj, ILayerCallBack iLayerCallBack) {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        FlexCubeLayerHelper flexCubeLayerHelper;
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || (renderDataEntity = layersEntity.renderData) == null || TextUtils.isEmpty(renderDataEntity.url)) {
            return null;
        }
        this.mCallBack = iLayerCallBack;
        BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
        if (baseLayerDelegate != null && (flexCubeLayerHelper = this.mHelper) != null) {
            this.layersEntity.renderData.materialGroupList = flexCubeLayerHelper.getMaterialGroupList(baseLayerDelegate.getStateMap());
        }
        if (this.mXViewAnimationManager == null) {
            this.mXViewAnimationManager = new XViewAnimationManager();
        }
        String finalLayerId = XView2Utils.getFinalLayerId(this.layersEntity.layerId);
        b files = XViewCache.getInstance().getFiles(finalLayerId + this.layersEntity.renderData.url);
        if (files != null && !TextUtils.isEmpty(files.getFilePath()) && SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PREDOWNLOAD, false)) {
            String stringFromFile = FileUtil.getStringFromFile(this.mContext, new File(files.getFilePath()));
            if (TextUtils.isEmpty(stringFromFile)) {
                getHttpUrlData();
            } else {
                try {
                    handleJsonData(new JSONObject(stringFromFile).optString("tpl"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    getHttpUrlData();
                }
            }
        } else {
            getHttpUrlData();
        }
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        this.mLayout = linearLayout;
        return linearLayout;
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void destroyLayer() {
        Iterator<IBaseLayer> it = this.mHelper.list.iterator();
        while (it.hasNext()) {
            it.next().destroyLayer();
        }
        super.destroyLayer();
    }

    public XViewWebView getCurrentFlexWebView() {
        XViewWebView xViewWebView;
        FlexCubeLayerHelper flexCubeLayerHelper = this.mHelper;
        if (flexCubeLayerHelper == null || (xViewWebView = flexCubeLayerHelper.xViewWebView) == null) {
            return null;
        }
        return xViewWebView;
    }

    public void getData(final String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFinalUrl(str);
        httpSetting.setUrl(str);
        httpSetting.setPost(false);
        httpSetting.setEffect(0);
        httpSetting.setLocalFileCache(true);
        httpSetting.setLocalFileCacheTime(2592000000L);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject == null) {
                    FlexCubeLayer.this.doException();
                    String str2 = str;
                    XViewConfigEntity.LayersEntity layersEntity = FlexCubeLayer.this.layersEntity;
                    XView2Utils.reportXView2ErrorWithSwitch("dslDataErr", "NXViewException", str2, "rootJson == null", layersEntity != null ? layersEntity.layerId : "", layersEntity != null ? layersEntity.name : "", "");
                    return;
                }
                FlexCubeLayer.this.handleJsonData(fastJsonObject.optString("tpl"));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                FlexCubeLayer.this.doException();
                String str2 = str;
                String message = httpError.getMessage();
                XViewConfigEntity.LayersEntity layersEntity = FlexCubeLayer.this.layersEntity;
                XView2Utils.reportXView2ErrorWithSwitch("dslFail", "NXViewException", str2, message, layersEntity != null ? layersEntity.layerId : "", layersEntity != null ? layersEntity.name : "", "");
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u83b7\u53d6json\u6587\u4ef6\u63a5\u53e3 onError");
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public ILayerCallBack getFlexCubeLayerCallBack() {
        if (this.flexCubeLayerCallBack == null) {
            this.flexCubeLayerCallBack = new ILayerCallBack() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer.5
                boolean onLoading = false;

                @Override // com.jingdong.common.XView2.layer.ILayerCallBack
                public void onCloseButtonClicked() {
                }

                @Override // com.jingdong.common.XView2.layer.ILayerCallBack
                public void onError(String str) {
                    FlexCubeLayer flexCubeLayer = FlexCubeLayer.this;
                    flexCubeLayer.mLayerState = BaseLayer.LAYERESTATE.ERROR;
                    flexCubeLayer.closeFlexCubeLayer();
                }

                @Override // com.jingdong.common.XView2.layer.ILayerCallBack
                public void onLayerClosed(String str, int i2) {
                    FlexCubeLayer flexCubeLayer = FlexCubeLayer.this;
                    XViewConfigEntity.LayersEntity layersEntity = flexCubeLayer.layersEntity;
                    if (layersEntity != null) {
                        flexCubeLayer.closeLayer(layersEntity.layerId, i2);
                    }
                }

                @Override // com.jingdong.common.XView2.layer.ILayerCallBack
                public void onLayerDisplayed(String str) {
                }

                @Override // com.jingdong.common.XView2.layer.ILayerCallBack
                public void onLayerLoading(String str) {
                    FlexCubeLayer flexCubeLayer;
                    XViewConfigEntity.LayersEntity layersEntity;
                    if (((BaseLayer) FlexCubeLayer.this).mHelper.xViewWebView != null || this.onLoading || (layersEntity = (flexCubeLayer = FlexCubeLayer.this).layersEntity) == null) {
                        return;
                    }
                    this.onLoading = true;
                    flexCubeLayer.onLoadingLayer(str, layersEntity.logicKey);
                }

                @Override // com.jingdong.common.XView2.layer.ILayerCallBack
                public void onLayerReady(String str) {
                    FlexCubeLayer flexCubeLayer = FlexCubeLayer.this;
                    flexCubeLayer.mLayerState = BaseLayer.LAYERESTATE.READY;
                    flexCubeLayer.realShowSuccess();
                }
            };
        }
        return this.flexCubeLayerCallBack;
    }

    public FlexCubeLayerHelper getHelper() {
        return this.mHelper;
    }

    public void getHttpUrlData() {
        getData(this.layersEntity.renderData.url);
        this.mXViewContainer.mContentContainer.setControlDispatchTouchEvent(true, false);
    }

    public JDWebView getJdWebView() {
        XView xView;
        XViewWebView xViewWebView = this.mHelper.xViewWebView;
        if (xViewWebView == null || (xView = xViewWebView.xView) == null) {
            return null;
        }
        return xView.getJdWebView();
    }

    public XViewCallBack getXViewCallBack() {
        return new XViewCallBack() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer.6
            @Override // com.jingdong.common.XView.XViewCallBack
            public void onCloseButtonClicked() {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onError(int i2) {
                FlexCubeLayer flexCubeLayer = FlexCubeLayer.this;
                flexCubeLayer.mLayerState = BaseLayer.LAYERESTATE.ERROR;
                if (flexCubeLayer.layersEntity != null) {
                    XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
                    XViewConfigEntity.LayersEntity layersEntity = FlexCubeLayer.this.layersEntity;
                    manager.notifyLayerShowError(layersEntity.layerId, layersEntity.logicKey);
                    FlexCubeLayer flexCubeLayer2 = FlexCubeLayer.this;
                    flexCubeLayer2.closeLayer(flexCubeLayer2.layersEntity.layerId, 15);
                    String url = (((BaseLayer) FlexCubeLayer.this).mHelper == null || ((BaseLayer) FlexCubeLayer.this).mHelper.xViewWebView == null) ? "" : ((BaseLayer) FlexCubeLayer.this).mHelper.xViewWebView.getUrl();
                    String valueOf = String.valueOf(i2);
                    XViewConfigEntity.LayersEntity layersEntity2 = FlexCubeLayer.this.layersEntity;
                    XView2Utils.reportXView2Error("webviewFail", "NXViewException", url, valueOf, layersEntity2.layerId, layersEntity2.name, "");
                }
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onStart() {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewDisplayed() {
                FlexCubeLayer flexCubeLayer = FlexCubeLayer.this;
                flexCubeLayer.mLayerState = BaseLayer.LAYERESTATE.DISPLAYED;
                if (flexCubeLayer.isCanLayerRealPop()) {
                    FlexCubeLayer.this.realShowSuccess();
                }
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewLoadingUrl(String str) {
                FlexCubeLayer.this.onLayerLoadingUrl();
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewReady() {
                FlexCubeLayer flexCubeLayer = FlexCubeLayer.this;
                flexCubeLayer.mLayerState = BaseLayer.LAYERESTATE.READY;
                flexCubeLayer.onLayerReady();
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewRequest(XViewRequest xViewRequest) {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewVisibleChanged(boolean z) {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXVivewClosed() {
                FlexCubeLayer flexCubeLayer = FlexCubeLayer.this;
                XViewConfigEntity.LayersEntity layersEntity = flexCubeLayer.layersEntity;
                if (layersEntity != null) {
                    flexCubeLayer.closeLayer(layersEntity.layerId, 10);
                }
            }
        };
    }

    public void onLayerLoadingUrl() {
        if (this.layersEntity == null || this.mStartLoading.get()) {
            return;
        }
        this.mStartLoading.set(true);
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        onLoadingLayer(layersEntity.layerId, layersEntity.logicKey);
    }

    public void onLayerReady() {
        XViewConfigEntity.LayersEntity layersEntity;
        XViewWebView xViewWebView;
        XView xView;
        if (XView2Utils.canWebViewLayerPreLoaded(this.layersEntity)) {
            if (!this.mIsPopStatusMode.get()) {
                return;
            }
            FlexCubeLayerHelper flexCubeLayerHelper = this.mHelper;
            if (flexCubeLayerHelper != null && (xViewWebView = flexCubeLayerHelper.xViewWebView) != null && (xView = xViewWebView.xView) != null) {
                xView.displayXView();
            }
        }
        ILayerCallBack iLayerCallBack = this.mCallBack;
        if (iLayerCallBack == null || (layersEntity = this.layersEntity) == null) {
            return;
        }
        iLayerCallBack.onLayerReady(layersEntity.layerId);
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void onPause() {
        super.onPause();
        Iterator<IBaseLayer> it = this.mHelper.list.iterator();
        while (it.hasNext()) {
            it.next().onPause();
        }
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void onResume() {
        super.onResume();
        Iterator<IBaseLayer> it = this.mHelper.list.iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer
    public void setCouldPop(boolean z) {
        super.setCouldPop(z);
        if ((z && this.mLayerState == BaseLayer.LAYERESTATE.READY) || this.mLayerState == BaseLayer.LAYERESTATE.DISPLAYED) {
            realShowSuccess();
        }
    }
}
