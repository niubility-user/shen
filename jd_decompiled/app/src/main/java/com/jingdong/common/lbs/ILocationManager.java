package com.jingdong.common.lbs;

import java.util.Map;

/* loaded from: classes5.dex */
public interface ILocationManager {
    public static final int LOCATION_TIME_SPAN = 5000;

    /* loaded from: classes5.dex */
    public interface UpdateLocationListener {
        void onFinish(Map<String, Double> map);
    }

    boolean isOpenGps();

    void requestLocation();

    void requestLocationAuto();

    void setUpdateLocationListener(UpdateLocationListener updateLocationListener);

    void stopLocation();
}
