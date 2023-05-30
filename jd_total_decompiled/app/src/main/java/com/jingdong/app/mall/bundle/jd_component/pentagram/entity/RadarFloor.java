package com.jingdong.app.mall.bundle.jd_component.pentagram.entity;

import java.util.Map;

/* loaded from: classes2.dex */
public class RadarFloor {
    private Map<String, Object> extraData;
    private RadarEntity radarEntity;
    private VisualData visualData;

    public Map<String, Object> getExtraData() {
        return this.extraData;
    }

    public RadarEntity getRadarEntity() {
        return this.radarEntity;
    }

    public VisualData getVisualData() {
        return this.visualData;
    }

    public void setExtraData(Map<String, Object> map) {
        this.extraData = map;
    }

    public void setRadarEntity(RadarEntity radarEntity) {
        this.radarEntity = radarEntity;
    }

    public void setVisualData(VisualData visualData) {
        this.visualData = visualData;
    }
}
