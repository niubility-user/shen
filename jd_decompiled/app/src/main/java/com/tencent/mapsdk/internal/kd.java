package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.HeatmapInfo;
import com.tencent.map.sdk.utilities.visualization.heatmap.GradientVectorOverlayProvider;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class kd extends HeatmapInfo implements uc {
    private GradientVectorOverlayProvider a;

    public kd(GradientVectorOverlayProvider gradientVectorOverlayProvider) {
        this.a = gradientVectorOverlayProvider;
        ArrayList arrayList = new ArrayList();
        arrayList.add(gradientVectorOverlayProvider.getData());
        setDataList(arrayList);
        this.radius = gradientVectorOverlayProvider.getRadius();
        this.colors = gradientVectorOverlayProvider.getColors();
        this.colorPoints = gradientVectorOverlayProvider.getColorPoints();
        this.colorMapSize = gradientVectorOverlayProvider.getColorMapSize();
        this.visible = gradientVectorOverlayProvider.isVisibility();
        this.opacity = gradientVectorOverlayProvider.getOpacity();
        this.maxZoom = gradientVectorOverlayProvider.getMaxZoom();
        this.minZoom = gradientVectorOverlayProvider.getMinZoom();
        this.maxHeight = gradientVectorOverlayProvider.getMaxHeight();
        this.draw3D = gradientVectorOverlayProvider.isEnable3D();
        this.maxIntensity = gradientVectorOverlayProvider.getMaxIntensity();
        this.minIntensity = gradientVectorOverlayProvider.getMinIntensity();
        this.intensityFlag = gradientVectorOverlayProvider.isIntensityFlag();
        this.mAnimate = gradientVectorOverlayProvider.isAnimate();
        this.mAnimateDuration = gradientVectorOverlayProvider.getAnimateDuration();
        this.level = gradientVectorOverlayProvider.getDisplayLevel();
        this.zIndex = gradientVectorOverlayProvider.getZIndex();
        String str = "java colors : " + this.colors.length;
        for (int i2 = 0; i2 < this.colors.length; i2++) {
            String str2 = "java colors: " + this.colors[i2];
        }
        String str3 = "java opacity : " + this.opacity;
        String str4 = "java colorPoints : " + this.colorPoints.length;
        String str5 = "java colorMapSize : " + this.colorMapSize;
        String str6 = "java notes : " + this.notes.length;
        String str7 = "java nodeIndexes : " + this.nodeIndexes.length;
    }

    public GradientVectorOverlayProvider a() {
        return this.a;
    }
}
