package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.engine.jni.models.IconImageInfo;

/* loaded from: classes9.dex */
public interface yd {
    public static final String a = "compass.png";
    public static final String b = "compass_dark.png";

    String a(GeoPoint geoPoint);

    void a(String str, IconImageInfo iconImageInfo);

    void setOptionalResourcePath(String str);
}
