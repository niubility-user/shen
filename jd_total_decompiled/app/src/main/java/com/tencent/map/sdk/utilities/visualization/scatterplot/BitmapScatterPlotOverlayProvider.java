package com.tencent.map.sdk.utilities.visualization.scatterplot;

import com.tencent.map.sdk.utilities.visualization.datamodels.ScatterLatLng;
import com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import java.util.List;

/* loaded from: classes9.dex */
public final class BitmapScatterPlotOverlayProvider extends ScatterPlotOverlayProvider {
    private static final BitmapDescriptor[] DEFAULT_BITMAP_ICONS = new BitmapDescriptor[0];
    private int DEFAULT_WIDTH = 30;
    private int DEFAULT_HEIGHT = 30;
    private int mWidth = 30;
    private int mHeight = 30;
    private BitmapDescriptor[] mBitmaps = DEFAULT_BITMAP_ICONS;

    public BitmapScatterPlotOverlayProvider() {
        type(ScatterPlotOverlayProvider.ScatterPlotType.Bitmap);
    }

    public BitmapScatterPlotOverlayProvider bitmaps(BitmapDescriptor[] bitmapDescriptorArr) {
        BitmapDescriptor[] bitmapDescriptorArr2 = this.mBitmaps;
        if (bitmapDescriptorArr2.length <= 0 && bitmapDescriptorArr2.length == 0 && bitmapDescriptorArr != null) {
            this.mBitmaps = bitmapDescriptorArr;
        }
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider
    public BitmapScatterPlotOverlayProvider data(List<ScatterLatLng> list) {
        super.data(list);
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider
    public /* bridge */ /* synthetic */ ScatterPlotOverlayProvider data(List list) {
        return data((List<ScatterLatLng>) list);
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public BitmapScatterPlotOverlayProvider displayLevel(int i2) {
        super.displayLevel(i2);
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider
    public BitmapScatterPlotOverlayProvider enable3D(boolean z) {
        super.enable3D(z);
        return this;
    }

    public BitmapDescriptor[] getBitmaps() {
        return this.mBitmaps;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider
    public List<ScatterLatLng> getData() {
        return super.getData();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getDisplayLevel() {
        return super.getDisplayLevel();
    }

    public int getHeight() {
        return this.mHeight;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getMaxZoom() {
        return super.getMaxZoom();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getMinZoom() {
        return super.getMinZoom();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public float getOpacity() {
        return super.getOpacity();
    }

    public int getWidth() {
        return this.mWidth;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public int getZIndex() {
        return super.getZIndex();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider
    public boolean isEnable3D() {
        return super.isEnable3D();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public boolean isVisibility() {
        return super.isVisibility();
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public BitmapScatterPlotOverlayProvider opacity(float f2) {
        super.opacity(f2);
        return this;
    }

    public BitmapScatterPlotOverlayProvider scale(int i2, int i3) {
        if (i2 > 0 && i3 > 0) {
            this.mWidth = i2;
            this.mHeight = i3;
        }
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public BitmapScatterPlotOverlayProvider visibility(boolean z) {
        super.visibility(z);
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider, com.tencent.map.sdk.utilities.visualization.BaseOverlayProvider, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider
    public BitmapScatterPlotOverlayProvider zIndex(int i2) {
        super.zIndex(i2);
        return this;
    }

    @Override // com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider
    public BitmapScatterPlotOverlayProvider zoomRange(int i2, int i3) {
        super.zoomRange(i2, i3);
        return this;
    }
}
