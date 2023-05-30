package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.Circle;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes13.dex */
public class JDReactMapCircle extends JDReactMapFeature {
    private LatLng center;
    private Circle circle;
    private CircleOptions circleOptions;
    private int fillColor;
    private double radius;
    private int strokeColor;
    private float strokeWidth;
    private int zIndex;

    public JDReactMapCircle(Context context) {
        super(context);
    }

    private CircleOptions createCircleOptions() {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(this.center);
        circleOptions.radius(this.radius);
        circleOptions.fillColor(this.fillColor);
        circleOptions.strokeColor(this.strokeColor);
        circleOptions.strokeWidth(this.strokeWidth);
        circleOptions.zIndex(this.zIndex);
        return circleOptions;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void addToMap(TencentMap tencentMap) {
        this.circle = tencentMap.addCircle(getCircleOptions());
    }

    public CircleOptions getCircleOptions() {
        if (this.circleOptions == null) {
            this.circleOptions = createCircleOptions();
        }
        return this.circleOptions;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public Object getFeature() {
        return this.circle;
    }

    @Override // com.jingdong.jdreact.plugin.map.JDReactMapFeature
    public void removeFromMap(TencentMap tencentMap) {
        this.circle.remove();
    }

    public void setCenter(LatLng latLng) {
        this.center = latLng;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setCenter(latLng);
        }
    }

    public void setFillColor(int i2) {
        this.fillColor = i2;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setFillColor(i2);
        }
    }

    public void setRadius(double d) {
        this.radius = d;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setRadius(d);
        }
    }

    public void setStrokeColor(int i2) {
        this.strokeColor = i2;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setStrokeColor(i2);
        }
    }

    public void setStrokeWidth(float f2) {
        this.strokeWidth = f2;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setStrokeWidth(f2);
        }
    }

    public void setZIndex(int i2) {
        this.zIndex = i2;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setZIndex(i2);
        }
    }
}
