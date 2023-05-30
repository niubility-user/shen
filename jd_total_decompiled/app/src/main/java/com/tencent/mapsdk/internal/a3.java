package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetMethod;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetRequest;
import com.tencent.mapsdk.internal.j2;

/* loaded from: classes.dex */
public interface a3 extends j2.a {
    @NetRequest(method = NetMethod.POST, path = "rttserverex/", userAgent = "sosomap navsns")
    NetResponse rttData(byte[] bArr);
}
