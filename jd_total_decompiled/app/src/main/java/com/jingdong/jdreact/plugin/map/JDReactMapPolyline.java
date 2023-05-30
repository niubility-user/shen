package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes13.dex */
public class JDReactMapPolyline extends JDReactMapFeature {
    private int color;
    private List<LatLng> coordinates;
    private boolean geodesic;
    private boolean isDottedLine;
    private Polyline polyline;
    private PolylineOptions polylineOptions;
    private float width;
    private int zIndex;

    public JDReactMapPolyline(Context context) {
        super(context);
    }

    private PolylineOptions createPolylineOptions() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(this.coordinates);
        polylineOptions.color(this.color);
        polylineOptions.width(this.width);
        polylineOptions.zIndex(this.zIndex);
        polylineOptions.lineType(2);
        return polylineOptions;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void addToMap(TencentMap tencentMap) {
        this.polyline = tencentMap.addPolyline(getPolylineOptions());
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public Object getFeature() {
        return this.polyline;
    }

    public PolylineOptions getPolylineOptions() {
        if (this.polylineOptions == null) {
            this.polylineOptions = createPolylineOptions();
        }
        return this.polylineOptions;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void removeFromMap(TencentMap tencentMap) {
        this.polyline.remove();
    }

    public void setColor(int i2) {
        this.color = i2;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setColor(i2);
        }
    }

    public void setCoordinates(ReadableArray readableArray) {
        this.coordinates = new ArrayList(readableArray.size());
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            ReadableMap map = readableArray.getMap(i2);
            this.coordinates.add(i2, new LatLng(map.getDouble(PdLVBody.LATITUDE), map.getDouble(PdLVBody.LONGITUDE)));
        }
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setPoints(this.coordinates);
        }
    }

    public void setDottedLine(boolean z) {
        this.isDottedLine = z;
    }

    public void setGeodesic(boolean z) {
        this.geodesic = z;
    }

    public void setWidth(float f2) {
        this.width = f2;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setWidth(f2);
        }
    }

    public void setZIndex(int i2) {
        this.zIndex = i2;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setZIndex(i2);
        }
    }
}
