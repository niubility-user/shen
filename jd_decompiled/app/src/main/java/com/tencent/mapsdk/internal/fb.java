package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public enum fb {
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
    
    private final int a;

    fb(int i2) {
        this.a = i2;
    }

    public static fb a(int i2) {
        fb[] values = values();
        for (int i3 = 0; i3 < 11; i3++) {
            fb fbVar = values[i3];
            if (fbVar.a == i2) {
                return fbVar;
            }
        }
        return NONE;
    }

    public int a() {
        return this.a;
    }
}
