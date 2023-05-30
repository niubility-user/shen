package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetMethod;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetRequest;
import com.tencent.mapsdk.internal.j2;

/* loaded from: classes.dex */
public interface z2 extends j2.a {
    @NetRequest(constQuery = "ctrlpf=vector&ctrlmb=and", method = NetMethod.GET, queryKeys = {"apikey", "ver", "ctrlver", "uk", "frontier", "scenetype", "mpt"})
    NetResponse checkAuth(String str, String str2, int i2, String str3, int i3, int i4, int i5);
}
