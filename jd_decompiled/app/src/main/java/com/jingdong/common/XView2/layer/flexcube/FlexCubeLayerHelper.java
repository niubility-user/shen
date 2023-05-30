package com.jingdong.common.XView2.layer.flexcube;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jd.framework.json.JDJSONObject;
import com.jd.hybrid.downloader.m.b;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorUI;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.servicekit.imagekit.BabelDrawableListener;
import com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer;
import com.jd.lib.babel.servicekit.imagekit.ImageArr;
import com.jd.lib.babel.servicekit.model.BaseEvent;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.iwidget.ui.IUserSide;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.MaterialGroup;
import com.jd.lib.flexcube.owidgets.view.close.CloseButton;
import com.jd.lib.flexcube.widgets.FlexBoxView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.entity.XViewWebviewEntity;
import com.jingdong.common.XView2.entity.webview.XViewWebviewDataPath;
import com.jingdong.common.XView2.layer.IBaseLayer;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.layer.flexcube.view.XViewLottieView;
import com.jingdong.common.XView2.layer.flexcube.view.XViewVideoView;
import com.jingdong.common.XView2.layer.flexcube.view.XViewWebView;
import com.jingdong.common.XView2.strategy.downloader.XViewCache;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class FlexCubeLayerHelper {
    public static Drawable sTransparentDrawable = new ColorDrawable(0);
    public ArrayList<IBaseLayer> list;
    public BabelScope mBabelScope;
    public Context mContext;
    private final FlexCubeLayer mFlexCubeLayer;
    public XViewLottieView mXViewLottieView;
    public XViewVideoView mXViewVideoView;
    public XViewWebView xViewWebView;
    private XViewWebviewEntity xViewWebviewEntity;

    public FlexCubeLayerHelper(Context context, FlexCubeLayer flexCubeLayer) {
        this.mContext = context;
        this.mFlexCubeLayer = flexCubeLayer;
        BabelScope babelScope = new BabelScope();
        this.mBabelScope = babelScope;
        babelScope.iFloorUI = new IFloorUI() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayerHelper.1
            {
                FlexCubeLayerHelper.this = this;
            }

            @Override // com.jd.lib.babel.ifloor.ui.IFloorUI
            public void notifyDataSetChanged() {
            }

            @Override // com.jd.lib.babel.ifloor.ui.IFloorUI
            public void sendEvent(BaseEvent baseEvent) {
            }

            @Override // com.jd.lib.babel.ifloor.ui.IFloorUI
            public void sendExposureData(MtaData mtaData) {
            }
        };
        this.mBabelScope.register(IUserSide.class, new IUserSide() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayerHelper.2
            {
                FlexCubeLayerHelper.this = this;
            }

            @Override // com.jd.lib.flexcube.iwidget.ui.IUserSide
            public int getUserSide() {
                return 1;
            }
        });
        this.mBabelScope.register(BabelImageKitServer.class, new BabelImageKitServer() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayerHelper.3
            {
                FlexCubeLayerHelper.this = this;
            }

            @Override // com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer
            public void displayImage(ImageArr imageArr) {
                String url;
                if (imageArr == null || imageArr.getImg() == null) {
                    return;
                }
                JDDisplayImageOptions isScale = new JDDisplayImageOptions().isScale(imageArr.isScale());
                if (!imageArr.isNeedImageOnFail()) {
                    isScale.showImageOnFail((Drawable) null);
                }
                isScale.showImageOnLoading(FlexCubeLayerHelper.sTransparentDrawable);
                if (imageArr.getBitmapConfig() != null) {
                    isScale.setBitmapConfig(imageArr.getBitmapConfig());
                }
                String finalLayerId = XView2Utils.getFinalLayerId(FlexCubeLayerHelper.this.mFlexCubeLayer.getLayerId());
                XView2Utils.setOptionReferer(isScale, finalLayerId);
                b files = XViewCache.getInstance().getFiles(finalLayerId + imageArr.getUrl());
                if (files != null && !TextUtils.isEmpty(files.getFilePath()) && SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PREDOWNLOAD, false)) {
                    url = "file://" + files.getFilePath();
                } else {
                    url = imageArr.getUrl();
                }
                JDImageUtils.displayImage(url, imageArr.getImg(), isScale, imageArr.isNeedImageOnFail(), new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayerHelper.3.1
                    {
                        AnonymousClass3.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put(com.jingdong.jdma.minterface.BaseEvent.SCENE, (Object) "2");
                        jDJSONObject.put("mediaType", (Object) "2");
                        XView2Utils.reportXView2Error("dlFail", "NXViewException", str, jDFailReason.getCause().toString(), FlexCubeLayerHelper.this.mFlexCubeLayer.getLayerId(), FlexCubeLayerHelper.this.mFlexCubeLayer.getLayersEntity() != null ? FlexCubeLayerHelper.this.mFlexCubeLayer.getLayersEntity().name : "", jDJSONObject.toString());
                    }
                }, null);
            }

            @Override // com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer
            public ImageView newImageView(Context context2, AttributeSet attributeSet) {
                return null;
            }

            @Override // com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer
            public void obtainDrawable(Context context2, String str, BabelDrawableListener babelDrawableListener) {
            }
        });
        this.mBabelScope.register(IXViewWithFlexCube.class, new IXViewWithFlexCube() { // from class: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayerHelper.4
            {
                FlexCubeLayerHelper.this = this;
            }

            @Override // com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube
            public ILayerCallBack getLayerCallBack() {
                return FlexCubeLayerHelper.this.mFlexCubeLayer.getFlexCubeLayerCallBack();
            }

            @Override // com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube
            public String getLayerId() {
                return FlexCubeLayerHelper.this.mFlexCubeLayer.getLayerId();
            }

            @Override // com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube
            public String getLayerName() {
                return FlexCubeLayerHelper.this.mFlexCubeLayer.getLayersEntity() != null ? FlexCubeLayerHelper.this.mFlexCubeLayer.getLayersEntity().name : "";
            }

            @Override // com.jingdong.common.XView2.layer.flexcube.IXViewWithFlexCube
            public String getLogicKey() {
                return FlexCubeLayerHelper.this.mFlexCubeLayer.getLayersEntity() != null ? FlexCubeLayerHelper.this.mFlexCubeLayer.getLayersEntity().logicKey : "";
            }
        });
        this.list = new ArrayList<>();
    }

    private void changeChild(View view, int i2, int i3, float f2) {
        if ((view instanceof ViewGroup) && !(view instanceof IWidget)) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width > 0) {
                    layoutParams.width = i2;
                }
                if (layoutParams.height > 0) {
                    layoutParams.height = i3;
                }
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                changeChild(viewGroup.getChildAt(i4), i2, i3, f2);
            }
        }
        if ((view instanceof IWidget) && (view instanceof CloseButton) && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.leftMargin = (int) (marginLayoutParams.leftMargin * f2);
            marginLayoutParams.topMargin = (int) (marginLayoutParams.topMargin * f2);
        }
    }

    private void dealChild(View view) {
        int i2 = 0;
        if ((view instanceof ViewGroup) && !(view instanceof IWidget)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            while (i2 < childCount) {
                dealChild(viewGroup.getChildAt(i2));
                i2++;
            }
        } else if (view instanceof IWidget) {
            if (view instanceof XViewWebView) {
                this.xViewWebView = (XViewWebView) view;
            }
            if (view instanceof XViewVideoView) {
                this.mXViewVideoView = (XViewVideoView) view;
            }
            if (view instanceof XViewLottieView) {
                this.mXViewLottieView = (XViewLottieView) view;
            }
            if (view instanceof IBaseLayer) {
                this.list.add((IBaseLayer) view);
            }
            if (view instanceof FlexBoxView) {
                ViewGroup viewGroup2 = (ViewGroup) view;
                int childCount2 = viewGroup2.getChildCount();
                while (i2 < childCount2) {
                    View childAt = viewGroup2.getChildAt(i2);
                    if (childAt instanceof ViewGroup) {
                        dealChild(childAt);
                    }
                    i2++;
                }
            }
        }
    }

    public boolean changeSize(View view, FlexCubeModel flexCubeModel, int i2, int i3) {
        if (view == null || flexCubeModel == null || flexCubeModel.getFlexCubeWidth() <= 0 || i2 <= 0 || i3 <= 0) {
            return false;
        }
        changeChild(view, i2, i3, (i2 * 1.0f) / flexCubeModel.getFlexCubeWidth());
        XViewWebView xViewWebView = this.xViewWebView;
        if (xViewWebView == null || xViewWebView.getLayoutParams() == null) {
            return true;
        }
        ViewGroup.LayoutParams layoutParams = this.xViewWebView.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        this.xViewWebView.setLayoutParams(layoutParams);
        return true;
    }

    public void dealFlexCubeModel(FlexCubeModel flexCubeModel, XViewConfigEntity.LayersEntity layersEntity) {
        List<BaseWidgetEntity> list;
        XViewWebviewEntity xViewWebviewEntity;
        XViewWebviewDataPath xViewWebviewDataPath;
        if (flexCubeModel == null || (list = flexCubeModel.widgetList) == null) {
            return;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            BaseWidgetEntity baseWidgetEntity = flexCubeModel.widgetList.get(i2);
            if ((baseWidgetEntity instanceof XViewWebviewEntity) && (xViewWebviewDataPath = (xViewWebviewEntity = (XViewWebviewEntity) baseWidgetEntity).dataPath) != null && !TextUtils.isEmpty(xViewWebviewDataPath.url)) {
                this.xViewWebviewEntity = xViewWebviewEntity;
                XViewEntity xViewEntity = new XViewEntity();
                xViewEntity.isIntercepted = XView2Utils.isConvertBool(layersEntity.userInteractionEnabled);
                xViewEntity.needCloseButton = false;
                xViewEntity.needAutoClose = "1".equals(layersEntity.jmpCls);
                xViewEntity.needAutoDisplay = !XView2Utils.canWebViewLayerPreLoaded(layersEntity);
                xViewEntity.isFragment = false;
                xViewWebviewEntity.setXViewEntity(xViewEntity, this.mFlexCubeLayer.getXViewCallBack(), this.mFlexCubeLayer.getBaseLayerDelegate());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int dealSize(com.jd.lib.flexcube.layout.entity.FlexCubeModel r6, com.jingdong.common.XView2.entity.XViewConfigEntity.LayersEntity r7) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView2.layer.flexcube.FlexCubeLayerHelper.dealSize(com.jd.lib.flexcube.layout.entity.FlexCubeModel, com.jingdong.common.XView2.entity.XViewConfigEntity$LayersEntity):int");
    }

    public View getFlexcubeView(FlexCubeModel flexCubeModel) {
        if (flexCubeModel != null && flexCubeModel.floorConfig != null) {
            String style = flexCubeModel.getStyle(0);
            if (!TextUtils.isEmpty(style)) {
                try {
                    View view = FlexCube.getView(this.mContext, style);
                    if (view instanceof IFloorView) {
                        ((IFloorView) view).initView(style);
                        ((IFloorView) view).update(this.mBabelScope, (BabelScope) flexCubeModel, 0);
                        dealChild(view);
                        return view;
                    }
                } catch (Throwable unused) {
                }
            }
        }
        return null;
    }

    public List<MaterialGroup> getMaterialGroupList(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MaterialGroup());
        ((MaterialGroup) arrayList.get(0)).groupInfoList = new ArrayList();
        MaterialModel materialModel = new MaterialModel();
        materialModel.flexData = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            materialModel.flexData.putAll(map);
        }
        ((MaterialGroup) arrayList.get(0)).groupInfoList.add(materialModel);
        return arrayList;
    }

    public String getOpenapp(Map<String, String> map, JDJSONObject jDJSONObject) {
        String d = com.jd.lib.flexcube.widgets.b.b.d(map, jDJSONObject.optString("openapp"));
        return d == null ? "" : d;
    }

    public void refreshFlexCubeView(FlexCubeModel flexCubeModel, View view) {
        if (flexCubeModel == null || flexCubeModel.floorConfig == null || TextUtils.isEmpty(flexCubeModel.getStyle(0))) {
            return;
        }
        try {
            if (view instanceof IFloorView) {
                ((IFloorView) view).update(this.mBabelScope, (BabelScope) flexCubeModel, 0);
            }
        } catch (Throwable unused) {
        }
    }
}
