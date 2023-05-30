package com.tencent.mapsdk.internal;

import com.facebook.common.util.UriUtil;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.core.components.protocol.jce.rtt.RttResponse;

/* loaded from: classes9.dex */
public class yg extends hb {
    private static final int b = 3;

    /* renamed from: c  reason: collision with root package name */
    private static yg f17518c;

    private byte[] a(RttResponse rttResponse) {
        if (rttResponse == null) {
            return null;
        }
        return rttResponse.result;
    }

    private RttResponse b(byte[] bArr) {
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                NetResponse rttData = ((a3) ((o3) l2.a(o3.class)).d()).rttData(bArr);
                if (rttData != null && rttData.data != null) {
                    f fVar = new f();
                    fVar.b("UTF-8");
                    fVar.a(rttData.data);
                    return (RttResponse) fVar.c(UriUtil.LOCAL_RESOURCE_SCHEME);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static synchronized yg c() {
        yg ygVar;
        synchronized (yg.class) {
            if (f17518c == null) {
                f17518c = new yg();
            }
            ygVar = f17518c;
        }
        return ygVar;
    }

    @Override // com.tencent.mapsdk.internal.hb
    public byte[] a(byte[] bArr) {
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    return a(b(bArr));
                }
            } catch (Throwable unused) {
            }
        }
        return null;
    }
}
