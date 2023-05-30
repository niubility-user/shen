package com.tencent.tencentmap.mapsdk.maps.model;

import com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng;

/* loaded from: classes9.dex */
public class VectorHeatAggregationUnit {
    private LatLng mCenter;
    private double mIntensity;
    private WeightedLatLng[] mNodes;

    private VectorHeatAggregationUnit() {
    }

    private VectorHeatAggregationUnit(LatLng latLng, double d, WeightedLatLng[] weightedLatLngArr) {
        this.mCenter = latLng;
        this.mIntensity = d;
        this.mNodes = weightedLatLngArr;
    }

    public LatLng getCenter() {
        return this.mCenter;
    }

    public double getIntensity() {
        return this.mIntensity;
    }

    public WeightedLatLng[] getNodes() {
        return this.mNodes;
    }
}
