package com.jingdong.common.XView2.layer.xview;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewHelper;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.BaseLayerDelegate;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.utils.XView2Utils;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class XViewLayer extends BaseLayer implements IXView {
    private AtomicBoolean mStartLoading;
    private XView xView;

    public XViewLayer(Activity activity, Object obj, XView2 xView2) {
        super(activity, obj, xView2);
        this.mStartLoading = new AtomicBoolean(false);
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void closeLayer(String str, int i2) {
        super.closeLayer(str, i2);
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer
    public View createLayer(XView2Container xView2Container, Object obj, ILayerCallBack iLayerCallBack) {
        if (this.mContext == null || this.layersEntity == null) {
            return null;
        }
        this.mCallBack = iLayerCallBack;
        this.mLayerState = BaseLayer.LAYERESTATE.CREATE;
        final XViewEntity xViewEntity = new XViewEntity();
        xViewEntity.isIntercepted = XView2Utils.isConvertBool(this.layersEntity.userInteractionEnabled);
        if (this.layersEntity.renderData != null) {
            BaseLayerDelegate baseLayerDelegate = this.mBaseLayerDelegate;
            xViewEntity.url = (baseLayerDelegate == null || TextUtils.isEmpty(baseLayerDelegate.mAppendUrlParams)) ? this.layersEntity.renderData.url : this.mBaseLayerDelegate.mAppendUrlParams;
        }
        xViewEntity.needCloseButton = false;
        xViewEntity.needAutoClose = "1".equals(this.layersEntity.jmpCls);
        xViewEntity.needAutoDisplay = !XView2Utils.canWebViewLayerPreLoaded(this.layersEntity);
        Context context = this.mContext;
        XView createXView = XViewHelper.createXView((Activity) context, xView2Container, context.getClass().getSimpleName(), xViewEntity, new XViewCallBack() { // from class: com.jingdong.common.XView2.layer.xview.XViewLayer.1
            @Override // com.jingdong.common.XView.XViewCallBack
            public void onCloseButtonClicked() {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onError(int i2) {
                XViewLayer xViewLayer = XViewLayer.this;
                xViewLayer.mLayerState = BaseLayer.LAYERESTATE.ERROR;
                xViewLayer.onLayerError();
                String str = xViewEntity.url;
                String valueOf = String.valueOf(i2);
                XViewConfigEntity.LayersEntity layersEntity = XViewLayer.this.layersEntity;
                XView2Utils.reportXView2Error("webviewFail", "NXViewException", str, valueOf, layersEntity.layerId, layersEntity.name, "");
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onStart() {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewDisplayed() {
                XViewLayer xViewLayer = XViewLayer.this;
                xViewLayer.mLayerState = BaseLayer.LAYERESTATE.DISPLAYED;
                xViewLayer.onLayerDisplayed();
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewLoadingUrl(String str) {
                XViewLayer.this.onLayerLoadingUrl();
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewReady() {
                XViewLayer xViewLayer = XViewLayer.this;
                xViewLayer.mLayerState = BaseLayer.LAYERESTATE.READY;
                xViewLayer.onLayerReady();
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewRequest(XViewRequest xViewRequest) {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXViewVisibleChanged(boolean z) {
            }

            @Override // com.jingdong.common.XView.XViewCallBack
            public void onXVivewClosed() {
                XViewLayer.this.onLayerClosed();
            }
        });
        this.xView = createXView;
        if (createXView != null) {
            createXView.startXView();
        }
        return this.xView;
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void destroyLayer() {
        super.destroyLayer();
        XView xView = this.xView;
        if (xView != null) {
            xView.closeXView();
            this.xView.destroyXView();
            this.xView = null;
        }
    }

    @Override // com.jingdong.common.XView2.layer.xview.IXView
    public void onLayerClosed() {
        XViewConfigEntity.LayersEntity layersEntity;
        ILayerCallBack iLayerCallBack = this.mCallBack;
        if (iLayerCallBack == null || (layersEntity = this.layersEntity) == null) {
            return;
        }
        iLayerCallBack.onLayerClosed(layersEntity.layerId, 10);
    }

    @Override // com.jingdong.common.XView2.layer.xview.IXView
    public void onLayerDisplayed() {
        ILayerCallBack iLayerCallBack;
        XViewConfigEntity.LayersEntity layersEntity;
        if (!isCanLayerRealPop() || (iLayerCallBack = this.mCallBack) == null || (layersEntity = this.layersEntity) == null) {
            return;
        }
        iLayerCallBack.onLayerDisplayed(layersEntity.layerId);
    }

    @Override // com.jingdong.common.XView2.layer.xview.IXView
    public void onLayerError() {
        XViewConfigEntity.LayersEntity layersEntity;
        ILayerCallBack iLayerCallBack = this.mCallBack;
        if (iLayerCallBack == null || (layersEntity = this.layersEntity) == null) {
            return;
        }
        iLayerCallBack.onError(layersEntity.layerId);
        XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
        XViewConfigEntity.LayersEntity layersEntity2 = this.layersEntity;
        manager.notifyLayerShowError(layersEntity2.layerId, layersEntity2.logicKey);
    }

    @Override // com.jingdong.common.XView2.layer.xview.IXView
    public void onLayerLoadingUrl() {
        if (this.mCallBack == null || this.layersEntity == null || this.mStartLoading.get()) {
            return;
        }
        this.mStartLoading.set(true);
        this.mCallBack.onLayerLoading(this.layersEntity.layerId);
    }

    @Override // com.jingdong.common.XView2.layer.xview.IXView
    public void onLayerReady() {
        XViewConfigEntity.LayersEntity layersEntity;
        if (XView2Utils.canWebViewLayerPreLoaded(this.layersEntity)) {
            if (!this.mIsPopStatusMode.get()) {
                return;
            }
            XView xView = this.xView;
            if (xView != null) {
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
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void onResume() {
        super.onResume();
        XView xView = this.xView;
        if (xView != null) {
            xView.onResume();
        }
    }
}
