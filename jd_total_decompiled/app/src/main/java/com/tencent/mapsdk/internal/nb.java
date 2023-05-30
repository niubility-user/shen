package com.tencent.mapsdk.internal;

import android.text.TextUtils;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;

/* loaded from: classes9.dex */
public class nb extends hb {
    @Override // com.tencent.mapsdk.internal.hb
    public byte[] e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        NetResponse doGet = NetManager.getInstance().builder().url(str).doGet();
        return (doGet == null || !doGet.available()) ? super.e(str) : doGet.data;
    }
}
