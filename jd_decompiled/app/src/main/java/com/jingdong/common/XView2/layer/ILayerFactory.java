package com.jingdong.common.XView2.layer;

import android.app.Activity;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface ILayerFactory {
    void addLayerControlById(String str, BaseLayer baseLayer);

    void changeLayerLayout(JSONObject jSONObject);

    void closeLayerById(String str);

    BaseLayer createLayerInstance(SoftReference<Activity> softReference, XViewConfigEntity.LayersEntity layersEntity, XView2 xView2);

    ArrayList<? extends BaseLayer> getShowEnterPopLayers();

    BaseLayer getShowLayerById(XViewConfigEntity.LayersEntity layersEntity);

    void layerDidShow(BaseLayer baseLayer, String str);

    void onLayerPause();

    void onLayerResume();

    void preLoadLayerShow(BaseLayer baseLayer);

    void removeAllLayers();

    void reportException(int i2, String str, String str2);

    void showAllEnterPopLayers();

    void showAllPopLayersImmediately();

    void showLayerById(String str, String str2);
}
