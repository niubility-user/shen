package com.tencent.mapsdk.internal;

import android.os.SystemClock;
import com.tencent.mapsdk.core.components.protocol.jce.trafficevent.Detail;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import java.util.List;

/* loaded from: classes9.dex */
public class dh {
    public long a = SystemClock.elapsedRealtime();
    public LatLngBounds b;

    /* renamed from: c  reason: collision with root package name */
    public List<Detail> f16430c;

    public dh(LatLngBounds latLngBounds, List<Detail> list) {
        this.b = latLngBounds;
        this.f16430c = list;
    }
}
