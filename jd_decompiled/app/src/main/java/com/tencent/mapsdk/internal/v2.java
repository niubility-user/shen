package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetMethod;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetRequest;
import com.tencent.mapsdk.internal.j2;

/* loaded from: classes.dex */
public interface v2 extends j2.a {
    @NetRequest(method = NetMethod.URL, path = "indoor_map")
    String getIndoorMapUrl();
}
