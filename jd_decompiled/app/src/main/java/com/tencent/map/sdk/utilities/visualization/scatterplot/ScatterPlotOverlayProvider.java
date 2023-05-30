package com.tencent.map.sdk.utilities.visualization.scatterplot;

import com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.datamodels.ScatterLatLng;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class ScatterPlotOverlayProvider extends BaseOverlayProvider {
    private List<ScatterLatLng> dataList = new ArrayList();
    private int mType = 0;
    private boolean mDraw3D = false;

    /* loaded from: classes9.dex */
    public enum ScatterPlotType {
        Dot,
        Bitmap
    }

    public ScatterPlotOverlayProvider data(List<ScatterLatLng> list) {
        if (list != null) {
            this.dataList = list;
        }
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public ScatterPlotOverlayProvider displayLevel(int i2) {
        super.displayLevel(i2);
        return this;
    }

    public ScatterPlotOverlayProvider enable3D(boolean z) {
        this.mDraw3D = z;
        return this;
    }

    public List<ScatterLatLng> getData() {
        return this.dataList;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getDisplayLevel() {
        return super.getDisplayLevel();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getMaxZoom() {
        return super.getMaxZoom();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getMinZoom() {
        return super.getMinZoom();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public float getOpacity() {
        return super.getOpacity();
    }

    public int getType() {
        return ScatterPlotType.values()[this.mType].ordinal();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getZIndex() {
        return super.getZIndex();
    }

    public boolean isEnable3D() {
        return this.mDraw3D;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public boolean isVisibility() {
        return super.isVisibility();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public ScatterPlotOverlayProvider opacity(float f2) {
        super.opacity(f2);
        return this;
    }

    public ScatterPlotOverlayProvider type(ScatterPlotType scatterPlotType) {
        this.mType = scatterPlotType.ordinal();
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public ScatterPlotOverlayProvider visibility(boolean z) {
        super.visibility(z);
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public ScatterPlotOverlayProvider zIndex(int i2) {
        super.zIndex(i2);
        return this;
    }

    public ScatterPlotOverlayProvider zoomRange(int i2, int i3) {
        if (i2 <= i3) {
            super.minZoom(i2);
            super.maxZoom(i3);
        }
        return this;
    }
}
