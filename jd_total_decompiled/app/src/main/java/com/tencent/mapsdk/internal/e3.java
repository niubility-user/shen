package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetMethod;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetRequest;
import com.tencent.mapsdk.internal.j2;

/* loaded from: classes.dex */
public interface e3 extends j2.a {
    @NetRequest(constQuery = "p_ver=1", method = NetMethod.GET, path = "map/traffic/event", queryKeys = {"param"})
    NetResponse mapTrafficEvent(String str);
}
