package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Polygon;
import com.tencent.tencentmap.mapsdk.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes13.dex */
public class JDReactMapPolygon extends JDReactMapFeature {
    private List<LatLng> coordinates;
    private int fillColor;
    private boolean geodesic;
    private Polygon polygon;
    private PolygonOptions polygonOptions;
    private int strokeColor;
    private float strokeWidth;
    private int zIndex;

    public JDReactMapPolygon(Context context) {
        super(context);
    }

    private PolygonOptions createPolygonOptions() {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(this.coordinates);
        polygonOptions.fillColor(this.fillColor);
        polygonOptions.strokeColor(this.strokeColor);
        polygonOptions.strokeWidth(this.strokeWidth);
        polygonOptions.zIndex(this.zIndex);
        return polygonOptions;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void addToMap(TencentMap tencentMap) {
        this.polygon = tencentMap.addPolygon(getPolygonOptions());
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public Object getFeature() {
        return this.polygon;
    }

    public PolygonOptions getPolygonOptions() {
        if (this.polygonOptions == null) {
            this.polygonOptions = createPolygonOptions();
        }
        return this.polygonOptions;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void removeFromMap(TencentMap tencentMap) {
        this.polygon.remove();
    }

    public void setCoordinates(ReadableArray readableArray) {
        this.coordinates = new ArrayList(readableArray.size());
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            ReadableMap map = readableArray.getMap(i2);
            this.coordinates.add(i2, new LatLng(map.getDouble(PdLVBody.LATITUDE), map.getDouble(PdLVBody.LONGITUDE)));
        }
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setPoints(this.coordinates);
        }
    }

    public void setFillColor(int i2) {
        this.fillColor = i2;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setFillColor(i2);
        }
    }

    public void setStrokeColor(int i2) {
        this.strokeColor = i2;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setStrokeColor(i2);
        }
    }

    public void setStrokeWidth(float f2) {
        this.strokeWidth = f2;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setStrokeWidth(f2);
        }
    }

    public void setZIndex(int i2) {
        this.zIndex = i2;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setZIndex(i2);
        }
    }
}
