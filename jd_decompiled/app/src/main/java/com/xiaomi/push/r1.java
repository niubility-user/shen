package com.xiaomi.push;

import android.content.Context;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes11.dex */
public class r1 {
    public void a(Context context, List<q1> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        p1.g("upload size = " + list.size());
        String d = com.xiaomi.push.service.t2.d(context);
        for (q1 q1Var : list) {
            HashMap hashMap = new HashMap();
            hashMap.put("count", Integer.valueOf(q1Var.a()));
            hashMap.put("host", q1Var.c());
            hashMap.put("network_state", Integer.valueOf(q1Var.g()));
            hashMap.put("reason", Integer.valueOf(q1Var.m()));
            hashMap.put("ping_interval", Long.valueOf(q1Var.b()));
            hashMap.put("network_type", Integer.valueOf(q1Var.q()));
            hashMap.put("wifi_digest", q1Var.i());
            hashMap.put("connected_network_type", Integer.valueOf(q1Var.u()));
            hashMap.put("duration", Long.valueOf(q1Var.h()));
            hashMap.put("disconnect_time", Long.valueOf(q1Var.n()));
            hashMap.put("connect_time", Long.valueOf(q1Var.r()));
            hashMap.put("xmsf_vc", Integer.valueOf(q1Var.w()));
            hashMap.put("android_vc", Integer.valueOf(q1Var.y()));
            hashMap.put("uuid", d);
            p4.b().a("disconnection_event", hashMap);
        }
    }
}
