package com.tencent.map.lib.models;

import androidx.annotation.Keep;

@Keep
/* loaded from: classes9.dex */
public enum DataSource {
    NONE(-1),
    SATELLITE(0),
    DEM(1),
    MAP(2),
    STREET_VIEW_ROAD(3),
    TRAFFIC_NETWORK(4),
    INDOOR_BUILDINGS(5),
    LANDMARK(6),
    TILE_OVERLAY(7),
    INDOOR_CONFIG(8),
    NUM(9);
    
    private final int mValue;

    DataSource(int i2) {
        this.mValue = i2;
    }

    public static DataSource get(int i2) {
        DataSource[] values = values();
        for (int i3 = 0; i3 < 11; i3++) {
            DataSource dataSource = values[i3];
            if (dataSource.mValue == i2) {
                return dataSource;
            }
        }
        return NONE;
    }

    public int getValue() {
        return this.mValue;
    }
}
