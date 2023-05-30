package com.jingdong.common.XView2.layer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer;
import com.jingdong.common.XView2.layer.imge.ImageViewLayer;
import com.jingdong.common.XView2.layer.lottie.LottieLayer;
import com.jingdong.common.XView2.layer.video.VideoPlayerLayer;
import com.jingdong.common.XView2.layer.xview.XViewLayer;
import com.jingdong.common.XView2.utils.LayerUtil;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class LayerTypeManager implements ILayerFactory {
    public static final int TYPE_ALL_POP_IMMEDIATELY = 2;
    public static final int TYPE_ALL_POP_SCREEN = 1;
    private Context mContext;
    private ConcurrentHashMap<String, BaseLayer> mLayoutControls = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, XViewConfigEntity.LayersEntity> mMuGroupNumber = new ConcurrentHashMap<>();

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void addLayerControlById(String str, BaseLayer baseLayer) {
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        if (concurrentHashMap != null) {
            concurrentHashMap.put(str, baseLayer);
        }
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void changeLayerLayout(JSONObject jSONObject) {
        long optLong;
        long optLong2;
        long optLong3;
        long optLong4;
        String optString = jSONObject.optString(XView2Constants.LAYER_ID);
        Context context = this.mContext;
        if (context != null && !((Activity) context).isFinishing() && !TextUtils.isEmpty(optString) && this.mLayoutControls != null) {
            boolean optBoolean = jSONObject.optBoolean(XView2Constants.FILL_CONTAINER);
            for (Map.Entry<String, BaseLayer> entry : this.mLayoutControls.entrySet()) {
                BaseLayer value = entry.getValue();
                String key = entry.getKey();
                if (value != null && optString.equals(key)) {
                    XView2Container container = value.getContainer();
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    if (container != null) {
                        if (optBoolean) {
                            Rect rect = new Rect();
                            container.getGlobalVisibleRect(rect);
                            int i2 = rect.left;
                            int i3 = rect.top;
                            long j2 = rect.right - i2;
                            optLong4 = rect.bottom - i3;
                            optLong2 = i3;
                            optLong3 = j2;
                            optLong = i2;
                        } else {
                            optLong = jSONObject.optLong("left");
                            optLong2 = jSONObject.optLong("top");
                            optLong3 = jSONObject.optLong("width");
                            optLong4 = jSONObject.optLong("height");
                            if (optLong == 0 && optLong2 == 0 && optLong3 == 0 && optLong4 == 0) {
                                value.changeLayoutCallBack(optString, "", -1, jDJSONObject.toString());
                                return;
                            }
                        }
                        jDJSONObject.put("left", (Object) Long.valueOf(optLong));
                        jDJSONObject.put("top", (Object) Long.valueOf(optLong2));
                        jDJSONObject.put("width", (Object) Long.valueOf(optLong3));
                        jDJSONObject.put("height", (Object) Long.valueOf(optLong4));
                        container.changeLayerLayout(optBoolean, jDJSONObject);
                        if (optBoolean) {
                            value.setBackKeyTriggerListener();
                        } else {
                            value.removeBackKeyTriggerListener();
                        }
                        value.changeLayoutCallBack(optString, "", 1, jDJSONObject.toString());
                        return;
                    }
                    value.changeLayoutCallBack(optString, "", -1, jDJSONObject.toString());
                }
            }
            return;
        }
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u4e0d\u6ee1\u8db3\u6539\u53d8\u5e03\u5c40\u6761\u4ef6");
    }

    public void clearMuGroupNumbers() {
        this.mMuGroupNumber.clear();
        this.mMuGroupNumber = null;
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void closeLayerById(String str) {
        if (this.mLayoutControls == null || TextUtils.isEmpty(str)) {
            return;
        }
        for (Map.Entry<String, BaseLayer> entry : this.mLayoutControls.entrySet()) {
            BaseLayer value = entry.getValue();
            String key = entry.getKey();
            if (value != null && str.equals(key)) {
                value.closeLayer(str, 9);
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public BaseLayer createLayerInstance(SoftReference<Activity> softReference, XViewConfigEntity.LayersEntity layersEntity, XView2 xView2) {
        if (layersEntity == null || softReference == null || softReference.get() == null) {
            return null;
        }
        this.mContext = softReference.get();
        int i2 = layersEntity.renderType;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 6) {
                            return null;
                        }
                        return new FlexCubeLayer(softReference.get(), layersEntity, xView2);
                    }
                    return new XViewLayer(softReference.get(), layersEntity, xView2);
                }
                return new VideoPlayerLayer(softReference.get(), layersEntity, xView2);
            }
            return new LottieLayer(softReference.get(), layersEntity, xView2);
        }
        return new ImageViewLayer(softReference.get(), layersEntity, xView2);
    }

    public ConcurrentHashMap<String, BaseLayer> getLayoutControls() {
        return this.mLayoutControls;
    }

    public ConcurrentHashMap<Integer, XViewConfigEntity.LayersEntity> getMuGroupNumbers() {
        return this.mMuGroupNumber;
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public ArrayList<BaseLayer> getShowEnterPopLayers() {
        ArrayList<BaseLayer> arrayList = new ArrayList<>();
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        if (concurrentHashMap == null) {
            return null;
        }
        Iterator<Map.Entry<String, BaseLayer>> it = concurrentHashMap.entrySet().iterator();
        while (it.hasNext()) {
            BaseLayer value = it.next().getValue();
            if (value != null && (value.isLayerEnterPop() || value.isLayerEnterImmediatelyPop())) {
                arrayList.add(value);
            }
        }
        return arrayList;
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public BaseLayer getShowLayerById(XViewConfigEntity.LayersEntity layersEntity) {
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        if (concurrentHashMap == null || layersEntity == null) {
            return null;
        }
        for (Map.Entry<String, BaseLayer> entry : concurrentHashMap.entrySet()) {
            BaseLayer value = entry.getValue();
            String key = entry.getKey();
            String str = layersEntity.layerId;
            if (!TextUtils.isEmpty(str) && str.equals(key)) {
                return value;
            }
        }
        return null;
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void layerDidShow(BaseLayer baseLayer, String str) {
        ViewGroup animateTargetView;
        if (baseLayer != null && !TextUtils.isEmpty(str) && baseLayer.mXViewContainer != null) {
            XViewConfigEntity.LayersEntity layersEntity = baseLayer.layersEntity;
            if (layersEntity != null && layersEntity.renderType == 6) {
                baseLayer.setCouldPop(true);
                return;
            }
            if (layersEntity != null && baseLayer.isPopUpAnimType() && (animateTargetView = LayerUtil.getAnimateTargetView(baseLayer.getLayersEntity(), baseLayer.getContainer())) != null) {
                animateTargetView.setAlpha(0.0f);
            }
            baseLayer.mXViewContainer.setVisibility(0);
            XView2Utils.setXViewGrayMode(baseLayer.mXViewContainer);
            return;
        }
        XView2LayerObservableManager.getManager().notifyLayerShowError(str, baseLayer.getLogicKey());
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void onLayerPause() {
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        if (concurrentHashMap == null) {
            return;
        }
        Iterator<Map.Entry<String, BaseLayer>> it = concurrentHashMap.entrySet().iterator();
        while (it.hasNext()) {
            BaseLayer value = it.next().getValue();
            if (value != null && !XView2Utils.isLayerInVisible(value)) {
                value.onPause();
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void onLayerResume() {
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        if (concurrentHashMap == null) {
            return;
        }
        Iterator<Map.Entry<String, BaseLayer>> it = concurrentHashMap.entrySet().iterator();
        while (it.hasNext()) {
            BaseLayer value = it.next().getValue();
            if (value != null && !XView2Utils.isLayerInVisible(value)) {
                value.onResume();
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void preLoadLayerShow(BaseLayer baseLayer) {
        if (baseLayer != null && XView2Utils.canWebViewLayerPreLoaded(baseLayer.layersEntity)) {
            baseLayer.mIsPopStatusMode.set(true);
            if (baseLayer instanceof XViewLayer) {
                View view = baseLayer.mCurrentLayer;
                if ((view instanceof XView) && ((XView) view).isXViewReady()) {
                    ((XViewLayer) baseLayer).onLayerReady();
                }
            }
            if (baseLayer instanceof FlexCubeLayer) {
                ((FlexCubeLayer) baseLayer).onLayerReady();
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void removeAllLayers() {
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        if (concurrentHashMap == null) {
            return;
        }
        Iterator<Map.Entry<String, BaseLayer>> it = concurrentHashMap.entrySet().iterator();
        while (it.hasNext()) {
            BaseLayer value = it.next().getValue();
            if (value != null) {
                value.destroyLayer();
            }
        }
        this.mLayoutControls.clear();
        this.mLayoutControls = null;
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void reportException(int i2, String str, String str2) {
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        if (concurrentHashMap == null) {
            return;
        }
        if (i2 == 1) {
            for (Map.Entry<String, BaseLayer> entry : concurrentHashMap.entrySet()) {
                BaseLayer value = entry.getValue();
                String key = entry.getKey();
                if (value != null && value.mXViewContainer != null && value.isLayerEnterPop()) {
                    XViewConfigEntity.LayersEntity layersEntity = value.layersEntity;
                    XView2Utils.reportXView2ErrorWithSwitch(str, "NXViewException", "", str2, key, layersEntity != null ? layersEntity.name : "", "");
                }
            }
        } else if (i2 == 2) {
            for (Map.Entry<String, BaseLayer> entry2 : concurrentHashMap.entrySet()) {
                BaseLayer value2 = entry2.getValue();
                String key2 = entry2.getKey();
                if (value2 != null && value2.mXViewContainer != null && value2.isLayerEnterImmediatelyPop()) {
                    XViewConfigEntity.LayersEntity layersEntity2 = value2.layersEntity;
                    XView2Utils.reportXView2ErrorWithSwitch(str, "NXViewException", "", str2, key2, layersEntity2 != null ? layersEntity2.name : "", "");
                }
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void showAllEnterPopLayers() {
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        if (concurrentHashMap == null) {
            return;
        }
        for (Map.Entry<String, BaseLayer> entry : concurrentHashMap.entrySet()) {
            BaseLayer value = entry.getValue();
            String key = entry.getKey();
            if (value != null && value.mXViewContainer != null && value.isLayerEnterPop()) {
                preLoadLayerShow(value);
                layerDidShow(value, key);
                value.onResume();
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void showAllPopLayersImmediately() {
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        if (concurrentHashMap == null) {
            return;
        }
        for (Map.Entry<String, BaseLayer> entry : concurrentHashMap.entrySet()) {
            BaseLayer value = entry.getValue();
            String key = entry.getKey();
            if (value != null && value.mXViewContainer != null && value.isLayerEnterImmediatelyPop()) {
                preLoadLayerShow(value);
                layerDidShow(value, key);
                value.onResume();
            }
        }
    }

    @Override // com.jingdong.common.XView2.layer.ILayerFactory
    public void showLayerById(String str, String str2) {
        ConcurrentHashMap<String, BaseLayer> concurrentHashMap = this.mLayoutControls;
        boolean z = false;
        if (concurrentHashMap == null) {
            XView2LayerObservableManager.getManager().notifyLayerShowError(str, str2);
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u672c\u5730layerId\u6bd4\u5bf9\u4e0d\u5b58\u5728");
            return;
        }
        for (Map.Entry<String, BaseLayer> entry : concurrentHashMap.entrySet()) {
            BaseLayer value = entry.getValue();
            String key = entry.getKey();
            if (value != null && str.equals(key)) {
                preLoadLayerShow(value);
                layerDidShow(value, str);
                value.onResume();
                z = true;
            }
        }
        if (z) {
            return;
        }
        XView2LayerObservableManager.getManager().notifyLayerShowError(str, str2);
    }
}
