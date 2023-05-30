package com.jingdong.common.XView2.utils;

import android.view.ViewGroup;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.XViewConfigEntity;

/* loaded from: classes5.dex */
public class LayerUtil {
    public static ViewGroup getAnimateTargetView(XViewConfigEntity.LayersEntity layersEntity, XView2Container xView2Container) {
        XViewConfigEntity.LayoutEntity layoutEntity;
        if (layersEntity == null || xView2Container == null) {
            return null;
        }
        return ((layersEntity.renderType == 6 || XView2Utils.isConvertBool(layersEntity.fullScreen) || ((layoutEntity = layersEntity.layout) != null && "1".equals(layoutEntity.fill))) && isConfigMaterialLayout(layersEntity)) ? xView2Container.getContentContainer() : xView2Container;
    }

    public static boolean isConfigMaterialLayout(XViewConfigEntity.LayersEntity layersEntity) {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        XViewConfigEntity.LayoutEntity layoutEntity;
        return (layersEntity == null || (renderDataEntity = layersEntity.renderData) == null || (layoutEntity = renderDataEntity.layout) == null || layoutEntity.height <= 0 || layoutEntity.width <= 0) ? false : true;
    }
}
