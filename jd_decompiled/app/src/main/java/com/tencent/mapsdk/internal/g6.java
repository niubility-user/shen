package com.tencent.mapsdk.internal;

import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.shell.events.NetFlowEvent;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class g6 extends qb {
    public g6() {
    }

    public g6(HashMap<String, String> hashMap) {
        super(hashMap);
    }

    public void a() {
        u.f().a(new NetFlowEvent(this.f17048c));
    }

    @Override // com.tencent.mapsdk.internal.qb, com.tencent.map.tools.net.processor.ResponseProcessor
    public void onResponse(NetResponse netResponse) {
        super.onResponse(netResponse);
        a();
    }
}
