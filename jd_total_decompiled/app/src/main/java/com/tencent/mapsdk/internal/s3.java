package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.TencentMapServiceProtocol;

/* loaded from: classes9.dex */
public class s3 extends i3<e3> {
    public s3() {
        b("sdkgw.map.qq.com");
        c("sdkgw.noscan.sparta.html5.qq.com");
    }

    @Override // com.tencent.mapsdk.internal.j2
    public String name() {
        return TencentMapServiceProtocol.SERVICE_NAME_TRAFFIC_EVENT;
    }
}
