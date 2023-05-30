package com.xiaomi.push;

import android.content.Context;
import com.jingdong.common.database.table.NavigationBarTable;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class a2 {
    public void a(Context context, z1 z1Var) {
        if (z1Var == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("off_up_ct", Integer.valueOf(z1Var.a()));
        hashMap.put("off_dn_ct", Integer.valueOf(z1Var.e()));
        hashMap.put("off_ping_ct", Integer.valueOf(z1Var.i()));
        hashMap.put("off_pong_ct", Integer.valueOf(z1Var.m()));
        hashMap.put("off_dur", Long.valueOf(z1Var.b()));
        hashMap.put("on_up_ct", Integer.valueOf(z1Var.q()));
        hashMap.put("on_dn_ct", Integer.valueOf(z1Var.s()));
        hashMap.put("on_ping_ct", Integer.valueOf(z1Var.u()));
        hashMap.put("on_pong_ct", Integer.valueOf(z1Var.w()));
        hashMap.put("on_dur", Long.valueOf(z1Var.f()));
        hashMap.put(NavigationBarTable.FIELD_START_TIME, Long.valueOf(z1Var.j()));
        hashMap.put(NavigationBarTable.FIELD_END_TIME, Long.valueOf(z1Var.n()));
        hashMap.put("xmsf_vc", Integer.valueOf(z1Var.y()));
        hashMap.put("android_vc", Integer.valueOf(z1Var.A()));
        hashMap.put("uuid", com.xiaomi.push.service.t2.d(context));
        p4.b().a("power_consumption_stats", hashMap);
    }
}
