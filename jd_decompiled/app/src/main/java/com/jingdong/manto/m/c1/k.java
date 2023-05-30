package com.jingdong.manto.m.c1;

import android.content.Context;
import com.jingdong.manto.utils.MantoUtils;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class k extends com.jingdong.manto.m.d {
    public void a(com.jingdong.manto.f fVar) {
        synchronized (k.class) {
            HashMap hashMap = new HashMap();
            Context a = com.jingdong.manto.c.a();
            hashMap.put("isConnected", Boolean.valueOf(MantoUtils.isConnected(a)));
            hashMap.put("networkType", MantoUtils.getNetworkType(a));
            a(fVar, 0).a(hashMap).a();
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "onNetworkStatusChange";
    }
}
