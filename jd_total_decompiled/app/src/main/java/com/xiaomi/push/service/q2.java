package com.xiaomi.push.service;

import com.xiaomi.push.b7;
import com.xiaomi.push.g7;
import java.util.List;

/* loaded from: classes11.dex */
public class q2 implements b7 {
    private final XMPushService a;

    public q2(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    @Override // com.xiaomi.push.b7
    public void a(List<g7> list, String str, String str2) {
        this.a.a(new r2(this, 4, str, list, str2));
    }
}
