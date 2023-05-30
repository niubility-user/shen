package com.tencent.map.geolocation.fence;

import com.tencent.map.geolocation.TencentLocation;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public interface TxGeofenceManagerState {
    void add(int i2, TencentLocation tencentLocation);

    long getLastInterval();

    TencentLocation getLastLocation();

    long getLastLocationTime();

    Map<String, String> getLastSummary();

    String getLocationTimes();

    List<TencentLocation> getLocations();

    long getNextLocationTime();

    double getSpeed();

    List<Map<String, String>> getSummary();

    void reset();
}
