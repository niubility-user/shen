package com.tencent.map.lib.models;

import androidx.annotation.Keep;
import com.jd.dynamic.DYConstants;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

@Keep
/* loaded from: classes9.dex */
public class PolygonInfo {
    public int borderColor;
    public int borderLineId;
    public float borderWidth;
    public int color;
    public int minScaleLevel;
    public int[] pattern;
    public LatLng[] points;
    public int polygonId;
    public String textureName;
    public int textureSpacing;
    public float zIndex = 0.0f;
    public int level = 2;
    public int maxScaleLevel = 30;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Polygon2D{");
        stringBuffer.append(", color=");
        stringBuffer.append(this.color);
        stringBuffer.append(", borderColor=");
        stringBuffer.append(this.borderColor);
        stringBuffer.append(", borderWidth=");
        stringBuffer.append(this.borderWidth);
        stringBuffer.append(", points=");
        LatLng[] latLngArr = this.points;
        stringBuffer.append(latLngArr == null ? DYConstants.DY_NULL_STR : Integer.valueOf(latLngArr.length));
        stringBuffer.append(", polygonId=");
        stringBuffer.append(this.polygonId);
        stringBuffer.append(", borderLineId=");
        stringBuffer.append(this.borderLineId);
        stringBuffer.append(", zIndex=");
        stringBuffer.append(this.zIndex);
        stringBuffer.append(", level=");
        stringBuffer.append(this.level);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
