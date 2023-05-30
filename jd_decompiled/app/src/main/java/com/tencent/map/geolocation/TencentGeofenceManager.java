package com.tencent.map.geolocation;

import android.app.PendingIntent;
import android.content.Context;
import c.t.m.g.a4;

/* loaded from: classes9.dex */
public class TencentGeofenceManager {
    public a4 a;

    public TencentGeofenceManager(Context context) {
        if (TencentLocationManager.getUserAgreePrivacy()) {
            this.a = new a4(context);
        }
    }

    public void addFence(TencentGeofence tencentGeofence, PendingIntent pendingIntent) {
        if (TencentLocationManager.getUserAgreePrivacy()) {
            this.a.i(tencentGeofence, pendingIntent);
        }
    }

    public void destroy() {
        if (TencentLocationManager.getUserAgreePrivacy()) {
            this.a.t();
        }
    }

    public void removeAllFences() {
        if (TencentLocationManager.getUserAgreePrivacy()) {
            this.a.z();
        }
    }

    public void removeFence(TencentGeofence tencentGeofence) {
        if (TencentLocationManager.getUserAgreePrivacy()) {
            this.a.h(tencentGeofence);
        }
    }

    public void removeFence(String str) {
        if (TencentLocationManager.getUserAgreePrivacy()) {
            this.a.s(str);
        }
    }
}
