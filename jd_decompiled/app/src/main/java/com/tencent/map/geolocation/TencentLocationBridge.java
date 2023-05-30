package com.tencent.map.geolocation;

import android.content.Context;
import android.os.Looper;

/* loaded from: classes9.dex */
public interface TencentLocationBridge {
    String getBuild();

    int getCoordinateType();

    TencentLocation getLastKnownLocation();

    TencentLocation getPosition();

    String getVersion();

    void init(Context context);

    boolean isSupport();

    void removeUpdates(TencentLocationListener tencentLocationListener);

    int requestLocationUpdates(TencentLocationRequest tencentLocationRequest, TencentLocationListener tencentLocationListener, Looper looper);

    int requestLocationWithScene(int i2, TencentLocationListener tencentLocationListener);

    int requestSingleFreshLocation(TencentLocationRequest tencentLocationRequest, TencentLocationListener tencentLocationListener, Looper looper);

    void setCoordinateType(int i2);

    void setDebuggable(boolean z);

    void setDeviceID(Context context, String str);

    int startDrEngine(int i2);

    boolean startIndoorLocation();

    boolean stopIndoorLocation();

    void stopLocationWithScene(int i2, TencentLocationListener tencentLocationListener);

    void terminateDrEngine();

    void triggerCodeGuarder(boolean z);
}
