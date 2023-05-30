package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.tencent.map.lib.models.ScatterPlotInfo;
import com.tencent.map.sdk.utilities.visualization.scatterplot.BitmapScatterPlotOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.scatterplot.DotScatterPlotOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.scatterplot.ScatterPlotOverlayProvider;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class td extends ScatterPlotInfo implements uc {
    private ScatterPlotOverlayProvider a;

    public td(Context context, ScatterPlotOverlayProvider scatterPlotOverlayProvider) {
        this.a = scatterPlotOverlayProvider;
        ArrayList arrayList = new ArrayList();
        arrayList.add(scatterPlotOverlayProvider.getData());
        setDataList(arrayList);
        this.visible = scatterPlotOverlayProvider.isVisibility();
        this.opacity = scatterPlotOverlayProvider.getOpacity();
        this.maxZoom = scatterPlotOverlayProvider.getMaxZoom();
        this.minZoom = scatterPlotOverlayProvider.getMinZoom();
        this.draw3D = scatterPlotOverlayProvider.isEnable3D();
        this.level = scatterPlotOverlayProvider.getDisplayLevel();
        this.mType = scatterPlotOverlayProvider.getType();
        this.zIndex = scatterPlotOverlayProvider.getZIndex();
        if (scatterPlotOverlayProvider instanceof DotScatterPlotOverlayProvider) {
            DotScatterPlotOverlayProvider dotScatterPlotOverlayProvider = (DotScatterPlotOverlayProvider) scatterPlotOverlayProvider;
            this.radius = dotScatterPlotOverlayProvider.getRadius();
            this.colors = dotScatterPlotOverlayProvider.getColors();
            this.mAnimate = dotScatterPlotOverlayProvider.isAnimate();
        } else if (scatterPlotOverlayProvider instanceof BitmapScatterPlotOverlayProvider) {
            BitmapScatterPlotOverlayProvider bitmapScatterPlotOverlayProvider = (BitmapScatterPlotOverlayProvider) scatterPlotOverlayProvider;
            this.mBitmapWidth = bitmapScatterPlotOverlayProvider.getWidth();
            this.mBitmapHeight = bitmapScatterPlotOverlayProvider.getHeight();
            if (bitmapScatterPlotOverlayProvider.getBitmaps() == null || bitmapScatterPlotOverlayProvider.getBitmaps().length <= 0) {
                return;
            }
            this.mBitmaps = new Bitmap[bitmapScatterPlotOverlayProvider.getBitmaps().length];
            for (int i2 = 0; i2 < bitmapScatterPlotOverlayProvider.getBitmaps().length; i2++) {
                BitmapDescriptor bitmapDescriptor = bitmapScatterPlotOverlayProvider.getBitmaps()[i2];
                if (bitmapDescriptor != null) {
                    Bitmap bitmap = bitmapDescriptor.getBitmap(context);
                    if (bitmap != null && (this.mBitmapWidth != bitmap.getWidth() || this.mBitmapHeight != bitmap.getHeight())) {
                        bitmap = a(bitmap, this.mBitmapWidth, this.mBitmapHeight);
                    }
                    this.mBitmaps[i2] = bitmap;
                } else {
                    this.mBitmaps[i2] = null;
                }
            }
        }
    }

    public Bitmap a(Bitmap bitmap, int i2, int i3) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i2 / width, i3 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public ScatterPlotOverlayProvider a() {
        return this.a;
    }
}
