package com.jd.manto.map;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TextureMapView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes17.dex */
public final class MapAndProcessCombineView extends FrameLayout {
    private MapProcessCenter mapProcessCenter;
    private final MapView mapView;

    public MapAndProcessCombineView(@NonNull Context context) {
        this(context, null);
    }

    public MapProcessCenter getMapProcessCenter() {
        return this.mapProcessCenter;
    }

    public MapView getMapView() {
        return this.mapView;
    }

    public synchronized void setMapProcessCenter(MapProcessCenter mapProcessCenter) {
        this.mapProcessCenter = mapProcessCenter;
    }

    public MapAndProcessCombineView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MapAndProcessCombineView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TextureMapView textureMapView = new TextureMapView(context);
        this.mapView = textureMapView;
        addView(textureMapView, -1, -1);
    }
}
